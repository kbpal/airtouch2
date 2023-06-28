package au.com.polyaire.airtouch2;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

/* loaded from: classes.dex */
public class SysParametersActivity extends TabActivity {
    TextView title;

    @Override // android.app.ActivityGroup, android.app.Activity
    protected void onCreate(Bundle bundle) {
        Intent intent = new Intent(this, NamingActivity.class);
        Intent intent2 = new Intent(this, ServiceActivity.class);
        Intent intent3 = new Intent(this, SystemActivity.class);
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(C0377R.layout.tab_layout);
        getWindow().setFeatureInt(7, C0377R.layout.systeminfotitle);
        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("System").setIndicator("System Info").setContent(intent3));
        tabHost.addTab(tabHost.newTabSpec("Naming").setIndicator("Naming").setContent(intent));
        tabHost.addTab(tabHost.newTabSpec("Service").setIndicator("Service").setContent(intent2));
        tabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
    }
}
