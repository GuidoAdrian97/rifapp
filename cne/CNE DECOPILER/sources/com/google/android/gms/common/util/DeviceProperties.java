package com.google.android.gms.common.util;

import android.content.Context;
import android.os.Build;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public abstract class DeviceProperties {
    private static Boolean zzhd;
    private static Boolean zzhe;
    private static Boolean zzhg;

    public static boolean isWearable(Context context) {
        if (zzhd == null) {
            zzhd = Boolean.valueOf(PlatformVersion.isAtLeastKitKatWatch() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return zzhd.booleanValue();
    }

    public static boolean isWearableWithoutPlayStore(Context context) {
        if (!isWearable(context)) {
            return false;
        }
        if (PlatformVersion.isAtLeastN()) {
            return zzf(context) && !PlatformVersion.isAtLeastO();
        }
        return true;
    }

    public static boolean isSidewinder(Context context) {
        return zzf(context);
    }

    private static boolean zzf(Context context) {
        if (zzhe == null) {
            zzhe = Boolean.valueOf(PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return zzhe.booleanValue();
    }

    public static boolean zzg(Context context) {
        if (zzhg == null) {
            zzhg = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded"));
        }
        return zzhg.booleanValue();
    }

    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }
}
