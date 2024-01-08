package crc6460d2b0234674b457;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import crc643f46942d9dd1fff9.PageRenderer;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PopupPageRenderer extends PageRenderer {
    public static final String __md_methods = "n_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\nn_onAttachedToWindow:()V:GetOnAttachedToWindowHandler\nn_onDetachedFromWindow:()V:GetOnDetachedFromWindowHandler\nn_onWindowVisibilityChanged:(I)V:GetOnWindowVisibilityChanged_IHandler\nn_dispatchTouchEvent:(Landroid/view/MotionEvent;)Z:GetDispatchTouchEvent_Landroid_view_MotionEvent_Handler\nn_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\n";
    private ArrayList refList;

    private native boolean n_dispatchTouchEvent(MotionEvent motionEvent);

    private native void n_onAttachedToWindow();

    private native void n_onDetachedFromWindow();

    private native void n_onLayout(boolean z, int i, int i2, int i3, int i4);

    private native boolean n_onTouchEvent(MotionEvent motionEvent);

    private native void n_onWindowVisibilityChanged(int i);

    static {
        Runtime.register("Rg.Plugins.Popup.Droid.Renderers.PopupPageRenderer, Rg.Plugins.Popup.Droid", PopupPageRenderer.class, __md_methods);
    }

    public PopupPageRenderer(Context context) {
        super(context);
        if (getClass() == PopupPageRenderer.class) {
            TypeManager.Activate("Rg.Plugins.Popup.Droid.Renderers.PopupPageRenderer, Rg.Plugins.Popup.Droid", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public PopupPageRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == PopupPageRenderer.class) {
            TypeManager.Activate("Rg.Plugins.Popup.Droid.Renderers.PopupPageRenderer, Rg.Plugins.Popup.Droid", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public PopupPageRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == PopupPageRenderer.class) {
            TypeManager.Activate("Rg.Plugins.Popup.Droid.Renderers.PopupPageRenderer, Rg.Plugins.Popup.Droid", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        n_onLayout(z, i, i2, i3, i4);
    }

    public void onAttachedToWindow() {
        n_onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        n_onDetachedFromWindow();
    }

    public void onWindowVisibilityChanged(int i) {
        n_onWindowVisibilityChanged(i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return n_dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return n_onTouchEvent(motionEvent);
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
