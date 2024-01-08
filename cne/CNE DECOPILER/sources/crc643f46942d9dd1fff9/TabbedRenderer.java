package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TabbedRenderer extends VisualElementRenderer_1 {
    public static final String __md_methods = "n_onAttachedToWindow:()V:GetOnAttachedToWindowHandler\nn_onDetachedFromWindow:()V:GetOnDetachedFromWindowHandler\n";
    private ArrayList refList;

    private native void n_onAttachedToWindow();

    private native void n_onDetachedFromWindow();

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.TabbedRenderer, Xamarin.Forms.Platform.Android", TabbedRenderer.class, __md_methods);
    }

    public TabbedRenderer(Context context) {
        super(context);
        if (getClass() == TabbedRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.TabbedRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public TabbedRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == TabbedRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.TabbedRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public TabbedRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == TabbedRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.TabbedRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public void onAttachedToWindow() {
        n_onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        n_onDetachedFromWindow();
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
