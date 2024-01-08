package crc643f46942d9dd1fff9;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.shapes.Shape;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GradientStrokeDrawable extends PaintDrawable implements IGCUserPeer {
    public static final String __md_methods = "n_onDraw:(Landroid/graphics/drawable/shapes/Shape;Landroid/graphics/Canvas;Landroid/graphics/Paint;)V:GetOnDraw_Landroid_graphics_drawable_shapes_Shape_Landroid_graphics_Canvas_Landroid_graphics_Paint_Handler\n";
    private ArrayList refList;

    private native void n_onDraw(Shape shape, Canvas canvas, Paint paint);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.GradientStrokeDrawable, Xamarin.Forms.Platform.Android", GradientStrokeDrawable.class, __md_methods);
    }

    public GradientStrokeDrawable() {
        if (getClass() == GradientStrokeDrawable.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.GradientStrokeDrawable, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public void onDraw(Shape shape, Canvas canvas, Paint paint) {
        n_onDraw(shape, canvas, paint);
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
