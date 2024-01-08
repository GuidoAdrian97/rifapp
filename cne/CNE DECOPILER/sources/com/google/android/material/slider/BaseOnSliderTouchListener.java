package com.google.android.material.slider;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo
public interface BaseOnSliderTouchListener {
    void onStartTrackingTouch(@NonNull Object obj);

    void onStopTrackingTouch(@NonNull Object obj);
}
