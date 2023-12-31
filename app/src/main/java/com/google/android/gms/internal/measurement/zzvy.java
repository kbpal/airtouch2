package com.google.android.gms.internal.measurement;

import java.util.Map;

/* loaded from: classes.dex */
final class zzvy<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzvw> zzcab;

    private zzvy(Map.Entry<K, zzvw> entry) {
        this.zzcab = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zzcab.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zzcab.getValue() == null) {
            return null;
        }
        return zzvw.zzwt();
    }

    public final zzvw zzwu() {
        return this.zzcab.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (!(obj instanceof zzwt)) {
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
        return this.zzcab.getValue().zzi((zzwt) obj);
    }
}
