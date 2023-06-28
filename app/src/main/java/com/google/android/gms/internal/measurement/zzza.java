package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzza;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class zzza<M extends zzza<M>> extends zzzg {
    protected zzzc zzcfc;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzzg
    public int zzf() {
        if (this.zzcfc != null) {
            int i = 0;
            for (int i2 = 0; i2 < this.zzcfc.size(); i2++) {
                i += this.zzcfc.zzcc(i2).zzf();
            }
            return i;
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public void zza(zzyy zzyyVar) throws IOException {
        if (this.zzcfc == null) {
            return;
        }
        for (int i = 0; i < this.zzcfc.size(); i++) {
            this.zzcfc.zzcc(i).zza(zzyyVar);
        }
    }

    public final <T> T zza(zzzb<M, T> zzzbVar) {
        zzzd zzcb;
        if (this.zzcfc == null || (zzcb = this.zzcfc.zzcb(zzzbVar.tag >>> 3)) == null) {
            return null;
        }
        return (T) zzcb.zzb(zzzbVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zza(zzyx zzyxVar, int i) throws IOException {
        int position = zzyxVar.getPosition();
        if (zzyxVar.zzao(i)) {
            int i2 = i >>> 3;
            zzzi zzziVar = new zzzi(i, zzyxVar.zzs(position, zzyxVar.getPosition() - position));
            zzzd zzzdVar = null;
            if (this.zzcfc == null) {
                this.zzcfc = new zzzc();
            } else {
                zzzdVar = this.zzcfc.zzcb(i2);
            }
            if (zzzdVar == null) {
                zzzdVar = new zzzd();
                this.zzcfc.zza(i2, zzzdVar);
            }
            zzzdVar.zza(zzziVar);
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zzyu() throws CloneNotSupportedException {
        return (zzza) clone();
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzza zzzaVar = (zzza) super.clone();
        zzze.zza(this, zzzaVar);
        return zzzaVar;
    }
}
