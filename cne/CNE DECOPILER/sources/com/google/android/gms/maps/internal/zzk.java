package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzk extends zza implements IMapViewDelegate {
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    public final void onCreate(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle);
        zzb(2, zza);
    }

    public final void onResume() {
        zzb(3, zza());
    }

    public final void onPause() {
        zzb(4, zza());
    }

    public final void onDestroy() {
        zzb(5, zza());
    }

    public final void onLowMemory() {
        zzb(6, zza());
    }

    public final void onSaveInstanceState(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle);
        Parcel zza2 = zza(7, zza);
        if (zza2.readInt() != 0) {
            bundle.readFromParcel(zza2);
        }
        zza2.recycle();
    }

    public final IObjectWrapper getView() {
        Parcel zza = zza(8, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }

    public final void getMapAsync(zzap zzap) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzap);
        zzb(9, zza);
    }

    public final void onEnterAmbient(Bundle bundle) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) bundle);
        zzb(10, zza);
    }

    public final void onExitAmbient() {
        zzb(11, zza());
    }

    public final void onStart() {
        zzb(12, zza());
    }

    public final void onStop() {
        zzb(13, zza());
    }
}
