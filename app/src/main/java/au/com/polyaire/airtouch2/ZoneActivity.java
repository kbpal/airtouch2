package au.com.polyaire.airtouch2;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import au.com.polyaire.airtouch2.data.ACInfo;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityBase;
import java.io.PrintStream;

/* loaded from: classes.dex */
public class ZoneActivity extends ActivityBase {
    GroupListAdapter groupArrayAdapter;
    private TextView internetIcon;
    private TextView systemStatus;

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected int getMainViewID() {
        return C0377R.id.zoneMain;
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onUpdateUI() {
        updateSystemStatus();
        if (this.groupArrayAdapter != null) {
            this.groupArrayAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.activity_zone);
        System.out.println("Zone onCreate");
        this.systemStatus = (TextView) findViewById(C0377R.id.systemStatus);
        this.internetIcon = (TextView) findViewById(C0377R.id.internetIcon);
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onSubResume() {
        initiateListview();
    }

    private void initiateListview() {
        this.groupArrayAdapter = new GroupListAdapter(this, ExchData.getGroupDataList());
        for (int i = 0; i < ExchData.getGroupDataList().size(); i++) {
            PrintStream printStream = System.out;
            printStream.println("gname " + ExchData.getGroupDataList().get(i).getGroupName());
            PrintStream printStream2 = System.out;
            printStream2.println("separator??" + ExchData.getGroupDataList().get(i).isSeparator());
        }
        ((ListView) findViewById(C0377R.id.listview)).setAdapter((ListAdapter) this.groupArrayAdapter);
    }

    private void updateSystemStatus() {
        if (ExchData.mConnection != null && ExchData.mConnection.isInternetMode()) {
            this.internetIcon.setVisibility(0);
        }
        if (ExchData.isDualDucted() && ExchData.isDualACMode()) {
            this.systemStatus.setText(ExchData.getAC1().getAcName());
        } else if (ExchData.getAC1().getControlMode() == ACInfo.E_ACControlMode.NotAvailable) {
            this.systemStatus.setText("AC Control Unavailable");
        } else {
            ACInfo ac1 = ExchData.getAC1();
            this.systemStatus.setText("NORMAL");
            if (ac1.isSpill()) {
                this.systemStatus.setText("SPILL ACTIVATED");
            }
            if (ac1.isSafety()) {
                this.systemStatus.setText("SAFETY IS ON");
            }
            int errorCode = ac1.getErrorCode();
            if ((ExchData.getAC1().getControlMode() == ACInfo.E_ACControlMode.Full || ExchData.getAC2().getControlMode() == ACInfo.E_ACControlMode.Full) && errorCode != 0) {
                TextView textView = this.systemStatus;
                textView.setText("AC ERROR - CODE: " + String.format("%02X", Integer.valueOf(errorCode)));
            }
            if ((ExchData.getAC1().getControlMode() == ACInfo.E_ACControlMode.Basic || ExchData.getAC2().getControlMode() == ACInfo.E_ACControlMode.Basic) && ac1.isError()) {
                this.systemStatus.setText("AC ERROR");
            }
        }
    }
}
