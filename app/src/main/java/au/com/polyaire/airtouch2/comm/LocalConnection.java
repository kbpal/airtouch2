package au.com.polyaire.airtouch2.comm;

import au.com.polyaire.airtouch2.MessageInBytes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/* loaded from: classes.dex */
public class LocalConnection extends DeviceConnection {
    private static final int sDevLocalPort = 8899;
    private String mDevIP;
    private String mDevMac;
    private boolean mInConnection = false;
    private Socket mSocket;

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    public boolean isInternetMode() {
        return false;
    }

    public LocalConnection(String str) {
        this.mDevIP = str;
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected void sendDataProc(byte[] bArr, int i) {
        if (this.mSocket == null || !this.mSocket.isConnected()) {
            return;
        }
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(this.mSocket.getOutputStream());
            if (-1 == i) {
                dataOutputStream.write(bArr);
            } else {
                dataOutputStream.write(bArr, 0, i);
            }
        } catch (Exception unused) {
        }
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected void beforeConnect() {
        this.mSocket = new Socket();
        this.mInConnection = true;
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected void doConnect() throws Exception {
        this.mSocket.connect(new InetSocketAddress(InetAddress.getByName(this.mDevIP), (int) sDevLocalPort), 5000);
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    public String getServerIP() {
        return this.mDevIP;
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    public String getServerMac() {
        return this.mDevMac;
    }

    public void setMac(String str) {
        this.mDevMac = str;
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected boolean afterConnect() {
        if (this.mSocket == null || !this.mSocket.isConnected()) {
            this.mInConnection = false;
            return false;
        }
        sendData(new MessageInBytes().GetInitMsg(), -1);
        System.out.println("send Init Message");
        while (this.mInConnection) {
            try {
                Thread.sleep(100L);
            } catch (Exception unused) {
            }
        }
        return true;
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected void doBreak() {
        if (this.mSocket != null) {
            try {
                this.mSocket.close();
            } catch (Exception unused) {
            }
            this.mSocket = null;
        }
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected boolean checkConnected() {
        return this.mSocket != null && this.mSocket.isConnected();
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected void doReceive(DataFormatter dataFormatter) throws Exception {
        int i;
        if (this.mSocket == null || !this.mSocket.isConnected()) {
            return;
        }
        this.mSocket.setSoTimeout(1000);
        byte[] bArr = new byte[1024];
        try {
            i = new DataInputStream(this.mSocket.getInputStream()).read(bArr);
        } catch (SocketTimeoutException unused) {
            i = 0;
        }
        if (i > 0) {
            if (1 == dataFormatter.onRecv(bArr, i)) {
                this.mInConnection = false;
            }
        } else if (-1 == i) {
            this.mInConnection = false;
            disconnect();
        }
    }
}
