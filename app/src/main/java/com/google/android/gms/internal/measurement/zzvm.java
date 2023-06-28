package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;
import com.google.android.gms.internal.measurement.zzvm.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public abstract class zzvm<MessageType extends zzvm<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zztw<MessageType, BuilderType> {
    private static Map<Object, zzvm<?, ?>> zzbyo = new ConcurrentHashMap();
    protected zzyc zzbym = zzyc.zzyf();
    private int zzbyn = -1;

    /* loaded from: classes.dex */
    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzvm<MessageType, BuilderType> implements zzwv {
        protected zzvd<Object> zzbys = zzvd.zzvt();
    }

    /* loaded from: classes.dex */
    public static class zzd<ContainingType extends zzwt, Type> extends zzux<ContainingType, Type> {
    }

    /* loaded from: classes.dex */
    public enum zze {
        public static final int zzbyt = 1;
        public static final int zzbyu = 2;
        public static final int zzbyv = 3;
        public static final int zzbyw = 4;
        public static final int zzbyx = 5;
        public static final int zzbyy = 6;
        public static final int zzbyz = 7;
        private static final /* synthetic */ int[] zzbza = {zzbyt, zzbyu, zzbyv, zzbyw, zzbyx, zzbyy, zzbyz};
        public static final int zzbzb = 1;
        public static final int zzbzc = 2;
        private static final /* synthetic */ int[] zzbzd = {zzbzb, zzbzc};
        public static final int zzbze = 1;
        public static final int zzbzf = 2;
        private static final /* synthetic */ int[] zzbzg = {zzbze, zzbzf};

        /* renamed from: values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0 */
        public static int[] m10x126d66cb() {
            return (int[]) zzbza.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    /* loaded from: classes.dex */
    public static class zzb<T extends zzvm<T, ?>> extends zzty<T> {
        private final T zzbyp;

        public zzb(T t) {
            this.zzbyp = t;
        }

        @Override // com.google.android.gms.internal.measurement.zzxd
        public final /* synthetic */ Object zza(zzuo zzuoVar, zzuz zzuzVar) throws zzvt {
            return zzvm.zza(this.zzbyp, zzuoVar, zzuzVar);
        }
    }

    public String toString() {
        return zzww.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzbtr != 0) {
            return this.zzbtr;
        }
        this.zzbtr = zzxf.zzxn().zzag(this).hashCode(this);
        return this.zzbtr;
    }

    /* loaded from: classes.dex */
    public static abstract class zza<MessageType extends zzvm<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zztx<MessageType, BuilderType> {
        private final MessageType zzbyp;
        private MessageType zzbyq;
        private boolean zzbyr = false;

        /* JADX INFO: Access modifiers changed from: protected */
        public zza(MessageType messagetype) {
            this.zzbyp = messagetype;
            this.zzbyq = (MessageType) messagetype.zza(zze.zzbyw, null, null);
        }

        @Override // com.google.android.gms.internal.measurement.zzwv
        public final boolean isInitialized() {
            return zzvm.zza(this.zzbyq, false);
        }

        @Override // com.google.android.gms.internal.measurement.zzwu
        /* renamed from: zzwg */
        public MessageType zzwi() {
            if (this.zzbyr) {
                return this.zzbyq;
            }
            MessageType messagetype = this.zzbyq;
            zzxf.zzxn().zzag(messagetype).zzu(messagetype);
            this.zzbyr = true;
            return this.zzbyq;
        }

        @Override // com.google.android.gms.internal.measurement.zzwu
        /* renamed from: zzwh */
        public final MessageType zzwj() {
            MessageType messagetype = (MessageType) zzwi();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype.zza(zze.zzbyt, null, null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzxf.zzxn().zzag(messagetype).zzaf(messagetype);
                    if (booleanValue) {
                        messagetype.zza(zze.zzbyu, z ? messagetype : null, null);
                    }
                }
            }
            if (z) {
                return messagetype;
            }
            throw new zzya(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzbyr) {
                MessageType messagetype2 = (MessageType) this.zzbyq.zza(zze.zzbyw, null, null);
                zza(messagetype2, this.zzbyq);
                this.zzbyq = messagetype2;
                this.zzbyr = false;
            }
            zza(this.zzbyq, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzxf.zzxn().zzag(messagetype).zzd(messagetype, messagetype2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.measurement.zztx
        protected final /* synthetic */ zztx zza(zztw zztwVar) {
            return zza((zza<MessageType, BuilderType>) ((zzvm) zztwVar));
        }

        @Override // com.google.android.gms.internal.measurement.zztx
        public final /* synthetic */ zztx zztv() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.measurement.zzwv
        public final /* synthetic */ zzwt zzwf() {
            return this.zzbyp;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.measurement.zztx
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzbyp.zza(zze.zzbyx, null, null);
            zzaVar.zza((zza) ((zzvm) zzwi()));
            return zzaVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzvm) zza(zze.zzbyy, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return zzxf.zzxn().zzag(this).equals(this, (zzvm) obj);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzwv
    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zze.zzbyt, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzaf = zzxf.zzxn().zzag(this).zzaf(this);
        if (booleanValue) {
            zza(zze.zzbyu, zzaf ? this : null, (Object) null);
        }
        return zzaf;
    }

    @Override // com.google.android.gms.internal.measurement.zztw
    final int zztu() {
        return this.zzbyn;
    }

    @Override // com.google.android.gms.internal.measurement.zztw
    final void zzah(int i) {
        this.zzbyn = i;
    }

    @Override // com.google.android.gms.internal.measurement.zzwt
    public final void zzb(zzut zzutVar) throws IOException {
        zzxf.zzxn().zzi(getClass()).zza(this, zzuv.zza(zzutVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzwt
    public final int zzvu() {
        if (this.zzbyn == -1) {
            this.zzbyn = zzxf.zzxn().zzag(this).zzae(this);
        }
        return this.zzbyn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends zzvm<?, ?>> T zzg(Class<T> cls) {
        T t = (T) zzbyo.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (T) zzbyo.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? "Unable to get default instance for: ".concat(valueOf) : new String("Unable to get default instance for: "));
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends zzvm<?, ?>> void zza(Class<T> cls, T t) {
        zzbyo.put(cls, t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zza(zzwt zzwtVar, String str, Object[] objArr) {
        return new zzxh(zzwtVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static final <T extends zzvm<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzbyt, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return zzxf.zzxn().zzag(t).zzaf(t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <E> zzvs<E> zzwc() {
        return zzxg.zzxo();
    }

    static <T extends zzvm<T, ?>> T zza(T t, zzuo zzuoVar, zzuz zzuzVar) throws zzvt {
        T t2 = (T) t.zza(zze.zzbyw, null, null);
        try {
            zzxf.zzxn().zzag(t2).zza(t2, zzur.zza(zzuoVar), zzuzVar);
            zzxf.zzxn().zzag(t2).zzu(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzvt) {
                throw ((zzvt) e.getCause());
            }
            throw new zzvt(e.getMessage()).zzg(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzvt) {
                throw ((zzvt) e2.getCause());
            }
            throw e2;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzwt
    public final /* synthetic */ zzwu zzwd() {
        zza zzaVar = (zza) zza(zze.zzbyx, (Object) null, (Object) null);
        zzaVar.zza((zza) this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzwt
    public final /* synthetic */ zzwu zzwe() {
        return (zza) zza(zze.zzbyx, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzwv
    public final /* synthetic */ zzwt zzwf() {
        return (zzvm) zza(zze.zzbyy, (Object) null, (Object) null);
    }
}
