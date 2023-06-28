package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* loaded from: classes.dex */
public final class zzyy {
    private final ByteBuffer zzbva;
    private zzut zzcfa;
    private int zzcfb;

    private zzyy(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzbi(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int zzbj(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    private zzyy(ByteBuffer byteBuffer) {
        this.zzbva = byteBuffer;
        this.zzbva.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static zzyy zzo(byte[] bArr) {
        return zzk(bArr, 0, bArr.length);
    }

    public static zzyy zzk(byte[] bArr, int i, int i2) {
        return new zzyy(bArr, 0, i2);
    }

    private final zzut zzys() throws IOException {
        if (this.zzcfa == null) {
            this.zzcfa = zzut.zza(this.zzbva);
            this.zzcfb = this.zzbva.position();
        } else if (this.zzcfb != this.zzbva.position()) {
            this.zzcfa.write(this.zzbva.array(), this.zzcfb, this.zzbva.position() - this.zzcfb);
            this.zzcfb = this.zzbva.position();
        }
        return this.zzcfa;
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, 1);
        long doubleToLongBits = Double.doubleToLongBits(d);
        if (this.zzbva.remaining() < 8) {
            throw new zzyz(this.zzbva.position(), this.zzbva.limit());
        }
        this.zzbva.putLong(doubleToLongBits);
    }

    public final void zza(int i, float f) throws IOException {
        zzc(i, 5);
        int floatToIntBits = Float.floatToIntBits(f);
        if (this.zzbva.remaining() < 4) {
            throw new zzyz(this.zzbva.position(), this.zzbva.limit());
        }
        this.zzbva.putInt(floatToIntBits);
    }

    public final void zza(int i, long j) throws IOException {
        zzc(i, 0);
        zzbh(j);
    }

    public final void zzi(int i, long j) throws IOException {
        zzc(i, 0);
        zzbh(j);
    }

    public final void zzd(int i, int i2) throws IOException {
        zzc(i, 0);
        if (i2 >= 0) {
            zzca(i2);
        } else {
            zzbh(i2);
        }
    }

    public final void zzb(int i, boolean z) throws IOException {
        zzc(i, 0);
        byte b = z ? (byte) 1 : (byte) 0;
        if (!this.zzbva.hasRemaining()) {
            throw new zzyz(this.zzbva.position(), this.zzbva.limit());
        }
        this.zzbva.put(b);
    }

    public final void zzb(int i, String str) throws IOException {
        zzc(i, 2);
        try {
            int zzbj = zzbj(str.length());
            if (zzbj == zzbj(str.length() * 3)) {
                int position = this.zzbva.position();
                if (this.zzbva.remaining() < zzbj) {
                    throw new zzyz(position + zzbj, this.zzbva.limit());
                }
                this.zzbva.position(position + zzbj);
                zzd(str, this.zzbva);
                int position2 = this.zzbva.position();
                this.zzbva.position(position);
                zzca((position2 - position) - zzbj);
                this.zzbva.position(position2);
                return;
            }
            zzca(zza(str));
            zzd(str, this.zzbva);
        } catch (BufferOverflowException e) {
            zzyz zzyzVar = new zzyz(this.zzbva.position(), this.zzbva.limit());
            zzyzVar.initCause(e);
            throw zzyzVar;
        }
    }

    public final void zza(int i, zzzg zzzgVar) throws IOException {
        zzc(i, 2);
        zzb(zzzgVar);
    }

    public final void zze(int i, zzwt zzwtVar) throws IOException {
        zzut zzys = zzys();
        zzys.zza(i, zzwtVar);
        zzys.flush();
        this.zzcfb = this.zzbva.position();
    }

    private static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i2);
                                throw new IllegalArgumentException(sb.toString());
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(i3 + 4294967296L);
        throw new IllegalArgumentException(sb2.toString());
    }

    private static void zzd(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        char charAt;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        int i3 = 0;
        if (byteBuffer.hasArray()) {
            try {
                byte[] array = byteBuffer.array();
                int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                int remaining = byteBuffer.remaining();
                int length = charSequence.length();
                int i4 = remaining + arrayOffset;
                while (i3 < length) {
                    int i5 = i3 + arrayOffset;
                    if (i5 >= i4 || (charAt = charSequence.charAt(i3)) >= 128) {
                        break;
                    }
                    array[i5] = (byte) charAt;
                    i3++;
                }
                if (i3 == length) {
                    i = arrayOffset + length;
                } else {
                    i = arrayOffset + i3;
                    while (i3 < length) {
                        char charAt2 = charSequence.charAt(i3);
                        if (charAt2 >= 128 || i >= i4) {
                            if (charAt2 < 2048 && i <= i4 - 2) {
                                int i6 = i + 1;
                                array[i] = (byte) ((charAt2 >>> 6) | 960);
                                i = i6 + 1;
                                array[i6] = (byte) ((charAt2 & '?') | 128);
                            } else if ((charAt2 >= 55296 && 57343 >= charAt2) || i > i4 - 3) {
                                if (i <= i4 - 4) {
                                    int i7 = i3 + 1;
                                    if (i7 != charSequence.length()) {
                                        char charAt3 = charSequence.charAt(i7);
                                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                                            int i8 = i + 1;
                                            array[i] = (byte) ((codePoint >>> 18) | 240);
                                            int i9 = i8 + 1;
                                            array[i8] = (byte) (((codePoint >>> 12) & 63) | 128);
                                            int i10 = i9 + 1;
                                            array[i9] = (byte) (((codePoint >>> 6) & 63) | 128);
                                            i = i10 + 1;
                                            array[i10] = (byte) ((codePoint & 63) | 128);
                                            i3 = i7;
                                        } else {
                                            i3 = i7;
                                        }
                                    }
                                    StringBuilder sb = new StringBuilder(39);
                                    sb.append("Unpaired surrogate at index ");
                                    sb.append(i3 - 1);
                                    throw new IllegalArgumentException(sb.toString());
                                }
                                StringBuilder sb2 = new StringBuilder(37);
                                sb2.append("Failed writing ");
                                sb2.append(charAt2);
                                sb2.append(" at index ");
                                sb2.append(i);
                                throw new ArrayIndexOutOfBoundsException(sb2.toString());
                            } else {
                                int i11 = i + 1;
                                array[i] = (byte) ((charAt2 >>> '\f') | 480);
                                int i12 = i11 + 1;
                                array[i11] = (byte) (((charAt2 >>> 6) & 63) | 128);
                                i2 = i12 + 1;
                                array[i12] = (byte) ((charAt2 & '?') | 128);
                            }
                            i3++;
                        } else {
                            i2 = i + 1;
                            array[i] = (byte) charAt2;
                        }
                        i = i2;
                        i3++;
                    }
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        }
        int length2 = charSequence.length();
        while (i3 < length2) {
            char charAt4 = charSequence.charAt(i3);
            if (charAt4 < 128) {
                byteBuffer.put((byte) charAt4);
            } else if (charAt4 < 2048) {
                byteBuffer.put((byte) ((charAt4 >>> 6) | 960));
                byteBuffer.put((byte) ((charAt4 & '?') | 128));
            } else if (charAt4 < 55296 || 57343 < charAt4) {
                byteBuffer.put((byte) ((charAt4 >>> '\f') | 480));
                byteBuffer.put((byte) (((charAt4 >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt4 & '?') | 128));
            } else {
                int i13 = i3 + 1;
                if (i13 != charSequence.length()) {
                    char charAt5 = charSequence.charAt(i13);
                    if (Character.isSurrogatePair(charAt4, charAt5)) {
                        int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                        byteBuffer.put((byte) ((codePoint2 >>> 18) | 240));
                        byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                        i3 = i13;
                    } else {
                        i3 = i13;
                    }
                }
                StringBuilder sb3 = new StringBuilder(39);
                sb3.append("Unpaired surrogate at index ");
                sb3.append(i3 - 1);
                throw new IllegalArgumentException(sb3.toString());
            }
            i3++;
        }
    }

    public final void zzb(zzzg zzzgVar) throws IOException {
        zzca(zzzgVar.zzza());
        zzzgVar.zza(this);
    }

    public static int zzd(int i, long j) {
        return zzbb(i) + zzbi(j);
    }

    public static int zzh(int i, int i2) {
        return zzbb(i) + zzbc(i2);
    }

    public static int zzc(int i, String str) {
        return zzbb(i) + zzfx(str);
    }

    public static int zzb(int i, zzzg zzzgVar) {
        int zzbb = zzbb(i);
        int zzvu = zzzgVar.zzvu();
        return zzbb + zzbj(zzvu) + zzvu;
    }

    public static int zzbc(int i) {
        if (i >= 0) {
            return zzbj(i);
        }
        return 10;
    }

    public static int zzfx(String str) {
        int zza = zza(str);
        return zzbj(zza) + zza;
    }

    public final void zzyt() {
        if (this.zzbva.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.zzbva.remaining())));
        }
    }

    private final void zzbz(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zzbva.hasRemaining()) {
            throw new zzyz(this.zzbva.position(), this.zzbva.limit());
        }
        this.zzbva.put(b);
    }

    public final void zzp(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzbva.remaining() >= length) {
            this.zzbva.put(bArr, 0, length);
            return;
        }
        throw new zzyz(this.zzbva.position(), this.zzbva.limit());
    }

    public final void zzc(int i, int i2) throws IOException {
        zzca((i << 3) | i2);
    }

    public static int zzbb(int i) {
        return zzbj(i << 3);
    }

    public final void zzca(int i) throws IOException {
        while ((i & (-128)) != 0) {
            zzbz((i & 127) | 128);
            i >>>= 7;
        }
        zzbz(i);
    }

    private final void zzbh(long j) throws IOException {
        while (((-128) & j) != 0) {
            zzbz((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzbz((int) j);
    }
}
