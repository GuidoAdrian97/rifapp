package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public final class zzv extends zza implements zzt {
    zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    public final void remove() {
        zzb(1, zza());
    }

    public final String getId() {
        Parcel zza = zza(2, zza());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final void setPosition(LatLng latLng) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) latLng);
        zzb(3, zza);
    }

    public final LatLng getPosition() {
        Parcel zza = zza(4, zza());
        LatLng latLng = (LatLng) zzc.zza(zza, LatLng.CREATOR);
        zza.recycle();
        return latLng;
    }

    public final void setTitle(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(5, zza);
    }

    public final String getTitle() {
        Parcel zza = zza(6, zza());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final void setSnippet(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(7, zza);
    }

    public final String getSnippet() {
        Parcel zza = zza(8, zza());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final void setDraggable(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(9, zza);
    }

    public final boolean isDraggable() {
        Parcel zza = zza(10, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void showInfoWindow() {
        zzb(11, zza());
    }

    public final void hideInfoWindow() {
        zzb(12, zza());
    }

    public final boolean isInfoWindowShown() {
        Parcel zza = zza(13, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void setVisible(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(14, zza);
    }

    public final boolean isVisible() {
        Parcel zza = zza(15, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean zzj(zzt zzt) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzt);
        Parcel zza2 = zza(16, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    public final int zzj() {
        Parcel zza = zza(17, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final void zzg(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzb(18, zza);
    }

    public final void setAnchor(float f, float f2) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zza.writeFloat(f2);
        zzb(19, zza);
    }

    public final void setFlat(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(20, zza);
    }

    public final boolean isFlat() {
        Parcel zza = zza(21, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void setRotation(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(22, zza);
    }

    public final float getRotation() {
        Parcel zza = zza(23, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    public final void setInfoWindowAnchor(float f, float f2) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zza.writeFloat(f2);
        zzb(24, zza);
    }

    public final void setAlpha(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(25, zza);
    }

    public final float getAlpha() {
        Parcel zza = zza(26, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    public final void setZIndex(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(27, zza);
    }

    public final float getZIndex() {
        Parcel zza = zza(28, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzb(29, zza);
    }

    public final IObjectWrapper zzk() {
        Parcel zza = zza(30, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }
}
