package crc643f46942d9dd1fff9;

import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ImageCache_CacheEntry implements IGCUserPeer {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.ImageCache+CacheEntry, Xamarin.Forms.Platform.Android", ImageCache_CacheEntry.class, "");
    }

    public ImageCache_CacheEntry() {
        if (getClass() == ImageCache_CacheEntry.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ImageCache+CacheEntry, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
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
