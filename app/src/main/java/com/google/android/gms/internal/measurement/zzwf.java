package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class zzwf extends zzwd {
    private static final Class<?> zzcal = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzwf() {
        super();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzwd
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzwd
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzyh.zzp(obj, j);
        if (list instanceof zzwc) {
            unmodifiableList = ((zzwc) list).zzww();
        } else if (zzcal.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof zzxe) && (list instanceof zzvs)) {
                zzvs zzvsVar = (zzvs) list;
                if (zzvsVar.zztw()) {
                    zzvsVar.zzsm();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzyh.zza(obj, j, unmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <L> List<L> zza(Object obj, long j, int i) {
        zzwb zzwbVar;
        List<L> arrayList;
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            if (zzc instanceof zzwc) {
                arrayList = new zzwb(i);
            } else if ((zzc instanceof zzxe) && (zzc instanceof zzvs)) {
                arrayList = ((zzvs) zzc).zzak(i);
            } else {
                arrayList = new ArrayList<>(i);
            }
            zzyh.zza(obj, j, arrayList);
            return arrayList;
        }
        if (zzcal.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList2 = new ArrayList(zzc.size() + i);
            arrayList2.addAll(zzc);
            zzyh.zza(obj, j, arrayList2);
            zzwbVar = arrayList2;
        } else if (zzc instanceof zzye) {
            zzwb zzwbVar2 = new zzwb(zzc.size() + i);
            zzwbVar2.addAll((zzye) zzc);
            zzyh.zza(obj, j, zzwbVar2);
            zzwbVar = zzwbVar2;
        } else if ((zzc instanceof zzxe) && (zzc instanceof zzvs)) {
            zzvs zzvsVar = (zzvs) zzc;
            if (zzvsVar.zztw()) {
                return zzc;
            }
            zzvs zzak = zzvsVar.zzak(zzc.size() + i);
            zzyh.zza(obj, j, zzak);
            return zzak;
        } else {
            return zzc;
        }
        return zzwbVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzwd
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzc = zzc(obj2, j);
        List zza = zza(obj, j, zzc.size());
        int size = zza.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzc);
        }
        if (size > 0) {
            zzc = zza;
        }
        zzyh.zza(obj, j, zzc);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzyh.zzp(obj, j);
    }
}
