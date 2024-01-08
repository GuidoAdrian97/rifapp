package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
final class zzg implements DynamiteModule.VersionPolicy {
    zzg() {
    }

    public final DynamiteModule.VersionPolicy.zza zza(Context context, String str, DynamiteModule.VersionPolicy.zzb zzb) {
        DynamiteModule.VersionPolicy.zza zza = new DynamiteModule.VersionPolicy.zza();
        zza.zzjg = zzb.getLocalVersion(context, str);
        int zza2 = zzb.zza(context, str, true);
        zza.zzjh = zza2;
        int i = zza.zzjg;
        if (i == 0 && zza2 == 0) {
            zza.zzji = 0;
        } else if (zza2 >= i) {
            zza.zzji = 1;
        } else {
            zza.zzji = -1;
        }
        return zza;
    }
}
