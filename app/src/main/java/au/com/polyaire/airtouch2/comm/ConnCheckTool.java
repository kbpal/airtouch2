package au.com.polyaire.airtouch2.comm;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.Toast;
import au.com.polyaire.airtouch2.InitActivity;
import au.com.polyaire.airtouch2.data.ExchData;
import java.io.PrintStream;
import java.util.Date;

/* loaded from: classes.dex */
public class ConnCheckTool {
    private static ConnCheckTool mInstance = new ConnCheckTool();
    protected static final long sMaxWaitTime = 20000;
    protected CountDownTimer mNormalTimer;
    protected long mRetryStartTime;
    public boolean mShowInfo = false;
    protected CountDownTimer mWaitTimer;

    public static ConnCheckTool instance() {
        if (mInstance == null) {
            mInstance = new ConnCheckTool();
        }
        return mInstance;
    }

    private ConnCheckTool() {
    }

    public void stopCheck() {
        if (this.mNormalTimer != null) {
            this.mNormalTimer.cancel();
        }
        if (this.mWaitTimer != null) {
            this.mWaitTimer.cancel();
        }
        this.mNormalTimer = null;
        this.mWaitTimer = null;
    }

    public void startCheck() {
        if (this.mNormalTimer != null) {
            this.mNormalTimer.cancel();
            this.mNormalTimer.start();
            return;
        }
        this.mNormalTimer = new CountDownTimer(200L, 200L) { // from class: au.com.polyaire.airtouch2.comm.ConnCheckTool.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ConnCheckTool.this.onCheck();
            }
        };
        this.mNormalTimer.start();
    }

    protected void onCheck() {
        if (ExchData.mConnection == null || !ExchData.isConnected()) {
            startRetry();
        } else {
            this.mNormalTimer.start();
        }
    }

    protected void startRetry() {
        this.mRetryStartTime = new Date().getTime();
        if (ExchData.mConnection != null) {
            PrintStream printStream = System.out;
            printStream.println("Disconnected, start try connect again. " + this.mRetryStartTime);
            ExchData.mConnection.connect();
        }
        if (this.mWaitTimer != null) {
            this.mWaitTimer.cancel();
            this.mWaitTimer.start();
            return;
        }
        this.mWaitTimer = new CountDownTimer(200L, 200L) { // from class: au.com.polyaire.airtouch2.comm.ConnCheckTool.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ConnCheckTool.this.doWaitConn();
            }
        };
        this.mWaitTimer.start();
    }

    protected void doWaitConn() {
        if (ExchData.mConnection == null) {
            return;
        }
        if (ExchData.mConnection.isConnected()) {
            System.out.println("Connected again. ");
            this.mWaitTimer.cancel();
            startCheck();
            if (this.mShowInfo) {
                this.mShowInfo = false;
                Toast.makeText(ExchData.mConnection.getContext(), "Connected.", 0).show();
            }
        } else if (new Date().getTime() - this.mRetryStartTime > sMaxWaitTime) {
            gotoInitPage();
        } else {
            if (!ExchData.mConnection.isConnected()) {
                System.out.println("Connect not success, try again.");
                ExchData.mConnection.connect();
            }
            this.mWaitTimer.start();
        }
    }

    protected void gotoInitPage() {
        ExchData.mErrorTitle = "Connection Lost";
        ExchData.mErrorMsg = "Please check your network";
        Activity context = ExchData.mConnection.getContext();
        context.startActivity(new Intent(context, InitActivity.class));
    }
}
