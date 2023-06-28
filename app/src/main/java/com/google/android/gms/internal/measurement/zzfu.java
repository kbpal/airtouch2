package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzfu extends zzza<zzfu> {
    private static volatile zzfu[] zzaux;
    public Integer zzauy = null;
    public zzfy[] zzauz = zzfy.zzml();
    public zzfv[] zzava = zzfv.zzmj();
    private Boolean zzavb = null;
    private Boolean zzavc = null;

    public static zzfu[] zzmi() {
        if (zzaux == null) {
            synchronized (zzze.zzcfl) {
                if (zzaux == null) {
                    zzaux = new zzfu[0];
                }
            }
        }
        return zzaux;
    }

    public zzfu() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfu) {
            zzfu zzfuVar = (zzfu) obj;
            if (this.zzauy == null) {
                if (zzfuVar.zzauy != null) {
                    return false;
                }
            } else if (!this.zzauy.equals(zzfuVar.zzauy)) {
                return false;
            }
            if (zzze.equals(this.zzauz, zzfuVar.zzauz) && zzze.equals(this.zzava, zzfuVar.zzava)) {
                if (this.zzavb == null) {
                    if (zzfuVar.zzavb != null) {
                        return false;
                    }
                } else if (!this.zzavb.equals(zzfuVar.zzavb)) {
                    return false;
                }
                if (this.zzavc == null) {
                    if (zzfuVar.zzavc != null) {
                        return false;
                    }
                } else if (!this.zzavc.equals(zzfuVar.zzavc)) {
                    return false;
                }
                if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                    return zzfuVar.zzcfc == null || zzfuVar.zzcfc.isEmpty();
                }
                return this.zzcfc.equals(zzfuVar.zzcfc);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzauy == null ? 0 : this.zzauy.hashCode())) * 31) + zzze.hashCode(this.zzauz)) * 31) + zzze.hashCode(this.zzava)) * 31) + (this.zzavb == null ? 0 : this.zzavb.hashCode())) * 31) + (this.zzavc == null ? 0 : this.zzavc.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzauy != null) {
            zzyyVar.zzd(1, this.zzauy.intValue());
        }
        if (this.zzauz != null && this.zzauz.length > 0) {
            for (int i = 0; i < this.zzauz.length; i++) {
                zzfy zzfyVar = this.zzauz[i];
                if (zzfyVar != null) {
                    zzyyVar.zza(2, zzfyVar);
                }
            }
        }
        if (this.zzava != null && this.zzava.length > 0) {
            for (int i2 = 0; i2 < this.zzava.length; i2++) {
                zzfv zzfvVar = this.zzava[i2];
                if (zzfvVar != null) {
                    zzyyVar.zza(3, zzfvVar);
                }
            }
        }
        if (this.zzavb != null) {
            zzyyVar.zzb(4, this.zzavb.booleanValue());
        }
        if (this.zzavc != null) {
            zzyyVar.zzb(5, this.zzavc.booleanValue());
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
        if (this.zzauz != null && this.zzauz.length > 0) {
            int i = zzf;
            for (int i2 = 0; i2 < this.zzauz.length; i2++) {
                zzfy zzfyVar = this.zzauz[i2];
                if (zzfyVar != null) {
                    i += zzyy.zzb(2, zzfyVar);
                }
            }
            zzf = i;
        }
        if (this.zzava != null && this.zzava.length > 0) {
            for (int i3 = 0; i3 < this.zzava.length; i3++) {
                zzfv zzfvVar = this.zzava[i3];
                if (zzfvVar != null) {
                    zzf += zzyy.zzb(3, zzfvVar);
                }
            }
        }
        if (this.zzavb != null) {
            this.zzavb.booleanValue();
            zzf += zzyy.zzbb(4) + 1;
        }
        if (this.zzavc != null) {
            this.zzavc.booleanValue();
            return zzf + zzyy.zzbb(5) + 1;
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
                int zzb = zzzj.zzb(zzyxVar, 18);
                int length = this.zzauz == null ? 0 : this.zzauz.length;
                zzfy[] zzfyVarArr = new zzfy[zzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzauz, 0, zzfyVarArr, 0, length);
                }
                while (length < zzfyVarArr.length - 1) {
                    zzfyVarArr[length] = new zzfy();
                    zzyxVar.zza(zzfyVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzfyVarArr[length] = new zzfy();
                zzyxVar.zza(zzfyVarArr[length]);
                this.zzauz = zzfyVarArr;
            } else if (zzug == 26) {
                int zzb2 = zzzj.zzb(zzyxVar, 26);
                int length2 = this.zzava == null ? 0 : this.zzava.length;
                zzfv[] zzfvVarArr = new zzfv[zzb2 + length2];
                if (length2 != 0) {
                    System.arraycopy(this.zzava, 0, zzfvVarArr, 0, length2);
                }
                while (length2 < zzfvVarArr.length - 1) {
                    zzfvVarArr[length2] = new zzfv();
                    zzyxVar.zza(zzfvVarArr[length2]);
                    zzyxVar.zzug();
                    length2++;
                }
                zzfvVarArr[length2] = new zzfv();
                zzyxVar.zza(zzfvVarArr[length2]);
                this.zzava = zzfvVarArr;
            } else if (zzug == 32) {
                this.zzavb = Boolean.valueOf(zzyxVar.zzum());
            } else if (zzug != 40) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.zzavc = Boolean.valueOf(zzyxVar.zzum());
            }
        }
    }
}
