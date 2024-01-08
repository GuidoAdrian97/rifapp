package crc643f46942d9dd1fff9;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ShellSearchView_ClipDrawableWrapper extends DrawableWrapper implements IGCUserPeer {
    public static final String __md_methods = "n_draw:(Landroid/graphics/Canvas;)V:GetDraw_Landroid_graphics_Canvas_Handler\n";
    private ArrayList refList;

    private native void n_draw(Canvas canvas);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.ShellSearchView+ClipDrawableWrapper, Xamarin.Forms.Platform.Android", ShellSearchView_ClipDrawableWrapper.class, "n_draw:(Landroid/graphics/Canvas;)V:GetDraw_Landroid_graphics_Canvas_Handler\n");
    }

    public ShellSearchView_ClipDrawableWrapper(Drawable drawable) {
        super(drawable);
        if (getClass() == ShellSearchView_ClipDrawableWrapper.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ShellSearchView+ClipDrawableWrapper, Xamarin.Forms.Platform.Android", "Android.Graphics.Drawables.Drawable, Mono.Android", this, new Object[]{drawable});
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
