package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzvm;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zzuv implements zzyw {
    private final zzut zzbuf;

    public static zzuv zza(zzut zzutVar) {
        if (zzutVar.zzbuw != null) {
            return zzutVar.zzbuw;
        }
        return new zzuv(zzutVar);
    }

    private zzuv(zzut zzutVar) {
        this.zzbuf = (zzut) zzvo.zza(zzutVar, "output");
        this.zzbuf.zzbuw = this;
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final int zzvj() {
        return zzvm.zze.zzbze;
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzn(int i, int i2) throws IOException {
        this.zzbuf.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzi(int i, long j) throws IOException {
        this.zzbuf.zza(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzj(int i, long j) throws IOException {
        this.zzbuf.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, float f) throws IOException {
        this.zzbuf.zza(i, f);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, double d) throws IOException {
        this.zzbuf.zza(i, d);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzo(int i, int i2) throws IOException {
        this.zzbuf.zzd(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, long j) throws IOException {
        this.zzbuf.zza(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzd(int i, int i2) throws IOException {
        this.zzbuf.zzd(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzc(int i, long j) throws IOException {
        this.zzbuf.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzg(int i, int i2) throws IOException {
        this.zzbuf.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzb(int i, boolean z) throws IOException {
        this.zzbuf.zzb(i, z);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzb(int i, String str) throws IOException {
        this.zzbuf.zzb(i, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, zzud zzudVar) throws IOException {
        this.zzbuf.zza(i, zzudVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zze(int i, int i2) throws IOException {
        this.zzbuf.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzf(int i, int i2) throws IOException {
        this.zzbuf.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzb(int i, long j) throws IOException {
        this.zzbuf.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, Object obj, zzxj zzxjVar) throws IOException {
        this.zzbuf.zza(i, (zzwt) obj, zzxjVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzb(int i, Object obj, zzxj zzxjVar) throws IOException {
        zzut zzutVar = this.zzbuf;
        zzutVar.zzc(i, 3);
        zzxjVar.zza((zzwt) obj, zzutVar.zzbuw);
        zzutVar.zzc(i, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzbk(int i) throws IOException {
        this.zzbuf.zzc(i, 3);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzbl(int i) throws IOException {
        this.zzbuf.zzc(i, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzud) {
            this.zzbuf.zzb(i, (zzud) obj);
        } else {
            this.zzbuf.zzb(i, (zzwt) obj);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzbc(list.get(i4).intValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzax(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzbf(list.get(i4).intValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzba(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzay(list.get(i4).longValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzav(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzaz(list.get(i4).longValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzav(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzbb(list.get(i4).longValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzax(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzb(list.get(i4).floatValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zza(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzc(list.get(i4).doubleValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzb(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzbh(list.get(i4).intValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzax(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzv(list.get(i4).booleanValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzu(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzb(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzwc) {
            zzwc zzwcVar = (zzwc) list;
            while (i2 < list.size()) {
                Object raw = zzwcVar.getRaw(i2);
                if (raw instanceof String) {
                    this.zzbuf.zzb(i, (String) raw);
                } else {
                    this.zzbuf.zza(i, (zzud) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzb(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzb(int i, List<zzud> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzbuf.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzbd(list.get(i4).intValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzay(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzbg(list.get(i4).intValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzba(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzbc(list.get(i4).longValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzax(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzbe(list.get(i4).intValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzaz(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzbuf.zzc(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzut.zzba(list.get(i4).longValue());
            }
            this.zzbuf.zzay(i3);
            while (i2 < list.size()) {
                this.zzbuf.zzaw(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbuf.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zza(int i, List<?> list, zzxj zzxjVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzxjVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final void zzb(int i, List<?> list, zzxj zzxjVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzxjVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzyw
    public final <K, V> void zza(int i, zzwm<K, V> zzwmVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zzbuf.zzc(i, 2);
            this.zzbuf.zzay(zzwl.zza(zzwmVar, entry.getKey(), entry.getValue()));
            zzwl.zza(this.zzbuf, zzwmVar, entry.getKey(), entry.getValue());
        }
    }
}
