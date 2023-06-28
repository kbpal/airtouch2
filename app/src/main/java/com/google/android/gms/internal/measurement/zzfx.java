package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzfx extends zzza<zzfx> {
    public Integer zzavo = null;
    public Boolean zzavp = null;
    public String zzavq = null;
    public String zzavr = null;
    public String zzavs = null;

    public zzfx() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfx) {
            zzfx zzfxVar = (zzfx) obj;
            if (this.zzavo == null) {
                if (zzfxVar.zzavo != null) {
                    return false;
                }
            } else if (!this.zzavo.equals(zzfxVar.zzavo)) {
                return false;
            }
            if (this.zzavp == null) {
                if (zzfxVar.zzavp != null) {
                    return false;
                }
            } else if (!this.zzavp.equals(zzfxVar.zzavp)) {
                return false;
            }
            if (this.zzavq == null) {
                if (zzfxVar.zzavq != null) {
                    return false;
                }
            } else if (!this.zzavq.equals(zzfxVar.zzavq)) {
                return false;
            }
            if (this.zzavr == null) {
                if (zzfxVar.zzavr != null) {
                    return false;
                }
            } else if (!this.zzavr.equals(zzfxVar.zzavr)) {
                return false;
            }
            if (this.zzavs == null) {
                if (zzfxVar.zzavs != null) {
                    return false;
                }
            } else if (!this.zzavs.equals(zzfxVar.zzavs)) {
                return false;
            }
            if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                return zzfxVar.zzcfc == null || zzfxVar.zzcfc.isEmpty();
            }
            return this.zzcfc.equals(zzfxVar.zzcfc);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzavo == null ? 0 : this.zzavo.intValue())) * 31) + (this.zzavp == null ? 0 : this.zzavp.hashCode())) * 31) + (this.zzavq == null ? 0 : this.zzavq.hashCode())) * 31) + (this.zzavr == null ? 0 : this.zzavr.hashCode())) * 31) + (this.zzavs == null ? 0 : this.zzavs.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzavo != null) {
            zzyyVar.zzd(1, this.zzavo.intValue());
        }
        if (this.zzavp != null) {
            zzyyVar.zzb(2, this.zzavp.booleanValue());
        }
        if (this.zzavq != null) {
            zzyyVar.zzb(3, this.zzavq);
        }
        if (this.zzavr != null) {
            zzyyVar.zzb(4, this.zzavr);
        }
        if (this.zzavs != null) {
            zzyyVar.zzb(5, this.zzavs);
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzavo != null) {
            zzf += zzyy.zzh(1, this.zzavo.intValue());
        }
        if (this.zzavp != null) {
            this.zzavp.booleanValue();
            zzf += zzyy.zzbb(2) + 1;
        }
        if (this.zzavq != null) {
            zzf += zzyy.zzc(3, this.zzavq);
        }
        if (this.zzavr != null) {
            zzf += zzyy.zzc(4, this.zzavr);
        }
        return this.zzavs != null ? zzf + zzyy.zzc(5, this.zzavs) : zzf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzzg
    /* renamed from: zzc */
    public final zzfx zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int zzug = zzyxVar.zzug();
            if (zzug == 0) {
                return this;
            }
            if (zzug == 8) {
                int position = zzyxVar.getPosition();
                try {
                    int zzuy = zzyxVar.zzuy();
                    if (zzuy < 0 || zzuy > 4) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(zzuy);
                        sb.append(" is not a valid enum ComparisonType");
                        throw new IllegalArgumentException(sb.toString());
                        break;
                    }
                    this.zzavo = Integer.valueOf(zzuy);
                } catch (IllegalArgumentException unused) {
                    zzyxVar.zzby(position);
                    zza(zzyxVar, zzug);
                }
            } else if (zzug == 16) {
                this.zzavp = Boolean.valueOf(zzyxVar.zzum());
            } else if (zzug == 26) {
                this.zzavq = zzyxVar.readString();
            } else if (zzug == 34) {
                this.zzavr = zzyxVar.readString();
            } else if (zzug != 42) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.zzavs = zzyxVar.readString();
            }
        }
    }
}
