package au.com.polyaire.airtouch2;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityProgSettingBase;
import java.util.Locale;

/* loaded from: classes.dex */
public class ProgramInfoMF extends ActivityProgSettingBase {
    Intent intent;
    private int mHour;
    private int mMinute;
    TextView programNO;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    Button tb1;
    Button tb2;
    Button tb3;
    Button tb4;
    String progNO = "";
    String[] programs = new String[96];
    MessageInBytes sendMessage = new MessageInBytes();
    View.OnClickListener onClickListerner = new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case C0377R.id.Button1 /* 2131230722 */:
                    ProgramInfoMF.this.sendMessage.setProgramMessage("MF", 1, ProgramInfoMF.this.progNO, -1, 0);
                    ProgramInfoMF.this.SendMessage(ProgramInfoMF.this.sendMessage.GetInBytes());
                    return;
                case C0377R.id.Button2 /* 2131230723 */:
                    ProgramInfoMF.this.sendMessage.setProgramMessage("MF", 2, ProgramInfoMF.this.progNO, -1, 0);
                    ProgramInfoMF.this.SendMessage(ProgramInfoMF.this.sendMessage.GetInBytes());
                    return;
                case C0377R.id.Button3 /* 2131230724 */:
                    ProgramInfoMF.this.sendMessage.setProgramMessage("MF", 3, ProgramInfoMF.this.progNO, -1, 0);
                    ProgramInfoMF.this.SendMessage(ProgramInfoMF.this.sendMessage.GetInBytes());
                    return;
                case C0377R.id.Button4 /* 2131230725 */:
                    ProgramInfoMF.this.sendMessage.setProgramMessage("MF", 4, ProgramInfoMF.this.progNO, -1, 0);
                    ProgramInfoMF.this.SendMessage(ProgramInfoMF.this.sendMessage.GetInBytes());
                    return;
                default:
                    return;
            }
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.6
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ProgramInfoMF.this.mHour = i;
            ProgramInfoMF.this.mMinute = i2;
            ProgramInfoMF.this.sendMessage.setProgramMessage("MF", 1, ProgramInfoMF.this.progNO, i, i2);
            ProgramInfoMF.this.SendMessage(ProgramInfoMF.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.7
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ProgramInfoMF.this.mHour = i;
            ProgramInfoMF.this.mMinute = i2;
            ProgramInfoMF.this.sendMessage.setProgramMessage("MF", 2, ProgramInfoMF.this.progNO, i, i2);
            ProgramInfoMF.this.SendMessage(ProgramInfoMF.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener3 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.8
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ProgramInfoMF.this.mHour = i;
            ProgramInfoMF.this.mMinute = i2;
            ProgramInfoMF.this.sendMessage.setProgramMessage("MF", 3, ProgramInfoMF.this.progNO, i, i2);
            ProgramInfoMF.this.SendMessage(ProgramInfoMF.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener4 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.9
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ProgramInfoMF.this.mHour = i;
            ProgramInfoMF.this.mMinute = i2;
            ProgramInfoMF.this.sendMessage.setProgramMessage("MF", 4, ProgramInfoMF.this.progNO, i, i2);
            ProgramInfoMF.this.SendMessage(ProgramInfoMF.this.sendMessage.GetInBytes());
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.programmf);
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);
        this.progNO = getIntent().getStringArrayExtra("programs")[96];
        this.t1 = (TextView) findViewById(C0377R.id.mtftime1onstatus);
        this.t2 = (TextView) findViewById(C0377R.id.mtftime1offstatus);
        this.t3 = (TextView) findViewById(C0377R.id.mtftime2onstatus);
        this.t4 = (TextView) findViewById(C0377R.id.mtftime2offstatus);
        this.tb1 = (Button) findViewById(C0377R.id.Button1);
        this.tb2 = (Button) findViewById(C0377R.id.Button2);
        this.tb3 = (Button) findViewById(C0377R.id.Button3);
        this.tb4 = (Button) findViewById(C0377R.id.Button4);
        this.programNO = (TextView) findViewById(C0377R.id.programno);
        this.t1.setOnClickListener(new TimeListener(C0377R.id.mtftime1onstatus));
        this.t2.setOnClickListener(new TimeListener(C0377R.id.mtftime1offstatus));
        this.t3.setOnClickListener(new TimeListener(C0377R.id.mtftime2onstatus));
        this.t4.setOnClickListener(new TimeListener(C0377R.id.mtftime2offstatus));
        this.tb1.setOnClickListener(this.onClickListerner);
        this.tb2.setOnClickListener(this.onClickListerner);
        this.tb3.setOnClickListener(this.onClickListerner);
        this.tb4.setOnClickListener(this.onClickListerner);
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityProgSettingBase
    protected void onUpdateUI() {
        this.programNO.setText(this.progNO);
        this.programs = ExchData.getProgramTime();
        if ((this.progNO != null) && (this.programs != null)) {
            SetTime(this.progNO);
        }
    }

    /* loaded from: classes.dex */
    class TimeListener implements View.OnClickListener {
        int choose;

        public TimeListener(int i) {
            this.choose = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (this.choose) {
                case C0377R.id.mtftime1offstatus /* 2131231042 */:
                    ProgramInfoMF.this.mHour = Integer.parseInt(ProgramInfoMF.this.t2.getText().toString().substring(0, 2));
                    if ("P".equals(ProgramInfoMF.this.t2.getText().toString().substring(5, 6))) {
                        ProgramInfoMF.this.mHour += 12;
                    }
                    ProgramInfoMF.this.mMinute = Integer.parseInt(ProgramInfoMF.this.t2.getText().toString().substring(3, 5));
                    ProgramInfoMF.this.showDialog(2);
                    return;
                case C0377R.id.mtftime1onstatus /* 2131231043 */:
                    ProgramInfoMF.this.mHour = Integer.parseInt(ProgramInfoMF.this.t1.getText().toString().substring(0, 2));
                    if ("P".equals(ProgramInfoMF.this.t1.getText().toString().substring(5, 6))) {
                        ProgramInfoMF.this.mHour += 12;
                    }
                    ProgramInfoMF.this.mMinute = Integer.parseInt(ProgramInfoMF.this.t1.getText().toString().substring(3, 5));
                    ProgramInfoMF.this.showDialog(1);
                    return;
                case C0377R.id.mtftime2offstatus /* 2131231044 */:
                    ProgramInfoMF.this.mHour = Integer.parseInt(ProgramInfoMF.this.t4.getText().toString().substring(0, 2));
                    if ("P".equals(ProgramInfoMF.this.t4.getText().toString().substring(5, 6))) {
                        ProgramInfoMF.this.mHour += 12;
                    }
                    ProgramInfoMF.this.mMinute = Integer.parseInt(ProgramInfoMF.this.t4.getText().toString().substring(3, 5));
                    ProgramInfoMF.this.showDialog(4);
                    return;
                case C0377R.id.mtftime2onstatus /* 2131231045 */:
                    ProgramInfoMF.this.mHour = Integer.parseInt(ProgramInfoMF.this.t3.getText().toString().substring(0, 2));
                    if ("P".equals(ProgramInfoMF.this.t3.getText().toString().substring(5, 6))) {
                        ProgramInfoMF.this.mHour += 12;
                    }
                    ProgramInfoMF.this.mMinute = Integer.parseInt(ProgramInfoMF.this.t3.getText().toString().substring(3, 5));
                    ProgramInfoMF.this.showDialog(3);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 1:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, this.mTimeSetListener1, this.mHour, this.mMinute, false);
                timePickerDialog.setTitle("Mon-Fri Time1 ON");
                timePickerDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ProgramInfoMF.this.removeDialog(1);
                    }
                });
                return timePickerDialog;
            case 2:
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(this, this.mTimeSetListener2, this.mHour, this.mMinute, false);
                timePickerDialog2.setTitle("Mon-Fri Time1 OFF");
                timePickerDialog2.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ProgramInfoMF.this.removeDialog(2);
                    }
                });
                return timePickerDialog2;
            case 3:
                TimePickerDialog timePickerDialog3 = new TimePickerDialog(this, this.mTimeSetListener3, this.mHour, this.mMinute, false);
                timePickerDialog3.setTitle("Mon-Fri Time2 ON");
                timePickerDialog3.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ProgramInfoMF.this.removeDialog(3);
                    }
                });
                return timePickerDialog3;
            case 4:
                TimePickerDialog timePickerDialog4 = new TimePickerDialog(this, this.mTimeSetListener4, this.mHour, this.mMinute, false);
                timePickerDialog4.setTitle("Mon-Fri Time2 OFF");
                timePickerDialog4.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoMF.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ProgramInfoMF.this.removeDialog(4);
                    }
                });
                return timePickerDialog4;
            default:
                return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0409  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void SetTime(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 1274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: au.com.polyaire.airtouch2.ProgramInfoMF.SetTime(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendMessage(byte[] bArr) {
        ExchData.sendMessage(bArr);
    }

    public String toFullBinaryString(int i) {
        char[] cArr = new char[32];
        for (int i2 = 0; i2 < 32; i2++) {
            cArr[31 - i2] = (char) (((i >> i2) & 1) + 48);
        }
        return new String(cArr);
    }
}
