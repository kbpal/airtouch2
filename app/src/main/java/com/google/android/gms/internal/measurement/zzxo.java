package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
final class zzxo<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzccg;
    private final /* synthetic */ zzxm zzcch;

    private zzxo(zzxm zzxmVar) {
        List list;
        this.zzcch = zzxmVar;
        list = this.zzcch.zzccb;
        this.pos = list.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        if (this.pos > 0) {
            int i = this.pos;
            list = this.zzcch.zzccb;
            if (i <= list.size()) {
                return true;
            }
        }
        return zzyb().hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzyb() {
        Map map;
        if (this.zzccg == null) {
            map = this.zzcch.zzcce;
            this.zzccg = map.entrySet().iterator();
        }
        return this.zzccg;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        if (zzyb().hasNext()) {
            return zzyb().next();
        }
        list = this.zzcch.zzccb;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) list.get(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzxo(zzxm zzxmVar, zzxn zzxnVar) {
        this(zzxmVar);
    }
}
