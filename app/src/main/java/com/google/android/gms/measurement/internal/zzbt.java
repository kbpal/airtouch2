package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzsl;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class zzbt implements zzcq {
    private static volatile zzbt zzapl;
    private final boolean zzadv;
    private final String zzadx;
    private final long zzagx;
    private final zzk zzaiq;
    private final String zzapm;
    private final String zzapn;
    private final zzn zzapo;
    private final zzba zzapp;
    private final zzap zzapq;
    private final zzbo zzapr;
    private final zzeq zzaps;
    private final AppMeasurement zzapt;
    private final zzfk zzapu;
    private final zzan zzapv;
    private final zzdo zzapw;
    private final zzcs zzapx;
    private final zza zzapy;
    private zzal zzapz;
    private zzdr zzaqa;
    private zzx zzaqb;
    private zzaj zzaqc;
    private zzbg zzaqd;
    private Boolean zzaqe;
    private long zzaqf;
    private volatile Boolean zzaqg;
    private int zzaqh;
    private int zzaqi;
    private final Context zzri;
    private final Clock zzrz;
    private boolean zzvz = false;

    private zzbt(zzcr zzcrVar) {
        Preconditions.checkNotNull(zzcrVar);
        this.zzaiq = new zzk(zzcrVar.zzri);
        zzaf.zza(this.zzaiq);
        this.zzri = zzcrVar.zzri;
        this.zzadx = zzcrVar.zzadx;
        this.zzapm = zzcrVar.zzapm;
        this.zzapn = zzcrVar.zzapn;
        this.zzadv = zzcrVar.zzadv;
        this.zzaqg = zzcrVar.zzaqg;
        zzsl.init(this.zzri);
        this.zzrz = DefaultClock.getInstance();
        this.zzagx = this.zzrz.currentTimeMillis();
        this.zzapo = new zzn(this);
        zzba zzbaVar = new zzba(this);
        zzbaVar.zzq();
        this.zzapp = zzbaVar;
        zzap zzapVar = new zzap(this);
        zzapVar.zzq();
        this.zzapq = zzapVar;
        zzfk zzfkVar = new zzfk(this);
        zzfkVar.zzq();
        this.zzapu = zzfkVar;
        zzan zzanVar = new zzan(this);
        zzanVar.zzq();
        this.zzapv = zzanVar;
        this.zzapy = new zza(this);
        zzdo zzdoVar = new zzdo(this);
        zzdoVar.zzq();
        this.zzapw = zzdoVar;
        zzcs zzcsVar = new zzcs(this);
        zzcsVar.zzq();
        this.zzapx = zzcsVar;
        this.zzapt = new AppMeasurement(this);
        zzeq zzeqVar = new zzeq(this);
        zzeqVar.zzq();
        this.zzaps = zzeqVar;
        zzbo zzboVar = new zzbo(this);
        zzboVar.zzq();
        this.zzapr = zzboVar;
        zzk zzkVar = this.zzaiq;
        if (this.zzri.getApplicationContext() instanceof Application) {
            zzcs zzge = zzge();
            if (zzge.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzge.getContext().getApplicationContext();
                if (zzge.zzaqv == null) {
                    zzge.zzaqv = new zzdm(zzge, null);
                }
                application.unregisterActivityLifecycleCallbacks(zzge.zzaqv);
                application.registerActivityLifecycleCallbacks(zzge.zzaqv);
                zzge.zzgo().zzjl().zzbx("Registered activity lifecycle callback");
            }
        } else {
            zzgo().zzjg().zzbx("Application context is not an Application");
        }
        this.zzapr.zzc(new zzbu(this, zzcrVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzcr zzcrVar) {
        zzar zzjj;
        String concat;
        zzgn().zzaf();
        zzn.zzht();
        zzx zzxVar = new zzx(this);
        zzxVar.zzq();
        this.zzaqb = zzxVar;
        zzaj zzajVar = new zzaj(this);
        zzajVar.zzq();
        this.zzaqc = zzajVar;
        zzal zzalVar = new zzal(this);
        zzalVar.zzq();
        this.zzapz = zzalVar;
        zzdr zzdrVar = new zzdr(this);
        zzdrVar.zzq();
        this.zzaqa = zzdrVar;
        this.zzapu.zzgs();
        this.zzapp.zzgs();
        this.zzaqd = new zzbg(this);
        this.zzaqc.zzgs();
        zzgo().zzjj().zzg("App measurement is starting up, version", Long.valueOf(this.zzapo.zzhc()));
        zzk zzkVar = this.zzaiq;
        zzgo().zzjj().zzbx("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzk zzkVar2 = this.zzaiq;
        String zzal = zzajVar.zzal();
        if (TextUtils.isEmpty(this.zzadx)) {
            if (zzgm().zzcw(zzal)) {
                zzjj = zzgo().zzjj();
                concat = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzjj = zzgo().zzjj();
                String valueOf = String.valueOf(zzal);
                concat = valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
            }
            zzjj.zzbx(concat);
        }
        zzgo().zzjk().zzbx("Debug-level message logging enabled");
        if (this.zzaqh != this.zzaqi) {
            zzgo().zzjd().zze("Not all components initialized", Integer.valueOf(this.zzaqh), Integer.valueOf(this.zzaqi));
        }
        this.zzvz = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void start() {
        zzgn().zzaf();
        if (zzgp().zzane.get() == 0) {
            zzgp().zzane.set(this.zzrz.currentTimeMillis());
        }
        if (Long.valueOf(zzgp().zzanj.get()).longValue() == 0) {
            zzgo().zzjl().zzg("Persisting first open", Long.valueOf(this.zzagx));
            zzgp().zzanj.set(this.zzagx);
        }
        if (!zzkr()) {
            if (isEnabled()) {
                if (!zzgm().zzx("android.permission.INTERNET")) {
                    zzgo().zzjd().zzbx("App is missing INTERNET permission");
                }
                if (!zzgm().zzx("android.permission.ACCESS_NETWORK_STATE")) {
                    zzgo().zzjd().zzbx("App is missing ACCESS_NETWORK_STATE permission");
                }
                zzk zzkVar = this.zzaiq;
                if (!Wrappers.packageManager(this.zzri).isCallerInstantApp() && !this.zzapo.zzib()) {
                    if (!zzbj.zza(this.zzri)) {
                        zzgo().zzjd().zzbx("AppMeasurementReceiver not registered/enabled");
                    }
                    if (!zzfk.zza(this.zzri, false)) {
                        zzgo().zzjd().zzbx("AppMeasurementService not registered/enabled");
                    }
                }
                zzgo().zzjd().zzbx("Uploading is not possible. App measurement disabled");
                return;
            }
            return;
        }
        zzk zzkVar2 = this.zzaiq;
        if (!TextUtils.isEmpty(zzgf().getGmpAppId()) || !TextUtils.isEmpty(zzgf().zzgw())) {
            zzgm();
            if (zzfk.zza(zzgf().getGmpAppId(), zzgp().zzjs(), zzgf().zzgw(), zzgp().zzjt())) {
                zzgo().zzjj().zzbx("Rechecking which service to use due to a GMP App Id change");
                zzgp().zzjv();
                if (this.zzapo.zza(zzaf.zzalc)) {
                    zzgi().resetAnalyticsData();
                }
                this.zzaqa.disconnect();
                this.zzaqa.zzdj();
                zzgp().zzanj.set(this.zzagx);
                zzgp().zzanl.zzcc(null);
            }
            zzgp().zzca(zzgf().getGmpAppId());
            zzgp().zzcb(zzgf().zzgw());
            if (this.zzapo.zzbj(zzgf().zzal())) {
                this.zzaps.zzam(this.zzagx);
            }
        }
        zzge().zzcm(zzgp().zzanl.zzjz());
        zzk zzkVar3 = this.zzaiq;
        if (TextUtils.isEmpty(zzgf().getGmpAppId()) && TextUtils.isEmpty(zzgf().zzgw())) {
            return;
        }
        boolean isEnabled = isEnabled();
        if (!zzgp().zzjy() && !this.zzapo.zzhu()) {
            zzgp().zzi(!isEnabled);
        }
        if (this.zzapo.zze(zzgf().zzal(), zzaf.zzalj)) {
            zzj(false);
        }
        if (!this.zzapo.zzbd(zzgf().zzal()) || isEnabled) {
            zzge().zzkz();
        }
        zzgg().zza(new AtomicReference<>());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzj(boolean r13) {
        /*
            r12 = this;
            com.google.android.gms.measurement.internal.zzbo r0 = r12.zzgn()
            r0.zzaf()
            com.google.android.gms.measurement.internal.zzba r0 = r12.zzgp()
            com.google.android.gms.measurement.internal.zzbf r0 = r0.zzans
            java.lang.String r4 = r0.zzjz()
            if (r13 != 0) goto L43
            if (r4 == 0) goto L43
            java.lang.String r13 = "unset"
            boolean r13 = r13.equals(r4)
            if (r13 == 0) goto L30
            com.google.android.gms.measurement.internal.zzcs r5 = r12.zzge()
            java.lang.String r6 = "app"
            java.lang.String r7 = "_ap"
            r8 = 0
            com.google.android.gms.common.util.Clock r13 = r12.zzrz
            long r9 = r13.currentTimeMillis()
            r5.zza(r6, r7, r8, r9)
            goto L43
        L30:
            com.google.android.gms.measurement.internal.zzcs r1 = r12.zzge()
            java.lang.String r2 = "app"
            java.lang.String r3 = "_ap"
            com.google.android.gms.common.util.Clock r13 = r12.zzrz
            long r5 = r13.currentTimeMillis()
            r1.zza(r2, r3, r4, r5)
            r13 = 0
            goto L44
        L43:
            r13 = 1
        L44:
            if (r13 == 0) goto L83
            com.google.android.gms.measurement.internal.zzn r13 = r12.zzapo
            java.lang.String r0 = "google_analytics_default_allow_ad_personalization_signals"
            java.lang.Boolean r13 = r13.zzau(r0)
            if (r13 == 0) goto L71
            com.google.android.gms.measurement.internal.zzcs r0 = r12.zzge()
            java.lang.String r1 = "auto"
            java.lang.String r2 = "_ap"
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L61
            r3 = 1
            goto L63
        L61:
            r3 = 0
        L63:
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            com.google.android.gms.common.util.Clock r13 = r12.zzrz
            long r4 = r13.currentTimeMillis()
            r0.zza(r1, r2, r3, r4)
            return
        L71:
            com.google.android.gms.measurement.internal.zzcs r6 = r12.zzge()
            java.lang.String r7 = "auto"
            java.lang.String r8 = "_ap"
            r9 = 0
            com.google.android.gms.common.util.Clock r13 = r12.zzrz
            long r10 = r13.currentTimeMillis()
            r6.zza(r7, r8, r9, r10)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzbt.zzj(boolean):void");
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzk zzgr() {
        return this.zzaiq;
    }

    public final zzn zzgq() {
        return this.zzapo;
    }

    public final zzba zzgp() {
        zza((zzco) this.zzapp);
        return this.zzapp;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzap zzgo() {
        zza((zzcp) this.zzapq);
        return this.zzapq;
    }

    public final zzap zzkf() {
        if (this.zzapq == null || !this.zzapq.isInitialized()) {
            return null;
        }
        return this.zzapq;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzbo zzgn() {
        zza((zzcp) this.zzapr);
        return this.zzapr;
    }

    public final zzeq zzgj() {
        zza((zzf) this.zzaps);
        return this.zzaps;
    }

    public final zzbg zzkg() {
        return this.zzaqd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzbo zzkh() {
        return this.zzapr;
    }

    public final zzcs zzge() {
        zza((zzf) this.zzapx);
        return this.zzapx;
    }

    public final AppMeasurement zzki() {
        return this.zzapt;
    }

    public final zzfk zzgm() {
        zza((zzco) this.zzapu);
        return this.zzapu;
    }

    public final zzan zzgl() {
        zza((zzco) this.zzapv);
        return this.zzapv;
    }

    public final zzal zzgi() {
        zza((zzf) this.zzapz);
        return this.zzapz;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final Context getContext() {
        return this.zzri;
    }

    public final boolean zzkj() {
        return TextUtils.isEmpty(this.zzadx);
    }

    public final String zzkk() {
        return this.zzadx;
    }

    public final String zzkl() {
        return this.zzapm;
    }

    public final String zzkm() {
        return this.zzapn;
    }

    public final boolean zzkn() {
        return this.zzadv;
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final Clock zzbx() {
        return this.zzrz;
    }

    public final zzdo zzgh() {
        zza((zzf) this.zzapw);
        return this.zzapw;
    }

    public final zzdr zzgg() {
        zza((zzf) this.zzaqa);
        return this.zzaqa;
    }

    public final zzx zzgk() {
        zza((zzcp) this.zzaqb);
        return this.zzaqb;
    }

    public final zzaj zzgf() {
        zza((zzf) this.zzaqc);
        return this.zzaqc;
    }

    public final zza zzgd() {
        if (this.zzapy == null) {
            throw new IllegalStateException("Component not created");
        }
        return this.zzapy;
    }

    public static zzbt zza(Context context, zzak zzakVar) {
        if (zzakVar != null && (zzakVar.origin == null || zzakVar.zzadx == null)) {
            zzakVar = new zzak(zzakVar.zzadt, zzakVar.zzadu, zzakVar.zzadv, zzakVar.zzadw, null, null, zzakVar.zzady);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzapl == null) {
            synchronized (zzbt.class) {
                if (zzapl == null) {
                    zzapl = new zzbt(new zzcr(context, zzakVar));
                }
            }
        } else if (zzakVar != null && zzakVar.zzady != null && zzakVar.zzady.containsKey("dataCollectionDefaultEnabled")) {
            zzapl.zzd(zzakVar.zzady.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zzapl;
    }

    private final void zzcl() {
        if (!this.zzvz) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    private static void zza(zzcp zzcpVar) {
        if (zzcpVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzcpVar.isInitialized()) {
            return;
        }
        String valueOf = String.valueOf(zzcpVar.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
        sb.append("Component not initialized: ");
        sb.append(valueOf);
        throw new IllegalStateException(sb.toString());
    }

    private static void zza(zzf zzfVar) {
        if (zzfVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzfVar.isInitialized()) {
            return;
        }
        String valueOf = String.valueOf(zzfVar.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
        sb.append("Component not initialized: ");
        sb.append(valueOf);
        throw new IllegalStateException(sb.toString());
    }

    private static void zza(zzco zzcoVar) {
        if (zzcoVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzd(boolean z) {
        this.zzaqg = Boolean.valueOf(z);
    }

    @WorkerThread
    public final boolean zzko() {
        return this.zzaqg != null && this.zzaqg.booleanValue();
    }

    @WorkerThread
    public final boolean isEnabled() {
        boolean z;
        zzgn().zzaf();
        zzcl();
        if (this.zzapo.zzhu()) {
            return false;
        }
        Boolean zzhv = this.zzapo.zzhv();
        if (zzhv != null) {
            z = zzhv.booleanValue();
        } else {
            z = !GoogleServices.isMeasurementExplicitlyDisabled();
            if (z && this.zzaqg != null && zzaf.zzalh.get().booleanValue()) {
                z = this.zzaqg.booleanValue();
            }
        }
        return zzgp().zzh(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zzkp() {
        Long valueOf = Long.valueOf(zzgp().zzanj.get());
        if (valueOf.longValue() == 0) {
            return this.zzagx;
        }
        return Math.min(this.zzagx, valueOf.longValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzgb() {
        zzk zzkVar = this.zzaiq;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzga() {
        zzk zzkVar = this.zzaiq;
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzcp zzcpVar) {
        this.zzaqh++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzf zzfVar) {
        this.zzaqh++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzkq() {
        this.zzaqi++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final boolean zzkr() {
        zzcl();
        zzgn().zzaf();
        if (this.zzaqe == null || this.zzaqf == 0 || (this.zzaqe != null && !this.zzaqe.booleanValue() && Math.abs(this.zzrz.elapsedRealtime() - this.zzaqf) > 1000)) {
            this.zzaqf = this.zzrz.elapsedRealtime();
            zzk zzkVar = this.zzaiq;
            boolean z = true;
            this.zzaqe = Boolean.valueOf(zzgm().zzx("android.permission.INTERNET") && zzgm().zzx("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzri).isCallerInstantApp() || this.zzapo.zzib() || (zzbj.zza(this.zzri) && zzfk.zza(this.zzri, false))));
            if (this.zzaqe.booleanValue()) {
                if (!zzgm().zzt(zzgf().getGmpAppId(), zzgf().zzgw()) && TextUtils.isEmpty(zzgf().zzgw())) {
                    z = false;
                }
                this.zzaqe = Boolean.valueOf(z);
            }
        }
        return this.zzaqe.booleanValue();
    }
}
