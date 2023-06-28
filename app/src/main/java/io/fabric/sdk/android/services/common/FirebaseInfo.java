package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;

/* loaded from: classes.dex */
public class FirebaseInfo {
    static final String FIREBASE_FEATURE_SWITCH = "com.crashlytics.useFirebaseAppId";
    static final String GOOGLE_APP_ID = "google_app_id";

    /* JADX INFO: Access modifiers changed from: protected */
    public String getApiKeyFromFirebaseAppId(Context context) {
        int resourcesIdentifier = CommonUtils.getResourcesIdentifier(context, GOOGLE_APP_ID, "string");
        if (resourcesIdentifier != 0) {
            Fabric.getLogger().mo9d(Fabric.TAG, "Generating Crashlytics ApiKey from google_app_id in Strings");
            return createApiKeyFromFirebaseAppId(context.getResources().getString(resourcesIdentifier));
        }
        return null;
    }

    protected String createApiKeyFromFirebaseAppId(String str) {
        return CommonUtils.sha256(str).substring(0, 40);
    }

    public boolean isFirebaseCrashlyticsEnabled(Context context) {
        if (CommonUtils.getBooleanResourceValue(context, FIREBASE_FEATURE_SWITCH, false)) {
            return true;
        }
        return (CommonUtils.getResourcesIdentifier(context, GOOGLE_APP_ID, "string") != 0) && !(!TextUtils.isEmpty(new ApiKey().getApiKeyFromManifest(context)) || !TextUtils.isEmpty(new ApiKey().getApiKeyFromStrings(context)));
    }

    public boolean isDataCollectionDefaultEnabled(Context context) {
        FirebaseApp firebaseAppImpl = FirebaseAppImpl.getInstance(context);
        if (firebaseAppImpl == null) {
            return true;
        }
        return firebaseAppImpl.isDataCollectionDefaultEnabled();
    }
}
