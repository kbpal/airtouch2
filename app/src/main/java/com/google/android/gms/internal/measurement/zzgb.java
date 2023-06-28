package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzgb extends zzza<zzgb> {
    public Long zzawe = null;
    public String zzafx = null;
    private Integer zzawf = null;
    public zzgc[] zzawg = zzgc.zzmn();
    public zzga[] zzawh = zzga.zzmm();
    public zzfu[] zzawi = zzfu.zzmi();
    private String zzawj = null;

    public zzgb() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgb) {
            zzgb zzgbVar = (zzgb) obj;
            if (this.zzawe == null) {
                if (zzgbVar.zzawe != null) {
                    return false;
                }
            } else if (!this.zzawe.equals(zzgbVar.zzawe)) {
                return false;
            }
            if (this.zzafx == null) {
                if (zzgbVar.zzafx != null) {
                    return false;
                }
            } else if (!this.zzafx.equals(zzgbVar.zzafx)) {
                return false;
            }
            if (this.zzawf == null) {
                if (zzgbVar.zzawf != null) {
                    return false;
                }
            } else if (!this.zzawf.equals(zzgbVar.zzawf)) {
                return false;
            }
            if (zzze.equals(this.zzawg, zzgbVar.zzawg) && zzze.equals(this.zzawh, zzgbVar.zzawh) && zzze.equals(this.zzawi, zzgbVar.zzawi)) {
                if (this.zzawj == null) {
                    if (zzgbVar.zzawj != null) {
                        return false;
                    }
                } else if (!this.zzawj.equals(zzgbVar.zzawj)) {
                    return false;
                }
                if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                    return zzgbVar.zzcfc == null || zzgbVar.zzcfc.isEmpty();
                }
                return this.zzcfc.equals(zzgbVar.zzcfc);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzawe == null ? 0 : this.zzawe.hashCode())) * 31) + (this.zzafx == null ? 0 : this.zzafx.hashCode())) * 31) + (this.zzawf == null ? 0 : this.zzawf.hashCode())) * 31) + zzze.hashCode(this.zzawg)) * 31) + zzze.hashCode(this.zzawh)) * 31) + zzze.hashCode(this.zzawi)) * 31) + (this.zzawj == null ? 0 : this.zzawj.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzawe != null) {
            zzyyVar.zzi(1, this.zzawe.longValue());
        }
        if (this.zzafx != null) {
            zzyyVar.zzb(2, this.zzafx);
        }
        if (this.zzawf != null) {
            zzyyVar.zzd(3, this.zzawf.intValue());
        }
        if (this.zzawg != null && this.zzawg.length > 0) {
            for (int i = 0; i < this.zzawg.length; i++) {
                zzgc zzgcVar = this.zzawg[i];
                if (zzgcVar != null) {
                    zzyyVar.zza(4, zzgcVar);
                }
            }
        }
        if (this.zzawh != null && this.zzawh.length > 0) {
            for (int i2 = 0; i2 < this.zzawh.length; i2++) {
                zzga zzgaVar = this.zzawh[i2];
                if (zzgaVar != null) {
                    zzyyVar.zza(5, zzgaVar);
                }
            }
        }
        if (this.zzawi != null && this.zzawi.length > 0) {
            for (int i3 = 0; i3 < this.zzawi.length; i3++) {
                zzfu zzfuVar = this.zzawi[i3];
                if (zzfuVar != null) {
                    zzyyVar.zza(6, zzfuVar);
                }
            }
        }
        if (this.zzawj != null) {
            zzyyVar.zzb(7, this.zzawj);
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzawe != null) {
            zzf += zzyy.zzd(1, this.zzawe.longValue());
        }
        if (this.zzafx != null) {
            zzf += zzyy.zzc(2, this.zzafx);
        }
        if (this.zzawf != null) {
            zzf += zzyy.zzh(3, this.zzawf.intValue());
        }
        if (this.zzawg != null && this.zzawg.length > 0) {
            int i = zzf;
            for (int i2 = 0; i2 < this.zzawg.length; i2++) {
                zzgc zzgcVar = this.zzawg[i2];
                if (zzgcVar != null) {
                    i += zzyy.zzb(4, zzgcVar);
                }
            }
            zzf = i;
        }
        if (this.zzawh != null && this.zzawh.length > 0) {
            int i3 = zzf;
            for (int i4 = 0; i4 < this.zzawh.length; i4++) {
                zzga zzgaVar = this.zzawh[i4];
                if (zzgaVar != null) {
                    i3 += zzyy.zzb(5, zzgaVar);
                }
            }
            zzf = i3;
        }
        if (this.zzawi != null && this.zzawi.length > 0) {
            for (int i5 = 0; i5 < this.zzawi.length; i5++) {
                zzfu zzfuVar = this.zzawi[i5];
                if (zzfuVar != null) {
                    zzf += zzyy.zzb(6, zzfuVar);
                }
            }
        }
        return this.zzawj != null ? zzf + zzyy.zzc(7, this.zzawj) : zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int zzug = zzyxVar.zzug();
            if (zzug == 0) {
                return this;
            }
            if (zzug == 8) {
                this.zzawe = Long.valueOf(zzyxVar.zzuz());
            } else if (zzug == 18) {
                this.zzafx = zzyxVar.readString();
            } else if (zzug == 24) {
                this.zzawf = Integer.valueOf(zzyxVar.zzuy());
            } else if (zzug == 34) {
                int zzb = zzzj.zzb(zzyxVar, 34);
                int length = this.zzawg == null ? 0 : this.zzawg.length;
                zzgc[] zzgcVarArr = new zzgc[zzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzawg, 0, zzgcVarArr, 0, length);
                }
                while (length < zzgcVarArr.length - 1) {
                    zzgcVarArr[length] = new zzgc();
                    zzyxVar.zza(zzgcVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzgcVarArr[length] = new zzgc();
                zzyxVar.zza(zzgcVarArr[length]);
                this.zzawg = zzgcVarArr;
            } else if (zzug == 42) {
                int zzb2 = zzzj.zzb(zzyxVar, 42);
                int length2 = this.zzawh == null ? 0 : this.zzawh.length;
                zzga[] zzgaVarArr = new zzga[zzb2 + length2];
                if (length2 != 0) {
                    System.arraycopy(this.zzawh, 0, zzgaVarArr, 0, length2);
                }
                while (length2 < zzgaVarArr.length - 1) {
                    zzgaVarArr[length2] = new zzga();
                    zzyxVar.zza(zzgaVarArr[length2]);
                    zzyxVar.zzug();
                    length2++;
                }
                zzgaVarArr[length2] = new zzga();
                zzyxVar.zza(zzgaVarArr[length2]);
                this.zzawh = zzgaVarArr;
            } else if (zzug == 50) {
                int zzb3 = zzzj.zzb(zzyxVar, 50);
                int length3 = this.zzawi == null ? 0 : this.zzawi.length;
                zzfu[] zzfuVarArr = new zzfu[zzb3 + length3];
                if (length3 != 0) {
                    System.arraycopy(this.zzawi, 0, zzfuVarArr, 0, length3);
                }
                while (length3 < zzfuVarArr.length - 1) {
                    zzfuVarArr[length3] = new zzfu();
                    zzyxVar.zza(zzfuVarArr[length3]);
                    zzyxVar.zzug();
                    length3++;
                }
                zzfuVarArr[length3] = new zzfu();
                zzyxVar.zza(zzfuVarArr[length3]);
                this.zzawi = zzfuVarArr;
            } else if (zzug != 58) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.zzawj = zzyxVar.readString();
            }
        }
    }
}
