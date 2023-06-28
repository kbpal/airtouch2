package au.com.polyaire.airtouch2.comm;

import au.com.polyaire.airtouch2.C0377R;
import au.com.polyaire.airtouch2.MessageInBytes;
import au.com.polyaire.airtouch2.data.ExchData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

/* loaded from: classes.dex */
public class InternetConnection extends DeviceConnection {
    private static final String CLIENT_KEY_STORE_PASSWORD = "testtest";
    private static final String CLIENT_TRUST_KEY_STORE_PASSWORD = "testtest";
    private static final String sDevSvrDomain = "www.airtouch.com.au";
    private static final int sDevSvrPort = 9000;
    protected int mConnectionState = 0;
    protected String mDevID;
    protected String mPassword;
    protected SSLSocket mSocket;

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    public String getServerIP() {
        return "N/A";
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    public String getServerMac() {
        return "N/A";
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    public boolean isInternetMode() {
        return true;
    }

    public InternetConnection(String str, String str2) {
        this.mDevID = str;
        this.mPassword = str2;
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
        this.mSocket = null;
        this.mConnectionState = 1;
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected void doConnect() throws Exception {
        SSLContext sSLContext = SSLContext.getInstance("TLSv1");
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        KeyStore keyStore2 = KeyStore.getInstance("BKS");
        keyStore.load(this.mContext.getResources().openRawResource(C0377R.raw.atclientp12), "testtest".toCharArray());
        keyStore2.load(this.mContext.getResources().openRawResource(C0377R.raw.tclient), "testtest".toCharArray());
        keyManagerFactory.init(keyStore, "testtest".toCharArray());
        trustManagerFactory.init(keyStore2);
        sSLContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        this.mSocket = (SSLSocket) sSLContext.getSocketFactory().createSocket(sDevSvrDomain, sDevSvrPort);
        this.mSocket.setEnabledProtocols(new String[]{"TLSv1"});
    }

    @Override // au.com.polyaire.airtouch2.comm.DeviceConnection
    protected boolean afterConnect() {
        if (this.mSocket == null || !this.mSocket.isConnected()) {
            this.mConnectionState = 0;
            return false;
        }
        MessageInBytes messageInBytes = new MessageInBytes();
        sendData(messageInBytes.SetAuthenticationMessage(this.mDevID, this.mPassword));
        System.out.println("send Authentication Message");
        waitingState(1);
        sendData(messageInBytes.GetInitMsg());
        System.out.println("send Init Message");
        waitingState(2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void waitingState(int i) {
        while (i == this.mConnectionState) {
            try {
                Thread.sleep(100L);
            } catch (Exception unused) {
            }
        }
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
        if (i <= 0) {
            if (-1 == i) {
                this.mConnectionState = 0;
                ExchData.mErrorTitle = "Airtouch Module Unresponsive";
                ExchData.mErrorMsg = "Please check your Airtouch unit.";
                disconnect();
                return;
            }
            return;
        }
        int onRecv = dataFormatter.onRecv(bArr, i);
        if (11 == onRecv) {
            this.mConnectionState = 2;
        } else if (10 == onRecv) {
            this.mConnectionState = 0;
            ExchData.mErrorTitle = "Authentication Error";
            ExchData.mErrorMsg = "Check Airtouch ID and password are correct.";
            disconnect();
        } else if (12 == onRecv) {
            this.mConnectionState = 0;
            ExchData.mErrorTitle = "Connection Error";
            ExchData.mErrorMsg = "Please ensure Airtouch unit is powered and connected.";
            disconnect();
        } else if (1 == onRecv && 2 == this.mConnectionState) {
            this.mConnectionState = 0;
        }
    }
}
