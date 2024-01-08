package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzi;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzu;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class zzg extends zza implements IGoogleMapDelegate {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final CameraPosition getCameraPosition() {
        Parcel zza = zza(1, zza());
        CameraPosition cameraPosition = (CameraPosition) zzc.zza(zza, CameraPosition.CREATOR);
        zza.recycle();
        return cameraPosition;
    }

    public final float getMaxZoomLevel() {
        Parcel zza = zza(2, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    public final float getMinZoomLevel() {
        Parcel zza = zza(3, zza());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    public final void moveCamera(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzb(4, zza);
    }

    public final void animateCamera(IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzb(5, zza);
    }

    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzc) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzc.zza(zza, (IInterface) zzc);
        zzb(6, zza);
    }

    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzc zzc) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zza.writeInt(i);
        zzc.zza(zza, (IInterface) zzc);
        zzb(7, zza);
    }

    public final void stopAnimation() {
        zzb(8, zza());
    }

    public final zzz addPolyline(PolylineOptions polylineOptions) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) polylineOptions);
        Parcel zza2 = zza(9, zza);
        zzz zzi = zzaa.zzi(zza2.readStrongBinder());
        zza2.recycle();
        return zzi;
    }

    public final zzw addPolygon(PolygonOptions polygonOptions) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) polygonOptions);
        Parcel zza2 = zza(10, zza);
        zzw zzh = zzx.zzh(zza2.readStrongBinder());
        zza2.recycle();
        return zzh;
    }

    public final zzt addMarker(MarkerOptions markerOptions) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) markerOptions);
        Parcel zza2 = zza(11, zza);
        zzt zzg = zzu.zzg(zza2.readStrongBinder());
        zza2.recycle();
        return zzg;
    }

    public final zzk addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) groundOverlayOptions);
        Parcel zza2 = zza(12, zza);
        zzk zzd = zzl.zzd(zza2.readStrongBinder());
        zza2.recycle();
        return zzd;
    }

    public final zzac addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) tileOverlayOptions);
        Parcel zza2 = zza(13, zza);
        zzac zzj = zzad.zzj(zza2.readStrongBinder());
        zza2.recycle();
        return zzj;
    }

    public final void clear() {
        zzb(14, zza());
    }

    public final int getMapType() {
        Parcel zza = zza(15, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final void setMapType(int i) {
        Parcel zza = zza();
        zza.writeInt(i);
        zzb(16, zza);
    }

    public final boolean isTrafficEnabled() {
        Parcel zza = zza(17, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void setTrafficEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(18, zza);
    }

    public final boolean isIndoorEnabled() {
        Parcel zza = zza(19, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean setIndoorEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        Parcel zza2 = zza(20, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    public final boolean isMyLocationEnabled() {
        Parcel zza = zza(21, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void setMyLocationEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(22, zza);
    }

    public final Location getMyLocation() {
        Parcel zza = zza(23, zza());
        Location location = (Location) zzc.zza(zza, Location.CREATOR);
        zza.recycle();
        return location;
    }

    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iLocationSourceDelegate);
        zzb(24, zza);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IUiSettingsDelegate getUiSettings() {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zza()
            r1 = 25
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IUiSettingsDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IUiSettingsDelegate
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.maps.internal.IUiSettingsDelegate r1 = (com.google.android.gms.maps.internal.IUiSettingsDelegate) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzbx r2 = new com.google.android.gms.maps.internal.zzbx
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getUiSettings():com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IProjectionDelegate getProjection() {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zza()
            r1 = 26
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IProjectionDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IProjectionDelegate
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.maps.internal.IProjectionDelegate r1 = (com.google.android.gms.maps.internal.IProjectionDelegate) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzbr r2 = new com.google.android.gms.maps.internal.zzbr
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getProjection():com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    public final void setOnCameraChangeListener(zzl zzl) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzl);
        zzb(27, zza);
    }

    public final void setOnMapClickListener(zzaj zzaj) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzaj);
        zzb(28, zza);
    }

    public final void setOnMapLongClickListener(zzan zzan) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzan);
        zzb(29, zza);
    }

    public final void setOnMarkerClickListener(zzar zzar) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzar);
        zzb(30, zza);
    }

    public final void setOnMarkerDragListener(zzat zzat) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzat);
        zzb(31, zza);
    }

    public final void setOnInfoWindowClickListener(zzab zzab) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzab);
        zzb(32, zza);
    }

    public final void setInfoWindowAdapter(zzh zzh) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzh);
        zzb(33, zza);
    }

    public final zzh addCircle(CircleOptions circleOptions) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) circleOptions);
        Parcel zza2 = zza(35, zza);
        zzh zzc = zzi.zzc(zza2.readStrongBinder());
        zza2.recycle();
        return zzc;
    }

    public final void setOnMyLocationChangeListener(zzax zzax) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzax);
        zzb(36, zza);
    }

    public final void setOnMyLocationButtonClickListener(zzav zzav) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzav);
        zzb(37, zza);
    }

    public final void snapshot(zzbs zzbs, IObjectWrapper iObjectWrapper) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzbs);
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zzb(38, zza);
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        Parcel zza = zza();
        zza.writeInt(i);
        zza.writeInt(i2);
        zza.writeInt(i3);
        zza.writeInt(i4);
        zzb(39, zza);
    }

    public final boolean isBuildingsEnabled() {
        Parcel zza = zza(40, zza());
        boolean zza2 = zzc.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void setBuildingsEnabled(boolean z) {
        Parcel zza = zza();
        zzc.writeBoolean(zza, z);
        zzb(41, zza);
    }

    public final void setOnMapLoadedCallback(zzal zzal) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzal);
        zzb(42, zza);
    }

    public final zzn getFocusedBuilding() {
        Parcel zza = zza(44, zza());
        zzn zze = zzo.zze(zza.readStrongBinder());
        zza.recycle();
        return zze;
    }

    public final void setOnIndoorStateChangeListener(zzz zzz) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzz);
        zzb(45, zza);
    }

    public final void setContentDescription(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(61, zza);
    }

    public final void setOnPoiClickListener(zzbb zzbb) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzbb);
        zzb(80, zza);
    }

    public final void setOnGroundOverlayClickListener(zzx zzx) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzx);
        zzb(83, zza);
    }

    public final void setOnInfoWindowLongClickListener(zzaf zzaf) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzaf);
        zzb(84, zza);
    }

    public final void setOnPolygonClickListener(zzbd zzbd) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzbd);
        zzb(85, zza);
    }

    public final void setOnInfoWindowCloseListener(zzad zzad) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzad);
        zzb(86, zza);
    }

    public final void setOnPolylineClickListener(zzbf zzbf) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzbf);
        zzb(87, zza);
    }

    public final void setOnCircleClickListener(zzv zzv) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzv);
        zzb(89, zza);
    }

    public final void setMinZoomPreference(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(92, zza);
    }

    public final void setMaxZoomPreference(float f) {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzb(93, zza);
    }

    public final void resetMinMaxZoomPreference() {
        zzb(94, zza());
    }

    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) latLngBounds);
        zzb(95, zza);
    }

    public final void setOnCameraMoveStartedListener(zzt zzt) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzt);
        zzb(96, zza);
    }

    public final void setOnCameraMoveListener(zzr zzr) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzr);
        zzb(97, zza);
    }

    public final void setOnCameraMoveCanceledListener(zzp zzp) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzp);
        zzb(98, zza);
    }

    public final void setOnCameraIdleListener(zzn zzn) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzn);
        zzb(99, zza);
    }

    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) {
        Parcel zza = zza();
        zzc.zza(zza, (Parcelable) mapStyleOptions);
        Parcel zza2 = zza(91, zza);
        boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }

    public final void setOnMyLocationClickListener(zzaz zzaz) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) zzaz);
        zzb(107, zza);
    }
}
