package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class zzcs extends zzf {
    @VisibleForTesting
    protected zzdm zzaqv;
    private AppMeasurement.EventInterceptor zzaqw;
    private final Set<AppMeasurement.OnEventListener> zzaqx;
    private boolean zzaqy;
    private final AtomicReference<String> zzaqz;
    @VisibleForTesting
    protected boolean zzara;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzcs(zzbt zzbtVar) {
        super(zzbtVar);
        this.zzaqx = new CopyOnWriteArraySet();
        this.zzara = true;
        this.zzaqz = new AtomicReference<>();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzgt() {
        return false;
    }

    public final void zzks() {
        if (getContext().getApplicationContext() instanceof Application) {
            ((Application) getContext().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zzaqv);
        }
    }

    public final Boolean zzkt() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzgn().zza(atomicReference, 15000L, "boolean test flag value", new zzct(this, atomicReference));
    }

    public final String zzku() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzgn().zza(atomicReference, 15000L, "String test flag value", new zzdd(this, atomicReference));
    }

    public final Long zzkv() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzgn().zza(atomicReference, 15000L, "long test flag value", new zzdf(this, atomicReference));
    }

    public final Integer zzkw() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzgn().zza(atomicReference, 15000L, "int test flag value", new zzdg(this, atomicReference));
    }

    public final Double zzkx() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzgn().zza(atomicReference, 15000L, "double test flag value", new zzdh(this, atomicReference));
    }

    public final void setMeasurementEnabled(boolean z) {
        zzcl();
        zzgb();
        zzgn().zzc(new zzdi(this, z));
    }

    public final void zzd(boolean z) {
        zzcl();
        zzgb();
        zzgn().zzc(new zzdj(this, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzk(boolean z) {
        zzaf();
        zzgb();
        zzcl();
        zzgo().zzjk().zzg("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzgp().setMeasurementEnabled(z);
        zzky();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzky() {
        if (zzgq().zze(zzgf().zzal(), zzaf.zzalj)) {
            this.zzadj.zzj(false);
        }
        if (zzgq().zzbd(zzgf().zzal()) && this.zzadj.isEnabled() && this.zzara) {
            zzgo().zzjk().zzbx("Recording app launch after enabling measurement for the first time (FE)");
            zzkz();
            return;
        }
        zzgo().zzjk().zzbx("Updating Scion state (FE)");
        zzgg().zzlc();
    }

    public final void setMinimumSessionDuration(long j) {
        zzgb();
        zzgn().zzc(new zzdk(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzgb();
        zzgn().zzc(new zzdl(this, j));
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        logEvent(str, str2, bundle, false, true, zzbx().currentTimeMillis());
    }

    public final void logEvent(String str, String str2, Bundle bundle) {
        logEvent(str, str2, bundle, true, true, zzbx().currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, String str2, Bundle bundle) {
        zzgb();
        zzaf();
        zza(str, str2, zzbx().currentTimeMillis(), bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, String str2, long j, Bundle bundle) {
        zzgb();
        zzaf();
        zza(str, str2, j, bundle, true, this.zzaqw == null || zzfk.zzcv(str2), false, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00aa  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(java.lang.String r29, java.lang.String r30, long r31, android.os.Bundle r33, boolean r34, boolean r35, boolean r36, java.lang.String r37) {
        /*
            Method dump skipped, instructions count: 912
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzcs.zza(java.lang.String, java.lang.String, long, android.os.Bundle, boolean, boolean, boolean, java.lang.String):void");
    }

    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        boolean z3;
        zzgb();
        String str3 = str == null ? SettingsJsonConstants.APP_KEY : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        if (z2 && this.zzaqw != null && !zzfk.zzcv(str2)) {
            z3 = false;
            zzb(str3, str2, j, bundle2, z2, z3, !z, null);
        }
        z3 = true;
        zzb(str3, str2, j, bundle2, z2, z3, !z, null);
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzgn().zzc(new zzcu(this, str, str2, j, zzfk.zzf(bundle), z, z2, z3, str3));
    }

    public final void zzb(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, z, zzbx().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        if (str == null) {
            str = SettingsJsonConstants.APP_KEY;
        }
        String str3 = str;
        int i = 6;
        if (z || "_ap".equals(str2)) {
            i = zzgm().zzcs(str2);
        } else {
            zzfk zzgm = zzgm();
            if (zzgm.zzr("user property", str2)) {
                if (!zzgm.zza("user property", AppMeasurement.UserProperty.zzado, str2)) {
                    i = 15;
                } else if (zzgm.zza("user property", 24, str2)) {
                    i = 0;
                }
            }
        }
        if (i != 0) {
            zzgm();
            this.zzadj.zzgm().zza(i, "_ev", zzfk.zza(str2, 24, true), str2 != null ? str2.length() : 0);
        } else if (obj != null) {
            int zzi = zzgm().zzi(str2, obj);
            if (zzi != 0) {
                zzgm();
                String zza = zzfk.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    r0 = String.valueOf(obj).length();
                }
                this.zzadj.zzgm().zza(zzi, "_ev", zza, r0);
                return;
            }
            Object zzj = zzgm().zzj(str2, obj);
            if (zzj != null) {
                zza(str3, str2, j, zzj);
            }
        } else {
            zza(str3, str2, j, (Object) null);
        }
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzgn().zzc(new zzcv(this, str, str2, obj, j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzgb();
        zzcl();
        if (zzgq().zze(zzgf().zzal(), zzaf.zzalj)) {
            if ("_ap".equals(str2) && !"auto".equals(str)) {
                if (obj instanceof String) {
                    String str3 = (String) obj;
                    if (!TextUtils.isEmpty(str3)) {
                        obj = Long.valueOf(("true".equals(str3.toLowerCase(Locale.ENGLISH)) || "1".equals(obj)) ? 1L : 0L);
                        zzgp().zzans.zzcc(((Long) obj).longValue() == 1 ? "true" : "false");
                    }
                }
                if (obj == null) {
                    zzgp().zzans.zzcc("unset");
                    zzgn().zzc(new zzcw(this));
                }
            }
        } else if ("_ap".equals(str2)) {
            return;
        }
        Object obj2 = obj;
        if (!this.zzadj.isEnabled()) {
            zzgo().zzjk().zzbx("User property not set since app measurement is disabled");
        } else if (this.zzadj.zzkr()) {
            zzgo().zzjk().zze("Setting user property (FE)", zzgl().zzbs(str2), obj2);
            zzgg().zzb(new zzfh(str2, j, obj2, str));
        }
    }

    public final List<zzfh> zzl(boolean z) {
        zzgb();
        zzcl();
        zzgo().zzjk().zzbx("Fetching user attributes (FE)");
        if (zzgn().zzkb()) {
            zzgo().zzjd().zzbx("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzk.isMainThread()) {
            zzgo().zzjd().zzbx("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzadj.zzgn().zzc(new zzcx(this, atomicReference, z));
                try {
                    atomicReference.wait(5000L);
                } catch (InterruptedException e) {
                    zzgo().zzjg().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzfh> list = (List) atomicReference.get();
            if (list == null) {
                zzgo().zzjg().zzbx("Timed out waiting for get user properties");
                return Collections.emptyList();
            }
            return list;
        }
    }

    @Nullable
    public final String zzfx() {
        zzgb();
        return this.zzaqz.get();
    }

    @Nullable
    public final String zzaj(long j) {
        if (zzgn().zzkb()) {
            zzgo().zzjd().zzbx("Cannot retrieve app instance id from analytics worker thread");
            return null;
        } else if (zzk.isMainThread()) {
            zzgo().zzjd().zzbx("Cannot retrieve app instance id from main thread");
            return null;
        } else {
            long elapsedRealtime = zzbx().elapsedRealtime();
            String zzak = zzak(120000L);
            long elapsedRealtime2 = zzbx().elapsedRealtime() - elapsedRealtime;
            return (zzak != null || elapsedRealtime2 >= 120000) ? zzak : zzak(120000 - elapsedRealtime2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzcm(@Nullable String str) {
        this.zzaqz.set(str);
    }

    @Nullable
    private final String zzak(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzgn().zzc(new zzcy(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException unused) {
                zzgo().zzjg().zzbx("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    public final void resetAnalyticsData(long j) {
        if (zzgq().zza(zzaf.zzalk)) {
            zzcm(null);
        }
        zzgn().zzc(new zzcz(this, j));
    }

    @WorkerThread
    public final void zzkz() {
        zzaf();
        zzgb();
        zzcl();
        if (this.zzadj.zzkr()) {
            zzgg().zzkz();
            this.zzara = false;
            String zzjw = zzgp().zzjw();
            if (TextUtils.isEmpty(zzjw)) {
                return;
            }
            zzgk().zzcl();
            if (zzjw.equals(Build.VERSION.RELEASE)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("_po", zzjw);
            logEvent("auto", "_ou", bundle);
        }
    }

    @WorkerThread
    public final void setEventInterceptor(AppMeasurement.EventInterceptor eventInterceptor) {
        zzaf();
        zzgb();
        zzcl();
        if (eventInterceptor != null && eventInterceptor != this.zzaqw) {
            Preconditions.checkState(this.zzaqw == null, "EventInterceptor already set.");
        }
        this.zzaqw = eventInterceptor;
    }

    public final void registerOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzgb();
        zzcl();
        Preconditions.checkNotNull(onEventListener);
        if (this.zzaqx.add(onEventListener)) {
            return;
        }
        zzgo().zzjg().zzbx("OnEventListener already registered");
    }

    public final void unregisterOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzgb();
        zzcl();
        Preconditions.checkNotNull(onEventListener);
        if (this.zzaqx.remove(onEventListener)) {
            return;
        }
        zzgo().zzjg().zzbx("OnEventListener had not been registered");
    }

    public final void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        zzgb();
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = new AppMeasurement.ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzgo().zzjg().zzbx("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mAppId);
        zzga();
        zza(new AppMeasurement.ConditionalUserProperty(conditionalUserProperty));
    }

    private final void zza(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zzbx().currentTimeMillis();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzgm().zzcs(str) != 0) {
            zzgo().zzjd().zzg("Invalid conditional user property name", zzgl().zzbu(str));
        } else if (zzgm().zzi(str, obj) != 0) {
            zzgo().zzjd().zze("Invalid conditional user property value", zzgl().zzbu(str), obj);
        } else {
            Object zzj = zzgm().zzj(str, obj);
            if (zzj == null) {
                zzgo().zzjd().zze("Unable to normalize conditional user property value", zzgl().zzbu(str), obj);
                return;
            }
            conditionalUserProperty.mValue = zzj;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (!TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) && (j > 15552000000L || j < 1)) {
                zzgo().zzjd().zze("Invalid conditional user property timeout", zzgl().zzbu(str), Long.valueOf(j));
                return;
            }
            long j2 = conditionalUserProperty.mTimeToLive;
            if (j2 > 15552000000L || j2 < 1) {
                zzgo().zzjd().zze("Invalid conditional user property time to live", zzgl().zzbu(str), Long.valueOf(j2));
            } else {
                zzgn().zzc(new zzda(this, conditionalUserProperty));
            }
        }
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzgb();
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zzga();
        zza(str, str2, str3, bundle);
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzbx().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzgn().zzc(new zzdb(this, conditionalUserProperty));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzb(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        if (!this.zzadj.isEnabled()) {
            zzgo().zzjk().zzbx("Conditional property not sent since collection is disabled");
            return;
        }
        zzfh zzfhVar = new zzfh(conditionalUserProperty.mName, conditionalUserProperty.mTriggeredTimestamp, conditionalUserProperty.mValue, conditionalUserProperty.mOrigin);
        try {
            zzad zza = zzgm().zza(conditionalUserProperty.mAppId, conditionalUserProperty.mTriggeredEventName, conditionalUserProperty.mTriggeredEventParams, conditionalUserProperty.mOrigin, 0L, true, false);
            zzgg().zzd(new zzl(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, zzfhVar, conditionalUserProperty.mCreationTimestamp, false, conditionalUserProperty.mTriggerEventName, zzgm().zza(conditionalUserProperty.mAppId, conditionalUserProperty.mTimedOutEventName, conditionalUserProperty.mTimedOutEventParams, conditionalUserProperty.mOrigin, 0L, true, false), conditionalUserProperty.mTriggerTimeout, zza, conditionalUserProperty.mTimeToLive, zzgm().zza(conditionalUserProperty.mAppId, conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, 0L, true, false)));
        } catch (IllegalArgumentException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzc(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        if (!this.zzadj.isEnabled()) {
            zzgo().zzjk().zzbx("Conditional property not cleared since collection is disabled");
            return;
        }
        try {
            zzgg().zzd(new zzl(conditionalUserProperty.mAppId, conditionalUserProperty.mOrigin, new zzfh(conditionalUserProperty.mName, 0L, null, null), conditionalUserProperty.mCreationTimestamp, conditionalUserProperty.mActive, conditionalUserProperty.mTriggerEventName, null, conditionalUserProperty.mTriggerTimeout, null, conditionalUserProperty.mTimeToLive, zzgm().zza(conditionalUserProperty.mAppId, conditionalUserProperty.mExpiredEventName, conditionalUserProperty.mExpiredEventParams, conditionalUserProperty.mOrigin, conditionalUserProperty.mCreationTimestamp, true, false)));
        } catch (IllegalArgumentException unused) {
        }
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzgb();
        return zzf(null, str, str2);
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzga();
        return zzf(str, str2, str3);
    }

    @VisibleForTesting
    private final List<AppMeasurement.ConditionalUserProperty> zzf(String str, String str2, String str3) {
        if (zzgn().zzkb()) {
            zzgo().zzjd().zzbx("Cannot get conditional user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzk.isMainThread()) {
            zzgo().zzjd().zzbx("Cannot get conditional user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzadj.zzgn().zzc(new zzdc(this, atomicReference, str, str2, str3));
                try {
                    atomicReference.wait(5000L);
                } catch (InterruptedException e) {
                    zzgo().zzjg().zze("Interrupted waiting for get conditional user properties", str, e);
                }
            }
            List<zzl> list = (List) atomicReference.get();
            if (list == null) {
                zzgo().zzjg().zzg("Timed out waiting for get conditional user properties", str);
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (zzl zzlVar : list) {
                AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
                conditionalUserProperty.mAppId = zzlVar.packageName;
                conditionalUserProperty.mOrigin = zzlVar.origin;
                conditionalUserProperty.mCreationTimestamp = zzlVar.creationTimestamp;
                conditionalUserProperty.mName = zzlVar.zzahb.name;
                conditionalUserProperty.mValue = zzlVar.zzahb.getValue();
                conditionalUserProperty.mActive = zzlVar.active;
                conditionalUserProperty.mTriggerEventName = zzlVar.triggerEventName;
                if (zzlVar.zzahc != null) {
                    conditionalUserProperty.mTimedOutEventName = zzlVar.zzahc.name;
                    if (zzlVar.zzahc.zzaid != null) {
                        conditionalUserProperty.mTimedOutEventParams = zzlVar.zzahc.zzaid.zziv();
                    }
                }
                conditionalUserProperty.mTriggerTimeout = zzlVar.triggerTimeout;
                if (zzlVar.zzahd != null) {
                    conditionalUserProperty.mTriggeredEventName = zzlVar.zzahd.name;
                    if (zzlVar.zzahd.zzaid != null) {
                        conditionalUserProperty.mTriggeredEventParams = zzlVar.zzahd.zzaid.zziv();
                    }
                }
                conditionalUserProperty.mTriggeredTimestamp = zzlVar.zzahb.zzaue;
                conditionalUserProperty.mTimeToLive = zzlVar.timeToLive;
                if (zzlVar.zzahe != null) {
                    conditionalUserProperty.mExpiredEventName = zzlVar.zzahe.name;
                    if (zzlVar.zzahe.zzaid != null) {
                        conditionalUserProperty.mExpiredEventParams = zzlVar.zzahe.zzaid.zziv();
                    }
                }
                arrayList.add(conditionalUserProperty);
            }
            return arrayList;
        }
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzgb();
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zzga();
        return zzb(str, str2, str3, z);
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzgn().zzkb()) {
            zzgo().zzjd().zzbx("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzk.isMainThread()) {
            zzgo().zzjd().zzbx("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzadj.zzgn().zzc(new zzde(this, atomicReference, str, str2, str3, z));
                try {
                    atomicReference.wait(5000L);
                } catch (InterruptedException e) {
                    zzgo().zzjg().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzfh> list = (List) atomicReference.get();
            if (list == null) {
                zzgo().zzjg().zzbx("Timed out waiting for get user properties");
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzfh zzfhVar : list) {
                arrayMap.put(zzfhVar.name, zzfhVar.getValue());
            }
            return arrayMap;
        }
    }

    @Nullable
    public final String getCurrentScreenName() {
        zzdn zzlb = this.zzadj.zzgh().zzlb();
        if (zzlb != null) {
            return zzlb.zzuw;
        }
        return null;
    }

    @Nullable
    public final String getCurrentScreenClass() {
        zzdn zzlb = this.zzadj.zzgh().zzlb();
        if (zzlb != null) {
            return zzlb.zzarl;
        }
        return null;
    }

    @Nullable
    public final String getGmpAppId() {
        if (this.zzadj.zzkk() != null) {
            return this.zzadj.zzkk();
        }
        try {
            return GoogleServices.getGoogleAppId();
        } catch (IllegalStateException e) {
            this.zzadj.zzgo().zzjd().zzg("getGoogleAppId failed with exception", e);
            return null;
        }
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
