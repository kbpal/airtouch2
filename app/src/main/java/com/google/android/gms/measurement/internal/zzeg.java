package com.google.android.gms.measurement.internal;

/* loaded from: classes.dex */
final class zzeg implements Runnable {
    private final /* synthetic */ zzag zzaso;
    private final /* synthetic */ zzef zzasp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeg(zzef zzefVar, zzag zzagVar) {
        this.zzasp = zzefVar;
        this.zzaso = zzagVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzasp) {
            zzef.zza(this.zzasp, false);
            if (!this.zzasp.zzasg.isConnected()) {
                this.zzasp.zzasg.zzgo().zzjl().zzbx("Connected to service");
                this.zzasp.zzasg.zza(this.zzaso);
            }
        }
    }
}
