package com.google.android.material.slider;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo
public interface BaseOnChangeListener {
    void onValueChange(@NonNull Object obj, float f, boolean z);
}
