package com.crashlytics.android.core;

import java.io.File;
import java.util.Map;

/* loaded from: classes.dex */
interface Report {

    /* loaded from: classes.dex */
    public enum Type {
        JAVA,
        NATIVE
    }

    Map<String, String> getCustomHeaders();

    File getFile();

    String getFileName();

    File[] getFiles();

    String getIdentifier();

    Type getType();

    void remove();
}
