package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnCameraMoveStartedListenerImplementor implements IGCUserPeer, GoogleMap.OnCameraMoveStartedListener {
    public static final String __md_methods = "n_onCameraMoveStarted:(I)V:GetOnCameraMoveStarted_IHandler:Android.Gms.Maps.GoogleMap/IOnCameraMoveStartedListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
    private ArrayList refList;

    private native void n_onCameraMoveStarted(int i);

    static {
        Runtime.register("Android.Gms.Maps.GoogleMap+IOnCameraMoveStartedListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnCameraMoveStartedListenerImplementor.class, __md_methods);
    }

    public GoogleMap_OnCameraMoveStartedListenerImplementor() {
        if (getClass() == GoogleMap_OnCameraMoveStartedListenerImplementor.class) {
            TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnCameraMoveStartedListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]);
        }
    }

    public void onCameraMoveStarted(int i) {
        n_onCameraMoveStarted(i);
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
