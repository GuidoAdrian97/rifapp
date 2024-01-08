package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
final class zzf implements DynamiteModule.VersionPolicy {
    zzf() {
    }

    public final DynamiteModule.VersionPolicy.zza zza(Context context, String str, DynamiteModule.VersionPolicy.zzb zzb) {
        DynamiteModule.VersionPolicy.zza zza = new DynamiteModule.VersionPolicy.zza();
        int localVersion = zzb.getLocalVersion(context, str);
        zza.zzjg = localVersion;
        if (localVersion != 0) {
            zza.zzjh = zzb.zza(context, str, false);
        } else {
            zza.zzjh = zzb.zza(context, str, true);
        }
        int i = zza.zzjg;
        if (i == 0 && zza.zzjh == 0) {
            zza.zzji = 0;
        } else if (zza.zzjh >= i) {
            zza.zzji = 1;
        } else {
            zza.zzji = -1;
        }
        return zza;
    }
}
