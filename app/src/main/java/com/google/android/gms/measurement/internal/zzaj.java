package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes.dex */
public final class zzaj extends zzf {
    private String zzafx;
    private String zzage;
    private long zzagh;
    private String zzagk;
    private int zzagy;
    private int zzalo;
    private long zzalp;
    private String zztr;
    private String zzts;
    private String zztt;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaj(zzbt zzbtVar) {
        super(zzbtVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzgt() {
        return true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:1|(1:3)(7:64|65|66|(1:68)(2:84|(1:86))|69|70|(6:72|(1:74)|75|77|78|79))|4|(1:63)(1:8)|9|(1:62)(1:13)|14|(1:(1:17)(1:18))|(2:20|(2:22|(1:24))(1:(1:(11:35|36|(1:40)|41|42|(1:44)(1:58)|45|(1:47)|(1:49)|51|(2:53|54)(2:56|57))(1:34))(2:28|(1:30))))|61|36|(2:38|40)|41|42|(0)(0)|45|(0)|(0)|51|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01d0, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01d1, code lost:
        zzgo().zzjd().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", com.google.android.gms.measurement.internal.zzap.zzbv(r3), r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01ab A[Catch: IllegalStateException -> 0x01d0, TryCatch #3 {IllegalStateException -> 0x01d0, blocks: (B:63:0x0195, B:67:0x01a3, B:69:0x01ab, B:71:0x01be), top: B:88:0x0195 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01be A[Catch: IllegalStateException -> 0x01d0, TRY_LEAVE, TryCatch #3 {IllegalStateException -> 0x01d0, blocks: (B:63:0x0195, B:67:0x01a3, B:69:0x01ab, B:71:0x01be), top: B:88:0x0195 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f3  */
    @Override // com.google.android.gms.measurement.internal.zzf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void zzgu() {
        /*
            Method dump skipped, instructions count: 502
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaj.zzgu():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final zzh zzbr(String str) {
        zzaf();
        zzgb();
        String zzal = zzal();
        String gmpAppId = getGmpAppId();
        zzcl();
        String str2 = this.zzts;
        long zzja = zzja();
        zzcl();
        String str3 = this.zzage;
        long zzhc = zzgq().zzhc();
        zzcl();
        zzaf();
        if (this.zzalp == 0) {
            this.zzalp = this.zzadj.zzgm().zzd(getContext(), getContext().getPackageName());
        }
        long j = this.zzalp;
        boolean isEnabled = this.zzadj.isEnabled();
        boolean z = !zzgp().zzanv;
        zzaf();
        zzgb();
        String zziz = (!zzgq().zzbc(this.zztt) || this.zzadj.isEnabled()) ? zziz() : null;
        zzcl();
        long j2 = this.zzagh;
        long zzkp = this.zzadj.zzkp();
        int zzjb = zzjb();
        zzn zzgq = zzgq();
        zzgq.zzgb();
        Boolean zzau = zzgq.zzau("google_analytics_adid_collection_enabled");
        boolean booleanValue = Boolean.valueOf(zzau == null || zzau.booleanValue()).booleanValue();
        zzn zzgq2 = zzgq();
        zzgq2.zzgb();
        Boolean zzau2 = zzgq2.zzau("google_analytics_ssaid_collection_enabled");
        return new zzh(zzal, gmpAppId, str2, zzja, str3, zzhc, j, str, isEnabled, z, zziz, j2, zzkp, zzjb, booleanValue, Boolean.valueOf(zzau2 == null || zzau2.booleanValue()).booleanValue(), zzgp().zzjx(), zzgw());
    }

    @WorkerThread
    @VisibleForTesting
    private final String zziz() {
        try {
            Class<?> loadClass = getContext().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if (loadClass == null) {
                return null;
            }
            try {
                Object invoke = loadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, getContext());
                if (invoke == null) {
                    return null;
                }
                try {
                    return (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                } catch (Exception unused) {
                    zzgo().zzji().zzbx("Failed to retrieve Firebase Instance Id");
                    return null;
                }
            } catch (Exception unused2) {
                zzgo().zzjh().zzbx("Failed to obtain Firebase Analytics instance");
                return null;
            }
        } catch (ClassNotFoundException unused3) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzal() {
        zzcl();
        return this.zztt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getGmpAppId() {
        zzcl();
        return this.zzafx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzgw() {
        zzcl();
        return this.zzagk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzja() {
        zzcl();
        return this.zzalo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzjb() {
        zzcl();
        return this.zzagy;
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzga() {
        super.zzga();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzgb() {
        super.zzgb();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzgc() {
        super.zzgc();
    }

    @Override // com.google.android.gms.measurement.internal.zze, com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zza zzgd() {
        return super.zzgd();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzcs zzge() {
        return super.zzge();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzaj zzgf() {
        return super.zzgf();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzdr zzgg() {
        return super.zzgg();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzdo zzgh() {
        return super.zzgh();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzal zzgi() {
        return super.zzgi();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    public final /* bridge */ /* synthetic */ zzeq zzgj() {
        return super.zzgj();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzx zzgk() {
        return super.zzgk();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzan zzgl() {
        return super.zzgl();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzfk zzgm() {
        return super.zzgm();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzbo zzgn() {
        return super.zzgn();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzap zzgo() {
        return super.zzgo();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzba zzgp() {
        return super.zzgp();
    }

    @Override // com.google.android.gms.measurement.internal.zzco
    public final /* bridge */ /* synthetic */ zzn zzgq() {
        return super.zzgq();
    }

    @Override // com.google.android.gms.measurement.internal.zzco, com.google.android.gms.measurement.internal.zzcq
    public final /* bridge */ /* synthetic */ zzk zzgr() {
        return super.zzgr();
    }
}
