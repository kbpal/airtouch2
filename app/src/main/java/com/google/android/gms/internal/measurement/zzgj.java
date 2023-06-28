package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzgj extends zzza<zzgj> {
    public long[] zzaye = zzzj.zzcfr;
    public long[] zzayf = zzzj.zzcfr;
    public zzge[] zzayg = zzge.zzmp();
    public zzgk[] zzayh = zzgk.zzmt();

    public zzgj() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgj) {
            zzgj zzgjVar = (zzgj) obj;
            if (zzze.equals(this.zzaye, zzgjVar.zzaye) && zzze.equals(this.zzayf, zzgjVar.zzayf) && zzze.equals(this.zzayg, zzgjVar.zzayg) && zzze.equals(this.zzayh, zzgjVar.zzayh)) {
                if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                    return zzgjVar.zzcfc == null || zzgjVar.zzcfc.isEmpty();
                }
                return this.zzcfc.equals(zzgjVar.zzcfc);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((getClass().getName().hashCode() + 527) * 31) + zzze.hashCode(this.zzaye)) * 31) + zzze.hashCode(this.zzayf)) * 31) + zzze.hashCode(this.zzayg)) * 31) + zzze.hashCode(this.zzayh)) * 31) + ((this.zzcfc == null || this.zzcfc.isEmpty()) ? 0 : this.zzcfc.hashCode());
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzaye != null && this.zzaye.length > 0) {
            for (int i = 0; i < this.zzaye.length; i++) {
                zzyyVar.zza(1, this.zzaye[i]);
            }
        }
        if (this.zzayf != null && this.zzayf.length > 0) {
            for (int i2 = 0; i2 < this.zzayf.length; i2++) {
                zzyyVar.zza(2, this.zzayf[i2]);
            }
        }
        if (this.zzayg != null && this.zzayg.length > 0) {
            for (int i3 = 0; i3 < this.zzayg.length; i3++) {
                zzge zzgeVar = this.zzayg[i3];
                if (zzgeVar != null) {
                    zzyyVar.zza(3, zzgeVar);
                }
            }
        }
        if (this.zzayh != null && this.zzayh.length > 0) {
            for (int i4 = 0; i4 < this.zzayh.length; i4++) {
                zzgk zzgkVar = this.zzayh[i4];
                if (zzgkVar != null) {
                    zzyyVar.zza(4, zzgkVar);
                }
            }
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzaye != null && this.zzaye.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.zzaye.length; i2++) {
                i += zzyy.zzbi(this.zzaye[i2]);
            }
            zzf = zzf + i + (this.zzaye.length * 1);
        }
        if (this.zzayf != null && this.zzayf.length > 0) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.zzayf.length; i4++) {
                i3 += zzyy.zzbi(this.zzayf[i4]);
            }
            zzf = zzf + i3 + (this.zzayf.length * 1);
        }
        if (this.zzayg != null && this.zzayg.length > 0) {
            int i5 = zzf;
            for (int i6 = 0; i6 < this.zzayg.length; i6++) {
                zzge zzgeVar = this.zzayg[i6];
                if (zzgeVar != null) {
                    i5 += zzyy.zzb(3, zzgeVar);
                }
            }
            zzf = i5;
        }
        if (this.zzayh != null && this.zzayh.length > 0) {
            for (int i7 = 0; i7 < this.zzayh.length; i7++) {
                zzgk zzgkVar = this.zzayh[i7];
                if (zzgkVar != null) {
                    zzf += zzyy.zzb(4, zzgkVar);
                }
            }
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
                int zzb = zzzj.zzb(zzyxVar, 8);
                int length = this.zzaye == null ? 0 : this.zzaye.length;
                long[] jArr = new long[zzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzaye, 0, jArr, 0, length);
                }
                while (length < jArr.length - 1) {
                    jArr[length] = zzyxVar.zzuz();
                    zzyxVar.zzug();
                    length++;
                }
                jArr[length] = zzyxVar.zzuz();
                this.zzaye = jArr;
            } else if (zzug == 10) {
                int zzaq = zzyxVar.zzaq(zzyxVar.zzuy());
                int position = zzyxVar.getPosition();
                int i = 0;
                while (zzyxVar.zzyr() > 0) {
                    zzyxVar.zzuz();
                    i++;
                }
                zzyxVar.zzby(position);
                int length2 = this.zzaye == null ? 0 : this.zzaye.length;
                long[] jArr2 = new long[i + length2];
                if (length2 != 0) {
                    System.arraycopy(this.zzaye, 0, jArr2, 0, length2);
                }
                while (length2 < jArr2.length) {
                    jArr2[length2] = zzyxVar.zzuz();
                    length2++;
                }
                this.zzaye = jArr2;
                zzyxVar.zzar(zzaq);
            } else if (zzug == 16) {
                int zzb2 = zzzj.zzb(zzyxVar, 16);
                int length3 = this.zzayf == null ? 0 : this.zzayf.length;
                long[] jArr3 = new long[zzb2 + length3];
                if (length3 != 0) {
                    System.arraycopy(this.zzayf, 0, jArr3, 0, length3);
                }
                while (length3 < jArr3.length - 1) {
                    jArr3[length3] = zzyxVar.zzuz();
                    zzyxVar.zzug();
                    length3++;
                }
                jArr3[length3] = zzyxVar.zzuz();
                this.zzayf = jArr3;
            } else if (zzug == 18) {
                int zzaq2 = zzyxVar.zzaq(zzyxVar.zzuy());
                int position2 = zzyxVar.getPosition();
                int i2 = 0;
                while (zzyxVar.zzyr() > 0) {
                    zzyxVar.zzuz();
                    i2++;
                }
                zzyxVar.zzby(position2);
                int length4 = this.zzayf == null ? 0 : this.zzayf.length;
                long[] jArr4 = new long[i2 + length4];
                if (length4 != 0) {
                    System.arraycopy(this.zzayf, 0, jArr4, 0, length4);
                }
                while (length4 < jArr4.length) {
                    jArr4[length4] = zzyxVar.zzuz();
                    length4++;
                }
                this.zzayf = jArr4;
                zzyxVar.zzar(zzaq2);
            } else if (zzug == 26) {
                int zzb3 = zzzj.zzb(zzyxVar, 26);
                int length5 = this.zzayg == null ? 0 : this.zzayg.length;
                zzge[] zzgeVarArr = new zzge[zzb3 + length5];
                if (length5 != 0) {
                    System.arraycopy(this.zzayg, 0, zzgeVarArr, 0, length5);
                }
                while (length5 < zzgeVarArr.length - 1) {
                    zzgeVarArr[length5] = new zzge();
                    zzyxVar.zza(zzgeVarArr[length5]);
                    zzyxVar.zzug();
                    length5++;
                }
                zzgeVarArr[length5] = new zzge();
                zzyxVar.zza(zzgeVarArr[length5]);
                this.zzayg = zzgeVarArr;
            } else if (zzug != 34) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                int zzb4 = zzzj.zzb(zzyxVar, 34);
                int length6 = this.zzayh == null ? 0 : this.zzayh.length;
                zzgk[] zzgkVarArr = new zzgk[zzb4 + length6];
                if (length6 != 0) {
                    System.arraycopy(this.zzayh, 0, zzgkVarArr, 0, length6);
                }
                while (length6 < zzgkVarArr.length - 1) {
                    zzgkVarArr[length6] = new zzgk();
                    zzyxVar.zza(zzgkVarArr[length6]);
                    zzyxVar.zzug();
                    length6++;
                }
                zzgkVarArr[length6] = new zzgk();
                zzyxVar.zza(zzgkVarArr[length6]);
                this.zzayh = zzgkVarArr;
            }
        }
    }
}
