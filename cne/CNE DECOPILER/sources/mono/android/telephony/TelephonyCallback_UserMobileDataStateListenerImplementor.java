package mono.android.telephony;

import android.telephony.TelephonyCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TelephonyCallback_UserMobileDataStateListenerImplementor implements IGCUserPeer, TelephonyCallback.UserMobileDataStateListener {
    public static final String __md_methods = "n_onUserMobileDataStateChanged:(Z)V:GetOnUserMobileDataStateChanged_ZHandler:Android.Telephony.TelephonyCallback/IUserMobileDataStateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onUserMobileDataStateChanged(boolean z);

    static {
        Runtime.register("Android.Telephony.TelephonyCallback+IUserMobileDataStateListenerImplementor, Mono.Android", TelephonyCallback_UserMobileDataStateListenerImplementor.class, __md_methods);
    }

    public TelephonyCallback_UserMobileDataStateListenerImplementor() {
        if (getClass() == TelephonyCallback_UserMobileDataStateListenerImplementor.class) {
            TypeManager.Activate("Android.Telephony.TelephonyCallback+IUserMobileDataStateListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onUserMobileDataStateChanged(boolean z) {
        n_onUserMobileDataStateChanged(z);
    }

    public void monodroidAddReference(Object obj) {
        if (this.refList == null) {
            this.refList = new ArrayList();
        }
        this.refList.add(obj);
    }

    public void monodroidClearReferences() {
        ArrayList arrayList = this.refList;
        if (arrayList != null) {
            arrayList.clear();
        }
    }
}
