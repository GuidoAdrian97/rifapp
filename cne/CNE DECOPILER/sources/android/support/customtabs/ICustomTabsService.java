package android.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import java.util.List;

public interface ICustomTabsService extends IInterface {
    Bundle extraCommand(String str, Bundle bundle);

    boolean mayLaunchUrl(ICustomTabsCallback iCustomTabsCallback, Uri uri, Bundle bundle, List list);

    boolean newSession(ICustomTabsCallback iCustomTabsCallback);

    boolean newSessionWithExtras(ICustomTabsCallback iCustomTabsCallback, Bundle bundle);

    int postMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle);

    boolean receiveFile(ICustomTabsCallback iCustomTabsCallback, Uri uri, int i, Bundle bundle);

    boolean requestPostMessageChannel(ICustomTabsCallback iCustomTabsCallback, Uri uri);

    boolean requestPostMessageChannelWithExtras(ICustomTabsCallback iCustomTabsCallback, Uri uri, Bundle bundle);

    boolean updateVisuals(ICustomTabsCallback iCustomTabsCallback, Bundle bundle);

    boolean validateRelationship(ICustomTabsCallback iCustomTabsCallback, int i, Uri uri, Bundle bundle);

    boolean warmup(long j);

    public abstract class Stub extends Binder implements ICustomTabsService {
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "android.support.customtabs.ICustomTabsService");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v34 */
        /* JADX WARNING: type inference failed for: r0v35 */
        /* JADX WARNING: type inference failed for: r0v36 */
        /* JADX WARNING: type inference failed for: r0v37 */
        /* JADX WARNING: type inference failed for: r0v38 */
        /* JADX WARNING: type inference failed for: r0v39 */
        /* JADX WARNING: type inference failed for: r0v40 */
        /* JADX WARNING: type inference failed for: r0v41 */
        /* JADX WARNING: type inference failed for: r0v42 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) {
            /*
                r4 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "android.support.customtabs.ICustomTabsService"
                if (r5 == r0) goto L_0x01dd
                r0 = 0
                switch(r5) {
                    case 2: goto L_0x01cb;
                    case 3: goto L_0x01b5;
                    case 4: goto L_0x017b;
                    case 5: goto L_0x0150;
                    case 6: goto L_0x012b;
                    case 7: goto L_0x0106;
                    case 8: goto L_0x00dd;
                    case 9: goto L_0x00a4;
                    case 10: goto L_0x007f;
                    case 11: goto L_0x004a;
                    case 12: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r5 = super.onTransact(r5, r6, r7, r8)
                return r5
            L_0x0011:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x002b
                android.os.Parcelable$Creator r8 = android.net.Uri.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                android.net.Uri r8 = (android.net.Uri) r8
                goto L_0x002c
            L_0x002b:
                r8 = r0
            L_0x002c:
                int r2 = r6.readInt()
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x003f
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r0.createFromParcel(r6)
                r0 = r6
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x003f:
                boolean r5 = r4.receiveFile(r5, r8, r2, r0)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x004a:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0064
                android.os.Parcelable$Creator r8 = android.net.Uri.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                android.net.Uri r8 = (android.net.Uri) r8
                goto L_0x0065
            L_0x0064:
                r8 = r0
            L_0x0065:
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x0074
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r0.createFromParcel(r6)
                r0 = r6
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0074:
                boolean r5 = r4.requestPostMessageChannelWithExtras(r5, r8, r0)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x007f:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0099
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r0 = r6
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0099:
                boolean r5 = r4.newSessionWithExtras(r5, r0)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x00a4:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                int r8 = r6.readInt()
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x00c2
                android.os.Parcelable$Creator r2 = android.net.Uri.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r6)
                android.net.Uri r2 = (android.net.Uri) r2
                goto L_0x00c3
            L_0x00c2:
                r2 = r0
            L_0x00c3:
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x00d2
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r0.createFromParcel(r6)
                r0 = r6
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00d2:
                boolean r5 = r4.validateRelationship(r5, r8, r2, r0)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x00dd:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                java.lang.String r8 = r6.readString()
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x00fb
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r0.createFromParcel(r6)
                r0 = r6
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00fb:
                int r5 = r4.postMessage(r5, r8, r0)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x0106:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0120
                android.os.Parcelable$Creator r8 = android.net.Uri.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r0 = r6
                android.net.Uri r0 = (android.net.Uri) r0
            L_0x0120:
                boolean r5 = r4.requestPostMessageChannel(r5, r0)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x012b:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0145
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r0 = r6
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0145:
                boolean r5 = r4.updateVisuals(r5, r0)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x0150:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0166
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r0 = r6
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0166:
                android.os.Bundle r5 = r4.extraCommand(r5, r0)
                r7.writeNoException()
                if (r5 == 0) goto L_0x0176
                r7.writeInt(r1)
                r5.writeToParcel(r7, r1)
                goto L_0x017a
            L_0x0176:
                r5 = 0
                r7.writeInt(r5)
            L_0x017a:
                return r1
            L_0x017b:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0195
                android.os.Parcelable$Creator r8 = android.net.Uri.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                android.net.Uri r8 = (android.net.Uri) r8
                goto L_0x0196
            L_0x0195:
                r8 = r0
            L_0x0196:
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x01a4
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x01a4:
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.util.ArrayList r6 = r6.createTypedArrayList(r2)
                boolean r5 = r4.mayLaunchUrl(r5, r8, r0, r6)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x01b5:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                android.support.customtabs.ICustomTabsCallback r5 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(r5)
                boolean r5 = r4.newSession(r5)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x01cb:
                r6.enforceInterface(r2)
                long r5 = r6.readLong()
                boolean r5 = r4.warmup(r5)
                r7.writeNoException()
                r7.writeInt(r5)
                return r1
            L_0x01dd:
                r7.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.customtabs.ICustomTabsService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }
}
