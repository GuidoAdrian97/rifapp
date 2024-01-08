package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzbx extends zza implements IUiSettingsDelegate {
    zzbx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public final void setZoomControlsEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(1, zza);
    }

    public final void setCompassEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(2, zza);
    }

    public final void setMyLocationButtonEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(3, zza);
    }

    public final void setScrollGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(4, zza);
    }

    public final void setZoomGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(5, zza);
    }

    public final void setTiltGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(6, zza);
    }

    public final void setRotateGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(7, zza);
    }

    public final void setAllGesturesEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(8, zza);
    }

    public final boolean isZoomControlsEnabled() {
        Parcel zza = zza(9, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isCompassEnabled() {
        Parcel zza = zza(10, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isMyLocationButtonEnabled() {
        Parcel zza = zza(11, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isScrollGesturesEnabled() {
        Parcel zza = zza(12, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isZoomGesturesEnabled() {
        Parcel zza = zza(13, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isTiltGesturesEnabled() {
        Parcel zza = zza(14, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isRotateGesturesEnabled() {
        Parcel zza = zza(15, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void setIndoorLevelPickerEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(16, zza);
    }

    public final boolean isIndoorLevelPickerEnabled() {
        Parcel zza = zza(17, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void setMapToolbarEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(18, zza);
    }

    public final boolean isMapToolbarEnabled() {
        Parcel zza = zza(19, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(20, zza);
    }
}
