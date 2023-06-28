package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class zzzd implements Cloneable {
    private Object value;
    private zzzb<?, ?> zzcfj;
    private List<zzzi> zzcfk = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzzi zzziVar) throws IOException {
        Object zzah;
        if (this.zzcfk != null) {
            this.zzcfk.add(zzziVar);
            return;
        }
        if (this.value instanceof zzzg) {
            byte[] bArr = zzziVar.zzbug;
            zzyx zzj = zzyx.zzj(bArr, 0, bArr.length);
            int zzuy = zzj.zzuy();
            if (zzuy != bArr.length - zzyy.zzbc(zzuy)) {
                throw zzzf.zzyw();
            }
            zzah = ((zzzg) this.value).zza(zzj);
        } else if (this.value instanceof zzzg[]) {
            zzzg[] zzzgVarArr = (zzzg[]) this.zzcfj.zzah(Collections.singletonList(zzziVar));
            zzzg[] zzzgVarArr2 = (zzzg[]) this.value;
            zzzg[] zzzgVarArr3 = (zzzg[]) Arrays.copyOf(zzzgVarArr2, zzzgVarArr2.length + zzzgVarArr.length);
            System.arraycopy(zzzgVarArr, 0, zzzgVarArr3, zzzgVarArr2.length, zzzgVarArr.length);
            zzah = zzzgVarArr3;
        } else {
            zzah = this.zzcfj.zzah(Collections.singletonList(zzziVar));
        }
        this.zzcfj = this.zzcfj;
        this.value = zzah;
        this.zzcfk = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T zzb(zzzb<?, T> zzzbVar) {
        if (this.value != null) {
            if (!this.zzcfj.equals(zzzbVar)) {
                throw new IllegalStateException("Tried to getExtension with a different Extension.");
            }
        } else {
            this.zzcfj = zzzbVar;
            this.value = zzzbVar.zzah(this.zzcfk);
            this.zzcfk = null;
        }
        return (T) this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzf() {
        if (this.value != null) {
            zzzb<?, ?> zzzbVar = this.zzcfj;
            Object obj = this.value;
            if (zzzbVar.zzcfe) {
                int length = Array.getLength(obj);
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    if (Array.get(obj, i2) != null) {
                        i += zzzbVar.zzak(Array.get(obj, i2));
                    }
                }
                return i;
            }
            return zzzbVar.zzak(obj);
        }
        int i3 = 0;
        for (zzzi zzziVar : this.zzcfk) {
            i3 += zzyy.zzbj(zzziVar.tag) + 0 + zzziVar.zzbug.length;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzyy zzyyVar) throws IOException {
        if (this.value != null) {
            zzzb<?, ?> zzzbVar = this.zzcfj;
            Object obj = this.value;
            if (zzzbVar.zzcfe) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzzbVar.zza(obj2, zzyyVar);
                    }
                }
                return;
            }
            zzzbVar.zza(obj, zzyyVar);
            return;
        }
        for (zzzi zzziVar : this.zzcfk) {
            zzyyVar.zzca(zzziVar.tag);
            zzyyVar.zzp(zzziVar.zzbug);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzzd) {
            zzzd zzzdVar = (zzzd) obj;
            if (this.value != null && zzzdVar.value != null) {
                if (this.zzcfj != zzzdVar.zzcfj) {
                    return false;
                }
                if (!this.zzcfj.zzcfd.isArray()) {
                    return this.value.equals(zzzdVar.value);
                }
                if (this.value instanceof byte[]) {
                    return Arrays.equals((byte[]) this.value, (byte[]) zzzdVar.value);
                }
                if (this.value instanceof int[]) {
                    return Arrays.equals((int[]) this.value, (int[]) zzzdVar.value);
                }
                if (this.value instanceof long[]) {
                    return Arrays.equals((long[]) this.value, (long[]) zzzdVar.value);
                }
                if (this.value instanceof float[]) {
                    return Arrays.equals((float[]) this.value, (float[]) zzzdVar.value);
                }
                if (this.value instanceof double[]) {
                    return Arrays.equals((double[]) this.value, (double[]) zzzdVar.value);
                }
                if (this.value instanceof boolean[]) {
                    return Arrays.equals((boolean[]) this.value, (boolean[]) zzzdVar.value);
                }
                return Arrays.deepEquals((Object[]) this.value, (Object[]) zzzdVar.value);
            } else if (this.zzcfk != null && zzzdVar.zzcfk != null) {
                return this.zzcfk.equals(zzzdVar.zzcfk);
            } else {
                try {
                    return Arrays.equals(toByteArray(), zzzdVar.toByteArray());
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzf()];
        zza(zzyy.zzo(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzyv */
    public final zzzd clone() {
        zzzd zzzdVar = new zzzd();
        try {
            zzzdVar.zzcfj = this.zzcfj;
            if (this.zzcfk == null) {
                zzzdVar.zzcfk = null;
            } else {
                zzzdVar.zzcfk.addAll(this.zzcfk);
            }
            if (this.value != null) {
                if (this.value instanceof zzzg) {
                    zzzdVar.value = (zzzg) ((zzzg) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzzdVar.value = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length];
                        zzzdVar.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        zzzdVar.value = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        zzzdVar.value = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        zzzdVar.value = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        zzzdVar.value = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        zzzdVar.value = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzzg[]) {
                        zzzg[] zzzgVarArr = (zzzg[]) this.value;
                        zzzg[] zzzgVarArr2 = new zzzg[zzzgVarArr.length];
                        zzzdVar.value = zzzgVarArr2;
                        while (i < zzzgVarArr.length) {
                            zzzgVarArr2[i] = (zzzg) zzzgVarArr[i].clone();
                            i++;
                        }
                    }
                }
            }
            return zzzdVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
