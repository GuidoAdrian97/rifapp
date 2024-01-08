package crc64bb223c2be3a01e03;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SKGLViewRenderer extends SKGLViewRendererBase_2 {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("SkiaSharp.Views.Forms.SKGLViewRenderer, SkiaSharp.Views.Forms", SKGLViewRenderer.class, "");
    }

    public SKGLViewRenderer(Context context) {
        super(context);
        if (getClass() == SKGLViewRenderer.class) {
            TypeManager.Activate("SkiaSharp.Views.Forms.SKGLViewRenderer, SkiaSharp.Views.Forms", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public SKGLViewRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == SKGLViewRenderer.class) {
            TypeManager.Activate("SkiaSharp.Views.Forms.SKGLViewRenderer, SkiaSharp.Views.Forms", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public SKGLViewRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == SKGLViewRenderer.class) {
            TypeManager.Activate("SkiaSharp.Views.Forms.SKGLViewRenderer, SkiaSharp.Views.Forms", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
