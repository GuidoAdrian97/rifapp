package mono.com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnMyLocationClickListenerImplementor implements IGCUserPeer, GoogleMap.OnMyLocationClickListener {
    public static final String __md_methods = "n_onMyLocationClick:(Landroid/location/Location;)V:GetOnMyLocationClick_Landroid_location_Location_Handler:Android.Gms.Maps.GoogleMap/IOnMyLocationClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
    private ArrayList refList;

    private native void n_onMyLocationClick(Location location);

    static {
        Runtime.register("Android.Gms.Maps.GoogleMap+IOnMyLocationClickListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnMyLocationClickListenerImplementor.class, __md_methods);
    }

    public GoogleMap_OnMyLocationClickListenerImplementor() {
        if (getClass() == GoogleMap_OnMyLocationClickListenerImplementor.class) {
            TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnMyLocationClickListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]);
        }
    }

    public void onMyLocationClick(Location location) {
        n_onMyLocationClick(location);
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
