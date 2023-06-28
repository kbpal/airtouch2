package au.com.polyaire.airtouch2.comm;

import android.app.Activity;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityBase;
import au.com.polyaire.airtouch2.service.ActivityProgSettingBase;
import au.com.polyaire.airtouch2.service.WifiCommService;

/* loaded from: classes.dex */
public class DataFormatter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private byte[] mBuffer = new byte[395];
    private int mBufLen = 0;
    protected WifiCommService commSvr = new WifiCommService();

    public int onRecv(byte[] bArr, int i) {
        int i2 = 0;
        String str = "";
        for (int i3 = 0; i3 < i; i3++) {
            str = str + String.format("%02X ", Byte.valueOf(bArr[i3]));
        }
        System.out.println("read: len=" + i + ", " + str);
        int i4 = i;
        int i5 = 0;
        while (i4 > 0) {
            while (this.mBufLen < 395 && i5 < i) {
                this.mBuffer[this.mBufLen] = bArr[i5];
                this.mBufLen++;
                i5++;
            }
            i4 = i - i5;
            int unpackBuffer = unpackBuffer();
            if (unpackBuffer != -999) {
                i2 = unpackBuffer;
            }
        }
        return i2;
    }

    private int unpackBuffer() {
        if (this.mBufLen == 395) {
            if (isInterMsgResponse()) {
                return unpackInterMsgResponse();
            }
            unpackStateMsg();
            this.mBufLen = 0;
            return 1;
        }
        return -999;
    }

    private boolean isInterMsgResponse() {
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            if (this.mBuffer[i2 + 100] == 0) {
                i++;
            }
        }
        return 8 == i;
    }

    private int unpackInterMsgResponse() {
        byte b = this.mBuffer[2];
        byte b2 = this.mBuffer[3];
        this.mBufLen -= 395;
        for (int i = 0; i < this.mBufLen; i++) {
            this.mBuffer[i] = this.mBuffer[i + 395];
        }
        if (1 == b) {
            return b2 + 10;
        }
        onResponseMsg(b, b2);
        return 0;
    }

    protected void onResponseMsg(int i, int i2) {
        if (ExchData.mConnection == null) {
            return;
        }
        Activity context = ExchData.mConnection.getContext();
        if (ActivityBase.class.isInstance(context)) {
            ((ActivityBase) context).onMessage(i, i2);
        }
    }

    protected void updateUI() {
        if (ExchData.mConnection == null) {
            return;
        }
        Activity context = ExchData.mConnection.getContext();
        if (ActivityBase.class.isInstance(context)) {
            ((ActivityBase) context).updateUI();
        } else if (ActivityProgSettingBase.class.isInstance(context)) {
            ((ActivityProgSettingBase) context).updateUI();
        }
    }

    private void unpackStateMsg() {
        if (ExchData.isWaitingForNormalMsg()) {
            onResponseMsg(4, 0);
            return;
        }
        this.commSvr.Updata(this.mBuffer, this.mBufLen);
        updateUI();
    }
}
