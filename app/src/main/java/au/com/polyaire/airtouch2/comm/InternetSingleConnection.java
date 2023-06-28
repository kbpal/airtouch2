package au.com.polyaire.airtouch2.comm;

import java.io.DataInputStream;
import java.net.SocketTimeoutException;

/* loaded from: classes.dex */
public class InternetSingleConnection extends InternetConnection {
    byte[] mCmd;

    public InternetSingleConnection(byte[] bArr) {
        super("", "");
        this.mCmd = bArr;
    }

    @Override // au.com.polyaire.airtouch2.comm.InternetConnection, au.com.polyaire.airtouch2.comm.DeviceConnection
    protected boolean afterConnect() {
        if (this.mSocket == null || !this.mSocket.isConnected()) {
            this.mConnectionState = 0;
            return false;
        }
        sendData(this.mCmd);
        waitingState(1);
        return false;
    }

    @Override // au.com.polyaire.airtouch2.comm.InternetConnection, au.com.polyaire.airtouch2.comm.DeviceConnection
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
            if (-999 != dataFormatter.onRecv(bArr, i)) {
                this.mConnectionState = 1;
            }
        } else if (-1 == i) {
            this.mConnectionState = 1;
        }
    }
}
