package io.fabric.sdk.android.services.persistence;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.io.File;

/* loaded from: classes.dex */
public class FileStoreImpl implements FileStore {
    private final String contentPath;
    private final Context context;
    private final String legacySupport;

    public FileStoreImpl(Kit kit) {
        if (kit.getContext() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.context = kit.getContext();
        this.contentPath = kit.getPath();
        this.legacySupport = "Android/" + this.context.getPackageName();
    }

    @Override // io.fabric.sdk.android.services.persistence.FileStore
    public File getCacheDir() {
        return prepare(this.context.getCacheDir());
    }

    @Override // io.fabric.sdk.android.services.persistence.FileStore
    public File getExternalCacheDir() {
        File file;
        if (!isExternalStorageAvailable()) {
            file = null;
        } else if (Build.VERSION.SDK_INT >= 8) {
            file = this.context.getExternalCacheDir();
        } else {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            file = new File(externalStorageDirectory, this.legacySupport + "/cache/" + this.contentPath);
        }
        return prepare(file);
    }

    @Override // io.fabric.sdk.android.services.persistence.FileStore
    public File getFilesDir() {
        return prepare(this.context.getFilesDir());
    }

    @Override // io.fabric.sdk.android.services.persistence.FileStore
    @TargetApi(8)
    public File getExternalFilesDir() {
        File file = null;
        if (isExternalStorageAvailable()) {
            if (Build.VERSION.SDK_INT >= 8) {
                file = this.context.getExternalFilesDir(null);
            } else {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                file = new File(externalStorageDirectory, this.legacySupport + "/files/" + this.contentPath);
            }
        }
        return prepare(file);
    }

    File prepare(File file) {
        if (file != null) {
            if (file.exists() || file.mkdirs()) {
                return file;
            }
            Fabric.getLogger().mo1w(Fabric.TAG, "Couldn't create file");
            return null;
        }
        Fabric.getLogger().mo9d(Fabric.TAG, "Null File");
        return null;
    }

    boolean isExternalStorageAvailable() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return true;
        }
        Fabric.getLogger().mo1w(Fabric.TAG, "External Storage is not mounted and/or writable\nHave you declared android.permission.WRITE_EXTERNAL_STORAGE in the manifest?");
        return false;
    }
}
