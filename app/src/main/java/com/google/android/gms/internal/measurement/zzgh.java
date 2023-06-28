package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzgh extends zzza<zzgh> {
    public zzgi[] zzawy = zzgi.zzms();

    public zzgh() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgh) {
            zzgh zzghVar = (zzgh) obj;
            if (zzze.equals(this.zzawy, zzghVar.zzawy)) {
                if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                    return zzghVar.zzcfc == null || zzghVar.zzcfc.isEmpty();
                }
                return this.zzcfc.equals(zzghVar.zzcfc);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzze.hashCode(this.zzawy)) * 31) + ((this.zzcfc == null || this.zzcfc.isEmpty()) ? 0 : this.zzcfc.hashCode());
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.zzawy != null && this.zzawy.length > 0) {
            for (int i = 0; i < this.zzawy.length; i++) {
                zzgi zzgiVar = this.zzawy[i];
                if (zzgiVar != null) {
                    zzyyVar.zza(1, zzgiVar);
                }
            }
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzawy != null && this.zzawy.length > 0) {
            for (int i = 0; i < this.zzawy.length; i++) {
                zzgi zzgiVar = this.zzawy[i];
                if (zzgiVar != null) {
                    zzf += zzyy.zzb(1, zzgiVar);
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
            if (zzug != 10) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                int zzb = zzzj.zzb(zzyxVar, 10);
                int length = this.zzawy == null ? 0 : this.zzawy.length;
                zzgi[] zzgiVarArr = new zzgi[zzb + length];
                if (length != 0) {
                    System.arraycopy(this.zzawy, 0, zzgiVarArr, 0, length);
                }
                while (length < zzgiVarArr.length - 1) {
                    zzgiVarArr[length] = new zzgi();
                    zzyxVar.zza(zzgiVarArr[length]);
                    zzyxVar.zzug();
                    length++;
                }
                zzgiVarArr[length] = new zzgi();
                zzyxVar.zza(zzgiVarArr[length]);
                this.zzawy = zzgiVarArr;
            }
        }
    }
}
