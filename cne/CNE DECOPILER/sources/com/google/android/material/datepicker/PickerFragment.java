package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

abstract class PickerFragment extends Fragment {
    protected final LinkedHashSet onSelectionChangedListeners = new LinkedHashSet();

    /* access modifiers changed from: package-private */
    public abstract DateSelector getDateSelector();

    PickerFragment() {
    }

    /* access modifiers changed from: package-private */
    public boolean addOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        return this.onSelectionChangedListeners.add(onSelectionChangedListener);
    }

    /* access modifiers changed from: package-private */
    public boolean removeOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        return this.onSelectionChangedListeners.remove(onSelectionChangedListener);
    }

    /* access modifiers changed from: package-private */
    public void clearOnSelectionChangedListeners() {
        this.onSelectionChangedListeners.clear();
    }
}
