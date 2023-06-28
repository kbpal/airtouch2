package au.com.polyaire.airtouch2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes.dex */
public class SlipButton extends View implements View.OnTouchListener {
    private Rect Btn_Off;
    private Rect Btn_On;
    private OnChangedListener ChgLsn;
    public float DownX;
    public boolean NowChoose;
    public float NowX;
    private boolean OnSlip;
    private Bitmap bg_off;
    private Bitmap bg_on;
    private boolean enabled;
    public boolean flag;
    private boolean isChgLsnOn;
    private Bitmap slip_btn;
    private String strName;

    public SlipButton(Context context) {
        super(context);
        this.enabled = true;
        this.flag = false;
        this.NowChoose = false;
        this.OnSlip = false;
        this.DownX = 0.0f;
        this.NowX = 0.0f;
        this.isChgLsnOn = false;
        init();
    }

    public SlipButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.enabled = true;
        this.flag = false;
        this.NowChoose = false;
        this.OnSlip = false;
        this.DownX = 0.0f;
        this.NowX = 0.0f;
        this.isChgLsnOn = false;
        init();
    }

    public void setChecked(boolean z) {
        if (z) {
            this.flag = true;
            this.NowChoose = true;
            this.NowX = 80.0f;
            return;
        }
        this.flag = false;
        this.NowChoose = false;
        this.NowX = 0.0f;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (z) {
            this.enabled = true;
        } else {
            this.enabled = false;
        }
    }

    private void init() {
        this.bg_on = BitmapFactory.decodeResource(getResources(), C0377R.C0378drawable.on_btn);
        this.bg_off = BitmapFactory.decodeResource(getResources(), C0377R.C0378drawable.off_btn);
        this.slip_btn = BitmapFactory.decodeResource(getResources(), C0377R.C0378drawable.white_btn);
        this.Btn_On = new Rect(0, 0, this.slip_btn.getWidth(), this.slip_btn.getHeight());
        this.Btn_Off = new Rect(this.bg_off.getWidth() - this.slip_btn.getWidth(), 0, this.bg_off.getWidth(), this.slip_btn.getHeight());
        setOnTouchListener(this);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        if (this.flag) {
            this.NowX = 80.0f;
            this.flag = false;
        }
        if (this.NowX < this.bg_on.getWidth() / 2) {
            canvas.drawBitmap(this.bg_off, matrix, paint);
        } else {
            canvas.drawBitmap(this.bg_on, matrix, paint);
        }
        if (this.OnSlip) {
            if (this.NowX >= this.bg_on.getWidth()) {
                f = this.bg_on.getWidth() - (this.slip_btn.getWidth() / 2);
            } else {
                f = this.NowX - (this.slip_btn.getWidth() / 2);
            }
        } else if (this.NowChoose) {
            f = this.Btn_Off.left;
        } else {
            f = this.Btn_On.left;
        }
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > this.bg_on.getWidth() - this.slip_btn.getWidth()) {
            f = this.bg_on.getWidth() - this.slip_btn.getWidth();
        }
        canvas.drawBitmap(this.slip_btn, f, 0.0f, paint);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.enabled) {
            switch (motionEvent.getAction()) {
                case 0:
                    if (motionEvent.getX() <= this.bg_on.getWidth() && motionEvent.getY() <= this.bg_on.getHeight()) {
                        this.OnSlip = true;
                        this.DownX = motionEvent.getX();
                        this.NowX = this.DownX;
                        break;
                    } else {
                        return false;
                    }
                case 1:
                    this.OnSlip = false;
                    boolean z = this.NowChoose;
                    if (motionEvent.getX() >= this.bg_on.getWidth() / 2) {
                        this.NowChoose = true;
                    } else {
                        this.NowChoose = false;
                    }
                    if (this.isChgLsnOn && z != this.NowChoose) {
                        this.ChgLsn.OnChanged(this.strName, this.NowChoose);
                        break;
                    }
                    break;
                case 2:
                    this.NowX = motionEvent.getX();
                    break;
            }
            invalidate();
            return true;
        }
        return false;
    }

    public void SetOnChangedListener(String str, OnChangedListener onChangedListener) {
        this.strName = str;
        this.isChgLsnOn = true;
        this.ChgLsn = onChangedListener;
    }
}
