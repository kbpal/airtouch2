package com.google.android.gms.internal.firebase_messaging;

/* loaded from: classes.dex */
final class zzg extends zzd {
    private final zze zzh = new zze();

    @Override // com.google.android.gms.internal.firebase_messaging.zzd
    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.zzh.zza(th, true).add(th2);
    }
}
