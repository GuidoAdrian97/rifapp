package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ViewModelStore {
    private final HashMap mMap = new HashMap();

    /* access modifiers changed from: package-private */
    public final void put(String str, ViewModel viewModel) {
        ViewModel viewModel2 = (ViewModel) this.mMap.put(str, viewModel);
        if (viewModel2 != null) {
            viewModel2.onCleared();
        }
    }

    /* access modifiers changed from: package-private */
    public final ViewModel get(String str) {
        return (ViewModel) this.mMap.get(str);
    }

    /* access modifiers changed from: package-private */
    public Set keys() {
        return new HashSet(this.mMap.keySet());
    }

    public final void clear() {
        for (ViewModel clear : this.mMap.values()) {
            clear.clear();
        }
        this.mMap.clear();
    }
}
