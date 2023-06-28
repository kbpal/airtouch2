package au.com.polyaire.airtouch2.comm;

import android.app.Activity;
import au.com.polyaire.airtouch2.data.ExchData;
import java.net.SocketTimeoutException;

/* loaded from: classes.dex */
public abstract class DeviceConnection {
    private Thread mConnThread;
    protected Activity mContext;
    private Thread mRecvThread;
    protected DataFormatter mDataFormatter = new DataFormatter();
    protected ConnState mConnState = ConnState.DISCONNECT;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public enum ConnState {
        DISCONNECT,
        CONNECTING,
        CONNECTED,
        DISCONNECTING
    }

    protected abstract boolean afterConnect();

    protected abstract void beforeConnect();

    protected abstract boolean checkConnected();

    protected abstract void doBreak();

    protected abstract void doConnect() throws Exception;

    protected abstract void doReceive(DataFormatter dataFormatter) throws Exception;

    public abstract String getServerIP();

    public abstract String getServerMac();

    public abstract boolean isInternetMode();

    protected abstract void sendDataProc(byte[] bArr, int i);

    public Activity getContext() {
        return this.mContext;
    }

    public void setContext(Activity activity) {
        this.mContext = activity;
    }

    public void connect() {
        if (this.mConnState == ConnState.CONNECTING || this.mConnState == ConnState.CONNECTED) {
            return;
        }
        while (this.mConnState == ConnState.DISCONNECTING) {
            try {
                Thread.sleep(1L);
            } catch (Exception unused) {
            }
        }
        this.mConnState = ConnState.CONNECTING;
        beforeConnect();
        this.mRecvThread = new Thread(new ReceiveProc());
        this.mConnThread = new Thread(new ConnectProc());
        this.mRecvThread.start();
        this.mConnThread.start();
    }

    public boolean isConnected() {
        if (this.mConnState == ConnState.CONNECTED) {
            return checkConnected();
        }
        return false;
    }

    public boolean isConnecting() {
        return this.mConnState == ConnState.CONNECTING;
    }

    public void disconnect() {
        doBreak();
        this.mConnState = ConnState.DISCONNECTING;
    }

    public void sendData(byte[] bArr) {
        sendData(bArr, -1);
    }

    public void sendData(byte[] bArr, int i) {
        new Thread(new SendDataRunable(bArr, i, this)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SendDataRunable implements Runnable {
        private byte[] mData;
        private int mLen;
        private DeviceConnection mParent;

        public SendDataRunable(byte[] bArr, int i, DeviceConnection deviceConnection) {
            this.mData = bArr;
            this.mLen = i;
            this.mParent = deviceConnection;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mParent.sendDataProc(this.mData, this.mLen);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ConnectProc implements Runnable {
        private ConnectProc() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DeviceConnection.this.doConnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (DeviceConnection.this.afterConnect()) {
                DeviceConnection.this.mConnState = ConnState.CONNECTED;
            } else {
                DeviceConnection.this.mConnState = ConnState.DISCONNECT;
            }
            DeviceConnection.this.mConnThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ReceiveProc implements Runnable {
        private ReceiveProc() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                if (DeviceConnection.this.mConnState != ConnState.CONNECTING && DeviceConnection.this.mConnState != ConnState.CONNECTED) {
                    DeviceConnection.this.mRecvThread = null;
                    DeviceConnection.this.mConnState = ConnState.DISCONNECT;
                    return;
                }
                try {
                    DeviceConnection.this.doReceive(DeviceConnection.this.mDataFormatter);
                } catch (SocketTimeoutException unused) {
                    ExchData.mErrorTitle = "Timeout";
                    ExchData.mErrorMsg = "Connection timeout, please try again.";
                } catch (Exception unused2) {
                }
            }
        }
    }
}
