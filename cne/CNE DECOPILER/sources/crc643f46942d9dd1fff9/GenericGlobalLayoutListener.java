package crc643f46942d9dd1fff9;

import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GenericGlobalLayoutListener implements IGCUserPeer, ViewTreeObserver.OnGlobalLayoutListener {
    public static final String __md_methods = "n_onGlobalLayout:()V:GetOnGlobalLayoutHandler:Android.Views.ViewTreeObserver/IOnGlobalLayoutListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onGlobalLayout();

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.GenericGlobalLayoutListener, Xamarin.Forms.Platform.Android", GenericGlobalLayoutListener.class, "n_onGlobalLayout:()V:GetOnGlobalLayoutHandler:Android.Views.ViewTreeObserver/IOnGlobalLayoutListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
    }

    public GenericGlobalLayoutListener() {
        if (getClass() == GenericGlobalLayoutListener.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.GenericGlobalLayoutListener, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public void onGlobalLayout() {
        n_onGlobalLayout();
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
