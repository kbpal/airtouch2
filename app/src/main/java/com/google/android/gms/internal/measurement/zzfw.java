package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzfw extends zzza<zzfw> {
    private static volatile zzfw[] zzavj;
    public zzfz zzavk = null;
    public zzfx zzavl = null;
    public Boolean zzavm = null;
    public String zzavn = null;

    public static zzfw[] zzmk() {
        if (zzavj == null) {
            synchronized (zzze.zzcfl) {
                if (zzavj == null) {
                    zzavj = new zzfw[0];
                }
            }
        }
        return zzavj;
    }

    public zzfw() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfw) {
            zzfw zzfwVar = (zzfw) obj;
            if (this.zzavk == null) {
                if (zzfwVar.zzavk != null) {
                    return false;
                }
            } else if (!this.zzavk.equals(zzfwVar.zzavk)) {
                return false;
            }
            if (this.zzavl == null) {
                if (zzfwVar.zzavl != null) {
                    return false;
                }
            } else if (!this.zzavl.equals(zzfwVar.zzavl)) {
                return false;
            }
            if (this.zzavm == null) {
                if (zzfwVar.zzavm != null) {
                    return false;
                }
            } else if (!this.zzavm.equals(zzfwVar.zzavm)) {
                return false;
            }
            if (this.zzavn == null) {
                if (zzfwVar.zzavn != null) {
                    return false;
                }
            } else if (!this.zzavn.equals(zzfwVar.zzavn)) {
                return false;
            }
            if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                return zzfwVar.zzcfc == null || zzfwVar.zzcfc.isEmpty();
            }
            return this.zzcfc.equals(zzfwVar.zzcfc);
        }
        return false;
    }

    public final int hashCode() {
        zzfz zzfzVar = this.zzavk;
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + (zzfzVar == null ? 0 : zzfzVar.hashCode());
        zzfx zzfxVar = this.zzavl;
        int hashCode2 = ((((((hashCode * 31) + (zzfxVar == null ? 0 : zzfxVar.hashCode())) * 31) + (this.zzavm == null ? 0 : this.zzavm.hashCode())) * 31) + (this.zzavn == null ? 0 : this.zzavn.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode2 + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzavk != null) {
            zzyyVar.zza(1, this.zzavk);
        }
        if (this.zzavl != null) {
            zzyyVar.zza(2, this.zzavl);
        }
        if (this.zzavm != null) {
            zzyyVar.zzb(3, this.zzavm.booleanValue());
        }
        if (this.zzavn != null) {
            zzyyVar.zzb(4, this.zzavn);
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzavk != null) {
            zzf += zzyy.zzb(1, this.zzavk);
        }
        if (this.zzavl != null) {
            zzf += zzyy.zzb(2, this.zzavl);
        }
        if (this.zzavm != null) {
            this.zzavm.booleanValue();
            zzf += zzyy.zzbb(3) + 1;
        }
        return this.zzavn != null ? zzf + zzyy.zzc(4, this.zzavn) : zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int zzug = zzyxVar.zzug();
            if (zzug == 0) {
                return this;
            }
            if (zzug == 10) {
                if (this.zzavk == null) {
                    this.zzavk = new zzfz();
                }
                zzyxVar.zza(this.zzavk);
            } else if (zzug == 18) {
                if (this.zzavl == null) {
                    this.zzavl = new zzfx();
                }
                zzyxVar.zza(this.zzavl);
            } else if (zzug == 24) {
                this.zzavm = Boolean.valueOf(zzyxVar.zzum());
            } else if (zzug != 34) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.zzavn = zzyxVar.readString();
            }
        }
    }
}
