package com.google.android.gms.common.api.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.GoogleApiManager;

/* loaded from: classes.dex */
public abstract class zac extends zab {
    public zac(int i) {
        super(i);
    }

    @Nullable
    public abstract Feature[] zab(GoogleApiManager.zaa<?> zaaVar);

    public abstract boolean zac(GoogleApiManager.zaa<?> zaaVar);
}
