package android.support.v4.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.internal.view.SupportMenu;
import android.view.Menu;
import android.view.MenuItem;

/* loaded from: classes.dex */
public final class MenuCompat {
    @Deprecated
    public static void setShowAsAction(MenuItem menuItem, int i) {
        menuItem.setShowAsAction(i);
    }

    @SuppressLint({"NewApi"})
    public static void setGroupDividerEnabled(Menu menu, boolean z) {
        if (menu instanceof SupportMenu) {
            ((SupportMenu) menu).setGroupDividerEnabled(z);
        } else if (Build.VERSION.SDK_INT >= 28) {
            menu.setGroupDividerEnabled(z);
        }
    }

    private MenuCompat() {
    }
}
