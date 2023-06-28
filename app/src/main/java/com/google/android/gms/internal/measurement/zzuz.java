package com.google.android.gms.internal.measurement;

import android.support.v4.internal.view.SupportMenu;
import com.google.android.gms.internal.measurement.zzvm;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class zzuz {
    private static volatile boolean zzbvj = false;
    private static volatile zzuz zzbvl;
    private final Map<zza, zzvm.zzd<?, ?>> zzbvn;
    private static final Class<?> zzbvk = zzvn();
    static final zzuz zzbvm = new zzuz(true);

    private static Class<?> zzvn() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * SupportMenu.USER_MASK) + this.number;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof zza) {
                zza zzaVar = (zza) obj;
                return this.object == zzaVar.object && this.number == zzaVar.number;
            }
            return false;
        }
    }

    public static zzuz zzvo() {
        return zzuy.zzvl();
    }

    public static zzuz zzvp() {
        zzuz zzuzVar = zzbvl;
        if (zzuzVar == null) {
            synchronized (zzuz.class) {
                zzuzVar = zzbvl;
                if (zzuzVar == null) {
                    zzuzVar = zzuy.zzvm();
                    zzbvl = zzuzVar;
                }
            }
        }
        return zzuzVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzuz zzvm() {
        return zzvk.zzd(zzuz.class);
    }

    public final <ContainingType extends zzwt> zzvm.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzvm.zzd<ContainingType, ?>) this.zzbvn.get(new zza(containingtype, i));
    }

    zzuz() {
        this.zzbvn = new HashMap();
    }

    private zzuz(boolean z) {
        this.zzbvn = Collections.emptyMap();
    }
}
