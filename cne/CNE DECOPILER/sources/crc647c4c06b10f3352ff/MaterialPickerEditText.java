package crc647c4c06b10f3352ff;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialPickerEditText extends MaterialFormsEditTextBase {
    public static final String __md_methods = "n_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onFocusChanged:(ZILandroid/graphics/Rect;)V:GetOnFocusChanged_ZILandroid_graphics_Rect_Handler\n";
    private ArrayList refList;

    private native void n_onFocusChanged(boolean z, int i, Rect rect);

    private native boolean n_onTouchEvent(MotionEvent motionEvent);

    static {
        Runtime.register("Xamarin.Forms.Material.Android.MaterialPickerEditText, Xamarin.Forms.Material", MaterialPickerEditText.class, "n_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onFocusChanged:(ZILandroid/graphics/Rect;)V:GetOnFocusChanged_ZILandroid_graphics_Rect_Handler\n");
    }

    public MaterialPickerEditText(Context context) {
        super(context);
        if (getClass() == MaterialPickerEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialPickerEditText, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public MaterialPickerEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == MaterialPickerEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialPickerEditText, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public MaterialPickerEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == MaterialPickerEditText.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialPickerEditText, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return n_onTouchEvent(motionEvent);
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
