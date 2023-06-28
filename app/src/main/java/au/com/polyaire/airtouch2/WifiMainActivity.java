package au.com.polyaire.airtouch2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import au.com.polyaire.airtouch2.data.ACInfo;
import au.com.polyaire.airtouch2.data.ExchData;
import au.com.polyaire.airtouch2.service.ActivityBase;

/* loaded from: classes.dex */
public class WifiMainActivity extends ActivityBase {
    private TextView acName;
    Drawable acoff;
    Drawable acon;
    private ImageView acstatus;
    private ImageView fanButtonAuto;
    private ImageView fanButtonHigh;
    private ImageView fanButtonLow;
    private ImageView fanButtonMed;
    private ImageView fanButtonPowerful;
    private ImageView fanButtonQuiet;
    private TextView fanTextAuto;
    private TextView fanTextHigh;
    private TextView fanTextLow;
    private TextView fanTextMed;
    private TextView fanTextPowerful;
    private TextView fanTextQuiet;
    private TextView internetIcon;
    private ImageView modeButtonAuto;
    private ImageView modeButtonCool;
    private ImageView modeButtonDry;
    private ImageView modeButtonFan;
    private ImageView modeButtonHeat;
    private TextView modeTextAuto;
    private TextView modeTextDry;
    Drawable na;
    private ImageView nextGroup;
    Drawable off;
    Drawable on;
    private TextView programIcon;
    TableRow row10FanText;
    TableRow row3Power;
    TableRow row7Temp;
    TableRow row8FanTitle;
    TableRow row9FanButtons;
    private TextView setTemp;
    private ImageView setTempMinus;
    private ImageView setTempPlus;
    private TextView setpointText;
    private LinearLayout timerIcon;
    private TextView topSpill;
    private ACInfo.E_ACControlMode currentControlMode = null;
    private SoundPool soundPool = null;
    int supportedFanSpeed = -1;
    boolean addAutotoMaxFanSpeed2 = false;
    boolean addAutotoMaxFanSpeed3 = false;
    public boolean addAutotoMaxFanSpeed4 = false;
    int lastBrand = -1;
    boolean initUnavail = true;

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected int getMainViewID() {
        return C0377R.id.acInfoMain;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0377R.layout.main_layout);
        System.out.println("Wifi onCreate");
        ExchData.setDisplayTempInTopSpill(getSharedPreferences("Settings", 0).getBoolean("displayTempInTopSpill", false));
        declareViews();
        setEventListener();
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase, android.app.Activity
    protected void onResume() {
        findViewById(C0377R.id.errorlayout).setVisibility(8);
        findViewById(C0377R.id.toplayout).setVisibility(0);
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MasterListener implements View.OnClickListener {
        int choose;

        public MasterListener(int i) {
            this.choose = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MessageInBytes messageInBytes = new MessageInBytes();
            int i = this.choose;
            if (i == C0377R.id.acstatus) {
                messageInBytes.SetACOnOff();
            } else if (i == C0377R.id.nextGroup) {
                ExchData.changeSelectedAC();
                WifiMainActivity.this.updateUI();
                return;
            } else if (i != C0377R.id.plusTemp) {
                switch (i) {
                    case C0377R.id.fanButtonAuto /* 2131230852 */:
                        messageInBytes.SetFanSpeedMessage(ACInfo.E_ACFanMode.Auto);
                        break;
                    case C0377R.id.fanButtonHigh /* 2131230853 */:
                        messageInBytes.SetFanSpeedMessage(ACInfo.E_ACFanMode.High);
                        break;
                    case C0377R.id.fanButtonLow /* 2131230854 */:
                        messageInBytes.SetFanSpeedMessage(ACInfo.E_ACFanMode.Low);
                        break;
                    case C0377R.id.fanButtonMed /* 2131230855 */:
                        messageInBytes.SetFanSpeedMessage(ACInfo.E_ACFanMode.Medium);
                        break;
                    case C0377R.id.fanButtonPowerful /* 2131230856 */:
                        messageInBytes.SetFanSpeedMessage(ACInfo.E_ACFanMode.Powerful);
                        break;
                    case C0377R.id.fanButtonQuiet /* 2131230857 */:
                        messageInBytes.SetFanSpeedMessage(ACInfo.E_ACFanMode.Quiet);
                        break;
                    default:
                        switch (i) {
                            case C0377R.id.minusTemp /* 2131231032 */:
                                messageInBytes.SetNewTempMessage("minus1");
                                break;
                            case C0377R.id.modeButtonAuto /* 2131231033 */:
                                messageInBytes.SetModeMessage(0);
                                break;
                            case C0377R.id.modeButtonCool /* 2131231034 */:
                                messageInBytes.SetModeMessage(4);
                                break;
                            case C0377R.id.modeButtonDry /* 2131231035 */:
                                messageInBytes.SetModeMessage(2);
                                break;
                            case C0377R.id.modeButtonFan /* 2131231036 */:
                                messageInBytes.SetModeMessage(3);
                                break;
                            case C0377R.id.modeButtonHeat /* 2131231037 */:
                                messageInBytes.SetModeMessage(1);
                                break;
                        }
                }
            } else {
                messageInBytes.SetNewTempMessage("plus1");
            }
            ExchData.sendMessage(messageInBytes.GetInBytes());
        }
    }

    public void setModeBackgroundWithoutAutoButton(ACInfo.E_ACMode e_ACMode) {
        this.modeButtonHeat.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
        this.modeButtonCool.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
        this.modeButtonFan.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
        this.modeButtonDry.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
        this.modeButtonAuto.setVisibility(8);
        this.modeTextAuto.setVisibility(8);
        this.row7Temp.setVisibility(0);
        this.row8FanTitle.setVisibility(0);
        this.row9FanButtons.setVisibility(0);
        this.row10FanText.setVisibility(0);
        if (e_ACMode == ACInfo.E_ACMode.Heat) {
            this.modeButtonHeat.setBackgroundResource(C0377R.C0378drawable.btnbase_left_s);
        } else if (e_ACMode == ACInfo.E_ACMode.Cool) {
            this.modeButtonCool.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
        } else if (e_ACMode == ACInfo.E_ACMode.Fan) {
            System.out.println("fan mode on");
            this.modeButtonFan.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            this.row7Temp.setVisibility(4);
        } else if (e_ACMode == ACInfo.E_ACMode.Dry) {
            this.modeButtonDry.setBackgroundResource(C0377R.C0378drawable.btnbase_right_s);
            this.row7Temp.setVisibility(4);
            this.row8FanTitle.setVisibility(4);
            this.row9FanButtons.setVisibility(4);
            this.row10FanText.setVisibility(4);
        }
    }

    public void setModeBackground(ACInfo.E_ACMode e_ACMode) {
        this.modeButtonHeat.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
        this.modeButtonCool.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
        this.modeButtonFan.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
        this.modeButtonDry.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
        this.modeButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
        this.row7Temp.setVisibility(0);
        this.row8FanTitle.setVisibility(0);
        this.row9FanButtons.setVisibility(0);
        this.row10FanText.setVisibility(0);
        if (e_ACMode == ACInfo.E_ACMode.Heat) {
            this.modeButtonHeat.setBackgroundResource(C0377R.C0378drawable.btnbase_left_s);
        } else if (e_ACMode == ACInfo.E_ACMode.Cool) {
            this.modeButtonCool.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
        } else if (e_ACMode == ACInfo.E_ACMode.Fan) {
            System.out.println("fan mode on");
            this.modeButtonFan.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            this.row7Temp.setVisibility(4);
        } else if (e_ACMode == ACInfo.E_ACMode.Dry) {
            this.modeButtonDry.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            this.row7Temp.setVisibility(4);
            this.row8FanTitle.setVisibility(4);
            this.row9FanButtons.setVisibility(4);
            this.row10FanText.setVisibility(4);
        } else if (e_ACMode == ACInfo.E_ACMode.Auto) {
            this.modeButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right_s);
        }
    }

    public void setFanBackground(ACInfo.E_ACFanMode e_ACFanMode) {
        ACInfo ac2;
        switch (this.supportedFanSpeed) {
            case 1:
                this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
                break;
            case 2:
                this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                break;
            case 3:
                if (this.addAutotoMaxFanSpeed2 || this.addAutotoMaxFanSpeed3) {
                    this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                    this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                    this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                    break;
                } else {
                    this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                    this.fanButtonMed.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                    this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                    break;
                }
                break;
            case 4:
                if (this.addAutotoMaxFanSpeed4) {
                    this.fanButtonQuiet.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                    this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                    this.fanButtonMed.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                    this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                    this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                    break;
                } else {
                    this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                    this.fanButtonMed.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                    this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                    this.fanButtonPowerful.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                    this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                    break;
                }
            default:
                this.fanButtonQuiet.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                this.fanButtonMed.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                break;
        }
        this.fanButtonMed.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
        if (e_ACFanMode == ACInfo.E_ACFanMode.Quiet) {
            this.fanButtonQuiet.setBackgroundResource(C0377R.C0378drawable.btnbase_left_s);
        } else if (e_ACFanMode == ACInfo.E_ACFanMode.Low) {
            if (this.supportedFanSpeed == 2) {
                this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left_s);
            } else if (this.supportedFanSpeed == 3) {
                this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left_s);
            } else if (this.supportedFanSpeed == 4) {
                if (this.addAutotoMaxFanSpeed4) {
                    this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
                } else {
                    this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left_s);
                }
            } else {
                this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            }
        } else if (e_ACFanMode == ACInfo.E_ACFanMode.Medium) {
            if (ExchData.getSelectedAC() == 0) {
                ac2 = ExchData.getAC1();
            } else {
                ac2 = ExchData.getAC2();
            }
            if (ac2.getBrand() == 11 || ac2.getBrand() == 12 || ac2.getBrand() == 2 || ac2.getBrand() == 14 || ac2.getBrand() == 4 || ac2.getBrand() == 7) {
                this.fanButtonMed.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            } else if (this.supportedFanSpeed == 2) {
                this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_right_s);
            } else if (this.addAutotoMaxFanSpeed3 && ac2.getBrand() != 10 && ac2.getBrand() != 5) {
                this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            } else {
                this.fanButtonMed.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            }
        } else if (e_ACFanMode == ACInfo.E_ACFanMode.High) {
            if (this.addAutotoMaxFanSpeed3 || this.addAutotoMaxFanSpeed2 || this.addAutotoMaxFanSpeed4) {
                this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            } else {
                this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_right_s);
            }
        } else if (e_ACFanMode == ACInfo.E_ACFanMode.Powerful) {
            if (this.supportedFanSpeed == 4) {
                this.fanButtonPowerful.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
            }
        } else if (e_ACFanMode == ACInfo.E_ACFanMode.Auto) {
            this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right_s);
        }
        if (this.supportedFanSpeed == 1) {
            this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
        }
    }

    @Override // au.com.polyaire.airtouch2.service.ActivityBase
    protected void onUpdateUI() {
        if (ExchData.getAC1().getControlMode() == ACInfo.E_ACControlMode.NotAvailable && ExchData.getAC2().getControlMode() == ACInfo.E_ACControlMode.NotAvailable && this.initUnavail) {
            this.initUnavail = false;
            Intent intent = new Intent(this, ZoneActivity.class);
            intent.addFlags(67108864);
            startActivity(intent);
        }
        if (ExchData.mConnection.isInternetMode()) {
            this.internetIcon.setVisibility(0);
        }
        this.row3Power.setVisibility(0);
        System.out.println("AC1 selected");
        ACInfo ac1 = ExchData.getAC1();
        if (ExchData.isDualACMode()) {
            this.nextGroup.setVisibility(0);
            if (ExchData.getSelectedAC() == 1) {
                ac1 = ExchData.getAC2();
                System.out.println("AC2 selected");
            }
        } else {
            this.nextGroup.setVisibility(4);
            ExchData.setSelectedAC(0);
        }
        this.acName.setText(ac1.getAcName());
        if (this.currentControlMode != ac1.getControlMode()) {
            changeControlMode(ac1.getControlMode());
            this.currentControlMode = ac1.getControlMode();
        }
        System.out.println("current control mode: " + this.currentControlMode);
        if (this.currentControlMode == ACInfo.E_ACControlMode.NotAvailable) {
            this.row3Power.setVisibility(4);
            this.timerIcon.setVisibility(4);
            this.programIcon.setVisibility(4);
            this.topSpill.setText("AC Control Unavailable");
        }
        if (this.currentControlMode == ACInfo.E_ACControlMode.NotAvailable || this.currentControlMode == ACInfo.E_ACControlMode.Basic) {
            this.setTemp.setText(String.valueOf(ExchData.getTemperature()));
            if (ExchData.isDisplayTempInTopSpill() && !ExchData.getTemperature().equals("")) {
                this.row7Temp.setVisibility(0);
            } else {
                this.row7Temp.setVisibility(4);
            }
        }
        if (this.currentControlMode == ACInfo.E_ACControlMode.Full) {
            if (ac1.getSetPoint() == 0) {
                this.row7Temp.setVisibility(4);
            } else {
                this.row7Temp.setVisibility(0);
                this.setTemp.setText(String.valueOf(ac1.getSetPoint()));
            }
            int brand = ac1.getBrand();
            if (brand == 10 || brand == 13) {
                this.modeButtonDry.setVisibility(8);
                this.modeTextDry.setVisibility(8);
            } else {
                this.modeButtonDry.setVisibility(0);
                this.modeTextDry.setVisibility(0);
            }
            int supportedFanSpeed = ac1.getSupportedFanSpeed();
            this.addAutotoMaxFanSpeed2 = false;
            this.addAutotoMaxFanSpeed3 = false;
            this.addAutotoMaxFanSpeed4 = false;
            if (ac1.getAcID() == 5) {
                if (3 == supportedFanSpeed) {
                    this.addAutotoMaxFanSpeed3 = true;
                } else {
                    this.addAutotoMaxFanSpeed2 = true;
                    supportedFanSpeed = 3;
                }
            }
            if (ac1.getBrand() == 4 || ac1.getBrand() == 7) {
                this.addAutotoMaxFanSpeed3 = true;
                supportedFanSpeed = 3;
            }
            if (ac1.getBrand() == 2) {
                if (supportedFanSpeed == 4) {
                    this.addAutotoMaxFanSpeed4 = true;
                } else {
                    this.addAutotoMaxFanSpeed3 = true;
                    supportedFanSpeed = 3;
                }
            }
            if (ac1.getBrand() == 14) {
                this.addAutotoMaxFanSpeed3 = true;
                supportedFanSpeed = 3;
            }
            if (ac1.getBrand() == 11) {
                this.addAutotoMaxFanSpeed3 = true;
                supportedFanSpeed = 3;
            }
            if (ac1.getBrand() == 11 && (ac1.getAcMode() == ACInfo.E_ACMode.Dry || ac1.getAcMode() == ACInfo.E_ACMode.Auto)) {
                this.addAutotoMaxFanSpeed3 = true;
                supportedFanSpeed = 1;
            }
            supportedFanSpeed = (ac1.getBrand() == 12 || ac1.getAcMode() == ACInfo.E_ACMode.Dry) ? 0 : 0;
            if (ac1.getBrand() == 12) {
                supportedFanSpeed = 3;
            }
            if (ac1.getBrand() == 13) {
                this.addAutotoMaxFanSpeed3 = true;
                supportedFanSpeed = 3;
            }
            if (ac1.getBrand() == 5 && ac1.getAcID() == 255) {
                this.addAutotoMaxFanSpeed2 = true;
                this.addAutotoMaxFanSpeed3 = false;
                supportedFanSpeed = 3;
            }
            if (ac1.getBrand() == 10) {
                this.addAutotoMaxFanSpeed2 = true;
                if (ExchData.isToshibaThreeFanSpeed()) {
                    this.addAutotoMaxFanSpeed3 = true;
                } else {
                    this.addAutotoMaxFanSpeed3 = false;
                }
                this.lastBrand = -1;
                supportedFanSpeed = 3;
            }
            if (this.supportedFanSpeed != supportedFanSpeed || brand != this.lastBrand) {
                this.lastBrand = brand;
                System.out.println("aaa:" + this.supportedFanSpeed + ':' + supportedFanSpeed);
                this.fanButtonQuiet.setVisibility(0);
                this.fanButtonQuiet.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                this.fanTextQuiet.setVisibility(0);
                this.fanButtonAuto.setVisibility(0);
                this.fanTextAuto.setVisibility(0);
                this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                this.fanButtonMed.setVisibility(0);
                this.fanTextMed.setVisibility(0);
                this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                switch (supportedFanSpeed) {
                    case 0:
                        this.fanButtonQuiet.setVisibility(8);
                        this.fanTextQuiet.setVisibility(8);
                        this.fanButtonLow.setVisibility(8);
                        this.fanTextLow.setVisibility(8);
                        this.fanButtonAuto.setVisibility(8);
                        this.fanTextAuto.setVisibility(8);
                        this.fanButtonMed.setVisibility(8);
                        this.fanTextMed.setVisibility(8);
                        this.fanButtonHigh.setVisibility(8);
                        this.fanTextHigh.setVisibility(8);
                        this.fanButtonPowerful.setVisibility(8);
                        this.fanTextPowerful.setVisibility(8);
                        break;
                    case 1:
                        this.fanButtonQuiet.setVisibility(8);
                        this.fanTextQuiet.setVisibility(8);
                        this.fanButtonLow.setVisibility(8);
                        this.fanTextLow.setVisibility(8);
                        this.fanButtonMed.setVisibility(8);
                        this.fanTextMed.setVisibility(8);
                        this.fanButtonHigh.setVisibility(8);
                        this.fanTextHigh.setVisibility(8);
                        this.fanButtonPowerful.setVisibility(8);
                        this.fanTextPowerful.setVisibility(8);
                        this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_mid_s);
                        this.fanTextAuto.setVisibility(0);
                        this.fanButtonAuto.setVisibility(0);
                        break;
                    case 2:
                        this.fanButtonQuiet.setVisibility(8);
                        this.fanTextQuiet.setVisibility(8);
                        this.fanButtonAuto.setVisibility(8);
                        this.fanTextAuto.setVisibility(8);
                        this.fanButtonMed.setVisibility(8);
                        this.fanTextMed.setVisibility(8);
                        this.fanButtonPowerful.setVisibility(8);
                        this.fanTextPowerful.setVisibility(8);
                        this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                        this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                        break;
                    case 3:
                        if (this.addAutotoMaxFanSpeed3) {
                            this.fanButtonQuiet.setVisibility(8);
                            this.fanTextQuiet.setVisibility(8);
                            this.fanButtonPowerful.setVisibility(8);
                            this.fanTextPowerful.setVisibility(8);
                            this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                            this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                            this.fanTextAuto.setVisibility(0);
                            this.fanButtonAuto.setVisibility(0);
                            this.fanTextLow.setVisibility(0);
                            this.fanButtonLow.setVisibility(0);
                            this.fanTextMed.setVisibility(0);
                            this.fanButtonMed.setVisibility(0);
                            this.fanTextHigh.setVisibility(0);
                            this.fanButtonHigh.setVisibility(0);
                            break;
                        } else if (this.addAutotoMaxFanSpeed2) {
                            this.fanButtonQuiet.setVisibility(8);
                            this.fanTextQuiet.setVisibility(8);
                            this.fanButtonPowerful.setVisibility(8);
                            this.fanTextPowerful.setVisibility(8);
                            this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                            this.fanButtonAuto.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                            this.fanTextAuto.setVisibility(0);
                            this.fanButtonAuto.setVisibility(0);
                            this.fanTextLow.setVisibility(0);
                            this.fanButtonLow.setVisibility(0);
                            this.fanTextMed.setVisibility(8);
                            this.fanButtonMed.setVisibility(8);
                            this.fanTextHigh.setVisibility(0);
                            this.fanButtonHigh.setVisibility(0);
                            break;
                        } else {
                            this.fanButtonQuiet.setVisibility(8);
                            this.fanTextQuiet.setVisibility(8);
                            this.fanButtonLow.setVisibility(0);
                            this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                            this.fanButtonMed.setVisibility(0);
                            this.fanButtonMed.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                            this.fanButtonAuto.setVisibility(8);
                            this.fanTextAuto.setVisibility(8);
                            this.fanButtonPowerful.setVisibility(8);
                            this.fanTextPowerful.setVisibility(8);
                            this.fanButtonHigh.setVisibility(0);
                            this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_right);
                            break;
                        }
                    case 4:
                        if (this.addAutotoMaxFanSpeed4) {
                            this.fanButtonQuiet.setVisibility(0);
                            this.fanTextQuiet.setVisibility(0);
                            this.fanButtonQuiet.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                            this.fanButtonPowerful.setVisibility(8);
                            this.fanTextPowerful.setVisibility(8);
                            break;
                        } else {
                            this.fanButtonQuiet.setVisibility(8);
                            this.fanTextQuiet.setVisibility(8);
                            this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_left);
                            break;
                        }
                    default:
                        this.fanButtonQuiet.setVisibility(0);
                        this.fanTextQuiet.setVisibility(0);
                        this.fanButtonAuto.setVisibility(0);
                        this.fanTextAuto.setVisibility(0);
                        this.fanButtonHigh.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                        this.fanButtonLow.setBackgroundResource(C0377R.C0378drawable.btnbase_mid);
                        break;
                }
                this.supportedFanSpeed = supportedFanSpeed;
            }
            if (ac1.getBrand() == 12) {
                setModeBackgroundWithoutAutoButton(ac1.getAcMode());
            } else {
                setModeBackground(ac1.getAcMode());
            }
            setFanBackground(ac1.getFanMode());
        }
        if (this.currentControlMode == ACInfo.E_ACControlMode.Basic || this.currentControlMode == ACInfo.E_ACControlMode.Full) {
            switch (ac1.getAcStatus()) {
                case ACOff:
                    this.acstatus.setImageResource(C0377R.C0378drawable.acoff);
                    break;
                case ACOn:
                    this.acstatus.setImageResource(C0377R.C0378drawable.acon);
                    break;
            }
            if (ac1.isHasProgram()) {
                if (ExchData.getProgramHasTimes()[ac1.getProgramNumber() - 1]) {
                    this.programIcon.setVisibility(0);
                    this.programIcon.setText("P " + ac1.getProgramNumber());
                } else {
                    this.programIcon.setVisibility(4);
                }
            } else {
                this.programIcon.setVisibility(4);
            }
            if (ac1.isHasTimer()) {
                this.timerIcon.setVisibility(0);
            } else {
                this.timerIcon.setVisibility(4);
            }
            System.out.println("dual ducted??" + ExchData.isDualDucted());
            if ((ExchData.getAC1().getControlMode() != ACInfo.E_ACControlMode.Full || ExchData.getAC2().getControlMode() != ACInfo.E_ACControlMode.Full) && !ExchData.isDualDucted()) {
                ac1 = ExchData.getAC1();
            }
            this.topSpill.setText("NORMAL");
            if (ExchData.isDisplayTempInTopSpill() && !ExchData.getTemperature().equals("")) {
                String str = "ROOM TEMP: " + ExchData.getTemperature() + " ℃";
                if (this.currentControlMode == ACInfo.E_ACControlMode.Full && ac1.isDisplayGatewayTemp() && ac1.getRoomTemp() > 0) {
                    str = "ROOM TEMP: " + ac1.getRoomTemp() + " ℃";
                }
                this.topSpill.setText(str);
            }
            if (ac1.isSpill()) {
                this.topSpill.setText("SPILL ACTIVATED");
            }
            if (ac1.isSafety()) {
                this.topSpill.setText("SAFETY IS ON");
            }
            int errorCode = ac1.getErrorCode();
            if (this.currentControlMode == ACInfo.E_ACControlMode.Full && errorCode != 0) {
                this.topSpill.setText("AC ERROR - CODE: " + String.format("%02X", Integer.valueOf(errorCode)));
            }
            if (this.currentControlMode == ACInfo.E_ACControlMode.Basic && ac1.isError()) {
                this.topSpill.setText("AC ERROR");
            }
        }
    }

    private void changeControlMode(ACInfo.E_ACControlMode e_ACControlMode) {
        TableRow tableRow = (TableRow) findViewById(C0377R.id.row4Text);
        TableRow tableRow2 = (TableRow) findViewById(C0377R.id.row5Buttons);
        TableRow tableRow3 = (TableRow) findViewById(C0377R.id.row6Text);
        LinearLayout linearLayout = (LinearLayout) findViewById(C0377R.id.tempControls);
        this.row7Temp = (TableRow) findViewById(C0377R.id.row7Temp);
        this.row8FanTitle = (TableRow) findViewById(C0377R.id.row8Text);
        this.row9FanButtons = (TableRow) findViewById(C0377R.id.row9Buttons);
        this.row10FanText = (TableRow) findViewById(C0377R.id.row10Text);
        switch (e_ACControlMode) {
            case NotAvailable:
                tableRow.setVisibility(4);
                tableRow2.setVisibility(8);
                tableRow3.setVisibility(4);
                linearLayout.setVisibility(8);
                this.row8FanTitle.setVisibility(4);
                this.row9FanButtons.setVisibility(4);
                this.row10FanText.setVisibility(4);
                this.setTempMinus.setVisibility(4);
                this.setTempPlus.setVisibility(4);
                this.setpointText.setText("ROOM TEMP");
                return;
            case Basic:
                this.acstatus.setVisibility(0);
                tableRow.setVisibility(4);
                tableRow2.setVisibility(8);
                tableRow3.setVisibility(4);
                linearLayout.setVisibility(8);
                this.row8FanTitle.setVisibility(4);
                this.row9FanButtons.setVisibility(4);
                this.row10FanText.setVisibility(4);
                this.setTempMinus.setVisibility(4);
                this.setTempPlus.setVisibility(4);
                this.setpointText.setText("ROOM TEMP");
                return;
            case Full:
                this.acstatus.setVisibility(0);
                tableRow.setVisibility(0);
                tableRow2.setVisibility(0);
                tableRow3.setVisibility(0);
                linearLayout.setVisibility(0);
                this.row8FanTitle.setVisibility(0);
                this.row9FanButtons.setVisibility(0);
                this.row10FanText.setVisibility(0);
                this.setTempMinus.setVisibility(0);
                this.setTempPlus.setVisibility(0);
                this.setpointText.setText("SET POINT");
                return;
            default:
                return;
        }
    }

    private void setEventListener() {
        this.acstatus.setOnClickListener(new MasterListener(C0377R.id.acstatus));
        this.nextGroup.setOnClickListener(new MasterListener(C0377R.id.nextGroup));
        this.modeButtonHeat.setOnClickListener(new MasterListener(C0377R.id.modeButtonHeat));
        this.modeButtonCool.setOnClickListener(new MasterListener(C0377R.id.modeButtonCool));
        this.modeButtonFan.setOnClickListener(new MasterListener(C0377R.id.modeButtonFan));
        this.modeButtonDry.setOnClickListener(new MasterListener(C0377R.id.modeButtonDry));
        this.modeButtonAuto.setOnClickListener(new MasterListener(C0377R.id.modeButtonAuto));
        this.fanButtonQuiet.setOnClickListener(new MasterListener(C0377R.id.fanButtonQuiet));
        this.fanButtonLow.setOnClickListener(new MasterListener(C0377R.id.fanButtonLow));
        this.fanButtonMed.setOnClickListener(new MasterListener(C0377R.id.fanButtonMed));
        this.fanButtonHigh.setOnClickListener(new MasterListener(C0377R.id.fanButtonHigh));
        this.fanButtonPowerful.setOnClickListener(new MasterListener(C0377R.id.fanButtonPowerful));
        this.fanButtonAuto.setOnClickListener(new MasterListener(C0377R.id.fanButtonAuto));
        this.setTempPlus.setOnClickListener(new MasterListener(C0377R.id.plusTemp));
        this.setTempMinus.setOnClickListener(new MasterListener(C0377R.id.minusTemp));
    }

    private void declareViews() {
        System.out.println("Wifi declareViews");
        this.row3Power = (TableRow) findViewById(C0377R.id.row3Power);
        this.acName = (TextView) findViewById(C0377R.id.ACName);
        this.acstatus = (ImageView) findViewById(C0377R.id.acstatus);
        this.nextGroup = (ImageView) findViewById(C0377R.id.nextGroup);
        this.setpointText = (TextView) findViewById(C0377R.id.setpointText);
        this.modeButtonHeat = (ImageView) findViewById(C0377R.id.modeButtonHeat);
        this.modeButtonCool = (ImageView) findViewById(C0377R.id.modeButtonCool);
        this.modeButtonFan = (ImageView) findViewById(C0377R.id.modeButtonFan);
        this.modeButtonDry = (ImageView) findViewById(C0377R.id.modeButtonDry);
        this.modeButtonAuto = (ImageView) findViewById(C0377R.id.modeButtonAuto);
        this.modeTextDry = (TextView) findViewById(C0377R.id.modeTextDry);
        this.modeTextAuto = (TextView) findViewById(C0377R.id.modeTextAuto);
        this.fanButtonQuiet = (ImageView) findViewById(C0377R.id.fanButtonQuiet);
        this.fanButtonLow = (ImageView) findViewById(C0377R.id.fanButtonLow);
        this.fanButtonMed = (ImageView) findViewById(C0377R.id.fanButtonMed);
        this.fanButtonHigh = (ImageView) findViewById(C0377R.id.fanButtonHigh);
        this.fanButtonPowerful = (ImageView) findViewById(C0377R.id.fanButtonPowerful);
        this.fanButtonAuto = (ImageView) findViewById(C0377R.id.fanButtonAuto);
        this.fanTextQuiet = (TextView) findViewById(C0377R.id.fanTextQuiet);
        this.fanTextLow = (TextView) findViewById(C0377R.id.fanTextLow);
        this.fanTextHigh = (TextView) findViewById(C0377R.id.fanTextHigh);
        this.fanTextMed = (TextView) findViewById(C0377R.id.fanTextMed);
        this.fanTextAuto = (TextView) findViewById(C0377R.id.fanTextAuto);
        this.fanTextPowerful = (TextView) findViewById(C0377R.id.fanTextPowerful);
        this.soundPool = new SoundPool(3, 3, 0);
        this.soundPool.load(this, C0377R.raw.keypressstandard, 1);
        this.internetIcon = (TextView) findViewById(C0377R.id.internetIcon);
        this.topSpill = (TextView) findViewById(C0377R.id.topspill);
        this.setTemp = (TextView) findViewById(C0377R.id.setTemp);
        this.timerIcon = (LinearLayout) findViewById(C0377R.id.timerIcon);
        this.programIcon = (TextView) findViewById(C0377R.id.programIcon);
        this.setTempPlus = (ImageView) findViewById(C0377R.id.plusTemp);
        this.setTempMinus = (ImageView) findViewById(C0377R.id.minusTemp);
    }

    private ACInfo.E_ACFanMode changeModeForFujitsu41(ACInfo.E_ACFanMode e_ACFanMode) {
        ACInfo.E_ACFanMode e_ACFanMode2 = ACInfo.E_ACFanMode.Auto;
        if (e_ACFanMode == ACInfo.E_ACFanMode.Medium) {
            e_ACFanMode2 = ACInfo.E_ACFanMode.Low;
        }
        if (e_ACFanMode == ACInfo.E_ACFanMode.High) {
            e_ACFanMode2 = ACInfo.E_ACFanMode.Medium;
        }
        if (e_ACFanMode == ACInfo.E_ACFanMode.Quiet) {
            e_ACFanMode2 = ACInfo.E_ACFanMode.High;
        }
        return e_ACFanMode == ACInfo.E_ACFanMode.Low ? ACInfo.E_ACFanMode.Quiet : e_ACFanMode2;
    }
}
