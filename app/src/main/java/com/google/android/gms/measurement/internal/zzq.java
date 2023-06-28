package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzyx;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzq extends zzez {
    private static final String[] zzahi = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    private static final String[] zzahj = {FirebaseAnalytics.Param.ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzahk = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;"};
    private static final String[] zzahl = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzahm = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzahn = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzt zzaho;
    private final zzev zzahp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(zzfa zzfaVar) {
        super(zzfaVar);
        this.zzahp = new zzev(zzbx());
        this.zzaho = new zzt(this, getContext(), "google_app_measurement.db");
    }

    @Override // com.google.android.gms.measurement.internal.zzez
    protected final boolean zzgt() {
        return false;
    }

    @WorkerThread
    public final void beginTransaction() {
        zzcl();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        zzcl();
        getWritableDatabase().setTransactionSuccessful();
    }

    @WorkerThread
    public final void endTransaction() {
        zzcl();
        getWritableDatabase().endTransaction();
    }

    @WorkerThread
    private final long zza(String str, String[] strArr) {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            try {
                cursor = getWritableDatabase().rawQuery(str, strArr);
                try {
                    if (cursor.moveToFirst()) {
                        long j = cursor.getLong(0);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return j;
                    }
                    throw new SQLiteException("Database returned empty set");
                } catch (SQLiteException e) {
                    e = e;
                    cursor2 = cursor;
                    zzgo().zzjd().zze("Database error", str, e);
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = cursor2;
            }
        } catch (SQLiteException e2) {
            e = e2;
        }
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
                try {
                    if (!rawQuery.moveToFirst()) {
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return j;
                    }
                    long j2 = rawQuery.getLong(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return j2;
                } catch (SQLiteException e) {
                    e = e;
                    cursor = rawQuery;
                    zzgo().zzjd().zze("Database error", str, e);
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    cursor = rawQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e2) {
            e = e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final SQLiteDatabase getWritableDatabase() {
        zzaf();
        try {
            return this.zzaho.getWritableDatabase();
        } catch (SQLiteException e) {
            zzgo().zzjg().zzg("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public final zzz zzg(String str, String str2) {
        Cursor cursor;
        Cursor cursor2;
        Cursor query;
        Boolean bool;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        try {
            try {
                query = getWritableDatabase().query("events", new String[]{"lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            } catch (SQLiteException e) {
                e = e;
                cursor2 = null;
            } catch (Throwable th) {
                th = th;
                cursor = null;
            }
            try {
                if (!query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                    return null;
                }
                long j = query.getLong(0);
                long j2 = query.getLong(1);
                long j3 = query.getLong(2);
                long j4 = query.isNull(3) ? 0L : query.getLong(3);
                Long valueOf = query.isNull(4) ? null : Long.valueOf(query.getLong(4));
                Long valueOf2 = query.isNull(5) ? null : Long.valueOf(query.getLong(5));
                Long valueOf3 = query.isNull(6) ? null : Long.valueOf(query.getLong(6));
                if (query.isNull(7)) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(query.getLong(7) == 1);
                }
                cursor2 = query;
                try {
                    zzz zzzVar = new zzz(str, str2, j, j2, j3, j4, valueOf, valueOf2, valueOf3, bool);
                    if (cursor2.moveToNext()) {
                        zzgo().zzjd().zzg("Got multiple records for event aggregates, expected one. appId", zzap.zzbv(str));
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return zzzVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    zzgo().zzjd().zzd("Error querying events. appId", zzap.zzbv(str), zzgl().zzbs(str2), e);
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return null;
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor2 = query;
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @WorkerThread
    public final void zza(zzz zzzVar) {
        Preconditions.checkNotNull(zzzVar);
        zzaf();
        zzcl();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzzVar.zztt);
        contentValues.put("name", zzzVar.name);
        contentValues.put("lifetime_count", Long.valueOf(zzzVar.zzaie));
        contentValues.put("current_bundle_count", Long.valueOf(zzzVar.zzaif));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzzVar.zzaig));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzzVar.zzaih));
        contentValues.put("last_bundled_day", zzzVar.zzaii);
        contentValues.put("last_sampled_complex_event_id", zzzVar.zzaij);
        contentValues.put("last_sampling_rate", zzzVar.zzaik);
        contentValues.put("last_exempt_from_sampling", (zzzVar.zzail == null || !zzzVar.zzail.booleanValue()) ? null : 1L);
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzgo().zzjd().zzg("Failed to insert/update event aggregates (got -1). appId", zzap.zzbv(zzzVar.zztt));
            }
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error storing event aggregates. appId", zzap.zzbv(zzzVar.zztt), e);
        }
    }

    @WorkerThread
    public final void zzh(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        try {
            zzgo().zzjl().zzg("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzgo().zzjd().zzd("Error deleting user attribute. appId", zzap.zzbv(str), zzgl().zzbu(str2), e);
        }
    }

    @WorkerThread
    public final boolean zza(zzfj zzfjVar) {
        Preconditions.checkNotNull(zzfjVar);
        zzaf();
        zzcl();
        if (zzi(zzfjVar.zztt, zzfjVar.name) == null) {
            if (zzfk.zzcq(zzfjVar.name)) {
                if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzfjVar.zztt}) >= 25) {
                    return false;
                }
            } else {
                long zza = zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzfjVar.zztt, zzfjVar.origin});
                if (zzgq().zze(zzfjVar.zztt, zzaf.zzalj)) {
                    if (!"_ap".equals(zzfjVar.name) && zza >= 25) {
                        return false;
                    }
                } else if (zza >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzfjVar.zztt);
        contentValues.put(FirebaseAnalytics.Param.ORIGIN, zzfjVar.origin);
        contentValues.put("name", zzfjVar.name);
        contentValues.put("set_timestamp", Long.valueOf(zzfjVar.zzaue));
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzfjVar.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzgo().zzjd().zzg("Failed to insert/update user property (got -1). appId", zzap.zzbv(zzfjVar.zztt));
            }
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error storing user property. appId", zzap.zzbv(zzfjVar.zztt), e);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00ae  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzfj zzi(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r8 = r20
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r18.zzaf()
            r18.zzcl()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r18.getWritableDatabase()     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            java.lang.String r11 = "user_attributes"
            r0 = 3
            java.lang.String[] r12 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            java.lang.String r0 = "set_timestamp"
            r1 = 0
            r12[r1] = r0     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            java.lang.String r0 = "value"
            r2 = 1
            r12[r2] = r0     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            java.lang.String r0 = "origin"
            r3 = 2
            r12[r3] = r0     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            java.lang.String r13 = "app_id=? and name=?"
            java.lang.String[] r14 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            r14[r1] = r19     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            r14[r2] = r8     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L88
            boolean r0 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteException -> L7f
            if (r0 != 0) goto L44
            if (r10 == 0) goto L43
            r10.close()
        L43:
            return r9
        L44:
            long r5 = r10.getLong(r1)     // Catch: java.lang.Throwable -> L7b android.database.sqlite.SQLiteException -> L7f
            r11 = r18
            java.lang.Object r7 = r11.zza(r10, r2)     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
            java.lang.String r3 = r10.getString(r3)     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
            com.google.android.gms.measurement.internal.zzfj r0 = new com.google.android.gms.measurement.internal.zzfj     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
            r1 = r0
            r2 = r19
            r4 = r20
            r1.<init>(r2, r3, r4, r5, r7)     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
            boolean r1 = r10.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
            if (r1 == 0) goto L73
            com.google.android.gms.measurement.internal.zzap r1 = r18.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzap.zzbv(r19)     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
            r1.zzg(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L79 java.lang.Throwable -> Lab
        L73:
            if (r10 == 0) goto L78
            r10.close()
        L78:
            return r0
        L79:
            r0 = move-exception
            goto L8c
        L7b:
            r0 = move-exception
            r11 = r18
            goto Lac
        L7f:
            r0 = move-exception
            r11 = r18
            goto L8c
        L83:
            r0 = move-exception
            r11 = r18
            r10 = r9
            goto Lac
        L88:
            r0 = move-exception
            r11 = r18
            r10 = r9
        L8c:
            com.google.android.gms.measurement.internal.zzap r1 = r18.zzgo()     // Catch: java.lang.Throwable -> Lab
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: java.lang.Throwable -> Lab
            java.lang.String r2 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzap.zzbv(r19)     // Catch: java.lang.Throwable -> Lab
            com.google.android.gms.measurement.internal.zzan r4 = r18.zzgl()     // Catch: java.lang.Throwable -> Lab
            java.lang.String r4 = r4.zzbu(r8)     // Catch: java.lang.Throwable -> Lab
            r1.zzd(r2, r3, r4, r0)     // Catch: java.lang.Throwable -> Lab
            if (r10 == 0) goto Laa
            r10.close()
        Laa:
            return r9
        Lab:
            r0 = move-exception
        Lac:
            if (r10 == 0) goto Lb1
            r10.close()
        Lb1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzi(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzfj");
    }

    @WorkerThread
    public final List<zzfj> zzbk(String str) {
        Cursor cursor;
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        ArrayList arrayList = new ArrayList();
        try {
            try {
                cursor = getWritableDatabase().query("user_attributes", new String[]{"name", FirebaseAnalytics.Param.ORIGIN, "set_timestamp", FirebaseAnalytics.Param.VALUE}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                try {
                    if (!cursor.moveToFirst()) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return arrayList;
                    }
                    do {
                        String string = cursor.getString(0);
                        String string2 = cursor.getString(1);
                        if (string2 == null) {
                            string2 = "";
                        }
                        String str2 = string2;
                        long j = cursor.getLong(2);
                        try {
                            Object zza = zza(cursor, 3);
                            if (zza == null) {
                                zzgo().zzjd().zzg("Read invalid user property value, ignoring it. appId", zzap.zzbv(str));
                            } else {
                                arrayList.add(new zzfj(str, str2, string, j, zza));
                            }
                        } catch (SQLiteException e) {
                            e = e;
                            zzgo().zzjd().zze("Error querying user properties. appId", zzap.zzbv(str), e);
                            if (cursor != null) {
                                cursor.close();
                            }
                            return null;
                        }
                    } while (cursor.moveToNext());
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (SQLiteException e2) {
                    e = e2;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x009f, code lost:
        zzgo().zzjd().zzg("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014a  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzfj> zzb(java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public final boolean zza(zzl zzlVar) {
        Preconditions.checkNotNull(zzlVar);
        zzaf();
        zzcl();
        if (zzi(zzlVar.packageName, zzlVar.zzahb.name) != null || zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzlVar.packageName}) < 1000) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzlVar.packageName);
            contentValues.put(FirebaseAnalytics.Param.ORIGIN, zzlVar.origin);
            contentValues.put("name", zzlVar.zzahb.name);
            zza(contentValues, FirebaseAnalytics.Param.VALUE, zzlVar.zzahb.getValue());
            contentValues.put("active", Boolean.valueOf(zzlVar.active));
            contentValues.put("trigger_event_name", zzlVar.triggerEventName);
            contentValues.put("trigger_timeout", Long.valueOf(zzlVar.triggerTimeout));
            zzgm();
            contentValues.put("timed_out_event", zzfk.zza(zzlVar.zzahc));
            contentValues.put("creation_timestamp", Long.valueOf(zzlVar.creationTimestamp));
            zzgm();
            contentValues.put("triggered_event", zzfk.zza(zzlVar.zzahd));
            contentValues.put("triggered_timestamp", Long.valueOf(zzlVar.zzahb.zzaue));
            contentValues.put("time_to_live", Long.valueOf(zzlVar.timeToLive));
            zzgm();
            contentValues.put("expired_event", zzfk.zza(zzlVar.zzahe));
            try {
                if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                    zzgo().zzjd().zzg("Failed to insert/update conditional user property (got -1)", zzap.zzbv(zzlVar.packageName));
                }
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing conditional user property", zzap.zzbv(zzlVar.packageName), e);
            }
            return true;
        }
        return false;
    }

    @WorkerThread
    public final zzl zzj(String str, String str2) {
        Cursor cursor;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        try {
            try {
                cursor = getWritableDatabase().query("conditional_properties", new String[]{FirebaseAnalytics.Param.ORIGIN, FirebaseAnalytics.Param.VALUE, "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            } catch (SQLiteException e) {
                e = e;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                cursor = null;
            }
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                String string = cursor.getString(0);
                try {
                    Object zza = zza(cursor, 1);
                    zzl zzlVar = new zzl(str, string, new zzfh(str2, cursor.getLong(8), zza, string), cursor.getLong(6), cursor.getInt(2) != 0, cursor.getString(3), (zzad) zzjo().zza(cursor.getBlob(5), zzad.CREATOR), cursor.getLong(4), (zzad) zzjo().zza(cursor.getBlob(7), zzad.CREATOR), cursor.getLong(9), (zzad) zzjo().zza(cursor.getBlob(10), zzad.CREATOR));
                    if (cursor.moveToNext()) {
                        zzgo().zzjd().zze("Got multiple records for conditional property, expected one", zzap.zzbv(str), zzgl().zzbu(str2));
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zzlVar;
                } catch (SQLiteException e2) {
                    e = e2;
                    zzgo().zzjd().zzd("Error querying conditional property", zzap.zzbv(str), zzgl().zzbu(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
            } catch (SQLiteException e3) {
                e = e3;
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @WorkerThread
    public final int zzk(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzgo().zzjd().zzd("Error deleting conditional property", zzap.zzbv(str), zzgl().zzbu(str2), e);
            return 0;
        }
    }

    @WorkerThread
    public final List<zzl> zzc(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzb(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x008e, code lost:
        zzgo().zzjd().zzg("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzl> zzb(java.lang.String r40, java.lang.String[] r41) {
        /*
            Method dump skipped, instructions count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzb(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0184 A[Catch: SQLiteException -> 0x0212, all -> 0x023c, TryCatch #2 {SQLiteException -> 0x0212, blocks: (B:11:0x00d8, B:13:0x0135, B:18:0x013f, B:22:0x0189, B:26:0x01bf, B:28:0x01ca, B:33:0x01d4, B:35:0x01df, B:38:0x01e6, B:40:0x01fb, B:25:0x01bb, B:21:0x0184), top: B:62:0x00d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01bb A[Catch: SQLiteException -> 0x0212, all -> 0x023c, TryCatch #2 {SQLiteException -> 0x0212, blocks: (B:11:0x00d8, B:13:0x0135, B:18:0x013f, B:22:0x0189, B:26:0x01bf, B:28:0x01ca, B:33:0x01d4, B:35:0x01df, B:38:0x01e6, B:40:0x01fb, B:25:0x01bb, B:21:0x0184), top: B:62:0x00d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01df A[Catch: SQLiteException -> 0x0212, all -> 0x023c, TryCatch #2 {SQLiteException -> 0x0212, blocks: (B:11:0x00d8, B:13:0x0135, B:18:0x013f, B:22:0x0189, B:26:0x01bf, B:28:0x01ca, B:33:0x01d4, B:35:0x01df, B:38:0x01e6, B:40:0x01fb, B:25:0x01bb, B:21:0x0184), top: B:62:0x00d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01fb A[Catch: SQLiteException -> 0x0212, all -> 0x023c, TRY_LEAVE, TryCatch #2 {SQLiteException -> 0x0212, blocks: (B:11:0x00d8, B:13:0x0135, B:18:0x013f, B:22:0x0189, B:26:0x01bf, B:28:0x01ca, B:33:0x01d4, B:35:0x01df, B:38:0x01e6, B:40:0x01fb, B:25:0x01bb, B:21:0x0184), top: B:62:0x00d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x023f  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzg zzbl(java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 579
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzbl(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    @WorkerThread
    public final void zza(zzg zzgVar) {
        Preconditions.checkNotNull(zzgVar);
        zzaf();
        zzcl();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzgVar.zzal());
        contentValues.put("app_instance_id", zzgVar.getAppInstanceId());
        contentValues.put("gmp_app_id", zzgVar.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzgVar.zzgx());
        contentValues.put("last_bundle_index", Long.valueOf(zzgVar.zzhe()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzgVar.zzgy()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzgVar.zzgz()));
        contentValues.put("app_version", zzgVar.zzak());
        contentValues.put("app_store", zzgVar.zzhb());
        contentValues.put("gmp_version", Long.valueOf(zzgVar.zzhc()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzgVar.zzhd()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzgVar.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzgVar.zzhi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzgVar.zzhj()));
        contentValues.put("daily_events_count", Long.valueOf(zzgVar.zzhk()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzgVar.zzhl()));
        contentValues.put("config_fetched_time", Long.valueOf(zzgVar.zzhf()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzgVar.zzhg()));
        contentValues.put("app_version_int", Long.valueOf(zzgVar.zzha()));
        contentValues.put("firebase_instance_id", zzgVar.getFirebaseInstanceId());
        contentValues.put("daily_error_events_count", Long.valueOf(zzgVar.zzhn()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzgVar.zzhm()));
        contentValues.put("health_monitor_sample", zzgVar.zzho());
        contentValues.put("android_id", Long.valueOf(zzgVar.zzhq()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzgVar.zzhr()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzgVar.zzhs()));
        contentValues.put("admob_app_id", zzgVar.zzgw());
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzgVar.zzal()}) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzgo().zzjd().zzg("Failed to insert/update app (got -1). appId", zzap.zzbv(zzgVar.zzal()));
            }
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error storing app. appId", zzap.zzbv(zzgVar.zzal()), e);
        }
    }

    public final long zzbm(String str) {
        Preconditions.checkNotEmpty(str);
        zzaf();
        zzcl();
        try {
            return getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzgq().zzb(str, zzaf.zzajs))))});
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Error deleting over the limit events. appId", zzap.zzbv(str), e);
            return 0L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0139  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzr zza(long r20, java.lang.String r22, boolean r23, boolean r24, boolean r25, boolean r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zza(long, java.lang.String, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.measurement.internal.zzr");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0079  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] zzbn(java.lang.String r12) {
        /*
            r11 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r11.zzaf()
            r11.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.getWritableDatabase()     // Catch: java.lang.Throwable -> L5a android.database.sqlite.SQLiteException -> L5d
            java.lang.String r2 = "apps"
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L5a android.database.sqlite.SQLiteException -> L5d
            java.lang.String r5 = "remote_config"
            r9 = 0
            r4[r9] = r5     // Catch: java.lang.Throwable -> L5a android.database.sqlite.SQLiteException -> L5d
            java.lang.String r5 = "app_id=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L5a android.database.sqlite.SQLiteException -> L5d
            r6[r9] = r12     // Catch: java.lang.Throwable -> L5a android.database.sqlite.SQLiteException -> L5d
            r7 = 0
            r8 = 0
            r10 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r10
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L5a android.database.sqlite.SQLiteException -> L5d
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L58 java.lang.Throwable -> L76
            if (r2 != 0) goto L37
            if (r1 == 0) goto L36
            r1.close()
        L36:
            return r0
        L37:
            byte[] r2 = r1.getBlob(r9)     // Catch: android.database.sqlite.SQLiteException -> L58 java.lang.Throwable -> L76
            boolean r3 = r1.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L58 java.lang.Throwable -> L76
            if (r3 == 0) goto L52
            com.google.android.gms.measurement.internal.zzap r3 = r11.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L58 java.lang.Throwable -> L76
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L58 java.lang.Throwable -> L76
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzap.zzbv(r12)     // Catch: android.database.sqlite.SQLiteException -> L58 java.lang.Throwable -> L76
            r3.zzg(r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L58 java.lang.Throwable -> L76
        L52:
            if (r1 == 0) goto L57
            r1.close()
        L57:
            return r2
        L58:
            r2 = move-exception
            goto L5f
        L5a:
            r12 = move-exception
            r1 = r0
            goto L77
        L5d:
            r2 = move-exception
            r1 = r0
        L5f:
            com.google.android.gms.measurement.internal.zzap r3 = r11.zzgo()     // Catch: java.lang.Throwable -> L76
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: java.lang.Throwable -> L76
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzap.zzbv(r12)     // Catch: java.lang.Throwable -> L76
            r3.zze(r4, r12, r2)     // Catch: java.lang.Throwable -> L76
            if (r1 == 0) goto L75
            r1.close()
        L75:
            return r0
        L76:
            r12 = move-exception
        L77:
            if (r1 == 0) goto L7c
            r1.close()
        L7c:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzbn(java.lang.String):byte[]");
    }

    @WorkerThread
    public final boolean zza(zzgi zzgiVar, boolean z) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzgiVar);
        Preconditions.checkNotEmpty(zzgiVar.zztt);
        Preconditions.checkNotNull(zzgiVar.zzaxf);
        zzif();
        long currentTimeMillis = zzbx().currentTimeMillis();
        if (zzgiVar.zzaxf.longValue() < currentTimeMillis - zzn.zzhw() || zzgiVar.zzaxf.longValue() > zzn.zzhw() + currentTimeMillis) {
            zzgo().zzjg().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzap.zzbv(zzgiVar.zztt), Long.valueOf(currentTimeMillis), zzgiVar.zzaxf);
        }
        try {
            byte[] bArr = new byte[zzgiVar.zzvu()];
            zzyy zzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgiVar.zza(zzk);
            zzk.zzyt();
            byte[] zzb = zzjo().zzb(bArr);
            zzgo().zzjl().zzg("Saving bundle, size", Integer.valueOf(zzb.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzgiVar.zztt);
            contentValues.put("bundle_end_timestamp", zzgiVar.zzaxf);
            contentValues.put("data", zzb);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzgiVar.zzayc != null) {
                contentValues.put("retry_count", zzgiVar.zzayc);
            }
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) == -1) {
                    zzgo().zzjd().zzg("Failed to insert bundle (got -1). appId", zzap.zzbv(zzgiVar.zztt));
                    return false;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing bundle. appId", zzap.zzbv(zzgiVar.zztt), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Data loss. Failed to serialize bundle. appId", zzap.zzbv(zzgiVar.zztt), e2);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zzid() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch: java.lang.Throwable -> L24 android.database.sqlite.SQLiteException -> L29
            boolean r2 = r0.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L22 java.lang.Throwable -> L3e
            if (r2 == 0) goto L1c
            r2 = 0
            java.lang.String r2 = r0.getString(r2)     // Catch: android.database.sqlite.SQLiteException -> L22 java.lang.Throwable -> L3e
            if (r0 == 0) goto L1b
            r0.close()
        L1b:
            return r2
        L1c:
            if (r0 == 0) goto L21
            r0.close()
        L21:
            return r1
        L22:
            r2 = move-exception
            goto L2b
        L24:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L3f
        L29:
            r2 = move-exception
            r0 = r1
        L2b:
            com.google.android.gms.measurement.internal.zzap r3 = r6.zzgo()     // Catch: java.lang.Throwable -> L3e
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: java.lang.Throwable -> L3e
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzg(r4, r2)     // Catch: java.lang.Throwable -> L3e
            if (r0 == 0) goto L3d
            r0.close()
        L3d:
            return r1
        L3e:
            r1 = move-exception
        L3f:
            if (r0 == 0) goto L44
            r0.close()
        L44:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzid():java.lang.String");
    }

    public final boolean zzie() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    @WorkerThread
    public final List<Pair<zzgi, Long>> zzb(String str, int i, int i2) {
        Cursor cursor;
        byte[] zza;
        zzaf();
        zzcl();
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor2 = null;
        try {
            try {
                cursor = getWritableDatabase().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
                try {
                    if (!cursor.moveToFirst()) {
                        List<Pair<zzgi, Long>> emptyList = Collections.emptyList();
                        if (cursor != null) {
                            cursor.close();
                        }
                        return emptyList;
                    }
                    ArrayList arrayList = new ArrayList();
                    int i3 = 0;
                    do {
                        long j = cursor.getLong(0);
                        try {
                            zza = zzjo().zza(cursor.getBlob(1));
                        } catch (IOException e) {
                            zzgo().zzjd().zze("Failed to unzip queued bundle. appId", zzap.zzbv(str), e);
                        }
                        if (!arrayList.isEmpty() && zza.length + i3 > i2) {
                            break;
                        }
                        zzyx zzj = zzyx.zzj(zza, 0, zza.length);
                        zzgi zzgiVar = new zzgi();
                        try {
                            zzgiVar.zza(zzj);
                            if (!cursor.isNull(2)) {
                                zzgiVar.zzayc = Integer.valueOf(cursor.getInt(2));
                            }
                            i3 += zza.length;
                            arrayList.add(Pair.create(zzgiVar, Long.valueOf(j)));
                        } catch (IOException e2) {
                            zzgo().zzjd().zze("Failed to merge queued bundle. appId", zzap.zzbv(str), e2);
                        }
                        if (!cursor.moveToNext()) {
                            break;
                        }
                    } while (i3 <= i2);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (SQLiteException e3) {
                    e = e3;
                    cursor2 = cursor;
                    zzgo().zzjd().zze("Error querying bundles. appId", zzap.zzbv(str), e);
                    List<Pair<zzgi, Long>> emptyList2 = Collections.emptyList();
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return emptyList2;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zzif() {
        int delete;
        zzaf();
        zzcl();
        if (zzil()) {
            long j = zzgp().zzanh.get();
            long elapsedRealtime = zzbx().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzaf.zzakb.get().longValue()) {
                zzgp().zzanh.set(elapsedRealtime);
                zzaf();
                zzcl();
                if (!zzil() || (delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzbx().currentTimeMillis()), String.valueOf(zzn.zzhw())})) <= 0) {
                    return;
                }
                zzgo().zzjl().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zzc(List<Long> list) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzil()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zza(sb3.toString(), (String[]) null) > 0) {
                zzgo().zzjg().zzbx("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                writableDatabase.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzgo().zzjd().zzg("Error incrementing retry count. error", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, zzfu[] zzfuVarArr) {
        boolean z;
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfuVarArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzcl();
            zzaf();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzfu zzfuVar : zzfuVarArr) {
                zzcl();
                zzaf();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzfuVar);
                Preconditions.checkNotNull(zzfuVar.zzava);
                Preconditions.checkNotNull(zzfuVar.zzauz);
                if (zzfuVar.zzauy == null) {
                    zzgo().zzjg().zzg("Audience with no ID. appId", zzap.zzbv(str));
                } else {
                    int intValue = zzfuVar.zzauy.intValue();
                    zzfv[] zzfvVarArr = zzfuVar.zzava;
                    int length = zzfvVarArr.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            if (zzfvVarArr[i].zzave == null) {
                                zzgo().zzjg().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzap.zzbv(str), zzfuVar.zzauy);
                                break;
                            }
                            i++;
                        } else {
                            zzfy[] zzfyVarArr = zzfuVar.zzauz;
                            int length2 = zzfyVarArr.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 < length2) {
                                    if (zzfyVarArr[i2].zzave == null) {
                                        zzgo().zzjg().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzap.zzbv(str), zzfuVar.zzauy);
                                        break;
                                    }
                                    i2++;
                                } else {
                                    zzfv[] zzfvVarArr2 = zzfuVar.zzava;
                                    int length3 = zzfvVarArr2.length;
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 >= length3) {
                                            z = true;
                                            break;
                                        } else if (!zza(str, intValue, zzfvVarArr2[i3])) {
                                            z = false;
                                            break;
                                        } else {
                                            i3++;
                                        }
                                    }
                                    if (z) {
                                        zzfy[] zzfyVarArr2 = zzfuVar.zzauz;
                                        int length4 = zzfyVarArr2.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= length4) {
                                                break;
                                            } else if (!zza(str, intValue, zzfyVarArr2[i4])) {
                                                z = false;
                                                break;
                                            } else {
                                                i4++;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzcl();
                                        zzaf();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase writableDatabase3 = getWritableDatabase();
                                        writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                        writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzfu zzfuVar2 : zzfuVarArr) {
                arrayList.add(zzfuVar2.zzauy);
            }
            zza(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzfv zzfvVar) {
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfvVar);
        if (TextUtils.isEmpty(zzfvVar.zzavf)) {
            zzgo().zzjg().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzap.zzbv(str), Integer.valueOf(i), String.valueOf(zzfvVar.zzave));
            return false;
        }
        try {
            byte[] bArr = new byte[zzfvVar.zzvu()];
            zzyy zzk = zzyy.zzk(bArr, 0, bArr.length);
            zzfvVar.zza(zzk);
            zzk.zzyt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzfvVar.zzave);
            contentValues.put("event_name", zzfvVar.zzavf);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzgo().zzjd().zzg("Failed to insert event filter (got -1). appId", zzap.zzbv(str));
                    return true;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing event filter. appId", zzap.zzbv(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Configuration loss. Failed to serialize event filter. appId", zzap.zzbv(str), e2);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzfy zzfyVar) {
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfyVar);
        if (TextUtils.isEmpty(zzfyVar.zzavu)) {
            zzgo().zzjg().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzap.zzbv(str), Integer.valueOf(i), String.valueOf(zzfyVar.zzave));
            return false;
        }
        try {
            byte[] bArr = new byte[zzfyVar.zzvu()];
            zzyy zzk = zzyy.zzk(bArr, 0, bArr.length);
            zzfyVar.zza(zzk);
            zzk.zzyt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzfyVar.zzave);
            contentValues.put("property_name", zzfyVar.zzavu);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) == -1) {
                    zzgo().zzjd().zzg("Failed to insert property filter (got -1). appId", zzap.zzbv(str));
                    return false;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing property filter. appId", zzap.zzbv(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Configuration loss. Failed to serialize property filter. appId", zzap.zzbv(str), e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b8  */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzfv>> zzl(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzcl()
            r12.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "event_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            java.lang.String r5 = "app_id=? AND event_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            r6[r10] = r13     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            r6[r11] = r14     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            boolean r1 = r14.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            if (r1 != 0) goto L48
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            if (r14 == 0) goto L47
            r14.close()
        L47:
            return r0
        L48:
            byte[] r1 = r14.getBlob(r11)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            int r2 = r1.length     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            com.google.android.gms.internal.measurement.zzyx r1 = com.google.android.gms.internal.measurement.zzyx.zzj(r1, r10, r2)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            com.google.android.gms.internal.measurement.zzfv r2 = new com.google.android.gms.internal.measurement.zzfv     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r2.<init>()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r2.zza(r1)     // Catch: java.io.IOException -> L79 android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            int r1 = r14.getInt(r10)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.lang.Object r3 = r0.get(r3)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.util.List r3 = (java.util.List) r3     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            if (r3 != 0) goto L75
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r3.<init>()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r0.put(r1, r3)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
        L75:
            r3.add(r2)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            goto L8b
        L79:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzap r2 = r12.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            com.google.android.gms.measurement.internal.zzar r2 = r2.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzap.zzbv(r13)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r2.zze(r3, r4, r1)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
        L8b:
            boolean r1 = r14.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            if (r1 != 0) goto L48
            if (r14 == 0) goto L96
            r14.close()
        L96:
            return r0
        L97:
            r0 = move-exception
            goto L9e
        L99:
            r13 = move-exception
            r14 = r9
            goto Lb6
        L9c:
            r0 = move-exception
            r14 = r9
        L9e:
            com.google.android.gms.measurement.internal.zzap r1 = r12.zzgo()     // Catch: java.lang.Throwable -> Lb5
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzap.zzbv(r13)     // Catch: java.lang.Throwable -> Lb5
            r1.zze(r2, r13, r0)     // Catch: java.lang.Throwable -> Lb5
            if (r14 == 0) goto Lb4
            r14.close()
        Lb4:
            return r9
        Lb5:
            r13 = move-exception
        Lb6:
            if (r14 == 0) goto Lbb
            r14.close()
        Lbb:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzl(java.lang.String, java.lang.String):java.util.Map");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b8  */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzfy>> zzm(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzcl()
            r12.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "property_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            java.lang.String r5 = "app_id=? AND property_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            r6[r10] = r13     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            r6[r11] = r14     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L99 android.database.sqlite.SQLiteException -> L9c
            boolean r1 = r14.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            if (r1 != 0) goto L48
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            if (r14 == 0) goto L47
            r14.close()
        L47:
            return r0
        L48:
            byte[] r1 = r14.getBlob(r11)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            int r2 = r1.length     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            com.google.android.gms.internal.measurement.zzyx r1 = com.google.android.gms.internal.measurement.zzyx.zzj(r1, r10, r2)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            com.google.android.gms.internal.measurement.zzfy r2 = new com.google.android.gms.internal.measurement.zzfy     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r2.<init>()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r2.zza(r1)     // Catch: java.io.IOException -> L79 android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            int r1 = r14.getInt(r10)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.lang.Object r3 = r0.get(r3)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.util.List r3 = (java.util.List) r3     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            if (r3 != 0) goto L75
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r3.<init>()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r0.put(r1, r3)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
        L75:
            r3.add(r2)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            goto L8b
        L79:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzap r2 = r12.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            com.google.android.gms.measurement.internal.zzar r2 = r2.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzap.zzbv(r13)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            r2.zze(r3, r4, r1)     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
        L8b:
            boolean r1 = r14.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L97 java.lang.Throwable -> Lb5
            if (r1 != 0) goto L48
            if (r14 == 0) goto L96
            r14.close()
        L96:
            return r0
        L97:
            r0 = move-exception
            goto L9e
        L99:
            r13 = move-exception
            r14 = r9
            goto Lb6
        L9c:
            r0 = move-exception
            r14 = r9
        L9e:
            com.google.android.gms.measurement.internal.zzap r1 = r12.zzgo()     // Catch: java.lang.Throwable -> Lb5
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzap.zzbv(r13)     // Catch: java.lang.Throwable -> Lb5
            r1.zze(r2, r13, r0)     // Catch: java.lang.Throwable -> Lb5
            if (r14 == 0) goto Lb4
            r14.close()
        Lb4:
            return r9
        Lb5:
            r13 = move-exception
        Lb6:
            if (r14 == 0) goto Lbb
            r14.close()
        Lbb:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzm(java.lang.String, java.lang.String):java.util.Map");
    }

    private final boolean zza(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzcl();
        zzaf();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zza = zza("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zzgq().zzb(str, zzaf.zzaki)));
            if (zza <= max) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("(");
            sb.append(join);
            sb.append(")");
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzgo().zzjd().zze("Database error querying filters. appId", zzap.zzbv(str), e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzgj> zzbo(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzcl()
            r11.zzaf()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L7c android.database.sqlite.SQLiteException -> L7f
            java.lang.String r3 = "audience_id"
            r9 = 0
            r2[r9] = r3     // Catch: java.lang.Throwable -> L7c android.database.sqlite.SQLiteException -> L7f
            java.lang.String r3 = "current_results"
            r10 = 1
            r2[r10] = r3     // Catch: java.lang.Throwable -> L7c android.database.sqlite.SQLiteException -> L7f
            java.lang.String r3 = "app_id=?"
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> L7c android.database.sqlite.SQLiteException -> L7f
            r4[r9] = r12     // Catch: java.lang.Throwable -> L7c android.database.sqlite.SQLiteException -> L7f
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L7c android.database.sqlite.SQLiteException -> L7f
            boolean r1 = r0.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            if (r1 != 0) goto L36
            if (r0 == 0) goto L35
            r0.close()
        L35:
            return r8
        L36:
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            r1.<init>()     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
        L3b:
            int r2 = r0.getInt(r9)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            byte[] r3 = r0.getBlob(r10)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            int r4 = r3.length     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzyx r3 = com.google.android.gms.internal.measurement.zzyx.zzj(r3, r9, r4)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzgj r4 = new com.google.android.gms.internal.measurement.zzgj     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            r4.<init>()     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            r4.zza(r3)     // Catch: java.io.IOException -> L58 android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            r1.put(r2, r4)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            goto L6e
        L58:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzap r4 = r11.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            com.google.android.gms.measurement.internal.zzar r4 = r4.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzap.zzbv(r12)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            r4.zzd(r5, r6, r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
        L6e:
            boolean r2 = r0.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> L98
            if (r2 != 0) goto L3b
            if (r0 == 0) goto L79
            r0.close()
        L79:
            return r1
        L7a:
            r1 = move-exception
            goto L81
        L7c:
            r12 = move-exception
            r0 = r8
            goto L99
        L7f:
            r1 = move-exception
            r0 = r8
        L81:
            com.google.android.gms.measurement.internal.zzap r2 = r11.zzgo()     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.measurement.internal.zzar r2 = r2.zzjd()     // Catch: java.lang.Throwable -> L98
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzap.zzbv(r12)     // Catch: java.lang.Throwable -> L98
            r2.zze(r3, r12, r1)     // Catch: java.lang.Throwable -> L98
            if (r0 == 0) goto L97
            r0.close()
        L97:
            return r8
        L98:
            r12 = move-exception
        L99:
            if (r0 == 0) goto L9e
            r0.close()
        L9e:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzbo(java.lang.String):java.util.Map");
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzgo().zzjd().zzbx("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzgo().zzjd().zzbx("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzgo().zzjd().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    public final long zzig() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final long zzn(String str, String str2) {
        long j;
        ContentValues contentValues;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzaf();
        zzcl();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
                sb.append("select ");
                sb.append(str2);
                sb.append(" from app2 where app_id=?");
                j = zza(sb.toString(), new String[]{str}, -1L);
                if (j == -1) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put("first_open_count", (Integer) 0);
                    contentValues2.put("previous_install_count", (Integer) 0);
                    if (writableDatabase.insertWithOnConflict("app2", null, contentValues2, 5) == -1) {
                        zzgo().zzjd().zze("Failed to insert column (got -1). appId", zzap.zzbv(str), str2);
                        return -1L;
                    }
                    j = 0;
                }
                try {
                    contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put(str2, Long.valueOf(1 + j));
                } catch (SQLiteException e) {
                    e = e;
                    zzgo().zzjd().zzd("Error inserting column. appId", zzap.zzbv(str), str2, e);
                    return j;
                }
            } finally {
                writableDatabase.endTransaction();
            }
        } catch (SQLiteException e2) {
            e = e2;
            j = 0;
        }
        if (writableDatabase.update("app2", contentValues, "app_id = ?", new String[]{str}) == 0) {
            zzgo().zzjd().zze("Failed to update column (got 0). appId", zzap.zzbv(str), str2);
            return -1L;
        }
        writableDatabase.setTransactionSuccessful();
        return j;
    }

    @WorkerThread
    public final long zzih() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0L);
    }

    public final long zza(zzgi zzgiVar) throws IOException {
        long zzc;
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzgiVar);
        Preconditions.checkNotEmpty(zzgiVar.zztt);
        try {
            byte[] bArr = new byte[zzgiVar.zzvu()];
            zzyy zzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgiVar.zza(zzk);
            zzk.zzyt();
            zzfg zzjo = zzjo();
            Preconditions.checkNotNull(bArr);
            zzjo.zzgm().zzaf();
            MessageDigest messageDigest = zzfk.getMessageDigest();
            if (messageDigest == null) {
                zzjo.zzgo().zzjd().zzbx("Failed to get MD5");
                zzc = 0;
            } else {
                zzc = zzfk.zzc(messageDigest.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzgiVar.zztt);
            contentValues.put("metadata_fingerprint", Long.valueOf(zzc));
            contentValues.put("metadata", bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return zzc;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing raw event metadata. appId", zzap.zzbv(zzgiVar.zztt), e);
                throw e;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Data loss. Failed to serialize event metadata. appId", zzap.zzbv(zzgiVar.zztt), e2);
            throw e2;
        }
    }

    public final boolean zzii() {
        return zza("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzij() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzbp(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
    /* JADX WARN: Type inference failed for: r5v0, types: [long] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zzah(long r5) {
        /*
            r4 = this;
            r4.zzaf()
            r4.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            boolean r1 = r5.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            if (r1 != 0) goto L34
            com.google.android.gms.measurement.internal.zzap r6 = r4.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            com.google.android.gms.measurement.internal.zzar r6 = r6.zzjl()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zzbx(r1)     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            if (r5 == 0) goto L33
            r5.close()
        L33:
            return r0
        L34:
            java.lang.String r6 = r5.getString(r6)     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            if (r5 == 0) goto L3d
            r5.close()
        L3d:
            return r6
        L3e:
            r6 = move-exception
            goto L45
        L40:
            r6 = move-exception
            r5 = r0
            goto L59
        L43:
            r6 = move-exception
            r5 = r0
        L45:
            com.google.android.gms.measurement.internal.zzap r1 = r4.zzgo()     // Catch: java.lang.Throwable -> L58
            com.google.android.gms.measurement.internal.zzar r1 = r1.zzjd()     // Catch: java.lang.Throwable -> L58
            java.lang.String r2 = "Error selecting expired configs"
            r1.zzg(r2, r6)     // Catch: java.lang.Throwable -> L58
            if (r5 == 0) goto L57
            r5.close()
        L57:
            return r0
        L58:
            r6 = move-exception
        L59:
            if (r5 == 0) goto L5e
            r5.close()
        L5e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zzah(long):java.lang.String");
    }

    public final long zzik() {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                try {
                    if (!rawQuery.moveToFirst()) {
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return -1L;
                    }
                    long j = rawQuery.getLong(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return j;
                } catch (SQLiteException e) {
                    e = e;
                    cursor = rawQuery;
                    zzgo().zzjd().zzg("Error querying raw events", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return -1L;
                } catch (Throwable th) {
                    th = th;
                    cursor = rawQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzgf, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzaf()
            r7.zzcl()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteException -> L77
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteException -> L77
            r4 = 0
            r3[r4] = r8     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteException -> L77
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteException -> L77
            r6 = 1
            r3[r6] = r5     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteException -> L77
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L74 android.database.sqlite.SQLiteException -> L77
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            if (r2 != 0) goto L37
            com.google.android.gms.measurement.internal.zzap r8 = r7.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            com.google.android.gms.measurement.internal.zzar r8 = r8.zzjl()     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            java.lang.String r9 = "Main event not found"
            r8.zzbx(r9)     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            if (r1 == 0) goto L36
            r1.close()
        L36:
            return r0
        L37:
            byte[] r2 = r1.getBlob(r4)     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            long r5 = r1.getLong(r6)     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            int r5 = r2.length     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            com.google.android.gms.internal.measurement.zzyx r2 = com.google.android.gms.internal.measurement.zzyx.zzj(r2, r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            com.google.android.gms.internal.measurement.zzgf r4 = new com.google.android.gms.internal.measurement.zzgf     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            r4.<init>()     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            r4.zza(r2)     // Catch: java.io.IOException -> L5a android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            android.util.Pair r8 = android.util.Pair.create(r4, r3)     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            if (r1 == 0) goto L59
            r1.close()
        L59:
            return r8
        L5a:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzap r3 = r7.zzgo()     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            com.google.android.gms.measurement.internal.zzar r3 = r3.zzjd()     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzap.zzbv(r8)     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            r3.zzd(r4, r8, r9, r2)     // Catch: android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L8c
            if (r1 == 0) goto L71
            r1.close()
        L71:
            return r0
        L72:
            r8 = move-exception
            goto L79
        L74:
            r8 = move-exception
            r1 = r0
            goto L8d
        L77:
            r8 = move-exception
            r1 = r0
        L79:
            com.google.android.gms.measurement.internal.zzap r9 = r7.zzgo()     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.measurement.internal.zzar r9 = r9.zzjd()     // Catch: java.lang.Throwable -> L8c
            java.lang.String r2 = "Error selecting main event"
            r9.zzg(r2, r8)     // Catch: java.lang.Throwable -> L8c
            if (r1 == 0) goto L8b
            r1.close()
        L8b:
            return r0
        L8c:
            r8 = move-exception
        L8d:
            if (r1 == 0) goto L92
            r1.close()
        L92:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    public final boolean zza(String str, Long l, long j, zzgf zzgfVar) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzgfVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        try {
            byte[] bArr = new byte[zzgfVar.zzvu()];
            zzyy zzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgfVar.zza(zzk);
            zzk.zzyt();
            zzgo().zzjl().zze("Saving complex main event, appId, data size", zzgl().zzbs(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("main_event_params", null, contentValues, 5) == -1) {
                    zzgo().zzjd().zzg("Failed to insert complex main event (got -1). appId", zzap.zzbv(str));
                    return false;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing complex main event. appId", zzap.zzbv(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzap.zzbv(str), l, e2);
            return false;
        }
    }

    public final boolean zza(zzy zzyVar, long j, boolean z) {
        zzaf();
        zzcl();
        Preconditions.checkNotNull(zzyVar);
        Preconditions.checkNotEmpty(zzyVar.zztt);
        zzgf zzgfVar = new zzgf();
        zzgfVar.zzawv = Long.valueOf(zzyVar.zzaic);
        zzgfVar.zzawt = new zzgg[zzyVar.zzaid.size()];
        Iterator<String> it = zzyVar.zzaid.iterator();
        int i = 0;
        while (it.hasNext()) {
            String next = it.next();
            zzgg zzggVar = new zzgg();
            int i2 = i + 1;
            zzgfVar.zzawt[i] = zzggVar;
            zzggVar.name = next;
            zzjo().zza(zzggVar, zzyVar.zzaid.get(next));
            i = i2;
        }
        try {
            byte[] bArr = new byte[zzgfVar.zzvu()];
            zzyy zzk = zzyy.zzk(bArr, 0, bArr.length);
            zzgfVar.zza(zzk);
            zzk.zzyt();
            zzgo().zzjl().zze("Saving event, name, data size", zzgl().zzbs(zzyVar.name), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzyVar.zztt);
            contentValues.put("name", zzyVar.name);
            contentValues.put(AppMeasurement.Param.TIMESTAMP, Long.valueOf(zzyVar.timestamp));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) == -1) {
                    zzgo().zzjd().zzg("Failed to insert raw event (got -1). appId", zzap.zzbv(zzyVar.zztt));
                    return false;
                }
                return true;
            } catch (SQLiteException e) {
                zzgo().zzjd().zze("Error storing raw event. appId", zzap.zzbv(zzyVar.zztt), e);
                return false;
            }
        } catch (IOException e2) {
            zzgo().zzjd().zze("Data loss. Failed to serialize event params/data. appId", zzap.zzbv(zzyVar.zztt), e2);
            return false;
        }
    }

    private final boolean zzil() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }
}
