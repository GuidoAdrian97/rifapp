package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class NativeViewWrapperRenderer extends ViewRenderer_2 {
    public static final String __md_methods = "n_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\nn_onMeasure:(II)V:GetOnMeasure_IIHandler\n";
    private ArrayList refList;

    private native void n_onLayout(boolean z, int i, int i2, int i3, int i4);

    private native void n_onMeasure(int i, int i2);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.NativeViewWrapperRenderer, Xamarin.Forms.Platform.Android", NativeViewWrapperRenderer.class, "n_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\nn_onMeasure:(II)V:GetOnMeasure_IIHandler\n");
    }

    public NativeViewWrapperRenderer(Context context) {
        super(context);
        if (getClass() == NativeViewWrapperRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.NativeViewWrapperRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public NativeViewWrapperRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == NativeViewWrapperRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.NativeViewWrapperRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public NativeViewWrapperRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == NativeViewWrapperRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.NativeViewWrapperRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        n_onLayout(z, i, i2, i3, i4);
    }

    public void onMeasure(int i, int i2) {
        n_onMeasure(i, i2);
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
