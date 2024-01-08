package crc647c4c06b10f3352ff;

import android.content.Context;
import android.util.AttributeSet;
import crc643f46942d9dd1fff9.CheckBoxRendererBase;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialCheckBoxRenderer extends CheckBoxRendererBase {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("Xamarin.Forms.Material.Android.MaterialCheckBoxRenderer, Xamarin.Forms.Material", MaterialCheckBoxRenderer.class, "");
    }

    public MaterialCheckBoxRenderer(Context context) {
        super(context);
        if (getClass() == MaterialCheckBoxRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialCheckBoxRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public MaterialCheckBoxRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == MaterialCheckBoxRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialCheckBoxRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public MaterialCheckBoxRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == MaterialCheckBoxRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialCheckBoxRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
