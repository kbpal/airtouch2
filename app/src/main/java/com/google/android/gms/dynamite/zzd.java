package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzd implements DynamiteModule.VersionPolicy {
    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zzaVar) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.zzb zzbVar = new DynamiteModule.VersionPolicy.zzb();
        zzbVar.zziq = zzaVar.getLocalVersion(context, str);
        zzbVar.zzir = zzaVar.zza(context, str, true);
        if (zzbVar.zziq == 0 && zzbVar.zzir == 0) {
            zzbVar.zzis = 0;
        } else if (zzbVar.zziq >= zzbVar.zzir) {
            zzbVar.zzis = -1;
        } else {
            zzbVar.zzis = 1;
        }
        return zzbVar;
    }
}
