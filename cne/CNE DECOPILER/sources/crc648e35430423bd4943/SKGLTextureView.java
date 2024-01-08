package crc648e35430423bd4943;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SKGLTextureView extends GLTextureView {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("SkiaSharp.Views.Android.SKGLTextureView, SkiaSharp.Views.Android", SKGLTextureView.class, "");
    }

    public SKGLTextureView(Context context) {
        super(context);
        if (getClass() == SKGLTextureView.class) {
            TypeManager.Activate("SkiaSharp.Views.Android.SKGLTextureView, SkiaSharp.Views.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public SKGLTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == SKGLTextureView.class) {
            TypeManager.Activate("SkiaSharp.Views.Android.SKGLTextureView, SkiaSharp.Views.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public SKGLTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == SKGLTextureView.class) {
            TypeManager.Activate("SkiaSharp.Views.Android.SKGLTextureView, SkiaSharp.Views.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
