package au.com.polyaire.airtouch2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import au.com.polyaire.airtouch2.comm.InternetConnection;
import au.com.polyaire.airtouch2.comm.InternetTool;
import au.com.polyaire.airtouch2.comm.LocalConnection;
import au.com.polyaire.airtouch2.comm.LocalDevices;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityManageTool;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class LoginActivity extends Activity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int sMaxWaitConnectTime = 10000;
    ImageView buttonAddNewSystem;
    ImageView buttonDeleteSystem;
    ImageView buttonHelp;
    Spinner dropdown;
    LinearLayout dropdownBox;
    ArrayAdapter<String> dropdownListAdapter;
    EditText editPassword;
    EditText editTextAddNewSystem;
    SharedPreferences.Editor editor;
    private TextView failedConnectionMsg;
    private TextView failedConnectionTitle;
    FileHelper fileHelper;
    private boolean inSetIP;
    Button internetButton;
    Button localButton;
    SafeCountDownTimer mInternetConnectMonitor;
    SafeCountDownTimer mLocalConnectMonitor;
    SafeCountDownTimer mLocalSearchMonitor;
    CheckBox remPasswordCheck;
    private SharedPreferences settings;
    TextView spinnerTag;
    ArrayList<String> storedVariables;
    LocalDevices localDev = new LocalDevices();
    boolean addNewSystemMode = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.activity_login);
        FileHelper.init(getExternalFilesDir(null));
        this.fileHelper = new FileHelper();
        this.failedConnectionTitle = (TextView) findViewById(C0377R.id.failedConnectionTitle);
        this.failedConnectionMsg = (TextView) findViewById(C0377R.id.failedConnectionMsg);
        ((Button) findViewById(C0377R.id.retry)).setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.startChecking();
            }
        });
        this.settings = getSharedPreferences("Settings", 0);
    }

    private void ExitDialog() {
        ActivityManageTool.onExit();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.inSetIP) {
            exitInputIP();
            return;
        }
        if (this.mLocalConnectMonitor != null) {
            this.mLocalConnectMonitor.cancelTimer();
        }
        if (this.mLocalSearchMonitor != null) {
            this.mLocalSearchMonitor.cancelTimer();
        }
        if (this.localDev != null) {
            this.localDev.stopSearching();
        }
        ActivityManageTool.onExit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class SafeCountDownTimer extends CountDownTimer {
        public boolean mCanceled;

        abstract void onFinishDoing();

        abstract void onTickDoing();

        public SafeCountDownTimer(long j, long j2) {
            super(j, j2);
            this.mCanceled = false;
            this.mCanceled = false;
        }

        public void cancelTimer() {
            this.mCanceled = true;
            cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (this.mCanceled) {
                return;
            }
            onTickDoing();
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (this.mCanceled) {
                return;
            }
            onFinishDoing();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.inSetIP = false;
        if (FileHelper.needInit()) {
            FileHelper.init(getExternalFilesDir(null));
        }
        this.fileHelper = new FileHelper();
        ActivityManageTool.onInitResume(this);
        showWaiting();
        this.settings = getSharedPreferences("Settings", 0);
        if ("".equals(ExchData.mErrorTitle) && "".equals(ExchData.mErrorMsg)) {
            startChecking();
        } else {
            showInfo();
        }
    }

    private boolean checkNetwork() {
        if (((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            ExchData.mErrorTitle = "No networks detected";
            ExchData.mErrorMsg = "Turn on WIFI or Mobile Data";
            showInfo();
            return false;
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [au.com.polyaire.airtouch2.LoginActivity$2] */
    protected void showInfo() {
        new CountDownTimer(100L, 100L) { // from class: au.com.polyaire.airtouch2.LoginActivity.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                LoginActivity.this.findViewById(C0377R.id.errorlayout).setVisibility(0);
                LoginActivity.this.findViewById(C0377R.id.initLoginDlg).setVisibility(8);
                LoginActivity.this.findViewById(C0377R.id.initWaitingDlg).setVisibility(8);
                LoginActivity.this.failedConnectionTitle.setText(ExchData.mErrorTitle);
                LoginActivity.this.failedConnectionMsg.setText(ExchData.mErrorMsg);
            }
        }.start();
    }

    protected void startChecking() {
        showWaiting();
        if (checkNetwork()) {
            doChecking();
        }
    }

    protected void doChecking() {
        findViewById(C0377R.id.errorlayout).setVisibility(8);
        if (!InternetTool.isWifiConnected(this)) {
            showLoginDialog();
            return;
        }
        if (!this.fileHelper.isFileExist("/AirTouch2/ip.txt") || !this.fileHelper.isFileExist("/AirTouch2/mac.txt")) {
            try {
                this.fileHelper.createSDDir("AirTouch2");
                this.fileHelper.createSDFile("/AirTouch2/ip.txt");
                this.fileHelper.createSDFile("/AirTouch2/mac.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String ip = this.fileHelper.getIp("/AirTouch2/ip.txt");
        String ip2 = this.fileHelper.getIp("/AirTouch2/mac.txt");
        if ("".equals(ip) || ip == null) {
            this.localDev.startSearching(this);
            startLocalSearchMonitor();
            return;
        }
        doConnLocal(ip, ip2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doConnLocal(String str, String str2) {
        showWaiting();
        ExchData.mConnection = new LocalConnection(str);
        ((LocalConnection) ExchData.mConnection).setMac(str2);
        ExchData.mConnection.setContext(this);
        ExchData.mConnection.connect();
        startLocalConnectionMonitor();
    }

    private void startLocalSearchMonitor() {
        this.mLocalSearchMonitor = new SafeCountDownTimer(60000L, 500L) { // from class: au.com.polyaire.airtouch2.LoginActivity.3
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // au.com.polyaire.airtouch2.LoginActivity.SafeCountDownTimer
            public void onTickDoing() {
                String ip;
                if (!LoginActivity.this.localDev.isSearching() && (ip = LoginActivity.this.localDev.getIp()) != null && !"".equals(ip)) {
                    cancelTimer();
                    LoginActivity.this.mLocalSearchMonitor = null;
                    LoginActivity.this.doConnLocal(ip, LoginActivity.this.localDev.getMac());
                } else if (InternetTool.isWifiConnected(LoginActivity.this) && LoginActivity.this.localDev.isSearching()) {
                } else {
                    cancelTimer();
                    LoginActivity.this.mLocalSearchMonitor = null;
                    LoginActivity.this.showLoginDialog();
                }
            }

            @Override // au.com.polyaire.airtouch2.LoginActivity.SafeCountDownTimer
            public void onFinishDoing() {
                LoginActivity.this.showLoginDialog();
            }
        };
        this.mLocalSearchMonitor.start();
    }

    private void startLocalConnectionMonitor() {
        this.mLocalConnectMonitor = new SafeCountDownTimer(10000L, 500L) { // from class: au.com.polyaire.airtouch2.LoginActivity.4
            @Override // au.com.polyaire.airtouch2.LoginActivity.SafeCountDownTimer
            public void onTickDoing() {
                if (ExchData.mConnection == null || !ExchData.mConnection.isConnecting()) {
                    onFinishDoing();
                    cancelTimer();
                    LoginActivity.this.mLocalConnectMonitor = null;
                }
            }

            @Override // au.com.polyaire.airtouch2.LoginActivity.SafeCountDownTimer
            public void onFinishDoing() {
                LoginActivity.this.mLocalConnectMonitor = null;
                if (ExchData.mConnection != null && ExchData.mConnection.isConnected()) {
                    LoginActivity.this.fileHelper.writeToSDCard("/AirTouch2/ip.txt", LoginActivity.this.localDev.getIp());
                    LoginActivity.this.fileHelper.writeToSDCard("/AirTouch2/mac.txt", LoginActivity.this.localDev.getMac());
                    LoginActivity.this.onConnected();
                    return;
                }
                if (ExchData.mConnection != null) {
                    ExchData.mConnection.disconnect();
                    ExchData.mConnection = null;
                }
                LoginActivity.this.showLoginDialog();
            }
        };
        this.mLocalConnectMonitor.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnected() {
        startActivity(new Intent(this, WifiMainActivity.class));
    }

    private void showWaiting() {
        findViewById(C0377R.id.initLoginDlg).setVisibility(8);
        findViewById(C0377R.id.errorlayout).setVisibility(8);
        findViewById(C0377R.id.initWaitingDlg).setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoginDialog() {
        findViewById(C0377R.id.initLoginDlg).setVisibility(0);
        findViewById(C0377R.id.errorlayout).setVisibility(8);
        findViewById(C0377R.id.initWaitingDlg).setVisibility(8);
        this.editor = this.settings.edit();
        this.dropdown = (Spinner) findViewById(C0377R.id.airtouchPicker);
        this.dropdownBox = (LinearLayout) findViewById(C0377R.id.dropdownBox);
        this.editTextAddNewSystem = (EditText) findViewById(C0377R.id.editTextAddNewSystem);
        this.spinnerTag = (TextView) findViewById(C0377R.id.spinnerTag);
        this.editPassword = (EditText) findViewById(C0377R.id.editTextUserPassword);
        this.internetButton = (Button) findViewById(C0377R.id.dialogButtonInternet);
        this.buttonAddNewSystem = (ImageView) findViewById(C0377R.id.buttonAddNewSystem);
        this.buttonDeleteSystem = (ImageView) findViewById(C0377R.id.buttonDeleteSystem);
        this.buttonHelp = (ImageView) findViewById(C0377R.id.buttonHelp);
        this.remPasswordCheck = (CheckBox) findViewById(C0377R.id.remPasswordCheck);
        if (!this.settings.getBoolean("remPass", true)) {
            this.remPasswordCheck.setChecked(false);
        }
        if (this.settings.getInt("savedSystems", 0) == 0) {
            this.buttonAddNewSystem.setVisibility(4);
            this.buttonDeleteSystem.setVisibility(8);
            this.addNewSystemMode = true;
            this.spinnerTag.setText("New Airtouch ID");
            this.dropdownBox.setVisibility(8);
            this.editTextAddNewSystem.setVisibility(0);
            this.editPassword.setText("");
        } else {
            EditText editText = this.editPassword;
            SharedPreferences sharedPreferences = this.settings;
            editText.setText(sharedPreferences.getString("airtouchPassword." + getSystemIndex(this.dropdown.getSelectedItemPosition()), ""));
            this.editPassword.setSelection(this.editPassword.getText().length());
        }
        this.storedVariables = getStoredIdAndNameAsString();
        this.buttonAddNewSystem.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.onAddNewSystem();
            }
        });
        this.buttonDeleteSystem.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.onDeleteSystem();
            }
        });
        this.storedVariables = getStoredIdAndNameAsString();
        if (this.storedVariables != null) {
            this.dropdownListAdapter = new ArrayAdapter<>(this, 17367043, this.storedVariables);
            this.dropdown.setAdapter((SpinnerAdapter) this.dropdownListAdapter);
        }
        this.dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.7
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                EditText editText2 = LoginActivity.this.editPassword;
                SharedPreferences sharedPreferences2 = LoginActivity.this.settings;
                editText2.setText(sharedPreferences2.getString("airtouchPassword." + LoginActivity.this.getSystemIndex(LoginActivity.this.dropdown.getSelectedItemPosition()), ""));
                LoginActivity.this.editPassword.setSelection(LoginActivity.this.editPassword.getText().length());
            }
        });
        ((Button) findViewById(C0377R.id.dialogButtonRetry)).setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.findViewById(C0377R.id.inputIPDlg).setVisibility(0);
                LoginActivity.this.inSetIP = true;
            }
        });
        findViewById(C0377R.id.dialogButtonInternetConn).setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.connectToIP();
            }
        });
        this.internetButton.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.onConnToInternet();
            }
        });
        this.buttonHelp.setOnClickListener(new View.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.onHelp();
            }
        });
    }

    private void exitInputIP() {
        findViewById(C0377R.id.inputIPDlg).setVisibility(8);
        this.inSetIP = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToIP() {
        exitInputIP();
        String trim = ((EditText) findViewById(C0377R.id.IDEditDevIP1)).getText().toString().trim();
        String trim2 = ((EditText) findViewById(C0377R.id.IDEditDevIP2)).getText().toString().trim();
        String trim3 = ((EditText) findViewById(C0377R.id.IDEditDevIP3)).getText().toString().trim();
        String trim4 = ((EditText) findViewById(C0377R.id.IDEditDevIP4)).getText().toString().trim();
        showWaiting();
        if (trim.isEmpty() && trim2.isEmpty() && trim3.isEmpty() && trim4.isEmpty()) {
            this.localDev.startSearching(this);
            startLocalSearchMonitor();
            return;
        }
        if (trim.isEmpty()) {
            trim = "0";
        }
        if (trim2.isEmpty()) {
            trim2 = "0";
        }
        if (trim3.isEmpty()) {
            trim3 = "0";
        }
        if (trim4.isEmpty()) {
            trim4 = "0";
        }
        String str = trim + "." + trim2 + "." + trim3 + "." + trim4;
        this.localDev.setIp(str);
        doConnLocal(str, "N/A");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAddNewSystem() {
        if (this.addNewSystemMode) {
            this.buttonAddNewSystem.setImageResource(C0377R.C0378drawable.ic_action_add);
            this.spinnerTag.setText("Saved Systems");
            this.editTextAddNewSystem.setVisibility(8);
            this.dropdownBox.setVisibility(0);
            EditText editText = this.editPassword;
            SharedPreferences sharedPreferences = this.settings;
            editText.setText(sharedPreferences.getString("airtouchPassword." + getSystemIndex(this.dropdown.getSelectedItemPosition()), ""));
            this.editPassword.setSelection(this.editPassword.getText().length());
            this.addNewSystemMode = false;
        } else if (this.addNewSystemMode) {
        } else {
            this.buttonAddNewSystem.setImageResource(C0377R.C0378drawable.ic_action_goleft);
            this.spinnerTag.setText("New Device ID");
            this.dropdownBox.setVisibility(8);
            this.editTextAddNewSystem.setVisibility(0);
            this.editPassword.setText("");
            this.editTextAddNewSystem.requestFocus();
            this.addNewSystemMode = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeleteSystem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this saved system?");
        builder.setMessage(this.storedVariables.get(this.dropdown.getSelectedItemPosition()));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                int i2 = LoginActivity.this.settings.getInt("savedSystems", 0);
                if (i2 != 0) {
                    LoginActivity.this.removeSystemFromStorage(i2, LoginActivity.this.dropdown.getSelectedItemPosition());
                    LoginActivity.this.storedVariables.remove(LoginActivity.this.dropdown.getSelectedItemPosition());
                    LoginActivity.this.dropdownListAdapter.notifyDataSetChanged();
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Deleted", 0).show();
                    EditText editText = LoginActivity.this.editPassword;
                    SharedPreferences sharedPreferences = LoginActivity.this.settings;
                    editText.setText(sharedPreferences.getString("airtouchPassword." + LoginActivity.this.getSystemIndex(LoginActivity.this.dropdown.getSelectedItemPosition()), ""));
                    LoginActivity.this.editPassword.setSelection(LoginActivity.this.editPassword.getText().length());
                    if (i2 == 1) {
                        LoginActivity.this.buttonAddNewSystem.setVisibility(4);
                        LoginActivity.this.buttonDeleteSystem.setVisibility(8);
                        LoginActivity.this.addNewSystemMode = true;
                        LoginActivity.this.spinnerTag.setText("New Airtouch ID");
                        LoginActivity.this.dropdownBox.setVisibility(8);
                        LoginActivity.this.editTextAddNewSystem.setVisibility(0);
                        LoginActivity.this.editPassword.setText("");
                        LoginActivity.this.editTextAddNewSystem.requestFocus();
                    }
                }
                LoginActivity.this.editor.commit();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnToInternet() {
        String obj = this.editPassword.getText().toString();
        if (obj.equals("")) {
            Toast.makeText(getApplicationContext(), "Input password to connect", 0).show();
            return;
        }
        if (this.remPasswordCheck.isChecked()) {
            this.editor.putBoolean("remPass", true);
            this.editor.putString("tempPassword", obj);
        } else {
            this.editor.putBoolean("remPass", false);
            this.editor.putString("tempPassword", "");
        }
        this.editor.apply();
        String str = null;
        if (this.addNewSystemMode) {
            String obj2 = this.editTextAddNewSystem.getText().toString();
            if (obj2.length() != 8) {
                Toast.makeText(getApplicationContext(), "Airtouch ID must be 8 characters", 0).show();
            } else {
                str = obj2;
            }
        } else if (!this.addNewSystemMode) {
            SharedPreferences sharedPreferences = this.settings;
            str = sharedPreferences.getString("airtouchID." + getSystemIndex(this.dropdown.getSelectedItemPosition()), "");
        }
        if (str != null) {
            showWaiting();
            ExchData.mConnection = new InternetConnection(str, obj);
            ExchData.mConnection.setContext(this);
            ExchData.mConnection.connect();
            startInternetConnectionMonitor();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHelp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login Help");
        builder.setMessage(C0377R.string.help);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: au.com.polyaire.airtouch2.LoginActivity.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSystemIndex(int i) {
        return (this.settings.getInt("savedSystems", 0) - 1) - i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeSystemFromStorage(int i, int i2) {
        SharedPreferences.Editor edit = this.settings.edit();
        int i3 = i - 1;
        int i4 = i3 - i2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i5 = 0; i5 < i; i5++) {
            arrayList.add(this.settings.getString("airtouchID." + i5, ""));
            arrayList2.add(this.settings.getString("ownerName." + i5, ""));
            arrayList3.add(this.settings.getString("airtouchPassword." + i5, ""));
        }
        arrayList.remove(i4);
        arrayList2.remove(i4);
        arrayList3.remove(i4);
        edit.putInt("savedSystems", i3);
        for (int i6 = 0; i6 < i3; i6++) {
            edit.putString("airtouchID." + i6, (String) arrayList.get(i6));
            edit.putString("ownerName." + i6, (String) arrayList2.get(i6));
            edit.putString("airtouchPassword." + i6, (String) arrayList3.get(i6));
        }
        edit.apply();
    }

    private ArrayList<String> getStoredIdAndNameAsString() {
        int i = this.settings.getInt("savedSystems", 0);
        if (i == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = i - 1; i2 >= 0; i2 += -1) {
            arrayList.add(this.settings.getString("airtouchID." + i2, ""));
            arrayList2.add(this.settings.getString("ownerName." + i2, ""));
        }
        ArrayList<String> arrayList3 = new ArrayList<>();
        for (int i3 = 0; i3 < i; i3++) {
            StringBuilder sb = new StringBuilder();
            sb.append((String) arrayList2.get(i3));
            sb.append(" (");
            sb.append((String) arrayList.get(i3));
            sb.append(")");
            arrayList3.add(String.valueOf(sb));
        }
        return arrayList3;
    }

    private void startInternetConnectionMonitor() {
        this.mInternetConnectMonitor = new SafeCountDownTimer(10000L, 500L) { // from class: au.com.polyaire.airtouch2.LoginActivity.15
            @Override // au.com.polyaire.airtouch2.LoginActivity.SafeCountDownTimer
            public void onTickDoing() {
                if (ExchData.mConnection == null || !ExchData.mConnection.isConnecting()) {
                    onFinishDoing();
                    cancelTimer();
                    if (LoginActivity.this.mInternetConnectMonitor != null) {
                        LoginActivity.this.mInternetConnectMonitor = null;
                    }
                }
            }

            @Override // au.com.polyaire.airtouch2.LoginActivity.SafeCountDownTimer
            public void onFinishDoing() {
                LoginActivity.this.mInternetConnectMonitor = null;
                if (ExchData.mConnection != null && ExchData.mConnection.isConnected()) {
                    LoginActivity.this.onConnected();
                    return;
                }
                if (ExchData.mConnection != null) {
                    ExchData.mConnection.disconnect();
                    ExchData.mConnection = null;
                }
                if ("".equals(ExchData.mErrorTitle) || "".equals(ExchData.mErrorMsg)) {
                    LoginActivity.this.showLoginDialog();
                } else {
                    LoginActivity.this.showInfo();
                }
            }
        };
        this.mInternetConnectMonitor.start();
    }
}
