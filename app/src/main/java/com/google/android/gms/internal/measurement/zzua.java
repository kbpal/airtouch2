package com.google.android.gms.internal.measurement;

/* loaded from: classes.dex */
final class zzua {
    private static final Class<?> zzbtv = zzfu("libcore.io.Memory");
    private static final boolean zzbtw;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzty() {
        return (zzbtv == null || zzbtw) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> zztz() {
        return zzbtv;
    }

    private static <T> Class<T> zzfu(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    static {
        zzbtw = zzfu("org.robolectric.Robolectric") != null;
    }
}
