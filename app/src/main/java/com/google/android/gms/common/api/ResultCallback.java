package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Result;

/* loaded from: classes.dex */
public interface ResultCallback<R extends Result> {
    void onResult(@NonNull R r);
}
