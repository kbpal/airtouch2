package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public class zaae extends zal {
    private GoogleApiManager zabm;
    private final ArraySet<zai<?>> zafo;

    public static void zaa(Activity activity, GoogleApiManager googleApiManager, zai<?> zaiVar) {
        LifecycleFragment fragment = getFragment(activity);
        zaae zaaeVar = (zaae) fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zaae.class);
        if (zaaeVar == null) {
            zaaeVar = new zaae(fragment);
        }
        zaaeVar.zabm = googleApiManager;
        Preconditions.checkNotNull(zaiVar, "ApiKey cannot be null");
        zaaeVar.zafo.add(zaiVar);
        googleApiManager.zaa(zaaeVar);
    }

    private zaae(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zafo = new ArraySet<>();
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    @Override // com.google.android.gms.common.api.internal.zal, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        zaak();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onResume() {
        super.onResume();
        zaak();
    }

    @Override // com.google.android.gms.common.api.internal.zal, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.zabm.zab(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.zal
    public final void zaa(ConnectionResult connectionResult, int i) {
        this.zabm.zaa(connectionResult, i);
    }

    @Override // com.google.android.gms.common.api.internal.zal
    protected final void zao() {
        this.zabm.zao();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ArraySet<zai<?>> zaaj() {
        return this.zafo;
    }

    private final void zaak() {
        if (this.zafo.isEmpty()) {
            return;
        }
        this.zabm.zaa(this);
    }
}
