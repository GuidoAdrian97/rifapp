package com.google.android.material.animation;

import android.view.View;

public interface TransformationCallback {
    void onScaleChanged(View view);

    void onTranslationChanged(View view);
}
