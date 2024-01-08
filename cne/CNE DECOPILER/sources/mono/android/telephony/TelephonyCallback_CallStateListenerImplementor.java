package mono.android.telephony;

import android.telephony.TelephonyCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TelephonyCallback_CallStateListenerImplementor implements IGCUserPeer, TelephonyCallback.CallStateListener {
    public static final String __md_methods = "n_onCallStateChanged:(I)V:GetOnCallStateChanged_IHandler:Android.Telephony.TelephonyCallback/ICallStateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onCallStateChanged(int i);

    static {
        Runtime.register("Android.Telephony.TelephonyCallback+ICallStateListenerImplementor, Mono.Android", TelephonyCallback_CallStateListenerImplementor.class, __md_methods);
    }

    public TelephonyCallback_CallStateListenerImplementor() {
        if (getClass() == TelephonyCallback_CallStateListenerImplementor.class) {
            TypeManager.Activate("Android.Telephony.TelephonyCallback+ICallStateListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onCallStateChanged(int i) {
        n_onCallStateChanged(i);
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
