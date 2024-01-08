package androidx.core.util;

import android.annotation.SuppressLint;

public interface Predicate {
    @SuppressLint({"UnknownNullness"})
    boolean test(Object obj);
}
