package com.google.android.gms.common.wrappers;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class PackageManagerWrapper {
    private final Context zzil;

    public PackageManagerWrapper(Context context) {
        this.zzil = context;
    }

    public ApplicationInfo getApplicationInfo(String str, int i) {
        return this.zzil.getPackageManager().getApplicationInfo(str, i);
    }

    public PackageInfo getPackageInfo(String str, int i) {
        return this.zzil.getPackageManager().getPackageInfo(str, i);
    }

    public final boolean zzb(int i, String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            try {
                ((AppOpsManager) this.zzil.getSystemService("appops")).checkPackage(i, str);
                return true;
            } catch (SecurityException unused) {
                return false;
            }
        } else {
            String[] packagesForUid = this.zzil.getPackageManager().getPackagesForUid(i);
            if (!(str == null || packagesForUid == null)) {
                for (String equals : packagesForUid) {
                    if (str.equals(equals)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public CharSequence getApplicationLabel(String str) {
        return this.zzil.getPackageManager().getApplicationLabel(this.zzil.getPackageManager().getApplicationInfo(str, 0));
    }
}
