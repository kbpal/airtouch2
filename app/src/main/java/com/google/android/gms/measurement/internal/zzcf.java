package com.google.android.gms.measurement.internal;

/* loaded from: classes.dex */
final class zzcf implements Runnable {
    private final /* synthetic */ zzh zzaqn;
    private final /* synthetic */ zzbv zzaqo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcf(zzbv zzbvVar, zzh zzhVar) {
        this.zzaqo = zzbvVar;
        this.zzaqn = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfa zzfaVar;
        zzfa zzfaVar2;
        zzfaVar = this.zzaqo.zzamz;
        zzfaVar.zzly();
        zzfaVar2 = this.zzaqo.zzamz;
        zzfaVar2.zzd(this.zzaqn);
    }
}
