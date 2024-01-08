package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
final class zzc implements DynamiteModule.VersionPolicy {
    zzc() {
    }

    public final DynamiteModule.VersionPolicy.zza zza(Context context, String str, DynamiteModule.VersionPolicy.zzb zzb) {
        DynamiteModule.VersionPolicy.zza zza = new DynamiteModule.VersionPolicy.zza();
        int zza2 = zzb.zza(context, str, true);
        zza.zzjh = zza2;
        if (zza2 != 0) {
            zza.zzji = 1;
        } else {
            int localVersion = zzb.getLocalVersion(context, str);
            zza.zzjg = localVersion;
            if (localVersion != 0) {
                zza.zzji = -1;
            }
        }
        return zza;
    }
}
