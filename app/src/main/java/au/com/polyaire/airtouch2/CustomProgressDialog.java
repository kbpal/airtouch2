package au.com.polyaire.airtouch2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

/* loaded from: classes.dex */
public class CustomProgressDialog extends ProgressDialog {
    public CustomProgressDialog(Context context) {
        super(context);
    }

    public CustomProgressDialog(Context context, int i) {
        super(context, i);
    }

    @Override // android.app.ProgressDialog, android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0377R.layout.progressdialoglayout);
    }

    public static CustomProgressDialog show(Context context) {
        CustomProgressDialog customProgressDialog = new CustomProgressDialog(context);
        customProgressDialog.setMessage("Please Wait...");
        customProgressDialog.show();
        return customProgressDialog;
    }
}
