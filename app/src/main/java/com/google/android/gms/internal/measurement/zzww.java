package com.google.android.gms.internal.measurement;

import com.google.firebase.analytics.FirebaseAnalytics;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzww {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzwt zzwtVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzwtVar, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x01f8, code lost:
        if (((java.lang.Boolean) r6).booleanValue() == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01fa, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x020b, code lost:
        if (((java.lang.Integer) r6).intValue() == 0) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x021c, code lost:
        if (((java.lang.Float) r6).floatValue() == 0.0f) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x022e, code lost:
        if (((java.lang.Double) r6).doubleValue() == 0.0d) goto L78;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zza(com.google.android.gms.internal.measurement.zzwt r12, java.lang.StringBuilder r13, int r14) {
        /*
            Method dump skipped, instructions count: 687
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzww.zza(com.google.android.gms.internal.measurement.zzwt, java.lang.StringBuilder, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zzb(sb, i, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zzb(sb, i, str, entry);
            }
        } else {
            sb.append('\n');
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzxx.zzd(zzud.zzfv((String) obj)));
                sb.append('\"');
            } else if (obj instanceof zzud) {
                sb.append(": \"");
                sb.append(zzxx.zzd((zzud) obj));
                sb.append('\"');
            } else if (obj instanceof zzvm) {
                sb.append(" {");
                zza((zzvm) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i4 = i + 2;
                zzb(sb, i4, "key", entry2.getKey());
                zzb(sb, i4, FirebaseAnalytics.Param.VALUE, entry2.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj.toString());
            }
        }
    }

    private static final String zzga(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
