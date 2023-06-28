package au.com.polyaire.airtouch2.service;

import android.content.SharedPreferences;
import au.com.polyaire.airtouch2.data.ACInfo;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.data.GroupDataModel;
import java.io.PrintStream;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class WifiCommService {
    private boolean finishedAddingToDataList = false;
    SharedPreferences settings;

    public void Updata(byte[] bArr, int i) {
        boolean z;
        int i2 = 0;
        this.settings = ExchData.mConnection.getContext().getSharedPreferences("Settings", 0);
        System.out.println("tmp length ------>" + i);
        String[] strArr = new String[i];
        for (int i3 = 0; i3 < i; i3++) {
            strArr[i3] = toFullBinaryString(255 & bArr[i3]).substring(24);
            System.out.println("i:" + i3 + ", TEMP[i]:" + ((int) bArr[i3]) + ", s:" + strArr[i3]);
        }
        String[] strArr2 = new String[353];
        for (int i4 = 0; i4 < 353; i4++) {
            strArr2[i4] = strArr[i4];
        }
        ExchData.setAllData(strArr2);
        String[] strArr3 = new String[8];
        for (int i5 = 0; i5 < 8; i5++) {
            strArr3[i5] = strArr[i5 + 292];
        }
        ExchData.setSysPara(strArr3);
        ACInfo aCInfo = new ACInfo();
        ACInfo aCInfo2 = new ACInfo();
        int i6 = 2;
        int parseInt = Integer.parseInt(strArr[356], 2);
        aCInfo.setBrand(parseInt);
        int parseInt2 = Integer.parseInt(strArr[357], 2);
        aCInfo2.setBrand(parseInt2);
        int parseInt3 = Integer.parseInt(strArr[368], 2);
        aCInfo.setAcID(parseInt3);
        int parseInt4 = Integer.parseInt(strArr[369], 2);
        aCInfo2.setAcID(parseInt4);
        replaceBrandWithGatewayID(aCInfo, parseInt3);
        replaceBrandWithGatewayID(aCInfo2, parseInt4);
        if (aCInfo.getBrand() == 0 || aCInfo2.getBrand() == 0) {
            ExchData.setDualACMode(false);
        } else {
            ExchData.setDualACMode(true);
        }
        int parseInt5 = Integer.parseInt(strArr[368], 2);
        int parseInt6 = Integer.parseInt(strArr[369], 2);
        if (parseInt5 != 0) {
            aCInfo.setControlMode(ACInfo.E_ACControlMode.Full);
        } else if (parseInt == 0) {
            aCInfo.setControlMode(ACInfo.E_ACControlMode.NotAvailable);
        } else {
            aCInfo.setControlMode(ACInfo.E_ACControlMode.Basic);
        }
        if (parseInt6 != 0) {
            aCInfo2.setControlMode(ACInfo.E_ACControlMode.Full);
        } else if (parseInt2 == 0) {
            aCInfo2.setControlMode(ACInfo.E_ACControlMode.NotAvailable);
        } else {
            aCInfo2.setControlMode(ACInfo.E_ACControlMode.Basic);
        }
        int parseInt7 = Integer.parseInt(strArr[346].substring(0, 1), 2);
        int parseInt8 = Integer.parseInt(strArr[348].substring(0, 1), 2);
        if (parseInt7 == 0 || parseInt8 == 0) {
            aCInfo.setHasTimer(true);
        } else {
            aCInfo.setHasTimer(false);
        }
        int parseInt9 = Integer.parseInt(strArr[350].substring(0, 1), 2);
        int parseInt10 = Integer.parseInt(strArr[352].substring(0, 1), 2);
        if (parseInt9 == 0 || parseInt10 == 0) {
            aCInfo2.setHasTimer(true);
        } else {
            aCInfo2.setHasTimer(false);
        }
        String str = strArr[354];
        String str2 = strArr[355];
        if (str.substring(1, 2).equals("1")) {
            aCInfo.setIsError(true);
        } else {
            aCInfo.setIsError(false);
        }
        if (str2.substring(1, 2).equals("1")) {
            aCInfo2.setIsError(true);
        } else {
            aCInfo2.setIsError(false);
        }
        if (str.substring(4, 5).equals("0")) {
            aCInfo.setDisplayGatewayTemp(true);
        } else {
            aCInfo.setDisplayGatewayTemp(false);
        }
        if (str2.substring(4, 5).equals("0")) {
            aCInfo2.setDisplayGatewayTemp(true);
        } else {
            aCInfo2.setDisplayGatewayTemp(false);
        }
        aCInfo.setErrorCode(Integer.parseInt(strArr[366], 2));
        aCInfo2.setErrorCode(Integer.parseInt(strArr[367], 2));
        if (str.substring(0, 1).equals("1")) {
            aCInfo.setAcStatus(ACInfo.E_ACStatus.ACOn);
        } else {
            aCInfo.setAcStatus(ACInfo.E_ACStatus.ACOff);
        }
        if (str2.substring(0, 1).equals("1")) {
            aCInfo2.setAcStatus(ACInfo.E_ACStatus.ACOn);
        } else {
            aCInfo2.setAcStatus(ACInfo.E_ACStatus.ACOff);
        }
        int parseInt11 = Integer.parseInt(str.substring(5, 8), 2);
        if (parseInt11 == 0) {
            aCInfo.setHasProgram(false);
            aCInfo.setProgramNumber(0);
        } else if (parseInt11 > 0 && parseInt11 <= 4) {
            aCInfo.setHasProgram(true);
            aCInfo.setProgramNumber(parseInt11);
        }
        int parseInt12 = Integer.parseInt(str2.substring(5, 8), 2);
        if (parseInt12 == 0) {
            aCInfo2.setHasProgram(false);
            aCInfo2.setProgramNumber(0);
        } else if (parseInt12 > 0 && parseInt12 <= 4) {
            aCInfo2.setHasProgram(true);
            aCInfo2.setProgramNumber(parseInt12);
        }
        int parseInt13 = Integer.parseInt(strArr[358].substring(1, 8), 2);
        switch (parseInt13) {
            case 0:
                aCInfo.setAcMode(ACInfo.E_ACMode.Auto);
                break;
            case 1:
                aCInfo.setAcMode(ACInfo.E_ACMode.Heat);
                break;
            case 2:
                aCInfo.setAcMode(ACInfo.E_ACMode.Dry);
                break;
            case 3:
                aCInfo.setAcMode(ACInfo.E_ACMode.Fan);
                break;
            case 4:
                aCInfo.setAcMode(ACInfo.E_ACMode.Cool);
                break;
            default:
                aCInfo.setAcMode(ACInfo.E_ACMode.Auto);
                break;
        }
        int parseInt14 = Integer.parseInt(strArr[359].substring(1, 8), 2);
        switch (parseInt14) {
            case 0:
                aCInfo2.setAcMode(ACInfo.E_ACMode.Auto);
                break;
            case 1:
                aCInfo2.setAcMode(ACInfo.E_ACMode.Heat);
                break;
            case 2:
                aCInfo2.setAcMode(ACInfo.E_ACMode.Dry);
                break;
            case 3:
                aCInfo2.setAcMode(ACInfo.E_ACMode.Fan);
                break;
            case 4:
                aCInfo2.setAcMode(ACInfo.E_ACMode.Cool);
                break;
            default:
                aCInfo2.setAcMode(ACInfo.E_ACMode.Auto);
                break;
        }
        if (aCInfo.getBrand() == 11) {
            switch (parseInt13) {
                case 0:
                    aCInfo.setAcMode(ACInfo.E_ACMode.Auto);
                    break;
                case 1:
                    aCInfo.setAcMode(ACInfo.E_ACMode.Cool);
                    break;
                case 2:
                    aCInfo.setAcMode(ACInfo.E_ACMode.Heat);
                    break;
                case 3:
                    aCInfo.setAcMode(ACInfo.E_ACMode.Dry);
                    break;
                case 4:
                    aCInfo.setAcMode(ACInfo.E_ACMode.Fan);
                    break;
                default:
                    aCInfo.setAcMode(ACInfo.E_ACMode.Auto);
                    break;
            }
        }
        if (aCInfo2.getBrand() == 11) {
            switch (parseInt14) {
                case 0:
                    aCInfo2.setAcMode(ACInfo.E_ACMode.Auto);
                    break;
                case 1:
                    aCInfo2.setAcMode(ACInfo.E_ACMode.Cool);
                    break;
                case 2:
                    aCInfo2.setAcMode(ACInfo.E_ACMode.Heat);
                    break;
                case 3:
                    aCInfo2.setAcMode(ACInfo.E_ACMode.Dry);
                    break;
                case 4:
                    aCInfo2.setAcMode(ACInfo.E_ACMode.Fan);
                    break;
                default:
                    aCInfo2.setAcMode(ACInfo.E_ACMode.Auto);
                    break;
            }
        }
        int parseInt15 = Integer.parseInt(strArr[360].substring(1, 4), 2);
        aCInfo.setSupportedFanSpeed(parseInt15);
        int parseInt16 = Integer.parseInt(strArr[361].substring(1, 4), 2);
        aCInfo2.setSupportedFanSpeed(parseInt16);
        aCInfo.setFanMode(ACInfo.formatFanSpeed(Integer.parseInt(strArr[360].substring(4, 8), 2), aCInfo.getBrand(), parseInt15));
        aCInfo2.setFanMode(ACInfo.formatFanSpeed(Integer.parseInt(strArr[361].substring(4, 8), 2), aCInfo2.getBrand(), parseInt16));
        aCInfo.setSetPoint(Integer.parseInt(strArr[362].substring(1, 8), 2));
        aCInfo2.setSetPoint(Integer.parseInt(strArr[363].substring(1, 8), 2));
        aCInfo.setRoomTemp(Integer.parseInt(strArr[364], 2));
        aCInfo2.setRoomTemp(Integer.parseInt(strArr[365], 2));
        int parseInt17 = Integer.parseInt(strArr[292], 2);
        int parseInt18 = Integer.parseInt(strArr[293].substring(1, 8), 2);
        aCInfo.setGroupNumber(parseInt18);
        aCInfo2.setGroupNumber(parseInt17 - parseInt18);
        if (strArr[293].substring(0, 1).equals("1") && aCInfo.getGroupNumber() > 0 && aCInfo2.getGroupNumber() > 0) {
            ExchData.setIsDualDucted(true);
        } else {
            ExchData.setIsDualDucted(false);
        }
        String str3 = strArr[299];
        if (str3.substring(2, 3).equals("1")) {
            aCInfo.setIsTurbo(true);
        } else {
            aCInfo.setIsTurbo(false);
        }
        if (str3.substring(3, 4).equals("1")) {
            aCInfo2.setIsTurbo(true);
        } else {
            aCInfo2.setIsTurbo(false);
        }
        if (str3.substring(4, 5).equals("1")) {
            aCInfo.setIsSafety(true);
        } else {
            aCInfo.setIsSafety(false);
        }
        if (str3.substring(5, 6).equals("1")) {
            aCInfo2.setIsSafety(true);
        } else {
            aCInfo2.setIsSafety(false);
        }
        if (str3.substring(6, 7).equals("1")) {
            aCInfo.setIsSpill(true);
        } else {
            aCInfo.setIsSpill(false);
        }
        if (str3.substring(7, 8).equals("1")) {
            aCInfo2.setIsSpill(true);
        } else {
            aCInfo2.setIsSpill(false);
        }
        char[] cArr = new char[8];
        char[] cArr2 = new char[8];
        int i7 = 0;
        for (int i8 = 8; i7 < i8; i8 = 8) {
            cArr[i7] = (char) Integer.parseInt(strArr[i7 + 370], 2);
            cArr2[i7] = (char) Integer.parseInt(strArr[i7 + 378], 2);
            i7++;
        }
        aCInfo.setAcName(String.valueOf(cArr));
        aCInfo2.setAcName(String.valueOf(cArr2));
        ExchData.setAC1(aCInfo);
        ExchData.setAC2(aCInfo2);
        ExchData.setACbrand(Integer.parseInt(strArr[3], 2));
        ExchData.setSetPoint(strArr[4]);
        String[] strArr4 = new String[2];
        for (int i9 = 0; i9 < 2; i9++) {
            strArr4[i9] = strArr[i9 + 5];
        }
        ExchData.setMaintain(strArr4);
        String[] strArr5 = new String[96];
        for (int i10 = 0; i10 < 96; i10++) {
            strArr5[i10] = strArr[i10 + 4];
        }
        ExchData.setProgramTime(strArr5);
        boolean[] zArr = {false, false, false, false};
        for (int i11 = 0; i11 < 4; i11++) {
            int i12 = i11 * 24;
            int i13 = 0;
            while (true) {
                if (i13 >= 24) {
                    break;
                } else if (strArr5[i12 + i13].substring(0, 1).equals("0")) {
                    zArr[i11] = true;
                } else {
                    i13 += 2;
                }
            }
        }
        ExchData.setProgramHasTimes(zArr);
        String[] strArr6 = new String[128];
        for (int i14 = 0; i14 < 128; i14++) {
            strArr6[i14] = strArr[i14 + 100];
        }
        ExchData.setGroupName(strArr6);
        String[] strArr7 = new String[16];
        for (int i15 = 0; i15 < 16; i15++) {
            strArr7[i15] = strArr[i15 + 228];
        }
        ExchData.setZoneData(strArr7);
        String[] strArr8 = new String[16];
        for (int i16 = 0; i16 < 16; i16++) {
            strArr8[i16] = strArr[i16 + 263];
        }
        ExchData.setZoneBalance(strArr8);
        String[] strArr9 = new String[10];
        for (int i17 = 0; i17 < 10; i17++) {
            strArr9[i17] = strArr[i17 + 286];
        }
        ExchData.setContactName(strArr9);
        String[] strArr10 = new String[12];
        for (int i18 = 0; i18 < 12; i18++) {
            strArr10[i18] = strArr[i18 + 296];
        }
        ExchData.setContactNO(strArr10);
        ExchData.setRemindDay(strArr[308]);
        String[] strArr11 = new String[16];
        for (int i19 = 0; i19 < 16; i19++) {
            strArr11[i19] = strArr[i19 + 244];
        }
        ExchData.setGroupData(strArr11);
        String[] strArr12 = new String[16];
        for (int i20 = 0; i20 < 16; i20++) {
            strArr12[i20] = strArr[i20 + 309];
        }
        ExchData.setGroupOpen(strArr12);
        int parseInt19 = Integer.parseInt(strArr[323], 2);
        if (parseInt19 == 255) {
            ExchData.setTemperature("");
        } else {
            ExchData.setTemperature(String.valueOf(parseInt19));
        }
        char[] cArr3 = new char[16];
        for (int i21 = 0; i21 < 16; i21++) {
            cArr3[i21] = (char) Integer.parseInt(strArr[i21 + 324], 2);
        }
        int i22 = 16;
        for (int i23 = 15; i23 >= 0 && cArr3[i23] == ' '; i23--) {
            i22--;
        }
        ExchData.setSystemName(String.valueOf(cArr3, 0, i22));
        String[] strArr13 = new String[6];
        for (int i24 = 0; i24 < 6; i24++) {
            strArr13[i24] = strArr[i24 + 342];
        }
        ExchData.setSystemTime(strArr13);
        String[] strArr14 = new String[8];
        int i25 = 0;
        for (int i26 = 8; i25 < i26; i26 = 8) {
            strArr14[i25] = strArr[i25 + 346];
            i25++;
        }
        ExchData.setACtimer(strArr14);
        String[] strArr15 = new String[23];
        for (int i27 = 0; i27 < 23; i27++) {
            strArr15[i27] = strArr[i27 + 300];
        }
        ExchData.setServiceInfo(strArr15);
        ExchData.setGroupNumber(Integer.parseInt(strArr[292], 2));
        StringBuilder sb = new StringBuilder("");
        String[] strArr16 = new String[16];
        for (int i28 = 0; i28 <= 15; i28++) {
            strArr16[i28] = strArr[i28 + 276];
        }
        ExchData.setSystemStatus("normal");
        ExchData.setACstatus(strArr[2]);
        if (strArr[2].substring(1, 2).equals("1")) {
            ExchData.setSystemStatus("AC ERROR. Check AC Unit.");
        }
        int i29 = 0;
        while (i29 < ExchData.getGroupNumber()) {
            GroupDataModel groupDataModel = new GroupDataModel();
            sb.setLength(i2);
            int i30 = i29 * 8;
            while (true) {
                int i31 = i29 + 1;
                if (i30 < i31 * 8) {
                    sb.append(String.valueOf((char) Integer.parseInt(strArr6[i30], i6)));
                    i30++;
                } else {
                    groupDataModel.setGroupName(sb.toString());
                    int parseInt20 = Integer.parseInt(strArr11[i29].substring(i2, 4), i6);
                    if (strArr7[parseInt20].substring(i2, 1).equals("1")) {
                        groupDataModel.setIsOn(true);
                        groupDataModel.setFanValue(Integer.parseInt(strArr16[parseInt20], i6) * 10);
                        z = false;
                    } else {
                        z = false;
                        groupDataModel.setIsOn(false);
                        groupDataModel.setFanValue(0);
                    }
                    if (strArr7[parseInt20].substring(1, i6).equals("1")) {
                        groupDataModel.setIsSpill(true);
                    } else {
                        groupDataModel.setIsSpill(z);
                    }
                    int parseInt21 = Integer.parseInt(strArr7[parseInt20].substring(2, 5), 2);
                    if (parseInt21 > 4) {
                        parseInt21 -= 4;
                    }
                    if (parseInt21 != 0 && !ExchData.getProgramHasTimes()[parseInt21 - 1]) {
                        parseInt21 = 0;
                    }
                    groupDataModel.setProgramNum(parseInt21);
                    int parseInt22 = Integer.parseInt(strArr[297].substring(1, 8), 2);
                    int parseInt23 = Integer.parseInt(strArr[298].substring(1, 8), 2);
                    if (parseInt22 == i31) {
                        groupDataModel.setIsTurboGroup(true);
                        if (ExchData.getAC1().isTurbo()) {
                            groupDataModel.setTurboIsOn(true);
                            groupDataModel.setFanValue(100);
                        } else {
                            groupDataModel.setTurboIsOn(false);
                        }
                    } else if (parseInt23 != 0 && ExchData.isDualACMode() && ExchData.isDualDucted() && parseInt23 + ExchData.getAC1().getGroupNumber() == i31) {
                        groupDataModel.setIsTurboGroup(true);
                        if (ExchData.getAC2().isTurbo()) {
                            groupDataModel.setTurboIsOn(true);
                            groupDataModel.setFanValue(100);
                        } else {
                            groupDataModel.setTurboIsOn(false);
                        }
                    } else {
                        groupDataModel.setIsTurboGroup(false);
                    }
                    if (ExchData.isDualACMode() && ExchData.isDualDucted()) {
                        if (this.finishedAddingToDataList) {
                            if (i29 < parseInt18) {
                                ExchData.setGroupDataModel(i29, groupDataModel);
                            } else {
                                ExchData.setGroupDataModel(i31, groupDataModel);
                            }
                        } else if (ExchData.getGroupDataList().size() <= ExchData.getGroupNumber()) {
                            if (ExchData.getGroupDataList().size() == parseInt18) {
                                ExchData.addSeparatorToGroupDataList();
                            }
                            ExchData.addToGroupDataList(groupDataModel);
                        } else {
                            this.finishedAddingToDataList = true;
                            printdatalist();
                        }
                    } else if (ExchData.getGroupDataList().size() < ExchData.getGroupNumber()) {
                        ExchData.addToGroupDataList(groupDataModel);
                    } else {
                        ExchData.setGroupDataModel(i29, groupDataModel);
                    }
                    i29 = i31;
                    i2 = 0;
                    i6 = 2;
                }
            }
        }
        int i32 = 0;
        int i33 = 0;
        for (int i34 = 0; i34 < ExchData.getGroupDataList().size(); i34++) {
            if (i34 >= ExchData.getAC1().getGroupNumber()) {
                if (ExchData.getGroupDataList().get(i34).isOn()) {
                    i33++;
                }
            } else if (ExchData.getGroupDataList().get(i34).isOn()) {
                i32++;
            }
        }
        ExchData.setAc1groupsOn(i32);
        ExchData.setAc2groupsOn(i33);
        StringBuilder sb2 = new StringBuilder();
        for (int i35 = 0; i35 < 8; i35++) {
            sb2.append(Integer.parseInt(strArr[i35 + 386].substring(4, 8), 2));
        }
        ExchData.setAirtouchID(sb2.toString());
        checkAndSaveNewIDAndName2(sb2.toString(), ExchData.getSystemName());
        ExchData.setToshibaThreeFanSpeed(this.settings.getBoolean(ExchData.getToshibaFanSpeedSettingName(), false));
    }

    private void checkAndSaveNewIDAndName2(String str, String str2) {
        SharedPreferences.Editor edit = this.settings.edit();
        int i = this.settings.getInt("savedSystems", 0);
        String string = this.settings.getString("tempPassword", "Polyaire");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (i == 0) {
            i++;
            arrayList.add(str);
            arrayList2.add(str2);
            arrayList3.add(string);
        } else {
            for (int i2 = 0; i2 < i; i2++) {
                SharedPreferences sharedPreferences = this.settings;
                arrayList.add(sharedPreferences.getString("airtouchID." + i2, ""));
                SharedPreferences sharedPreferences2 = this.settings;
                arrayList2.add(sharedPreferences2.getString("ownerName." + i2, ""));
                SharedPreferences sharedPreferences3 = this.settings;
                arrayList3.add(sharedPreferences3.getString("airtouchPassword." + i2, ""));
            }
        }
        int i3 = 0;
        boolean z = false;
        while (true) {
            if (i3 >= arrayList.size()) {
                break;
            } else if (((String) arrayList.get(i3)).equals(str)) {
                arrayList.remove(i3);
                arrayList.add(str);
                arrayList2.remove(i3);
                arrayList2.add(str2);
                String str3 = (String) arrayList3.get(i3);
                if (ExchData.mConnection.isInternetMode()) {
                    str3 = string;
                }
                arrayList3.remove(i3);
                arrayList3.add(str3);
            } else {
                if (i3 == arrayList.size() - 1) {
                    z = true;
                }
                i3++;
            }
        }
        if (z) {
            i++;
            arrayList.add(str);
            arrayList2.add(str2);
            arrayList3.add(string);
        }
        edit.putInt("savedSystems", i);
        for (int i4 = 0; i4 < i; i4++) {
            edit.putString("airtouchID." + i4, (String) arrayList.get(i4));
            edit.putString("ownerName." + i4, (String) arrayList2.get(i4));
            edit.putString("airtouchPassword." + i4, (String) arrayList3.get(i4));
        }
        edit.apply();
    }

    private void printdatalist() {
        for (int i = 0; i < ExchData.getGroupDataList().size(); i++) {
            PrintStream printStream = System.out;
            printStream.println("Index: " + i);
            System.out.println(ExchData.getGroupDataList().get(i).getGroupName());
            PrintStream printStream2 = System.out;
            printStream2.println("Is separator?? + " + ExchData.getGroupDataList().get(i).isSeparator());
        }
    }

    private void replaceBrandWithGatewayID(ACInfo aCInfo, int i) {
        if (i != 0) {
            switch (i) {
                case 5:
                case 255:
                    aCInfo.setBrand(5);
                    return;
                case 8:
                    aCInfo.setBrand(1);
                    return;
                case 13:
                case 34:
                    aCInfo.setBrand(2);
                    return;
                case 15:
                    aCInfo.setBrand(6);
                    return;
                case 16:
                    aCInfo.setBrand(4);
                    return;
                case 18:
                    aCInfo.setBrand(14);
                    return;
                case 20:
                    aCInfo.setBrand(12);
                    return;
                case 21:
                    aCInfo.setBrand(7);
                    return;
                case 31:
                    aCInfo.setBrand(10);
                    return;
                case 224:
                    aCInfo.setBrand(11);
                    return;
                case 225:
                    aCInfo.setBrand(13);
                    return;
                default:
                    return;
            }
        }
    }

    public String toFullBinaryString(int i) {
        char[] cArr = new char[32];
        for (int i2 = 0; i2 < 32; i2++) {
            cArr[31 - i2] = (char) (((i >> i2) & 1) + 48);
        }
        return new String(cArr);
    }
}
