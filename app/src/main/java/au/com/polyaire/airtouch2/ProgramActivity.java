package au.com.polyaire.airtouch2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import au.com.polyaire.airtouch2.data.ACInfo;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityBase;
import java.io.PrintStream;

/* loaded from: classes.dex */
public class ProgramActivity extends ActivityBase {
    private TextView AC2Btn;
    private Button AC2BtnStatus;
    private TextView AC2BtnStatust;
    private TextView ACBtn;
    private Button ACBtnStatus;
    private TextView ACBtnStatust;
    String[] ACTimer;
    private TextView ACTimerProg;
    byte[] data;
    private TextView imageBedroom10Prog;
    private Button imageBedroom10ProgNo;
    private TextView imageBedroom10ProgNot;
    private TextView imageBedroom11Prog;
    private Button imageBedroom11ProgNo;
    private TextView imageBedroom11ProgNot;
    private TextView imageBedroom12Prog;
    private Button imageBedroom12ProgNo;
    private TextView imageBedroom12ProgNot;
    private TextView imageBedroom1Prog;
    private Button imageBedroom1ProgNo;
    private TextView imageBedroom1ProgNot;
    private TextView imageBedroom2Prog;
    private Button imageBedroom2ProgNo;
    private TextView imageBedroom2ProgNot;
    private TextView imageBedroom3Prog;
    private Button imageBedroom3ProgNo;
    private TextView imageBedroom3ProgNot;
    private TextView imageBedroom4Prog;
    private Button imageBedroom4ProgNo;
    private TextView imageBedroom4ProgNot;
    private TextView imageBedroom5Prog;
    private Button imageBedroom5ProgNo;
    private TextView imageBedroom5ProgNot;
    private TextView imageBedroom6Prog;
    private Button imageBedroom6ProgNo;
    private TextView imageBedroom6ProgNot;
    private TextView imageBedroom7Prog;
    private Button imageBedroom7ProgNo;
    private TextView imageBedroom7ProgNot;
    private TextView imageBedroom8Prog;
    private Button imageBedroom8ProgNo;
    private TextView imageBedroom8ProgNot;
    private TextView imageBedroom9Prog;
    private Button imageBedroom9ProgNo;
    private TextView imageBedroom9ProgNot;
    private TextView imageDiningProg;
    private Button imageDiningProgNo;
    private TextView imageDiningProgNot;
    private TextView imageLivingProg;
    private Button imageLivingProgNo;
    private TextView imageLivingProgNot;
    private TextView imageMasterProg;
    private Button imageMasterProgNo;
    private TextView imageMasterProgNot;
    private TextView imageTheatreProg;
    private Button imageTheatreProgNo;
    private TextView imageTheatreProgNot;
    int zoneTurbo;
    String[] zoneData = new String[16];
    int[] startZones = new int[16];
    String ACStatus = "";
    String AC2Status = "";
    byte[] sendMessage = new byte[13];
    byte[] sumByte = new byte[this.sendMessage.length];
    String[] programs = null;
    String isSysturbo = null;

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected int getMainViewID() {
        return C0377R.id.proListMain;
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onUpdateUI() {
        String[] allData = ExchData.getAllData();
        String[] groupData = ExchData.getGroupData();
        this.startZones = new int[16];
        for (int i = 0; i < 16; i++) {
            this.startZones[i] = Integer.parseInt(groupData[i].substring(0, 4), 2);
        }
        String[] groupName = ExchData.getGroupName();
        char[] cArr = new char[128];
        for (int i2 = 0; i2 <= 127; i2++) {
            cArr[i2] = (char) Integer.parseInt(groupName[i2], 2);
        }
        String[] strArr = new String[17];
        for (int i3 = 0; i3 <= 15; i3++) {
            int i4 = i3 * 8;
            strArr[i3] = String.valueOf(cArr).substring(i4, i4 + 8);
        }
        int parseInt = Integer.parseInt(ExchData.getSysPara()[0], 2);
        this.zoneData = ExchData.getZoneData();
        this.ACStatus = String.valueOf(ExchData.getAC1().getProgramNumber());
        this.AC2Status = String.valueOf(ExchData.getAC2().getProgramNumber());
        this.programs = ExchData.getProgramTime();
        this.ACTimer = ExchData.getACtimer();
        this.zoneTurbo = Integer.parseInt(allData[284].substring(4), 2);
        this.isSysturbo = allData[285].substring(2, 3);
        this.ACBtn.setText(ExchData.getAC1().getAcName());
        this.AC2Btn.setText(ExchData.getAC2().getAcName());
        this.imageMasterProg.setText(strArr[0]);
        this.imageDiningProg.setText(strArr[1]);
        this.imageLivingProg.setText(strArr[2]);
        this.imageTheatreProg.setText(strArr[3]);
        this.imageBedroom1Prog.setText(strArr[4]);
        this.imageBedroom2Prog.setText(strArr[5]);
        this.imageBedroom3Prog.setText(strArr[6]);
        this.imageBedroom4Prog.setText(strArr[7]);
        this.imageBedroom5Prog.setText(strArr[8]);
        this.imageBedroom6Prog.setText(strArr[9]);
        this.imageBedroom7Prog.setText(strArr[10]);
        this.imageBedroom8Prog.setText(strArr[11]);
        this.imageBedroom9Prog.setText(strArr[12]);
        this.imageBedroom10Prog.setText(strArr[13]);
        this.imageBedroom11Prog.setText(strArr[14]);
        this.imageBedroom12Prog.setText(strArr[15]);
        TableRow tableRow = (TableRow) findViewById(C0377R.id.table1);
        TableRow tableRow2 = (TableRow) findViewById(C0377R.id.tableRow1);
        TableRow tableRow3 = (TableRow) findViewById(C0377R.id.ac2Program);
        if (!ExchData.isDualACMode()) {
            tableRow3.setVisibility(8);
            if (ExchData.getAC1().getControlMode() == ACInfo.E_ACControlMode.NotAvailable) {
                tableRow2.setVisibility(8);
                tableRow.setVisibility(8);
            }
        } else if (ExchData.getAC1().getControlMode() == ACInfo.E_ACControlMode.NotAvailable && ExchData.getAC2().getControlMode() == ACInfo.E_ACControlMode.NotAvailable) {
            tableRow2.setVisibility(8);
            tableRow3.setVisibility(8);
            tableRow.setVisibility(8);
        }
        for (int i5 = 0; i5 <= 15; i5++) {
            Resources resources = getResources();
            findViewById(resources.getIdentifier("tableRow" + String.valueOf(i5 + 2), "id", getPackageName())).setVisibility(8);
        }
        for (int i6 = 0; i6 < parseInt; i6++) {
            Resources resources2 = getResources();
            findViewById(resources2.getIdentifier("tableRow" + String.valueOf(i6 + 2), "id", getPackageName())).setVisibility(0);
        }
        setProgNo(this.ACBtnStatus, this.ACStatus, this.ACBtnStatust);
        setProgNo(this.AC2BtnStatus, this.AC2Status, this.AC2BtnStatust);
        setProgNo(this.imageMasterProgNo, this.zoneData[this.startZones[0]], this.imageMasterProgNot);
        setProgNo(this.imageDiningProgNo, this.zoneData[this.startZones[1]], this.imageDiningProgNot);
        setProgNo(this.imageLivingProgNo, this.zoneData[this.startZones[2]], this.imageLivingProgNot);
        setProgNo(this.imageTheatreProgNo, this.zoneData[this.startZones[3]], this.imageTheatreProgNot);
        setProgNo(this.imageBedroom1ProgNo, this.zoneData[this.startZones[4]], this.imageBedroom1ProgNot);
        setProgNo(this.imageBedroom2ProgNo, this.zoneData[this.startZones[5]], this.imageBedroom2ProgNot);
        setProgNo(this.imageBedroom3ProgNo, this.zoneData[this.startZones[6]], this.imageBedroom3ProgNot);
        setProgNo(this.imageBedroom4ProgNo, this.zoneData[this.startZones[7]], this.imageBedroom4ProgNot);
        setProgNo(this.imageBedroom5ProgNo, this.zoneData[this.startZones[8]], this.imageBedroom5ProgNot);
        setProgNo(this.imageBedroom6ProgNo, this.zoneData[this.startZones[9]], this.imageBedroom6ProgNot);
        setProgNo(this.imageBedroom7ProgNo, this.zoneData[this.startZones[10]], this.imageBedroom7ProgNot);
        setProgNo(this.imageBedroom8ProgNo, this.zoneData[this.startZones[11]], this.imageBedroom8ProgNot);
        setProgNo(this.imageBedroom9ProgNo, this.zoneData[this.startZones[12]], this.imageBedroom9ProgNot);
        setProgNo(this.imageBedroom10ProgNo, this.zoneData[this.startZones[13]], this.imageBedroom10ProgNot);
        setProgNo(this.imageBedroom11ProgNo, this.zoneData[this.startZones[14]], this.imageBedroom11ProgNot);
        setProgNo(this.imageBedroom12ProgNo, this.zoneData[this.startZones[15]], this.imageBedroom12ProgNot);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0377R.layout.prog);
        getWindow().setFeatureInt(7, C0377R.layout.programtitle);
        this.ACTimerProg = (TextView) findViewById(C0377R.id.actimerprog);
        this.ACTimerProg.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(ProgramActivity.this, ACTimerActivity.class);
                intent.putExtra("ACTimer", ProgramActivity.this.ACTimer);
                ProgramActivity.this.startActivity(intent);
            }
        });
        this.ACBtn = (TextView) findViewById(C0377R.id.acbtn);
        this.ACBtnStatus = (Button) findViewById(C0377R.id.acStatus);
        this.ACBtnStatus.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProgramActivity.this.showDialog(1);
            }
        });
        this.ACBtnStatust = (TextView) findViewById(C0377R.id.acStatust);
        this.AC2Btn = (TextView) findViewById(C0377R.id.ac2btn);
        this.AC2BtnStatus = (Button) findViewById(C0377R.id.ac2Status);
        this.AC2BtnStatus.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProgramActivity.this.showDialog(18);
            }
        });
        this.AC2BtnStatust = (TextView) findViewById(C0377R.id.ac2Statust);
        this.imageMasterProg = (TextView) findViewById(C0377R.id.imageMasterProg);
        this.imageMasterProgNo = (Button) findViewById(C0377R.id.imageMasterProgNo);
        this.imageMasterProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 1 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(2);
                }
            }
        });
        this.imageMasterProgNot = (TextView) findViewById(C0377R.id.imageMasterProgNot);
        this.imageDiningProg = (TextView) findViewById(C0377R.id.imageDiningProg);
        this.imageDiningProgNo = (Button) findViewById(C0377R.id.imageDiningProgNo);
        this.imageDiningProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 2 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(3);
                }
            }
        });
        this.imageDiningProgNot = (TextView) findViewById(C0377R.id.imageDiningProgNot);
        this.imageLivingProg = (TextView) findViewById(C0377R.id.imageLivingProg);
        this.imageLivingProgNo = (Button) findViewById(C0377R.id.imageLivingProgNo);
        this.imageLivingProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 3 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(4);
                }
            }
        });
        this.imageLivingProgNot = (TextView) findViewById(C0377R.id.imageLivingProgNot);
        this.imageTheatreProg = (TextView) findViewById(C0377R.id.imageTheatreProg);
        this.imageTheatreProgNo = (Button) findViewById(C0377R.id.imageTheatreProgNo);
        this.imageTheatreProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 4 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(5);
                }
            }
        });
        this.imageTheatreProgNot = (TextView) findViewById(C0377R.id.imageTheatreProgNot);
        this.imageBedroom1Prog = (TextView) findViewById(C0377R.id.imageBedroom1Prog);
        this.imageBedroom1ProgNo = (Button) findViewById(C0377R.id.imageBedroom1ProgNo);
        this.imageBedroom1ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 5 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(6);
                }
            }
        });
        this.imageBedroom1ProgNot = (TextView) findViewById(C0377R.id.imageBedroom1ProgNot);
        this.imageBedroom2Prog = (TextView) findViewById(C0377R.id.imageBedroom2Prog);
        this.imageBedroom2ProgNo = (Button) findViewById(C0377R.id.imageBedroom2ProgNo);
        this.imageBedroom2ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 6 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(7);
                }
            }
        });
        this.imageBedroom2ProgNot = (TextView) findViewById(C0377R.id.imageBedroom2ProgNot);
        this.imageBedroom3Prog = (TextView) findViewById(C0377R.id.imageBedroom3Prog);
        this.imageBedroom3ProgNo = (Button) findViewById(C0377R.id.imageBedroom3ProgNo);
        this.imageBedroom3ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 7 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(8);
                }
            }
        });
        this.imageBedroom3ProgNot = (TextView) findViewById(C0377R.id.imageBedroom3ProgNot);
        this.imageBedroom4Prog = (TextView) findViewById(C0377R.id.imageBedroom4Prog);
        this.imageBedroom4ProgNo = (Button) findViewById(C0377R.id.imageBedroom4ProgNo);
        this.imageBedroom4ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 8 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(9);
                }
            }
        });
        this.imageBedroom4ProgNot = (TextView) findViewById(C0377R.id.imageBedroom4ProgNot);
        this.imageBedroom5Prog = (TextView) findViewById(C0377R.id.imageBedroom5Prog);
        this.imageBedroom5ProgNo = (Button) findViewById(C0377R.id.imageBedroom5ProgNo);
        this.imageBedroom5ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 9 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(10);
                }
            }
        });
        this.imageBedroom5ProgNot = (TextView) findViewById(C0377R.id.imageBedroom5ProgNot);
        this.imageBedroom6Prog = (TextView) findViewById(C0377R.id.imageBedroom6Prog);
        this.imageBedroom6ProgNo = (Button) findViewById(C0377R.id.imageBedroom6ProgNo);
        this.imageBedroom6ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 10 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(11);
                }
            }
        });
        this.imageBedroom6ProgNot = (TextView) findViewById(C0377R.id.imageBedroom6ProgNot);
        this.imageBedroom7Prog = (TextView) findViewById(C0377R.id.imageBedroom7Prog);
        this.imageBedroom7ProgNo = (Button) findViewById(C0377R.id.imageBedroom7ProgNo);
        this.imageBedroom7ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 11 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(12);
                }
            }
        });
        this.imageBedroom7ProgNot = (TextView) findViewById(C0377R.id.imageBedroom7ProgNot);
        this.imageBedroom8Prog = (TextView) findViewById(C0377R.id.imageBedroom8Prog);
        this.imageBedroom8ProgNo = (Button) findViewById(C0377R.id.imageBedroom8ProgNo);
        this.imageBedroom8ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 12 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(13);
                }
            }
        });
        this.imageBedroom8ProgNot = (TextView) findViewById(C0377R.id.imageBedroom8ProgNot);
        this.imageBedroom9Prog = (TextView) findViewById(C0377R.id.imageBedroom9Prog);
        this.imageBedroom9ProgNo = (Button) findViewById(C0377R.id.imageBedroom9ProgNo);
        this.imageBedroom9ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 13 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(14);
                }
            }
        });
        this.imageBedroom9ProgNot = (TextView) findViewById(C0377R.id.imageBedroom9ProgNot);
        this.imageBedroom10Prog = (TextView) findViewById(C0377R.id.imageBedroom10Prog);
        this.imageBedroom10ProgNo = (Button) findViewById(C0377R.id.imageBedroom10ProgNo);
        this.imageBedroom10ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 14 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(15);
                }
            }
        });
        this.imageBedroom10ProgNot = (TextView) findViewById(C0377R.id.imageBedroom10ProgNot);
        this.imageBedroom11Prog = (TextView) findViewById(C0377R.id.imageBedroom11Prog);
        this.imageBedroom11ProgNo = (Button) findViewById(C0377R.id.imageBedroom11ProgNo);
        this.imageBedroom11ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 15 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(16);
                }
            }
        });
        this.imageBedroom11ProgNot = (TextView) findViewById(C0377R.id.imageBedroom11ProgNot);
        this.imageBedroom12Prog = (TextView) findViewById(C0377R.id.imageBedroom12Prog);
        this.imageBedroom12ProgNo = (Button) findViewById(C0377R.id.imageBedroom12ProgNo);
        this.imageBedroom12ProgNo.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("1".equals(ProgramActivity.this.isSysturbo) && 16 == ProgramActivity.this.zoneTurbo) {
                    ProgramActivity.this.showDialog(0);
                } else {
                    ProgramActivity.this.showDialog(17);
                }
            }
        });
        this.imageBedroom12ProgNot = (TextView) findViewById(C0377R.id.imageBedroom12ProgNot);
    }

    private void setProgNo(Button button, String str, TextView textView) {
        int parseInt;
        if (str.length() == 1) {
            parseInt = Integer.valueOf(str).intValue();
        } else if (str.length() == 4) {
            parseInt = Integer.parseInt(str, 2);
        } else {
            parseInt = Integer.parseInt(str.substring(2, 5), 2);
        }
        if (parseInt == 4) {
            button.setText("Program4");
        } else if (parseInt == 1) {
            button.setText("Program1");
        } else if (parseInt == 2) {
            button.setText("Program2");
        } else if (parseInt == 3) {
            button.setText("Program3");
        } else {
            button.setText("NO");
            textView.setVisibility(4);
        }
        final String charSequence = button.getText().toString();
        if (charSequence.equals("NO")) {
            return;
        }
        textView.setVisibility(0);
        textView.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(ProgramActivity.this, ProgMenu.class);
                intent.putExtra("programno", charSequence);
                intent.putExtra("programs", ProgramActivity.this.programs);
                ProgramActivity.this.startActivity(intent);
            }
        });
    }

    public String toFullBinaryString(int i) {
        char[] cArr = new char[32];
        for (int i2 = 0; i2 < 32; i2++) {
            cArr[31 - i2] = (char) (((i >> i2) & 1) + 48);
        }
        return new String(cArr);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 0:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Turbo active,cannot set up program.").setPositiveButton("Cancel", (DialogInterface.OnClickListener) null);
                return builder.create();
            case 1:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle(this.ACBtn.getText());
                builder2.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.21
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 16;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder2.create();
            case 2:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setTitle(this.imageMasterProg.getText());
                builder3.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.23
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 0;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder3.create();
            case 3:
                AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
                builder4.setTitle(this.imageDiningProg.getText());
                builder4.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.24
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 1;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder4.create();
            case 4:
                AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
                builder5.setTitle(this.imageLivingProg.getText());
                builder5.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.25
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 2;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder5.create();
            case 5:
                AlertDialog.Builder builder6 = new AlertDialog.Builder(this);
                builder6.setTitle(this.imageTheatreProg.getText());
                builder6.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.26
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 3;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder6.create();
            case 6:
                AlertDialog.Builder builder7 = new AlertDialog.Builder(this);
                builder7.setTitle(this.imageBedroom1Prog.getText());
                builder7.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.27
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 4;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder7.create();
            case 7:
                AlertDialog.Builder builder8 = new AlertDialog.Builder(this);
                builder8.setTitle(this.imageBedroom2Prog.getText());
                builder8.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.28
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 5;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder8.create();
            case 8:
                AlertDialog.Builder builder9 = new AlertDialog.Builder(this);
                builder9.setTitle(this.imageBedroom3Prog.getText());
                builder9.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.29
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 6;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder9.create();
            case 9:
                AlertDialog.Builder builder10 = new AlertDialog.Builder(this);
                builder10.setTitle(this.imageBedroom4Prog.getText());
                builder10.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.30
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 7;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder10.create();
            case 10:
                AlertDialog.Builder builder11 = new AlertDialog.Builder(this);
                builder11.setTitle(this.imageBedroom5Prog.getText());
                builder11.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.31
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 8;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder11.create();
            case 11:
                AlertDialog.Builder builder12 = new AlertDialog.Builder(this);
                builder12.setTitle(this.imageBedroom6Prog.getText());
                builder12.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.32
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 9;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder12.create();
            case 12:
                AlertDialog.Builder builder13 = new AlertDialog.Builder(this);
                builder13.setTitle(this.imageBedroom7Prog.getText());
                builder13.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.33
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 10;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder13.create();
            case 13:
                AlertDialog.Builder builder14 = new AlertDialog.Builder(this);
                builder14.setTitle(this.imageBedroom8Prog.getText());
                builder14.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.34
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 11;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder14.create();
            case 14:
                AlertDialog.Builder builder15 = new AlertDialog.Builder(this);
                builder15.setTitle(this.imageBedroom9Prog.getText());
                builder15.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.35
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 12;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder15.create();
            case 15:
                AlertDialog.Builder builder16 = new AlertDialog.Builder(this);
                builder16.setTitle(this.imageBedroom10Prog.getText());
                builder16.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.36
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 13;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder16.create();
            case 16:
                AlertDialog.Builder builder17 = new AlertDialog.Builder(this);
                builder17.setTitle(this.imageBedroom11Prog.getText());
                builder17.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.37
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 14;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder17.create();
            case 17:
                AlertDialog.Builder builder18 = new AlertDialog.Builder(this);
                builder18.setTitle(this.imageBedroom12Prog.getText());
                builder18.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.38
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 15;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder18.create();
            case 18:
                AlertDialog.Builder builder19 = new AlertDialog.Builder(this);
                builder19.setTitle(this.AC2Btn.getText());
                builder19.setItems(C0377R.array.progarray, new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.ProgramActivity.22
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String str = ProgramActivity.this.getResources().getStringArray(C0377R.array.progarray)[i2];
                        ProgramActivity.this.sendMessage[3] = 17;
                        ProgramActivity.this.sendMessage[4] = ProgramActivity.this.progno(str);
                        ProgramActivity.this.sendmessage();
                    }
                });
                return builder19.create();
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte progno(String str) {
        if ("NO".equals(str)) {
            return (byte) 0;
        }
        if ("1".equals(str.substring(7, 8))) {
            return (byte) 1;
        }
        if ("2".equals(str.substring(7, 8))) {
            return (byte) 2;
        }
        if ("3".equals(str.substring(7, 8))) {
            return (byte) 3;
        }
        return "4".equals(str.substring(7, 8)) ? (byte) 4 : (byte) 0;
    }

    private byte checkSum() {
        int i = 0;
        for (int i2 = 0; i2 <= this.sumByte.length - 1; i2++) {
            this.sumByte[i2] = this.sendMessage[i2];
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

    /* JADX INFO: Access modifiers changed from: private */
    public void sendmessage() {
        this.sendMessage[0] = 85;
        this.sendMessage[1] = -125;
        this.sendMessage[2] = 12;
        byte[] bArr = new byte[this.sendMessage.length];
        this.sendMessage[12] = checkSum();
        for (int i = 0; i <= 12; i++) {
            PrintStream printStream = System.out;
            printStream.println("sendMessage from progractivity" + i + "----->" + Integer.toHexString(this.sendMessage[i]));
        }
        ExchData.sendMessage(this.sendMessage);
        Log.d("sendMessage", new String(bArr));
    }
}
