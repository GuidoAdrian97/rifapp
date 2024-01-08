package com.google.android.material.datepicker;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo
public abstract class OnSelectionChangedListener {
    public void onIncompleteSelectionChanged() {
    }

    public abstract void onSelectionChanged(@NonNull Object obj);
}
