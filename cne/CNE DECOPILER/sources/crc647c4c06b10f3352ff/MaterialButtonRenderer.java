package crc647c4c06b10f3352ff;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialButtonRenderer extends MaterialButton implements IGCUserPeer, View.OnAttachStateChangeListener, View.OnFocusChangeListener, View.OnClickListener, View.OnTouchListener {
    public static final String __md_methods = "n_draw:(Landroid/graphics/Canvas;)V:GetDraw_Landroid_graphics_Canvas_Handler\nn_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\nn_isEnabled:()Z:GetIsEnabledHandler\nn_setEnabled:(Z)V:GetSetEnabled_ZHandler\nn_onViewAttachedToWindow:(Landroid/view/View;)V:GetOnViewAttachedToWindow_Landroid_view_View_Handler:Android.Views.View/IOnAttachStateChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onViewDetachedFromWindow:(Landroid/view/View;)V:GetOnViewDetachedFromWindow_Landroid_view_View_Handler:Android.Views.View/IOnAttachStateChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onFocusChange:(Landroid/view/View;Z)V:GetOnFocusChange_Landroid_view_View_ZHandler:Android.Views.View/IOnFocusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler:Android.Views.View/IOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onTouch:(Landroid/view/View;Landroid/view/MotionEvent;)Z:GetOnTouch_Landroid_view_View_Landroid_view_MotionEvent_Handler:Android.Views.View/IOnTouchListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_draw(Canvas canvas);

    private native boolean n_isEnabled();

    private native void n_onClick(View view);

    private native void n_onFocusChange(View view, boolean z);

    private native void n_onLayout(boolean z, int i, int i2, int i3, int i4);

    private native boolean n_onTouch(View view, MotionEvent motionEvent);

    private native boolean n_onTouchEvent(MotionEvent motionEvent);

    private native void n_onViewAttachedToWindow(View view);

    private native void n_onViewDetachedFromWindow(View view);

    private native void n_setEnabled(boolean z);

    static {
        Runtime.register("Xamarin.Forms.Material.Android.MaterialButtonRenderer, Xamarin.Forms.Material", MaterialButtonRenderer.class, __md_methods);
    }

    public MaterialButtonRenderer(Context context) {
        super(context);
        if (getClass() == MaterialButtonRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialButtonRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public MaterialButtonRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == MaterialButtonRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialButtonRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public MaterialButtonRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == MaterialButtonRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialButtonRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public void draw(Canvas canvas) {
        n_draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return n_onTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        n_onLayout(z, i, i2, i3, i4);
    }

    public boolean isEnabled() {
        return n_isEnabled();
    }

    public void setEnabled(boolean z) {
        n_setEnabled(z);
    }

    public void onViewAttachedToWindow(View view) {
        n_onViewAttachedToWindow(view);
    }

    public void onViewDetachedFromWindow(View view) {
        n_onViewDetachedFromWindow(view);
    }

    public void onFocusChange(View view, boolean z) {
        n_onFocusChange(view, z);
    }

    public void onClick(View view) {
        n_onClick(view);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return n_onTouch(view, motionEvent);
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
