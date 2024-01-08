package com.google.android.material.resources;

import android.graphics.Typeface;
import androidx.annotation.RestrictTo;

@RestrictTo
public abstract class TextAppearanceFontCallback {
    public abstract void onFontRetrievalFailed(int i);

    public abstract void onFontRetrieved(Typeface typeface, boolean z);
}
