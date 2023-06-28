package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

/* loaded from: classes.dex */
public final class zzf extends Binder {
    private final zzb zzu;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zzb zzbVar) {
        this.zzu = zzbVar;
    }

    public final void zza(zzd zzdVar) {
        if (Binder.getCallingUid() != Process.myUid()) {
            throw new SecurityException("Binding only allowed within app");
        }
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "service received new intent via bind strategy");
        }
        if (this.zzu.zzc(zzdVar.intent)) {
            zzdVar.finish();
            return;
        }
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "intent being queued for bg execution");
        }
        this.zzu.zzi.execute(new zzg(this, zzdVar));
    }
}
