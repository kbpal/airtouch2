package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public final class zzbf {
    private String value;
    private boolean zzanx;
    private final /* synthetic */ zzba zzany;
    private final String zzaod;
    private final String zzoj;

    public zzbf(zzba zzbaVar, String str, String str2) {
        this.zzany = zzbaVar;
        Preconditions.checkNotEmpty(str);
        this.zzoj = str;
        this.zzaod = null;
    }

    @WorkerThread
    public final String zzjz() {
        SharedPreferences zzjr;
        if (!this.zzanx) {
            this.zzanx = true;
            zzjr = this.zzany.zzjr();
            this.value = zzjr.getString(this.zzoj, null);
        }
        return this.value;
    }

    @WorkerThread
    public final void zzcc(String str) {
        SharedPreferences zzjr;
        if (zzfk.zzu(str, this.value)) {
            return;
        }
        zzjr = this.zzany.zzjr();
        SharedPreferences.Editor edit = zzjr.edit();
        edit.putString(this.zzoj, str);
        edit.apply();
        this.value = str;
    }
}
