package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
final class zzfj {
    final String name;
    final String origin;
    final Object value;
    final long zzaue;
    final String zztt;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfj(String str, String str2, String str3, long j, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(obj);
        this.zztt = str;
        this.origin = str2;
        this.name = str3;
        this.zzaue = j;
        this.value = obj;
    }
}
