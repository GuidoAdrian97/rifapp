package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormsAppCompatEditText extends FormsAppCompatEditTextBase {
    public static final String __md_methods = "n_onKeyPreIme:(ILandroid/view/KeyEvent;)Z:GetOnKeyPreIme_ILandroid_view_KeyEvent_Handler\nn_onSelectionChanged:(II)V:GetOnSelectionChanged_IIHandler\n";
    private ArrayList refList;

    private native boolean n_onKeyPreIme(int i, KeyEvent keyEvent);

    private native void n_onSelectionChanged(int i, int i2);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.FormsAppCompatEditText, Xamarin.Forms.Platform.Android", FormsAppCompatEditText.class, "n_onKeyPreIme:(ILandroid/view/KeyEvent;)Z:GetOnKeyPreIme_ILandroid_view_KeyEvent_Handler\nn_onSelectionChanged:(II)V:GetOnSelectionChanged_IIHandler\n");
    }

    public FormsAppCompatEditText(Context context) {
        super(context);
        if (getClass() == FormsAppCompatEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsAppCompatEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public FormsAppCompatEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == FormsAppCompatEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsAppCompatEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public FormsAppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == FormsAppCompatEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsAppCompatEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        return n_onKeyPreIme(i, keyEvent);
    }

    public void onSelectionChanged(int i, int i2) {
        n_onSelectionChanged(i, i2);
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
