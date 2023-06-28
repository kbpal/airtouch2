package au.com.polyaire.airtouch2.data;

/* loaded from: classes.dex */
public class GroupDataModel {
    private int fanValue;
    private boolean isSeparator = false;
    private boolean isOn = false;
    private boolean isTurboGroup = false;
    private boolean turboIsOn = false;
    private boolean isSpill = false;
    private int programNum = 0;
    private String groupName = "Unset";

    public boolean isOn() {
        return this.isOn;
    }

    public void setIsOn(boolean z) {
        this.isOn = z;
    }

    public boolean isTurboGroup() {
        return this.isTurboGroup;
    }

    public void setIsTurboGroup(boolean z) {
        this.isTurboGroup = z;
    }

    public boolean isTurboIsOn() {
        return this.turboIsOn;
    }

    public void setTurboIsOn(boolean z) {
        this.turboIsOn = z;
    }

    public boolean isSpill() {
        return this.isSpill;
    }

    public void setIsSpill(boolean z) {
        this.isSpill = z;
    }

    public int getProgramNum() {
        return this.programNum;
    }

    public void setProgramNum(int i) {
        this.programNum = i;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public int getFanValue() {
        return this.fanValue;
    }

    public void setFanValue(int i) {
        this.fanValue = i;
    }

    public boolean isSeparator() {
        return this.isSeparator;
    }

    public void setIsSeparator(boolean z) {
        this.isSeparator = z;
    }
}
