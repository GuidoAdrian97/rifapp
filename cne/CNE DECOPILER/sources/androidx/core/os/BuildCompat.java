package androidx.core.os;

import android.os.Build;

public abstract class BuildCompat {
    public static boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30;
    }
}
