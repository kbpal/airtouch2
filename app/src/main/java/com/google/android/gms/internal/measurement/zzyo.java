package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
final class zzyo extends zzyl {
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00bb, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.measurement.zzyl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzb(int r18, byte[] r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzyo.zzb(int, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzyl
    public final String zzh(byte[] bArr, int i, int i2) throws zzvt {
        boolean zzd;
        boolean zzd2;
        boolean zze;
        boolean zzf;
        boolean zzd3;
        if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte zza = zzyh.zza(bArr, i);
            zzd3 = zzyk.zzd(zza);
            if (!zzd3) {
                break;
            }
            i++;
            zzyk.zza(zza, cArr, i4);
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte zza2 = zzyh.zza(bArr, i);
            zzd = zzyk.zzd(zza2);
            if (zzd) {
                int i7 = i5 + 1;
                zzyk.zza(zza2, cArr, i5);
                while (i6 < i3) {
                    byte zza3 = zzyh.zza(bArr, i6);
                    zzd2 = zzyk.zzd(zza3);
                    if (!zzd2) {
                        break;
                    }
                    i6++;
                    zzyk.zza(zza3, cArr, i7);
                    i7++;
                }
                i = i6;
                i5 = i7;
            } else {
                zze = zzyk.zze(zza2);
                if (!zze) {
                    zzf = zzyk.zzf(zza2);
                    if (zzf) {
                        if (i6 < i3 - 1) {
                            int i8 = i6 + 1;
                            zzyk.zza(zza2, zzyh.zza(bArr, i6), zzyh.zza(bArr, i8), cArr, i5);
                            i = i8 + 1;
                            i5++;
                        } else {
                            throw zzvt.zzwr();
                        }
                    } else if (i6 < i3 - 2) {
                        int i9 = i6 + 1;
                        int i10 = i9 + 1;
                        zzyk.zza(zza2, zzyh.zza(bArr, i6), zzyh.zza(bArr, i9), zzyh.zza(bArr, i10), cArr, i5);
                        i = i10 + 1;
                        i5 = i5 + 1 + 1;
                    } else {
                        throw zzvt.zzwr();
                    }
                } else if (i6 < i3) {
                    zzyk.zza(zza2, zzyh.zza(bArr, i6), cArr, i5);
                    i = i6 + 1;
                    i5++;
                } else {
                    throw zzvt.zzwr();
                }
            }
        }
        return new String(cArr, 0, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzyl
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j;
        int i3;
        char charAt;
        long j2 = i;
        long j3 = i2 + j2;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char charAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (i4 < length && (charAt = charSequence.charAt(i4)) < 128) {
            zzyh.zza(bArr, j2, (byte) charAt);
            i4++;
            j2 = 1 + j2;
        }
        if (i4 == length) {
            return (int) j2;
        }
        while (i4 < length) {
            char charAt3 = charSequence.charAt(i4);
            if (charAt3 >= 128 || j2 >= j3) {
                if (charAt3 < 2048 && j2 <= j3 - 2) {
                    long j4 = j2 + 1;
                    zzyh.zza(bArr, j2, (byte) ((charAt3 >>> 6) | 960));
                    j2 = j4 + 1;
                    zzyh.zza(bArr, j4, (byte) ((charAt3 & '?') | 128));
                } else if ((charAt3 >= 55296 && 57343 >= charAt3) || j2 > j3 - 3) {
                    if (j2 <= j3 - 4) {
                        int i5 = i4 + 1;
                        if (i5 != length) {
                            char charAt4 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(charAt3, charAt4)) {
                                int codePoint = Character.toCodePoint(charAt3, charAt4);
                                long j5 = j2 + 1;
                                zzyh.zza(bArr, j2, (byte) ((codePoint >>> 18) | 240));
                                long j6 = j5 + 1;
                                zzyh.zza(bArr, j5, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j7 = j6 + 1;
                                zzyh.zza(bArr, j6, (byte) (((codePoint >>> 6) & 63) | 128));
                                j2 = j7 + 1;
                                zzyh.zza(bArr, j7, (byte) ((codePoint & 63) | 128));
                                i4 = i5;
                            }
                        } else {
                            i5 = i4;
                        }
                        throw new zzyn(i5 - 1, length);
                    } else if (55296 <= charAt3 && charAt3 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(i3)))) {
                        throw new zzyn(i4, length);
                    } else {
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Failed writing ");
                        sb2.append(charAt3);
                        sb2.append(" at index ");
                        sb2.append(j2);
                        throw new ArrayIndexOutOfBoundsException(sb2.toString());
                    }
                } else {
                    long j8 = j2 + 1;
                    zzyh.zza(bArr, j2, (byte) ((charAt3 >>> '\f') | 480));
                    long j9 = j8 + 1;
                    zzyh.zza(bArr, j8, (byte) (((charAt3 >>> 6) & 63) | 128));
                    j = j9 + 1;
                    zzyh.zza(bArr, j9, (byte) ((charAt3 & '?') | 128));
                }
                i4++;
            } else {
                j = j2 + 1;
                zzyh.zza(bArr, j2, (byte) charAt3);
            }
            j2 = j;
            i4++;
        }
        return (int) j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzyl
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        char c;
        long j;
        long j2;
        long j3;
        int i;
        char charAt;
        long zzb = zzyh.zzb(byteBuffer);
        long position = byteBuffer.position() + zzb;
        long limit = byteBuffer.limit() + zzb;
        int length = charSequence.length();
        if (length > limit - position) {
            char charAt2 = charSequence.charAt(length - 1);
            int limit2 = byteBuffer.limit();
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(limit2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i2 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i2 >= length || (charAt = charSequence.charAt(i2)) >= 128) {
                break;
            }
            zzyh.zza(position, (byte) charAt);
            i2++;
            position = 1 + position;
        }
        if (i2 == length) {
            byteBuffer.position((int) (position - zzb));
            return;
        }
        while (i2 < length) {
            char charAt3 = charSequence.charAt(i2);
            if (charAt3 < c && position < limit) {
                j2 = position + j;
                zzyh.zza(position, (byte) charAt3);
            } else if (charAt3 < 2048 && position <= limit - 2) {
                long j4 = position + j;
                zzyh.zza(position, (byte) ((charAt3 >>> 6) | 960));
                zzyh.zza(j4, (byte) ((charAt3 & '?') | 128));
                j2 = j4 + j;
            } else if ((charAt3 >= 55296 && 57343 >= charAt3) || position > limit - 3) {
                if (position <= limit - 4) {
                    int i3 = i2 + 1;
                    if (i3 != length) {
                        char charAt4 = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(charAt3, charAt4)) {
                            int codePoint = Character.toCodePoint(charAt3, charAt4);
                            long j5 = position + 1;
                            zzyh.zza(position, (byte) ((codePoint >>> 18) | 240));
                            long j6 = j5 + 1;
                            zzyh.zza(j5, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j7 = j6 + 1;
                            zzyh.zza(j6, (byte) (((codePoint >>> 6) & 63) | 128));
                            j3 = 1;
                            zzyh.zza(j7, (byte) ((codePoint & 63) | 128));
                            i2 = i3;
                            j2 = j7 + 1;
                            i2++;
                            j = j3;
                            position = j2;
                            c = 128;
                        } else {
                            i2 = i3;
                        }
                    }
                    throw new zzyn(i2 - 1, length);
                } else if (55296 <= charAt3 && charAt3 <= 57343 && ((i = i2 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(i)))) {
                    throw new zzyn(i2, length);
                } else {
                    StringBuilder sb2 = new StringBuilder(46);
                    sb2.append("Failed writing ");
                    sb2.append(charAt3);
                    sb2.append(" at index ");
                    sb2.append(position);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
            } else {
                long j8 = position + j;
                zzyh.zza(position, (byte) ((charAt3 >>> '\f') | 480));
                long j9 = j8 + j;
                zzyh.zza(j8, (byte) (((charAt3 >>> 6) & 63) | 128));
                zzyh.zza(j9, (byte) ((charAt3 & '?') | 128));
                j2 = j9 + 1;
                j3 = 1;
                i2++;
                j = j3;
                position = j2;
                c = 128;
            }
            j3 = j;
            i2++;
            j = j3;
            position = j2;
            c = 128;
        }
        byteBuffer.position((int) (position - zzb));
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        int zzbw;
        int zzq;
        int zzc;
        switch (i2) {
            case 0:
                zzbw = zzyj.zzbw(i);
                return zzbw;
            case 1:
                zzq = zzyj.zzq(i, zzyh.zza(bArr, j));
                return zzq;
            case 2:
                zzc = zzyj.zzc(i, zzyh.zza(bArr, j), zzyh.zza(bArr, j + 1));
                return zzc;
            default:
                throw new AssertionError();
        }
    }
}
