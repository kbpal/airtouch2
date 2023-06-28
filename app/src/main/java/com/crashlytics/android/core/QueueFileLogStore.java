package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.QueueFile;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/* loaded from: classes.dex */
class QueueFileLogStore implements FileLogStore {
    private QueueFile logFile;
    private final int maxLogSize;
    private final File workingFile;

    /* loaded from: classes.dex */
    public class LogBytes {
        public final byte[] bytes;
        public final int offset;

        public LogBytes(byte[] bArr, int i) {
            this.bytes = bArr;
            this.offset = i;
        }
    }

    public QueueFileLogStore(File file, int i) {
        this.workingFile = file;
        this.maxLogSize = i;
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public void writeToLog(long j, String str) {
        openLogFile();
        doWriteToLog(j, str);
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public ByteString getLogAsByteString() {
        LogBytes logBytes = getLogBytes();
        if (logBytes == null) {
            return null;
        }
        return ByteString.copyFrom(logBytes.bytes, 0, logBytes.offset);
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public byte[] getLogAsBytes() {
        LogBytes logBytes = getLogBytes();
        if (logBytes == null) {
            return null;
        }
        return logBytes.bytes;
    }

    private LogBytes getLogBytes() {
        if (this.workingFile.exists()) {
            openLogFile();
            if (this.logFile == null) {
                return null;
            }
            final int[] iArr = {0};
            final byte[] bArr = new byte[this.logFile.usedBytes()];
            try {
                this.logFile.forEach(new QueueFile.ElementReader() { // from class: com.crashlytics.android.core.QueueFileLogStore.1
                    @Override // io.fabric.sdk.android.services.common.QueueFile.ElementReader
                    public void read(InputStream inputStream, int i) throws IOException {
                        try {
                            inputStream.read(bArr, iArr[0], i);
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] + i;
                        } finally {
                            inputStream.close();
                        }
                    }
                });
            } catch (IOException e) {
                Fabric.getLogger().mo6e(CrashlyticsCore.TAG, "A problem occurred while reading the Crashlytics log file.", e);
            }
            return new LogBytes(bArr, iArr[0]);
        }
        return null;
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public void closeLogFile() {
        CommonUtils.closeOrLog(this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    @Override // com.crashlytics.android.core.FileLogStore
    public void deleteLogFile() {
        closeLogFile();
        this.workingFile.delete();
    }

    private void openLogFile() {
        if (this.logFile == null) {
            try {
                this.logFile = new QueueFile(this.workingFile);
            } catch (IOException e) {
                Logger logger = Fabric.getLogger();
                logger.mo6e(CrashlyticsCore.TAG, "Could not open log file: " + this.workingFile, e);
            }
        }
    }

    private void doWriteToLog(long j, String str) {
        int i;
        if (this.logFile == null) {
            return;
        }
        if (str == null) {
            str = "null";
        }
        try {
            if (str.length() > this.maxLogSize / 4) {
                str = "..." + str.substring(str.length() - i);
            }
            this.logFile.add(String.format(Locale.US, "%d %s%n", Long.valueOf(j), str.replaceAll("\r", " ").replaceAll("\n", " ")).getBytes(HttpRequest.CHARSET_UTF8));
            while (!this.logFile.isEmpty() && this.logFile.usedBytes() > this.maxLogSize) {
                this.logFile.remove();
            }
        } catch (IOException e) {
            Fabric.getLogger().mo6e(CrashlyticsCore.TAG, "There was a problem writing to the Crashlytics log.", e);
        }
    }
}
