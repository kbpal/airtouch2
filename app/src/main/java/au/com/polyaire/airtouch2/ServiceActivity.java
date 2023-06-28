package au.com.polyaire.airtouch2;

import android.os.Bundle;
import android.widget.TextView;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityBase;

/* loaded from: classes.dex */
public class ServiceActivity extends ActivityBase {
    TextView halfYear;
    TextView installer;
    TextView oneYear;
    TextView reminder;
    TextView telNumber;
    TextView twoYear;

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected int getMainViewID() {
        return C0377R.id.serviceMain;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.service_layout);
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onUpdateUI() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String[] serviceInfo = ExchData.getServiceInfo();
        this.installer = (TextView) findViewById(C0377R.id.installer);
        this.telNumber = (TextView) findViewById(C0377R.id.telnumber);
        this.reminder = (TextView) findViewById(C0377R.id.reminder);
        this.halfYear = (TextView) findViewById(C0377R.id.halfyear);
        this.oneYear = (TextView) findViewById(C0377R.id.oneyear);
        this.twoYear = (TextView) findViewById(C0377R.id.twoyear);
        for (int i = 0; i <= 9; i++) {
            sb.append(String.valueOf((char) Integer.parseInt(serviceInfo[i], 2)));
        }
        this.installer.setText(sb.toString());
        int i2 = 10;
        while (true) {
            if (i2 > 21) {
                break;
            }
            if ((Integer.parseInt(serviceInfo[i2], 2) > 57) | (Integer.parseInt(serviceInfo[i2], 2) < 48)) {
                sb2.append(" ");
            } else {
                sb2.append(Integer.parseInt(serviceInfo[i2], 2) - 48);
            }
            i2++;
        }
        this.telNumber.setText(sb2.toString());
        String substring = serviceInfo[22].substring(0, 1);
        String substring2 = serviceInfo[22].substring(1, 2);
        String substring3 = serviceInfo[22].substring(2, 3);
        if ("0".equals(substring)) {
            this.halfYear.setText("Enabled");
        } else {
            this.halfYear.setText("Disabled");
        }
        if ("0".equals(substring2)) {
            this.oneYear.setText("Enabled");
        } else {
            this.oneYear.setText("Disabled");
        }
        if ("0".equals(substring3)) {
            this.twoYear.setText("Enabled");
        } else {
            this.twoYear.setText("Disabled");
        }
        String substring4 = serviceInfo[22].substring(3);
        TextView textView = this.reminder;
        textView.setText(String.valueOf(Integer.parseInt(substring4, 2)) + " Days");
    }
}
