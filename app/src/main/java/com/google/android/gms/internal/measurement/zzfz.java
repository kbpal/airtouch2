package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzfz extends zzza<zzfz> {
    public Integer zzavw = null;
    public String zzavx = null;
    public Boolean zzavy = null;
    public String[] zzavz = zzzj.zzcfv;

    public zzfz() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfz) {
            zzfz zzfzVar = (zzfz) obj;
            if (this.zzavw == null) {
                if (zzfzVar.zzavw != null) {
                    return false;
                }
            } else if (!this.zzavw.equals(zzfzVar.zzavw)) {
                return false;
            }
            if (this.zzavx == null) {
                if (zzfzVar.zzavx != null) {
                    return false;
                }
            } else if (!this.zzavx.equals(zzfzVar.zzavx)) {
                return false;
            }
            if (this.zzavy == null) {
                if (zzfzVar.zzavy != null) {
                    return false;
                }
            } else if (!this.zzavy.equals(zzfzVar.zzavy)) {
                return false;
            }
            if (zzze.equals(this.zzavz, zzfzVar.zzavz)) {
                if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                    return zzfzVar.zzcfc == null || zzfzVar.zzcfc.isEmpty();
                }
                return this.zzcfc.equals(zzfzVar.zzcfc);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzavw == null ? 0 : this.zzavw.intValue())) * 31) + (this.zzavx == null ? 0 : this.zzavx.hashCode())) * 31) + (this.zzavy == null ? 0 : this.zzavy.hashCode())) * 31) + zzze.hashCode(this.zzavz)) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzavw != null) {
            zzyyVar.zzd(1, this.zzavw.intValue());
        }
        if (this.zzavx != null) {
            zzyyVar.zzb(2, this.zzavx);
        }
        if (this.zzavy != null) {
            zzyyVar.zzb(3, this.zzavy.booleanValue());
        }
        if (this.zzavz != null && this.zzavz.length > 0) {
            for (int i = 0; i < this.zzavz.length; i++) {
                String str = this.zzavz[i];
                if (str != null) {
                    zzyyVar.zzb(4, str);
                }
            }
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzavw != null) {
            zzf += zzyy.zzh(1, this.zzavw.intValue());
        }
        if (this.zzavx != null) {
            zzf += zzyy.zzc(2, this.zzavx);
        }
        if (this.zzavy != null) {
            this.zzavy.booleanValue();
            zzf += zzyy.zzbb(3) + 1;
        }
        if (this.zzavz == null || this.zzavz.length <= 0) {
            return zzf;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzavz.length; i3++) {
            String str = this.zzavz[i3];
            if (str != null) {
                i2++;
                i += zzyy.zzfx(str);
            }
        }
        return zzf + i + (i2 * 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzzg
    /* renamed from: zzd */
    public final zzfz zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int zzug = zzyxVar.zzug();
            if (zzug == 0) {
                return this;
            }
            if (zzug == 8) {
                int position = zzyxVar.getPosition();
                try {
                    int zzuy = zzyxVar.zzuy();
                    if (zzuy < 0 || zzuy > 6) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append(zzuy);
                        sb.append(" is not a valid enum MatchType");
                        throw new IllegalArgumentException(sb.toString());
                        break;
                    }
                    this.zzavw = Integer.valueOf(zzuy);
                } catch (IllegalArgumentException unused) {
                    zzyxVar.zzby(position);
                    zza(zzyxVar, zzug);
                }
            } else if (zzug == 18) {
                this.zzavx = zzyxVar.readString();
            } else if (zzug == 24) {
                this.zzavy = Boolean.valueOf(zzyxVar.zzum());
            } else if (zzug != 34) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                int zzb = zzzj.zzb(zzyxVar, 34);
                int length = this.zzavz == null ? 0 : this.zzavz.length;
                String[] strArr = new String[zzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzavz, 0, strArr, 0, length);
                }
                while (length < strArr.length - 1) {
                    strArr[length] = zzyxVar.readString();
                    zzyxVar.zzug();
                    length++;
                }
                strArr[length] = zzyxVar.readString();
                this.zzavz = strArr;
            }
        }
    }
}
