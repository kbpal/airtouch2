package au.com.polyaire.airtouch2.service;

import android.app.Activity;
import android.content.Intent;
import au.com.polyaire.airtouch2.InitActivity;
import au.com.polyaire.airtouch2.comm.ConnCheckTool;
import au.com.polyaire.airtouch2.data.ExchData;

/* loaded from: classes.dex */
public class ActivityProgSettingBase extends Activity {
    protected void onUpdateUI() {
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (ExchData.mConnection == null) {
            startActivity(new Intent(this, InitActivity.class));
            return;
        }
        ExchData.mConnection.setContext(this);
        ConnCheckTool.instance().startCheck();
        updateUI();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        ConnCheckTool.instance().stopCheck();
    }

    public void updateUI() {
        runOnUiThread(new Runnable() { // from class: au.com.polyaire.airtouch2.service.ActivityProgSettingBase.1
            @Override // java.lang.Runnable
            public void run() {
                ActivityProgSettingBase.this.onUpdateUI();
            }
        });
    }
}
