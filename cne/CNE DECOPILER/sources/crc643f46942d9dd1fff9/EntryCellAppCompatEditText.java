package crc643f46942d9dd1fff9;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EntryCellAppCompatEditText extends FormsAppCompatEditTextBase {
    public static final String __md_methods = "n_onKeyPreIme:(ILandroid/view/KeyEvent;)Z:GetOnKeyPreIme_ILandroid_view_KeyEvent_Handler\nn_onFocusChanged:(ZILandroid/graphics/Rect;)V:GetOnFocusChanged_ZILandroid_graphics_Rect_Handler\n";
    private ArrayList refList;

    private native void n_onFocusChanged(boolean z, int i, Rect rect);

    private native boolean n_onKeyPreIme(int i, KeyEvent keyEvent);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.EntryCellAppCompatEditText, Xamarin.Forms.Platform.Android", EntryCellAppCompatEditText.class, "n_onKeyPreIme:(ILandroid/view/KeyEvent;)Z:GetOnKeyPreIme_ILandroid_view_KeyEvent_Handler\nn_onFocusChanged:(ZILandroid/graphics/Rect;)V:GetOnFocusChanged_ZILandroid_graphics_Rect_Handler\n");
    }

    public EntryCellAppCompatEditText(Context context) {
        super(context);
        if (getClass() == EntryCellAppCompatEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.EntryCellAppCompatEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public EntryCellAppCompatEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == EntryCellAppCompatEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.EntryCellAppCompatEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public EntryCellAppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == EntryCellAppCompatEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.EntryCellAppCompatEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        return n_onKeyPreIme(i, keyEvent);
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        n_onFocusChanged(z, i, rect);
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
