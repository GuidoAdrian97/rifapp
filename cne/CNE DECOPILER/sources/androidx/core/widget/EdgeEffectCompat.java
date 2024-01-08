package androidx.core.widget;

import android.widget.EdgeEffect;

public abstract class EdgeEffectCompat {
    public static void onPull(EdgeEffect edgeEffect, float f, float f2) {
        edgeEffect.onPull(f, f2);
    }
}
