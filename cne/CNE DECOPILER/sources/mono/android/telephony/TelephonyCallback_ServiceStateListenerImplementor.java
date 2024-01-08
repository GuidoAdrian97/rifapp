package mono.android.telephony;

import android.telephony.ServiceState;
import android.telephony.TelephonyCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TelephonyCallback_ServiceStateListenerImplementor implements IGCUserPeer, TelephonyCallback.ServiceStateListener {
    public static final String __md_methods = "n_onServiceStateChanged:(Landroid/telephony/ServiceState;)V:GetOnServiceStateChanged_Landroid_telephony_ServiceState_Handler:Android.Telephony.TelephonyCallback/IServiceStateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onServiceStateChanged(ServiceState serviceState);

    static {
        Runtime.register("Android.Telephony.TelephonyCallback+IServiceStateListenerImplementor, Mono.Android", TelephonyCallback_ServiceStateListenerImplementor.class, __md_methods);
    }

    public TelephonyCallback_ServiceStateListenerImplementor() {
        if (getClass() == TelephonyCallback_ServiceStateListenerImplementor.class) {
            TypeManager.Activate("Android.Telephony.TelephonyCallback+IServiceStateListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onServiceStateChanged(ServiceState serviceState) {
        n_onServiceStateChanged(serviceState);
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
