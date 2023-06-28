package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzgd extends zzza<zzgd> {
    private static volatile zzgd[] zzawl;
    public Integer zzauy = null;
    public zzgj zzawm = null;
    public zzgj zzawn = null;
    public Boolean zzawo = null;

    public static zzgd[] zzmo() {
        if (zzawl == null) {
            synchronized (zzze.zzcfl) {
                if (zzawl == null) {
                    zzawl = new zzgd[0];
                }
            }
        }
        return zzawl;
    }

    public zzgd() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgd) {
            zzgd zzgdVar = (zzgd) obj;
            if (this.zzauy == null) {
                if (zzgdVar.zzauy != null) {
                    return false;
                }
            } else if (!this.zzauy.equals(zzgdVar.zzauy)) {
                return false;
            }
            if (this.zzawm == null) {
                if (zzgdVar.zzawm != null) {
                    return false;
                }
            } else if (!this.zzawm.equals(zzgdVar.zzawm)) {
                return false;
            }
            if (this.zzawn == null) {
                if (zzgdVar.zzawn != null) {
                    return false;
                }
            } else if (!this.zzawn.equals(zzgdVar.zzawn)) {
                return false;
            }
            if (this.zzawo == null) {
                if (zzgdVar.zzawo != null) {
                    return false;
                }
            } else if (!this.zzawo.equals(zzgdVar.zzawo)) {
                return false;
            }
            if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                return zzgdVar.zzcfc == null || zzgdVar.zzcfc.isEmpty();
            }
            return this.zzcfc.equals(zzgdVar.zzcfc);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + (this.zzauy == null ? 0 : this.zzauy.hashCode());
        zzgj zzgjVar = this.zzawm;
        int hashCode2 = (hashCode * 31) + (zzgjVar == null ? 0 : zzgjVar.hashCode());
        zzgj zzgjVar2 = this.zzawn;
        int hashCode3 = ((((hashCode2 * 31) + (zzgjVar2 == null ? 0 : zzgjVar2.hashCode())) * 31) + (this.zzawo == null ? 0 : this.zzawo.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode3 + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzauy != null) {
            zzyyVar.zzd(1, this.zzauy.intValue());
        }
        if (this.zzawm != null) {
            zzyyVar.zza(2, this.zzawm);
        }
        if (this.zzawn != null) {
            zzyyVar.zza(3, this.zzawn);
        }
        if (this.zzawo != null) {
            zzyyVar.zzb(4, this.zzawo.booleanValue());
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzauy != null) {
            zzf += zzyy.zzh(1, this.zzauy.intValue());
        }
        if (this.zzawm != null) {
            zzf += zzyy.zzb(2, this.zzawm);
        }
        if (this.zzawn != null) {
            zzf += zzyy.zzb(3, this.zzawn);
        }
        if (this.zzawo != null) {
            this.zzawo.booleanValue();
            return zzf + zzyy.zzbb(4) + 1;
        }
        return zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int zzug = zzyxVar.zzug();
            if (zzug == 0) {
                return this;
            }
            if (zzug == 8) {
                this.zzauy = Integer.valueOf(zzyxVar.zzuy());
            } else if (zzug == 18) {
                if (this.zzawm == null) {
                    this.zzawm = new zzgj();
                }
                zzyxVar.zza(this.zzawm);
            } else if (zzug == 26) {
                if (this.zzawn == null) {
                    this.zzawn = new zzgj();
                }
                zzyxVar.zza(this.zzawn);
            } else if (zzug != 32) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.zzawo = Boolean.valueOf(zzyxVar.zzum());
            }
        }
    }
}
