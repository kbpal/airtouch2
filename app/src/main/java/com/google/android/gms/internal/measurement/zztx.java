package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zztw;
import com.google.android.gms.internal.measurement.zztx;

/* loaded from: classes.dex */
public abstract class zztx<MessageType extends zztw<MessageType, BuilderType>, BuilderType extends zztx<MessageType, BuilderType>> implements zzwu {
    protected abstract BuilderType zza(MessageType messagetype);

    @Override // 
    /* renamed from: zztv */
    public abstract BuilderType clone();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzwu
    public final /* synthetic */ zzwu zza(zzwt zzwtVar) {
        if (!zzwf().getClass().isInstance(zzwtVar)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return zza((zztx<MessageType, BuilderType>) ((zztw) zzwtVar));
    }
}
