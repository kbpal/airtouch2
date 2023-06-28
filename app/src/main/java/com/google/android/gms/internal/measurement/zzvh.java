package com.google.android.gms.internal.measurement;

/* loaded from: classes.dex */
final /* synthetic */ class zzvh {
    static final /* synthetic */ int[] zzbya;
    static final /* synthetic */ int[] zzbyb = new int[zzvv.values().length];

    static {
        try {
            zzbyb[zzvv.BYTE_STRING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzbyb[zzvv.MESSAGE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzbyb[zzvv.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        zzbya = new int[zzvi.values().length];
        try {
            zzbya[zzvi.MAP.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzbya[zzvi.VECTOR.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zzbya[zzvi.SCALAR.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
