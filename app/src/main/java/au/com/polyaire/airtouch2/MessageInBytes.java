package au.com.polyaire.airtouch2;

import android.util.Log;
import au.com.polyaire.airtouch2.data.ACInfo;
import au.com.polyaire.airtouch2.data.ExchData;
import java.io.PrintStream;
import java.util.Arrays;

/* loaded from: classes.dex */
public class MessageInBytes {
    private byte[] message = new byte[13];
    private byte[] sumByte = new byte[this.message.length];

    public void ResetContents() {
        Arrays.fill(this.message, (byte) 0);
        this.message[0] = 85;
        this.message[2] = 12;
    }

    public void SetHomeMessage() {
        ResetContents();
    }

    public void SetModeMessage(int i) {
        ResetContents();
        if ((ExchData.getSelectedAC() == 0 && ExchData.getAC1().getBrand() == 11) || (ExchData.getSelectedAC() == 1 && ExchData.getAC2().getBrand() == 11)) {
            switch (i) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 3;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 1;
                    break;
            }
        }
        this.message[1] = -122;
        this.message[3] = (byte) ExchData.getSelectedAC();
        this.message[4] = -127;
        this.message[5] = (byte) i;
        this.message[12] = checkSum();
    }

    public void SetFanSpeedMessage(ACInfo.E_ACFanMode e_ACFanMode) {
        ACInfo ac2;
        ResetContents();
        if (ExchData.getSelectedAC() == 0) {
            ac2 = ExchData.getAC1();
        } else {
            ac2 = ExchData.getAC2();
        }
        int formatFanSpeed = ACInfo.formatFanSpeed(e_ACFanMode, ac2.getBrand(), ac2.getSupportedFanSpeed());
        this.message[1] = -122;
        this.message[3] = (byte) ExchData.getSelectedAC();
        this.message[4] = -126;
        this.message[5] = (byte) formatFanSpeed;
        this.message[12] = checkSum();
    }

    public void SetNewTempMessage(String str) {
        ResetContents();
        this.message[1] = -122;
        this.message[3] = (byte) ExchData.getSelectedAC();
        if (str.equals("plus1")) {
            this.message[4] = -93;
        } else if (str.equals("minus1")) {
            this.message[4] = -109;
        }
        this.message[12] = checkSum();
    }

    public void SetACOnOff() {
        ResetContents();
        this.message[1] = -122;
        this.message[3] = (byte) ExchData.getSelectedAC();
        this.message[4] = Byte.MIN_VALUE;
        this.message[12] = checkSum();
    }

    public void SetZoneMessage(int i) {
        ResetContents();
        this.message[1] = -127;
        this.message[3] = (byte) i;
        this.message[4] = Byte.MIN_VALUE;
        this.message[12] = checkSum();
    }

    public void SetFanMessage(int i, String str) {
        ResetContents();
        this.message[1] = 1;
        this.message[1] = -127;
        this.message[3] = (byte) i;
        if (str.equals("UP")) {
            this.message[4] = 2;
        } else {
            this.message[4] = 1;
        }
        this.message[5] = 1;
        this.message[12] = checkSum();
    }

    public void SetNameMessage(int i, String str) {
        ResetContents();
        this.message[1] = -123;
        this.message[3] = (byte) ((i - 1) + 128);
        byte[] StringtoASCMax8 = StringtoASCMax8(str);
        for (int i2 = 0; i2 <= 7; i2++) {
            this.message[i2 + 4] = StringtoASCMax8[i2];
        }
        this.message[12] = checkSum();
    }

    public void SetACTimeMessage(int i, boolean z, int i2, int i3) {
        ResetContents();
        this.message[1] = -124;
        this.message[3] = (byte) (i - 1);
        PrintStream printStream = System.out;
        printStream.println("listener??" + ((int) this.message[3]));
        if (z) {
            this.message[4] = 0;
            PrintStream printStream2 = System.out;
            printStream2.println("hour: " + i2);
            PrintStream printStream3 = System.out;
            printStream3.println("minute: " + i3);
            this.message[5] = (byte) i2;
            this.message[6] = (byte) i3;
        } else {
            this.message[4] = -127;
        }
        this.message[12] = checkSum();
    }

    public void setProgramMessage(String str, int i, String str2, int i2, int i3) {
        ResetContents();
        this.message[1] = -126;
        if (str.equals("SAT")) {
            i += 4;
        } else if (str.equals("SUN")) {
            i += 8;
        }
        this.message[3] = (byte) (i - 1);
        if (str2.equals("Program2")) {
            byte[] bArr = this.message;
            bArr[3] = (byte) (bArr[3] + 12);
        } else if (str2.equals("Program3")) {
            byte[] bArr2 = this.message;
            bArr2[3] = (byte) (bArr2[3] + 24);
        } else if (str2.equals("Program4")) {
            byte[] bArr3 = this.message;
            bArr3[3] = (byte) (bArr3[3] + 36);
        }
        if (i2 == -1) {
            this.message[4] = -127;
        } else {
            this.message[4] = 0;
            this.message[5] = (byte) i2;
            this.message[6] = (byte) i3;
        }
        this.message[12] = checkSum();
    }

    public void printOutMsg(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            String format = String.format("%02X ", Byte.valueOf(bArr[i]));
            PrintStream printStream = System.out;
            printStream.print(format + " ");
        }
        System.out.println();
    }

    public byte[] GetInBytes() {
        return this.message;
    }

    public byte[] GetNewOwnerMessage(String str) {
        byte[] bArr = new byte[21];
        byte[] bArr2 = new byte[bArr.length];
        Arrays.fill(bArr, (byte) 0);
        bArr[0] = 85;
        bArr[1] = -121;
        bArr[2] = 20;
        bArr[3] = Byte.MIN_VALUE;
        byte[] StringtoASC = StringtoASC(str, 16);
        for (int i = 0; i <= 15; i++) {
            bArr[i + 4] = StringtoASC[i];
        }
        bArr[20] = checkSumGivenParameters(bArr, bArr2);
        return bArr;
    }

    public byte[] SetLocalPasswordMessage(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println("id: " + str + " " + str2);
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[bArr.length];
        bArr[0] = -32;
        bArr[1] = 2;
        bArr[2] = 19;
        byte[] StringtoASCMax8Spaces = StringtoASCMax8Spaces(str);
        for (int i = 0; i <= 7; i++) {
            bArr[i + 3] = StringtoASCMax8Spaces[i];
        }
        byte[] StringtoASCMax8Spaces2 = StringtoASCMax8Spaces(str2);
        for (int i2 = 0; i2 <= 7; i2++) {
            bArr[i2 + 11] = StringtoASCMax8Spaces2[i2];
        }
        bArr[19] = checkSumGivenParameters(bArr, bArr2);
        return bArr;
    }

    public byte[] SetInternetPasswordMessage(String str, String str2) {
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[bArr.length];
        bArr[0] = -32;
        bArr[1] = 1;
        bArr[2] = 19;
        byte[] StringtoASCMax8Spaces = StringtoASCMax8Spaces(str);
        for (int i = 0; i <= 7; i++) {
            bArr[i + 3] = StringtoASCMax8Spaces[i];
        }
        byte[] StringtoASCMax8Spaces2 = StringtoASCMax8Spaces(str2);
        for (int i2 = 0; i2 <= 7; i2++) {
            bArr[i2 + 11] = StringtoASCMax8Spaces2[i2];
        }
        bArr[19] = checkSumGivenParameters(bArr, bArr2);
        return bArr;
    }

    public byte[] SetAuthenticationMessage(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println("authenticate: " + str + " " + str2);
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[bArr.length];
        bArr[0] = -32;
        bArr[1] = 0;
        bArr[2] = 19;
        byte[] StringtoASCMax8Spaces = StringtoASCMax8Spaces(str);
        for (int i = 0; i <= 7; i++) {
            bArr[i + 3] = StringtoASCMax8Spaces[i];
        }
        byte[] StringtoASCMax8Spaces2 = StringtoASCMax8Spaces(str2);
        for (int i2 = 0; i2 <= 7; i2++) {
            bArr[i2 + 11] = StringtoASCMax8Spaces2[i2];
        }
        bArr[19] = checkSumGivenParameters(bArr, bArr2);
        return bArr;
    }

    public byte[] GetInitMsg() {
        ResetContents();
        this.message[1] = 1;
        this.message[12] = checkSum();
        return this.message;
    }

    private byte checkSum() {
        int i = 0;
        for (int i2 = 0; i2 <= this.sumByte.length - 1; i2++) {
            this.sumByte[i2] = this.message[i2];
        }
        int i3 = 0;
        while (i <= this.sumByte.length - 2) {
            int i4 = this.sumByte[i];
            i++;
            i3 = i4 < 0 ? i4 == -128 ? i3 + 128 : i3 + ((byte) (i4 + 256)) : i3 + i4;
        }
        Log.d("SUM", String.valueOf(i3));
        return (byte) i3;
    }

    private byte checkSumGivenParameters(byte[] bArr, byte[] bArr2) {
        int i = 0;
        for (int i2 = 0; i2 <= bArr2.length - 1; i2++) {
            bArr2[i2] = bArr[i2];
        }
        int i3 = 0;
        while (i <= bArr2.length - 2) {
            int i4 = bArr2[i];
            i++;
            i3 = i4 < 0 ? i4 == -128 ? i3 + 128 : i3 + ((byte) (i4 + 256)) : i3 + i4;
        }
        Log.d("SUM", String.valueOf(i3));
        return (byte) i3;
    }

    public byte[] StringtoASCMax8(String str) {
        byte[] bArr = new byte[8];
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2] = 32;
        }
        char[] charArray = str.toCharArray();
        if (str.length() < 9) {
            while (i < str.length()) {
                bArr[i] = (byte) charArray[i];
                i++;
            }
        } else {
            while (i < 8) {
                bArr[i] = (byte) charArray[i];
                i++;
            }
        }
        return bArr;
    }

    public byte[] StringtoASCMax8Spaces(String str) {
        byte[] bArr = new byte[8];
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2] = 0;
        }
        char[] charArray = str.toCharArray();
        if (str.length() < 9) {
            while (i < str.length()) {
                bArr[i] = (byte) charArray[i];
                i++;
            }
        } else {
            while (i < 8) {
                bArr[i] = (byte) charArray[i];
                i++;
            }
        }
        return bArr;
    }

    public byte[] StringtoASC(String str, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = 32;
        }
        char[] charArray = str.toCharArray();
        if (str.length() < i + 1) {
            while (i2 < str.length()) {
                bArr[i2] = (byte) charArray[i2];
                i2++;
            }
        } else {
            while (i2 < i) {
                bArr[i2] = (byte) charArray[i2];
                i2++;
            }
        }
        return bArr;
    }
}
