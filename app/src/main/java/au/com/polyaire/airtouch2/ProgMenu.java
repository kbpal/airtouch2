package au.com.polyaire.airtouch2;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;

/* loaded from: classes.dex */
public class ProgMenu extends TabActivity {
    Intent intent;
    Intent intent2;
    Intent intent3;
    Intent intent4;

    @Override // android.app.ActivityGroup, android.app.Activity
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(C0377R.layout.tab_layout);
        getWindow().setFeatureInt(7, C0377R.layout.programtitle);
        String[] strArr = new String[97];
        this.intent = getIntent();
        String stringExtra = this.intent.getStringExtra("programno");
        String[] stringArrayExtra = this.intent.getStringArrayExtra("programs");
        for (int i = 0; i <= 95; i++) {
            strArr[i] = stringArrayExtra[i];
        }
        strArr[96] = stringExtra;
        this.intent2 = new Intent();
        this.intent2.setClass(this, ProgramInfoMF.class);
        this.intent2.putExtra("programno", stringExtra);
        this.intent2.putExtra("programs", strArr);
        this.intent3 = new Intent(this, ProgramInfoSat.class);
        this.intent3.putExtra("programno", stringExtra);
        this.intent3.putExtra("programs", strArr);
        this.intent4 = new Intent(this, ProgramInfoSun.class);
        this.intent4.putExtra("programno", stringExtra);
        this.intent4.putExtra("programs", strArr);
        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("Week1").setIndicator("Mon-Fri").setContent(this.intent2));
        tabHost.addTab(tabHost.newTabSpec("Week2").setIndicator("Saturday").setContent(this.intent3));
        tabHost.addTab(tabHost.newTabSpec("Week3").setIndicator("Sunday").setContent(this.intent4));
        tabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
    }
}
