package com.google.android.gms.measurement.internal;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzge;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzj extends zzez {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(zzfa zzfaVar) {
        super(zzfaVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzez
    protected final boolean zzgt() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x042f  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x089d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02bb  */
    @android.support.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.measurement.zzgd[] zza(java.lang.String r84, com.google.android.gms.internal.measurement.zzgf[] r85, com.google.android.gms.internal.measurement.zzgl[] r86) {
        /*
            Method dump skipped, instructions count: 3190
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzj.zza(java.lang.String, com.google.android.gms.internal.measurement.zzgf[], com.google.android.gms.internal.measurement.zzgl[]):com.google.android.gms.internal.measurement.zzgd[]");
    }

    private final Boolean zza(zzfv zzfvVar, String str, zzgg[] zzggVarArr, long j) {
        zzfw[] zzfwVarArr;
        zzfw[] zzfwVarArr2;
        Boolean zza;
        if (zzfvVar.zzavi != null) {
            Boolean zza2 = zza(j, zzfvVar.zzavi);
            if (zza2 == null) {
                return null;
            }
            if (!zza2.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        for (zzfw zzfwVar : zzfvVar.zzavg) {
            if (TextUtils.isEmpty(zzfwVar.zzavn)) {
                zzgo().zzjg().zzg("null or empty param name in filter. event", zzgl().zzbs(str));
                return null;
            }
            hashSet.add(zzfwVar.zzavn);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzgg zzggVar : zzggVarArr) {
            if (hashSet.contains(zzggVar.name)) {
                if (zzggVar.zzawx != null) {
                    arrayMap.put(zzggVar.name, zzggVar.zzawx);
                } else if (zzggVar.zzauh != null) {
                    arrayMap.put(zzggVar.name, zzggVar.zzauh);
                } else if (zzggVar.zzamp != null) {
                    arrayMap.put(zzggVar.name, zzggVar.zzamp);
                } else {
                    zzgo().zzjg().zze("Unknown value for param. event, param", zzgl().zzbs(str), zzgl().zzbt(zzggVar.name));
                    return null;
                }
            }
        }
        for (zzfw zzfwVar2 : zzfvVar.zzavg) {
            boolean equals = Boolean.TRUE.equals(zzfwVar2.zzavm);
            String str2 = zzfwVar2.zzavn;
            if (TextUtils.isEmpty(str2)) {
                zzgo().zzjg().zzg("Event has empty param name. event", zzgl().zzbs(str));
                return null;
            }
            V v = arrayMap.get(str2);
            if (v instanceof Long) {
                if (zzfwVar2.zzavl == null) {
                    zzgo().zzjg().zze("No number filter for long param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                Boolean zza3 = zza(((Long) v).longValue(), zzfwVar2.zzavl);
                if (zza3 == null) {
                    return null;
                }
                if ((true ^ zza3.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (v instanceof Double) {
                if (zzfwVar2.zzavl == null) {
                    zzgo().zzjg().zze("No number filter for double param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                Boolean zza4 = zza(((Double) v).doubleValue(), zzfwVar2.zzavl);
                if (zza4 == null) {
                    return null;
                }
                if ((true ^ zza4.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (v instanceof String) {
                if (zzfwVar2.zzavk != null) {
                    zza = zza((String) v, zzfwVar2.zzavk);
                } else if (zzfwVar2.zzavl != null) {
                    String str3 = (String) v;
                    if (zzfg.zzcp(str3)) {
                        zza = zza(str3, zzfwVar2.zzavl);
                    } else {
                        zzgo().zzjg().zze("Invalid param value for number filter. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                        return null;
                    }
                } else {
                    zzgo().zzjg().zze("No filter for String param. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return null;
                }
                if (zza == null) {
                    return null;
                }
                if ((true ^ zza.booleanValue()) ^ equals) {
                    return false;
                }
            } else {
                if (v == 0) {
                    zzgo().zzjl().zze("Missing param for filter. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                    return false;
                }
                zzgo().zzjg().zze("Unknown param type. event, param", zzgl().zzbs(str), zzgl().zzbt(str2));
                return null;
            }
        }
        return true;
    }

    private final Boolean zza(zzfy zzfyVar, zzgl zzglVar) {
        zzfw zzfwVar = zzfyVar.zzavv;
        if (zzfwVar == null) {
            zzgo().zzjg().zzg("Missing property filter. property", zzgl().zzbu(zzglVar.name));
            return null;
        }
        boolean equals = Boolean.TRUE.equals(zzfwVar.zzavm);
        if (zzglVar.zzawx != null) {
            if (zzfwVar.zzavl == null) {
                zzgo().zzjg().zzg("No number filter for long property. property", zzgl().zzbu(zzglVar.name));
                return null;
            }
            return zza(zza(zzglVar.zzawx.longValue(), zzfwVar.zzavl), equals);
        } else if (zzglVar.zzauh != null) {
            if (zzfwVar.zzavl == null) {
                zzgo().zzjg().zzg("No number filter for double property. property", zzgl().zzbu(zzglVar.name));
                return null;
            }
            return zza(zza(zzglVar.zzauh.doubleValue(), zzfwVar.zzavl), equals);
        } else if (zzglVar.zzamp != null) {
            if (zzfwVar.zzavk == null) {
                if (zzfwVar.zzavl == null) {
                    zzgo().zzjg().zzg("No string or number filter defined. property", zzgl().zzbu(zzglVar.name));
                } else if (zzfg.zzcp(zzglVar.zzamp)) {
                    return zza(zza(zzglVar.zzamp, zzfwVar.zzavl), equals);
                } else {
                    zzgo().zzjg().zze("Invalid user property value for Numeric number filter. property, value", zzgl().zzbu(zzglVar.name), zzglVar.zzamp);
                }
                return null;
            }
            return zza(zza(zzglVar.zzamp, zzfwVar.zzavk), equals);
        } else {
            zzgo().zzjg().zzg("User property has no value, property", zzgl().zzbu(zzglVar.name));
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzfz zzfzVar) {
        String str2;
        ArrayList arrayList;
        Preconditions.checkNotNull(zzfzVar);
        if (str == null || zzfzVar.zzavw == null || zzfzVar.zzavw.intValue() == 0) {
            return null;
        }
        if (zzfzVar.zzavw.intValue() == 6) {
            if (zzfzVar.zzavz == null || zzfzVar.zzavz.length == 0) {
                return null;
            }
        } else if (zzfzVar.zzavx == null) {
            return null;
        }
        int intValue = zzfzVar.zzavw.intValue();
        boolean z = zzfzVar.zzavy != null && zzfzVar.zzavy.booleanValue();
        if (z || intValue == 1 || intValue == 6) {
            str2 = zzfzVar.zzavx;
        } else {
            str2 = zzfzVar.zzavx.toUpperCase(Locale.ENGLISH);
        }
        String str3 = str2;
        if (zzfzVar.zzavz == null) {
            arrayList = null;
        } else {
            String[] strArr = zzfzVar.zzavz;
            if (z) {
                arrayList = Arrays.asList(strArr);
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (String str4 : strArr) {
                    arrayList2.add(str4.toUpperCase(Locale.ENGLISH));
                }
                arrayList = arrayList2;
            }
        }
        return zza(str, intValue, z, str3, arrayList, intValue == 1 ? str3 : null);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    zzgo().zzjg().zzg("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private final Boolean zza(long j, zzfx zzfxVar) {
        try {
            return zza(new BigDecimal(j), zzfxVar, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(double d, zzfx zzfxVar) {
        try {
            return zza(new BigDecimal(d), zzfxVar, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(String str, zzfx zzfxVar) {
        if (zzfg.zzcp(str)) {
            try {
                return zza(new BigDecimal(str), zzfxVar, 0.0d);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0072, code lost:
        if (r3 != null) goto L24;
     */
    @com.google.android.gms.common.util.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Boolean zza(java.math.BigDecimal r7, com.google.android.gms.internal.measurement.zzfx r8, double r9) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzj.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzfx, double):java.lang.Boolean");
    }

    private static zzge[] zzd(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        int i = 0;
        zzge[] zzgeVarArr = new zzge[map.size()];
        for (Integer num : map.keySet()) {
            zzge zzgeVar = new zzge();
            zzgeVar.zzawq = num;
            zzgeVar.zzawr = map.get(num);
            zzgeVarArr[i] = zzgeVar;
            i++;
        }
        return zzgeVarArr;
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = map.get(Integer.valueOf(i));
        long j2 = j / 1000;
        if (l == null || j2 > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j2));
        }
    }

    private static void zzb(Map<Integer, List<Long>> map, int i, long j) {
        List<Long> list = map.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList<>();
            map.put(Integer.valueOf(i), list);
        }
        list.add(Long.valueOf(j / 1000));
    }
}
