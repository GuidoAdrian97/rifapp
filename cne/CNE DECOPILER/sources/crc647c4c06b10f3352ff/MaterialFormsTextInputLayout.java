package crc647c4c06b10f3352ff;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialFormsTextInputLayout extends MaterialFormsTextInputLayoutBase {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("Xamarin.Forms.Material.Android.MaterialFormsTextInputLayout, Xamarin.Forms.Material", MaterialFormsTextInputLayout.class, "");
    }

    public MaterialFormsTextInputLayout(Context context) {
        super(context);
        if (getClass() == MaterialFormsTextInputLayout.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsTextInputLayout, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public MaterialFormsTextInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == MaterialFormsTextInputLayout.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsTextInputLayout, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public MaterialFormsTextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == MaterialFormsTextInputLayout.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsTextInputLayout, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
