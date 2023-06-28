package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzev {
    private long startTime;
    private final Clock zzrz;

    public zzev(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.zzrz = clock;
    }

    public final void start() {
        this.startTime = this.zzrz.elapsedRealtime();
    }

    public final void clear() {
        this.startTime = 0L;
    }

    public final boolean zzj(long j) {
        return this.startTime == 0 || this.zzrz.elapsedRealtime() - this.startTime >= 3600000;
    }
}
