package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
@KeepForSdk
/* loaded from: classes.dex */
public interface Publisher {
    @KeepForSdk
    void publish(Event<?> event);
}
