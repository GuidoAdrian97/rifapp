package com.google.android.gms.common.internal;

import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class GoogleApiAvailabilityCache {
    private final SparseIntArray zapd = new SparseIntArray();
    private GoogleApiAvailabilityLight zape;

    public GoogleApiAvailabilityCache(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.zape = googleApiAvailabilityLight;
    }
}
