package mono.android.telephony;

import android.telephony.TelephonyCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TelephonyCallback_CallDisconnectCauseListenerImplementor implements IGCUserPeer, TelephonyCallback.CallDisconnectCauseListener {
    public static final String __md_methods = "n_onCallDisconnectCauseChanged:(II)V:GetOnCallDisconnectCauseChanged_IIHandler:Android.Telephony.TelephonyCallback/ICallDisconnectCauseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onCallDisconnectCauseChanged(int i, int i2);

    static {
        Runtime.register("Android.Telephony.TelephonyCallback+ICallDisconnectCauseListenerImplementor, Mono.Android", TelephonyCallback_CallDisconnectCauseListenerImplementor.class, __md_methods);
    }

    public TelephonyCallback_CallDisconnectCauseListenerImplementor() {
        if (getClass() == TelephonyCallback_CallDisconnectCauseListenerImplementor.class) {
            TypeManager.Activate("Android.Telephony.TelephonyCallback+ICallDisconnectCauseListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onCallDisconnectCauseChanged(int i, int i2) {
        n_onCallDisconnectCauseChanged(i, i2);
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
