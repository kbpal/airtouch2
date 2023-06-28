package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public class zzfa implements zzcq {
    private static volatile zzfa zzatc;
    private final zzbt zzadj;
    private zzbn zzatd;
    private zzat zzate;
    private zzq zzatf;
    private zzay zzatg;
    private zzew zzath;
    private zzj zzati;
    private final zzfg zzatj;
    private boolean zzatk;
    @VisibleForTesting
    private long zzatl;
    private List<Runnable> zzatm;
    private int zzatn;
    private int zzato;
    private boolean zzatp;
    private boolean zzatq;
    private boolean zzatr;
    private FileLock zzats;
    private FileChannel zzatt;
    private List<Long> zzatu;
    private List<Long> zzatv;
    private long zzatw;
    private boolean zzvz;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class zza implements zzs {
        zzgi zzaua;
        List<Long> zzaub;
        List<zzgf> zzauc;
        private long zzaud;

        private zza() {
        }

        @Override // com.google.android.gms.measurement.internal.zzs
        public final void zzb(zzgi zzgiVar) {
            Preconditions.checkNotNull(zzgiVar);
            this.zzaua = zzgiVar;
        }

        @Override // com.google.android.gms.measurement.internal.zzs
        public final boolean zza(long j, zzgf zzgfVar) {
            Preconditions.checkNotNull(zzgfVar);
            if (this.zzauc == null) {
                this.zzauc = new ArrayList();
            }
            if (this.zzaub == null) {
                this.zzaub = new ArrayList();
            }
            if (this.zzauc.size() <= 0 || zza(this.zzauc.get(0)) == zza(zzgfVar)) {
                long zzvu = this.zzaud + zzgfVar.zzvu();
                if (zzvu >= Math.max(0, zzaf.zzajl.get().intValue())) {
                    return false;
                }
                this.zzaud = zzvu;
                this.zzauc.add(zzgfVar);
                this.zzaub.add(Long.valueOf(j));
                return this.zzauc.size() < Math.max(1, zzaf.zzajm.get().intValue());
            }
            return false;
        }

        private static long zza(zzgf zzgfVar) {
            return ((zzgfVar.zzawu.longValue() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzfa zzfaVar, zzfb zzfbVar) {
            this();
        }
    }

    public static zzfa zzm(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzatc == null) {
            synchronized (zzfa.class) {
                if (zzatc == null) {
                    zzatc = new zzfa(new zzff(context));
                }
            }
        }
        return zzatc;
    }

    private zzfa(zzff zzffVar) {
        this(zzffVar, null);
    }

    private zzfa(zzff zzffVar, zzbt zzbtVar) {
        this.zzvz = false;
        Preconditions.checkNotNull(zzffVar);
        this.zzadj = zzbt.zza(zzffVar.zzri, (zzak) null);
        this.zzatw = -1L;
        zzfg zzfgVar = new zzfg(this);
        zzfgVar.zzq();
        this.zzatj = zzfgVar;
        zzat zzatVar = new zzat(this);
        zzatVar.zzq();
        this.zzate = zzatVar;
        zzbn zzbnVar = new zzbn(this);
        zzbnVar.zzq();
        this.zzatd = zzbnVar;
        this.zzadj.zzgn().zzc(new zzfb(this, zzffVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzff zzffVar) {
        this.zzadj.zzgn().zzaf();
        zzq zzqVar = new zzq(this);
        zzqVar.zzq();
        this.zzatf = zzqVar;
        this.zzadj.zzgq().zza(this.zzatd);
        zzj zzjVar = new zzj(this);
        zzjVar.zzq();
        this.zzati = zzjVar;
        zzew zzewVar = new zzew(this);
        zzewVar.zzq();
        this.zzath = zzewVar;
        this.zzatg = new zzay(this);
        if (this.zzatn != this.zzato) {
            this.zzadj.zzgo().zzjd().zze("Not all upload components initialized", Integer.valueOf(this.zzatn), Integer.valueOf(this.zzato));
        }
        this.zzvz = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void start() {
        this.zzadj.zzgn().zzaf();
        zzjq().zzif();
        if (this.zzadj.zzgp().zzane.get() == 0) {
            this.zzadj.zzgp().zzane.set(this.zzadj.zzbx().currentTimeMillis());
        }
        zzlv();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzk zzgr() {
        return this.zzadj.zzgr();
    }

    public final zzn zzgq() {
        return this.zzadj.zzgq();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzap zzgo() {
        return this.zzadj.zzgo();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final zzbo zzgn() {
        return this.zzadj.zzgn();
    }

    private final zzbn zzln() {
        zza(this.zzatd);
        return this.zzatd;
    }

    public final zzat zzlo() {
        zza(this.zzate);
        return this.zzate;
    }

    public final zzq zzjq() {
        zza(this.zzatf);
        return this.zzatf;
    }

    private final zzay zzlp() {
        if (this.zzatg == null) {
            throw new IllegalStateException("Network broadcast receiver not created");
        }
        return this.zzatg;
    }

    private final zzew zzlq() {
        zza(this.zzath);
        return this.zzath;
    }

    public final zzj zzjp() {
        zza(this.zzati);
        return this.zzati;
    }

    public final zzfg zzjo() {
        zza(this.zzatj);
        return this.zzatj;
    }

    public final zzan zzgl() {
        return this.zzadj.zzgl();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final Context getContext() {
        return this.zzadj.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzcq
    public final Clock zzbx() {
        return this.zzadj.zzbx();
    }

    public final zzfk zzgm() {
        return this.zzadj.zzgm();
    }

    @WorkerThread
    private final void zzaf() {
        this.zzadj.zzgn().zzaf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzlr() {
        if (!this.zzvz) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zza(zzez zzezVar) {
        if (zzezVar == null) {
            throw new IllegalStateException("Upload Component not created");
        }
        if (zzezVar.isInitialized()) {
            return;
        }
        String valueOf = String.valueOf(zzezVar.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
        sb.append("Component not initialized: ");
        sb.append(valueOf);
        throw new IllegalStateException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(zzh zzhVar) {
        zzaf();
        zzlr();
        Preconditions.checkNotEmpty(zzhVar.packageName);
        zzg(zzhVar);
    }

    private final long zzls() {
        long currentTimeMillis = this.zzadj.zzbx().currentTimeMillis();
        zzba zzgp = this.zzadj.zzgp();
        zzgp.zzcl();
        zzgp.zzaf();
        long j = zzgp.zzani.get();
        if (j == 0) {
            j = 1 + zzgp.zzgm().zzmd().nextInt(86400000);
            zzgp.zzani.set(j);
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzad zzadVar, String str) {
        zzg zzbl = zzjq().zzbl(str);
        if (zzbl == null || TextUtils.isEmpty(zzbl.zzak())) {
            this.zzadj.zzgo().zzjk().zzg("No app data available; dropping event", str);
            return;
        }
        Boolean zzc = zzc(zzbl);
        if (zzc == null) {
            if (!"_ui".equals(zzadVar.name)) {
                this.zzadj.zzgo().zzjg().zzg("Could not find package. appId", zzap.zzbv(str));
            }
        } else if (!zzc.booleanValue()) {
            this.zzadj.zzgo().zzjd().zzg("App version does not match; dropping event. appId", zzap.zzbv(str));
            return;
        }
        zzc(zzadVar, new zzh(str, zzbl.getGmpAppId(), zzbl.zzak(), zzbl.zzha(), zzbl.zzhb(), zzbl.zzhc(), zzbl.zzhd(), (String) null, zzbl.isMeasurementEnabled(), false, zzbl.getFirebaseInstanceId(), zzbl.zzhq(), 0L, 0, zzbl.zzhr(), zzbl.zzhs(), false, zzbl.zzgw()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzad zzadVar, zzh zzhVar) {
        List<zzl> zzb;
        List<zzl> zzb2;
        List<zzl> zzb3;
        Preconditions.checkNotNull(zzhVar);
        Preconditions.checkNotEmpty(zzhVar.packageName);
        zzaf();
        zzlr();
        String str = zzhVar.packageName;
        long j = zzadVar.zzaip;
        if (zzjo().zze(zzadVar, zzhVar)) {
            if (!zzhVar.zzagg) {
                zzg(zzhVar);
                return;
            }
            zzjq().beginTransaction();
            try {
                zzq zzjq = zzjq();
                Preconditions.checkNotEmpty(str);
                zzjq.zzaf();
                zzjq.zzcl();
                if (j < 0) {
                    zzjq.zzgo().zzjg().zze("Invalid time querying timed out conditional properties", zzap.zzbv(str), Long.valueOf(j));
                    zzb = Collections.emptyList();
                } else {
                    zzb = zzjq.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzl zzlVar : zzb) {
                    if (zzlVar != null) {
                        this.zzadj.zzgo().zzjk().zzd("User property timed out", zzlVar.packageName, this.zzadj.zzgl().zzbu(zzlVar.zzahb.name), zzlVar.zzahb.getValue());
                        if (zzlVar.zzahc != null) {
                            zzd(new zzad(zzlVar.zzahc, j), zzhVar);
                        }
                        zzjq().zzk(str, zzlVar.zzahb.name);
                    }
                }
                zzq zzjq2 = zzjq();
                Preconditions.checkNotEmpty(str);
                zzjq2.zzaf();
                zzjq2.zzcl();
                if (j < 0) {
                    zzjq2.zzgo().zzjg().zze("Invalid time querying expired conditional properties", zzap.zzbv(str), Long.valueOf(j));
                    zzb2 = Collections.emptyList();
                } else {
                    zzb2 = zzjq2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(zzb2.size());
                for (zzl zzlVar2 : zzb2) {
                    if (zzlVar2 != null) {
                        this.zzadj.zzgo().zzjk().zzd("User property expired", zzlVar2.packageName, this.zzadj.zzgl().zzbu(zzlVar2.zzahb.name), zzlVar2.zzahb.getValue());
                        zzjq().zzh(str, zzlVar2.zzahb.name);
                        if (zzlVar2.zzahe != null) {
                            arrayList.add(zzlVar2.zzahe);
                        }
                        zzjq().zzk(str, zzlVar2.zzahb.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zzd(new zzad((zzad) obj, j), zzhVar);
                }
                zzq zzjq3 = zzjq();
                String str2 = zzadVar.name;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zzjq3.zzaf();
                zzjq3.zzcl();
                if (j < 0) {
                    zzjq3.zzgo().zzjg().zzd("Invalid time querying triggered conditional properties", zzap.zzbv(str), zzjq3.zzgl().zzbs(str2), Long.valueOf(j));
                    zzb3 = Collections.emptyList();
                } else {
                    zzb3 = zzjq3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(zzb3.size());
                for (zzl zzlVar3 : zzb3) {
                    if (zzlVar3 != null) {
                        zzfh zzfhVar = zzlVar3.zzahb;
                        zzfj zzfjVar = new zzfj(zzlVar3.packageName, zzlVar3.origin, zzfhVar.name, j, zzfhVar.getValue());
                        if (zzjq().zza(zzfjVar)) {
                            this.zzadj.zzgo().zzjk().zzd("User property triggered", zzlVar3.packageName, this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                        } else {
                            this.zzadj.zzgo().zzjd().zzd("Too many active user properties, ignoring", zzap.zzbv(zzlVar3.packageName), this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                        }
                        if (zzlVar3.zzahd != null) {
                            arrayList3.add(zzlVar3.zzahd);
                        }
                        zzlVar3.zzahb = new zzfh(zzfjVar);
                        zzlVar3.active = true;
                        zzjq().zza(zzlVar3);
                    }
                }
                zzd(zzadVar, zzhVar);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj2 = arrayList4.get(i2);
                    i2++;
                    zzd(new zzad((zzad) obj2, j), zzhVar);
                }
                zzjq().setTransactionSuccessful();
            } finally {
                zzjq().endTransaction();
            }
        }
    }

    @WorkerThread
    private final void zzd(zzad zzadVar, zzh zzhVar) {
        zzz zzai;
        zzg zzbl;
        Preconditions.checkNotNull(zzhVar);
        Preconditions.checkNotEmpty(zzhVar.packageName);
        long nanoTime = System.nanoTime();
        zzaf();
        zzlr();
        String str = zzhVar.packageName;
        if (zzjo().zze(zzadVar, zzhVar)) {
            if (!zzhVar.zzagg) {
                zzg(zzhVar);
            } else if (zzln().zzo(str, zzadVar.name)) {
                this.zzadj.zzgo().zzjg().zze("Dropping blacklisted event. appId", zzap.zzbv(str), this.zzadj.zzgl().zzbs(zzadVar.name));
                boolean z = zzln().zzck(str) || zzln().zzcl(str);
                if (!z && !"_err".equals(zzadVar.name)) {
                    this.zzadj.zzgm().zza(str, 11, "_ev", zzadVar.name, 0);
                }
                if (!z || (zzbl = zzjq().zzbl(str)) == null) {
                    return;
                }
                if (Math.abs(this.zzadj.zzbx().currentTimeMillis() - Math.max(zzbl.zzhg(), zzbl.zzhf())) > zzaf.zzakc.get().longValue()) {
                    this.zzadj.zzgo().zzjk().zzbx("Fetching config for blacklisted app");
                    zzb(zzbl);
                }
            } else {
                if (this.zzadj.zzgo().isLoggable(2)) {
                    this.zzadj.zzgo().zzjl().zzg("Logging event", this.zzadj.zzgl().zzb(zzadVar));
                }
                zzjq().beginTransaction();
                try {
                    zzg(zzhVar);
                    if (("_iap".equals(zzadVar.name) || FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzadVar.name)) && !zza(str, zzadVar)) {
                        zzjq().setTransactionSuccessful();
                        return;
                    }
                    boolean zzcq = zzfk.zzcq(zzadVar.name);
                    boolean equals = "_err".equals(zzadVar.name);
                    zzr zza2 = zzjq().zza(zzls(), str, true, zzcq, false, equals, false);
                    long intValue = zza2.zzahr - zzaf.zzajn.get().intValue();
                    if (intValue > 0) {
                        if (intValue % 1000 == 1) {
                            this.zzadj.zzgo().zzjd().zze("Data loss. Too many events logged. appId, count", zzap.zzbv(str), Long.valueOf(zza2.zzahr));
                        }
                        zzjq().setTransactionSuccessful();
                        return;
                    }
                    if (zzcq) {
                        long intValue2 = zza2.zzahq - zzaf.zzajp.get().intValue();
                        if (intValue2 > 0) {
                            if (intValue2 % 1000 == 1) {
                                this.zzadj.zzgo().zzjd().zze("Data loss. Too many public events logged. appId, count", zzap.zzbv(str), Long.valueOf(zza2.zzahq));
                            }
                            this.zzadj.zzgm().zza(str, 16, "_ev", zzadVar.name, 0);
                            zzjq().setTransactionSuccessful();
                            return;
                        }
                    }
                    if (equals) {
                        long max = zza2.zzaht - Math.max(0, Math.min(1000000, this.zzadj.zzgq().zzb(zzhVar.packageName, zzaf.zzajo)));
                        if (max > 0) {
                            if (max == 1) {
                                this.zzadj.zzgo().zzjd().zze("Too many error events logged. appId, count", zzap.zzbv(str), Long.valueOf(zza2.zzaht));
                            }
                            zzjq().setTransactionSuccessful();
                            return;
                        }
                    }
                    Bundle zziv = zzadVar.zzaid.zziv();
                    this.zzadj.zzgm().zza(zziv, "_o", zzadVar.origin);
                    if (this.zzadj.zzgm().zzcw(str)) {
                        this.zzadj.zzgm().zza(zziv, "_dbg", (Object) 1L);
                        this.zzadj.zzgm().zza(zziv, "_r", (Object) 1L);
                    }
                    long zzbm = zzjq().zzbm(str);
                    if (zzbm > 0) {
                        this.zzadj.zzgo().zzjg().zze("Data lost. Too many events stored on disk, deleted. appId", zzap.zzbv(str), Long.valueOf(zzbm));
                    }
                    zzy zzyVar = r11;
                    boolean z2 = false;
                    zzy zzyVar2 = new zzy(this.zzadj, zzadVar.origin, str, zzadVar.name, zzadVar.zzaip, 0L, zziv);
                    zzz zzg = zzjq().zzg(str, zzyVar.name);
                    if (zzg == null) {
                        if (zzjq().zzbp(str) >= 500 && zzcq) {
                            this.zzadj.zzgo().zzjd().zzd("Too many event names used, ignoring event. appId, name, supported count", zzap.zzbv(str), this.zzadj.zzgl().zzbs(zzyVar.name), 500);
                            this.zzadj.zzgm().zza(str, 8, (String) null, (String) null, 0);
                            return;
                        }
                        zzai = new zzz(str, zzyVar.name, 0L, 0L, zzyVar.timestamp, 0L, null, null, null, null);
                    } else {
                        zzy zza3 = zzyVar.zza(this.zzadj, zzg.zzaig);
                        zzai = zzg.zzai(zza3.timestamp);
                        zzyVar = zza3;
                    }
                    zzjq().zza(zzai);
                    zzaf();
                    zzlr();
                    Preconditions.checkNotNull(zzyVar);
                    Preconditions.checkNotNull(zzhVar);
                    Preconditions.checkNotEmpty(zzyVar.zztt);
                    Preconditions.checkArgument(zzyVar.zztt.equals(zzhVar.packageName));
                    zzgi zzgiVar = new zzgi();
                    zzgiVar.zzaxa = 1;
                    zzgiVar.zzaxi = AbstractSpiCall.ANDROID_CLIENT_TYPE;
                    zzgiVar.zztt = zzhVar.packageName;
                    zzgiVar.zzage = zzhVar.zzage;
                    zzgiVar.zzts = zzhVar.zzts;
                    zzgiVar.zzaxu = zzhVar.zzagd == -2147483648L ? null : Integer.valueOf((int) zzhVar.zzagd);
                    zzgiVar.zzaxm = Long.valueOf(zzhVar.zzadt);
                    zzgiVar.zzafx = zzhVar.zzafx;
                    zzgiVar.zzawj = zzhVar.zzagk;
                    zzgiVar.zzaxq = zzhVar.zzagf == 0 ? null : Long.valueOf(zzhVar.zzagf);
                    Pair<String, Boolean> zzby = this.zzadj.zzgp().zzby(zzhVar.packageName);
                    if (zzby != null && !TextUtils.isEmpty((CharSequence) zzby.first)) {
                        if (zzhVar.zzagi) {
                            zzgiVar.zzaxo = (String) zzby.first;
                            zzgiVar.zzaxp = (Boolean) zzby.second;
                        }
                    } else if (!this.zzadj.zzgk().zzl(this.zzadj.getContext()) && zzhVar.zzagj) {
                        String string = Settings.Secure.getString(this.zzadj.getContext().getContentResolver(), "android_id");
                        if (string == null) {
                            this.zzadj.zzgo().zzjg().zzg("null secure ID. appId", zzap.zzbv(zzgiVar.zztt));
                            string = "null";
                        } else if (string.isEmpty()) {
                            this.zzadj.zzgo().zzjg().zzg("empty secure ID. appId", zzap.zzbv(zzgiVar.zztt));
                        }
                        zzgiVar.zzaxx = string;
                    }
                    this.zzadj.zzgk().zzcl();
                    zzgiVar.zzaxk = Build.MODEL;
                    this.zzadj.zzgk().zzcl();
                    zzgiVar.zzaxj = Build.VERSION.RELEASE;
                    zzgiVar.zzaxl = Integer.valueOf((int) this.zzadj.zzgk().zzis());
                    zzgiVar.zzaia = this.zzadj.zzgk().zzit();
                    zzgiVar.zzaxn = null;
                    zzgiVar.zzaxd = null;
                    zzgiVar.zzaxe = null;
                    zzgiVar.zzaxf = null;
                    zzgiVar.zzaxz = Long.valueOf(zzhVar.zzagh);
                    if (this.zzadj.isEnabled() && zzn.zzhz()) {
                        zzgiVar.zzaya = null;
                    }
                    zzg zzbl2 = zzjq().zzbl(zzhVar.packageName);
                    if (zzbl2 == null) {
                        zzbl2 = new zzg(this.zzadj, zzhVar.packageName);
                        zzbl2.zzam(this.zzadj.zzgm().zzmf());
                        zzbl2.zzaq(zzhVar.zzafz);
                        zzbl2.zzan(zzhVar.zzafx);
                        zzbl2.zzap(this.zzadj.zzgp().zzbz(zzhVar.packageName));
                        zzbl2.zzx(0L);
                        zzbl2.zzs(0L);
                        zzbl2.zzt(0L);
                        zzbl2.setAppVersion(zzhVar.zzts);
                        zzbl2.zzu(zzhVar.zzagd);
                        zzbl2.zzar(zzhVar.zzage);
                        zzbl2.zzv(zzhVar.zzadt);
                        zzbl2.zzw(zzhVar.zzagf);
                        zzbl2.setMeasurementEnabled(zzhVar.zzagg);
                        zzbl2.zzag(zzhVar.zzagh);
                        zzjq().zza(zzbl2);
                    }
                    zzgiVar.zzafw = zzbl2.getAppInstanceId();
                    zzgiVar.zzafz = zzbl2.getFirebaseInstanceId();
                    List<zzfj> zzbk = zzjq().zzbk(zzhVar.packageName);
                    zzgiVar.zzaxc = new zzgl[zzbk.size()];
                    for (int i = 0; i < zzbk.size(); i++) {
                        zzgl zzglVar = new zzgl();
                        zzgiVar.zzaxc[i] = zzglVar;
                        zzglVar.name = zzbk.get(i).name;
                        zzglVar.zzayl = Long.valueOf(zzbk.get(i).zzaue);
                        zzjo().zza(zzglVar, zzbk.get(i).value);
                    }
                    try {
                        long zza4 = zzjq().zza(zzgiVar);
                        zzq zzjq = zzjq();
                        if (zzyVar.zzaid != null) {
                            Iterator<String> it = zzyVar.zzaid.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if ("_r".equals(it.next())) {
                                        break;
                                    }
                                } else {
                                    boolean zzp = zzln().zzp(zzyVar.zztt, zzyVar.name);
                                    zzr zza5 = zzjq().zza(zzls(), zzyVar.zztt, false, false, false, false, false);
                                    if (zzp && zza5.zzahu < this.zzadj.zzgq().zzat(zzyVar.zztt)) {
                                    }
                                }
                            }
                            z2 = true;
                        }
                        if (zzjq.zza(zzyVar, zza4, z2)) {
                            this.zzatl = 0L;
                        }
                    } catch (IOException e) {
                        this.zzadj.zzgo().zzjd().zze("Data loss. Failed to insert raw event metadata. appId", zzap.zzbv(zzgiVar.zztt), e);
                    }
                    zzjq().setTransactionSuccessful();
                    if (this.zzadj.zzgo().isLoggable(2)) {
                        this.zzadj.zzgo().zzjl().zzg("Event recorded", this.zzadj.zzgl().zza(zzyVar));
                    }
                    zzjq().endTransaction();
                    zzlv();
                    this.zzadj.zzgo().zzjl().zzg("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                } finally {
                    zzjq().endTransaction();
                }
            }
        }
    }

    private final boolean zza(String str, zzad zzadVar) {
        long longValue;
        zzfj zzfjVar;
        String string = zzadVar.zzaid.getString(FirebaseAnalytics.Param.CURRENCY);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzadVar.name)) {
            double doubleValue = zzadVar.zzaid.zzbq(FirebaseAnalytics.Param.VALUE).doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                double longValue2 = zzadVar.zzaid.getLong(FirebaseAnalytics.Param.VALUE).longValue();
                Double.isNaN(longValue2);
                doubleValue = longValue2 * 1000000.0d;
            }
            if (doubleValue <= 9.223372036854776E18d && doubleValue >= -9.223372036854776E18d) {
                longValue = Math.round(doubleValue);
            } else {
                this.zzadj.zzgo().zzjg().zze("Data lost. Currency value is too big. appId", zzap.zzbv(str), Double.valueOf(doubleValue));
                return false;
            }
        } else {
            longValue = zzadVar.zzaid.getLong(FirebaseAnalytics.Param.VALUE).longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String upperCase = string.toUpperCase(Locale.US);
            if (upperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                String valueOf2 = String.valueOf(upperCase);
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                zzfj zzi = zzjq().zzi(str, concat);
                if (zzi == null || !(zzi.value instanceof Long)) {
                    zzq zzjq = zzjq();
                    int zzb = this.zzadj.zzgq().zzb(str, zzaf.zzakh) - 1;
                    Preconditions.checkNotEmpty(str);
                    zzjq.zzaf();
                    zzjq.zzcl();
                    try {
                        zzjq.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                    } catch (SQLiteException e) {
                        zzjq.zzgo().zzjd().zze("Error pruning currencies. appId", zzap.zzbv(str), e);
                    }
                    zzfjVar = new zzfj(str, zzadVar.origin, concat, this.zzadj.zzbx().currentTimeMillis(), Long.valueOf(longValue));
                } else {
                    zzfjVar = new zzfj(str, zzadVar.origin, concat, this.zzadj.zzbx().currentTimeMillis(), Long.valueOf(((Long) zzi.value).longValue() + longValue));
                }
                if (!zzjq().zza(zzfjVar)) {
                    this.zzadj.zzgo().zzjd().zzd("Too many unique user properties are set. Ignoring user property. appId", zzap.zzbv(str), this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                    this.zzadj.zzgm().zza(str, 9, (String) null, (String) null, 0);
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzlt() {
        zzg zzbl;
        String str;
        zzaf();
        zzlr();
        this.zzatr = true;
        try {
            this.zzadj.zzgr();
            Boolean zzle = this.zzadj.zzgg().zzle();
            if (zzle == null) {
                this.zzadj.zzgo().zzjg().zzbx("Upload data called on the client side before use of service was decided");
            } else if (zzle.booleanValue()) {
                this.zzadj.zzgo().zzjd().zzbx("Upload called in the client side when service should be used");
            } else if (this.zzatl > 0) {
                zzlv();
            } else {
                zzaf();
                if (this.zzatu != null) {
                    this.zzadj.zzgo().zzjl().zzbx("Uploading requested multiple times");
                } else if (!zzlo().zzfb()) {
                    this.zzadj.zzgo().zzjl().zzbx("Network not connected, ignoring upload request");
                    zzlv();
                } else {
                    long currentTimeMillis = this.zzadj.zzbx().currentTimeMillis();
                    zzd((String) null, currentTimeMillis - zzn.zzhx());
                    long j = this.zzadj.zzgp().zzane.get();
                    if (j != 0) {
                        this.zzadj.zzgo().zzjk().zzg("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
                    }
                    String zzid = zzjq().zzid();
                    if (!TextUtils.isEmpty(zzid)) {
                        if (this.zzatw == -1) {
                            this.zzatw = zzjq().zzik();
                        }
                        List<Pair<zzgi, Long>> zzb = zzjq().zzb(zzid, this.zzadj.zzgq().zzb(zzid, zzaf.zzajj), Math.max(0, this.zzadj.zzgq().zzb(zzid, zzaf.zzajk)));
                        if (!zzb.isEmpty()) {
                            Iterator<Pair<zzgi, Long>> it = zzb.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    str = null;
                                    break;
                                }
                                zzgi zzgiVar = (zzgi) it.next().first;
                                if (!TextUtils.isEmpty(zzgiVar.zzaxo)) {
                                    str = zzgiVar.zzaxo;
                                    break;
                                }
                            }
                            if (str != null) {
                                int i = 0;
                                while (true) {
                                    if (i >= zzb.size()) {
                                        break;
                                    }
                                    zzgi zzgiVar2 = (zzgi) zzb.get(i).first;
                                    if (!TextUtils.isEmpty(zzgiVar2.zzaxo) && !zzgiVar2.zzaxo.equals(str)) {
                                        zzb = zzb.subList(0, i);
                                        break;
                                    }
                                    i++;
                                }
                            }
                            zzgh zzghVar = new zzgh();
                            zzghVar.zzawy = new zzgi[zzb.size()];
                            ArrayList arrayList = new ArrayList(zzb.size());
                            boolean z = zzn.zzhz() && this.zzadj.zzgq().zzav(zzid);
                            for (int i2 = 0; i2 < zzghVar.zzawy.length; i2++) {
                                zzghVar.zzawy[i2] = (zzgi) zzb.get(i2).first;
                                arrayList.add((Long) zzb.get(i2).second);
                                zzghVar.zzawy[i2].zzaxn = Long.valueOf(this.zzadj.zzgq().zzhc());
                                zzghVar.zzawy[i2].zzaxd = Long.valueOf(currentTimeMillis);
                                zzgi zzgiVar3 = zzghVar.zzawy[i2];
                                this.zzadj.zzgr();
                                zzgiVar3.zzaxs = false;
                                if (!z) {
                                    zzghVar.zzawy[i2].zzaya = null;
                                }
                            }
                            String zzb2 = this.zzadj.zzgo().isLoggable(2) ? zzjo().zzb(zzghVar) : null;
                            byte[] zza2 = zzjo().zza(zzghVar);
                            String str2 = zzaf.zzajt.get();
                            try {
                                URL url = new URL(str2);
                                Preconditions.checkArgument(!arrayList.isEmpty());
                                if (this.zzatu != null) {
                                    this.zzadj.zzgo().zzjd().zzbx("Set uploading progress before finishing the previous upload");
                                } else {
                                    this.zzatu = new ArrayList(arrayList);
                                }
                                this.zzadj.zzgp().zzanf.set(currentTimeMillis);
                                this.zzadj.zzgo().zzjl().zzd("Uploading data. app, uncompressed size, data", zzghVar.zzawy.length > 0 ? zzghVar.zzawy[0].zztt : "?", Integer.valueOf(zza2.length), zzb2);
                                this.zzatq = true;
                                zzat zzlo = zzlo();
                                zzfc zzfcVar = new zzfc(this, zzid);
                                zzlo.zzaf();
                                zzlo.zzcl();
                                Preconditions.checkNotNull(url);
                                Preconditions.checkNotNull(zza2);
                                Preconditions.checkNotNull(zzfcVar);
                                zzlo.zzgn().zzd(new zzax(zzlo, zzid, url, zza2, null, zzfcVar));
                            } catch (MalformedURLException unused) {
                                this.zzadj.zzgo().zzjd().zze("Failed to parse upload URL. Not uploading. appId", zzap.zzbv(zzid), str2);
                            }
                        }
                    } else {
                        this.zzatw = -1L;
                        String zzah = zzjq().zzah(currentTimeMillis - zzn.zzhx());
                        if (!TextUtils.isEmpty(zzah) && (zzbl = zzjq().zzbl(zzah)) != null) {
                            zzb(zzbl);
                        }
                    }
                }
            }
        } finally {
            this.zzatr = false;
            zzlw();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:(1:8)|(7:10|(2:517|518)(1:12)|13|(1:15)(1:516)|16|17|(5:(1:20)|21|(2:26|(29:28|(4:31|(6:33|(4:38|(1:42)|43|44)|46|(2:40|42)|43|44)(20:47|(2:49|(2:51|(5:53|(3:130|57|(1:60)(7:61|62|63|(1:126)(7:66|(4:69|(2:71|72)(2:74|(2:76|77)(1:78))|73|67)|79|80|(2:82|(4:87|(1:89)(3:108|(4:114|(3:117|(2:120|121)(1:119)|115)|122|123)|113)|(1:91)|92)(1:86))|125|92)|(3:96|(2:101|(1:103)(1:104))|105)|106|107))|56|57|(0)(0))(5:131|(3:133|57|(0)(0))|56|57|(0)(0)))(5:134|(3:136|57|(0)(0))|56|57|(0)(0)))|137|(1:139)|140|(3:142|(2:144|145)(2:147|(2:149|150)(1:151))|146)|152|(1:155)|(1:157)|158|(2:160|(2:161|(1:199)(2:163|(6:166|167|(1:169)|170|(1:172)|173)(1:165))))(1:200)|174|(11:179|(3:181|(2:183|184)(2:186|(2:188|189)(1:190))|185)|191|192|(1:(1:197)(1:198))(1:195)|63|(0)|126|(4:94|96|(3:98|101|(0)(0))|105)|106|107)|62|63|(0)|126|(0)|106|107)|45|29)|201|202|(1:204)|(8:206|(6:211|212|(2:213|(2:215|(2:218|219)(1:217))(2:225|226))|(1:221)|222|(1:224))|227|212|(3:213|(0)(0)|217)|(0)|222|(0))|228|(9:311|312|(3:314|(6:316|(1:318)|319|(6:321|(1:323)(1:334)|324|(1:328)|329|330)(1:335)|331|332)(8:337|338|(1:426)(3:341|342|(1:(2:344|(3:347|348|(1:352))(1:346))(1:425)))|424|(1:354)(1:415)|(1:356)(7:359|(1:363)|364|(1:366)(1:414)|367|368|(3:370|371|(1:379))(2:380|(5:382|(1:384)|385|386|387)(4:388|389|(3:391|(2:393|394)(1:410)|395)(3:411|(2:413|397)|409)|(4:399|(1:401)|402|403)(2:404|(2:406|407)(1:408)))))|357|358)|333)|427|428|(1:430)|431|(2:434|432)|435)(1:230)|231|232|233|(6:236|(1:238)|239|(2:241|242)(1:244)|243|234)|251|252|253|(2:255|256)(2:292|(7:294|(1:296)(1:306)|297|(1:299)(1:305)|300|(1:302)(1:304)|303))|257|(5:259|(2:264|265)|266|(1:268)(1:269)|265)|270|(3:(2:274|275)(1:277)|276|271)|278|279|(1:281)|282|283|284|285|286|287)(4:436|437|438|439))|440|(0)(0))(4:441|442|443|444))(7:522|(1:524)(1:536)|525|(1:527)(1:535)|528|529|(5:(1:532)|21|(3:23|26|(0)(0))|440|(0)(0))(2:533|534))|445|446|448|449|(2:451|(1:453))(11:454|455|456|457|(1:459)|460|(1:462)(1:500)|463|464|465|(2:467|(1:469))(1:(7:470|471|472|473|(2:480|(1:482))|475|(2:477|(1:479)))))|21|(0)|440|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x024a, code lost:
        r8 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x024e, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x024f, code lost:
        r7 = r3;
        r8 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x09fc, code lost:
        if (r18 != r7) goto L397;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0245, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0246, code lost:
        r7 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0249, code lost:
        r0 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0276 A[Catch: all -> 0x0cb0, TRY_ENTER, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x027d A[Catch: all -> 0x0cb0, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x028b A[Catch: all -> 0x0cb0, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0592 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0645 A[Catch: all -> 0x0cb0, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0666 A[Catch: all -> 0x0cb0, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:272:0x067e A[Catch: all -> 0x0cb0, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x073e A[Catch: all -> 0x0cb0, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0758 A[Catch: all -> 0x0cb0, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0778 A[Catch: all -> 0x0cb0, TryCatch #10 {all -> 0x0cb0, blocks: (B:3:0x0009, B:26:0x0088, B:113:0x0279, B:115:0x027d, B:121:0x028b, B:122:0x02a8, B:124:0x02b2, B:126:0x02ca, B:128:0x02fb, B:134:0x030f, B:136:0x0319, B:275:0x06a5, B:138:0x0337, B:140:0x0347, B:157:0x0382, B:218:0x0582, B:221:0x0594, B:222:0x0599, B:224:0x059c, B:230:0x05b7, B:227:0x05aa, B:233:0x05bd, B:235:0x05c3, B:237:0x05c9, B:258:0x061e, B:259:0x063f, B:262:0x0645, B:264:0x064f, B:266:0x0653, B:269:0x0659, B:271:0x0666, B:272:0x067e, B:273:0x0685, B:274:0x069c, B:242:0x05f2, B:244:0x05f8, B:248:0x0601, B:250:0x0607, B:253:0x0612, B:147:0x0363, B:150:0x036d, B:153:0x0377, B:164:0x0391, B:166:0x0395, B:167:0x039a, B:169:0x03a4, B:171:0x03b4, B:175:0x03cf, B:172:0x03bd, B:174:0x03c7, B:178:0x03da, B:180:0x041a, B:181:0x0458, B:184:0x048c, B:186:0x0491, B:188:0x049f, B:190:0x04a8, B:191:0x04ae, B:193:0x04b1, B:194:0x04ba, B:195:0x04bd, B:197:0x04c1, B:200:0x04cb, B:202:0x04fe, B:204:0x051d, B:210:0x053c, B:207:0x0531, B:214:0x054b, B:216:0x055e, B:217:0x056b, B:276:0x06ac, B:278:0x06b4, B:280:0x06c0, B:282:0x06ce, B:285:0x06d3, B:287:0x0719, B:288:0x0739, B:290:0x073e, B:292:0x074c, B:296:0x0758, B:299:0x0778, B:293:0x0752, B:286:0x06fc, B:300:0x0790, B:306:0x07d3, B:308:0x07e6, B:309:0x07f5, B:311:0x07f9, B:313:0x0803, B:315:0x0817, B:317:0x081b, B:319:0x0823, B:320:0x0834, B:330:0x087e, B:332:0x0888, B:334:0x0894, B:336:0x0898, B:351:0x08c8, B:354:0x08da, B:358:0x0902, B:360:0x0912, B:369:0x0965, B:371:0x096d, B:373:0x0971, B:375:0x0975, B:377:0x0979, B:381:0x098b, B:383:0x09a9, B:384:0x09b2, B:391:0x09de, B:338:0x08a0, B:340:0x08a4, B:342:0x08ac, B:344:0x08b0, B:347:0x08ba, B:54:0x0136, B:70:0x01cc, B:77:0x01ff, B:85:0x021f, B:112:0x0276, B:95:0x0241, B:45:0x00ea, B:57:0x0149), top: B:511:0x0009, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0c92  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0ca9 A[Catch: all -> 0x0cad, TRY_ENTER, TryCatch #6 {all -> 0x0cad, blocks: (B:436:0x0b22, B:452:0x0b98, B:454:0x0b9d, B:456:0x0bb0, B:459:0x0bb5, B:464:0x0be4, B:460:0x0bba, B:462:0x0bc4, B:463:0x0bcd, B:465:0x0bed, B:466:0x0c04, B:469:0x0c0c, B:470:0x0c11, B:471:0x0c21, B:473:0x0c3b, B:474:0x0c54, B:475:0x0c5c, B:480:0x0c7e, B:479:0x0c6d, B:437:0x0b3a, B:439:0x0b41, B:441:0x0b4b, B:443:0x0b51, B:449:0x0b63, B:451:0x0b69, B:486:0x0c93, B:492:0x0ca9, B:495:0x0caf), top: B:504:0x0023, inners: #16 }] */
    /* JADX WARN: Removed duplicated region for block: B:544:0x0755 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v51 */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zzd(java.lang.String r63, long r64) {
        /*
            Method dump skipped, instructions count: 3270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zzd(java.lang.String, long):boolean");
    }

    @VisibleForTesting
    private static zzgg[] zza(zzgg[] zzggVarArr, @NonNull String str) {
        int i = 0;
        while (true) {
            if (i >= zzggVarArr.length) {
                i = -1;
                break;
            } else if (str.equals(zzggVarArr[i].name)) {
                break;
            } else {
                i++;
            }
        }
        return i < 0 ? zzggVarArr : zza(zzggVarArr, i);
    }

    @VisibleForTesting
    private static zzgg[] zza(zzgg[] zzggVarArr, int i) {
        zzgg[] zzggVarArr2 = new zzgg[zzggVarArr.length - 1];
        if (i > 0) {
            System.arraycopy(zzggVarArr, 0, zzggVarArr2, 0, i);
        }
        if (i < zzggVarArr2.length) {
            System.arraycopy(zzggVarArr, i + 1, zzggVarArr2, i, zzggVarArr2.length - i);
        }
        return zzggVarArr2;
    }

    @VisibleForTesting
    private static zzgg[] zza(zzgg[] zzggVarArr, int i, String str) {
        for (zzgg zzggVar : zzggVarArr) {
            if ("_err".equals(zzggVar.name)) {
                return zzggVarArr;
            }
        }
        zzgg[] zzggVarArr2 = new zzgg[zzggVarArr.length + 2];
        System.arraycopy(zzggVarArr, 0, zzggVarArr2, 0, zzggVarArr.length);
        zzgg zzggVar2 = new zzgg();
        zzggVar2.name = "_err";
        zzggVar2.zzawx = Long.valueOf(i);
        zzgg zzggVar3 = new zzgg();
        zzggVar3.name = "_ev";
        zzggVar3.zzamp = str;
        zzggVarArr2[zzggVarArr2.length - 2] = zzggVar2;
        zzggVarArr2[zzggVarArr2.length - 1] = zzggVar3;
        return zzggVarArr2;
    }

    private final zzgd[] zza(String str, zzgl[] zzglVarArr, zzgf[] zzgfVarArr) {
        Preconditions.checkNotEmpty(str);
        return zzjp().zza(str, zzgfVarArr, zzglVarArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x015c, code lost:
        r9.zzadj.zzgp().zzang.set(r9.zzadj.zzbx().currentTimeMillis());
     */
    @android.support.annotation.WorkerThread
    @com.google.android.gms.common.util.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(int r10, java.lang.Throwable r11, byte[] r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zza(int, java.lang.Throwable, byte[], java.lang.String):void");
    }

    private final boolean zzlu() {
        zzaf();
        zzlr();
        return zzjq().zzii() || !TextUtils.isEmpty(zzjq().zzid());
    }

    @WorkerThread
    private final void zzb(zzg zzgVar) {
        ArrayMap arrayMap;
        zzaf();
        if (TextUtils.isEmpty(zzgVar.getGmpAppId()) && (!zzn.zzic() || TextUtils.isEmpty(zzgVar.zzgw()))) {
            zzb(zzgVar.zzal(), 204, null, null, null);
            return;
        }
        zzn zzgq = this.zzadj.zzgq();
        Uri.Builder builder = new Uri.Builder();
        String gmpAppId = zzgVar.getGmpAppId();
        if (TextUtils.isEmpty(gmpAppId) && zzn.zzic()) {
            gmpAppId = zzgVar.zzgw();
        }
        Uri.Builder encodedAuthority = builder.scheme(zzaf.zzajh.get()).encodedAuthority(zzaf.zzaji.get());
        String valueOf = String.valueOf(gmpAppId);
        encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", zzgVar.getAppInstanceId()).appendQueryParameter("platform", AbstractSpiCall.ANDROID_CLIENT_TYPE).appendQueryParameter("gmp_version", String.valueOf(zzgq.zzhc()));
        String uri = builder.build().toString();
        try {
            URL url = new URL(uri);
            this.zzadj.zzgo().zzjl().zzg("Fetching remote configuration", zzgVar.zzal());
            zzgb zzcf = zzln().zzcf(zzgVar.zzal());
            String zzcg = zzln().zzcg(zzgVar.zzal());
            if (zzcf == null || TextUtils.isEmpty(zzcg)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put("If-Modified-Since", zzcg);
                arrayMap = arrayMap2;
            }
            this.zzatp = true;
            zzat zzlo = zzlo();
            String zzal = zzgVar.zzal();
            zzfd zzfdVar = new zzfd(this);
            zzlo.zzaf();
            zzlo.zzcl();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzfdVar);
            zzlo.zzgn().zzd(new zzax(zzlo, zzal, url, null, arrayMap, zzfdVar));
        } catch (MalformedURLException unused) {
            this.zzadj.zzgo().zzjd().zze("Failed to parse config URL. Not fetching. appId", zzap.zzbv(zzgVar.zzal()), uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b4, code lost:
        r6.zzadj.zzgp().zzang.set(r6.zzadj.zzbx().currentTimeMillis());
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x013e A[Catch: all -> 0x0191, TryCatch #1 {all -> 0x000f, blocks: (B:4:0x000c, B:7:0x0012, B:66:0x0184, B:45:0x00fa, B:52:0x011b, B:8:0x002d, B:17:0x0049, B:65:0x017d, B:22:0x0065, B:29:0x00b4, B:30:0x00c9, B:33:0x00d1, B:36:0x00dd, B:38:0x00e3, B:43:0x00f0, B:55:0x0128, B:57:0x013e, B:59:0x0166, B:61:0x0170, B:63:0x0176, B:64:0x017a, B:58:0x014e, B:48:0x0107, B:50:0x0111), top: B:74:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x014e A[Catch: all -> 0x0191, TryCatch #1 {all -> 0x000f, blocks: (B:4:0x000c, B:7:0x0012, B:66:0x0184, B:45:0x00fa, B:52:0x011b, B:8:0x002d, B:17:0x0049, B:65:0x017d, B:22:0x0065, B:29:0x00b4, B:30:0x00c9, B:33:0x00d1, B:36:0x00dd, B:38:0x00e3, B:43:0x00f0, B:55:0x0128, B:57:0x013e, B:59:0x0166, B:61:0x0170, B:63:0x0176, B:64:0x017a, B:58:0x014e, B:48:0x0107, B:50:0x0111), top: B:74:0x000c }] */
    @android.support.annotation.WorkerThread
    @com.google.android.gms.common.util.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzb(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zzb(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01b1  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzlv() {
        /*
            Method dump skipped, instructions count: 632
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zzlv():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzg(Runnable runnable) {
        zzaf();
        if (this.zzatm == null) {
            this.zzatm = new ArrayList();
        }
        this.zzatm.add(runnable);
    }

    @WorkerThread
    private final void zzlw() {
        zzaf();
        if (this.zzatp || this.zzatq || this.zzatr) {
            this.zzadj.zzgo().zzjl().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzatp), Boolean.valueOf(this.zzatq), Boolean.valueOf(this.zzatr));
            return;
        }
        this.zzadj.zzgo().zzjl().zzbx("Stopping uploading service(s)");
        if (this.zzatm == null) {
            return;
        }
        for (Runnable runnable : this.zzatm) {
            runnable.run();
        }
        this.zzatm.clear();
    }

    @WorkerThread
    private final Boolean zzc(zzg zzgVar) {
        try {
            if (zzgVar.zzha() != -2147483648L) {
                if (zzgVar.zzha() == Wrappers.packageManager(this.zzadj.getContext()).getPackageInfo(zzgVar.zzal(), 0).versionCode) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzadj.getContext()).getPackageInfo(zzgVar.zzal(), 0).versionName;
                if (zzgVar.zzak() != null && zzgVar.zzak().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zzlx() {
        zzaf();
        try {
            this.zzatt = new RandomAccessFile(new File(this.zzadj.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzats = this.zzatt.tryLock();
            if (this.zzats != null) {
                this.zzadj.zzgo().zzjl().zzbx("Storage concurrent access okay");
                return true;
            }
            this.zzadj.zzgo().zzjd().zzbx("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            this.zzadj.zzgo().zzjd().zzg("Failed to acquire storage lock", e);
            return false;
        } catch (IOException e2) {
            this.zzadj.zzgo().zzjd().zzg("Failed to access storage lock file", e2);
            return false;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzaf();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzadj.zzgo().zzjd().zzbx("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0L);
            int read = fileChannel.read(allocate);
            if (read == 4) {
                allocate.flip();
                return allocate.getInt();
            }
            if (read != -1) {
                this.zzadj.zzgo().zzjg().zzg("Unexpected data length. Bytes read", Integer.valueOf(read));
            }
            return 0;
        } catch (IOException e) {
            this.zzadj.zzgo().zzjd().zzg("Failed to read from channel", e);
            return 0;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzaf();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzadj.zzgo().zzjd().zzbx("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0L);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzadj.zzgo().zzjd().zzg("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzadj.zzgo().zzjd().zzg("Failed to write to channel", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzly() {
        zzaf();
        zzlr();
        if (this.zzatk) {
            return;
        }
        this.zzadj.zzgo().zzjj().zzbx("This instance being marked as an uploader");
        zzaf();
        zzlr();
        if (zzlz() && zzlx()) {
            int zza2 = zza(this.zzatt);
            int zzja = this.zzadj.zzgf().zzja();
            zzaf();
            if (zza2 > zzja) {
                this.zzadj.zzgo().zzjd().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzja));
            } else if (zza2 < zzja) {
                if (zza(zzja, this.zzatt)) {
                    this.zzadj.zzgo().zzjl().zze("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzja));
                } else {
                    this.zzadj.zzgo().zzjd().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzja));
                }
            }
        }
        this.zzatk = true;
        zzlv();
    }

    @WorkerThread
    private final boolean zzlz() {
        zzaf();
        zzlr();
        return this.zzatk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zzd(zzh zzhVar) {
        if (this.zzatu != null) {
            this.zzatv = new ArrayList();
            this.zzatv.addAll(this.zzatu);
        }
        zzq zzjq = zzjq();
        String str = zzhVar.packageName;
        Preconditions.checkNotEmpty(str);
        zzjq.zzaf();
        zzjq.zzcl();
        try {
            SQLiteDatabase writableDatabase = zzjq.getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("apps", "app_id=?", strArr) + 0 + writableDatabase.delete("events", "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzjq.zzgo().zzjl().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzjq.zzgo().zzjd().zze("Error resetting analytics data. appId, error", zzap.zzbv(str), e);
        }
        zzh zza2 = zza(this.zzadj.getContext(), zzhVar.packageName, zzhVar.zzafx, zzhVar.zzagg, zzhVar.zzagi, zzhVar.zzagj, zzhVar.zzagx, zzhVar.zzagk);
        if (!this.zzadj.zzgq().zzbd(zzhVar.packageName) || zzhVar.zzagg) {
            zzf(zza2);
        }
    }

    private final zzh zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j, String str3) {
        String str4;
        String str5;
        int i;
        String str6 = "Unknown";
        str4 = "Unknown";
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            this.zzadj.zzgo().zzjd().zzbx("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str6 = packageManager.getInstallerPackageName(str);
        } catch (IllegalArgumentException unused) {
            this.zzadj.zzgo().zzjd().zzg("Error retrieving installer package name. appId", zzap.zzbv(str));
        }
        if (str6 == null) {
            str6 = "manual_install";
        } else if ("com.android.vending".equals(str6)) {
            str6 = "";
        }
        String str7 = str6;
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str);
                str4 = TextUtils.isEmpty(applicationLabel) ? "Unknown" : applicationLabel.toString();
                str5 = packageInfo.versionName;
                i = packageInfo.versionCode;
            } else {
                str5 = "Unknown";
                i = Integer.MIN_VALUE;
            }
            this.zzadj.zzgr();
            return new zzh(str, str2, str5, i, str7, this.zzadj.zzgq().zzhc(), this.zzadj.zzgm().zzd(context, str), (String) null, z, false, "", 0L, this.zzadj.zzgq().zzbe(str) ? j : 0L, 0, z2, z3, false, str3);
        } catch (PackageManager.NameNotFoundException unused2) {
            this.zzadj.zzgo().zzjd().zze("Error retrieving newly installed package info. appId, appName", zzap.zzbv(str), str4);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzfh zzfhVar, zzh zzhVar) {
        zzfj zzi;
        zzaf();
        zzlr();
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
        } else if (this.zzadj.zzgq().zze(zzhVar.packageName, zzaf.zzalj) && "_ap".equals(zzfhVar.name) && (zzi = zzjq().zzi(zzhVar.packageName, "_ap")) != null && "auto".equals(zzfhVar.origin) && !"auto".equals(zzi.origin)) {
            this.zzadj.zzgo().zzjk().zzbx("Not setting lower priority ad personalization property");
        } else {
            int zzcs = this.zzadj.zzgm().zzcs(zzfhVar.name);
            if (zzcs != 0) {
                this.zzadj.zzgm();
                this.zzadj.zzgm().zza(zzhVar.packageName, zzcs, "_ev", zzfk.zza(zzfhVar.name, 24, true), zzfhVar.name != null ? zzfhVar.name.length() : 0);
                return;
            }
            int zzi2 = this.zzadj.zzgm().zzi(zzfhVar.name, zzfhVar.getValue());
            if (zzi2 != 0) {
                this.zzadj.zzgm();
                String zza2 = zzfk.zza(zzfhVar.name, 24, true);
                Object value = zzfhVar.getValue();
                this.zzadj.zzgm().zza(zzhVar.packageName, zzi2, "_ev", zza2, (value == null || !((value instanceof String) || (value instanceof CharSequence))) ? 0 : String.valueOf(value).length());
                return;
            }
            Object zzj = this.zzadj.zzgm().zzj(zzfhVar.name, zzfhVar.getValue());
            if (zzj == null) {
                return;
            }
            zzfj zzfjVar = new zzfj(zzhVar.packageName, zzfhVar.origin, zzfhVar.name, zzfhVar.zzaue, zzj);
            this.zzadj.zzgo().zzjk().zze("Setting user property", this.zzadj.zzgl().zzbu(zzfjVar.name), zzj);
            zzjq().beginTransaction();
            try {
                zzg(zzhVar);
                boolean zza3 = zzjq().zza(zzfjVar);
                zzjq().setTransactionSuccessful();
                if (zza3) {
                    this.zzadj.zzgo().zzjk().zze("User property set", this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                } else {
                    this.zzadj.zzgo().zzjd().zze("Too many unique user properties are set. Ignoring user property", this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                    this.zzadj.zzgm().zza(zzhVar.packageName, 9, (String) null, (String) null, 0);
                }
            } finally {
                zzjq().endTransaction();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzfh zzfhVar, zzh zzhVar) {
        zzfj zzi;
        zzaf();
        zzlr();
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
        } else if (this.zzadj.zzgq().zze(zzhVar.packageName, zzaf.zzalj) && "_ap".equals(zzfhVar.name) && (zzi = zzjq().zzi(zzhVar.packageName, "_ap")) != null && "auto".equals(zzfhVar.origin) && !"auto".equals(zzi.origin)) {
            this.zzadj.zzgo().zzjk().zzbx("Not removing higher priority ad personalization property");
        } else {
            this.zzadj.zzgo().zzjk().zzg("Removing user property", this.zzadj.zzgl().zzbu(zzfhVar.name));
            zzjq().beginTransaction();
            try {
                zzg(zzhVar);
                zzjq().zzh(zzhVar.packageName, zzfhVar.name);
                zzjq().setTransactionSuccessful();
                this.zzadj.zzgo().zzjk().zzg("User property removed", this.zzadj.zzgl().zzbu(zzfhVar.name));
            } finally {
                zzjq().endTransaction();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzez zzezVar) {
        this.zzatn++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzma() {
        this.zzato++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzbt zzmb() {
        return this.zzadj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzf(zzh zzhVar) {
        int i;
        zzz zzg;
        long j;
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        boolean z;
        zzaf();
        zzlr();
        Preconditions.checkNotNull(zzhVar);
        Preconditions.checkNotEmpty(zzhVar.packageName);
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        zzg zzbl = zzjq().zzbl(zzhVar.packageName);
        if (zzbl != null && TextUtils.isEmpty(zzbl.getGmpAppId()) && !TextUtils.isEmpty(zzhVar.zzafx)) {
            zzbl.zzy(0L);
            zzjq().zza(zzbl);
            zzln().zzci(zzhVar.packageName);
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
            return;
        }
        long j2 = zzhVar.zzagx;
        if (j2 == 0) {
            j2 = this.zzadj.zzbx().currentTimeMillis();
        }
        int i2 = zzhVar.zzagy;
        if (i2 == 0 || i2 == 1) {
            i = i2;
        } else {
            this.zzadj.zzgo().zzjg().zze("Incorrect app type, assuming installed app. appId, appType", zzap.zzbv(zzhVar.packageName), Integer.valueOf(i2));
            i = 0;
        }
        zzjq().beginTransaction();
        try {
            zzg zzbl2 = zzjq().zzbl(zzhVar.packageName);
            if (zzbl2 != null) {
                this.zzadj.zzgm();
                if (zzfk.zza(zzhVar.zzafx, zzbl2.getGmpAppId(), zzhVar.zzagk, zzbl2.zzgw())) {
                    this.zzadj.zzgo().zzjg().zzg("New GMP App Id passed in. Removing cached database data. appId", zzap.zzbv(zzbl2.zzal()));
                    zzq zzjq = zzjq();
                    String zzal = zzbl2.zzal();
                    zzjq.zzcl();
                    zzjq.zzaf();
                    Preconditions.checkNotEmpty(zzal);
                    try {
                        SQLiteDatabase writableDatabase = zzjq.getWritableDatabase();
                        String[] strArr = {zzal};
                        int delete = writableDatabase.delete("events", "app_id=?", strArr) + 0 + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr);
                        if (delete > 0) {
                            zzjq.zzgo().zzjl().zze("Deleted application data. app, records", zzal, Integer.valueOf(delete));
                        }
                    } catch (SQLiteException e) {
                        zzjq.zzgo().zzjd().zze("Error deleting application data. appId, error", zzap.zzbv(zzal), e);
                    }
                    zzbl2 = null;
                }
            }
            if (zzbl2 != null) {
                if (zzbl2.zzha() != -2147483648L) {
                    if (zzbl2.zzha() != zzhVar.zzagd) {
                        Bundle bundle = new Bundle();
                        bundle.putString("_pv", zzbl2.zzak());
                        zzc(new zzad("_au", new zzaa(bundle), "auto", j2), zzhVar);
                    }
                } else if (zzbl2.zzak() != null && !zzbl2.zzak().equals(zzhVar.zzts)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("_pv", zzbl2.zzak());
                    zzc(new zzad("_au", new zzaa(bundle2), "auto", j2), zzhVar);
                }
            }
            zzg(zzhVar);
            if (i == 0) {
                zzg = zzjq().zzg(zzhVar.packageName, "_f");
            } else {
                zzg = i == 1 ? zzjq().zzg(zzhVar.packageName, "_v") : null;
            }
            if (zzg == null) {
                long j3 = ((j2 / 3600000) + 1) * 3600000;
                if (i == 0) {
                    j = 1;
                    zzb(new zzfh("_fot", j2, Long.valueOf(j3), "auto"), zzhVar);
                    if (this.zzadj.zzgq().zzbg(zzhVar.zzafx)) {
                        zzaf();
                        this.zzadj.zzkg().zzcd(zzhVar.packageName);
                    }
                    zzaf();
                    zzlr();
                    Bundle bundle3 = new Bundle();
                    bundle3.putLong("_c", 1L);
                    bundle3.putLong("_r", 1L);
                    bundle3.putLong("_uwa", 0L);
                    bundle3.putLong("_pfo", 0L);
                    bundle3.putLong("_sys", 0L);
                    bundle3.putLong("_sysu", 0L);
                    if (this.zzadj.zzgq().zzbd(zzhVar.packageName) && zzhVar.zzagz) {
                        bundle3.putLong("_dac", 1L);
                    }
                    if (this.zzadj.getContext().getPackageManager() == null) {
                        this.zzadj.zzgo().zzjd().zzg("PackageManager is null, first open report might be inaccurate. appId", zzap.zzbv(zzhVar.packageName));
                    } else {
                        try {
                            packageInfo = Wrappers.packageManager(this.zzadj.getContext()).getPackageInfo(zzhVar.packageName, 0);
                        } catch (PackageManager.NameNotFoundException e2) {
                            this.zzadj.zzgo().zzjd().zze("Package info is null, first open report might be inaccurate. appId", zzap.zzbv(zzhVar.packageName), e2);
                            packageInfo = null;
                        }
                        if (packageInfo != null && packageInfo.firstInstallTime != 0) {
                            if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                bundle3.putLong("_uwa", 1L);
                                z = false;
                            } else {
                                z = true;
                            }
                            zzb(new zzfh("_fi", j2, Long.valueOf(z ? 1L : 0L), "auto"), zzhVar);
                        }
                        try {
                            applicationInfo = Wrappers.packageManager(this.zzadj.getContext()).getApplicationInfo(zzhVar.packageName, 0);
                        } catch (PackageManager.NameNotFoundException e3) {
                            this.zzadj.zzgo().zzjd().zze("Application info is null, first open report might be inaccurate. appId", zzap.zzbv(zzhVar.packageName), e3);
                            applicationInfo = null;
                        }
                        if (applicationInfo != null) {
                            if ((applicationInfo.flags & 1) != 0) {
                                bundle3.putLong("_sys", 1L);
                            }
                            if ((applicationInfo.flags & 128) != 0) {
                                bundle3.putLong("_sysu", 1L);
                            }
                        }
                    }
                    zzq zzjq2 = zzjq();
                    String str = zzhVar.packageName;
                    Preconditions.checkNotEmpty(str);
                    zzjq2.zzaf();
                    zzjq2.zzcl();
                    long zzn = zzjq2.zzn(str, "first_open_count");
                    if (zzn >= 0) {
                        bundle3.putLong("_pfo", zzn);
                    }
                    zzc(new zzad("_f", new zzaa(bundle3), "auto", j2), zzhVar);
                } else {
                    j = 1;
                    if (i == 1) {
                        zzb(new zzfh("_fvt", j2, Long.valueOf(j3), "auto"), zzhVar);
                        zzaf();
                        zzlr();
                        Bundle bundle4 = new Bundle();
                        bundle4.putLong("_c", 1L);
                        bundle4.putLong("_r", 1L);
                        if (this.zzadj.zzgq().zzbd(zzhVar.packageName) && zzhVar.zzagz) {
                            bundle4.putLong("_dac", 1L);
                        }
                        zzc(new zzad("_v", new zzaa(bundle4), "auto", j2), zzhVar);
                    }
                }
                Bundle bundle5 = new Bundle();
                bundle5.putLong("_et", j);
                zzc(new zzad("_e", new zzaa(bundle5), "auto", j2), zzhVar);
            } else if (zzhVar.zzagw) {
                zzc(new zzad("_cd", new zzaa(new Bundle()), "auto", j2), zzhVar);
            }
            zzjq().setTransactionSuccessful();
        } finally {
            zzjq().endTransaction();
        }
    }

    @WorkerThread
    private final zzh zzco(String str) {
        zzg zzbl = zzjq().zzbl(str);
        if (zzbl == null || TextUtils.isEmpty(zzbl.zzak())) {
            this.zzadj.zzgo().zzjk().zzg("No app data available; dropping", str);
            return null;
        }
        Boolean zzc = zzc(zzbl);
        if (zzc != null && !zzc.booleanValue()) {
            this.zzadj.zzgo().zzjd().zzg("App version does not match; dropping. appId", zzap.zzbv(str));
            return null;
        }
        return new zzh(str, zzbl.getGmpAppId(), zzbl.zzak(), zzbl.zzha(), zzbl.zzhb(), zzbl.zzhc(), zzbl.zzhd(), (String) null, zzbl.isMeasurementEnabled(), false, zzbl.getFirebaseInstanceId(), zzbl.zzhq(), 0L, 0, zzbl.zzhr(), zzbl.zzhs(), false, zzbl.zzgw());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zze(zzl zzlVar) {
        zzh zzco = zzco(zzlVar.packageName);
        if (zzco != null) {
            zzb(zzlVar, zzco);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzl zzlVar, zzh zzhVar) {
        Preconditions.checkNotNull(zzlVar);
        Preconditions.checkNotEmpty(zzlVar.packageName);
        Preconditions.checkNotNull(zzlVar.origin);
        Preconditions.checkNotNull(zzlVar.zzahb);
        Preconditions.checkNotEmpty(zzlVar.zzahb.name);
        zzaf();
        zzlr();
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
            return;
        }
        zzl zzlVar2 = new zzl(zzlVar);
        boolean z = false;
        zzlVar2.active = false;
        zzjq().beginTransaction();
        try {
            zzl zzj = zzjq().zzj(zzlVar2.packageName, zzlVar2.zzahb.name);
            if (zzj != null && !zzj.origin.equals(zzlVar2.origin)) {
                this.zzadj.zzgo().zzjg().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzadj.zzgl().zzbu(zzlVar2.zzahb.name), zzlVar2.origin, zzj.origin);
            }
            if (zzj != null && zzj.active) {
                zzlVar2.origin = zzj.origin;
                zzlVar2.creationTimestamp = zzj.creationTimestamp;
                zzlVar2.triggerTimeout = zzj.triggerTimeout;
                zzlVar2.triggerEventName = zzj.triggerEventName;
                zzlVar2.zzahd = zzj.zzahd;
                zzlVar2.active = zzj.active;
                zzlVar2.zzahb = new zzfh(zzlVar2.zzahb.name, zzj.zzahb.zzaue, zzlVar2.zzahb.getValue(), zzj.zzahb.origin);
            } else if (TextUtils.isEmpty(zzlVar2.triggerEventName)) {
                zzlVar2.zzahb = new zzfh(zzlVar2.zzahb.name, zzlVar2.creationTimestamp, zzlVar2.zzahb.getValue(), zzlVar2.zzahb.origin);
                zzlVar2.active = true;
                z = true;
            }
            if (zzlVar2.active) {
                zzfh zzfhVar = zzlVar2.zzahb;
                zzfj zzfjVar = new zzfj(zzlVar2.packageName, zzlVar2.origin, zzfhVar.name, zzfhVar.zzaue, zzfhVar.getValue());
                if (zzjq().zza(zzfjVar)) {
                    this.zzadj.zzgo().zzjk().zzd("User property updated immediately", zzlVar2.packageName, this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                } else {
                    this.zzadj.zzgo().zzjd().zzd("(2)Too many active user properties, ignoring", zzap.zzbv(zzlVar2.packageName), this.zzadj.zzgl().zzbu(zzfjVar.name), zzfjVar.value);
                }
                if (z && zzlVar2.zzahd != null) {
                    zzd(new zzad(zzlVar2.zzahd, zzlVar2.creationTimestamp), zzhVar);
                }
            }
            if (zzjq().zza(zzlVar2)) {
                this.zzadj.zzgo().zzjk().zzd("Conditional property added", zzlVar2.packageName, this.zzadj.zzgl().zzbu(zzlVar2.zzahb.name), zzlVar2.zzahb.getValue());
            } else {
                this.zzadj.zzgo().zzjd().zzd("Too many conditional properties, ignoring", zzap.zzbv(zzlVar2.packageName), this.zzadj.zzgl().zzbu(zzlVar2.zzahb.name), zzlVar2.zzahb.getValue());
            }
            zzjq().setTransactionSuccessful();
        } finally {
            zzjq().endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzf(zzl zzlVar) {
        zzh zzco = zzco(zzlVar.packageName);
        if (zzco != null) {
            zzc(zzlVar, zzco);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzl zzlVar, zzh zzhVar) {
        Preconditions.checkNotNull(zzlVar);
        Preconditions.checkNotEmpty(zzlVar.packageName);
        Preconditions.checkNotNull(zzlVar.zzahb);
        Preconditions.checkNotEmpty(zzlVar.zzahb.name);
        zzaf();
        zzlr();
        if (TextUtils.isEmpty(zzhVar.zzafx) && TextUtils.isEmpty(zzhVar.zzagk)) {
            return;
        }
        if (!zzhVar.zzagg) {
            zzg(zzhVar);
            return;
        }
        zzjq().beginTransaction();
        try {
            zzg(zzhVar);
            zzl zzj = zzjq().zzj(zzlVar.packageName, zzlVar.zzahb.name);
            if (zzj != null) {
                this.zzadj.zzgo().zzjk().zze("Removing conditional user property", zzlVar.packageName, this.zzadj.zzgl().zzbu(zzlVar.zzahb.name));
                zzjq().zzk(zzlVar.packageName, zzlVar.zzahb.name);
                if (zzj.active) {
                    zzjq().zzh(zzlVar.packageName, zzlVar.zzahb.name);
                }
                if (zzlVar.zzahe != null) {
                    zzd(this.zzadj.zzgm().zza(zzlVar.packageName, zzlVar.zzahe.name, zzlVar.zzahe.zzaid != null ? zzlVar.zzahe.zzaid.zziv() : null, zzj.origin, zzlVar.zzahe.zzaip, true, false), zzhVar);
                }
            } else {
                this.zzadj.zzgo().zzjg().zze("Conditional user property doesn't exist", zzap.zzbv(zzlVar.packageName), this.zzadj.zzgl().zzbu(zzlVar.zzahb.name));
            }
            zzjq().setTransactionSuccessful();
        } finally {
            zzjq().endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x015a  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzg zzg(com.google.android.gms.measurement.internal.zzh r9) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfa.zzg(com.google.android.gms.measurement.internal.zzh):com.google.android.gms.measurement.internal.zzg");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WorkerThread
    public final byte[] zza(@NonNull zzad zzadVar, @Size(min = 1) String str) {
        zzfj zzfjVar;
        Bundle bundle;
        zzgi zzgiVar;
        zzg zzgVar;
        zzgh zzghVar;
        byte[] bArr;
        long j;
        zzlr();
        zzaf();
        this.zzadj.zzga();
        Preconditions.checkNotNull(zzadVar);
        Preconditions.checkNotEmpty(str);
        zzgh zzghVar2 = new zzgh();
        zzjq().beginTransaction();
        try {
            zzg zzbl = zzjq().zzbl(str);
            if (zzbl == null) {
                this.zzadj.zzgo().zzjk().zzg("Log and bundle not available. package_name", str);
                return new byte[0];
            } else if (!zzbl.isMeasurementEnabled()) {
                this.zzadj.zzgo().zzjk().zzg("Log and bundle disabled. package_name", str);
                return new byte[0];
            } else {
                if (("_iap".equals(zzadVar.name) || FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzadVar.name)) && !zza(str, zzadVar)) {
                    this.zzadj.zzgo().zzjg().zzg("Failed to handle purchase event at single event bundle creation. appId", zzap.zzbv(str));
                }
                boolean zzax = this.zzadj.zzgq().zzax(str);
                Long l = 0L;
                if (zzax && "_e".equals(zzadVar.name)) {
                    if (zzadVar.zzaid != null && zzadVar.zzaid.size() != 0) {
                        if (zzadVar.zzaid.getLong("_et") == null) {
                            this.zzadj.zzgo().zzjg().zzg("The engagement event does not include duration. appId", zzap.zzbv(str));
                        } else {
                            l = zzadVar.zzaid.getLong("_et");
                        }
                    }
                    this.zzadj.zzgo().zzjg().zzg("The engagement event does not contain any parameters. appId", zzap.zzbv(str));
                }
                zzgi zzgiVar2 = new zzgi();
                zzghVar2.zzawy = new zzgi[]{zzgiVar2};
                zzgiVar2.zzaxa = 1;
                zzgiVar2.zzaxi = AbstractSpiCall.ANDROID_CLIENT_TYPE;
                zzgiVar2.zztt = zzbl.zzal();
                zzgiVar2.zzage = zzbl.zzhb();
                zzgiVar2.zzts = zzbl.zzak();
                long zzha = zzbl.zzha();
                zzgiVar2.zzaxu = zzha == -2147483648L ? null : Integer.valueOf((int) zzha);
                zzgiVar2.zzaxm = Long.valueOf(zzbl.zzhc());
                zzgiVar2.zzafx = zzbl.getGmpAppId();
                if (TextUtils.isEmpty(zzgiVar2.zzafx)) {
                    zzgiVar2.zzawj = zzbl.zzgw();
                }
                zzgiVar2.zzaxq = Long.valueOf(zzbl.zzhd());
                if (this.zzadj.isEnabled() && zzn.zzhz() && this.zzadj.zzgq().zzav(zzgiVar2.zztt)) {
                    zzgiVar2.zzaya = null;
                }
                Pair<String, Boolean> zzby = this.zzadj.zzgp().zzby(zzbl.zzal());
                if (zzbl.zzhr() && zzby != null && !TextUtils.isEmpty((CharSequence) zzby.first)) {
                    zzgiVar2.zzaxo = (String) zzby.first;
                    zzgiVar2.zzaxp = (Boolean) zzby.second;
                }
                this.zzadj.zzgk().zzcl();
                zzgiVar2.zzaxk = Build.MODEL;
                this.zzadj.zzgk().zzcl();
                zzgiVar2.zzaxj = Build.VERSION.RELEASE;
                zzgiVar2.zzaxl = Integer.valueOf((int) this.zzadj.zzgk().zzis());
                zzgiVar2.zzaia = this.zzadj.zzgk().zzit();
                zzgiVar2.zzafw = zzbl.getAppInstanceId();
                zzgiVar2.zzafz = zzbl.getFirebaseInstanceId();
                List<zzfj> zzbk = zzjq().zzbk(zzbl.zzal());
                zzgiVar2.zzaxc = new zzgl[zzbk.size()];
                if (zzax) {
                    zzfjVar = zzjq().zzi(zzgiVar2.zztt, "_lte");
                    if (zzfjVar != null && zzfjVar.value != null) {
                        if (l.longValue() > 0) {
                            zzfjVar = new zzfj(zzgiVar2.zztt, "auto", "_lte", this.zzadj.zzbx().currentTimeMillis(), Long.valueOf(((Long) zzfjVar.value).longValue() + l.longValue()));
                        }
                    }
                    zzfjVar = new zzfj(zzgiVar2.zztt, "auto", "_lte", this.zzadj.zzbx().currentTimeMillis(), l);
                } else {
                    zzfjVar = null;
                }
                zzgl zzglVar = null;
                for (int i = 0; i < zzbk.size(); i++) {
                    zzgl zzglVar2 = new zzgl();
                    zzgiVar2.zzaxc[i] = zzglVar2;
                    zzglVar2.name = zzbk.get(i).name;
                    zzglVar2.zzayl = Long.valueOf(zzbk.get(i).zzaue);
                    zzjo().zza(zzglVar2, zzbk.get(i).value);
                    if (zzax && "_lte".equals(zzglVar2.name)) {
                        zzglVar2.zzawx = (Long) zzfjVar.value;
                        zzglVar2.zzayl = Long.valueOf(this.zzadj.zzbx().currentTimeMillis());
                        zzglVar = zzglVar2;
                    }
                }
                if (zzax && zzglVar == null) {
                    zzgl zzglVar3 = new zzgl();
                    zzglVar3.name = "_lte";
                    zzglVar3.zzayl = Long.valueOf(this.zzadj.zzbx().currentTimeMillis());
                    zzglVar3.zzawx = (Long) zzfjVar.value;
                    zzgiVar2.zzaxc = (zzgl[]) Arrays.copyOf(zzgiVar2.zzaxc, zzgiVar2.zzaxc.length + 1);
                    zzgiVar2.zzaxc[zzgiVar2.zzaxc.length - 1] = zzglVar3;
                }
                if (l.longValue() > 0) {
                    zzjq().zza(zzfjVar);
                }
                Bundle zziv = zzadVar.zzaid.zziv();
                if ("_iap".equals(zzadVar.name)) {
                    zziv.putLong("_c", 1L);
                    this.zzadj.zzgo().zzjk().zzbx("Marking in-app purchase as real-time");
                    zziv.putLong("_r", 1L);
                }
                zziv.putString("_o", zzadVar.origin);
                if (this.zzadj.zzgm().zzcw(zzgiVar2.zztt)) {
                    this.zzadj.zzgm().zza(zziv, "_dbg", (Object) 1L);
                    this.zzadj.zzgm().zza(zziv, "_r", (Object) 1L);
                }
                zzz zzg = zzjq().zzg(str, zzadVar.name);
                if (zzg == null) {
                    bundle = zziv;
                    bArr = null;
                    zzgiVar = zzgiVar2;
                    zzgVar = zzbl;
                    zzghVar = zzghVar2;
                    zzjq().zza(new zzz(str, zzadVar.name, 1L, 0L, zzadVar.zzaip, 0L, null, null, null, null));
                    j = 0;
                } else {
                    bundle = zziv;
                    zzgiVar = zzgiVar2;
                    zzgVar = zzbl;
                    zzghVar = zzghVar2;
                    bArr = null;
                    long j2 = zzg.zzaig;
                    zzjq().zza(zzg.zzai(zzadVar.zzaip).zziu());
                    j = j2;
                }
                zzy zzyVar = new zzy(this.zzadj, zzadVar.origin, str, zzadVar.name, zzadVar.zzaip, j, bundle);
                zzgf zzgfVar = new zzgf();
                zzgi zzgiVar3 = zzgiVar;
                zzgiVar3.zzaxb = new zzgf[]{zzgfVar};
                zzgfVar.zzawu = Long.valueOf(zzyVar.timestamp);
                zzgfVar.name = zzyVar.name;
                zzgfVar.zzawv = Long.valueOf(zzyVar.zzaic);
                zzgfVar.zzawt = new zzgg[zzyVar.zzaid.size()];
                Iterator<String> it = zzyVar.zzaid.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String next = it.next();
                    zzgg zzggVar = new zzgg();
                    zzgfVar.zzawt[i2] = zzggVar;
                    zzggVar.name = next;
                    zzjo().zza(zzggVar, zzyVar.zzaid.get(next));
                    i2++;
                }
                zzgiVar3.zzaxt = zza(zzgVar.zzal(), zzgiVar3.zzaxc, zzgiVar3.zzaxb);
                zzgiVar3.zzaxe = zzgfVar.zzawu;
                zzgiVar3.zzaxf = zzgfVar.zzawu;
                long zzgz = zzgVar.zzgz();
                zzgiVar3.zzaxh = zzgz != 0 ? Long.valueOf(zzgz) : bArr;
                long zzgy = zzgVar.zzgy();
                if (zzgy != 0) {
                    zzgz = zzgy;
                }
                zzgiVar3.zzaxg = zzgz != 0 ? Long.valueOf(zzgz) : bArr;
                zzgVar.zzhh();
                zzgiVar3.zzaxr = Integer.valueOf((int) zzgVar.zzhe());
                zzgiVar3.zzaxn = Long.valueOf(this.zzadj.zzgq().zzhc());
                zzgiVar3.zzaxd = Long.valueOf(this.zzadj.zzbx().currentTimeMillis());
                zzgiVar3.zzaxs = Boolean.TRUE;
                zzg zzgVar2 = zzgVar;
                zzgVar2.zzs(zzgiVar3.zzaxe.longValue());
                zzgVar2.zzt(zzgiVar3.zzaxf.longValue());
                zzjq().zza(zzgVar2);
                zzjq().setTransactionSuccessful();
                try {
                    byte[] bArr2 = new byte[zzghVar.zzvu()];
                    zzyy zzk = zzyy.zzk(bArr2, 0, bArr2.length);
                    zzghVar.zza(zzk);
                    zzk.zzyt();
                    return zzjo().zzb(bArr2);
                } catch (IOException e) {
                    this.zzadj.zzgo().zzjd().zze("Data loss. Failed to bundle and serialize. appId", zzap.zzbv(str), e);
                    return bArr;
                }
            }
        } finally {
            zzjq().endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzh(zzh zzhVar) {
        try {
            return (String) this.zzadj.zzgn().zzb(new zzfe(this, zzhVar)).get(30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzadj.zzgo().zzjd().zze("Failed to get app instance id. appId", zzap.zzbv(zzhVar.packageName), e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzo(boolean z) {
        zzlv();
    }
}
