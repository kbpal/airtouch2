package com.google.android.gms.internal.measurement;

import java.util.Arrays;

/* loaded from: classes.dex */
final class zzzi {
    final int tag;
    final byte[] zzbug;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzzi(int i, byte[] bArr) {
        this.tag = i;
        this.zzbug = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzzi) {
            zzzi zzziVar = (zzzi) obj;
            return this.tag == zzziVar.tag && Arrays.equals(this.zzbug, zzziVar.zzbug);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzbug);
    }
}
