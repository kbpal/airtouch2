package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "ScopeCreator")
/* loaded from: classes.dex */
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new zza();
    @SafeParcelable.Field(getter = "getScopeUri", id = 2)
    private final String zzap;
    @SafeParcelable.VersionField(id = 1)
    private final int zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public Scope(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str) {
        Preconditions.checkNotEmpty(str, "scopeUri must not be null or empty");
        this.zzg = i;
        this.zzap = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    @KeepForSdk
    public final String getScopeUri() {
        return this.zzap;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Scope) {
            return this.zzap.equals(((Scope) obj).zzap);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzap.hashCode();
    }

    public final String toString() {
        return this.zzap;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzg);
        SafeParcelWriter.writeString(parcel, 2, getScopeUri(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
