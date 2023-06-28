package au.com.polyaire.airtouch2.service;

import android.app.Activity;
import au.com.polyaire.airtouch2.InitActivity;
import au.com.polyaire.airtouch2.data.ExchData;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ActivityManageTool {
    private static boolean exiting = false;
    private static ArrayList mActivities = new ArrayList();

    public static void onExit() {
        exiting = true;
        ExchData.mErrorTitle = "";
        ExchData.mErrorMsg = "";
        for (int i = 0; i < mActivities.size(); i++) {
            ((Activity) mActivities.get(i)).finish();
        }
        mActivities.clear();
        if (ExchData.mConnection != null) {
            ExchData.mConnection.disconnect();
            ExchData.mConnection = null;
        }
    }

    public static void onInitResume(Activity activity) {
        for (int i = 0; i < mActivities.size(); i++) {
            Activity activity2 = (Activity) mActivities.get(i);
            if (activity2.getClass() != InitActivity.class) {
                activity2.finish();
            }
        }
        mActivities.clear();
        mActivities.add(activity);
    }

    public static boolean isExiting() {
        return exiting;
    }

    public static void exited() {
        exiting = false;
    }

    public static int addActivity(Activity activity) {
        int i = 0;
        while (i < mActivities.size() && ((Activity) mActivities.get(i)) != activity) {
            i++;
        }
        int size = mActivities.size();
        if (i >= size) {
            mActivities.add(activity);
        }
        return size;
    }
}
