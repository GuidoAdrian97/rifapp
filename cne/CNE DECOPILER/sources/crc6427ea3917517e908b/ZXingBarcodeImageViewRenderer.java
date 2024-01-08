package crc6427ea3917517e908b;

import android.content.Context;
import android.util.AttributeSet;
import crc643f46942d9dd1fff9.ViewRenderer_2;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ZXingBarcodeImageViewRenderer extends ViewRenderer_2 {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("ZXing.Net.Mobile.Forms.Android.ZXingBarcodeImageViewRenderer, ZXing.Net.Mobile.Forms.Android", ZXingBarcodeImageViewRenderer.class, "");
    }

    public ZXingBarcodeImageViewRenderer(Context context) {
        super(context);
        if (getClass() == ZXingBarcodeImageViewRenderer.class) {
            TypeManager.Activate("ZXing.Net.Mobile.Forms.Android.ZXingBarcodeImageViewRenderer, ZXing.Net.Mobile.Forms.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public ZXingBarcodeImageViewRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == ZXingBarcodeImageViewRenderer.class) {
            TypeManager.Activate("ZXing.Net.Mobile.Forms.Android.ZXingBarcodeImageViewRenderer, ZXing.Net.Mobile.Forms.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public ZXingBarcodeImageViewRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == ZXingBarcodeImageViewRenderer.class) {
            TypeManager.Activate("ZXing.Net.Mobile.Forms.Android.ZXingBarcodeImageViewRenderer, ZXing.Net.Mobile.Forms.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
