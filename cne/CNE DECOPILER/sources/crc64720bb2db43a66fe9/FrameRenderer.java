package crc64720bb2db43a66fe9;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FrameRenderer extends crc64ee486da937c010f4.FrameRenderer {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.AppCompat.FrameRenderer, Xamarin.Forms.Platform.Android", FrameRenderer.class, "");
    }

    public FrameRenderer(Context context) {
        super(context);
        if (getClass() == FrameRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.FrameRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public FrameRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == FrameRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.FrameRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public FrameRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == FrameRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.FrameRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
