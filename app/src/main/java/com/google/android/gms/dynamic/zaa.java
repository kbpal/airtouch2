package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
final class zaa<T> implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zari;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zari = deferredLifecycleHelper;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        LinkedList linkedList;
        LinkedList linkedList2;
        LifecycleDelegate lifecycleDelegate2;
        this.zari.zare = lifecycleDelegate;
        linkedList = this.zari.zarg;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            lifecycleDelegate2 = this.zari.zare;
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(lifecycleDelegate2);
        }
        linkedList2 = this.zari.zarg;
        linkedList2.clear();
        DeferredLifecycleHelper.zaa(this.zari, (Bundle) null);
    }
}
