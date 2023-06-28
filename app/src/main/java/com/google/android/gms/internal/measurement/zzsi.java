package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.GuardedBy;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzsi {
    private static final Object zzbqp = new Object();
    @GuardedBy("loadersLock")
    private static final Map<Uri, zzsi> zzbqq = new HashMap();
    private static final String[] zzbqw = {"key", FirebaseAnalytics.Param.VALUE};
    private final Uri uri;
    private final ContentResolver zzbqr;
    private volatile Map<String, String> zzbqt;
    private final Object zzbqs = new Object();
    private final Object zzbqu = new Object();
    @GuardedBy("listenersLock")
    private final List<zzsk> zzbqv = new ArrayList();

    private zzsi(ContentResolver contentResolver, Uri uri) {
        this.zzbqr = contentResolver;
        this.uri = uri;
        this.zzbqr.registerContentObserver(uri, false, new zzsj(this, null));
    }

    public static zzsi zza(ContentResolver contentResolver, Uri uri) {
        zzsi zzsiVar;
        synchronized (zzbqp) {
            zzsiVar = zzbqq.get(uri);
            if (zzsiVar == null) {
                zzsiVar = new zzsi(contentResolver, uri);
                zzbqq.put(uri, zzsiVar);
            }
        }
        return zzsiVar;
    }

    public final Map<String, String> zzsz() {
        Map<String, String> zztb = zzsl.zzd("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? zztb() : this.zzbqt;
        if (zztb == null) {
            synchronized (this.zzbqs) {
                zztb = this.zzbqt;
                if (zztb == null) {
                    zztb = zztb();
                    this.zzbqt = zztb;
                }
            }
        }
        return zztb != null ? zztb : Collections.emptyMap();
    }

    public final void zzta() {
        synchronized (this.zzbqs) {
            this.zzbqt = null;
        }
    }

    private final Map<String, String> zztb() {
        try {
            HashMap hashMap = new HashMap();
            Cursor query = this.zzbqr.query(this.uri, zzbqw, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    hashMap.put(query.getString(0), query.getString(1));
                }
                query.close();
            }
            return hashMap;
        } catch (SQLiteException | SecurityException unused) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zztc() {
        synchronized (this.zzbqu) {
            for (zzsk zzskVar : this.zzbqv) {
                zzskVar.zztd();
            }
        }
    }
}
