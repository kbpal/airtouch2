package com.google.android.gms.measurement.internal;

/* loaded from: classes.dex */
final class zzeu implements Runnable {
    private final /* synthetic */ long zzafv;
    private final /* synthetic */ zzeq zzasz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeu(zzeq zzeqVar, long j) {
        this.zzasz = zzeqVar;
        this.zzafv = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzasz.zzan(this.zzafv);
    }
}
