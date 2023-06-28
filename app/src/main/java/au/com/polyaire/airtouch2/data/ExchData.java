package au.com.polyaire.airtouch2.data;

import android.widget.Toast;
import au.com.polyaire.airtouch2.comm.ConnCheckTool;
import au.com.polyaire.airtouch2.comm.DeviceConnection;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ExchData {
    static int ACbrand;
    static String ACstatus;
    static String airtouchID;
    static int groupNumber;
    static boolean isDualDucted;
    static int onGroupCount;
    static boolean[] programHasTimes;
    static String remindDay;
    static int selectedFanMode;
    static int selectedMode;
    static String setPoint;
    static String systemName;
    static String temperature;
    static String[] allData = new String[353];
    static ACInfo AC1 = new ACInfo();
    static ACInfo AC2 = new ACInfo();
    static boolean dualACMode = false;
    static int selectedAC = 0;
    static String[] maintain = new String[2];
    static String[] programTime = new String[96];
    static String[] groupName = new String[128];
    static String[] zoneData = new String[16];
    static String[] groupData = new String[16];
    static String[] zoneBalance = new String[16];
    static String[] sysPara = new String[8];
    static String[] contactName = new String[10];
    static String[] contactNO = new String[12];
    static String[] groupOpen = new String[16];
    static String[] systemTime = new String[6];
    static String[] ACtimer = new String[8];
    static String[] serviceInfo = new String[23];
    static ArrayList<GroupDataModel> groupDataList = new ArrayList<>();
    static String systemStatus = "normal";
    static int ac1groupsOn = 0;
    static int ac2groupsOn = 0;
    static boolean displayTempInTopSpill = false;
    static boolean toshibaThreeFanSpeed = false;
    static boolean waitingForNormalMsg = false;
    public static DeviceConnection mConnection = null;
    public static String mErrorTitle = "";
    public static String mErrorMsg = "";

    public static String[] getAllData() {
        return allData;
    }

    public static void setAllData(String[] strArr) {
        allData = strArr;
    }

    public static String getACstatus() {
        return ACstatus;
    }

    public static void setACstatus(String str) {
        ACstatus = str;
    }

    public static int getACbrand() {
        return ACbrand;
    }

    public static void setACbrand(int i) {
        ACbrand = i;
    }

    public static String getSetPoint() {
        return setPoint;
    }

    public static void setSetPoint(String str) {
        setPoint = str;
    }

    public static String[] getMaintain() {
        return maintain;
    }

    public static void setMaintain(String[] strArr) {
        maintain = strArr;
    }

    public static String[] getProgramTime() {
        return programTime;
    }

    public static void setProgramTime(String[] strArr) {
        programTime = strArr;
    }

    public static String[] getGroupName() {
        return groupName;
    }

    public static void setGroupName(String[] strArr) {
        groupName = strArr;
    }

    public static String[] getZoneData() {
        return zoneData;
    }

    public static void setZoneData(String[] strArr) {
        zoneData = strArr;
    }

    public static String[] getGroupData() {
        return groupData;
    }

    public static void setGroupData(String[] strArr) {
        groupData = strArr;
    }

    public static String[] getZoneBalance() {
        return zoneBalance;
    }

    public static void setZoneBalance(String[] strArr) {
        zoneBalance = strArr;
    }

    public static String[] getSysPara() {
        return sysPara;
    }

    public static void setSysPara(String[] strArr) {
        sysPara = strArr;
    }

    public static String[] getContactName() {
        return contactName;
    }

    public static void setContactName(String[] strArr) {
        contactName = strArr;
    }

    public static String[] getContactNO() {
        return contactNO;
    }

    public static void setContactNO(String[] strArr) {
        contactNO = strArr;
    }

    public static String getRemindDay() {
        return remindDay;
    }

    public static void setRemindDay(String str) {
        remindDay = str;
    }

    public static String[] getGroupOpen() {
        return groupOpen;
    }

    public static void setGroupOpen(String[] strArr) {
        groupOpen = strArr;
    }

    public static String getTemperature() {
        return temperature;
    }

    public static void setTemperature(String str) {
        temperature = str;
    }

    public static String getSystemName() {
        return systemName;
    }

    public static void setSystemName(String str) {
        systemName = str;
    }

    public static String[] getSystemTime() {
        return systemTime;
    }

    public static void setSystemTime(String[] strArr) {
        systemTime = strArr;
    }

    public static String[] getACtimer() {
        return ACtimer;
    }

    public static void setACtimer(String[] strArr) {
        ACtimer = strArr;
    }

    public static String[] getServiceInfo() {
        return serviceInfo;
    }

    public static void setServiceInfo(String[] strArr) {
        serviceInfo = strArr;
    }

    public static int getGroupNumber() {
        return groupNumber;
    }

    public static void setGroupNumber(int i) {
        groupNumber = i;
    }

    public static ArrayList<GroupDataModel> getGroupDataList() {
        return groupDataList;
    }

    public static void setGroupDataList(ArrayList<GroupDataModel> arrayList) {
        groupDataList = arrayList;
    }

    public static void addToGroupDataList(GroupDataModel groupDataModel) {
        groupDataList.add(groupDataModel);
    }

    public static void addSeparatorToGroupDataList() {
        GroupDataModel groupDataModel = new GroupDataModel();
        groupDataModel.setGroupName("Separator");
        groupDataModel.setIsSeparator(true);
        addToGroupDataList(groupDataModel);
    }

    public static void setGroupDataModel(int i, GroupDataModel groupDataModel) {
        groupDataList.set(i, groupDataModel);
    }

    public static int getOnGroupCount() {
        return onGroupCount;
    }

    public static void setOnGroupCount(int i) {
        onGroupCount = i;
    }

    public static String getSystemStatus() {
        return systemStatus;
    }

    public static void setSystemStatus(String str) {
        systemStatus = str;
    }

    public static int getAc1groupsOn() {
        return ac1groupsOn;
    }

    public static void setAc1groupsOn(int i) {
        ac1groupsOn = i;
    }

    public static int getAc2groupsOn() {
        return ac2groupsOn;
    }

    public static void setAc2groupsOn(int i) {
        ac2groupsOn = i;
    }

    public static int getSelectedMode() {
        return selectedMode;
    }

    public static void setSelectedMode(int i) {
        selectedMode = i;
    }

    public static int getSelectedFanMode() {
        return selectedFanMode;
    }

    public static void setSelectedFanMode(int i) {
        selectedFanMode = i;
    }

    public static ACInfo getAC1() {
        return AC1;
    }

    public static void setAC1(ACInfo aCInfo) {
        AC1 = aCInfo;
    }

    public static ACInfo getAC2() {
        return AC2;
    }

    public static void setAC2(ACInfo aCInfo) {
        AC2 = aCInfo;
    }

    public static boolean isDualACMode() {
        return dualACMode;
    }

    public static void setDualACMode(boolean z) {
        dualACMode = z;
    }

    public static int getSelectedAC() {
        return selectedAC;
    }

    public static void changeSelectedAC() {
        if (selectedAC == 0) {
            selectedAC = 1;
        } else {
            selectedAC = 0;
        }
    }

    public static void setSelectedAC(int i) {
        selectedAC = i;
    }

    public static boolean isDualDucted() {
        return isDualDucted;
    }

    public static void setIsDualDucted(boolean z) {
        isDualDucted = z;
    }

    public static String getAirtouchID() {
        return airtouchID;
    }

    public static void setAirtouchID(String str) {
        airtouchID = str;
    }

    public static boolean isDisplayTempInTopSpill() {
        return displayTempInTopSpill;
    }

    public static void setDisplayTempInTopSpill(boolean z) {
        displayTempInTopSpill = z;
    }

    public static boolean[] getProgramHasTimes() {
        return programHasTimes;
    }

    public static void setProgramHasTimes(boolean[] zArr) {
        programHasTimes = zArr;
    }

    public static boolean isToshibaThreeFanSpeed() {
        return toshibaThreeFanSpeed;
    }

    public static void setToshibaThreeFanSpeed(boolean z) {
        toshibaThreeFanSpeed = z;
    }

    public static String getToshibaFanSpeedSettingName() {
        return "TOSHIBAFanSpeed" + airtouchID;
    }

    public static boolean hasToshibaAC() {
        return 10 == AC1.getBrand() || 10 == AC2.getBrand();
    }

    public static boolean isWaitingForNormalMsg() {
        return waitingForNormalMsg;
    }

    public static void setWaitingForNormalMsg(boolean z) {
        waitingForNormalMsg = z;
    }

    public static boolean isConnected() {
        if (mConnection == null) {
            return false;
        }
        return mConnection.isConnected();
    }

    public static void sendMessage(byte[] bArr) {
        if (!isConnected()) {
            ConnCheckTool.instance().mShowInfo = true;
            Toast.makeText(mConnection.getContext(), "Connection lost, trying to connect again. Please wait.", 0).show();
            return;
        }
        mConnection.sendData(bArr);
    }
}
