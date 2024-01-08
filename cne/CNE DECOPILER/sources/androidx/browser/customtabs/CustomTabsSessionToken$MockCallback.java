package androidx.browser.customtabs;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.customtabs.ICustomTabsCallback;

class CustomTabsSessionToken$MockCallback extends ICustomTabsCallback.Stub {
    public IBinder asBinder() {
        return this;
    }

    public void extraCallback(String str, Bundle bundle) {
    }

    public Bundle extraCallbackWithResult(String str, Bundle bundle) {
        return null;
    }

    public void onMessageChannelReady(Bundle bundle) {
    }

    public void onNavigationEvent(int i, Bundle bundle) {
    }

    public void onPostMessage(String str, Bundle bundle) {
    }

    public void onRelationshipValidationResult(int i, Uri uri, boolean z, Bundle bundle) {
    }

    CustomTabsSessionToken$MockCallback() {
    }
}
