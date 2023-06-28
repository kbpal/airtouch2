package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzy {
    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final zzz zzb(Context context, String str) throws zzaa {
        zzz zzd = zzd(context, str);
        return zzd != null ? zzd : zzc(context, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final zzz zzc(Context context, String str) {
        zzz zzzVar = new zzz(zza.zzb(), System.currentTimeMillis());
        zzz zza = zza(context, str, zzzVar, true);
        if (zza != null && !zza.equals(zzzVar)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
            }
            return zza;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Generated new key");
        }
        zza(context, str, zzzVar);
        return zzzVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Context context) {
        File[] listFiles;
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    @Nullable
    private final zzz zzd(Context context, String str) throws zzaa {
        zzz zze;
        try {
            zze = zze(context, str);
        } catch (zzaa e) {
            e = e;
        }
        if (zze != null) {
            zza(context, str, zze);
            return zze;
        }
        e = null;
        try {
            zzz zza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
            if (zza != null) {
                zza(context, str, zza, false);
                return zza;
            }
        } catch (zzaa e2) {
            e = e2;
        }
        if (e == null) {
            return null;
        }
        throw e;
    }

    private static KeyPair zzc(String str, String str2) throws zzaa {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(decode)), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
                sb.append("Invalid key stored ");
                sb.append(valueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                throw new zzaa(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzaa(e2);
        }
    }

    @Nullable
    private final zzz zze(Context context, String str) throws zzaa {
        File zzf = zzf(context, str);
        if (zzf.exists()) {
            try {
                return zza(zzf);
            } catch (zzaa | IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 40);
                    sb.append("Failed to read key from file, retrying: ");
                    sb.append(valueOf);
                    Log.d("FirebaseInstanceId", sb.toString());
                }
                try {
                    return zza(zzf);
                } catch (IOException e2) {
                    String valueOf2 = String.valueOf(e2);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 45);
                    sb2.append("IID file exists, but failed to read from it: ");
                    sb2.append(valueOf2);
                    Log.w("FirebaseInstanceId", sb2.toString());
                    throw new zzaa(e2);
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b1 A[Catch: Throwable -> 0x00b8, TRY_ENTER, TryCatch #6 {IOException -> 0x00c2, blocks: (B:6:0x0039, B:16:0x0062, B:25:0x00a2, B:7:0x0043, B:15:0x005f, B:34:0x00b1, B:35:0x00b4, B:24:0x009f), top: B:50:0x0039 }] */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.google.firebase.iid.zzz zza(android.content.Context r9, java.lang.String r10, com.google.firebase.iid.zzz r11, boolean r12) {
        /*
            r8 = this;
            java.lang.String r0 = "FirebaseInstanceId"
            r1 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r1)
            if (r0 == 0) goto L10
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r2 = "Writing key to properties file"
            android.util.Log.d(r0, r2)
        L10:
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.lang.String r2 = "pub"
            java.lang.String r3 = com.google.firebase.iid.zzz.zza(r11)
            r0.setProperty(r2, r3)
            java.lang.String r2 = "pri"
            java.lang.String r3 = com.google.firebase.iid.zzz.zzb(r11)
            r0.setProperty(r2, r3)
            java.lang.String r2 = "cre"
            long r3 = com.google.firebase.iid.zzz.zzc(r11)
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r0.setProperty(r2, r3)
            java.io.File r9 = zzf(r9, r10)
            r10 = 0
            r9.createNewFile()     // Catch: java.io.IOException -> Lc2
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch: java.io.IOException -> Lc2
            java.lang.String r3 = "rw"
            r2.<init>(r9, r3)     // Catch: java.io.IOException -> Lc2
            java.nio.channels.FileChannel r9 = r2.getChannel()     // Catch: java.lang.Throwable -> Lb8
            r9.lock()     // Catch: java.lang.Throwable -> La9
            r3 = 0
            if (r12 == 0) goto L93
            long r5 = r9.size()     // Catch: java.lang.Throwable -> La9
            int r12 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r12 <= 0) goto L93
            r9.position(r3)     // Catch: java.lang.Throwable -> L66 java.lang.Throwable -> La6 java.lang.Throwable -> La9
            com.google.firebase.iid.zzz r12 = zza(r9)     // Catch: java.lang.Throwable -> L66 java.lang.Throwable -> La6 java.lang.Throwable -> La9
            if (r9 == 0) goto L62
            zza(r10, r9)     // Catch: java.lang.Throwable -> Lb8
        L62:
            zza(r10, r2)     // Catch: java.io.IOException -> Lc2
            return r12
        L66:
            r12 = move-exception
            java.lang.String r5 = "FirebaseInstanceId"
            boolean r1 = android.util.Log.isLoggable(r5, r1)     // Catch: java.lang.Throwable -> La9
            if (r1 == 0) goto L93
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch: java.lang.Throwable -> La9
            java.lang.String r5 = java.lang.String.valueOf(r12)     // Catch: java.lang.Throwable -> La9
            int r5 = r5.length()     // Catch: java.lang.Throwable -> La9
            int r5 = r5 + 64
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La9
            r6.<init>(r5)     // Catch: java.lang.Throwable -> La9
            java.lang.String r5 = "Tried reading key pair before writing new one, but failed with: "
            r6.append(r5)     // Catch: java.lang.Throwable -> La9
            r6.append(r12)     // Catch: java.lang.Throwable -> La9
            java.lang.String r12 = r6.toString()     // Catch: java.lang.Throwable -> La9
            android.util.Log.d(r1, r12)     // Catch: java.lang.Throwable -> La9
        L93:
            r9.position(r3)     // Catch: java.lang.Throwable -> La9
            java.io.OutputStream r12 = java.nio.channels.Channels.newOutputStream(r9)     // Catch: java.lang.Throwable -> La9
            r0.store(r12, r10)     // Catch: java.lang.Throwable -> La9
            if (r9 == 0) goto La2
            zza(r10, r9)     // Catch: java.lang.Throwable -> Lb8
        La2:
            zza(r10, r2)     // Catch: java.io.IOException -> Lc2
            return r11
        La6:
            r11 = move-exception
            r12 = r10
            goto Laf
        La9:
            r11 = move-exception
            throw r11     // Catch: java.lang.Throwable -> Lab
        Lab:
            r12 = move-exception
            r7 = r12
            r12 = r11
            r11 = r7
        Laf:
            if (r9 == 0) goto Lb4
            zza(r12, r9)     // Catch: java.lang.Throwable -> Lb8
        Lb4:
            throw r11     // Catch: java.lang.Throwable -> Lb8
        Lb5:
            r9 = move-exception
            r11 = r10
            goto Lbe
        Lb8:
            r9 = move-exception
            throw r9     // Catch: java.lang.Throwable -> Lba
        Lba:
            r11 = move-exception
            r7 = r11
            r11 = r9
            r9 = r7
        Lbe:
            zza(r11, r2)     // Catch: java.io.IOException -> Lc2
            throw r9     // Catch: java.io.IOException -> Lc2
        Lc2:
            r9 = move-exception
            java.lang.String r11 = "FirebaseInstanceId"
            java.lang.String r9 = java.lang.String.valueOf(r9)
            java.lang.String r12 = java.lang.String.valueOf(r9)
            int r12 = r12.length()
            int r12 = r12 + 21
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r12)
            java.lang.String r12 = "Failed to write key: "
            r0.append(r12)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            android.util.Log.w(r11, r9)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzy.zza(android.content.Context, java.lang.String, com.google.firebase.iid.zzz, boolean):com.google.firebase.iid.zzz");
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir == null || !noBackupFilesDir.isDirectory()) {
            Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
            return context.getFilesDir();
        }
        return noBackupFilesDir;
    }

    private static File zzf(Context context, String str) {
        String sb;
        if (TextUtils.isEmpty(str)) {
            sb = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes(HttpRequest.CHARSET_UTF8), 11);
                StringBuilder sb2 = new StringBuilder(String.valueOf(encodeToString).length() + 33);
                sb2.append("com.google.InstanceId_");
                sb2.append(encodeToString);
                sb2.append(".properties");
                sb = sb2.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), sb);
    }

    private final zzz zza(File file) throws zzaa, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileChannel channel = fileInputStream.getChannel();
            channel.lock(0L, Long.MAX_VALUE, true);
            zzz zza = zza(channel);
            if (channel != null) {
                zza((Throwable) null, channel);
            }
            zza((Throwable) null, fileInputStream);
            return zza;
        } finally {
        }
    }

    private static zzz zza(FileChannel fileChannel) throws zzaa, IOException {
        Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        String property = properties.getProperty("pub");
        String property2 = properties.getProperty("pri");
        if (property == null || property2 == null) {
            throw new zzaa("Invalid properties file");
        }
        try {
            return new zzz(zzc(property, property2), Long.parseLong(properties.getProperty("cre")));
        } catch (NumberFormatException e) {
            throw new zzaa(e);
        }
    }

    @Nullable
    private static zzz zza(SharedPreferences sharedPreferences, String str) throws zzaa {
        String string = sharedPreferences.getString(zzaw.zzd(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzaw.zzd(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzz(zzc(string, string2), zzb(sharedPreferences, str));
    }

    private final void zza(Context context, String str, zzz zzzVar) {
        String zzv;
        String zzw;
        long j;
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzzVar.equals(zza(sharedPreferences, str))) {
                return;
            }
        } catch (zzaa unused) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String zzd = zzaw.zzd(str, "|P|");
        zzv = zzzVar.zzv();
        edit.putString(zzd, zzv);
        String zzd2 = zzaw.zzd(str, "|K|");
        zzw = zzzVar.zzw();
        edit.putString(zzd2, zzw);
        String zzd3 = zzaw.zzd(str, "cre");
        j = zzzVar.zzbs;
        edit.putString(zzd3, String.valueOf(j));
        edit.commit();
    }

    private static long zzb(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzaw.zzd(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
                return 0L;
            }
        }
        return 0L;
    }

    private static /* synthetic */ void zza(Throwable th, FileChannel fileChannel) {
        if (th == null) {
            fileChannel.close();
            return;
        }
        try {
            fileChannel.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zzc.zza(th, th2);
        }
    }

    private static /* synthetic */ void zza(Throwable th, RandomAccessFile randomAccessFile) {
        if (th == null) {
            randomAccessFile.close();
            return;
        }
        try {
            randomAccessFile.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zzc.zza(th, th2);
        }
    }

    private static /* synthetic */ void zza(Throwable th, FileInputStream fileInputStream) {
        if (th == null) {
            fileInputStream.close();
            return;
        }
        try {
            fileInputStream.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.firebase_messaging.zzc.zza(th, th2);
        }
    }
}
