package au.com.polyaire.airtouch2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import au.com.polyaire.airtouch2.comm.InternetSingleConnection;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityBase;

/* loaded from: classes.dex */
public class SystemActivity extends ActivityBase {
    CheckBox checkBoxDisplayRoomTemp;
    CheckBox checkBoxToshibaThreeFanSpeed;
    TextView deviceIDView;
    TextView displayIPAddress;
    TextView displayMACAddress;
    ImageView editOwnerButton;
    Button editPasswordButton;
    FileHelper fileHelper = new FileHelper();
    String newPassword;
    TextView ownerNameDisplay;
    EditText ownerNameEdit;
    SharedPreferences settings;

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected int getMainViewID() {
        return C0377R.id.systemMain;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.system_layout);
        this.settings = getSharedPreferences("Settings", 0);
        declareAndSetupOwnerPasswordFields();
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onSubResume() {
        changeEditFields("ownerReset");
        displayIPMacDeviceID();
        declareDisplayTemp();
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onUpdateUI() {
        this.ownerNameDisplay.setText(ExchData.getSystemName());
        this.ownerNameEdit.setText(ExchData.getSystemName());
    }

    public void changeEditFields(String str) {
        if (str.equals("ownerEdit")) {
            this.ownerNameDisplay.setVisibility(8);
            this.ownerNameEdit.setVisibility(0);
            this.editOwnerButton.setVisibility(8);
        } else if (str.equals("ownerReset")) {
            this.ownerNameEdit.setVisibility(8);
            this.ownerNameDisplay.setVisibility(0);
            this.editOwnerButton.setVisibility(0);
        }
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onRecvMessage(int i, int i2) {
        if (i == 2) {
            if (i2 == 1) {
                SharedPreferences.Editor edit = this.settings.edit();
                edit.putString("airtouchPassword." + (this.settings.getInt("savedSystems", 0) - 1), this.newPassword);
                edit.apply();
                Toast.makeText(this, "Password change successful", 1).show();
                return;
            }
            Toast.makeText(this, "Failed", 1).show();
        }
    }

    private void declareAndSetupOwnerPasswordFields() {
        this.ownerNameDisplay = (TextView) findViewById(C0377R.id.ownerNameDisplay);
        this.ownerNameEdit = (EditText) findViewById(C0377R.id.ownerNameEdit);
        this.ownerNameEdit.setOnKeyListener(new View.OnKeyListener() { // from class: au.com.polyaire.airtouch2.SystemActivity.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 0 && i == 66) {
                    String obj = SystemActivity.this.ownerNameEdit.getText().toString();
                    if (!obj.equals("")) {
                        ExchData.sendMessage(new MessageInBytes().GetNewOwnerMessage(obj));
                    }
                    SystemActivity.this.changeEditFields("ownerReset");
                    ((InputMethodManager) SystemActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(SystemActivity.this.getCurrentFocus().getWindowToken(), 2);
                    SystemActivity.this.findViewById(SystemActivity.this.getMainViewID()).post(new Runnable() { // from class: au.com.polyaire.airtouch2.SystemActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SystemActivity.this.showPopMenu();
                        }
                    });
                    return true;
                }
                return false;
            }
        });
        this.editOwnerButton = (ImageView) findViewById(C0377R.id.buttonEditOwner);
        this.editOwnerButton.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.SystemActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SystemActivity.this.hidePopMenu();
                SystemActivity.this.changeEditFields("ownerEdit");
                SystemActivity.this.ownerNameEdit.setText("");
                SystemActivity.this.ownerNameEdit.setHint(ExchData.getSystemName());
                SystemActivity.this.ownerNameEdit.requestFocus();
                SystemActivity.this.ownerNameEdit.postDelayed(new Runnable() { // from class: au.com.polyaire.airtouch2.SystemActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((InputMethodManager) SystemActivity.this.getSystemService("input_method")).showSoftInput(SystemActivity.this.ownerNameEdit, 0);
                    }
                }, 0L);
            }
        });
        this.editPasswordButton = (Button) findViewById(C0377R.id.buttonEditPassword);
        this.editPasswordButton.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.SystemActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SystemActivity.this.showPasswordDialog();
            }
        });
        if (ExchData.hasToshibaAC()) {
            findViewById(C0377R.id.FanSpeedLine).setVisibility(0);
        } else {
            findViewById(C0377R.id.FanSpeedLine).setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Password:");
        final EditText editText = new EditText(this);
        editText.setSingleLine(true);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        builder.setView(editText);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.SystemActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText.getText().toString();
                MessageInBytes messageInBytes = new MessageInBytes();
                if (obj.length() < 4 || obj.length() > 8) {
                    Toast.makeText(SystemActivity.this, "Password must be between 4 and 8 characters", 0).show();
                    return;
                }
                if (ExchData.mConnection.isInternetMode()) {
                    ExchData.sendMessage(messageInBytes.SetLocalPasswordMessage(ExchData.getAirtouchID(), obj));
                } else {
                    InternetSingleConnection internetSingleConnection = new InternetSingleConnection(messageInBytes.SetLocalPasswordMessage(ExchData.getAirtouchID(), obj));
                    internetSingleConnection.setContext(SystemActivity.this);
                    internetSingleConnection.connect();
                }
                SystemActivity.this.newPassword = obj;
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.SystemActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private void displayIPMacDeviceID() {
        String serverIP = ExchData.mConnection.getServerIP();
        String serverMac = ExchData.mConnection.getServerMac();
        this.displayIPAddress = (TextView) findViewById(C0377R.id.ipaddr);
        this.displayMACAddress = (TextView) findViewById(C0377R.id.macaddr);
        this.deviceIDView = (TextView) findViewById(C0377R.id.deviceID);
        this.checkBoxDisplayRoomTemp = (CheckBox) findViewById(C0377R.id.checkBoxDisplayRoomTemp);
        this.checkBoxToshibaThreeFanSpeed = (CheckBox) findViewById(C0377R.id.checkBoxTOSHIBA3FanSpeed);
        this.ownerNameDisplay.setText(ExchData.getSystemName());
        this.displayIPAddress.setText(serverIP);
        if (serverMac.length() > 11) {
            int parseInt = Integer.parseInt(serverMac.substring(11, 12), 16);
            String substring = serverMac.substring(0, 11);
            if (12 == parseInt) {
                serverMac = substring + "D";
            } else if (8 == parseInt) {
                serverMac = substring + "9";
            } else if (4 == parseInt) {
                serverMac = substring + "5";
            } else if (parseInt == 0) {
                serverMac = substring + "1";
            }
            serverMac = serverMac.substring(0, 2) + ":" + serverMac.substring(2, 4) + ":" + serverMac.substring(4, 6) + ":" + serverMac.substring(6, 8) + ":" + serverMac.substring(8, 10) + ":" + serverMac.substring(10, 12);
        }
        this.displayMACAddress.setText(serverMac);
        this.deviceIDView.setText(ExchData.getAirtouchID());
    }

    private void declareDisplayTemp() {
        if (ExchData.isDisplayTempInTopSpill()) {
            this.checkBoxDisplayRoomTemp.setChecked(true);
        } else {
            this.checkBoxDisplayRoomTemp.setChecked(false);
        }
        if (ExchData.hasToshibaAC()) {
            if (ExchData.isToshibaThreeFanSpeed()) {
                this.checkBoxToshibaThreeFanSpeed.setChecked(true);
            } else {
                this.checkBoxToshibaThreeFanSpeed.setChecked(false);
            }
        }
    }

    public void onCheckboxClicked(View view) {
        SharedPreferences.Editor edit = this.settings.edit();
        if (((CheckBox) view).isChecked()) {
            ExchData.setDisplayTempInTopSpill(true);
            edit.putBoolean("displayTempInTopSpill", true);
        } else {
            ExchData.setDisplayTempInTopSpill(false);
            edit.putBoolean("displayTempInTopSpill", false);
        }
        edit.commit();
    }

    public void onCheckboxTOSHIBA3FanSpeedClicked(View view) {
        SharedPreferences.Editor edit = this.settings.edit();
        if (((CheckBox) view).isChecked()) {
            ExchData.setToshibaThreeFanSpeed(true);
            edit.putBoolean(ExchData.getToshibaFanSpeedSettingName(), true);
        } else {
            ExchData.setToshibaThreeFanSpeed(false);
            edit.putBoolean(ExchData.getToshibaFanSpeedSettingName(), false);
        }
        edit.commit();
    }
}
