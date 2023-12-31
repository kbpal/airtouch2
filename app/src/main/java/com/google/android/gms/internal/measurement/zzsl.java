package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public abstract class zzsl<T> {
    private final zzsv zzbrb;
    final String zzbrc;
    private final String zzbrd;
    private final T zzbre;
    private T zzbrf;
    private volatile zzsi zzbrg;
    private volatile SharedPreferences zzbrh;
    private static final Object zzbqy = new Object();
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzri = null;
    private static boolean zzbqz = false;
    private static volatile Boolean zzbra = null;

    public static void init(Context context) {
        Context applicationContext;
        synchronized (zzbqy) {
            if ((Build.VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) && (applicationContext = context.getApplicationContext()) != null) {
                context = applicationContext;
            }
            if (zzri != context) {
                zzbra = null;
            }
            zzri = context;
        }
        zzbqz = false;
    }

    protected abstract T zzfj(String str);

    private zzsl(zzsv zzsvVar, String str, T t) {
        Uri uri;
        String str2;
        String str3;
        this.zzbrf = null;
        this.zzbrg = null;
        this.zzbrh = null;
        uri = zzsvVar.zzbrn;
        if (uri == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzbrb = zzsvVar;
        str2 = zzsvVar.zzbro;
        String valueOf = String.valueOf(str2);
        String valueOf2 = String.valueOf(str);
        this.zzbrd = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        str3 = zzsvVar.zzbrp;
        String valueOf3 = String.valueOf(str3);
        String valueOf4 = String.valueOf(str);
        this.zzbrc = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        this.zzbre = t;
    }

    public final T getDefaultValue() {
        return this.zzbre;
    }

    public final T get() {
        if (zzri == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        zzsv zzsvVar = this.zzbrb;
        T zzte = zzte();
        if (zzte != null) {
            return zzte;
        }
        T zztf = zztf();
        return zztf != null ? zztf : this.zzbre;
    }

    @Nullable
    @TargetApi(24)
    private final T zzte() {
        Uri uri;
        String str;
        zzsv zzsvVar = this.zzbrb;
        if (!zzd("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            uri = this.zzbrb.zzbrn;
            if (uri != null) {
                zzsi zztg = zztg();
                if (zztg == null || (str = (String) zza(new zzsu(this, zztg) { // from class: com.google.android.gms.internal.measurement.zzsm
                    private final zzsl zzbri;
                    private final zzsi zzbrj;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zzbri = this;
                        this.zzbrj = zztg;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzsu
                    public final Object zztj() {
                        return this.zzbrj.zzsz().get(this.zzbri.zzbrc);
                    }
                })) == null) {
                    return null;
                }
                return zzfj(str);
            }
            zzsv zzsvVar2 = this.zzbrb;
            return null;
        }
        String valueOf = String.valueOf(this.zzbrc);
        Log.w("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
        return null;
    }

    @Nullable
    private final T zztf() {
        zzsv zzsvVar = this.zzbrb;
        if (zzth()) {
            try {
                String str = (String) zza(new zzsu(this) { // from class: com.google.android.gms.internal.measurement.zzsn
                    private final zzsl zzbri;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zzbri = this;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzsu
                    public final Object zztj() {
                        return this.zzbri.zzti();
                    }
                });
                if (str != null) {
                    return zzfj(str);
                }
                return null;
            } catch (SecurityException e) {
                String valueOf = String.valueOf(this.zzbrc);
                Log.e("PhenotypeFlag", valueOf.length() != 0 ? "Unable to read GServices for flag: ".concat(valueOf) : new String("Unable to read GServices for flag: "), e);
                return null;
            }
        }
        return null;
    }

    private static <V> V zza(zzsu<V> zzsuVar) {
        try {
            return zzsuVar.zztj();
        } catch (SecurityException unused) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return zzsuVar.zztj();
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    private final zzsi zztg() {
        Uri uri;
        if (this.zzbrg == null) {
            try {
                ContentResolver contentResolver = zzri.getContentResolver();
                uri = this.zzbrb.zzbrn;
                this.zzbrg = zzsi.zza(contentResolver, uri);
            } catch (SecurityException unused) {
            }
        }
        return this.zzbrg;
    }

    public static boolean zzd(String str, boolean z) {
        try {
            if (zzth()) {
                return ((Boolean) zza(new zzsu(str, false) { // from class: com.google.android.gms.internal.measurement.zzso
                    private final String zzbrk;
                    private final boolean zzbrl = false;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zzbrk = str;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzsu
                    public final Object zztj() {
                        Boolean valueOf;
                        valueOf = Boolean.valueOf(zzsg.zza(zzsl.zzri.getContentResolver(), this.zzbrk, this.zzbrl));
                        return valueOf;
                    }
                })).booleanValue();
            }
            return false;
        } catch (SecurityException e) {
            Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", e);
            return false;
        }
    }

    private static boolean zzth() {
        if (zzbra == null) {
            if (zzri == null) {
                return false;
            }
            zzbra = Boolean.valueOf(PermissionChecker.checkSelfPermission(zzri, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return zzbra.booleanValue();
    }

    public static zzsl<Long> zza(zzsv zzsvVar, String str, long j) {
        return new zzsp(zzsvVar, str, Long.valueOf(j));
    }

    public static zzsl<Integer> zza(zzsv zzsvVar, String str, int i) {
        return new zzsq(zzsvVar, str, Integer.valueOf(i));
    }

    public static zzsl<Boolean> zza(zzsv zzsvVar, String str, boolean z) {
        return new zzsr(zzsvVar, str, Boolean.valueOf(z));
    }

    public static zzsl<Double> zza(zzsv zzsvVar, String str, double d) {
        return new zzss(zzsvVar, str, Double.valueOf(d));
    }

    public static zzsl<String> zza(zzsv zzsvVar, String str, String str2) {
        return new zzst(zzsvVar, str, str2);
    }

    public final /* synthetic */ String zzti() {
        return zzsg.zza(zzri.getContentResolver(), this.zzbrd, (String) null);
    }

    public /* synthetic */ zzsl(zzsv zzsvVar, String str, Object obj, zzsp zzspVar) {
        this(zzsvVar, str, obj);
    }
}
