package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public abstract class zzah extends com.google.android.gms.internal.measurement.zzr implements zzag {
    public zzah() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.internal.measurement.zzr
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzad) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzad.CREATOR), (zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                return true;
            case 2:
                zza((zzfh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzfh.CREATOR), (zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                return true;
            case 3:
            case 8:
            default:
                return false;
            case 4:
                zza((zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                return true;
            case 5:
                zza((zzad) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzad.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zzb((zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                return true;
            case 7:
                List<zzfh> zza = zza((zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR), com.google.android.gms.internal.measurement.zzs.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                return true;
            case 9:
                byte[] zza2 = zza((zzad) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzad.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                return true;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                String zzc = zzc((zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                return true;
            case 12:
                zza((zzl) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzl.CREATOR), (zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                return true;
            case 13:
                zzb((zzl) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzl.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                List<zzfh> zza3 = zza(parcel.readString(), parcel.readString(), com.google.android.gms.internal.measurement.zzs.zza(parcel), (zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza3);
                return true;
            case 15:
                List<zzfh> zza4 = zza(parcel.readString(), parcel.readString(), parcel.readString(), com.google.android.gms.internal.measurement.zzs.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza4);
                return true;
            case 16:
                List<zzl> zza5 = zza(parcel.readString(), parcel.readString(), (zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza5);
                return true;
            case 17:
                List<zzl> zze = zze(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zze);
                return true;
            case 18:
                zzd((zzh) com.google.android.gms.internal.measurement.zzs.zza(parcel, zzh.CREATOR));
                parcel2.writeNoException();
                return true;
        }
    }
}
