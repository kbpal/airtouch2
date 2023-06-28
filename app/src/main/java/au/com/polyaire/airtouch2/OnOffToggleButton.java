package au.com.polyaire.airtouch2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* loaded from: classes.dex */
public class OnOffToggleButton extends RelativeLayout {
    private Boolean _crossfadeRunning;
    private ObjectAnimator _oaLeft;
    private ObjectAnimator _oaRight;
    View background_oval_off;
    View background_oval_on;
    View background_oval_turbo;
    int circleMoveDistance;
    int dimen;
    TextView textView;
    View toggleCircle;
    int toggleStatus;

    public OnOffToggleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.toggleStatus = 0;
        this._crossfadeRunning = false;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0377R.layout.togglebuton, (ViewGroup) this, true);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0377R.styleable.OnOffToggleButton);
        String string = obtainStyledAttributes.getString(0);
        String string2 = obtainStyledAttributes.getString(1);
        obtainStyledAttributes.recycle();
        this.background_oval_off = findViewById(C0377R.id.background_oval_off);
        this.background_oval_on = findViewById(C0377R.id.background_oval_on);
        this.background_oval_turbo = findViewById(C0377R.id.background_oval_turbo);
        this.toggleCircle = findViewById(C0377R.id.toggleCircle);
        this.textView = (TextView) findViewById(C0377R.id.text);
        if (string != null) {
            this.background_oval_off.setBackground(getResources().getDrawable(getResources().getIdentifier(string, "drawable", context.getPackageName())));
        }
        if (string2 != null) {
            this.background_oval_on.setBackground(getResources().getDrawable(getResources().getIdentifier(string2, "drawable", context.getPackageName())));
        }
        this.dimen = getResources().getDimensionPixelSize(C0377R.dimen.settings_toggle_width);
        this.circleMoveDistance = this.dimen / 2;
        this._oaLeft = ObjectAnimator.ofFloat(this.toggleCircle, "x", this.circleMoveDistance, 0.0f).setDuration(300L);
        this._oaRight = ObjectAnimator.ofFloat(this.toggleCircle, "x", 0.0f, this.circleMoveDistance).setDuration(300L);
        setState();
    }

    public OnOffToggleButton(Context context) {
        this(context, null);
    }

    public void updateState(int i) {
        this.toggleStatus = i;
        setState();
    }

    public void swipeLeft() {
        if (this._oaLeft.isRunning() || this._oaRight.isRunning() || this._crossfadeRunning.booleanValue()) {
            return;
        }
        switch (this.toggleStatus) {
            case 0:
                this.toggleStatus = (this.toggleStatus + 1) % 3;
                this._oaLeft.start();
                _crossfadeViews(this.background_oval_off, this.background_oval_on);
                changeToggleText("ON");
                return;
            case 1:
            default:
                return;
        }
    }

    public void swipeRight() {
        if (this._oaLeft.isRunning() || this._oaRight.isRunning() || this._crossfadeRunning.booleanValue()) {
            return;
        }
        switch (this.toggleStatus) {
            case 0:
            default:
                return;
            case 1:
                this.toggleStatus = (this.toggleStatus + 1) % 3;
                this._oaLeft.start();
                _crossfadeViews(this.background_oval_on, this.background_oval_turbo);
                changeToggleText("TURBO");
                return;
            case 2:
                this.toggleStatus = (this.toggleStatus + 1) % 3;
                this._oaRight.start();
                _crossfadeViews(this.background_oval_turbo, this.background_oval_off);
                changeToggleText("OFF");
                return;
        }
    }

    private void setState() {
        if (isInEditMode()) {
            return;
        }
        if (this.toggleStatus == 0) {
            this.toggleCircle.setX(this.circleMoveDistance);
            this.background_oval_off.setVisibility(0);
            this.background_oval_on.setVisibility(8);
            this.background_oval_turbo.setVisibility(8);
            changeToggleText("OFF");
        } else if (this.toggleStatus == 1) {
            this.toggleCircle.setX(0.0f);
            this.background_oval_off.setVisibility(8);
            this.background_oval_on.setVisibility(0);
            this.background_oval_turbo.setVisibility(8);
            changeToggleText("ON");
        } else if (this.toggleStatus == 2) {
            this.toggleCircle.setX(0.0f);
            this.background_oval_off.setVisibility(8);
            this.background_oval_on.setVisibility(8);
            this.background_oval_turbo.setVisibility(0);
            changeToggleText("TURBO");
        }
    }

    private void changeToggleText(String str) {
        if (str.equals("OFF")) {
            this.textView.setText("OFF");
            this.textView.setX(10.0f);
            this.textView.setTextSize(10.0f);
        } else if (str.equals("ON")) {
            this.textView.setText("ON");
            this.textView.setX(54.0f);
            this.textView.setTextSize(10.0f);
        } else if (str.equals("TURBO")) {
            this.textView.setText("TURBO");
            this.textView.setX(46.0f);
            this.textView.setTextSize(8.0f);
        }
    }

    private void _crossfadeViews(final View view, View view2) {
        this._crossfadeRunning = true;
        view2.setAlpha(0.0f);
        view2.setVisibility(0);
        view2.animate().alpha(1.0f).setDuration(300L).setListener(null);
        view.animate().alpha(0.0f).setDuration(300L).setListener(new AnimatorListenerAdapter() { // from class: au.com.polyaire.airtouch2.OnOffToggleButton.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
                OnOffToggleButton.this._crossfadeRunning = false;
            }
        });
    }
}
