package crc647c4c06b10f3352ff;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialFormsEditTextBase extends TextInputEditText implements IGCUserPeer {
    public static final String __md_methods = "n_requestFocus:(ILandroid/graphics/Rect;)Z:GetRequestFocus_ILandroid_graphics_Rect_Handler\n";
    private ArrayList refList;

    private native boolean n_requestFocus(int i, Rect rect);

    static {
        Runtime.register("Xamarin.Forms.Material.Android.MaterialFormsEditTextBase, Xamarin.Forms.Material", MaterialFormsEditTextBase.class, "n_requestFocus:(ILandroid/graphics/Rect;)Z:GetRequestFocus_ILandroid_graphics_Rect_Handler\n");
    }

    public MaterialFormsEditTextBase(Context context) {
        super(context);
        if (getClass() == MaterialFormsEditTextBase.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsEditTextBase, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public MaterialFormsEditTextBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == MaterialFormsEditTextBase.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsEditTextBase, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public MaterialFormsEditTextBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == MaterialFormsEditTextBase.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsEditTextBase, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        return n_requestFocus(i, rect);
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
