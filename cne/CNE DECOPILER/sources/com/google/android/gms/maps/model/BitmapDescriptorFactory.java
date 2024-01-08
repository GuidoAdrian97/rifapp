package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zze;

public abstract class BitmapDescriptorFactory {
    private static zze zzcm;

    public static void zza(zze zze) {
        if (zzcm == null) {
            zzcm = (zze) Preconditions.checkNotNull(zze);
        }
    }
}
