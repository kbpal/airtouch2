package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzgk extends zzza<zzgk> {
    private static volatile zzgk[] zzayi;
    public Integer zzawq = null;
    public long[] zzayj = zzzj.zzcfr;

    public static zzgk[] zzmt() {
        if (zzayi == null) {
            synchronized (zzze.zzcfl) {
                if (zzayi == null) {
                    zzayi = new zzgk[0];
                }
            }
        }
        return zzayi;
    }

    public zzgk() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgk) {
            zzgk zzgkVar = (zzgk) obj;
            if (this.zzawq == null) {
                if (zzgkVar.zzawq != null) {
                    return false;
                }
            } else if (!this.zzawq.equals(zzgkVar.zzawq)) {
                return false;
            }
            if (zzze.equals(this.zzayj, zzgkVar.zzayj)) {
                if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                    return zzgkVar.zzcfc == null || zzgkVar.zzcfc.isEmpty();
                }
                return this.zzcfc.equals(zzgkVar.zzcfc);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzawq == null ? 0 : this.zzawq.hashCode())) * 31) + zzze.hashCode(this.zzayj)) * 31;
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
        if (this.zzayj != null && this.zzayj.length > 0) {
            for (int i = 0; i < this.zzayj.length; i++) {
                zzyyVar.zzi(2, this.zzayj[i]);
            }
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
        if (this.zzayj == null || this.zzayj.length <= 0) {
            return zzf;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzayj.length; i2++) {
            i += zzyy.zzbi(this.zzayj[i2]);
        }
        return zzf + i + (this.zzayj.length * 1);
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
            } else if (zzug == 16) {
                int zzb = zzzj.zzb(zzyxVar, 16);
                int length = this.zzayj == null ? 0 : this.zzayj.length;
                long[] jArr = new long[zzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzayj, 0, jArr, 0, length);
                }
                while (length < jArr.length - 1) {
                    jArr[length] = zzyxVar.zzuz();
                    zzyxVar.zzug();
                    length++;
                }
                jArr[length] = zzyxVar.zzuz();
                this.zzayj = jArr;
            } else if (zzug != 18) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                int zzaq = zzyxVar.zzaq(zzyxVar.zzuy());
                int position = zzyxVar.getPosition();
                int i = 0;
                while (zzyxVar.zzyr() > 0) {
                    zzyxVar.zzuz();
                    i++;
                }
                zzyxVar.zzby(position);
                int length2 = this.zzayj == null ? 0 : this.zzayj.length;
                long[] jArr2 = new long[i + length2];
                if (length2 != 0) {
                    System.arraycopy(this.zzayj, 0, jArr2, 0, length2);
                }
                while (length2 < jArr2.length) {
                    jArr2[length2] = zzyxVar.zzuz();
                    length2++;
                }
                this.zzayj = jArr2;
                zzyxVar.zzar(zzaq);
            }
        }
    }
}
