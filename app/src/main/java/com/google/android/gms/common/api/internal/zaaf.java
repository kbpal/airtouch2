package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
final class zaaf {
    private final zai<?> zafp;
    private final TaskCompletionSource<Boolean> zafq = new TaskCompletionSource<>();

    public zaaf(zai<?> zaiVar) {
        this.zafp = zaiVar;
    }

    public final zai<?> zak() {
        return this.zafp;
    }

    public final TaskCompletionSource<Boolean> zaal() {
        return this.zafq;
    }
}
