package crc648e35430423bd4943;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public abstract class SKGLTextureViewRenderer implements IGCUserPeer {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("SkiaSharp.Views.Android.SKGLTextureViewRenderer, SkiaSharp.Views.Android", SKGLTextureViewRenderer.class, "");
    }

    public SKGLTextureViewRenderer() {
        if (getClass() == SKGLTextureViewRenderer.class) {
            TypeManager.Activate("SkiaSharp.Views.Android.SKGLTextureViewRenderer, SkiaSharp.Views.Android", "", this, new Object[0]);
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
