package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.PointOfInterest;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnPoiClickListenerImplementor implements IGCUserPeer, GoogleMap.OnPoiClickListener {
    public static final String __md_methods = "n_onPoiClick:(Lcom/google/android/gms/maps/model/PointOfInterest;)V:GetOnPoiClick_Lcom_google_android_gms_maps_model_PointOfInterest_Handler:Android.Gms.Maps.GoogleMap/IOnPoiClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
    private ArrayList refList;

    private native void n_onPoiClick(PointOfInterest pointOfInterest);

    static {
        Runtime.register("Android.Gms.Maps.GoogleMap+IOnPoiClickListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnPoiClickListenerImplementor.class, __md_methods);
    }

    public GoogleMap_OnPoiClickListenerImplementor() {
        if (getClass() == GoogleMap_OnPoiClickListenerImplementor.class) {
            TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnPoiClickListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]);
        }
    }

    public void onPoiClick(PointOfInterest pointOfInterest) {
        n_onPoiClick(pointOfInterest);
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
