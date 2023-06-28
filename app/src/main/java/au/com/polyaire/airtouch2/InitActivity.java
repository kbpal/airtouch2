package au.com.polyaire.airtouch2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

/* loaded from: classes.dex */
public class InitActivity extends Activity {
    private SharedPreferences settings;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.activity_init);
        FileHelper.init(getExternalFilesDir(null));
        this.settings = getSharedPreferences("Settings", 0);
        findViewById(C0377R.id.dialogButtonOK).setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.InitActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InitActivity.this.onBackPressed();
            }
        });
        onBackPressed();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
