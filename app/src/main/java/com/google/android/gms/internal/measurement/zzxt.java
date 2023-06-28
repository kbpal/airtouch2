package com.google.android.gms.internal.measurement;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX WARN: Incorrect field signature: TK; */
/* loaded from: classes.dex */
public final class zzxt<K, V> implements Comparable<zzxt>, Map.Entry<K, V> {
    private V value;
    private final /* synthetic */ zzxm zzcch;
    private final Comparable zzcck;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzxt(zzxm zzxmVar, Map.Entry<K, V> entry) {
        this(zzxmVar, (Comparable) entry.getKey(), entry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public zzxt(zzxm zzxmVar, K k, V v) {
        this.zzcch = zzxmVar;
        this.zzcck = k;
        this.value = v;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        this.zzcch.zzxz();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return equals(this.zzcck, entry.getKey()) && equals(this.value, entry.getValue());
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return (this.zzcck == null ? 0 : this.zzcck.hashCode()) ^ (this.value != null ? this.value.hashCode() : 0);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzcck);
        String valueOf2 = String.valueOf(this.value);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        return sb.toString();
    }

    private static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzcck;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzxt zzxtVar) {
        return ((Comparable) getKey()).compareTo((Comparable) zzxtVar.getKey());
    }
}
