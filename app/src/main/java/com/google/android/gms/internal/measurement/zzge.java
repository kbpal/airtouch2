package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzge extends zzza<zzge> {
    private static volatile zzge[] zzawp;
    public Integer zzawq = null;
    public Long zzawr = null;

    public static zzge[] zzmp() {
        if (zzawp == null) {
            synchronized (zzze.zzcfl) {
                if (zzawp == null) {
                    zzawp = new zzge[0];
                }
            }
        }
        return zzawp;
    }

    public zzge() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzge) {
            zzge zzgeVar = (zzge) obj;
            if (this.zzawq == null) {
                if (zzgeVar.zzawq != null) {
                    return false;
                }
            } else if (!this.zzawq.equals(zzgeVar.zzawq)) {
                return false;
            }
            if (this.zzawr == null) {
                if (zzgeVar.zzawr != null) {
                    return false;
                }
            } else if (!this.zzawr.equals(zzgeVar.zzawr)) {
                return false;
            }
            if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                return zzgeVar.zzcfc == null || zzgeVar.zzcfc.isEmpty();
            }
            return this.zzcfc.equals(zzgeVar.zzcfc);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzawq == null ? 0 : this.zzawq.hashCode())) * 31) + (this.zzawr == null ? 0 : this.zzawr.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzawq != null) {
            zzyyVar.zzd(1, this.zzawq.intValue());
        }
        if (this.zzawr != null) {
            zzyyVar.zzi(2, this.zzawr.longValue());
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzawq != null) {
            zzf += zzyy.zzh(1, this.zzawq.intValue());
        }
        return this.zzawr != null ? zzf + zzyy.zzd(2, this.zzawr.longValue()) : zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int zzug = zzyxVar.zzug();
            if (zzug == 0) {
                return this;
            }
            if (zzug == 8) {
                this.zzawq = Integer.valueOf(zzyxVar.zzuy());
            } else if (zzug != 16) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.zzawr = Long.valueOf(zzyxVar.zzuz());
            }
        }
    }
}
