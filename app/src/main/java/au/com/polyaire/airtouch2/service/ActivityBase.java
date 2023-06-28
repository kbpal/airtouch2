package au.com.polyaire.airtouch2.service;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import au.com.polyaire.airtouch2.C0377R;
import au.com.polyaire.airtouch2.FileHelper;
import au.com.polyaire.airtouch2.HelpActivity;
import au.com.polyaire.airtouch2.InitActivity;
import au.com.polyaire.airtouch2.NamingActivity;
import au.com.polyaire.airtouch2.ProgramActivity;
import au.com.polyaire.airtouch2.ServiceActivity;
import au.com.polyaire.airtouch2.SysParametersActivity;
import au.com.polyaire.airtouch2.SystemActivity;
import au.com.polyaire.airtouch2.WifiMainActivity;
import au.com.polyaire.airtouch2.ZoneActivity;
import au.com.polyaire.airtouch2.comm.ConnCheckTool;
import au.com.polyaire.airtouch2.data.ExchData;

/* loaded from: classes.dex */
public class ActivityBase extends Activity {
    PopupWindow mPopMenu = null;

    protected int getMainViewID() {
        return C0377R.id.main;
    }

    protected void onRecvMessage(int i, int i2) {
    }

    protected void onSubResume() {
    }

    protected void onUpdateUI() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        ActivityManageTool.addActivity(this);
        try {
            ExchData.mConnection.setContext(this);
            ConnCheckTool.instance().startCheck();
            if (this.mPopMenu == null) {
                initPopupWindow();
            }
            if (FileHelper.needInit()) {
                FileHelper.init(getExternalFilesDir(null));
            }
            showPopMenu();
            updateUI();
            onSubResume();
        } catch (Exception unused) {
            startActivity(new Intent(this, InitActivity.class));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showPopMenu() {
        View findViewById = findViewById(getMainViewID());
        if (findViewById != null) {
            findViewById.post(new Runnable() { // from class: au.com.polyaire.airtouch2.service.ActivityBase.1
                @Override // java.lang.Runnable
                public void run() {
                    ActivityBase.this.mPopMenu.showAtLocation(ActivityBase.this.findViewById(ActivityBase.this.getMainViewID()), 80, 0, 0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hidePopMenu() {
        if (this.mPopMenu == null || !this.mPopMenu.isShowing()) {
            return;
        }
        this.mPopMenu.dismiss();
    }

    @Override // android.app.Activity
    protected void onPause() {
        ConnCheckTool.instance().stopCheck();
        hidePopMenu();
        super.onPause();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        ActivityManageTool.onExit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MessageRunnable implements Runnable {
        int mRes;
        int mType;

        MessageRunnable(int i, int i2) {
            this.mType = i;
            this.mRes = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActivityBase.this.onRecvMessage(this.mType, this.mRes);
        }
    }

    public void onMessage(int i, int i2) {
        runOnUiThread(new MessageRunnable(i, i2));
    }

    public void updateUI() {
        runOnUiThread(new Runnable() { // from class: au.com.polyaire.airtouch2.service.ActivityBase.2
            @Override // java.lang.Runnable
            public void run() {
                ActivityBase.this.onUpdateUI();
            }
        });
    }

    protected void initPopupMenuEvt(View view, int i, Class cls) {
        if (cls.isInstance(this)) {
            view.findViewById(i).setBackgroundResource(C0377R.C0378drawable.menubgselected);
        } else {
            view.findViewById(i).setOnClickListener(new OnMenuClickListener(cls));
        }
    }

    protected void initPopupMenuSysEvt(View view) {
        if (SystemActivity.class.isInstance(this) || NamingActivity.class.isInstance(this) || ServiceActivity.class.isInstance(this)) {
            view.findViewById(C0377R.id.obssysparameters).setBackgroundResource(C0377R.C0378drawable.menubgselected);
        } else {
            view.findViewById(C0377R.id.obssysparameters).setOnClickListener(new OnMenuClickListener(SysParametersActivity.class));
        }
    }

    protected void initPopupWindow() {
        View inflate = getLayoutInflater().inflate(C0377R.layout.popmenu, (ViewGroup) null);
        this.mPopMenu = new PopupWindow(inflate, -1, -2);
        this.mPopMenu.setOutsideTouchable(false);
        initPopupMenuEvt(inflate, C0377R.id.home, WifiMainActivity.class);
        initPopupMenuEvt(inflate, C0377R.id.zone, ZoneActivity.class);
        initPopupMenuEvt(inflate, C0377R.id.program, ProgramActivity.class);
        initPopupMenuEvt(inflate, C0377R.id.help, HelpActivity.class);
        initPopupMenuSysEvt(inflate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class OnMenuClickListener implements View.OnClickListener {
        Class mNext;

        OnMenuClickListener(Class cls) {
            this.mNext = cls;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityBase.this.mPopMenu.dismiss();
            ActivityBase.this.startActivity(new Intent(ActivityBase.this, this.mNext));
        }
    }
}
