package au.com.polyaire.airtouch2;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityProgSettingBase;
import java.util.Locale;

/* loaded from: classes.dex */
public class ProgramInfoSun extends ActivityProgSettingBase {
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
    View.OnClickListener onClickListerner = new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case C0377R.id.Button1 /* 2131230722 */:
                    ProgramInfoSun.this.sendMessage.setProgramMessage("SUN", 1, ProgramInfoSun.this.progNO, -1, 0);
                    ProgramInfoSun.this.SendMessage(ProgramInfoSun.this.sendMessage.GetInBytes());
                    return;
                case C0377R.id.Button2 /* 2131230723 */:
                    ProgramInfoSun.this.sendMessage.setProgramMessage("SUN", 2, ProgramInfoSun.this.progNO, -1, 0);
                    ProgramInfoSun.this.SendMessage(ProgramInfoSun.this.sendMessage.GetInBytes());
                    return;
                case C0377R.id.Button3 /* 2131230724 */:
                    ProgramInfoSun.this.sendMessage.setProgramMessage("SUN", 3, ProgramInfoSun.this.progNO, -1, 0);
                    ProgramInfoSun.this.SendMessage(ProgramInfoSun.this.sendMessage.GetInBytes());
                    return;
                case C0377R.id.Button4 /* 2131230725 */:
                    ProgramInfoSun.this.sendMessage.setProgramMessage("SUN", 4, ProgramInfoSun.this.progNO, -1, 0);
                    ProgramInfoSun.this.SendMessage(ProgramInfoSun.this.sendMessage.GetInBytes());
                    return;
                default:
                    return;
            }
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.6
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ProgramInfoSun.this.mHour = i;
            ProgramInfoSun.this.mMinute = i2;
            ProgramInfoSun.this.sendMessage.setProgramMessage("SUN", 1, ProgramInfoSun.this.progNO, ProgramInfoSun.this.mHour, ProgramInfoSun.this.mMinute);
            ProgramInfoSun.this.SendMessage(ProgramInfoSun.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.7
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ProgramInfoSun.this.mHour = i;
            ProgramInfoSun.this.mMinute = i2;
            ProgramInfoSun.this.sendMessage.setProgramMessage("SUN", 2, ProgramInfoSun.this.progNO, ProgramInfoSun.this.mHour, ProgramInfoSun.this.mMinute);
            ProgramInfoSun.this.SendMessage(ProgramInfoSun.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener3 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.8
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ProgramInfoSun.this.mHour = i;
            ProgramInfoSun.this.mMinute = i2;
            ProgramInfoSun.this.sendMessage.setProgramMessage("SUN", 3, ProgramInfoSun.this.progNO, ProgramInfoSun.this.mHour, ProgramInfoSun.this.mMinute);
            ProgramInfoSun.this.SendMessage(ProgramInfoSun.this.sendMessage.GetInBytes());
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener4 = new TimePickerDialog.OnTimeSetListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.9
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            ProgramInfoSun.this.mHour = i;
            ProgramInfoSun.this.mMinute = i2;
            ProgramInfoSun.this.sendMessage.setProgramMessage("SUN", 4, ProgramInfoSun.this.progNO, ProgramInfoSun.this.mHour, ProgramInfoSun.this.mMinute);
            ProgramInfoSun.this.SendMessage(ProgramInfoSun.this.sendMessage.GetInBytes());
        }
    };
    Handler mHandler = new Handler() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.10
    };
    Runnable updataThread = new Runnable() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.11
        @Override // java.lang.Runnable
        public void run() {
            ProgramInfoSun.this.programs = ExchData.getProgramTime();
            ProgramInfoSun.this.mHandler.post(new Runnable() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.11.1
                @Override // java.lang.Runnable
                public void run() {
                    if ((ProgramInfoSun.this.progNO != null) && (ProgramInfoSun.this.programs != null)) {
                        ProgramInfoSun.this.SetTime(ProgramInfoSun.this.progNO);
                    }
                }
            });
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.programsun);
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);
        this.intent = getIntent();
        this.progNO = this.intent.getStringArrayExtra("programs")[96];
        this.t1 = (TextView) findViewById(C0377R.id.suntime1onstatus);
        this.t2 = (TextView) findViewById(C0377R.id.suntime1offstatus);
        this.t3 = (TextView) findViewById(C0377R.id.suntime2onstatus);
        this.t4 = (TextView) findViewById(C0377R.id.suntime2offstatus);
        this.tb1 = (Button) findViewById(C0377R.id.Button1);
        this.tb2 = (Button) findViewById(C0377R.id.Button2);
        this.tb3 = (Button) findViewById(C0377R.id.Button3);
        this.tb4 = (Button) findViewById(C0377R.id.Button4);
        this.programNO = (TextView) findViewById(C0377R.id.programno);
        this.t1.setOnClickListener(new TimeListener(C0377R.id.suntime1onstatus));
        this.t2.setOnClickListener(new TimeListener(C0377R.id.suntime1offstatus));
        this.t3.setOnClickListener(new TimeListener(C0377R.id.suntime2onstatus));
        this.t4.setOnClickListener(new TimeListener(C0377R.id.suntime2offstatus));
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
                case C0377R.id.suntime1offstatus /* 2131231168 */:
                    ProgramInfoSun.this.mHour = Integer.parseInt(ProgramInfoSun.this.t2.getText().toString().substring(0, 2));
                    if ("P".equals(ProgramInfoSun.this.t2.getText().toString().substring(5, 6))) {
                        ProgramInfoSun.this.mHour += 12;
                    }
                    ProgramInfoSun.this.mMinute = Integer.parseInt(ProgramInfoSun.this.t2.getText().toString().substring(3, 5));
                    ProgramInfoSun.this.showDialog(2);
                    return;
                case C0377R.id.suntime1onstatus /* 2131231169 */:
                    ProgramInfoSun.this.mHour = Integer.parseInt(ProgramInfoSun.this.t1.getText().toString().substring(0, 2));
                    if ("P".equals(ProgramInfoSun.this.t1.getText().toString().substring(5, 6))) {
                        ProgramInfoSun.this.mHour += 12;
                    }
                    ProgramInfoSun.this.mMinute = Integer.parseInt(ProgramInfoSun.this.t1.getText().toString().substring(3, 5));
                    ProgramInfoSun.this.showDialog(1);
                    return;
                case C0377R.id.suntime2offstatus /* 2131231170 */:
                    ProgramInfoSun.this.mHour = Integer.parseInt(ProgramInfoSun.this.t4.getText().toString().substring(0, 2));
                    if ("P".equals(ProgramInfoSun.this.t4.getText().toString().substring(5, 6))) {
                        ProgramInfoSun.this.mHour += 12;
                    }
                    ProgramInfoSun.this.mMinute = Integer.parseInt(ProgramInfoSun.this.t4.getText().toString().substring(3, 5));
                    ProgramInfoSun.this.showDialog(4);
                    return;
                case C0377R.id.suntime2onstatus /* 2131231171 */:
                    ProgramInfoSun.this.mHour = Integer.parseInt(ProgramInfoSun.this.t3.getText().toString().substring(0, 2));
                    if ("P".equals(ProgramInfoSun.this.t3.getText().toString().substring(5, 6))) {
                        ProgramInfoSun.this.mHour += 12;
                    }
                    ProgramInfoSun.this.mMinute = Integer.parseInt(ProgramInfoSun.this.t3.getText().toString().substring(3, 5));
                    ProgramInfoSun.this.showDialog(3);
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
                timePickerDialog.setTitle("Sunday Time1 ON");
                timePickerDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ProgramInfoSun.this.removeDialog(1);
                    }
                });
                return timePickerDialog;
            case 2:
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(this, this.mTimeSetListener2, this.mHour, this.mMinute, false);
                timePickerDialog2.setTitle("Sunday Time1 OFF");
                timePickerDialog2.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ProgramInfoSun.this.removeDialog(2);
                    }
                });
                return timePickerDialog2;
            case 3:
                TimePickerDialog timePickerDialog3 = new TimePickerDialog(this, this.mTimeSetListener3, this.mHour, this.mMinute, false);
                timePickerDialog3.setTitle("Sunday Time2 ON");
                timePickerDialog3.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ProgramInfoSun.this.removeDialog(3);
                    }
                });
                return timePickerDialog3;
            case 4:
                TimePickerDialog timePickerDialog4 = new TimePickerDialog(this, this.mTimeSetListener4, this.mHour, this.mMinute, false);
                timePickerDialog4.setTitle("Sunday Time2 OFF");
                timePickerDialog4.setButton(-2, "Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramInfoSun.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ProgramInfoSun.this.removeDialog(4);
                    }
                });
                return timePickerDialog4;
            default:
                return null;
        }
    }

    public void SetTime(String str) {
        int i;
        int i2;
        String str2;
        String str3;
        int parseInt;
        String str4;
        int i3;
        String str5;
        String str6;
        int parseInt2;
        String str7;
        int i4;
        String str8;
        String str9;
        int parseInt3;
        String str10;
        int i5;
        String str11;
        String str12;
        int parseInt4;
        String str13;
        if ("1".equals(str.substring(7, 8))) {
            i = 16;
        } else if ("2".equals(str.substring(7, 8))) {
            i = 40;
        } else if ("3".equals(str.substring(7, 8))) {
            i = 64;
        } else {
            i = "4".equals(str.substring(7, 8)) ? 88 : 0;
        }
        if ("1".equals(this.programs[i].substring(0, 1))) {
            this.tb1.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_off));
            this.t1.setClickable(false);
            this.t1.setText("  None  ");
            this.t1.setTextColor(getResources().getColor(C0377R.color.progNone));
        } else {
            this.tb1.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_on));
            this.t1.setClickable(true);
            this.t1.setTextColor(getResources().getColor(C0377R.color.progTime));
            String str14 = "AM";
            int parseInt5 = Integer.parseInt(this.programs[i], 2);
            if (parseInt5 <= 12) {
                if (parseInt5 < 10) {
                    str3 = "0" + String.valueOf(parseInt5) + ":";
                } else {
                    str3 = String.valueOf(parseInt5) + ":";
                }
                if (parseInt5 == 0) {
                    str3 = "12:";
                }
                if (parseInt5 == 12) {
                    str14 = "PM";
                }
            } else {
                if (Integer.parseInt(String.valueOf(parseInt5 - 12)) < 10) {
                    str2 = "0" + String.valueOf(i2) + ":";
                } else {
                    str2 = String.valueOf(i2) + ":";
                }
                str3 = str2;
                str14 = "PM";
            }
            if (Integer.parseInt(this.programs[i + 1], 2) < 10) {
                str4 = str3 + "0" + String.valueOf(parseInt);
            } else {
                str4 = str3 + String.valueOf(parseInt);
            }
            this.t1.setText(str4 + str14);
        }
        int i6 = i + 2;
        if ("1".equals(this.programs[i6].substring(0, 1))) {
            this.tb2.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_off));
            this.t2.setClickable(false);
            this.t2.setText("  None  ");
            this.t2.setTextColor(getResources().getColor(C0377R.color.progNone));
        } else {
            this.tb2.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_on));
            this.t2.setClickable(true);
            this.t2.setTextColor(getResources().getColor(C0377R.color.progTime));
            String str15 = "AM";
            int parseInt6 = Integer.parseInt(this.programs[i6], 2);
            if (parseInt6 <= 12) {
                if (parseInt6 < 10) {
                    str6 = "0" + String.valueOf(parseInt6) + ":";
                } else {
                    str6 = String.valueOf(parseInt6) + ":";
                }
                if (parseInt6 == 0) {
                    str6 = "12:";
                }
                if (parseInt6 == 12) {
                    str15 = "PM";
                }
            } else {
                if (Integer.parseInt(String.valueOf(parseInt6 - 12)) < 10) {
                    str5 = "0" + String.valueOf(i3) + ":";
                } else {
                    str5 = String.valueOf(i3) + ":";
                }
                str6 = str5;
                str15 = "PM";
            }
            if (Integer.parseInt(this.programs[i + 3], 2) < 10) {
                str7 = str6 + "0" + String.valueOf(parseInt2);
            } else {
                str7 = str6 + String.valueOf(parseInt2);
            }
            this.t2.setText(str7 + str15);
        }
        int i7 = i + 4;
        if ("1".equals(this.programs[i7].substring(0, 1))) {
            this.tb3.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_off));
            this.t3.setClickable(false);
            this.t3.setText("  None  ");
            this.t3.setTextColor(getResources().getColor(C0377R.color.progNone));
        } else {
            this.tb3.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_on));
            this.t3.setClickable(true);
            this.t3.setTextColor(getResources().getColor(C0377R.color.progTime));
            String str16 = "AM";
            int parseInt7 = Integer.parseInt(this.programs[i7], 2);
            if (parseInt7 <= 12) {
                if (parseInt7 < 10) {
                    str9 = "0" + String.valueOf(parseInt7) + ":";
                } else {
                    str9 = String.valueOf(parseInt7) + ":";
                }
                if (parseInt7 == 0) {
                    str9 = "12:";
                }
                if (parseInt7 == 12) {
                    str16 = "PM";
                }
            } else {
                if (Integer.parseInt(String.valueOf(parseInt7 - 12)) < 10) {
                    str8 = "0" + String.valueOf(i4) + ":";
                } else {
                    str8 = String.valueOf(i4) + ":";
                }
                str9 = str8;
                str16 = "PM";
            }
            if (Integer.parseInt(this.programs[i + 5], 2) < 10) {
                str10 = str9 + "0" + String.valueOf(parseInt3);
            } else {
                str10 = str9 + String.valueOf(parseInt3);
            }
            this.t3.setText(str10 + str16);
        }
        int i8 = i + 6;
        if ("1".equals(this.programs[i8].substring(0, 1))) {
            this.tb4.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_off));
            this.t4.setClickable(false);
            this.t4.setText("  None  ");
            this.t4.setTextColor(getResources().getColor(C0377R.color.progNone));
            return;
        }
        this.tb4.setBackgroundDrawable(getResources().getDrawable(C0377R.C0378drawable.btn_radio_on));
        this.t4.setTextColor(getResources().getColor(C0377R.color.progTime));
        this.t4.setClickable(true);
        String str17 = "AM";
        int parseInt8 = Integer.parseInt(this.programs[i8], 2);
        if (parseInt8 <= 12) {
            if (parseInt8 < 10) {
                str12 = "0" + String.valueOf(parseInt8) + ":";
            } else {
                str12 = String.valueOf(parseInt8) + ":";
            }
            if (parseInt8 == 0) {
                str12 = "12:";
            }
            if (parseInt8 == 12) {
                str17 = "PM";
            }
        } else {
            if (Integer.parseInt(String.valueOf(parseInt8 - 12)) < 10) {
                str11 = "0" + String.valueOf(i5) + ":";
            } else {
                str11 = String.valueOf(i5) + ":";
            }
            str12 = str11;
            str17 = "PM";
        }
        if (Integer.parseInt(this.programs[i + 7], 2) < 10) {
            str13 = str12 + "0" + String.valueOf(parseInt4);
        } else {
            str13 = str12 + String.valueOf(parseInt4);
        }
        this.t4.setText(str13 + str17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendMessage(byte[] bArr) {
        ExchData.sendMessage(bArr);
    }
}
