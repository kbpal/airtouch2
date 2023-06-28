package au.com.polyaire.airtouch2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.Spinner;

/* loaded from: classes.dex */
public class NDSpinner extends Spinner {
    AdapterView.OnItemSelectedListener listener;

    public NDSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.AbsSpinner, android.widget.AdapterView
    public void setSelection(int i) {
        super.setSelection(i);
        this.listener.onItemSelected(this, getSelectedView(), i, getSelectedItemId());
    }

    @Override // android.widget.AdapterView
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.listener = onItemSelectedListener;
    }
}
