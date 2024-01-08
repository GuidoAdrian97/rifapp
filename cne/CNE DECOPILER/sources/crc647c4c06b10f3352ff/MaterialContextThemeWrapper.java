package crc647c4c06b10f3352ff;

import android.content.Context;
import android.view.ContextThemeWrapper;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialContextThemeWrapper extends ContextThemeWrapper implements IGCUserPeer {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("Xamarin.Forms.Material.Android.MaterialContextThemeWrapper, Xamarin.Forms.Material", MaterialContextThemeWrapper.class, "");
    }

    public MaterialContextThemeWrapper(Context context, int i) {
        super(context, i);
        if (getClass() == MaterialContextThemeWrapper.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialContextThemeWrapper, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, Integer.valueOf(i)});
        }
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
