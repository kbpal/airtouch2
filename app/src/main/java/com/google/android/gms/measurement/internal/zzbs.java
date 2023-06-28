package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzbs extends Thread {
    private final /* synthetic */ zzbo zzapg;
    private final Object zzapj;
    private final BlockingQueue<zzbr<?>> zzapk;

    public zzbs(zzbo zzboVar, String str, BlockingQueue<zzbr<?>> blockingQueue) {
        this.zzapg = zzboVar;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzapj = new Object();
        this.zzapk = blockingQueue;
        setName(str);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Object obj;
        Semaphore semaphore;
        Object obj2;
        zzbs zzbsVar;
        zzbs zzbsVar2;
        Object obj3;
        Object obj4;
        Semaphore semaphore2;
        Object obj5;
        zzbs zzbsVar3;
        zzbs zzbsVar4;
        boolean z;
        Semaphore semaphore3;
        boolean z2 = false;
        while (!z2) {
            try {
                semaphore3 = this.zzapg.zzapc;
                semaphore3.acquire();
                z2 = true;
            } catch (InterruptedException e) {
                zza(e);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzbr<?> poll = this.zzapk.poll();
                if (poll != null) {
                    Process.setThreadPriority(poll.zzapi ? threadPriority : 10);
                    poll.run();
                } else {
                    synchronized (this.zzapj) {
                        if (this.zzapk.peek() == null) {
                            z = this.zzapg.zzapd;
                            if (!z) {
                                try {
                                    this.zzapj.wait(30000L);
                                } catch (InterruptedException e2) {
                                    zza(e2);
                                }
                            }
                        }
                    }
                    obj3 = this.zzapg.zzapb;
                    synchronized (obj3) {
                        if (this.zzapk.peek() == null) {
                            break;
                        }
                    }
                }
            }
            obj4 = this.zzapg.zzapb;
            synchronized (obj4) {
                semaphore2 = this.zzapg.zzapc;
                semaphore2.release();
                obj5 = this.zzapg.zzapb;
                obj5.notifyAll();
                zzbsVar3 = this.zzapg.zzaov;
                if (this == zzbsVar3) {
                    zzbo.zza(this.zzapg, null);
                } else {
                    zzbsVar4 = this.zzapg.zzaow;
                    if (this == zzbsVar4) {
                        zzbo.zzb(this.zzapg, null);
                    } else {
                        this.zzapg.zzgo().zzjd().zzbx("Current scheduler thread is neither worker nor network");
                    }
                }
            }
        } catch (Throwable th) {
            obj = this.zzapg.zzapb;
            synchronized (obj) {
                semaphore = this.zzapg.zzapc;
                semaphore.release();
                obj2 = this.zzapg.zzapb;
                obj2.notifyAll();
                zzbsVar = this.zzapg.zzaov;
                if (this == zzbsVar) {
                    zzbo.zza(this.zzapg, null);
                } else {
                    zzbsVar2 = this.zzapg.zzaow;
                    if (this == zzbsVar2) {
                        zzbo.zzb(this.zzapg, null);
                    } else {
                        this.zzapg.zzgo().zzjd().zzbx("Current scheduler thread is neither worker nor network");
                    }
                }
                throw th;
            }
        }
    }

    public final void zzke() {
        synchronized (this.zzapj) {
            this.zzapj.notifyAll();
        }
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzapg.zzgo().zzjg().zzg(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }
}
