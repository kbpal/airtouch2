package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zze implements AppMeasurement.OnEventListener {
    private final /* synthetic */ zzd zzbsv;

    public zze(zzd zzdVar) {
        this.zzbsv = zzdVar;
    }

    @Override // com.google.android.gms.measurement.AppMeasurement.OnEventListener
    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener;
        if (this.zzbsv.zzbss.contains(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("events", zzc.zzfs(str2));
            analyticsConnectorListener = this.zzbsv.zzbst;
            analyticsConnectorListener.onMessageTriggered(2, bundle2);
        }
    }
}
