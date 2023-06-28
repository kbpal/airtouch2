package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
/* loaded from: classes.dex */
public final class zzdr extends zzf {
    private final zzef zzarz;
    private zzag zzasa;
    private volatile Boolean zzasb;
    private final zzv zzasc;
    private final zzev zzasd;
    private final List<Runnable> zzase;
    private final zzv zzasf;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzdr(zzbt zzbtVar) {
        super(zzbtVar);
        this.zzase = new ArrayList();
        this.zzasd = new zzev(zzbtVar.zzbx());
        this.zzarz = new zzef(this);
        this.zzasc = new zzds(this, zzbtVar);
        this.zzasf = new zzdx(this, zzbtVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzgt() {
        return false;
    }

    @WorkerThread
    public final boolean isConnected() {
        zzaf();
        zzcl();
        return this.zzasa != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zzlc() {
        zzaf();
        zzcl();
        zzf(new zzdy(this, zzm(true)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzag zzagVar, AbstractSafeParcelable abstractSafeParcelable, zzh zzhVar) {
        int i;
        List<AbstractSafeParcelable> zzr;
        zzaf();
        zzgb();
        zzcl();
        boolean zzld = zzld();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            if (!zzld || (zzr = zzgi().zzr(100)) == null) {
                i = 0;
            } else {
                arrayList.addAll(zzr);
                i = zzr.size();
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList2.get(i4);
                i4++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzad) {
                    try {
                        zzagVar.zza((zzad) abstractSafeParcelable2, zzhVar);
                    } catch (RemoteException e) {
                        zzgo().zzjd().zzg("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzfh) {
                    try {
                        zzagVar.zza((zzfh) abstractSafeParcelable2, zzhVar);
                    } catch (RemoteException e2) {
                        zzgo().zzjd().zzg("Failed to send attribute to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzl) {
                    try {
                        zzagVar.zza((zzl) abstractSafeParcelable2, zzhVar);
                    } catch (RemoteException e3) {
                        zzgo().zzjd().zzg("Failed to send conditional property to the service", e3);
                    }
                } else {
                    zzgo().zzjd().zzbx("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzad zzadVar, String str) {
        Preconditions.checkNotNull(zzadVar);
        zzaf();
        zzcl();
        boolean zzld = zzld();
        zzf(new zzdz(this, zzld, zzld && zzgi().zza(zzadVar), zzadVar, zzm(true), str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zzd(zzl zzlVar) {
        Preconditions.checkNotNull(zzlVar);
        zzaf();
        zzcl();
        zzgr();
        zzf(new zzea(this, true, zzgi().zzc(zzlVar), new zzl(zzlVar), zzm(true), zzlVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzl>> atomicReference, String str, String str2, String str3) {
        zzaf();
        zzcl();
        zzf(new zzeb(this, atomicReference, str, str2, str3, zzm(false)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzfh>> atomicReference, String str, String str2, String str3, boolean z) {
        zzaf();
        zzcl();
        zzf(new zzec(this, atomicReference, str, str2, str3, z, zzm(false)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzfh zzfhVar) {
        zzaf();
        zzcl();
        zzf(new zzed(this, zzld() && zzgi().zza(zzfhVar), zzfhVar, zzm(true)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzfh>> atomicReference, boolean z) {
        zzaf();
        zzcl();
        zzf(new zzee(this, atomicReference, zzm(false), z));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void resetAnalyticsData() {
        zzaf();
        zzgb();
        zzcl();
        zzh zzm = zzm(false);
        if (zzld()) {
            zzgi().resetAnalyticsData();
        }
        zzf(new zzdt(this, zzm));
    }

    private final boolean zzld() {
        zzgr();
        return true;
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzaf();
        zzcl();
        zzf(new zzdu(this, atomicReference, zzm(false)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zzkz() {
        zzaf();
        zzcl();
        zzf(new zzdv(this, zzm(true)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzdn zzdnVar) {
        zzaf();
        zzcl();
        zzf(new zzdw(this, zzdnVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzcy() {
        zzaf();
        this.zzasd.start();
        this.zzasc.zzh(zzaf.zzakj.get().longValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010f  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzdj() {
        /*
            Method dump skipped, instructions count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzdr.zzdj():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzle() {
        return this.zzasb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzag zzagVar) {
        zzaf();
        Preconditions.checkNotNull(zzagVar);
        this.zzasa = zzagVar;
        zzcy();
        zzlf();
    }

    @WorkerThread
    public final void disconnect() {
        zzaf();
        zzcl();
        if (zzn.zzia()) {
            this.zzarz.zzlg();
        }
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzarz);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzasa = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void onServiceDisconnected(ComponentName componentName) {
        zzaf();
        if (this.zzasa != null) {
            this.zzasa = null;
            zzgo().zzjl().zzg("Disconnected from device MeasurementService", componentName);
            zzaf();
            zzdj();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzcz() {
        zzaf();
        if (isConnected()) {
            zzgo().zzjl().zzbx("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    private final void zzf(Runnable runnable) throws IllegalStateException {
        zzaf();
        if (isConnected()) {
            runnable.run();
        } else if (this.zzase.size() >= 1000) {
            zzgo().zzjd().zzbx("Discarding data. Max runnable queue size reached");
        } else {
            this.zzase.add(runnable);
            this.zzasf.zzh(60000L);
            zzdj();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzlf() {
        zzaf();
        zzgo().zzjl().zzg("Processing queued up service tasks", Integer.valueOf(this.zzase.size()));
        for (Runnable runnable : this.zzase) {
            try {
                runnable.run();
            } catch (Exception e) {
                zzgo().zzjd().zzg("Task exception while flushing queue", e);
            }
        }
        this.zzase.clear();
        this.zzasf.cancel();
    }

    @WorkerThread
    @Nullable
    private final zzh zzm(boolean z) {
        zzgr();
        return zzgf().zzbr(z ? zzgo().zzjn() : null);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ zzag zza(zzdr zzdrVar, zzag zzagVar) {
        zzdrVar.zzasa = null;
        return null;
    }
}
