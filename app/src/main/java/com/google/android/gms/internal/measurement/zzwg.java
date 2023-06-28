package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes.dex */
final class zzwg extends zzwd {
    private zzwg() {
        super();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzwd
    public final <L> List<L> zza(Object obj, long j) {
        zzvs zzd = zzd(obj, j);
        if (zzd.zztw()) {
            return zzd;
        }
        int size = zzd.size();
        zzvs zzak = zzd.zzak(size == 0 ? 10 : size << 1);
        zzyh.zza(obj, j, zzak);
        return zzak;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzwd
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzsm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.measurement.zzvs] */
    @Override // com.google.android.gms.internal.measurement.zzwd
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzvs<E> zzd = zzd(obj, j);
        zzvs<E> zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        zzvs<E> zzvsVar = zzd;
        zzvsVar = zzd;
        if (size > 0 && size2 > 0) {
            boolean zztw = zzd.zztw();
            zzvs<E> zzvsVar2 = zzd;
            if (!zztw) {
                zzvsVar2 = zzd.zzak(size2 + size);
            }
            zzvsVar2.addAll(zzd2);
            zzvsVar = zzvsVar2;
        }
        if (size > 0) {
            zzd2 = zzvsVar;
        }
        zzyh.zza(obj, j, zzd2);
    }

    private static <E> zzvs<E> zzd(Object obj, long j) {
        return (zzvs) zzyh.zzp(obj, j);
    }
}
