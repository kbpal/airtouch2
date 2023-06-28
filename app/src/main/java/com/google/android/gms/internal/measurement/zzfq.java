package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;

/* loaded from: classes.dex */
public final class zzfq {

    /* loaded from: classes.dex */
    public static final class zza extends zzvm<zza, C0504zza> implements zzwv {
        private static final zza zzauq = new zza();
        private static volatile zzxd<zza> zznw;
        private String zzauo = "";
        private long zzaup;
        private int zznr;

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.measurement.zzfq$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0504zza extends zzvm.zza<zza, C0504zza> implements zzwv {
            private C0504zza() {
                super(zza.zzauq);
            }

            /* synthetic */ C0504zza(zzfr zzfrVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzvm
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfr.zznq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0504zza(null);
                case 3:
                    return zza(zzauq, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001", new Object[]{"zznr", "zzauo", "zzaup"});
                case 4:
                    return zzauq;
                case 5:
                    zzxd<zza> zzxdVar = zznw;
                    if (zzxdVar == null) {
                        synchronized (zza.class) {
                            zzxdVar = zznw;
                            if (zzxdVar == null) {
                                zzxdVar = new zzvm.zzb<>(zzauq);
                                zznw = zzxdVar;
                            }
                        }
                    }
                    return zzxdVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzvm.zza(zza.class, zzauq);
        }
    }

    /* loaded from: classes.dex */
    public static final class zzb extends zzvm<zzb, zza> implements zzwv {
        private static final zzb zzaut = new zzb();
        private static volatile zzxd<zzb> zznw;
        private int zzaur = 1;
        private zzvs<zza> zzaus = zzwc();
        private int zznr;

        /* renamed from: com.google.android.gms.internal.measurement.zzfq$zzb$zzb  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public enum EnumC0505zzb implements zzvp {
            RADS(1),
            PROVISIONING(2);
            
            private static final zzvq<EnumC0505zzb> zzoa = new zzfs();
            private final int value;

            @Override // com.google.android.gms.internal.measurement.zzvp
            public final int zzc() {
                return this.value;
            }

            public static EnumC0505zzb zzs(int i) {
                switch (i) {
                    case 1:
                        return RADS;
                    case 2:
                        return PROVISIONING;
                    default:
                        return null;
                }
            }

            public static zzvr zzd() {
                return zzft.zzoc;
            }

            EnumC0505zzb(int i) {
                this.value = i;
            }
        }

        private zzb() {
        }

        /* loaded from: classes.dex */
        public static final class zza extends zzvm.zza<zzb, zza> implements zzwv {
            private zza() {
                super(zzb.zzaut);
            }

            /* synthetic */ zza(zzfr zzfrVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzvm
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfr.zznq[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzaut, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u001b", new Object[]{"zznr", "zzaur", EnumC0505zzb.zzd(), "zzaus", zza.class});
                case 4:
                    return zzaut;
                case 5:
                    zzxd<zzb> zzxdVar = zznw;
                    if (zzxdVar == null) {
                        synchronized (zzb.class) {
                            zzxdVar = zznw;
                            if (zzxdVar == null) {
                                zzxdVar = new zzvm.zzb<>(zzaut);
                                zznw = zzxdVar;
                            }
                        }
                    }
                    return zzxdVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzxd<zzb> zza() {
            return (zzxd) zzaut.zza(zzvm.zze.zzbyz, (Object) null, (Object) null);
        }

        static {
            zzvm.zza(zzb.class, zzaut);
        }
    }
}
