package androidx.core.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IResultReceiver extends IInterface {
    void send(int i, Bundle bundle);

    public abstract class Stub extends Binder implements IResultReceiver {
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "androidx.core.os.IResultReceiver");
        }

        public static IResultReceiver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("androidx.core.os.IResultReceiver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IResultReceiver)) {
                return new Proxy(iBinder);
            }
            return (IResultReceiver) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("androidx.core.os.IResultReceiver");
                send(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("androidx.core.os.IResultReceiver");
                return true;
            }
        }

        class Proxy implements IResultReceiver {
            public static IResultReceiver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void send(int i, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.core.os.IResultReceiver");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().send(i, bundle);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static IResultReceiver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
