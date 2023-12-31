package io.fabric.sdk.android.services.cache;

import android.content.Context;

/* loaded from: classes.dex */
public class MemoryValueCache<T> extends AbstractValueCache<T> {
    private T value;

    public MemoryValueCache() {
        this(null);
    }

    public MemoryValueCache(ValueCache<T> valueCache) {
        super(valueCache);
    }

    @Override // io.fabric.sdk.android.services.cache.AbstractValueCache
    protected void doInvalidate(Context context) {
        this.value = null;
    }

    @Override // io.fabric.sdk.android.services.cache.AbstractValueCache
    protected T getCached(Context context) {
        return this.value;
    }

    @Override // io.fabric.sdk.android.services.cache.AbstractValueCache
    protected void cacheValue(Context context, T t) {
        this.value = t;
    }
}
