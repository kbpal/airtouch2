package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitInfo;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class AbstractAppSpiCall extends AbstractSpiCall implements AppSpiCall {
    public static final String APP_BUILD_VERSION_PARAM = "app[build_version]";
    public static final String APP_BUILT_SDK_VERSION_PARAM = "app[built_sdk_version]";
    public static final String APP_DISPLAY_VERSION_PARAM = "app[display_version]";
    public static final String APP_ICON_DATA_PARAM = "app[icon][data]";
    public static final String APP_ICON_HASH_PARAM = "app[icon][hash]";
    public static final String APP_ICON_HEIGHT_PARAM = "app[icon][height]";
    public static final String APP_ICON_PRERENDERED_PARAM = "app[icon][prerendered]";
    public static final String APP_ICON_WIDTH_PARAM = "app[icon][width]";
    public static final String APP_IDENTIFIER_PARAM = "app[identifier]";
    public static final String APP_INSTANCE_IDENTIFIER_PARAM = "app[instance_identifier]";
    public static final String APP_MIN_SDK_VERSION_PARAM = "app[minimum_sdk_version]";
    public static final String APP_NAME_PARAM = "app[name]";
    public static final String APP_SDK_MODULES_PARAM_BUILD_TYPE = "app[build][libraries][%s][type]";
    public static final String APP_SDK_MODULES_PARAM_PREFIX = "app[build][libraries][%s]";
    public static final String APP_SDK_MODULES_PARAM_VERSION = "app[build][libraries][%s][version]";
    public static final String APP_SOURCE_PARAM = "app[source]";
    static final String ICON_CONTENT_TYPE = "application/octet-stream";
    static final String ICON_FILE_NAME = "icon.png";

    public AbstractAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    @Override // io.fabric.sdk.android.services.settings.AppSpiCall
    public boolean invoke(AppRequestData appRequestData) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), appRequestData), appRequestData);
        Logger logger = Fabric.getLogger();
        logger.mo9d(Fabric.TAG, "Sending app info to " + getUrl());
        if (appRequestData.icon != null) {
            Logger logger2 = Fabric.getLogger();
            logger2.mo9d(Fabric.TAG, "App icon hash is " + appRequestData.icon.hash);
            Logger logger3 = Fabric.getLogger();
            logger3.mo9d(Fabric.TAG, "App icon size is " + appRequestData.icon.width + "x" + appRequestData.icon.height);
        }
        int code = applyMultipartDataTo.code();
        String str = HttpRequest.METHOD_POST.equals(applyMultipartDataTo.method()) ? "Create" : "Update";
        Logger logger4 = Fabric.getLogger();
        logger4.mo9d(Fabric.TAG, str + " app request ID: " + applyMultipartDataTo.header(AbstractSpiCall.HEADER_REQUEST_ID));
        Logger logger5 = Fabric.getLogger();
        logger5.mo9d(Fabric.TAG, "Result was " + code);
        return ResponseParser.parse(code) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, AppRequestData appRequestData) {
        return httpRequest.header(AbstractSpiCall.HEADER_API_KEY, appRequestData.apiKey).header(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private io.fabric.sdk.android.services.network.HttpRequest applyMultipartDataTo(io.fabric.sdk.android.services.network.HttpRequest r8, io.fabric.sdk.android.services.settings.AppRequestData r9) {
        /*
            r7 = this;
            java.lang.String r0 = "app[identifier]"
            java.lang.String r1 = r9.appId
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[name]"
            java.lang.String r1 = r9.name
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[display_version]"
            java.lang.String r1 = r9.displayVersion
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[build_version]"
            java.lang.String r1 = r9.buildVersion
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[source]"
            int r1 = r9.source
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[minimum_sdk_version]"
            java.lang.String r1 = r9.minSdkVersion
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = "app[built_sdk_version]"
            java.lang.String r1 = r9.builtSdkVersion
            io.fabric.sdk.android.services.network.HttpRequest r8 = r8.part(r0, r1)
            java.lang.String r0 = r9.instanceIdentifier
            boolean r0 = io.fabric.sdk.android.services.common.CommonUtils.isNullOrEmpty(r0)
            if (r0 != 0) goto L4b
            java.lang.String r0 = "app[instance_identifier]"
            java.lang.String r1 = r9.instanceIdentifier
            r8.part(r0, r1)
        L4b:
            io.fabric.sdk.android.services.settings.IconRequest r0 = r9.icon
            if (r0 == 0) goto Lc6
            r0 = 0
            io.fabric.sdk.android.Kit r1 = r7.kit     // Catch: java.lang.Throwable -> L94 android.content.res.Resources.NotFoundException -> L97
            android.content.Context r1 = r1.getContext()     // Catch: java.lang.Throwable -> L94 android.content.res.Resources.NotFoundException -> L97
            android.content.res.Resources r1 = r1.getResources()     // Catch: java.lang.Throwable -> L94 android.content.res.Resources.NotFoundException -> L97
            io.fabric.sdk.android.services.settings.IconRequest r2 = r9.icon     // Catch: java.lang.Throwable -> L94 android.content.res.Resources.NotFoundException -> L97
            int r2 = r2.iconResourceId     // Catch: java.lang.Throwable -> L94 android.content.res.Resources.NotFoundException -> L97
            java.io.InputStream r1 = r1.openRawResource(r2)     // Catch: java.lang.Throwable -> L94 android.content.res.Resources.NotFoundException -> L97
            java.lang.String r0 = "app[icon][hash]"
            io.fabric.sdk.android.services.settings.IconRequest r2 = r9.icon     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            java.lang.String r2 = r2.hash     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            io.fabric.sdk.android.services.network.HttpRequest r0 = r8.part(r0, r2)     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            java.lang.String r2 = "app[icon][data]"
            java.lang.String r3 = "icon.png"
            java.lang.String r4 = "application/octet-stream"
            io.fabric.sdk.android.services.network.HttpRequest r0 = r0.part(r2, r3, r4, r1)     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            java.lang.String r2 = "app[icon][width]"
            io.fabric.sdk.android.services.settings.IconRequest r3 = r9.icon     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            int r3 = r3.width     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            io.fabric.sdk.android.services.network.HttpRequest r0 = r0.part(r2, r3)     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            java.lang.String r2 = "app[icon][height]"
            io.fabric.sdk.android.services.settings.IconRequest r3 = r9.icon     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            int r3 = r3.height     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            r0.part(r2, r3)     // Catch: android.content.res.Resources.NotFoundException -> L92 java.lang.Throwable -> Lbf
            goto Lb9
        L92:
            r0 = move-exception
            goto L9b
        L94:
            r8 = move-exception
            r1 = r0
            goto Lc0
        L97:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L9b:
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r3 = "Fabric"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbf
            r4.<init>()     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r5 = "Failed to find app icon with resource ID: "
            r4.append(r5)     // Catch: java.lang.Throwable -> Lbf
            io.fabric.sdk.android.services.settings.IconRequest r5 = r9.icon     // Catch: java.lang.Throwable -> Lbf
            int r5 = r5.iconResourceId     // Catch: java.lang.Throwable -> Lbf
            r4.append(r5)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> Lbf
            r2.mo6e(r3, r4, r0)     // Catch: java.lang.Throwable -> Lbf
        Lb9:
            java.lang.String r0 = "Failed to close app icon InputStream."
            io.fabric.sdk.android.services.common.CommonUtils.closeOrLog(r1, r0)
            goto Lc6
        Lbf:
            r8 = move-exception
        Lc0:
            java.lang.String r9 = "Failed to close app icon InputStream."
            io.fabric.sdk.android.services.common.CommonUtils.closeOrLog(r1, r9)
            throw r8
        Lc6:
            java.util.Collection<io.fabric.sdk.android.KitInfo> r0 = r9.sdkKits
            if (r0 == 0) goto Lf3
            java.util.Collection<io.fabric.sdk.android.KitInfo> r9 = r9.sdkKits
            java.util.Iterator r9 = r9.iterator()
        Ld0:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto Lf3
            java.lang.Object r0 = r9.next()
            io.fabric.sdk.android.KitInfo r0 = (io.fabric.sdk.android.KitInfo) r0
            java.lang.String r1 = r7.getKitVersionKey(r0)
            java.lang.String r2 = r0.getVersion()
            r8.part(r1, r2)
            java.lang.String r1 = r7.getKitBuildTypeKey(r0)
            java.lang.String r0 = r0.getBuildType()
            r8.part(r1, r0)
            goto Ld0
        Lf3:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.settings.AbstractAppSpiCall.applyMultipartDataTo(io.fabric.sdk.android.services.network.HttpRequest, io.fabric.sdk.android.services.settings.AppRequestData):io.fabric.sdk.android.services.network.HttpRequest");
    }

    String getKitVersionKey(KitInfo kitInfo) {
        return String.format(Locale.US, APP_SDK_MODULES_PARAM_VERSION, kitInfo.getIdentifier());
    }

    String getKitBuildTypeKey(KitInfo kitInfo) {
        return String.format(Locale.US, APP_SDK_MODULES_PARAM_BUILD_TYPE, kitInfo.getIdentifier());
    }
}
