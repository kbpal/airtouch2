package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzfv extends zzza<zzfv> {
    private static volatile zzfv[] zzavd;
    public Integer zzave = null;
    public String zzavf = null;
    public zzfw[] zzavg = zzfw.zzmk();
    private Boolean zzavh = null;
    public zzfx zzavi = null;
    public Boolean zzavb = null;
    public Boolean zzavc = null;

    public static zzfv[] zzmj() {
        if (zzavd == null) {
            synchronized (zzze.zzcfl) {
                if (zzavd == null) {
                    zzavd = new zzfv[0];
                }
            }
        }
        return zzavd;
    }

    public zzfv() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfv) {
            zzfv zzfvVar = (zzfv) obj;
            if (this.zzave == null) {
                if (zzfvVar.zzave != null) {
                    return false;
                }
            } else if (!this.zzave.equals(zzfvVar.zzave)) {
                return false;
            }
            if (this.zzavf == null) {
                if (zzfvVar.zzavf != null) {
                    return false;
                }
            } else if (!this.zzavf.equals(zzfvVar.zzavf)) {
                return false;
            }
            if (zzze.equals(this.zzavg, zzfvVar.zzavg)) {
                if (this.zzavh == null) {
                    if (zzfvVar.zzavh != null) {
                        return false;
                    }
                } else if (!this.zzavh.equals(zzfvVar.zzavh)) {
                    return false;
                }
                if (this.zzavi == null) {
                    if (zzfvVar.zzavi != null) {
                        return false;
                    }
                } else if (!this.zzavi.equals(zzfvVar.zzavi)) {
                    return false;
                }
                if (this.zzavb == null) {
                    if (zzfvVar.zzavb != null) {
                        return false;
                    }
                } else if (!this.zzavb.equals(zzfvVar.zzavb)) {
                    return false;
                }
                if (this.zzavc == null) {
                    if (zzfvVar.zzavc != null) {
                        return false;
                    }
                } else if (!this.zzavc.equals(zzfvVar.zzavc)) {
                    return false;
                }
                if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                    return zzfvVar.zzcfc == null || zzfvVar.zzcfc.isEmpty();
                }
                return this.zzcfc.equals(zzfvVar.zzcfc);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzave == null ? 0 : this.zzave.hashCode())) * 31) + (this.zzavf == null ? 0 : this.zzavf.hashCode())) * 31) + zzze.hashCode(this.zzavg)) * 31) + (this.zzavh == null ? 0 : this.zzavh.hashCode());
        zzfx zzfxVar = this.zzavi;
        int hashCode2 = ((((((hashCode * 31) + (zzfxVar == null ? 0 : zzfxVar.hashCode())) * 31) + (this.zzavb == null ? 0 : this.zzavb.hashCode())) * 31) + (this.zzavc == null ? 0 : this.zzavc.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode2 + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzave != null) {
            zzyyVar.zzd(1, this.zzave.intValue());
        }
        if (this.zzavf != null) {
            zzyyVar.zzb(2, this.zzavf);
        }
        if (this.zzavg != null && this.zzavg.length > 0) {
            for (int i = 0; i < this.zzavg.length; i++) {
                zzfw zzfwVar = this.zzavg[i];
                if (zzfwVar != null) {
                    zzyyVar.zza(3, zzfwVar);
                }
            }
        }
        if (this.zzavh != null) {
            zzyyVar.zzb(4, this.zzavh.booleanValue());
        }
        if (this.zzavi != null) {
            zzyyVar.zza(5, this.zzavi);
        }
        if (this.zzavb != null) {
            zzyyVar.zzb(6, this.zzavb.booleanValue());
        }
        if (this.zzavc != null) {
            zzyyVar.zzb(7, this.zzavc.booleanValue());
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzave != null) {
            zzf += zzyy.zzh(1, this.zzave.intValue());
        }
        if (this.zzavf != null) {
            zzf += zzyy.zzc(2, this.zzavf);
        }
        if (this.zzavg != null && this.zzavg.length > 0) {
            for (int i = 0; i < this.zzavg.length; i++) {
                zzfw zzfwVar = this.zzavg[i];
                if (zzfwVar != null) {
                    zzf += zzyy.zzb(3, zzfwVar);
                }
            }
        }
        if (this.zzavh != null) {
            this.zzavh.booleanValue();
            zzf += zzyy.zzbb(4) + 1;
        }
        if (this.zzavi != null) {
            zzf += zzyy.zzb(5, this.zzavi);
        }
        if (this.zzavb != null) {
            this.zzavb.booleanValue();
            zzf += zzyy.zzbb(6) + 1;
        }
        if (this.zzavc != null) {
            this.zzavc.booleanValue();
            return zzf + zzyy.zzbb(7) + 1;
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
                this.zzave = Integer.valueOf(zzyxVar.zzuy());
            } else if (zzug == 18) {
                this.zzavf = zzyxVar.readString();
            } else if (zzug == 26) {
                int zzb = zzzj.zzb(zzyxVar, 26);
                int length = this.zzavg == null ? 0 : this.zzavg.length;
                zzfw[] zzfwVarArr = new zzfw[zzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzavg, 0, zzfwVarArr, 0, length);
                }
                while (length < zzfwVarArr.length - 1) {
                    zzfwVarArr[length] = new zzfw();
                    zzyxVar.zza(zzfwVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzfwVarArr[length] = new zzfw();
                zzyxVar.zza(zzfwVarArr[length]);
                this.zzavg = zzfwVarArr;
            } else if (zzug == 32) {
                this.zzavh = Boolean.valueOf(zzyxVar.zzum());
            } else if (zzug == 42) {
                if (this.zzavi == null) {
                    this.zzavi = new zzfx();
                }
                zzyxVar.zza(this.zzavi);
            } else if (zzug == 48) {
                this.zzavb = Boolean.valueOf(zzyxVar.zzum());
            } else if (zzug != 56) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.zzavc = Boolean.valueOf(zzyxVar.zzum());
            }
        }
    }
}
