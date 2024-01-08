package mono;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;

public class MonoRuntimeProvider extends ContentProvider {
    public boolean onCreate() {
        return true;
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        String[] strArr;
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String[] strArr2 = applicationInfo.splitPublicSourceDirs;
        if (strArr2 == null || strArr2.length <= 0) {
            strArr = null;
        } else {
            strArr = new String[(strArr2.length + 1)];
            strArr[0] = applicationInfo.sourceDir;
            System.arraycopy(strArr2, 0, strArr, 1, strArr2.length);
        }
        if (strArr == null) {
            strArr = new String[]{applicationInfo.sourceDir};
        }
        MonoPackageManager.LoadApplication(context, applicationInfo, strArr);
        super.attachInfo(context, providerInfo);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new RuntimeException("This operation is not supported.");
    }

    public String getType(Uri uri) {
        throw new RuntimeException("This operation is not supported.");
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new RuntimeException("This operation is not supported.");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new RuntimeException("This operation is not supported.");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new RuntimeException("This operation is not supported.");
    }
}
