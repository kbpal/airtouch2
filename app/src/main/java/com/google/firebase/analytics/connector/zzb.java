package com.google.firebase.analytics.connector;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class zzb implements EventHandler {
    static final EventHandler zzbsj = new zzb();

    private zzb() {
    }

    @Override // com.google.firebase.events.EventHandler
    public final void handle(Event event) {
        AnalyticsConnectorImpl.zza(event);
    }
}
