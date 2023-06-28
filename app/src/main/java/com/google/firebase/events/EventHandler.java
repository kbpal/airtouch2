package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
@KeepForSdk
/* loaded from: classes.dex */
public interface EventHandler<T> {
    @KeepForSdk
    void handle(Event<T> event);
}
