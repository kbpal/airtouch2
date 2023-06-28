package com.google.android.gms.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

/* loaded from: classes.dex */
public final class zae extends Drawable implements Drawable.Callback {
    private int mAlpha;
    private int mFrom;
    private boolean zamy;
    private int zang;
    private long zanh;
    private int zani;
    private int zanj;
    private int zank;
    private boolean zanl;
    private zai zanm;
    private Drawable zann;
    private Drawable zano;
    private boolean zanp;
    private boolean zanq;
    private boolean zanr;
    private int zans;

    public zae(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zag.zant : drawable;
        this.zann = drawable;
        drawable.setCallback(this);
        zai zaiVar = this.zanm;
        zaiVar.zanv = drawable.getChangingConfigurations() | zaiVar.zanv;
        drawable2 = drawable2 == null ? zag.zant : drawable2;
        this.zano = drawable2;
        drawable2.setCallback(this);
        zai zaiVar2 = this.zanm;
        zaiVar2.zanv = drawable2.getChangingConfigurations() | zaiVar2.zanv;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zae(zai zaiVar) {
        this.zang = 0;
        this.zanj = 255;
        this.mAlpha = 0;
        this.zamy = true;
        this.zanm = new zai(zaiVar);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zanm.mChangingConfigurations | this.zanm.zanv;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.mAlpha == this.zanj) {
            this.mAlpha = i;
        }
        this.zanj = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.zann.setColorFilter(colorFilter);
        this.zano.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.zann.getIntrinsicWidth(), this.zano.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.zann.getIntrinsicHeight(), this.zano.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        this.zann.setBounds(rect);
        this.zano.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (canConstantState()) {
            this.zanm.mChangingConfigurations = getChangingConfigurations();
            return this.zanm;
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if (!this.zanr) {
            this.zans = Drawable.resolveOpacity(this.zann.getOpacity(), this.zano.getOpacity());
            this.zanr = true;
        }
        return this.zans;
    }

    private final boolean canConstantState() {
        if (!this.zanp) {
            this.zanq = (this.zann.getConstantState() == null || this.zano.getConstantState() == null) ? false : true;
            this.zanp = true;
        }
        return this.zanq;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.zanl && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zann.mutate();
            this.zano.mutate();
            this.zanl = true;
        }
        return this;
    }

    public final Drawable zacf() {
        return this.zano;
    }

    public final void startTransition(int i) {
        this.mFrom = 0;
        this.zani = this.zanj;
        this.mAlpha = 0;
        this.zank = 250;
        this.zang = 1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        switch (this.zang) {
            case 1:
                this.zanh = SystemClock.uptimeMillis();
                this.zang = 2;
                r1 = false;
                break;
            case 2:
                if (this.zanh >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zanh)) / this.zank;
                    r1 = uptimeMillis >= 1.0f;
                    if (r1) {
                        this.zang = 0;
                    }
                    this.mAlpha = (int) ((this.zani * Math.min(uptimeMillis, 1.0f)) + 0.0f);
                    break;
                }
                break;
        }
        int i = this.mAlpha;
        boolean z = this.zamy;
        Drawable drawable = this.zann;
        Drawable drawable2 = this.zano;
        if (r1) {
            if (!z || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zanj) {
                drawable2.setAlpha(this.zanj);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z) {
            drawable.setAlpha(this.zanj - i);
        }
        drawable.draw(canvas);
        if (z) {
            drawable.setAlpha(this.zanj);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zanj);
        }
        invalidateSelf();
    }
}
