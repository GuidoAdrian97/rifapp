package mono.android.net.wifi;

import android.net.wifi.WifiManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WifiManager_SuggestionUserApprovalStatusListenerImplementor implements IGCUserPeer, WifiManager.SuggestionUserApprovalStatusListener {
    public static final String __md_methods = "n_onUserApprovalStatusChange:(I)V:GetOnUserApprovalStatusChange_IHandler:Android.Net.Wifi.WifiManager/ISuggestionUserApprovalStatusListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onUserApprovalStatusChange(int i);

    static {
        Runtime.register("Android.Net.Wifi.WifiManager+ISuggestionUserApprovalStatusListenerImplementor, Mono.Android", WifiManager_SuggestionUserApprovalStatusListenerImplementor.class, __md_methods);
    }

    public WifiManager_SuggestionUserApprovalStatusListenerImplementor() {
        if (getClass() == WifiManager_SuggestionUserApprovalStatusListenerImplementor.class) {
            TypeManager.Activate("Android.Net.Wifi.WifiManager+ISuggestionUserApprovalStatusListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onUserApprovalStatusChange(int i) {
        n_onUserApprovalStatusChange(i);
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
