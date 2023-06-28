package com.google.android.gms.internal.measurement;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class zzxm<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzbpo;
    private final int zzcca;
    private List<zzxt> zzccb;
    private Map<K, V> zzccc;
    private volatile zzxv zzccd;
    private Map<K, V> zzcce;
    private volatile zzxp zzccf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <FieldDescriptorType extends zzvf<FieldDescriptorType>> zzxm<FieldDescriptorType, Object> zzbt(int i) {
        return new zzxn(i);
    }

    private zzxm(int i) {
        this.zzcca = i;
        this.zzccb = Collections.emptyList();
        this.zzccc = Collections.emptyMap();
        this.zzcce = Collections.emptyMap();
    }

    public void zzsm() {
        Map<K, V> unmodifiableMap;
        Map<K, V> unmodifiableMap2;
        if (this.zzbpo) {
            return;
        }
        if (this.zzccc.isEmpty()) {
            unmodifiableMap = Collections.emptyMap();
        } else {
            unmodifiableMap = Collections.unmodifiableMap(this.zzccc);
        }
        this.zzccc = unmodifiableMap;
        if (this.zzcce.isEmpty()) {
            unmodifiableMap2 = Collections.emptyMap();
        } else {
            unmodifiableMap2 = Collections.unmodifiableMap(this.zzcce);
        }
        this.zzcce = unmodifiableMap2;
        this.zzbpo = true;
    }

    public final boolean isImmutable() {
        return this.zzbpo;
    }

    public final int zzxw() {
        return this.zzccb.size();
    }

    public final Map.Entry<K, V> zzbu(int i) {
        return this.zzccb.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzxx() {
        if (this.zzccc.isEmpty()) {
            return zzxq.zzyc();
        }
        return this.zzccc.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzccb.size() + this.zzccc.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((zzxm<K, V>) comparable) >= 0 || this.zzccc.containsKey(comparable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((zzxm<K, V>) comparable);
        if (zza >= 0) {
            return (V) this.zzccb.get(zza).getValue();
        }
        return this.zzccc.get(comparable);
    }

    public final V zza(K k, V v) {
        zzxz();
        int zza = zza((zzxm<K, V>) k);
        if (zza >= 0) {
            return (V) this.zzccb.get(zza).setValue(v);
        }
        zzxz();
        if (this.zzccb.isEmpty() && !(this.zzccb instanceof ArrayList)) {
            this.zzccb = new ArrayList(this.zzcca);
        }
        int i = -(zza + 1);
        if (i >= this.zzcca) {
            return zzya().put(k, v);
        }
        if (this.zzccb.size() == this.zzcca) {
            zzxt remove = this.zzccb.remove(this.zzcca - 1);
            zzya().put((K) remove.getKey(), (V) remove.getValue());
        }
        this.zzccb.add(i, new zzxt(this, k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzxz();
        if (!this.zzccb.isEmpty()) {
            this.zzccb.clear();
        }
        if (this.zzccc.isEmpty()) {
            return;
        }
        this.zzccc.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzxz();
        Comparable comparable = (Comparable) obj;
        int zza = zza((zzxm<K, V>) comparable);
        if (zza >= 0) {
            return (V) zzbv(zza);
        }
        if (this.zzccc.isEmpty()) {
            return null;
        }
        return this.zzccc.remove(comparable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzbv(int i) {
        zzxz();
        V v = (V) this.zzccb.remove(i).getValue();
        if (!this.zzccc.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzya().entrySet().iterator();
            this.zzccb.add(new zzxt(this, it.next()));
            it.remove();
        }
        return v;
    }

    private final int zza(K k) {
        int size = this.zzccb.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzccb.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzccb.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzccd == null) {
            this.zzccd = new zzxv(this, null);
        }
        return this.zzccd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzxy() {
        if (this.zzccf == null) {
            this.zzccf = new zzxp(this, null);
        }
        return this.zzccf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzxz() {
        if (this.zzbpo) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzya() {
        zzxz();
        if (this.zzccc.isEmpty() && !(this.zzccc instanceof TreeMap)) {
            this.zzccc = new TreeMap();
            this.zzcce = ((TreeMap) this.zzccc).descendingMap();
        }
        return (SortedMap) this.zzccc;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzxm)) {
            return super.equals(obj);
        }
        zzxm zzxmVar = (zzxm) obj;
        int size = size();
        if (size != zzxmVar.size()) {
            return false;
        }
        int zzxw = zzxw();
        if (zzxw != zzxmVar.zzxw()) {
            return entrySet().equals(zzxmVar.entrySet());
        }
        for (int i = 0; i < zzxw; i++) {
            if (!zzbu(i).equals(zzxmVar.zzbu(i))) {
                return false;
            }
        }
        if (zzxw != size) {
            return this.zzccc.equals(zzxmVar.zzccc);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int zzxw = zzxw();
        int i = 0;
        for (int i2 = 0; i2 < zzxw; i2++) {
            i += this.zzccb.get(i2).hashCode();
        }
        return this.zzccc.size() > 0 ? i + this.zzccc.hashCode() : i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((zzxm<K, V>) ((Comparable) obj), (Comparable) obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzxm(int i, zzxn zzxnVar) {
        this(i);
    }
}
