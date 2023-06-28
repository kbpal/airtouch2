package au.com.polyaire.airtouch2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public class FileHelper {
    private static File mAppPath;

    public static void init(File file) {
        mAppPath = file;
    }

    public static boolean needInit() {
        return mAppPath == null;
    }

    public File createSDFile(String str) throws IOException {
        File file = new File(mAppPath, str);
        file.createNewFile();
        return file;
    }

    public File createSDDir(String str) {
        File file = new File(mAppPath, str);
        file.mkdir();
        return file;
    }

    public boolean isFileExist(String str) {
        return new File(mAppPath, str).exists();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v7 */
    public File writeToSDCard(String str, String str2, InputStream inputStream) {
        File file;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    createSDDir(str);
                    file = createSDFile(str + str2);
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                        try {
                            byte[] bArr = new byte[4096];
                            while (inputStream.read(bArr) != -1) {
                                fileOutputStream2.write(bArr);
                            }
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                            str = file;
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            fileOutputStream.close();
                            str = file;
                            return str;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            try {
                                fileOutputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
                file = null;
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return str;
    }

    public void writeToSDCard(String str, String str2) {
        try {
            FileWriter fileWriter = new FileWriter(mAppPath.getPath() + str);
            if (str2 == null) {
                str2 = "";
            }
            fileWriter.write(str2);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIp(String str) {
        try {
            String readLine = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mAppPath, str)))).readLine();
            return readLine != null ? readLine : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
