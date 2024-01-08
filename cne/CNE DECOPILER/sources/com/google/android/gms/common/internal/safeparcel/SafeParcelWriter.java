package com.google.android.gms.common.internal.safeparcel;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.internal.view.SupportMenu;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public abstract class SafeParcelWriter {
    private static void zzb(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | SupportMenu.CATEGORY_MASK);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt(i | (i2 << 16));
    }

    private static int zza(Parcel parcel, int i) {
        parcel.writeInt(i | SupportMenu.CATEGORY_MASK);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void zzb(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    public static int beginObjectHeader(Parcel parcel) {
        return zza(parcel, 20293);
    }

    public static void finishObjectHeader(Parcel parcel, int i) {
        zzb(parcel, i);
    }

    public static void writeBoolean(Parcel parcel, int i, boolean z) {
        zzb(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void writeByte(Parcel parcel, int i, byte b) {
        zzb(parcel, i, 4);
        parcel.writeInt(b);
    }

    public static void writeInt(Parcel parcel, int i, int i2) {
        zzb(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void writeLong(Parcel parcel, int i, long j) {
        zzb(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void writeFloat(Parcel parcel, int i, float f) {
        zzb(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void writeFloatObject(Parcel parcel, int i, Float f, boolean z) {
        if (f != null) {
            zzb(parcel, i, 4);
            parcel.writeFloat(f.floatValue());
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeDouble(Parcel parcel, int i, double d) {
        zzb(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void writeString(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int zza = zza(parcel, i);
            parcel.writeString(str);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeIBinder(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int zza = zza(parcel, i);
            parcel.writeStrongBinder(iBinder);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeParcelable(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int zza = zza(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeByteArray(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr != null) {
            int zza = zza(parcel, i);
            parcel.writeByteArray(bArr);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    public static void writeTypedList(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    zza(parcel, parcelable, 0);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }

    private static void zza(Parcel parcel, Parcelable parcelable, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    public static void writeList(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int zza = zza(parcel, i);
            parcel.writeList(list);
            zzb(parcel, zza);
        } else if (z) {
            zzb(parcel, i, 0);
        }
    }
}
