package crc648aad9efe354a1d8f;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import crc643f46942d9dd1fff9.ViewRenderer_2;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MapRenderer extends ViewRenderer_2 implements GoogleMap.OnCameraMoveListener, OnMapReadyCallback {
    public static final String __md_methods = "n_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\nn_onCameraMove:()V:GetOnCameraMoveHandler:Android.Gms.Maps.GoogleMap/IOnCameraMoveListenerInvoker, Xamarin.GooglePlayServices.Maps\nn_onMapReady:(Lcom/google/android/gms/maps/GoogleMap;)V:GetOnMapReady_Lcom_google_android_gms_maps_GoogleMap_Handler:Android.Gms.Maps.IOnMapReadyCallbackInvoker, Xamarin.GooglePlayServices.Maps\n";
    private ArrayList refList;

    private native void n_onCameraMove();

    private native void n_onLayout(boolean z, int i, int i2, int i3, int i4);

    private native void n_onMapReady(GoogleMap googleMap);

    static {
        Runtime.register("Xamarin.Forms.Maps.Android.MapRenderer, Xamarin.Forms.Maps.Android", MapRenderer.class, __md_methods);
    }

    public MapRenderer(Context context) {
        super(context);
        if (getClass() == MapRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Maps.Android.MapRenderer, Xamarin.Forms.Maps.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public MapRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == MapRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Maps.Android.MapRenderer, Xamarin.Forms.Maps.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public MapRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == MapRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Maps.Android.MapRenderer, Xamarin.Forms.Maps.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        n_onLayout(z, i, i2, i3, i4);
    }

    public void onCameraMove() {
        n_onCameraMove();
    }

    public void onMapReady(GoogleMap googleMap) {
        n_onMapReady(googleMap);
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
