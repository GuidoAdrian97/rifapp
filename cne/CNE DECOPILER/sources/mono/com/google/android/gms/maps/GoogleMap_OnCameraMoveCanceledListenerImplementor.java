package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnCameraMoveCanceledListenerImplementor implements IGCUserPeer, GoogleMap.OnCameraMoveCanceledListener {
    public static final String __md_methods = "n_onCameraMoveCanceled:()V:GetOnCameraMoveCanceledHandler:Android.Gms.Maps.GoogleMap/IOnCameraMoveCanceledListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
    private ArrayList refList;

    private native void n_onCameraMoveCanceled();

    static {
        Runtime.register("Android.Gms.Maps.GoogleMap+IOnCameraMoveCanceledListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnCameraMoveCanceledListenerImplementor.class, __md_methods);
    }

    public GoogleMap_OnCameraMoveCanceledListenerImplementor() {
        if (getClass() == GoogleMap_OnCameraMoveCanceledListenerImplementor.class) {
            TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnCameraMoveCanceledListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]);
        }
    }

    public void onCameraMoveCanceled() {
        n_onCameraMoveCanceled();
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
