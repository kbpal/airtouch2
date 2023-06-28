package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    static final String BUILD_VERSION_PARAM = "build_version";
    static final String DISPLAY_VERSION_PARAM = "display_version";
    static final String HEADER_DEVICE_MODEL = "X-CRASHLYTICS-DEVICE-MODEL";
    static final String HEADER_INSTALLATION_ID = "X-CRASHLYTICS-INSTALLATION-ID";
    static final String HEADER_OS_BUILD_VERSION = "X-CRASHLYTICS-OS-BUILD-VERSION";
    static final String HEADER_OS_DISPLAY_VERSION = "X-CRASHLYTICS-OS-DISPLAY-VERSION";
    static final String ICON_HASH = "icon_hash";
    static final String INSTANCE_PARAM = "instance";
    static final String SOURCE_PARAM = "source";

    boolean requestWasSuccessful(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }

    public DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        this(kit, str, str2, httpRequestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ad  */
    @Override // io.fabric.sdk.android.services.settings.SettingsSpiCall
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject invoke(io.fabric.sdk.android.services.settings.SettingsRequest r8) {
        /*
            r7 = this;
            r0 = 0
            java.util.Map r1 = r7.getQueryParamsFor(r8)     // Catch: java.lang.Throwable -> L75 io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L7a
            io.fabric.sdk.android.services.network.HttpRequest r2 = r7.getHttpRequest(r1)     // Catch: java.lang.Throwable -> L75 io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L7a
            io.fabric.sdk.android.services.network.HttpRequest r8 = r7.applyHeadersTo(r2, r8)     // Catch: java.lang.Throwable -> L6f io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L72
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            java.lang.String r3 = "Fabric"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            r4.<init>()     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            java.lang.String r5 = "Requesting settings from "
            r4.append(r5)     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            java.lang.String r5 = r7.getUrl()     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            r4.append(r5)     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            java.lang.String r4 = r4.toString()     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            r2.mo9d(r3, r4)     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            java.lang.String r3 = "Fabric"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            r4.<init>()     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            java.lang.String r5 = "Settings query params were: "
            r4.append(r5)     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            r4.append(r1)     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            java.lang.String r1 = r4.toString()     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            r2.mo9d(r3, r1)     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            org.json.JSONObject r1 = r7.handleResponse(r8)     // Catch: io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException -> L6d java.lang.Throwable -> Laa
            if (r8 == 0) goto L6b
            io.fabric.sdk.android.Logger r0 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Fabric"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Settings request ID: "
            r3.append(r4)
            java.lang.String r4 = "X-REQUEST-ID"
            java.lang.String r8 = r8.header(r4)
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r0.mo9d(r2, r8)
        L6b:
            r0 = r1
            goto La9
        L6d:
            r1 = move-exception
            goto L7c
        L6f:
            r0 = move-exception
            r8 = r2
            goto Lab
        L72:
            r1 = move-exception
            r8 = r2
            goto L7c
        L75:
            r8 = move-exception
            r6 = r0
            r0 = r8
            r8 = r6
            goto Lab
        L7a:
            r1 = move-exception
            r8 = r0
        L7c:
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r3 = "Fabric"
            java.lang.String r4 = "Settings request failed."
            r2.mo6e(r3, r4, r1)     // Catch: java.lang.Throwable -> Laa
            if (r8 == 0) goto La9
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Fabric"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Settings request ID: "
            r3.append(r4)
            java.lang.String r4 = "X-REQUEST-ID"
            java.lang.String r8 = r8.header(r4)
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r1.mo9d(r2, r8)
        La9:
            return r0
        Laa:
            r0 = move-exception
        Lab:
            if (r8 == 0) goto Lcd
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Settings request ID: "
            r2.append(r3)
            java.lang.String r3 = "X-REQUEST-ID"
            java.lang.String r8 = r8.header(r3)
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            java.lang.String r2 = "Fabric"
            r1.mo9d(r2, r8)
        Lcd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.settings.DefaultSettingsSpiCall.invoke(io.fabric.sdk.android.services.settings.SettingsRequest):org.json.JSONObject");
    }

    JSONObject handleResponse(HttpRequest httpRequest) {
        int code = httpRequest.code();
        Logger logger = Fabric.getLogger();
        logger.mo9d(Fabric.TAG, "Settings result was: " + code);
        if (requestWasSuccessful(code)) {
            return getJsonObjectFrom(httpRequest.body());
        }
        Logger logger2 = Fabric.getLogger();
        logger2.mo7e(Fabric.TAG, "Failed to retrieve settings from " + getUrl());
        return null;
    }

    private JSONObject getJsonObjectFrom(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            Logger logger = Fabric.getLogger();
            logger.mo8d(Fabric.TAG, "Failed to parse settings JSON from " + getUrl(), e);
            Logger logger2 = Fabric.getLogger();
            logger2.mo9d(Fabric.TAG, "Settings response " + str);
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest settingsRequest) {
        HashMap hashMap = new HashMap();
        hashMap.put(BUILD_VERSION_PARAM, settingsRequest.buildVersion);
        hashMap.put(DISPLAY_VERSION_PARAM, settingsRequest.displayVersion);
        hashMap.put("source", Integer.toString(settingsRequest.source));
        if (settingsRequest.iconHash != null) {
            hashMap.put(ICON_HASH, settingsRequest.iconHash);
        }
        String str = settingsRequest.instanceId;
        if (!CommonUtils.isNullOrEmpty(str)) {
            hashMap.put(INSTANCE_PARAM, str);
        }
        return hashMap;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, SettingsRequest settingsRequest) {
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_API_KEY, settingsRequest.apiKey);
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE);
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
        applyNonNullHeader(httpRequest, "Accept", "application/json");
        applyNonNullHeader(httpRequest, HEADER_DEVICE_MODEL, settingsRequest.deviceModel);
        applyNonNullHeader(httpRequest, HEADER_OS_BUILD_VERSION, settingsRequest.osBuildVersion);
        applyNonNullHeader(httpRequest, HEADER_OS_DISPLAY_VERSION, settingsRequest.osDisplayVersion);
        applyNonNullHeader(httpRequest, HEADER_INSTALLATION_ID, settingsRequest.installationId);
        return httpRequest;
    }

    private void applyNonNullHeader(HttpRequest httpRequest, String str, String str2) {
        if (str2 != null) {
            httpRequest.header(str, str2);
        }
    }
}
