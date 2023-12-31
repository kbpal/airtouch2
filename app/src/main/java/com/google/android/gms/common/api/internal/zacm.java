package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
public final class zacm<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final WeakReference<GoogleApiClient> zadp;
    private final zaco zaks;
    private ResultTransform<? super R, ? extends Result> zakn = null;
    private zacm<? extends Result> zako = null;
    private volatile ResultCallbacks<? super R> zakp = null;
    private PendingResult<R> zakq = null;
    private final Object zadn = new Object();
    private Status zakr = null;
    private boolean zakt = false;

    public zacm(WeakReference<GoogleApiClient> weakReference) {
        Preconditions.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.zadp = weakReference;
        GoogleApiClient googleApiClient = this.zadp.get();
        this.zaks = new zaco(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zacm<? extends Result> zacmVar;
        synchronized (this.zadn) {
            Preconditions.checkState(this.zakn == null, "Cannot call then() twice.");
            Preconditions.checkState(this.zakp == null, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakn = resultTransform;
            zacmVar = new zacm<>(this.zadp);
            this.zako = zacmVar;
            zabu();
        }
        return zacmVar;
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        synchronized (this.zadn) {
            Preconditions.checkState(this.zakp == null, "Cannot call andFinally() twice.");
            Preconditions.checkState(this.zakn == null, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakp = resultCallbacks;
            zabu();
        }
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final void onResult(R r) {
        synchronized (this.zadn) {
            if (r.getStatus().isSuccess()) {
                if (this.zakn != null) {
                    zacc.zabb().submit(new zacn(this, r));
                } else if (zabw()) {
                    this.zakp.onSuccess(r);
                }
            } else {
                zad(r.getStatus());
                zab(r);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zaa(PendingResult<?> pendingResult) {
        synchronized (this.zadn) {
            this.zakq = pendingResult;
            zabu();
        }
    }

    @GuardedBy("mSyncToken")
    private final void zabu() {
        if (this.zakn == null && this.zakp == null) {
            return;
        }
        GoogleApiClient googleApiClient = this.zadp.get();
        if (!this.zakt && this.zakn != null && googleApiClient != null) {
            googleApiClient.zaa(this);
            this.zakt = true;
        }
        if (this.zakr != null) {
            zae(this.zakr);
        } else if (this.zakq != null) {
            this.zakq.setResultCallback(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zad(Status status) {
        synchronized (this.zadn) {
            this.zakr = status;
            zae(this.zakr);
        }
    }

    private final void zae(Status status) {
        synchronized (this.zadn) {
            if (this.zakn != null) {
                Status onFailure = this.zakn.onFailure(status);
                Preconditions.checkNotNull(onFailure, "onFailure must not return null");
                this.zako.zad(onFailure);
            } else if (zabw()) {
                this.zakp.onFailure(status);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zabv() {
        this.zakp = null;
    }

    @GuardedBy("mSyncToken")
    private final boolean zabw() {
        return (this.zakp == null || this.zadp.get() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zab(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                sb.append("Unable to release ");
                sb.append(valueOf);
                Log.w("TransformedResultImpl", sb.toString(), e);
            }
        }
    }
}
