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
public final class zaz implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafh;

    private zaz(zax zaxVar) {
        this.zafh = zaxVar;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<Map<zai<?>, String>> task) {
        Lock lock;
        Lock lock2;
        boolean z;
        boolean z2;
        ConnectionResult zaaf;
        Map map;
        Map map2;
        boolean zaa;
        Map map3;
        Map map4;
        Map map5;
        ConnectionResult connectionResult;
        zaaw zaawVar;
        ConnectionResult connectionResult2;
        Condition condition;
        Map map6;
        Map map7;
        ConnectionResult zaaf2;
        Map map8;
        Map map9;
        Map map10;
        lock = this.zafh.zaen;
        lock.lock();
        try {
            z = this.zafh.zafc;
            if (z) {
                if (task.isSuccessful()) {
                    zax zaxVar = this.zafh;
                    map8 = this.zafh.zaet;
                    zaxVar.zafd = new ArrayMap(map8.size());
                    map9 = this.zafh.zaet;
                    for (zaw zawVar : map9.values()) {
                        map10 = this.zafh.zafd;
                        map10.put(zawVar.zak(), ConnectionResult.RESULT_SUCCESS);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    z2 = this.zafh.zafa;
                    if (z2) {
                        zax zaxVar2 = this.zafh;
                        map = this.zafh.zaet;
                        zaxVar2.zafd = new ArrayMap(map.size());
                        map2 = this.zafh.zaet;
                        for (zaw zawVar2 : map2.values()) {
                            Object zak = zawVar2.zak();
                            ConnectionResult connectionResult3 = availabilityException.getConnectionResult(zawVar2);
                            zaa = this.zafh.zaa(zawVar2, connectionResult3);
                            if (zaa) {
                                map3 = this.zafh.zafd;
                                map3.put(zak, new ConnectionResult(16));
                            } else {
                                map4 = this.zafh.zafd;
                                map4.put(zak, connectionResult3);
                            }
                        }
                    } else {
                        this.zafh.zafd = availabilityException.zaj();
                    }
                    zax zaxVar3 = this.zafh;
                    zaaf = this.zafh.zaaf();
                    zaxVar3.zafg = zaaf;
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    this.zafh.zafd = Collections.emptyMap();
                    this.zafh.zafg = new ConnectionResult(8);
                }
                map5 = this.zafh.zafe;
                if (map5 != null) {
                    map6 = this.zafh.zafd;
                    map7 = this.zafh.zafe;
                    map6.putAll(map7);
                    zax zaxVar4 = this.zafh;
                    zaaf2 = this.zafh.zaaf();
                    zaxVar4.zafg = zaaf2;
                }
                connectionResult = this.zafh.zafg;
                if (connectionResult != null) {
                    zax.zaa(this.zafh, false);
                    zaawVar = this.zafh.zaew;
                    connectionResult2 = this.zafh.zafg;
                    zaawVar.zac(connectionResult2);
                } else {
                    this.zafh.zaad();
                    this.zafh.zaae();
                }
                condition = this.zafh.zaey;
                condition.signalAll();
            }
        } finally {
            lock2 = this.zafh.zaen;
            lock2.unlock();
        }
    }
}
