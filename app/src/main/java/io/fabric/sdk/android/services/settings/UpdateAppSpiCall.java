package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequestFactory;

/* loaded from: classes.dex */
public class UpdateAppSpiCall extends AbstractAppSpiCall {
    @Override // io.fabric.sdk.android.services.settings.AbstractAppSpiCall, io.fabric.sdk.android.services.settings.AppSpiCall
    public /* bridge */ /* synthetic */ boolean invoke(AppRequestData appRequestData) {
        return super.invoke(appRequestData);
    }

    public UpdateAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.PUT);
    }
}
