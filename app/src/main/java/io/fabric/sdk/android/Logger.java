package io.fabric.sdk.android;

/* loaded from: classes.dex */
public interface Logger {
    /* renamed from: d */
    void mo9d(String str, String str2);

    /* renamed from: d */
    void mo8d(String str, String str2, Throwable th);

    /* renamed from: e */
    void mo7e(String str, String str2);

    /* renamed from: e */
    void mo6e(String str, String str2, Throwable th);

    int getLogLevel();

    /* renamed from: i */
    void mo5i(String str, String str2);

    /* renamed from: i */
    void mo4i(String str, String str2, Throwable th);

    boolean isLoggable(String str, int i);

    void log(int i, String str, String str2);

    void log(int i, String str, String str2, boolean z);

    void setLogLevel(int i);

    /* renamed from: v */
    void mo3v(String str, String str2);

    /* renamed from: v */
    void mo2v(String str, String str2, Throwable th);

    /* renamed from: w */
    void mo1w(String str, String str2);

    /* renamed from: w */
    void mo0w(String str, String str2, Throwable th);
}
