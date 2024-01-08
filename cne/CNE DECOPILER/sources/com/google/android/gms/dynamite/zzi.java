package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzd;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class zzi extends zzb implements zzj {
    zzi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i) {
        Parcel zza = zza();
        zzd.zza(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(i);
        Parcel zza2 = zza(2, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    public final int zza(IObjectWrapper iObjectWrapper, String str, boolean z) {
        Parcel zza = zza();
        zzd.zza(zza, iObjectWrapper);
        zza.writeString(str);
        zzd.writeBoolean(zza, z);
        Parcel zza2 = zza(3, zza);
        int readInt = zza2.readInt();
        zza2.recycle();
        return readInt;
    }

    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, String str, int i) {
        Parcel zza = zza();
        zzd.zza(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeInt(i);
        Parcel zza2 = zza(4, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return asInterface;
    }

    public final int zzb(IObjectWrapper iObjectWrapper, String str, boolean z) {
        Parcel zza = zza();
        zzd.zza(zza, iObjectWrapper);
        zza.writeString(str);
        zzd.writeBoolean(zza, z);
        Parcel zza2 = zza(5, zza);
        int readInt = zza2.readInt();
        zza2.recycle();
        return readInt;
    }

    public final int zzak() {
        Parcel zza = zza(6, zza());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }
}
