package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class zzc extends Fragment implements LifecycleFragment {
    private static WeakHashMap<FragmentActivity, WeakReference<zzc>> zzbd = new WeakHashMap<>();
    private Map<String, LifecycleCallback> zzbe = new ArrayMap();
    private int zzbf = 0;
    private Bundle zzbg;

    public static zzc zza(FragmentActivity fragmentActivity) {
        zzc zzcVar;
        WeakReference<zzc> weakReference = zzbd.get(fragmentActivity);
        if (weakReference == null || (zzcVar = weakReference.get()) == null) {
            try {
                zzc zzcVar2 = (zzc) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
                if (zzcVar2 == null || zzcVar2.isRemoving()) {
                    zzcVar2 = new zzc();
                    fragmentActivity.getSupportFragmentManager().beginTransaction().add(zzcVar2, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
                }
                zzbd.put(fragmentActivity, new WeakReference<>(zzcVar2));
                return zzcVar2;
            } catch (ClassCastException e) {
                throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
            }
        }
        return zzcVar;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return cls.cast(this.zzbe.get(str));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String str, @NonNull LifecycleCallback lifecycleCallback) {
        if (!this.zzbe.containsKey(str)) {
            this.zzbe.put(str, lifecycleCallback);
            if (this.zzbf > 0) {
                new zze(Looper.getMainLooper()).post(new zzd(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 59);
        sb.append("LifecycleCallback with tag ");
        sb.append(str);
        sb.append(" already added to this fragment.");
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isCreated() {
        return this.zzbf > 0;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isStarted() {
        return this.zzbf >= 2;
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbf = 1;
        this.zzbg = bundle;
        for (Map.Entry<String, LifecycleCallback> entry : this.zzbe.entrySet()) {
            entry.getValue().onCreate(bundle != null ? bundle.getBundle(entry.getKey()) : null);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzbf = 2;
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onStart();
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzbf = 3;
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onResume();
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            return;
        }
        for (Map.Entry<String, LifecycleCallback> entry : this.zzbe.entrySet()) {
            Bundle bundle2 = new Bundle();
            entry.getValue().onSaveInstanceState(bundle2);
            bundle.putBundle(entry.getKey(), bundle2);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzbf = 4;
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onStop();
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzbf = 5;
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.onDestroy();
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback lifecycleCallback : this.zzbe.values()) {
            lifecycleCallback.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final /* synthetic */ Activity getLifecycleActivity() {
        return getActivity();
    }
}
