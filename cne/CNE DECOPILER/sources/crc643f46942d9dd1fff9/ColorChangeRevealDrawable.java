package crc643f46942d9dd1fff9;

import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ColorChangeRevealDrawable extends AnimationDrawable implements IGCUserPeer {
    public static final String __md_methods = "n_draw:(Landroid/graphics/Canvas;)V:GetDraw_Landroid_graphics_Canvas_Handler\n";
    private ArrayList refList;

    private native void n_draw(Canvas canvas);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.ColorChangeRevealDrawable, Xamarin.Forms.Platform.Android", ColorChangeRevealDrawable.class, "n_draw:(Landroid/graphics/Canvas;)V:GetDraw_Landroid_graphics_Canvas_Handler\n");
    }

    public ColorChangeRevealDrawable() {
        if (getClass() == ColorChangeRevealDrawable.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ColorChangeRevealDrawable, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public void draw(Canvas canvas) {
        n_draw(canvas);
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
