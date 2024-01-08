package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class LineRenderer extends ShapeRenderer_2 {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.LineRenderer, Xamarin.Forms.Platform.Android", LineRenderer.class, "");
    }

    public LineRenderer(Context context) {
        super(context);
        if (getClass() == LineRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.LineRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public LineRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == LineRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.LineRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public LineRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == LineRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.LineRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
