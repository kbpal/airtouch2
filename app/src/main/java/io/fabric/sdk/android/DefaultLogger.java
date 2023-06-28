package io.fabric.sdk.android;

import android.util.Log;

/* loaded from: classes.dex */
public class DefaultLogger implements Logger {
    private int logLevel;

    public DefaultLogger(int i) {
        this.logLevel = i;
    }

    public DefaultLogger() {
        this.logLevel = 4;
    }

    @Override // io.fabric.sdk.android.Logger
    public boolean isLoggable(String str, int i) {
        return this.logLevel <= i;
    }

    @Override // io.fabric.sdk.android.Logger
    public int getLogLevel() {
        return this.logLevel;
    }

    @Override // io.fabric.sdk.android.Logger
    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: d */
    public void mo8d(String str, String str2, Throwable th) {
        if (isLoggable(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: v */
    public void mo2v(String str, String str2, Throwable th) {
        if (isLoggable(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: i */
    public void mo4i(String str, String str2, Throwable th) {
        if (isLoggable(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: w */
    public void mo0w(String str, String str2, Throwable th) {
        if (isLoggable(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: e */
    public void mo6e(String str, String str2, Throwable th) {
        if (isLoggable(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: d */
    public void mo9d(String str, String str2) {
        mo8d(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: v */
    public void mo3v(String str, String str2) {
        mo2v(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: i */
    public void mo5i(String str, String str2) {
        mo4i(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: w */
    public void mo1w(String str, String str2) {
        mo0w(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: e */
    public void mo7e(String str, String str2) {
        mo6e(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    public void log(int i, String str, String str2) {
        log(i, str, str2, false);
    }

    @Override // io.fabric.sdk.android.Logger
    public void log(int i, String str, String str2, boolean z) {
        if (z || isLoggable(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
