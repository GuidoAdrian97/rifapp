package crc643f46942d9dd1fff9;

import android.view.View;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TemplatedItemViewHolder extends SelectableViewHolder {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.TemplatedItemViewHolder, Xamarin.Forms.Platform.Android", TemplatedItemViewHolder.class, "");
    }

    public TemplatedItemViewHolder(View view) {
        super(view);
        if (getClass() == TemplatedItemViewHolder.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.TemplatedItemViewHolder, Xamarin.Forms.Platform.Android", "Android.Views.View, Mono.Android", this, new Object[]{view});
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
