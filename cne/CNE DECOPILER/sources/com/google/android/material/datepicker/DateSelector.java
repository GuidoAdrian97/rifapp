package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import java.util.Collection;

@RestrictTo
public interface DateSelector extends Parcelable {
    @StyleRes
    int getDefaultThemeResId(Context context);

    @StringRes
    int getDefaultTitleResId();

    @NonNull
    Collection getSelectedDays();

    @NonNull
    Collection getSelectedRanges();

    @Nullable
    Object getSelection();

    @NonNull
    String getSelectionDisplayString(Context context);

    boolean isSelectionComplete();

    @NonNull
    View onCreateTextInputView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle, @NonNull CalendarConstraints calendarConstraints, @NonNull OnSelectionChangedListener onSelectionChangedListener);

    void select(long j);

    void setSelection(@NonNull Object obj);
}
