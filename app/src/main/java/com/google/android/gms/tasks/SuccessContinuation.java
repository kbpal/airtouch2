package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public interface SuccessContinuation<TResult, TContinuationResult> {
    @NonNull
    Task<TContinuationResult> then(@Nullable TResult tresult) throws Exception;
}
