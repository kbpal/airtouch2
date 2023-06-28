package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzxl {
    private static final Class<?> zzcbw = zzxu();
    private static final zzyb<?, ?> zzcbx = zzx(false);
    private static final zzyb<?, ?> zzcby = zzx(true);
    private static final zzyb<?, ?> zzcbz = new zzyd();

    public static void zzj(Class<?> cls) {
        if (!zzvm.class.isAssignableFrom(cls) && zzcbw != null && !zzcbw.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzg(i, list, z);
    }

    public static void zzb(int i, List<Float> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzf(i, list, z);
    }

    public static void zzc(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzn(i, list, z);
    }

    public static void zzf(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zze(i, list, z);
    }

    public static void zzg(int i, List<Long> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzl(i, list, z);
    }

    public static void zzh(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zza(i, list, z);
    }

    public static void zzi(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzj(i, list, z);
    }

    public static void zzj(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzm(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzb(i, list, z);
    }

    public static void zzl(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzk(i, list, z);
    }

    public static void zzm(int i, List<Integer> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzh(i, list, z);
    }

    public static void zzn(int i, List<Boolean> list, zzyw zzywVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzi(i, list, z);
    }

    public static void zza(int i, List<String> list, zzyw zzywVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zza(i, list);
    }

    public static void zzb(int i, List<zzud> list, zzyw zzywVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzb(i, list);
    }

    public static void zza(int i, List<?> list, zzyw zzywVar, zzxj zzxjVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zza(i, list, zzxjVar);
    }

    public static void zzb(int i, List<?> list, zzyw zzywVar, zzxj zzxjVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzywVar.zzb(i, list, zzxjVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            i = 0;
            while (i2 < size) {
                i += zzut.zzay(zzwhVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzut.zzay(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzx(list) + (list.size() * zzut.zzbb(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            i = 0;
            while (i2 < size) {
                i += zzut.zzaz(zzwhVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzut.zzaz(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzut.zzbb(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzz(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzwh) {
            zzwh zzwhVar = (zzwh) list;
            i = 0;
            while (i2 < size) {
                i += zzut.zzba(zzwhVar.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzut.zzba(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzz(list) + (size * zzut.zzbb(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzaa(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            i = 0;
            while (i2 < size) {
                i += zzut.zzbh(zzvnVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzut.zzbh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaa(list) + (size * zzut.zzbb(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzab(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            i = 0;
            while (i2 < size) {
                i += zzut.zzbc(zzvnVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzut.zzbc(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzab(list) + (size * zzut.zzbb(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzac(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            i = 0;
            while (i2 < size) {
                i += zzut.zzbd(zzvnVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzut.zzbd(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzac(list) + (size * zzut.zzbb(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzad(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvn) {
            zzvn zzvnVar = (zzvn) list;
            i = 0;
            while (i2 < size) {
                i += zzut.zzbe(zzvnVar.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzut.zzbe(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzad(list) + (size * zzut.zzbb(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzae(List<?> list) {
        return list.size() << 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzut.zzk(i, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzaf(List<?> list) {
        return list.size() << 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzut.zzg(i, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzag(List<?> list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzut.zzc(i, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List<?> list) {
        int zzfx;
        int zzfx2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzbb = zzut.zzbb(i) * size;
        if (list instanceof zzwc) {
            zzwc zzwcVar = (zzwc) list;
            while (i2 < size) {
                Object raw = zzwcVar.getRaw(i2);
                if (raw instanceof zzud) {
                    zzfx2 = zzut.zzb((zzud) raw);
                } else {
                    zzfx2 = zzut.zzfx((String) raw);
                }
                zzbb += zzfx2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzud) {
                    zzfx = zzut.zzb((zzud) obj);
                } else {
                    zzfx = zzut.zzfx((String) obj);
                }
                zzbb += zzfx;
                i2++;
            }
        }
        return zzbb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, Object obj, zzxj zzxjVar) {
        if (obj instanceof zzwa) {
            return zzut.zza(i, (zzwa) obj);
        }
        return zzut.zzb(i, (zzwt) obj, zzxjVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List<?> list, zzxj zzxjVar) {
        int zzb;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzut.zzbb(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzwa) {
                zzb = zzut.zza((zzwa) obj);
            } else {
                zzb = zzut.zzb((zzwt) obj, zzxjVar);
            }
            zzbb += zzb;
        }
        return zzbb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List<zzud> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = size * zzut.zzbb(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzbb += zzut.zzb(list.get(i2));
        }
        return zzbb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List<zzwt> list, zzxj zzxjVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzut.zzc(i, list.get(i3), zzxjVar);
        }
        return i2;
    }

    public static zzyb<?, ?> zzxr() {
        return zzcbx;
    }

    public static zzyb<?, ?> zzxs() {
        return zzcby;
    }

    public static zzyb<?, ?> zzxt() {
        return zzcbz;
    }

    private static zzyb<?, ?> zzx(boolean z) {
        try {
            Class<?> zzxv = zzxv();
            if (zzxv == null) {
                return null;
            }
            return (zzyb) zzxv.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzxu() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzxv() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zze(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void zza(zzwo zzwoVar, T t, T t2, long j) {
        zzyh.zza(t, j, zzwoVar.zzc(zzyh.zzp(t, j), zzyh.zzp(t2, j)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, FT extends zzvf<FT>> void zza(zzva<FT> zzvaVar, T t, T t2) {
        zzvd<FT> zzs = zzvaVar.zzs(t2);
        if (zzs.isEmpty()) {
            return;
        }
        zzvaVar.zzt(t).zza(zzs);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, UT, UB> void zza(zzyb<UT, UB> zzybVar, T t, T t2) {
        zzybVar.zzf(t, zzybVar.zzh(zzybVar.zzah(t), zzybVar.zzah(t2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int i, List<Integer> list, zzvr zzvrVar, UB ub, zzyb<UT, UB> zzybVar) {
        UB ub2;
        int intValue;
        if (zzvrVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue2 = list.get(i3).intValue();
                if (zzvrVar.zzb(intValue2)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = (UB) zza(i, intValue2, ub2, zzybVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            loop1: while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    intValue = it.next().intValue();
                    if (!zzvrVar.zzb(intValue)) {
                        break;
                    }
                }
                ub = (UB) zza(i, intValue, ub2, zzybVar);
                it.remove();
            }
        }
        return ub2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int i, int i2, UB ub, zzyb<UT, UB> zzybVar) {
        if (ub == null) {
            ub = zzybVar.zzye();
        }
        zzybVar.zza((zzyb<UT, UB>) ub, i, i2);
        return ub;
    }
}
