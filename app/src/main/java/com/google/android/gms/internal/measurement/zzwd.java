package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes.dex */
abstract class zzwd {
    private static final zzwd zzcaj = new zzwf();
    private static final zzwd zzcak = new zzwg();

    private zzwd() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> List<L> zza(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> void zza(Object obj, Object obj2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzwd zzwx() {
        return zzcaj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzwd zzwy() {
        return zzcak;
    }
}
