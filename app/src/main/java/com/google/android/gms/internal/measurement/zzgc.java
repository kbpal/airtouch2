package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzgc extends zzza<zzgc> {
    private static volatile zzgc[] zzawk;
    public String zzoj = null;
    public String value = null;

    public static zzgc[] zzmn() {
        if (zzawk == null) {
            synchronized (zzze.zzcfl) {
                if (zzawk == null) {
                    zzawk = new zzgc[0];
                }
            }
        }
        return zzawk;
    }

    public zzgc() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgc) {
            zzgc zzgcVar = (zzgc) obj;
            if (this.zzoj == null) {
                if (zzgcVar.zzoj != null) {
                    return false;
                }
            } else if (!this.zzoj.equals(zzgcVar.zzoj)) {
                return false;
            }
            if (this.value == null) {
                if (zzgcVar.value != null) {
                    return false;
                }
            } else if (!this.value.equals(zzgcVar.value)) {
                return false;
            }
            if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                return zzgcVar.zzcfc == null || zzgcVar.zzcfc.isEmpty();
            }
            return this.zzcfc.equals(zzgcVar.zzcfc);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzoj == null ? 0 : this.zzoj.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzoj != null) {
            zzyyVar.zzb(1, this.zzoj);
        }
        if (this.value != null) {
            zzyyVar.zzb(2, this.value);
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzoj != null) {
            zzf += zzyy.zzc(1, this.zzoj);
        }
        return this.value != null ? zzf + zzyy.zzc(2, this.value) : zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int zzug = zzyxVar.zzug();
            if (zzug == 0) {
                return this;
            }
            if (zzug == 10) {
                this.zzoj = zzyxVar.readString();
            } else if (zzug != 18) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.value = zzyxVar.readString();
            }
        }
    }
}
