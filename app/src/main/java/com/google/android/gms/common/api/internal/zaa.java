package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0503zaa> zack;

    public zaa(Activity activity) {
        this(C0503zaa.zaa(activity));
    }

    @VisibleForTesting(otherwise = 2)
    private zaa(C0503zaa c0503zaa) {
        this.zack = new WeakReference<>(c0503zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0503zaa c0503zaa = this.zack.get();
        if (c0503zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0503zaa.zaa(runnable);
        return this;
    }

    @VisibleForTesting(otherwise = 2)
    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0503zaa extends LifecycleCallback {
        private List<Runnable> zacl;

        /* JADX INFO: Access modifiers changed from: private */
        public static C0503zaa zaa(Activity activity) {
            C0503zaa c0503zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0503zaa = (C0503zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0503zaa.class);
                if (c0503zaa == null) {
                    c0503zaa = new C0503zaa(fragment);
                }
            }
            return c0503zaa;
        }

        private C0503zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacl = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacl.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacl;
                this.zacl = new ArrayList();
            }
            for (Runnable runnable : list) {
                runnable.run();
            }
        }
    }
}
