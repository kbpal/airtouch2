package com.google.firebase.components;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
@KeepForSdk
/* loaded from: classes.dex */
public class ComponentDiscoveryService extends Service {
    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
}
