package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class OpenGLViewRenderer extends ViewRenderer_2 {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.OpenGLViewRenderer, Xamarin.Forms.Platform.Android", OpenGLViewRenderer.class, "");
    }

    public OpenGLViewRenderer(Context context) {
        super(context);
        if (getClass() == OpenGLViewRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.OpenGLViewRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public OpenGLViewRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == OpenGLViewRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.OpenGLViewRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public OpenGLViewRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == OpenGLViewRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.OpenGLViewRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
