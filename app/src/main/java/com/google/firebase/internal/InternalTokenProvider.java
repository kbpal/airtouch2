package com.google.firebase.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
@KeepForSdk
@Deprecated
/* loaded from: classes.dex */
public interface InternalTokenProvider {
    @KeepForSdk
    Task<GetTokenResult> getAccessToken(boolean z);

    @KeepForSdk
    @Nullable
    String getUid();
}
