package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

abstract class DrawingDelegate {
    protected DrawableWithAnimatedVisibilityChange drawable;
    BaseProgressIndicatorSpec spec;

    /* access modifiers changed from: package-private */
    public abstract void adjustCanvas(@NonNull Canvas canvas, @FloatRange float f);

    /* access modifiers changed from: package-private */
    public abstract void fillIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange float f, @FloatRange float f2, @ColorInt int i);

    /* access modifiers changed from: package-private */
    public abstract void fillTrack(@NonNull Canvas canvas, @NonNull Paint paint);

    /* access modifiers changed from: package-private */
    public abstract int getPreferredHeight();

    /* access modifiers changed from: package-private */
    public abstract int getPreferredWidth();

    public DrawingDelegate(BaseProgressIndicatorSpec baseProgressIndicatorSpec) {
        this.spec = baseProgressIndicatorSpec;
    }

    /* access modifiers changed from: protected */
    public void registerDrawable(@NonNull DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
        this.drawable = drawableWithAnimatedVisibilityChange;
    }

    /* access modifiers changed from: package-private */
    public void validateSpecAndAdjustCanvas(@NonNull Canvas canvas, @FloatRange float f) {
        this.spec.validateSpec();
        adjustCanvas(canvas, f);
    }
}
