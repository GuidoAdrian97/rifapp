package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zze;

public final class zzf extends zza implements zze {
    zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICreator");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IMapViewDelegate zza(com.google.android.gms.dynamic.IObjectWrapper r3, com.google.android.gms.maps.GoogleMapOptions r4) {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.zza()
            com.google.android.gms.internal.maps.zzc.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            com.google.android.gms.internal.maps.zzc.zza((android.os.Parcel) r0, (android.os.Parcelable) r4)
            r3 = 3
            android.os.Parcel r3 = r2.zza(r3, r0)
            android.os.IBinder r4 = r3.readStrongBinder()
            if (r4 != 0) goto L_0x0017
            r4 = 0
            goto L_0x002b
        L_0x0017:
            java.lang.String r0 = "com.google.android.gms.maps.internal.IMapViewDelegate"
            android.os.IInterface r0 = r4.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.maps.internal.IMapViewDelegate
            if (r1 == 0) goto L_0x0025
            r4 = r0
            com.google.android.gms.maps.internal.IMapViewDelegate r4 = (com.google.android.gms.maps.internal.IMapViewDelegate) r4
            goto L_0x002b
        L_0x0025:
            com.google.android.gms.maps.internal.zzk r0 = new com.google.android.gms.maps.internal.zzk
            r0.<init>(r4)
            r4 = r0
        L_0x002b:
            r3.recycle()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzf.zza(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.maps.GoogleMapOptions):com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate zze() {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zza()
            r1 = 4
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0025
        L_0x0011:
            java.lang.String r2 = "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate
            if (r3 == 0) goto L_0x001f
            r1 = r2
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r1 = (com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate) r1
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.maps.internal.zzb r2 = new com.google.android.gms.maps.internal.zzb
            r2.<init>(r1)
            r1 = r2
        L_0x0025:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzf.zze():com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    public final zze zzf() {
        Parcel zza = zza(5, zza());
        zze zzb = com.google.android.gms.internal.maps.zzf.zzb(zza.readStrongBinder());
        zza.recycle();
        return zzb;
    }

    public final void zza(IObjectWrapper iObjectWrapper, int i) {
        Parcel zza = zza();
        zzc.zza(zza, (IInterface) iObjectWrapper);
        zza.writeInt(i);
        zzb(6, zza);
    }
}
