package au.com.polyaire.airtouch2.data;

import android.support.v4.view.InputDeviceCompat;

/* loaded from: classes.dex */
public class ACInfo {
    private int acID;
    private E_ACMode acMode;
    private String acName;
    private E_ACStatus acStatus;
    private int activeGroups;
    private int brand;
    private E_ACControlMode controlMode;
    private boolean displayGatewayTemp;
    private int errorCode;
    private E_ACFanMode fanMode;
    private int groupNumber;
    private boolean hasProgram;
    private boolean hasTimer;
    private boolean isError;
    private boolean isSafety;
    private boolean isSpill;
    private boolean isTurbo;
    private int programNumber;
    private int roomTemp;
    private int setPoint;
    private int supportedFanSpeed;

    /* loaded from: classes.dex */
    public enum E_ACControlMode {
        NotAvailable,
        Basic,
        Full
    }

    /* loaded from: classes.dex */
    public enum E_ACFanMode {
        Quiet,
        Low,
        Medium,
        High,
        Powerful,
        Auto
    }

    /* loaded from: classes.dex */
    public enum E_ACMode {
        Heat,
        Cool,
        Fan,
        Dry,
        Auto
    }

    /* loaded from: classes.dex */
    public enum E_ACStatus {
        ACOff,
        ACOn
    }

    /* loaded from: classes.dex */
    public enum E_SupportedFanSpeed {
        LowHigh,
        LowMedHigh,
        Full
    }

    public E_ACStatus getAcStatus() {
        return this.acStatus;
    }

    public String getAcName() {
        return this.acName;
    }

    public void setAcName(String str) {
        this.acName = str;
    }

    public void setAcStatus(E_ACStatus e_ACStatus) {
        this.acStatus = e_ACStatus;
    }

    public int getActiveGroups() {
        return this.activeGroups;
    }

    public void setActiveGroups(int i) {
        this.activeGroups = i;
    }

    public boolean isError() {
        return this.isError;
    }

    public void setIsError(boolean z) {
        this.isError = z;
    }

    public boolean isSpill() {
        return this.isSpill;
    }

    public void setIsSpill(boolean z) {
        this.isSpill = z;
    }

    public boolean isSafety() {
        return this.isSafety;
    }

    public void setIsSafety(boolean z) {
        this.isSafety = z;
    }

    public boolean isTurbo() {
        return this.isTurbo;
    }

    public void setIsTurbo(boolean z) {
        this.isTurbo = z;
    }

    public E_ACControlMode getControlMode() {
        return this.controlMode;
    }

    public void setControlMode(E_ACControlMode e_ACControlMode) {
        this.controlMode = e_ACControlMode;
    }

    public int getSupportedFanSpeed() {
        return this.supportedFanSpeed;
    }

    public void setSupportedFanSpeed(int i) {
        if (this.acID == 5 && 4 == i) {
            i = 3;
        }
        this.supportedFanSpeed = i;
    }

    public int getSetPoint() {
        return this.setPoint;
    }

    public void setSetPoint(int i) {
        this.setPoint = i;
    }

    public int getRoomTemp() {
        return this.roomTemp;
    }

    public void setRoomTemp(int i) {
        if (i > 127) {
            this.roomTemp = i + InputDeviceCompat.SOURCE_ANY;
        } else {
            this.roomTemp = i;
        }
    }

    public E_ACMode getAcMode() {
        return this.acMode;
    }

    public void setAcMode(E_ACMode e_ACMode) {
        this.acMode = e_ACMode;
    }

    public E_ACFanMode getFanMode() {
        return this.fanMode;
    }

    public void setFanMode(E_ACFanMode e_ACFanMode) {
        this.fanMode = e_ACFanMode;
    }

    public boolean isHasTimer() {
        return this.hasTimer;
    }

    public void setHasTimer(boolean z) {
        this.hasTimer = z;
    }

    public boolean isHasProgram() {
        return this.hasProgram;
    }

    public void setHasProgram(boolean z) {
        this.hasProgram = z;
    }

    public int getProgramNumber() {
        return this.programNumber;
    }

    public void setProgramNumber(int i) {
        this.programNumber = i;
    }

    public int getBrand() {
        return this.brand;
    }

    public void setBrand(int i) {
        this.brand = i;
    }

    public int getGroupNumber() {
        return this.groupNumber;
    }

    public void setGroupNumber(int i) {
        this.groupNumber = i;
    }

    public int getAcID() {
        return this.acID;
    }

    public void setAcID(int i) {
        this.acID = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        if (this.brand == 11 && i >= 109 && i <= 116) {
            i = 0;
        }
        this.errorCode = i;
    }

    public boolean isDisplayGatewayTemp() {
        return this.displayGatewayTemp;
    }

    public void setDisplayGatewayTemp(boolean z) {
        this.displayGatewayTemp = z;
    }

    public static E_ACFanMode formatFanSpeed(int i, int i2, int i3) {
        if (i == 0) {
            return E_ACFanMode.Auto;
        }
        if (i < 5) {
            E_ACFanMode[] e_ACFanModeArr = {E_ACFanMode.Quiet, E_ACFanMode.Low, E_ACFanMode.Medium, E_ACFanMode.High, E_ACFanMode.Powerful};
            if (2 == i2 && 4 == i3) {
                return e_ACFanModeArr[i - 1];
            }
            return e_ACFanModeArr[i];
        }
        return E_ACFanMode.Auto;
    }

    public static int formatFanSpeed(E_ACFanMode e_ACFanMode, int i, int i2) {
        int i3 = 0;
        if (E_ACFanMode.Auto == e_ACFanMode) {
            return 0;
        }
        if (E_ACFanMode.Low == e_ACFanMode) {
            i3 = 1;
        } else if (E_ACFanMode.Medium == e_ACFanMode) {
            i3 = 2;
        } else if (E_ACFanMode.High == e_ACFanMode) {
            i3 = 3;
        } else if (E_ACFanMode.Powerful == e_ACFanMode) {
            i3 = 4;
        }
        return (2 == i && 4 == i2) ? i3 + 1 : i3;
    }
}
