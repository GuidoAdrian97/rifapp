package mono.android.telephony;

import android.telephony.TelephonyCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TelephonyCallback_CarrierNetworkListenerImplementor implements IGCUserPeer, TelephonyCallback.CarrierNetworkListener {
    public static final String __md_methods = "n_onCarrierNetworkChange:(Z)V:GetOnCarrierNetworkChange_ZHandler:Android.Telephony.TelephonyCallback/ICarrierNetworkListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onCarrierNetworkChange(boolean z);

    static {
        Runtime.register("Android.Telephony.TelephonyCallback+ICarrierNetworkListenerImplementor, Mono.Android", TelephonyCallback_CarrierNetworkListenerImplementor.class, __md_methods);
    }

    public TelephonyCallback_CarrierNetworkListenerImplementor() {
        if (getClass() == TelephonyCallback_CarrierNetworkListenerImplementor.class) {
            TypeManager.Activate("Android.Telephony.TelephonyCallback+ICarrierNetworkListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onCarrierNetworkChange(boolean z) {
        n_onCarrierNetworkChange(z);
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
