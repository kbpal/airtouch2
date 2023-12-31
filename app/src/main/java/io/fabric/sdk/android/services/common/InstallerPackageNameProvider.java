package io.fabric.sdk.android.services.common;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.cache.MemoryValueCache;
import io.fabric.sdk.android.services.cache.ValueLoader;

/* loaded from: classes.dex */
public class InstallerPackageNameProvider {
    private static final String NO_INSTALLER_PACKAGE_NAME = "";
    private final ValueLoader<String> installerPackageNameLoader = new ValueLoader<String>() { // from class: io.fabric.sdk.android.services.common.InstallerPackageNameProvider.1
        @Override // io.fabric.sdk.android.services.cache.ValueLoader
        public String load(Context context) throws Exception {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? "" : installerPackageName;
        }
    };
    private final MemoryValueCache<String> installerPackageNameCache = new MemoryValueCache<>();

    public String getInstallerPackageName(Context context) {
        try {
            String str = this.installerPackageNameCache.get(context, this.installerPackageNameLoader);
            if ("".equals(str)) {
                return null;
            }
            return str;
        } catch (Exception e) {
            Fabric.getLogger().mo6e(Fabric.TAG, "Failed to determine installer package name", e);
            return null;
        }
    }
}
