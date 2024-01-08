package androidx.core.view;

import android.view.ViewGroup;

public abstract class ViewGroupCompat {
    public static boolean isTransitionGroup(ViewGroup viewGroup) {
        return viewGroup.isTransitionGroup();
    }
}
