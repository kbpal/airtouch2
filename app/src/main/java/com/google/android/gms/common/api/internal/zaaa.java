package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zaaa implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafh;
    private SignInConnectionListener zafi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaaa(zax zaxVar, SignInConnectionListener signInConnectionListener) {
        this.zafh = zaxVar;
        this.zafi = signInConnectionListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void cancel() {
        this.zafi.onComplete();
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<Map<zai<?>, String>> task) {
        Lock lock;
        Lock lock2;
        boolean z;
        boolean z2;
        Map map;
        Map map2;
        boolean zaa;
        Map map3;
        Map map4;
        Map map5;
        Map map6;
        ConnectionResult zaaf;
        Condition condition;
        Map map7;
        Map map8;
        Map map9;
        lock = this.zafh.zaen;
        lock.lock();
        try {
            z = this.zafh.zafc;
            if (!z) {
                this.zafi.onComplete();
                return;
            }
            if (task.isSuccessful()) {
                zax zaxVar = this.zafh;
                map7 = this.zafh.zaeu;
                zaxVar.zafe = new ArrayMap(map7.size());
                map8 = this.zafh.zaeu;
                for (zaw zawVar : map8.values()) {
                    map9 = this.zafh.zafe;
                    map9.put(zawVar.zak(), ConnectionResult.RESULT_SUCCESS);
                }
            } else if (task.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task.getException();
                z2 = this.zafh.zafa;
                if (z2) {
                    zax zaxVar2 = this.zafh;
                    map = this.zafh.zaeu;
                    zaxVar2.zafe = new ArrayMap(map.size());
                    map2 = this.zafh.zaeu;
                    for (zaw zawVar2 : map2.values()) {
                        Object zak = zawVar2.zak();
                        ConnectionResult connectionResult = availabilityException.getConnectionResult(zawVar2);
                        zaa = this.zafh.zaa(zawVar2, connectionResult);
                        if (zaa) {
                            map3 = this.zafh.zafe;
                            map3.put(zak, new ConnectionResult(16));
                        } else {
                            map4 = this.zafh.zafe;
                            map4.put(zak, connectionResult);
                        }
                    }
                } else {
                    this.zafh.zafe = availabilityException.zaj();
                }
            } else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                this.zafh.zafe = Collections.emptyMap();
            }
            if (this.zafh.isConnected()) {
                map5 = this.zafh.zafd;
                map6 = this.zafh.zafe;
                map5.putAll(map6);
                zaaf = this.zafh.zaaf();
                if (zaaf == null) {
                    this.zafh.zaad();
                    this.zafh.zaae();
                    condition = this.zafh.zaey;
                    condition.signalAll();
                }
            }
            this.zafi.onComplete();
        } finally {
            lock2 = this.zafh.zaen;
            lock2.unlock();
        }
    }
}
