package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* loaded from: classes.dex */
final class zzwx<T> implements zzxj<T> {
    private static final int[] zzcax = new int[0];
    private static final Unsafe zzcay = zzyh.zzyk();
    private final int[] zzcaz;
    private final Object[] zzcba;
    private final int zzcbb;
    private final int zzcbc;
    private final zzwt zzcbd;
    private final boolean zzcbe;
    private final boolean zzcbf;
    private final boolean zzcbg;
    private final boolean zzcbh;
    private final int[] zzcbi;
    private final int zzcbj;
    private final int zzcbk;
    private final zzxa zzcbl;
    private final zzwd zzcbm;
    private final zzyb<?, ?> zzcbn;
    private final zzva<?> zzcbo;
    private final zzwo zzcbp;

    private zzwx(int[] iArr, Object[] objArr, int i, int i2, zzwt zzwtVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzxa zzxaVar, zzwd zzwdVar, zzyb<?, ?> zzybVar, zzva<?> zzvaVar, zzwo zzwoVar) {
        this.zzcaz = iArr;
        this.zzcba = objArr;
        this.zzcbb = i;
        this.zzcbc = i2;
        this.zzcbf = zzwtVar instanceof zzvm;
        this.zzcbg = z;
        this.zzcbe = zzvaVar != null && zzvaVar.zze(zzwtVar);
        this.zzcbh = false;
        this.zzcbi = iArr2;
        this.zzcbj = i3;
        this.zzcbk = i4;
        this.zzcbl = zzxaVar;
        this.zzcbm = zzwdVar;
        this.zzcbn = zzybVar;
        this.zzcbo = zzvaVar;
        this.zzcbd = zzwtVar;
        this.zzcbp = zzwoVar;
    }

    private static boolean zzbs(int i) {
        return (i & 536870912) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> com.google.android.gms.internal.measurement.zzwx<T> zza(java.lang.Class<T> r37, com.google.android.gms.internal.measurement.zzwr r38, com.google.android.gms.internal.measurement.zzxa r39, com.google.android.gms.internal.measurement.zzwd r40, com.google.android.gms.internal.measurement.zzyb<?, ?> r41, com.google.android.gms.internal.measurement.zzva<?> r42, com.google.android.gms.internal.measurement.zzwo r43) {
        /*
            Method dump skipped, instructions count: 1170
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zza(java.lang.Class, com.google.android.gms.internal.measurement.zzwr, com.google.android.gms.internal.measurement.zzxa, com.google.android.gms.internal.measurement.zzwd, com.google.android.gms.internal.measurement.zzyb, com.google.android.gms.internal.measurement.zzva, com.google.android.gms.internal.measurement.zzwo):com.google.android.gms.internal.measurement.zzwx");
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final T newInstance() {
        return (T) this.zzcbl.newInstance(this.zzcbd);
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01af, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.measurement.zzxl.zze(com.google.android.gms.internal.measurement.zzyh.zzp(r10, r6), com.google.android.gms.internal.measurement.zzyh.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (com.google.android.gms.internal.measurement.zzxl.zze(com.google.android.gms.internal.measurement.zzyh.zzp(r10, r6), com.google.android.gms.internal.measurement.zzyh.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007e, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a4, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00da, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f0, code lost:
        if (com.google.android.gms.internal.measurement.zzxl.zze(com.google.android.gms.internal.measurement.zzyh.zzp(r10, r6), com.google.android.gms.internal.measurement.zzyh.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0106, code lost:
        if (com.google.android.gms.internal.measurement.zzxl.zze(com.google.android.gms.internal.measurement.zzyh.zzp(r10, r6), com.google.android.gms.internal.measurement.zzyh.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011c, code lost:
        if (com.google.android.gms.internal.measurement.zzxl.zze(com.google.android.gms.internal.measurement.zzyh.zzp(r10, r6), com.google.android.gms.internal.measurement.zzyh.zzp(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x012e, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzm(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzm(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0140, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0154, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0165, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzk(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0178, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x018b, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzl(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzl(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x019c, code lost:
        if (com.google.android.gms.internal.measurement.zzyh.zzk(r10, r6) == com.google.android.gms.internal.measurement.zzyh.zzk(r11, r6)) goto L85;
     */
    @Override // com.google.android.gms.internal.measurement.zzxj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.equals(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final int hashCode(T t) {
        int length = this.zzcaz.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzbq = zzbq(i2);
            int i3 = this.zzcaz[i2];
            long j = 1048575 & zzbq;
            switch ((zzbq & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzvo.zzbf(Double.doubleToLongBits(zzyh.zzo(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzyh.zzn(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzyh.zzk(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzyh.zzk(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzvo.zzw(zzyh.zzm(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzyh.zzp(t, j)).hashCode();
                    break;
                case 9:
                    Object zzp = zzyh.zzp(t, j);
                    i = (i * 53) + (zzp != null ? zzp.hashCode() : 37);
                    break;
                case 10:
                    i = (i * 53) + zzyh.zzp(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzyh.zzk(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzyh.zzk(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzyh.zzk(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzyh.zzk(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzvo.zzbf(zzyh.zzl(t, j));
                    break;
                case 17:
                    Object zzp2 = zzyh.zzp(t, j);
                    i = (i * 53) + (zzp2 != null ? zzp2.hashCode() : 37);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzyh.zzp(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzyh.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzvo.zzbf(Double.doubleToLongBits(zzf(t, j)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + Float.floatToIntBits(zzg(t, j));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzvo.zzbf(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzvo.zzbf(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzvo.zzbf(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzvo.zzw(zzj(t, j));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + ((String) zzyh.zzp(t, j)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzyh.zzp(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzyh.zzp(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzvo.zzbf(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzh(t, j);
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzvo.zzbf(zzi(t, j));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza((zzwx<T>) t, i3, i2)) {
                        i = (i * 53) + zzyh.zzp(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzcbn.zzah(t).hashCode();
        return this.zzcbe ? (hashCode * 53) + this.zzcbo.zzs(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zzd(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzcaz.length; i += 3) {
            int zzbq = zzbq(i);
            long j = 1048575 & zzbq;
            int i2 = this.zzcaz[i];
            switch ((zzbq & 267386880) >>> 20) {
                case 0:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza(t, j, zzyh.zzo(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzn(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza(t, j, zzyh.zzm(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza(t, j, zzyh.zzp(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza(t, j, zzyh.zzp(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zzb(t, j, zzyh.zzk(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzb((zzwx<T>) t2, i)) {
                        zzyh.zza((Object) t, j, zzyh.zzl(t2, j));
                        zzc(t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zza(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzcbm.zza(t, t2, j);
                    break;
                case 50:
                    zzxl.zza(this.zzcbp, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zza((zzwx<T>) t2, i2, i)) {
                        zzyh.zza(t, j, zzyh.zzp(t2, j));
                        zzb((zzwx<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zza((zzwx<T>) t2, i2, i)) {
                        zzyh.zza(t, j, zzyh.zzp(t2, j));
                        zzb((zzwx<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (this.zzcbg) {
            return;
        }
        zzxl.zza(this.zzcbn, t, t2);
        if (this.zzcbe) {
            zzxl.zza(this.zzcbo, t, t2);
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzbq = zzbq(i) & 1048575;
        if (zzb((zzwx<T>) t2, i)) {
            Object zzp = zzyh.zzp(t, zzbq);
            Object zzp2 = zzyh.zzp(t2, zzbq);
            if (zzp != null && zzp2 != null) {
                zzyh.zza(t, zzbq, zzvo.zzb(zzp, zzp2));
                zzc(t, i);
            } else if (zzp2 != null) {
                zzyh.zza(t, zzbq, zzp2);
                zzc(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzbq = zzbq(i);
        int i2 = this.zzcaz[i];
        long j = zzbq & 1048575;
        if (zza((zzwx<T>) t2, i2, i)) {
            Object zzp = zzyh.zzp(t, j);
            Object zzp2 = zzyh.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzyh.zza(t, j, zzvo.zzb(zzp, zzp2));
                zzb((zzwx<T>) t, i2, i);
            } else if (zzp2 != null) {
                zzyh.zza(t, j, zzp2);
                zzb((zzwx<T>) t, i2, i);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.measurement.zzxj
    public final int zzae(T t) {
        int i;
        int i2;
        long j;
        int i3 = 267386880;
        if (this.zzcbg) {
            Unsafe unsafe = zzcay;
            int i4 = 0;
            int i5 = 0;
            while (i4 < this.zzcaz.length) {
                int zzbq = zzbq(i4);
                int i6 = (zzbq & i3) >>> 20;
                int i7 = this.zzcaz[i4];
                long j2 = zzbq & 1048575;
                int i8 = (i6 < zzvg.DOUBLE_LIST_PACKED.id() || i6 > zzvg.SINT64_LIST_PACKED.id()) ? 0 : this.zzcaz[i4 + 2] & 1048575;
                switch (i6) {
                    case 0:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzb(i7, 0.0d);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzb(i7, 0.0f);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzd(i7, zzyh.zzl(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zze(i7, zzyh.zzl(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzh(i7, zzyh.zzk(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzg(i7, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzk(i7, 0);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzc(i7, true);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzb((zzwx<T>) t, i4)) {
                            Object zzp = zzyh.zzp(t, j2);
                            if (zzp instanceof zzud) {
                                i5 += zzut.zzc(i7, (zzud) zzp);
                                break;
                            } else {
                                i5 += zzut.zzc(i7, (String) zzp);
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzxl.zzc(i7, zzyh.zzp(t, j2), zzbn(i4));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzc(i7, (zzud) zzyh.zzp(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzi(i7, zzyh.zzk(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzm(i7, zzyh.zzk(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzl(i7, 0);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzh(i7, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzj(i7, zzyh.zzk(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzf(i7, zzyh.zzl(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzb((zzwx<T>) t, i4)) {
                            i5 += zzut.zzc(i7, (zzwt) zzyh.zzp(t, j2), zzbn(i4));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        i5 += zzxl.zzw(i7, zze(t, j2), false);
                        break;
                    case 19:
                        i5 += zzxl.zzv(i7, zze(t, j2), false);
                        break;
                    case 20:
                        i5 += zzxl.zzo(i7, zze(t, j2), false);
                        break;
                    case 21:
                        i5 += zzxl.zzp(i7, zze(t, j2), false);
                        break;
                    case 22:
                        i5 += zzxl.zzs(i7, zze(t, j2), false);
                        break;
                    case 23:
                        i5 += zzxl.zzw(i7, zze(t, j2), false);
                        break;
                    case 24:
                        i5 += zzxl.zzv(i7, zze(t, j2), false);
                        break;
                    case 25:
                        i5 += zzxl.zzx(i7, zze(t, j2), false);
                        break;
                    case 26:
                        i5 += zzxl.zzc(i7, zze(t, j2));
                        break;
                    case 27:
                        i5 += zzxl.zzc(i7, (List<?>) zze(t, j2), zzbn(i4));
                        break;
                    case 28:
                        i5 += zzxl.zzd(i7, zze(t, j2));
                        break;
                    case 29:
                        i5 += zzxl.zzt(i7, zze(t, j2), false);
                        break;
                    case 30:
                        i5 += zzxl.zzr(i7, zze(t, j2), false);
                        break;
                    case 31:
                        i5 += zzxl.zzv(i7, zze(t, j2), false);
                        break;
                    case 32:
                        i5 += zzxl.zzw(i7, zze(t, j2), false);
                        break;
                    case 33:
                        i5 += zzxl.zzu(i7, zze(t, j2), false);
                        break;
                    case 34:
                        i5 += zzxl.zzq(i7, zze(t, j2), false);
                        break;
                    case 35:
                        int zzaf = zzxl.zzaf((List) unsafe.getObject(t, j2));
                        if (zzaf <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzaf);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzaf) + zzaf;
                            break;
                        }
                    case 36:
                        int zzae = zzxl.zzae((List) unsafe.getObject(t, j2));
                        if (zzae <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzae);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzae) + zzae;
                            break;
                        }
                    case 37:
                        int zzx = zzxl.zzx((List) unsafe.getObject(t, j2));
                        if (zzx <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzx);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzx) + zzx;
                            break;
                        }
                    case 38:
                        int zzy = zzxl.zzy((List) unsafe.getObject(t, j2));
                        if (zzy <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzy);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzy) + zzy;
                            break;
                        }
                    case 39:
                        int zzab = zzxl.zzab((List) unsafe.getObject(t, j2));
                        if (zzab <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzab);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzab) + zzab;
                            break;
                        }
                    case 40:
                        int zzaf2 = zzxl.zzaf((List) unsafe.getObject(t, j2));
                        if (zzaf2 <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzaf2);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzaf2) + zzaf2;
                            break;
                        }
                    case 41:
                        int zzae2 = zzxl.zzae((List) unsafe.getObject(t, j2));
                        if (zzae2 <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzae2);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzae2) + zzae2;
                            break;
                        }
                    case 42:
                        int zzag = zzxl.zzag((List) unsafe.getObject(t, j2));
                        if (zzag <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzag);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzag) + zzag;
                            break;
                        }
                    case 43:
                        int zzac = zzxl.zzac((List) unsafe.getObject(t, j2));
                        if (zzac <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzac);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzac) + zzac;
                            break;
                        }
                    case 44:
                        int zzaa = zzxl.zzaa((List) unsafe.getObject(t, j2));
                        if (zzaa <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzaa);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzaa) + zzaa;
                            break;
                        }
                    case 45:
                        int zzae3 = zzxl.zzae((List) unsafe.getObject(t, j2));
                        if (zzae3 <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzae3);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzae3) + zzae3;
                            break;
                        }
                    case 46:
                        int zzaf3 = zzxl.zzaf((List) unsafe.getObject(t, j2));
                        if (zzaf3 <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzaf3);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzaf3) + zzaf3;
                            break;
                        }
                    case 47:
                        int zzad = zzxl.zzad((List) unsafe.getObject(t, j2));
                        if (zzad <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzad);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzad) + zzad;
                            break;
                        }
                    case 48:
                        int zzz = zzxl.zzz((List) unsafe.getObject(t, j2));
                        if (zzz <= 0) {
                            break;
                        } else {
                            if (this.zzcbh) {
                                unsafe.putInt(t, i8, zzz);
                            }
                            i5 += zzut.zzbb(i7) + zzut.zzbd(zzz) + zzz;
                            break;
                        }
                    case 49:
                        i5 += zzxl.zzd(i7, zze(t, j2), zzbn(i4));
                        break;
                    case 50:
                        i5 += this.zzcbp.zzb(i7, zzyh.zzp(t, j2), zzbo(i4));
                        break;
                    case 51:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzb(i7, 0.0d);
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzb(i7, 0.0f);
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzd(i7, zzi(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zze(i7, zzi(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzh(i7, zzh(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzg(i7, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzk(i7, 0);
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzc(i7, true);
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            Object zzp2 = zzyh.zzp(t, j2);
                            if (zzp2 instanceof zzud) {
                                i5 += zzut.zzc(i7, (zzud) zzp2);
                                break;
                            } else {
                                i5 += zzut.zzc(i7, (String) zzp2);
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzxl.zzc(i7, zzyh.zzp(t, j2), zzbn(i4));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzc(i7, (zzud) zzyh.zzp(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzi(i7, zzh(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzm(i7, zzh(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzl(i7, 0);
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzh(i7, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzj(i7, zzh(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzf(i7, zzi(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzwx<T>) t, i7, i4)) {
                            i5 += zzut.zzc(i7, (zzwt) zzyh.zzp(t, j2), zzbn(i4));
                            break;
                        } else {
                            break;
                        }
                }
                i4 += 3;
                i3 = 267386880;
            }
            return i5 + zza(this.zzcbn, t);
        }
        Unsafe unsafe2 = zzcay;
        int i9 = 0;
        int i10 = -1;
        int i11 = 0;
        for (int i12 = 0; i12 < this.zzcaz.length; i12 += 3) {
            int zzbq2 = zzbq(i12);
            int i13 = this.zzcaz[i12];
            int i14 = (zzbq2 & 267386880) >>> 20;
            if (i14 <= 17) {
                i = this.zzcaz[i12 + 2];
                int i15 = i & 1048575;
                i2 = 1 << (i >>> 20);
                if (i15 != i10) {
                    i11 = unsafe2.getInt(t, i15);
                    i10 = i15;
                }
            } else {
                i = (!this.zzcbh || i14 < zzvg.DOUBLE_LIST_PACKED.id() || i14 > zzvg.SINT64_LIST_PACKED.id()) ? 0 : this.zzcaz[i12 + 2] & 1048575;
                i2 = 0;
            }
            long j3 = zzbq2 & 1048575;
            switch (i14) {
                case 0:
                    j = 0;
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzb(i13, 0.0d);
                        break;
                    }
                    break;
                case 1:
                    j = 0;
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzb(i13, 0.0f);
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzd(i13, unsafe2.getLong(t, j3));
                    }
                    break;
                case 3:
                    j = 0;
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zze(i13, unsafe2.getLong(t, j3));
                    }
                    break;
                case 4:
                    j = 0;
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzh(i13, unsafe2.getInt(t, j3));
                    }
                    break;
                case 5:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzg(i13, 0L);
                        j = 0;
                        break;
                    }
                    j = 0;
                    break;
                case 6:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzk(i13, 0);
                        j = 0;
                        break;
                    }
                    j = 0;
                case 7:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzc(i13, true);
                    }
                    j = 0;
                    break;
                case 8:
                    if ((i11 & i2) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzud) {
                            i9 += zzut.zzc(i13, (zzud) object);
                        } else {
                            i9 += zzut.zzc(i13, (String) object);
                        }
                    }
                    j = 0;
                    break;
                case 9:
                    if ((i11 & i2) != 0) {
                        i9 += zzxl.zzc(i13, unsafe2.getObject(t, j3), zzbn(i12));
                    }
                    j = 0;
                    break;
                case 10:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzc(i13, (zzud) unsafe2.getObject(t, j3));
                    }
                    j = 0;
                    break;
                case 11:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzi(i13, unsafe2.getInt(t, j3));
                    }
                    j = 0;
                    break;
                case 12:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzm(i13, unsafe2.getInt(t, j3));
                    }
                    j = 0;
                    break;
                case 13:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzl(i13, 0);
                    }
                    j = 0;
                    break;
                case 14:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzh(i13, 0L);
                    }
                    j = 0;
                    break;
                case 15:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzj(i13, unsafe2.getInt(t, j3));
                    }
                    j = 0;
                    break;
                case 16:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzf(i13, unsafe2.getLong(t, j3));
                    }
                    j = 0;
                    break;
                case 17:
                    if ((i11 & i2) != 0) {
                        i9 += zzut.zzc(i13, (zzwt) unsafe2.getObject(t, j3), zzbn(i12));
                    }
                    j = 0;
                    break;
                case 18:
                    i9 += zzxl.zzw(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 19:
                    i9 += zzxl.zzv(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 20:
                    i9 += zzxl.zzo(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 21:
                    i9 += zzxl.zzp(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 22:
                    i9 += zzxl.zzs(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 23:
                    i9 += zzxl.zzw(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 24:
                    i9 += zzxl.zzv(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 25:
                    i9 += zzxl.zzx(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 26:
                    i9 += zzxl.zzc(i13, (List) unsafe2.getObject(t, j3));
                    j = 0;
                    break;
                case 27:
                    i9 += zzxl.zzc(i13, (List<?>) unsafe2.getObject(t, j3), zzbn(i12));
                    j = 0;
                    break;
                case 28:
                    i9 += zzxl.zzd(i13, (List) unsafe2.getObject(t, j3));
                    j = 0;
                    break;
                case 29:
                    i9 += zzxl.zzt(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 30:
                    i9 += zzxl.zzr(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 31:
                    i9 += zzxl.zzv(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 32:
                    i9 += zzxl.zzw(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 33:
                    i9 += zzxl.zzu(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 34:
                    i9 += zzxl.zzq(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 35:
                    int zzaf4 = zzxl.zzaf((List) unsafe2.getObject(t, j3));
                    if (zzaf4 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzaf4);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzaf4) + zzaf4;
                    }
                    j = 0;
                    break;
                case 36:
                    int zzae4 = zzxl.zzae((List) unsafe2.getObject(t, j3));
                    if (zzae4 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzae4);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzae4) + zzae4;
                    }
                    j = 0;
                    break;
                case 37:
                    int zzx2 = zzxl.zzx((List) unsafe2.getObject(t, j3));
                    if (zzx2 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzx2);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzx2) + zzx2;
                    }
                    j = 0;
                    break;
                case 38:
                    int zzy2 = zzxl.zzy((List) unsafe2.getObject(t, j3));
                    if (zzy2 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzy2);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzy2) + zzy2;
                    }
                    j = 0;
                    break;
                case 39:
                    int zzab2 = zzxl.zzab((List) unsafe2.getObject(t, j3));
                    if (zzab2 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzab2);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzab2) + zzab2;
                    }
                    j = 0;
                    break;
                case 40:
                    int zzaf5 = zzxl.zzaf((List) unsafe2.getObject(t, j3));
                    if (zzaf5 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzaf5);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzaf5) + zzaf5;
                    }
                    j = 0;
                    break;
                case 41:
                    int zzae5 = zzxl.zzae((List) unsafe2.getObject(t, j3));
                    if (zzae5 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzae5);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzae5) + zzae5;
                    }
                    j = 0;
                    break;
                case 42:
                    int zzag2 = zzxl.zzag((List) unsafe2.getObject(t, j3));
                    if (zzag2 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzag2);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzag2) + zzag2;
                    }
                    j = 0;
                    break;
                case 43:
                    int zzac2 = zzxl.zzac((List) unsafe2.getObject(t, j3));
                    if (zzac2 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzac2);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzac2) + zzac2;
                    }
                    j = 0;
                    break;
                case 44:
                    int zzaa2 = zzxl.zzaa((List) unsafe2.getObject(t, j3));
                    if (zzaa2 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzaa2);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzaa2) + zzaa2;
                    }
                    j = 0;
                    break;
                case 45:
                    int zzae6 = zzxl.zzae((List) unsafe2.getObject(t, j3));
                    if (zzae6 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzae6);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzae6) + zzae6;
                    }
                    j = 0;
                    break;
                case 46:
                    int zzaf6 = zzxl.zzaf((List) unsafe2.getObject(t, j3));
                    if (zzaf6 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzaf6);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzaf6) + zzaf6;
                    }
                    j = 0;
                    break;
                case 47:
                    int zzad2 = zzxl.zzad((List) unsafe2.getObject(t, j3));
                    if (zzad2 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzad2);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzad2) + zzad2;
                    }
                    j = 0;
                    break;
                case 48:
                    int zzz2 = zzxl.zzz((List) unsafe2.getObject(t, j3));
                    if (zzz2 > 0) {
                        if (this.zzcbh) {
                            unsafe2.putInt(t, i, zzz2);
                        }
                        i9 += zzut.zzbb(i13) + zzut.zzbd(zzz2) + zzz2;
                    }
                    j = 0;
                    break;
                case 49:
                    i9 += zzxl.zzd(i13, (List) unsafe2.getObject(t, j3), zzbn(i12));
                    j = 0;
                    break;
                case 50:
                    i9 += this.zzcbp.zzb(i13, unsafe2.getObject(t, j3), zzbo(i12));
                    j = 0;
                    break;
                case 51:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzb(i13, 0.0d);
                    }
                    j = 0;
                    break;
                case 52:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzb(i13, 0.0f);
                    }
                    j = 0;
                    break;
                case 53:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzd(i13, zzi(t, j3));
                    }
                    j = 0;
                    break;
                case 54:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zze(i13, zzi(t, j3));
                    }
                    j = 0;
                    break;
                case 55:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzh(i13, zzh(t, j3));
                    }
                    j = 0;
                    break;
                case 56:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzg(i13, 0L);
                    }
                    j = 0;
                    break;
                case 57:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzk(i13, 0);
                    }
                    j = 0;
                    break;
                case 58:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzc(i13, true);
                    }
                    j = 0;
                    break;
                case 59:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzud) {
                            i9 += zzut.zzc(i13, (zzud) object2);
                        } else {
                            i9 += zzut.zzc(i13, (String) object2);
                        }
                    }
                    j = 0;
                    break;
                case 60:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzxl.zzc(i13, unsafe2.getObject(t, j3), zzbn(i12));
                    }
                    j = 0;
                    break;
                case 61:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzc(i13, (zzud) unsafe2.getObject(t, j3));
                    }
                    j = 0;
                    break;
                case 62:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzi(i13, zzh(t, j3));
                    }
                    j = 0;
                    break;
                case 63:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzm(i13, zzh(t, j3));
                    }
                    j = 0;
                    break;
                case 64:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzl(i13, 0);
                    }
                    j = 0;
                    break;
                case 65:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzh(i13, 0L);
                    }
                    j = 0;
                    break;
                case 66:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzj(i13, zzh(t, j3));
                    }
                    j = 0;
                    break;
                case 67:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzf(i13, zzi(t, j3));
                    }
                    j = 0;
                    break;
                case 68:
                    if (zza((zzwx<T>) t, i13, i12)) {
                        i9 += zzut.zzc(i13, (zzwt) unsafe2.getObject(t, j3), zzbn(i12));
                    }
                    j = 0;
                    break;
                default:
                    j = 0;
                    break;
            }
        }
        int zza = i9 + zza(this.zzcbn, t);
        return this.zzcbe ? zza + this.zzcbo.zzs(t).zzvu() : zza;
    }

    private static <UT, UB> int zza(zzyb<UT, UB> zzybVar, T t) {
        return zzybVar.zzae(zzybVar.zzah(t));
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzyh.zzp(obj, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0a27  */
    @Override // com.google.android.gms.internal.measurement.zzxj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r14, com.google.android.gms.internal.measurement.zzyw r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2914
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzyw):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0527  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzb(T r20, com.google.android.gms.internal.measurement.zzyw r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1488
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zzb(java.lang.Object, com.google.android.gms.internal.measurement.zzyw):void");
    }

    private final <K, V> void zza(zzyw zzywVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzywVar.zza(i, this.zzcbp.zzad(zzbo(i2)), this.zzcbp.zzz(obj));
        }
    }

    private static <UT, UB> void zza(zzyb<UT, UB> zzybVar, T t, zzyw zzywVar) throws IOException {
        zzybVar.zza((zzyb<UT, UB>) zzybVar.zzah(t), zzywVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zza(T t, zzxi zzxiVar, zzuz zzuzVar) throws IOException {
        Object zza;
        if (zzuzVar == null) {
            throw new NullPointerException();
        }
        zzyb zzybVar = this.zzcbn;
        zzva<?> zzvaVar = this.zzcbo;
        zzvd<?> zzvdVar = null;
        Object obj = null;
        while (true) {
            try {
                int zzve = zzxiVar.zzve();
                int i = -1;
                if (zzve >= this.zzcbb && zzve <= this.zzcbc) {
                    int i2 = 0;
                    int length = (this.zzcaz.length / 3) - 1;
                    while (true) {
                        if (i2 <= length) {
                            int i3 = (length + i2) >>> 1;
                            int i4 = i3 * 3;
                            int i5 = this.zzcaz[i4];
                            if (zzve == i5) {
                                i = i4;
                            } else if (zzve < i5) {
                                length = i3 - 1;
                            } else {
                                i2 = i3 + 1;
                            }
                        }
                    }
                }
                if (i >= 0) {
                    int zzbq = zzbq(i);
                    switch ((267386880 & zzbq) >>> 20) {
                        case 0:
                            zzyh.zza(t, zzbq & 1048575, zzxiVar.readDouble());
                            zzc(t, i);
                            break;
                        case 1:
                            zzyh.zza((Object) t, zzbq & 1048575, zzxiVar.readFloat());
                            zzc(t, i);
                            break;
                        case 2:
                            zzyh.zza((Object) t, zzbq & 1048575, zzxiVar.zzui());
                            zzc(t, i);
                            break;
                        case 3:
                            zzyh.zza((Object) t, zzbq & 1048575, zzxiVar.zzuh());
                            zzc(t, i);
                            break;
                        case 4:
                            zzyh.zzb(t, zzbq & 1048575, zzxiVar.zzuj());
                            zzc(t, i);
                            break;
                        case 5:
                            zzyh.zza((Object) t, zzbq & 1048575, zzxiVar.zzuk());
                            zzc(t, i);
                            break;
                        case 6:
                            zzyh.zzb(t, zzbq & 1048575, zzxiVar.zzul());
                            zzc(t, i);
                            break;
                        case 7:
                            zzyh.zza(t, zzbq & 1048575, zzxiVar.zzum());
                            zzc(t, i);
                            break;
                        case 8:
                            zza(t, zzbq, zzxiVar);
                            zzc(t, i);
                            break;
                        case 9:
                            if (zzb((zzwx<T>) t, i)) {
                                long j = zzbq & 1048575;
                                zzyh.zza(t, j, zzvo.zzb(zzyh.zzp(t, j), zzxiVar.zza(zzbn(i), zzuzVar)));
                                break;
                            } else {
                                zzyh.zza(t, zzbq & 1048575, zzxiVar.zza(zzbn(i), zzuzVar));
                                zzc(t, i);
                                break;
                            }
                        case 10:
                            zzyh.zza(t, zzbq & 1048575, zzxiVar.zzuo());
                            zzc(t, i);
                            break;
                        case 11:
                            zzyh.zzb(t, zzbq & 1048575, zzxiVar.zzup());
                            zzc(t, i);
                            break;
                        case 12:
                            int zzuq = zzxiVar.zzuq();
                            zzvr zzbp = zzbp(i);
                            if (zzbp != null && !zzbp.zzb(zzuq)) {
                                zza = zzxl.zza(zzve, zzuq, obj, zzybVar);
                                obj = zza;
                                break;
                            }
                            zzyh.zzb(t, zzbq & 1048575, zzuq);
                            zzc(t, i);
                            break;
                        case 13:
                            zzyh.zzb(t, zzbq & 1048575, zzxiVar.zzur());
                            zzc(t, i);
                            break;
                        case 14:
                            zzyh.zza((Object) t, zzbq & 1048575, zzxiVar.zzus());
                            zzc(t, i);
                            break;
                        case 15:
                            zzyh.zzb(t, zzbq & 1048575, zzxiVar.zzut());
                            zzc(t, i);
                            break;
                        case 16:
                            zzyh.zza((Object) t, zzbq & 1048575, zzxiVar.zzuu());
                            zzc(t, i);
                            break;
                        case 17:
                            if (zzb((zzwx<T>) t, i)) {
                                long j2 = zzbq & 1048575;
                                zzyh.zza(t, j2, zzvo.zzb(zzyh.zzp(t, j2), zzxiVar.zzb(zzbn(i), zzuzVar)));
                                break;
                            } else {
                                zzyh.zza(t, zzbq & 1048575, zzxiVar.zzb(zzbn(i), zzuzVar));
                                zzc(t, i);
                                break;
                            }
                        case 18:
                            zzxiVar.zzh(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 19:
                            zzxiVar.zzi(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 20:
                            zzxiVar.zzk(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 21:
                            zzxiVar.zzj(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 22:
                            zzxiVar.zzl(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 23:
                            zzxiVar.zzm(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 24:
                            zzxiVar.zzn(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 25:
                            zzxiVar.zzo(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 26:
                            if (zzbs(zzbq)) {
                                zzxiVar.zzp(this.zzcbm.zza(t, zzbq & 1048575));
                                break;
                            } else {
                                zzxiVar.readStringList(this.zzcbm.zza(t, zzbq & 1048575));
                                break;
                            }
                        case 27:
                            zzxiVar.zza(this.zzcbm.zza(t, zzbq & 1048575), zzbn(i), zzuzVar);
                            break;
                        case 28:
                            zzxiVar.zzq(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 29:
                            zzxiVar.zzr(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 30:
                            List<Integer> zza2 = this.zzcbm.zza(t, zzbq & 1048575);
                            zzxiVar.zzs(zza2);
                            zza = zzxl.zza(zzve, zza2, zzbp(i), obj, zzybVar);
                            obj = zza;
                            break;
                        case 31:
                            zzxiVar.zzt(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 32:
                            zzxiVar.zzu(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 33:
                            zzxiVar.zzv(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 34:
                            zzxiVar.zzw(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 35:
                            zzxiVar.zzh(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 36:
                            zzxiVar.zzi(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 37:
                            zzxiVar.zzk(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 38:
                            zzxiVar.zzj(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 39:
                            zzxiVar.zzl(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 40:
                            zzxiVar.zzm(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 41:
                            zzxiVar.zzn(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 42:
                            zzxiVar.zzo(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 43:
                            zzxiVar.zzr(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 44:
                            List<Integer> zza3 = this.zzcbm.zza(t, zzbq & 1048575);
                            zzxiVar.zzs(zza3);
                            zza = zzxl.zza(zzve, zza3, zzbp(i), obj, zzybVar);
                            obj = zza;
                            break;
                        case 45:
                            zzxiVar.zzt(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 46:
                            zzxiVar.zzu(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 47:
                            zzxiVar.zzv(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 48:
                            zzxiVar.zzw(this.zzcbm.zza(t, zzbq & 1048575));
                            break;
                        case 49:
                            zzxiVar.zzb(this.zzcbm.zza(t, zzbq & 1048575), zzbn(i), zzuzVar);
                            break;
                        case 50:
                            Object zzbo = zzbo(i);
                            long zzbq2 = zzbq(i) & 1048575;
                            Object zzp = zzyh.zzp(t, zzbq2);
                            if (zzp == null) {
                                zzp = this.zzcbp.zzac(zzbo);
                                zzyh.zza(t, zzbq2, zzp);
                            } else if (this.zzcbp.zzaa(zzp)) {
                                Object zzac = this.zzcbp.zzac(zzbo);
                                this.zzcbp.zzc(zzac, zzp);
                                zzyh.zza(t, zzbq2, zzac);
                                zzp = zzac;
                            }
                            zzxiVar.zza(this.zzcbp.zzy(zzp), this.zzcbp.zzad(zzbo), zzuzVar);
                            break;
                        case 51:
                            zzyh.zza(t, zzbq & 1048575, Double.valueOf(zzxiVar.readDouble()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 52:
                            zzyh.zza(t, zzbq & 1048575, Float.valueOf(zzxiVar.readFloat()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 53:
                            zzyh.zza(t, zzbq & 1048575, Long.valueOf(zzxiVar.zzui()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 54:
                            zzyh.zza(t, zzbq & 1048575, Long.valueOf(zzxiVar.zzuh()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 55:
                            zzyh.zza(t, zzbq & 1048575, Integer.valueOf(zzxiVar.zzuj()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 56:
                            zzyh.zza(t, zzbq & 1048575, Long.valueOf(zzxiVar.zzuk()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 57:
                            zzyh.zza(t, zzbq & 1048575, Integer.valueOf(zzxiVar.zzul()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 58:
                            zzyh.zza(t, zzbq & 1048575, Boolean.valueOf(zzxiVar.zzum()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 59:
                            zza(t, zzbq, zzxiVar);
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 60:
                            if (zza((zzwx<T>) t, zzve, i)) {
                                long j3 = zzbq & 1048575;
                                zzyh.zza(t, j3, zzvo.zzb(zzyh.zzp(t, j3), zzxiVar.zza(zzbn(i), zzuzVar)));
                            } else {
                                zzyh.zza(t, zzbq & 1048575, zzxiVar.zza(zzbn(i), zzuzVar));
                                zzc(t, i);
                            }
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 61:
                            zzyh.zza(t, zzbq & 1048575, zzxiVar.zzuo());
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 62:
                            zzyh.zza(t, zzbq & 1048575, Integer.valueOf(zzxiVar.zzup()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 63:
                            int zzuq2 = zzxiVar.zzuq();
                            zzvr zzbp2 = zzbp(i);
                            if (zzbp2 != null && !zzbp2.zzb(zzuq2)) {
                                zza = zzxl.zza(zzve, zzuq2, obj, zzybVar);
                                obj = zza;
                                break;
                            }
                            zzyh.zza(t, zzbq & 1048575, Integer.valueOf(zzuq2));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 64:
                            zzyh.zza(t, zzbq & 1048575, Integer.valueOf(zzxiVar.zzur()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 65:
                            zzyh.zza(t, zzbq & 1048575, Long.valueOf(zzxiVar.zzus()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 66:
                            zzyh.zza(t, zzbq & 1048575, Integer.valueOf(zzxiVar.zzut()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 67:
                            zzyh.zza(t, zzbq & 1048575, Long.valueOf(zzxiVar.zzuu()));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        case 68:
                            zzyh.zza(t, zzbq & 1048575, zzxiVar.zzb(zzbn(i), zzuzVar));
                            zzb((zzwx<T>) t, zzve, i);
                            break;
                        default:
                            if (obj == null) {
                                try {
                                    obj = zzybVar.zzye();
                                } catch (zzvu unused) {
                                    zzybVar.zza(zzxiVar);
                                    if (obj == null) {
                                        obj = zzybVar.zzai(t);
                                    }
                                    if (!zzybVar.zza((zzyb) obj, zzxiVar)) {
                                        for (int i6 = this.zzcbj; i6 < this.zzcbk; i6++) {
                                            obj = zza((Object) t, this.zzcbi[i6], (int) obj, (zzyb<UT, int>) zzybVar);
                                        }
                                        if (obj != null) {
                                            zzybVar.zzg(t, obj);
                                            return;
                                        }
                                        return;
                                    }
                                    break;
                                }
                            }
                            if (!zzybVar.zza((zzyb) obj, zzxiVar)) {
                                for (int i7 = this.zzcbj; i7 < this.zzcbk; i7++) {
                                    obj = zza((Object) t, this.zzcbi[i7], (int) obj, (zzyb<UT, int>) zzybVar);
                                }
                                if (obj != null) {
                                    zzybVar.zzg(t, obj);
                                    return;
                                }
                                return;
                            }
                            break;
                    }
                } else if (zzve == Integer.MAX_VALUE) {
                    for (int i8 = this.zzcbj; i8 < this.zzcbk; i8++) {
                        obj = zza((Object) t, this.zzcbi[i8], (int) obj, (zzyb<UT, int>) zzybVar);
                    }
                    if (obj != null) {
                        zzybVar.zzg(t, obj);
                        return;
                    }
                    return;
                } else {
                    Object zza4 = !this.zzcbe ? null : zzvaVar.zza(zzuzVar, this.zzcbd, zzve);
                    if (zza4 != null) {
                        if (zzvdVar == null) {
                            zzvdVar = zzvaVar.zzt(t);
                        }
                        zzvd<?> zzvdVar2 = zzvdVar;
                        obj = zzvaVar.zza(zzxiVar, zza4, zzuzVar, zzvdVar2, obj, zzybVar);
                        zzvdVar = zzvdVar2;
                    } else {
                        zzybVar.zza(zzxiVar);
                        if (obj == null) {
                            obj = zzybVar.zzai(t);
                        }
                        if (!zzybVar.zza((zzyb) obj, zzxiVar)) {
                            for (int i9 = this.zzcbj; i9 < this.zzcbk; i9++) {
                                obj = zza((Object) t, this.zzcbi[i9], (int) obj, (zzyb<UT, int>) zzybVar);
                            }
                            if (obj != null) {
                                zzybVar.zzg(t, obj);
                                return;
                            }
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                for (int i10 = this.zzcbj; i10 < this.zzcbk; i10++) {
                    obj = zza((Object) t, this.zzcbi[i10], (int) obj, (zzyb<UT, int>) zzybVar);
                }
                if (obj != null) {
                    zzybVar.zzg(t, obj);
                }
                throw th;
            }
        }
    }

    private final zzxj zzbn(int i) {
        int i2 = (i / 3) << 1;
        zzxj zzxjVar = (zzxj) this.zzcba[i2];
        if (zzxjVar != null) {
            return zzxjVar;
        }
        zzxj<T> zzi = zzxf.zzxn().zzi((Class) this.zzcba[i2 + 1]);
        this.zzcba[i2] = zzi;
        return zzi;
    }

    private final Object zzbo(int i) {
        return this.zzcba[(i / 3) << 1];
    }

    private final zzvr zzbp(int i) {
        return (zzvr) this.zzcba[((i / 3) << 1) + 1];
    }

    @Override // com.google.android.gms.internal.measurement.zzxj
    public final void zzu(T t) {
        for (int i = this.zzcbj; i < this.zzcbk; i++) {
            long zzbq = zzbq(this.zzcbi[i]) & 1048575;
            Object zzp = zzyh.zzp(t, zzbq);
            if (zzp != null) {
                zzyh.zza(t, zzbq, this.zzcbp.zzab(zzp));
            }
        }
        int length = this.zzcbi.length;
        for (int i2 = this.zzcbk; i2 < length; i2++) {
            this.zzcbm.zzb(t, this.zzcbi[i2]);
        }
        this.zzcbn.zzu(t);
        if (this.zzcbe) {
            this.zzcbo.zzu(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzyb<UT, UB> zzybVar) {
        zzvr zzbp;
        int i2 = this.zzcaz[i];
        Object zzp = zzyh.zzp(obj, zzbq(i) & 1048575);
        return (zzp == null || (zzbp = zzbp(i)) == null) ? ub : (UB) zza(i, i2, this.zzcbp.zzy(zzp), zzbp, ub, zzybVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzvr zzvrVar, UB ub, zzyb<UT, UB> zzybVar) {
        zzwm<?, ?> zzad = this.zzcbp.zzad(zzbo(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzvrVar.zzb(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzybVar.zzye();
                }
                zzuk zzam = zzud.zzam(zzwl.zza(zzad, next.getKey(), next.getValue()));
                try {
                    zzwl.zza(zzam.zzuf(), zzad, next.getKey(), next.getValue());
                    zzybVar.zza((zzyb<UT, UB>) ub, i2, zzam.zzue());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x0104, code lost:
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.measurement.zzxj] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.measurement.zzxj] */
    @Override // com.google.android.gms.internal.measurement.zzxj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzaf(T r14) {
        /*
            Method dump skipped, instructions count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zzaf(java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzxj zzxjVar) {
        return zzxjVar.zzaf(zzyh.zzp(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzyw zzywVar) throws IOException {
        if (obj instanceof String) {
            zzywVar.zzb(i, (String) obj);
        } else {
            zzywVar.zza(i, (zzud) obj);
        }
    }

    private final void zza(Object obj, int i, zzxi zzxiVar) throws IOException {
        if (zzbs(i)) {
            zzyh.zza(obj, i & 1048575, zzxiVar.zzun());
        } else if (this.zzcbf) {
            zzyh.zza(obj, i & 1048575, zzxiVar.readString());
        } else {
            zzyh.zza(obj, i & 1048575, zzxiVar.zzuo());
        }
    }

    private final int zzbq(int i) {
        return this.zzcaz[i + 1];
    }

    private final int zzbr(int i) {
        return this.zzcaz[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzyh.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzyh.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzyh.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzyh.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzyh.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzb((zzwx<T>) t, i) == zzb((zzwx<T>) t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzcbg) {
            return zzb((zzwx<T>) t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zzb(T t, int i) {
        if (this.zzcbg) {
            int zzbq = zzbq(i);
            long j = zzbq & 1048575;
            switch ((zzbq & 267386880) >>> 20) {
                case 0:
                    return zzyh.zzo(t, j) != 0.0d;
                case 1:
                    return zzyh.zzn(t, j) != 0.0f;
                case 2:
                    return zzyh.zzl(t, j) != 0;
                case 3:
                    return zzyh.zzl(t, j) != 0;
                case 4:
                    return zzyh.zzk(t, j) != 0;
                case 5:
                    return zzyh.zzl(t, j) != 0;
                case 6:
                    return zzyh.zzk(t, j) != 0;
                case 7:
                    return zzyh.zzm(t, j);
                case 8:
                    Object zzp = zzyh.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    } else if (zzp instanceof zzud) {
                        return !zzud.zzbtz.equals(zzp);
                    } else {
                        throw new IllegalArgumentException();
                    }
                case 9:
                    return zzyh.zzp(t, j) != null;
                case 10:
                    return !zzud.zzbtz.equals(zzyh.zzp(t, j));
                case 11:
                    return zzyh.zzk(t, j) != 0;
                case 12:
                    return zzyh.zzk(t, j) != 0;
                case 13:
                    return zzyh.zzk(t, j) != 0;
                case 14:
                    return zzyh.zzl(t, j) != 0;
                case 15:
                    return zzyh.zzk(t, j) != 0;
                case 16:
                    return zzyh.zzl(t, j) != 0;
                case 17:
                    return zzyh.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int zzbr = zzbr(i);
        return (zzyh.zzk(t, (long) (zzbr & 1048575)) & (1 << (zzbr >>> 20))) != 0;
    }

    private final void zzc(T t, int i) {
        if (this.zzcbg) {
            return;
        }
        int zzbr = zzbr(i);
        long j = zzbr & 1048575;
        zzyh.zzb(t, j, zzyh.zzk(t, j) | (1 << (zzbr >>> 20)));
    }

    private final boolean zza(T t, int i, int i2) {
        return zzyh.zzk(t, (long) (zzbr(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzyh.zzb(t, zzbr(i2) & 1048575, i);
    }
}
