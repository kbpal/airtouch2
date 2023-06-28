package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* loaded from: classes.dex */
public final class zzxn<FieldDescriptorType> extends zzxm<FieldDescriptorType, Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzxn(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzxm
    public final void zzsm() {
        if (!isImmutable()) {
            for (int i = 0; i < zzxw(); i++) {
                Map.Entry<FieldDescriptorType, Object> zzbu = zzbu(i);
                if (((zzvf) zzbu.getKey()).zzvy()) {
                    zzbu.setValue(Collections.unmodifiableList((List) zzbu.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzxx()) {
                if (((zzvf) entry.getKey()).zzvy()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzsm();
    }
}
