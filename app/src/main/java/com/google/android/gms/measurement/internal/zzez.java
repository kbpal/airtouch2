package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class zzez extends zzey {
    private boolean zzvz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzez(zzfa zzfaVar) {
        super(zzfaVar);
        this.zzamz.zzb(this);
    }

    protected abstract boolean zzgt();

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
        zzgt();
        this.zzamz.zzma();
        this.zzvz = true;
    }
}
