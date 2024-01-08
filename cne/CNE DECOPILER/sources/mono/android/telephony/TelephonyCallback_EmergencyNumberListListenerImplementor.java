package mono.android.telephony;

import android.telephony.TelephonyCallback;
import java.util.ArrayList;
import java.util.Map;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TelephonyCallback_EmergencyNumberListListenerImplementor implements IGCUserPeer, TelephonyCallback.EmergencyNumberListListener {
    public static final String __md_methods = "n_onEmergencyNumberListChanged:(Ljava/util/Map;)V:GetOnEmergencyNumberListChanged_Ljava_util_Map_Handler:Android.Telephony.TelephonyCallback/IEmergencyNumberListListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onEmergencyNumberListChanged(Map map);

    static {
        Runtime.register("Android.Telephony.TelephonyCallback+IEmergencyNumberListListenerImplementor, Mono.Android", TelephonyCallback_EmergencyNumberListListenerImplementor.class, __md_methods);
    }

    public TelephonyCallback_EmergencyNumberListListenerImplementor() {
        if (getClass() == TelephonyCallback_EmergencyNumberListListenerImplementor.class) {
            TypeManager.Activate("Android.Telephony.TelephonyCallback+IEmergencyNumberListListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onEmergencyNumberListChanged(Map map) {
        n_onEmergencyNumberListChanged(map);
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
