package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnCameraIdleListenerImplementor implements IGCUserPeer, GoogleMap.OnCameraIdleListener {
    public static final String __md_methods = "n_onCameraIdle:()V:GetOnCameraIdleHandler:Android.Gms.Maps.GoogleMap/IOnCameraIdleListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
    private ArrayList refList;

    private native void n_onCameraIdle();

    static {
        Runtime.register("Android.Gms.Maps.GoogleMap+IOnCameraIdleListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnCameraIdleListenerImplementor.class, __md_methods);
    }

    public GoogleMap_OnCameraIdleListenerImplementor() {
        if (getClass() == GoogleMap_OnCameraIdleListenerImplementor.class) {
            TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnCameraIdleListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]);
        }
    }

    public void onCameraIdle() {
        n_onCameraIdle();
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
