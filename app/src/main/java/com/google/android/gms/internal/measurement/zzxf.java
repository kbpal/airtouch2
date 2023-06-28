package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzxf {
    private static final zzxf zzcbs = new zzxf();
    private final zzxk zzcbt;
    private final ConcurrentMap<Class<?>, zzxj<?>> zzcbu = new ConcurrentHashMap();

    public static zzxf zzxn() {
        return zzcbs;
    }

    public final <T> zzxj<T> zzi(Class<T> cls) {
        zzvo.zza(cls, "messageType");
        zzxj<T> zzxjVar = (zzxj<T>) this.zzcbu.get(cls);
        if (zzxjVar == null) {
            zzxj<T> zzh = this.zzcbt.zzh(cls);
            zzvo.zza(cls, "messageType");
            zzvo.zza(zzh, "schema");
            zzxj<T> zzxjVar2 = (zzxj<T>) this.zzcbu.putIfAbsent(cls, zzh);
            return zzxjVar2 != null ? zzxjVar2 : zzh;
        }
        return zzxjVar;
    }

    public final <T> zzxj<T> zzag(T t) {
        return zzi(t.getClass());
    }

    private zzxf() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzxk zzxkVar = null;
        for (int i = 0; i <= 0; i++) {
            zzxkVar = zzgb(strArr[0]);
            if (zzxkVar != null) {
                break;
            }
        }
        this.zzcbt = zzxkVar == null ? new zzwi() : zzxkVar;
    }

    private static zzxk zzgb(String str) {
        try {
            return (zzxk) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }
}
