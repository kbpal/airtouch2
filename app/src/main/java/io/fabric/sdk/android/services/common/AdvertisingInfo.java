package io.fabric.sdk.android.services.common;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AdvertisingInfo {
    public final String advertisingId;
    public final boolean limitAdTrackingEnabled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdvertisingInfo(String str, boolean z) {
        this.advertisingId = str;
        this.limitAdTrackingEnabled = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvertisingInfo advertisingInfo = (AdvertisingInfo) obj;
        if (this.limitAdTrackingEnabled != advertisingInfo.limitAdTrackingEnabled) {
            return false;
        }
        return this.advertisingId == null ? advertisingInfo.advertisingId == null : this.advertisingId.equals(advertisingInfo.advertisingId);
    }

    public int hashCode() {
        return ((this.advertisingId != null ? this.advertisingId.hashCode() : 0) * 31) + (this.limitAdTrackingEnabled ? 1 : 0);
    }
}
