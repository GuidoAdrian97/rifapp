package mono.android.telephony;

import android.telephony.BarringInfo;
import android.telephony.TelephonyCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TelephonyCallback_BarringInfoListenerImplementor implements IGCUserPeer, TelephonyCallback.BarringInfoListener {
    public static final String __md_methods = "n_onBarringInfoChanged:(Landroid/telephony/BarringInfo;)V:GetOnBarringInfoChanged_Landroid_telephony_BarringInfo_Handler:Android.Telephony.TelephonyCallback/IBarringInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onBarringInfoChanged(BarringInfo barringInfo);

    static {
        Runtime.register("Android.Telephony.TelephonyCallback+IBarringInfoListenerImplementor, Mono.Android", TelephonyCallback_BarringInfoListenerImplementor.class, __md_methods);
    }

    public TelephonyCallback_BarringInfoListenerImplementor() {
        if (getClass() == TelephonyCallback_BarringInfoListenerImplementor.class) {
            TypeManager.Activate("Android.Telephony.TelephonyCallback+IBarringInfoListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onBarringInfoChanged(BarringInfo barringInfo) {
        n_onBarringInfoChanged(barringInfo);
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
