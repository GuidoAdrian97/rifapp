package crc647c4c06b10f3352ff;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialFormsTextInputLayoutBase extends TextInputLayout implements IGCUserPeer {
    public static final String __md_methods = "n_getEditText:()Landroid/widget/EditText;:GetGetEditTextHandler\n";
    private ArrayList refList;

    private native EditText n_getEditText();

    static {
        Runtime.register("Xamarin.Forms.Material.Android.MaterialFormsTextInputLayoutBase, Xamarin.Forms.Material", MaterialFormsTextInputLayoutBase.class, __md_methods);
    }

    public MaterialFormsTextInputLayoutBase(Context context) {
        super(context);
        if (getClass() == MaterialFormsTextInputLayoutBase.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsTextInputLayoutBase, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public MaterialFormsTextInputLayoutBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == MaterialFormsTextInputLayoutBase.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsTextInputLayoutBase, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public MaterialFormsTextInputLayoutBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == MaterialFormsTextInputLayoutBase.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialFormsTextInputLayoutBase, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public EditText getEditText() {
        return n_getEditText();
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
