package com.google.android.material.transition.platform;

import androidx.annotation.RequiresApi;

@RequiresApi
interface FadeModeEvaluator {
    FadeModeResult evaluate(float f, float f2, float f3, float f4);
}
