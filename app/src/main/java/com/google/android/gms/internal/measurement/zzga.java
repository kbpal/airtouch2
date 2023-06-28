package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzga extends zzza<zzga> {
    private static volatile zzga[] zzawa;
    public String name = null;
    public Boolean zzawb = null;
    public Boolean zzawc = null;
    public Integer zzawd = null;

    public static zzga[] zzmm() {
        if (zzawa == null) {
            synchronized (zzze.zzcfl) {
                if (zzawa == null) {
                    zzawa = new zzga[0];
                }
            }
        }
        return zzawa;
    }

    public zzga() {
        this.zzcfc = null;
        this.zzcfm = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzga) {
            zzga zzgaVar = (zzga) obj;
            if (this.name == null) {
                if (zzgaVar.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzgaVar.name)) {
                return false;
            }
            if (this.zzawb == null) {
                if (zzgaVar.zzawb != null) {
                    return false;
                }
            } else if (!this.zzawb.equals(zzgaVar.zzawb)) {
                return false;
            }
            if (this.zzawc == null) {
                if (zzgaVar.zzawc != null) {
                    return false;
                }
            } else if (!this.zzawc.equals(zzgaVar.zzawc)) {
                return false;
            }
            if (this.zzawd == null) {
                if (zzgaVar.zzawd != null) {
                    return false;
                }
            } else if (!this.zzawd.equals(zzgaVar.zzawd)) {
                return false;
            }
            if (this.zzcfc == null || this.zzcfc.isEmpty()) {
                return zzgaVar.zzcfc == null || zzgaVar.zzcfc.isEmpty();
            }
            return this.zzcfc.equals(zzgaVar.zzcfc);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzawb == null ? 0 : this.zzawb.hashCode())) * 31) + (this.zzawc == null ? 0 : this.zzawc.hashCode())) * 31) + (this.zzawd == null ? 0 : this.zzawd.hashCode())) * 31;
        if (this.zzcfc != null && !this.zzcfc.isEmpty()) {
            i = this.zzcfc.hashCode();
        }
        return hashCode + i;
    }

    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.name != null) {
            zzyyVar.zzb(1, this.name);
        }
        if (this.zzawb != null) {
            zzyyVar.zzb(2, this.zzawb.booleanValue());
        }
        if (this.zzawc != null) {
            zzyyVar.zzb(3, this.zzawc.booleanValue());
        }
        if (this.zzawd != null) {
            zzyyVar.zzd(4, this.zzawd.intValue());
        }
        super.zza(zzyyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzza, com.google.android.gms.internal.measurement.zzzg
    public final int zzf() {
        int zzf = super.zzf();
        if (this.name != null) {
            zzf += zzyy.zzc(1, this.name);
        }
        if (this.zzawb != null) {
            this.zzawb.booleanValue();
            zzf += zzyy.zzbb(2) + 1;
        }
        if (this.zzawc != null) {
            this.zzawc.booleanValue();
            zzf += zzyy.zzbb(3) + 1;
        }
        return this.zzawd != null ? zzf + zzyy.zzh(4, this.zzawd.intValue()) : zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzzg
    public final /* synthetic */ zzzg zza(zzyx zzyxVar) throws IOException {
        while (true) {
            int zzug = zzyxVar.zzug();
            if (zzug == 0) {
                return this;
            }
            if (zzug == 10) {
                this.name = zzyxVar.readString();
            } else if (zzug == 16) {
                this.zzawb = Boolean.valueOf(zzyxVar.zzum());
            } else if (zzug == 24) {
                this.zzawc = Boolean.valueOf(zzyxVar.zzum());
            } else if (zzug != 32) {
                if (!super.zza(zzyxVar, zzug)) {
                    return this;
                }
            } else {
                this.zzawd = Integer.valueOf(zzyxVar.zzuy());
            }
        }
    }
}
