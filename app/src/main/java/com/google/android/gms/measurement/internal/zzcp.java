package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class zzcp extends zzco {
    private boolean zzvz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcp(zzbt zzbtVar) {
        super(zzbtVar);
        this.zzadj.zzb(this);
    }

    protected abstract boolean zzgt();

    protected void zzgu() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isInitialized() {
        return this.zzvz;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzcl() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzq() {
        if (this.zzvz) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (zzgt()) {
            return;
        }
        this.zzadj.zzkq();
        this.zzvz = true;
    }

    public final void zzgs() {
        if (this.zzvz) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzgu();
        this.zzadj.zzkq();
        this.zzvz = true;
    }
}
