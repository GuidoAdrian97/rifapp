package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnCircleClickListenerImplementor implements IGCUserPeer, GoogleMap.OnCircleClickListener {
    public static final String __md_methods = "n_onCircleClick:(Lcom/google/android/gms/maps/model/Circle;)V:GetOnCircleClick_Lcom_google_android_gms_maps_model_Circle_Handler:Android.Gms.Maps.GoogleMap/IOnCircleClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
    private ArrayList refList;

    private native void n_onCircleClick(Circle circle);

    static {
        Runtime.register("Android.Gms.Maps.GoogleMap+IOnCircleClickListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnCircleClickListenerImplementor.class, __md_methods);
    }

    public GoogleMap_OnCircleClickListenerImplementor() {
        if (getClass() == GoogleMap_OnCircleClickListenerImplementor.class) {
            TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnCircleClickListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]);
        }
    }

    public void onCircleClick(Circle circle) {
        n_onCircleClick(circle);
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
