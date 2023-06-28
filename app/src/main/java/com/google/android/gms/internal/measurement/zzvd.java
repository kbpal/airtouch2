package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zzvd<FieldDescriptorType extends zzvf<FieldDescriptorType>> {
    private static final zzvd zzbvs = new zzvd(true);
    private boolean zzbpo;
    private boolean zzbvr = false;
    private final zzxm<FieldDescriptorType, Object> zzbvq = zzxm.zzbt(16);

    private zzvd() {
    }

    private zzvd(boolean z) {
        zzsm();
    }

    public static <T extends zzvf<T>> zzvd<T> zzvt() {
        return zzbvs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEmpty() {
        return this.zzbvq.isEmpty();
    }

    public final void zzsm() {
        if (this.zzbpo) {
            return;
        }
        this.zzbvq.zzsm();
        this.zzbpo = true;
    }

    public final boolean isImmutable() {
        return this.zzbpo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzvd) {
            return this.zzbvq.equals(((zzvd) obj).zzbvq);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzbvq.hashCode();
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
        if (this.zzbvr) {
            return new zzvz(this.zzbvq.entrySet().iterator());
        }
        return this.zzbvq.entrySet().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
        if (this.zzbvr) {
            return new zzvz(this.zzbvq.zzxy().iterator());
        }
        return this.zzbvq.zzxy().iterator();
    }

    private final Object zza(FieldDescriptorType fielddescriptortype) {
        Object obj = this.zzbvq.get(fielddescriptortype);
        return obj instanceof zzvw ? zzvw.zzwt() : obj;
    }

    private final void zza(FieldDescriptorType fielddescriptortype, Object obj) {
        if (fielddescriptortype.zzvy()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fielddescriptortype.zzvw(), obj2);
            }
            obj = arrayList;
        } else {
            zza(fielddescriptortype.zzvw(), obj);
        }
        if (obj instanceof zzvw) {
            this.zzbvr = true;
        }
        this.zzbvq.zza((zzxm<FieldDescriptorType, Object>) fielddescriptortype, (FieldDescriptorType) obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
        if ((r3 instanceof com.google.android.gms.internal.measurement.zzvp) == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.measurement.zzvw) == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zza(com.google.android.gms.internal.measurement.zzyq r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.measurement.zzvo.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.measurement.zzve.zzbvt
            com.google.android.gms.internal.measurement.zzyv r2 = r2.zzyp()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L40;
                case 2: goto L3d;
                case 3: goto L3a;
                case 4: goto L37;
                case 5: goto L34;
                case 6: goto L31;
                case 7: goto L28;
                case 8: goto L1e;
                case 9: goto L15;
                default: goto L14;
            }
        L14:
            goto L43
        L15:
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzwt
            if (r2 != 0) goto L26
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzvw
            if (r2 == 0) goto L43
            goto L26
        L1e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L26
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzvp
            if (r2 == 0) goto L43
        L26:
            r1 = 1
            goto L43
        L28:
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzud
            if (r2 != 0) goto L26
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L43
            goto L26
        L31:
            boolean r0 = r3 instanceof java.lang.String
            goto L42
        L34:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L42
        L37:
            boolean r0 = r3 instanceof java.lang.Double
            goto L42
        L3a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L42
        L3d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L42
        L40:
            boolean r0 = r3 instanceof java.lang.Integer
        L42:
            r1 = r0
        L43:
            if (r1 == 0) goto L46
            return
        L46:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzvd.zza(com.google.android.gms.internal.measurement.zzyq, java.lang.Object):void");
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzbvq.zzxw(); i++) {
            if (!zzc(this.zzbvq.zzbu(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzbvq.zzxx()) {
            if (!zzc(entry)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzc(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.zzvx() == zzyv.MESSAGE) {
            if (key.zzvy()) {
                for (zzwt zzwtVar : (List) entry.getValue()) {
                    if (!zzwtVar.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzwt) {
                    if (!((zzwt) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzvw) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzvd<FieldDescriptorType> zzvdVar) {
        for (int i = 0; i < zzvdVar.zzbvq.zzxw(); i++) {
            zzd(zzvdVar.zzbvq.zzbu(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : zzvdVar.zzbvq.zzxx()) {
            zzd(entry);
        }
    }

    private static Object zzv(Object obj) {
        if (obj instanceof zzwz) {
            return ((zzwz) obj).zzxj();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    private final void zzd(Map.Entry<FieldDescriptorType, Object> entry) {
        zzwt zzwj;
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzvw) {
            value = zzvw.zzwt();
        }
        if (key.zzvy()) {
            Object zza = zza((zzvd<FieldDescriptorType>) key);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) zza).add(zzv(obj));
            }
            this.zzbvq.zza((zzxm<FieldDescriptorType, Object>) key, (FieldDescriptorType) zza);
        } else if (key.zzvx() == zzyv.MESSAGE) {
            Object zza2 = zza((zzvd<FieldDescriptorType>) key);
            if (zza2 == null) {
                this.zzbvq.zza((zzxm<FieldDescriptorType, Object>) key, (FieldDescriptorType) zzv(value));
                return;
            }
            if (zza2 instanceof zzwz) {
                zzwj = key.zza((zzwz) zza2, (zzwz) value);
            } else {
                zzwj = key.zza(((zzwt) zza2).zzwd(), (zzwt) value).zzwj();
            }
            this.zzbvq.zza((zzxm<FieldDescriptorType, Object>) key, (FieldDescriptorType) zzwj);
        } else {
            this.zzbvq.zza((zzxm<FieldDescriptorType, Object>) key, (FieldDescriptorType) zzv(value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzut zzutVar, zzyq zzyqVar, int i, Object obj) throws IOException {
        if (zzyqVar == zzyq.zzcdz) {
            zzwt zzwtVar = (zzwt) obj;
            zzvo.zzf(zzwtVar);
            zzutVar.zzc(i, 3);
            zzwtVar.zzb(zzutVar);
            zzutVar.zzc(i, 4);
            return;
        }
        zzutVar.zzc(i, zzyqVar.zzyq());
        switch (zzve.zzbuu[zzyqVar.ordinal()]) {
            case 1:
                zzutVar.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                zzutVar.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzutVar.zzav(((Long) obj).longValue());
                return;
            case 4:
                zzutVar.zzav(((Long) obj).longValue());
                return;
            case 5:
                zzutVar.zzax(((Integer) obj).intValue());
                return;
            case 6:
                zzutVar.zzax(((Long) obj).longValue());
                return;
            case 7:
                zzutVar.zzba(((Integer) obj).intValue());
                return;
            case 8:
                zzutVar.zzu(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzwt) obj).zzb(zzutVar);
                return;
            case 10:
                zzutVar.zzb((zzwt) obj);
                return;
            case 11:
                if (obj instanceof zzud) {
                    zzutVar.zza((zzud) obj);
                    return;
                } else {
                    zzutVar.zzfw((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzud) {
                    zzutVar.zza((zzud) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzutVar.zze(bArr, 0, bArr.length);
                return;
            case 13:
                zzutVar.zzay(((Integer) obj).intValue());
                return;
            case 14:
                zzutVar.zzba(((Integer) obj).intValue());
                return;
            case 15:
                zzutVar.zzax(((Long) obj).longValue());
                return;
            case 16:
                zzutVar.zzaz(((Integer) obj).intValue());
                return;
            case 17:
                zzutVar.zzaw(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzvp) {
                    zzutVar.zzax(((zzvp) obj).zzc());
                    return;
                } else {
                    zzutVar.zzax(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzvu() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzbvq.zzxw(); i2++) {
            Map.Entry<FieldDescriptorType, Object> zzbu = this.zzbvq.zzbu(i2);
            i += zzb((zzvf<?>) zzbu.getKey(), zzbu.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzbvq.zzxx()) {
            i += zzb((zzvf<?>) entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int zzvv() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzbvq.zzxw(); i2++) {
            i += zze(this.zzbvq.zzbu(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzbvq.zzxx()) {
            i += zze(entry);
        }
        return i;
    }

    private static int zze(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzvx() == zzyv.MESSAGE && !key.zzvy() && !key.zzvz()) {
            if (value instanceof zzvw) {
                return zzut.zzb(entry.getKey().zzc(), (zzvw) value);
            }
            return zzut.zzd(entry.getKey().zzc(), (zzwt) value);
        }
        return zzb((zzvf<?>) key, value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzyq zzyqVar, int i, Object obj) {
        int zzbb = zzut.zzbb(i);
        if (zzyqVar == zzyq.zzcdz) {
            zzvo.zzf((zzwt) obj);
            zzbb <<= 1;
        }
        return zzbb + zzb(zzyqVar, obj);
    }

    private static int zzb(zzyq zzyqVar, Object obj) {
        switch (zzve.zzbuu[zzyqVar.ordinal()]) {
            case 1:
                return zzut.zzc(((Double) obj).doubleValue());
            case 2:
                return zzut.zzb(((Float) obj).floatValue());
            case 3:
                return zzut.zzay(((Long) obj).longValue());
            case 4:
                return zzut.zzaz(((Long) obj).longValue());
            case 5:
                return zzut.zzbc(((Integer) obj).intValue());
            case 6:
                return zzut.zzbb(((Long) obj).longValue());
            case 7:
                return zzut.zzbf(((Integer) obj).intValue());
            case 8:
                return zzut.zzv(((Boolean) obj).booleanValue());
            case 9:
                return zzut.zzd((zzwt) obj);
            case 10:
                if (obj instanceof zzvw) {
                    return zzut.zza((zzvw) obj);
                }
                return zzut.zzc((zzwt) obj);
            case 11:
                if (obj instanceof zzud) {
                    return zzut.zzb((zzud) obj);
                }
                return zzut.zzfx((String) obj);
            case 12:
                if (obj instanceof zzud) {
                    return zzut.zzb((zzud) obj);
                }
                return zzut.zzk((byte[]) obj);
            case 13:
                return zzut.zzbd(((Integer) obj).intValue());
            case 14:
                return zzut.zzbg(((Integer) obj).intValue());
            case 15:
                return zzut.zzbc(((Long) obj).longValue());
            case 16:
                return zzut.zzbe(((Integer) obj).intValue());
            case 17:
                return zzut.zzba(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzvp) {
                    return zzut.zzbh(((zzvp) obj).zzc());
                }
                return zzut.zzbh(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static int zzb(zzvf<?> zzvfVar, Object obj) {
        zzyq zzvw = zzvfVar.zzvw();
        int zzc = zzvfVar.zzc();
        if (zzvfVar.zzvy()) {
            int i = 0;
            if (zzvfVar.zzvz()) {
                for (Object obj2 : (List) obj) {
                    i += zzb(zzvw, obj2);
                }
                return zzut.zzbb(zzc) + i + zzut.zzbj(i);
            }
            for (Object obj3 : (List) obj) {
                i += zza(zzvw, zzc, obj3);
            }
            return i;
        }
        return zza(zzvw, zzc, obj);
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzvd zzvdVar = new zzvd();
        for (int i = 0; i < this.zzbvq.zzxw(); i++) {
            Map.Entry<FieldDescriptorType, Object> zzbu = this.zzbvq.zzbu(i);
            zzvdVar.zza((zzvd) zzbu.getKey(), zzbu.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.zzbvq.zzxx()) {
            zzvdVar.zza((zzvd) entry.getKey(), entry.getValue());
        }
        zzvdVar.zzbvr = this.zzbvr;
        return zzvdVar;
    }
}
