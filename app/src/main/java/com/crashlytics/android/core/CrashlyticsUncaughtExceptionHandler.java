package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final boolean firebaseCrashlyticsClientFlag;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);
    private final SettingsDataProvider settingsDataProvider;

    /* loaded from: classes.dex */
    interface CrashListener {
        void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable th, boolean z);
    }

    /* loaded from: classes.dex */
    interface SettingsDataProvider {
        SettingsData getSettingsData();
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener, SettingsDataProvider settingsDataProvider, boolean z, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.crashListener = crashListener;
        this.settingsDataProvider = settingsDataProvider;
        this.firebaseCrashlyticsClientFlag = z;
        this.defaultHandler = uncaughtExceptionHandler;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Thread$UncaughtExceptionHandler] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.concurrent.atomic.AtomicBoolean] */
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.isHandlingException.set(true);
        try {
            try {
                this.crashListener.onUncaughtException(this.settingsDataProvider, thread, th, this.firebaseCrashlyticsClientFlag);
            } catch (Exception e) {
                Fabric.getLogger().mo6e(CrashlyticsCore.TAG, "An error occurred in the uncaught exception handler", e);
            }
        } finally {
            Fabric.getLogger().mo9d(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }
}
