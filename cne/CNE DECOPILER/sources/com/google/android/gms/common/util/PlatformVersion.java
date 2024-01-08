package com.google.android.gms.common.util;

import android.os.Build;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public abstract class PlatformVersion {
    public static boolean isAtLeastIceCreamSandwich() {
        return true;
    }

    public static boolean isAtLeastJellyBean() {
        return true;
    }

    public static boolean isAtLeastJellyBeanMR2() {
        return true;
    }

    public static boolean isAtLeastKitKat() {
        return true;
    }

    public static boolean isAtLeastKitKatWatch() {
        return true;
    }

    public static boolean isAtLeastLollipop() {
        return true;
    }

    public static boolean isAtLeastN() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= 26;
    }
}
