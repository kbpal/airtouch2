package au.com.polyaire.airtouch2.comm;

import android.app.Activity;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class LocalDevices {
    private static final String sMatchData = "HF-A11ASSISTHREAD";
    private static final long sMaxCheckTime = 10000;
    private static final int sRecvPort = 48899;
    private static final long sResendTime = 2000;
    private Timer mEndCheckTimer;
    private String mIP;
    private String mMac;
    private Activity mParent;
    private int mDataSent = 0;
    private long mStartTime = 0;
    private boolean mIsSearching = false;
    private DatagramSocket mRecvSocket = null;

    static /* synthetic */ int access$308(LocalDevices localDevices) {
        int i = localDevices.mDataSent;
        localDevices.mDataSent = i + 1;
        return i;
    }

    public boolean startSearching(Activity activity) {
        if (this.mIsSearching) {
            return true;
        }
        this.mParent = activity;
        this.mStartTime = new Date().getTime();
        this.mIsSearching = true;
        this.mDataSent = 0;
        this.mIP = "";
        this.mMac = "";
        this.mEndCheckTimer = new Timer();
        new Thread(new WaitDataThread()).start();
        doSendData();
        this.mEndCheckTimer.schedule(new TimerTask() { // from class: au.com.polyaire.airtouch2.comm.LocalDevices.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                LocalDevices.this.doCheckEnd();
            }
        }, sResendTime, 12000L);
        return true;
    }

    public void stopSearching() {
        if (this.mIsSearching) {
            endSearching();
        }
    }

    private void endSearching() {
        if (this.mEndCheckTimer != null) {
            this.mEndCheckTimer.cancel();
        }
        this.mEndCheckTimer = null;
        if (this.mRecvSocket != null) {
            this.mRecvSocket.close();
        }
        this.mRecvSocket = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCheckEnd() {
        if (!this.mIsSearching) {
            endSearching();
            return;
        }
        if (this.mDataSent < 3) {
            doSendData();
        }
        if (new Date().getTime() - this.mStartTime > sMaxCheckTime) {
            this.mIsSearching = false;
            endSearching();
        }
    }

    private void doSendData() {
        new Timer().schedule(new TimerTask() { // from class: au.com.polyaire.airtouch2.comm.LocalDevices.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                try {
                    PrintStream printStream = System.out;
                    printStream.println("Send to : " + InternetTool.getBroadcastIP(LocalDevices.this.mParent));
                    InetAddress byName = InetAddress.getByName(InternetTool.getBroadcastIP(LocalDevices.this.mParent));
                    DatagramSocket datagramSocket = new DatagramSocket();
                    datagramSocket.send(new DatagramPacket(LocalDevices.sMatchData.getBytes(), LocalDevices.sMatchData.length(), byName, (int) LocalDevices.sRecvPort));
                    datagramSocket.close();
                    LocalDevices.access$308(LocalDevices.this);
                } catch (Exception unused) {
                }
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doWaitData() {
        String[] split;
        try {
            byte[] bArr = new byte[1024];
            this.mRecvSocket = new DatagramSocket((int) sRecvPort);
            DatagramPacket datagramPacket = new DatagramPacket(bArr, 1024);
            while (true) {
                this.mRecvSocket.receive(datagramPacket);
                if (datagramPacket.getLength() > 0) {
                    String str = new String(bArr, 0, datagramPacket.getLength());
                    PrintStream printStream = System.out;
                    printStream.println("Read: " + str);
                    split = str.split(",");
                    if (split != null && split.length == 3 && split[2].equalsIgnoreCase("AirTouch2")) {
                        break;
                    }
                }
            }
            this.mIP = split[0];
            this.mMac = split[1];
            endSearching();
            if (this.mRecvSocket != null) {
                this.mRecvSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.mRecvSocket.close();
            } catch (Exception unused) {
            }
        }
        this.mRecvSocket = null;
        this.mIsSearching = false;
    }

    public boolean isSearching() {
        return this.mIsSearching;
    }

    public String getIp() {
        return this.mIP;
    }

    public void setIp(String str) {
        this.mIP = str;
    }

    public String getMac() {
        return this.mMac;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WaitDataThread implements Runnable {
        private WaitDataThread() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LocalDevices.this.doWaitData();
        }
    }
}
