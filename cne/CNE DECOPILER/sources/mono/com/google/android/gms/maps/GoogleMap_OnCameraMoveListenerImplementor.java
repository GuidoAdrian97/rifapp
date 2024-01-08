package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnCameraMoveListenerImplementor implements IGCUserPeer, GoogleMap.OnCameraMoveListener {
    public static final String __md_methods = "n_onCameraMove:()V:GetOnCameraMoveHandler:Android.Gms.Maps.GoogleMap/IOnCameraMoveListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
    private ArrayList refList;

    private native void n_onCameraMove();

    static {
        Runtime.register("Android.Gms.Maps.GoogleMap+IOnCameraMoveListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnCameraMoveListenerImplementor.class, __md_methods);
    }

    public GoogleMap_OnCameraMoveListenerImplementor() {
        if (getClass() == GoogleMap_OnCameraMoveListenerImplementor.class) {
            TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnCameraMoveListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]);
        }
    }

    public void onCameraMove() {
        n_onCameraMove();
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
