package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
final class zzvb extends zzva<Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final boolean zze(zzwt zzwtVar) {
        return zzwtVar instanceof zzvm.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final zzvd<Object> zzs(Object obj) {
        return ((zzvm.zzc) obj).zzbys;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final void zza(Object obj, zzvd<Object> zzvdVar) {
        ((zzvm.zzc) obj).zzbys = zzvdVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final zzvd<Object> zzt(Object obj) {
        zzvd<Object> zzs = zzs(obj);
        if (zzs.isImmutable()) {
            zzvd<Object> zzvdVar = (zzvd) zzs.clone();
            zza(obj, zzvdVar);
            return zzvdVar;
        }
        return zzs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final void zzu(Object obj) {
        zzs(obj).zzsm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final <UT, UB> UB zza(zzxi zzxiVar, Object obj, zzuz zzuzVar, zzvd<Object> zzvdVar, UB ub, zzyb<UT, UB> zzybVar) throws IOException {
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final int zzb(Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final void zza(zzyw zzywVar, Map.Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final Object zza(zzuz zzuzVar, zzwt zzwtVar, int i) {
        return zzuzVar.zza(zzwtVar, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final void zza(zzxi zzxiVar, Object obj, zzuz zzuzVar, zzvd<Object> zzvdVar) throws IOException {
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzva
    public final void zza(zzud zzudVar, Object obj, zzuz zzuzVar, zzvd<Object> zzvdVar) throws IOException {
        throw new NoSuchMethodError();
    }
}
