package au.com.polyaire.airtouch2;

import android.os.Bundle;
import au.com.polyaire.airtouch2.service.ActivityBase;

/* loaded from: classes.dex */
public class HelpActivity extends ActivityBase {
    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected int getMainViewID() {
        return C0377R.id.helpMain;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0377R.layout.help);
    }
}
