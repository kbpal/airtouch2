package au.com.polyaire.airtouch2;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityProgSettingBase;
import java.util.Locale;

/* loaded from: classes.dex */
public class ACTimerActivity extends ActivityProgSettingBase {
    String[] ACTimer;
    Button ac1OffButton;
    Button ac1OnButton;
    TextView ac1TimeOff;
    TextView ac1TimeOffDisplay;
    TextView ac1TimeOn;
    TextView ac1TimeOnDisplay;
    Button ac2OffButton;
    Button ac2OnButton;
    TextView ac2TimeOff;
    TextView ac2TimeOffDisplay;
    TextView ac2TimeOn;
    TextView ac2TimeOnDisplay;
    private int mHour;
    private int mMinute;
    MessageInBytes sendMessage = new MessageInBytes();
    View.OnClickListener onClickListerner = new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case C0377R.id.Button1 /* 2131230722 */:
                    ACTimerActivity.this.sendMessage.SetACTimeMessage(1, false, 0, 0);
                    break;
                case C0377R.id.Button2 /* 2131230723 */:
                    ACTimerActivity.this.sendMessage.SetACTimeMessage(2, false, 0, 0);
                    break;
                case C0377R.id.ac2OffButton /* 2131230740 */:
                    ACTimerActivity.this.sendMessage.SetACTimeMessage(4, false, 0, 0);
                    break;
                case C0377R.id.ac2onButton /* 2131230746 */:
                    ACTimerActivity.this.sendMessage.SetACTimeMessage(3, false, 0, 0);
                    break;
            }
            Log.d("SendMessage", new String(ACTimerActivity.this.sendMessage.GetInBytes()));
            ACTimerActivity.this.SendToAC(ACTimerActivity.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.6
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ACTimerActivity.this.sendMessage.SetACTimeMessage(1, true, i, i2);
            ACTimerActivity.this.SendToAC(ACTimerActivity.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.7
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ACTimerActivity.this.sendMessage.SetACTimeMessage(2, true, i, i2);
            ACTimerActivity.this.SendToAC(ACTimerActivity.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener3 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.8
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ACTimerActivity.this.sendMessage.SetACTimeMessage(3, true, i, i2);
            ACTimerActivity.this.SendToAC(ACTimerActivity.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener4 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.9
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ACTimerActivity.this.sendMessage.SetACTimeMessage(4, true, i, i2);
            ACTimerActivity.this.SendToAC(ACTimerActivity.this.sendMessage.GetInBytes());
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0377R.layout.actimer);
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);
        this.ac1TimeOn = (TextView) findViewById(C0377R.id.mtftime1onstatus);
        this.ac1TimeOff = (TextView) findViewById(C0377R.id.mtftime1offstatus);
        this.ac2TimeOn = (TextView) findViewById(C0377R.id.mtftime2onstatus);
        this.ac2TimeOff = (TextView) findViewById(C0377R.id.mtftime2offstatus);
        this.ac1OnButton = (Button) findViewById(C0377R.id.Button1);
        this.ac1OffButton = (Button) findViewById(C0377R.id.Button2);
        this.ac2OnButton = (Button) findViewById(C0377R.id.ac2onButton);
        this.ac2OffButton = (Button) findViewById(C0377R.id.ac2OffButton);
        this.ac1TimeOn.setOnClickListener(new TimeListener(C0377R.id.mtftime1onstatus));
        this.ac1TimeOff.setOnClickListener(new TimeListener(C0377R.id.mtftime1offstatus));
        this.ac2TimeOn.setOnClickListener(new TimeListener(C0377R.id.mtftime2onstatus));
        this.ac2TimeOff.setOnClickListener(new TimeListener(C0377R.id.mtftime2offstatus));
        this.ac1OnButton.setOnClickListener(this.onClickListerner);
        this.ac1OffButton.setOnClickListener(this.onClickListerner);
        this.ac2OnButton.setOnClickListener(this.onClickListerner);
        this.ac2OffButton.setOnClickListener(this.onClickListerner);
        this.ac1TimeOnDisplay = (TextView) findViewById(C0377R.id.ac1ontime);
        this.ac1TimeOffDisplay = (TextView) findViewById(C0377R.id.ac1offtime);
        this.ac2TimeOnDisplay = (TextView) findViewById(C0377R.id.ac2ontime);
        this.ac2TimeOffDisplay = (TextView) findViewById(C0377R.id.ac2offtime);
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityProgSettingBase
    protected void onUpdateUI() {
        if (ExchData.isDualACMode()) {
            ((TableRow) findViewById(C0377R.id.tableRow3)).setVisibility(0);
            ((TableRow) findViewById(C0377R.id.tableRow4)).setVisibility(0);
        }
        this.ACTimer = ExchData.getACtimer();
        TextView textView = this.ac1TimeOnDisplay;
        textView.setText(ExchData.getAC1().getAcName() + " ON");
        TextView textView2 = this.ac1TimeOffDisplay;
        textView2.setText(ExchData.getAC1().getAcName() + " OFF");
        TextView textView3 = this.ac2TimeOnDisplay;
        textView3.setText(ExchData.getAC2().getAcName() + " ON");
        TextView textView4 = this.ac2TimeOffDisplay;
        textView4.setText(ExchData.getAC2().getAcName() + " OFF");
        if (this.ACTimer != null) {
            SetTime();
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
                    ACTimerActivity.this.mHour = Integer.parseInt(ACTimerActivity.this.ac1TimeOff.getText().toString().substring(0, 2));
                    if ("P".equals(ACTimerActivity.this.ac1TimeOff.getText().toString().substring(5, 6))) {
                        ACTimerActivity.this.mHour += 12;
                    }
                    ACTimerActivity.this.mMinute = Integer.parseInt(ACTimerActivity.this.ac1TimeOff.getText().toString().substring(3, 5));
                    ACTimerActivity.this.showDialog(2);
                    return;
                case C0377R.id.mtftime1onstatus /* 2131231043 */:
                    ACTimerActivity.this.mHour = Integer.parseInt(ACTimerActivity.this.ac1TimeOn.getText().toString().substring(0, 2));
                    if ("P".equals(ACTimerActivity.this.ac1TimeOn.getText().toString().substring(5, 6))) {
                        ACTimerActivity.this.mHour += 12;
                    }
                    ACTimerActivity.this.mMinute = Integer.parseInt(ACTimerActivity.this.ac1TimeOn.getText().toString().substring(3, 5));
                    ACTimerActivity.this.showDialog(1);
                    return;
                case C0377R.id.mtftime2offstatus /* 2131231044 */:
                    ACTimerActivity.this.mHour = Integer.parseInt(ACTimerActivity.this.ac2TimeOff.getText().toString().substring(0, 2));
                    if ("P".equals(ACTimerActivity.this.ac2TimeOff.getText().toString().substring(5, 6))) {
                        ACTimerActivity.this.mHour += 12;
                    }
                    ACTimerActivity.this.mMinute = Integer.parseInt(ACTimerActivity.this.ac2TimeOff.getText().toString().substring(3, 5));
                    ACTimerActivity.this.showDialog(4);
                    return;
                case C0377R.id.mtftime2onstatus /* 2131231045 */:
                    ACTimerActivity.this.mHour = Integer.parseInt(ACTimerActivity.this.ac2TimeOn.getText().toString().substring(0, 2));
                    if ("P".equals(ACTimerActivity.this.ac2TimeOn.getText().toString().substring(5, 6))) {
                        ACTimerActivity.this.mHour += 12;
                    }
                    ACTimerActivity.this.mMinute = Integer.parseInt(ACTimerActivity.this.ac2TimeOn.getText().toString().substring(3, 5));
                    ACTimerActivity.this.showDialog(3);
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
                timePickerDialog.setTitle("AC ON Time");
                timePickerDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ACTimerActivity.this.removeDialog(1);
                    }
                });
                return timePickerDialog;
            case 2:
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(this, this.mTimeSetListener2, this.mHour, this.mMinute, false);
                timePickerDialog2.setTitle("AC OFF Time");
                timePickerDialog2.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ACTimerActivity.this.removeDialog(2);
                    }
                });
                return timePickerDialog2;
            case 3:
                TimePickerDialog timePickerDialog3 = new TimePickerDialog(this, this.mTimeSetListener3, this.mHour, this.mMinute, false);
                timePickerDialog3.setTitle("AC2 ON Time");
                timePickerDialog3.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ACTimerActivity.this.removeDialog(3);
                    }
                });
                return timePickerDialog3;
            case 4:
                TimePickerDialog timePickerDialog4 = new TimePickerDialog(this, this.mTimeSetListener4, this.mHour, this.mMinute, false);
                timePickerDialog4.setTitle("AC2 OFF Time");
                timePickerDialog4.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ACTimerActivity.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ACTimerActivity.this.removeDialog(4);
                    }
                });
                return timePickerDialog4;
            default:
                return null;
        }
    }

    public void SetTime() {
        int i;
        String str;
        String str2;
        int parseInt;
        String str3;
        int i2;
        String str4;
        String str5;
        int parseInt2;
        String str6;
        int i3;
        String str7;
        String str8;
        int parseInt3;
        String str9;
        int i4;
        String str10;
        String str11;
        int parseInt4;
        String str12;
        if ("0".equals(this.ACTimer[0].substring(0, 1))) {
            this.ac1OnButton.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_on));
            this.ac1TimeOn.setClickable(true);
            String str13 = "AM";
            int parseInt5 = Integer.parseInt(this.ACTimer[0].substring(1), 2) % 24;
            if (parseInt5 <= 12) {
                if (parseInt5 < 10) {
                    str11 = "0" + String.valueOf(parseInt5) + ":";
                } else {
                    str11 = String.valueOf(parseInt5) + ":";
                }
                if (parseInt5 == 0) {
                    str11 = "12:";
                }
                if (parseInt5 == 12) {
                    str13 = "PM";
                }
            } else {
                if (Integer.parseInt(String.valueOf(parseInt5 - 12)) < 10) {
                    str10 = "0" + String.valueOf(i4) + ":";
                } else {
                    str10 = String.valueOf(i4) + ":";
                }
                str11 = str10;
                str13 = "PM";
            }
            if (Integer.parseInt(this.ACTimer[1], 2) < 10) {
                str12 = str11 + "0" + String.valueOf(parseInt4);
            } else {
                str12 = str11 + String.valueOf(parseInt4);
            }
            this.ac1TimeOn.setText(str12 + str13);
        } else {
            this.ac1OnButton.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_off));
            this.ac1TimeOn.setClickable(false);
            this.ac1TimeOn.setText("  None  ");
        }
        if ("0".equals(this.ACTimer[2].substring(0, 1))) {
            this.ac1OffButton.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_on));
            this.ac1TimeOff.setClickable(true);
            String str14 = "AM";
            int parseInt6 = Integer.parseInt(this.ACTimer[2].substring(1), 2) % 24;
            if (parseInt6 <= 12) {
                if (parseInt6 < 10) {
                    str8 = "0" + String.valueOf(parseInt6) + ":";
                } else {
                    str8 = String.valueOf(parseInt6) + ":";
                }
                if (parseInt6 == 0) {
                    str8 = "12:";
                }
                if (parseInt6 == 12) {
                    str14 = "PM";
                }
            } else {
                if (Integer.parseInt(String.valueOf(parseInt6 - 12)) < 10) {
                    str7 = "0" + String.valueOf(i3) + ":";
                } else {
                    str7 = String.valueOf(i3) + ":";
                }
                str8 = str7;
                str14 = "PM";
            }
            if (Integer.parseInt(this.ACTimer[3], 2) < 10) {
                str9 = str8 + "0" + String.valueOf(parseInt3);
            } else {
                str9 = str8 + String.valueOf(parseInt3);
            }
            this.ac1TimeOff.setText(str9 + str14);
        } else {
            this.ac1OffButton.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_off));
            this.ac1TimeOff.setClickable(false);
            this.ac1TimeOff.setText("  None  ");
        }
        if ("0".equals(this.ACTimer[4].substring(0, 1))) {
            this.ac2OnButton.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_on));
            this.ac2TimeOn.setClickable(true);
            String str15 = "AM";
            int parseInt7 = Integer.parseInt(this.ACTimer[4].substring(1), 2) % 24;
            if (parseInt7 <= 12) {
                if (parseInt7 < 10) {
                    str5 = "0" + String.valueOf(parseInt7) + ":";
                } else {
                    str5 = String.valueOf(parseInt7) + ":";
                }
                if (parseInt7 == 0) {
                    str5 = "12:";
                }
                if (parseInt7 == 12) {
                    str15 = "PM";
                }
            } else {
                if (Integer.parseInt(String.valueOf(parseInt7 - 12)) < 10) {
                    str4 = "0" + String.valueOf(i2) + ":";
                } else {
                    str4 = String.valueOf(i2) + ":";
                }
                str5 = str4;
                str15 = "PM";
            }
            if (Integer.parseInt(this.ACTimer[5], 2) < 10) {
                str6 = str5 + "0" + String.valueOf(parseInt2);
            } else {
                str6 = str5 + String.valueOf(parseInt2);
            }
            this.ac2TimeOn.setText(str6 + str15);
        } else {
            this.ac2OnButton.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_off));
            this.ac2TimeOn.setClickable(false);
            this.ac2TimeOn.setText("  None  ");
        }
        if ("0".equals(this.ACTimer[6].substring(0, 1))) {
            this.ac2OffButton.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_on));
            this.ac2TimeOff.setClickable(true);
            String str16 = "AM";
            int parseInt8 = Integer.parseInt(this.ACTimer[6].substring(1), 2) % 24;
            if (parseInt8 <= 12) {
                if (parseInt8 < 10) {
                    str2 = "0" + String.valueOf(parseInt8) + ":";
                } else {
                    str2 = String.valueOf(parseInt8) + ":";
                }
                if (parseInt8 == 0) {
                    str2 = "12:";
                }
                if (parseInt8 == 12) {
                    str16 = "PM";
                }
            } else {
                if (Integer.parseInt(String.valueOf(parseInt8 - 12)) < 10) {
                    str = "0" + String.valueOf(i) + ":";
                } else {
                    str = String.valueOf(i) + ":";
                }
                str2 = str;
                str16 = "PM";
            }
            if (Integer.parseInt(this.ACTimer[7], 2) < 10) {
                str3 = str2 + "0" + String.valueOf(parseInt);
            } else {
                str3 = str2 + String.valueOf(parseInt);
            }
            this.ac2TimeOff.setText(str3 + str16);
            return;
        }
        this.ac2OffButton.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_off));
        this.ac2TimeOff.setClickable(false);
        this.ac2TimeOff.setText("  None  ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendToAC(byte[] bArr) {
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
