package io.fabric.sdk.android;

/* loaded from: classes.dex */
public class SilentLogger implements Logger {
    private int logLevel = 7;

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: d */
    public void mo9d(String str, String str2) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: d */
    public void mo8d(String str, String str2, Throwable th) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: e */
    public void mo7e(String str, String str2) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: e */
    public void mo6e(String str, String str2, Throwable th) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: i */
    public void mo5i(String str, String str2) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: i */
    public void mo4i(String str, String str2, Throwable th) {
    }

    @Override // io.fabric.sdk.android.Logger
    public boolean isLoggable(String str, int i) {
        return false;
    }

    @Override // io.fabric.sdk.android.Logger
    public void log(int i, String str, String str2) {
    }

    @Override // io.fabric.sdk.android.Logger
    public void log(int i, String str, String str2, boolean z) {
    }

    @Override // io.fabric.sdk.android.Logger
    public void setLogLevel(int i) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: v */
    public void mo3v(String str, String str2) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: v */
    public void mo2v(String str, String str2, Throwable th) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: w */
    public void mo1w(String str, String str2) {
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: w */
    public void mo0w(String str, String str2, Throwable th) {
    }

    @Override // io.fabric.sdk.android.Logger
    public int getLogLevel() {
        return this.logLevel;
    }
}
