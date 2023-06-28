package au.com.polyaire.airtouch2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityBase;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class NamingActivity extends ActivityBase {
    byte[] data;
    String[] mainButtons;
    private String[] tmp;
    MessageInBytes sendMessage = new MessageInBytes();
    char[] mainButtonsName = new char[128];
    String[] proActivityButtonName = new String[17];

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected int getMainViewID() {
        return C0377R.id.namingMain;
    }

    protected void initContrl(int i, int i2, int i3, int i4, int i5, ArrayAdapter arrayAdapter) {
        hideExtraGroups(i, (TableRow) findViewById(i2));
        Spinner spinner = (Spinner) findViewById(i4);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        spinner.setPrompt("Choose Name");
        spinner.setSelection(0, true);
        spinner.setOnItemSelectedListener(new SpinnerOnSelectedListener(i));
        findViewById(i5).setOnClickListener(new OnNameClickListener(i, (TextView) findViewById(i3)));
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.naming_layout);
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onSubResume() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Lounge");
        arrayList.add("Family");
        arrayList.add("Living");
        arrayList.add("Master");
        arrayList.add("Bedroom");
        arrayList.add("Boy");
        arrayList.add("Girl");
        arrayList.add("Kids");
        arrayList.add("Study");
        arrayList.add("Office");
        arrayList.add("Computer");
        arrayList.add("Library");
        arrayList.add("Dining");
        arrayList.add("Kitchen");
        arrayList.add("Bathroom");
        arrayList.add("Ensuite");
        arrayList.add("Playroom");
        arrayList.add("Games");
        arrayList.add("Rumpus");
        arrayList.add("TV room");
        arrayList.add("Billiard");
        arrayList.add("Dnstairs");
        arrayList.add("Upstairs");
        arrayList.add("Group");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, (int) C0377R.layout.item, (int) C0377R.id.textViewId, arrayList);
        initContrl(1, C0377R.id.tableRow1, C0377R.id.group1, C0377R.id.spinner1, C0377R.id.button1, arrayAdapter);
        initContrl(2, C0377R.id.tableRow2, C0377R.id.group2, C0377R.id.spinner2, C0377R.id.button2, arrayAdapter);
        initContrl(3, C0377R.id.tableRow3, C0377R.id.group3, C0377R.id.spinner3, C0377R.id.button3, arrayAdapter);
        initContrl(4, C0377R.id.tableRow4, C0377R.id.group4, C0377R.id.spinner4, C0377R.id.button4, arrayAdapter);
        initContrl(5, C0377R.id.tableRow5, C0377R.id.group5, C0377R.id.spinner5, C0377R.id.button5, arrayAdapter);
        initContrl(6, C0377R.id.tableRow6, C0377R.id.group6, C0377R.id.spinner6, C0377R.id.button6, arrayAdapter);
        initContrl(7, C0377R.id.tableRow7, C0377R.id.group7, C0377R.id.spinner7, C0377R.id.button7, arrayAdapter);
        initContrl(8, C0377R.id.tableRow8, C0377R.id.group8, C0377R.id.spinner8, C0377R.id.button8, arrayAdapter);
        initContrl(9, C0377R.id.tableRow9, C0377R.id.group9, C0377R.id.spinner9, C0377R.id.button9, arrayAdapter);
        initContrl(10, C0377R.id.tableRow10, C0377R.id.group10, C0377R.id.spinner10, C0377R.id.button10, arrayAdapter);
        initContrl(11, C0377R.id.tableRow11, C0377R.id.group11, C0377R.id.spinner11, C0377R.id.button11, arrayAdapter);
        initContrl(12, C0377R.id.tableRow12, C0377R.id.group12, C0377R.id.spinner12, C0377R.id.button12, arrayAdapter);
        initContrl(13, C0377R.id.tableRow13, C0377R.id.group13, C0377R.id.spinner13, C0377R.id.button13, arrayAdapter);
        initContrl(14, C0377R.id.tableRow14, C0377R.id.group14, C0377R.id.spinner14, C0377R.id.button14, arrayAdapter);
        initContrl(15, C0377R.id.tableRow15, C0377R.id.group15, C0377R.id.spinner15, C0377R.id.button15, arrayAdapter);
        initContrl(16, C0377R.id.tableRow16, C0377R.id.group16, C0377R.id.spinner16, C0377R.id.button16, arrayAdapter);
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onUpdateUI() {
        int i;
        int groupNumber = ExchData.getGroupNumber();
        int i2 = 1;
        while (true) {
            i = 0;
            if (i2 > groupNumber) {
                break;
            }
            Resources resources = getResources();
            findViewById(resources.getIdentifier("tableRow" + String.valueOf(i2), "id", getPackageName())).setVisibility(0);
            i2++;
        }
        this.tmp = ExchData.getAllData();
        this.mainButtons = new String[128];
        System.arraycopy(this.tmp, 100, this.mainButtons, 0, 128);
        for (int i3 = 0; i3 <= 127; i3++) {
            if (this.mainButtons[i3] != null && this.mainButtons[i3] != "") {
                try {
                    this.mainButtonsName[i3] = (char) Integer.parseInt(this.mainButtons[i3], 2);
                } catch (Exception unused) {
                    PrintStream printStream = System.out;
                    printStream.println("i: " + i3);
                    PrintStream printStream2 = System.out;
                    printStream2.println("Exception: " + Arrays.toString(this.mainButtons));
                    System.out.println(this.mainButtons[i3]);
                    System.out.println(this.mainButtons[i3] != null);
                    System.out.println(this.mainButtons[i3] != "");
                }
            }
        }
        while (i <= 15) {
            int i4 = i * 8;
            this.proActivityButtonName[i] = String.valueOf(this.mainButtonsName).substring(i4, i4 + 8);
            Resources resources2 = getResources();
            StringBuilder sb = new StringBuilder();
            sb.append("group");
            int i5 = i + 1;
            sb.append(String.valueOf(i5));
            ((TextView) findViewById(resources2.getIdentifier(sb.toString(), "id", getPackageName()))).setText(this.proActivityButtonName[i]);
            i = i5;
        }
    }

    private void hideExtraGroups(int i, TableRow tableRow) {
        if (i > ExchData.getGroupNumber()) {
            tableRow.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SpinnerOnSelectedListener implements AdapterView.OnItemSelectedListener {
        int mGroupNumber;

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        public SpinnerOnSelectedListener(int i) {
            this.mGroupNumber = i;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            PrintStream printStream = System.out;
            printStream.println("adapterview1>>" + i);
            PrintStream printStream2 = System.out;
            printStream2.println("adapterview>>" + adapterView);
            NamingActivity.this.sendMessage.SetNameMessage(this.mGroupNumber, adapterView.getItemAtPosition(i).toString());
            ExchData.sendMessage(NamingActivity.this.sendMessage.GetInBytes());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class OnNameClickListener implements View.OnClickListener {
        protected int mIndex;
        protected TextView mName;

        OnNameClickListener(int i, TextView textView) {
            this.mIndex = i;
            this.mName = textView;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final EditText editText = new EditText(NamingActivity.this);
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
            editText.setText(NamingActivity.this.ClearBlank((String) this.mName.getText()));
            Editable text = editText.getText();
            if (text instanceof Spannable) {
                Selection.setSelection(text, text.length());
            }
            AlertDialog.Builder negativeButton = new AlertDialog.Builder(NamingActivity.this).setTitle("Please Input Group Name").setView(editText).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.NamingActivity.OnNameClickListener.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            negativeButton.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.NamingActivity.OnNameClickListener.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    NamingActivity.this.sendMessage.SetNameMessage(OnNameClickListener.this.mIndex, editText.getText().toString());
                    ExchData.sendMessage(NamingActivity.this.sendMessage.GetInBytes());
                }
            });
            negativeButton.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String ClearBlank(String str) {
        return str.trim();
    }
}
