package crc643f46942d9dd1fff9;

import android.widget.BaseAdapter;
import android.widget.Filter;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ShellSearchViewAdapter_CustomFilter extends Filter implements IGCUserPeer {
    public static final String __md_methods = "n_performFiltering:(Ljava/lang/CharSequence;)Landroid/widget/Filter$FilterResults;:GetPerformFiltering_Ljava_lang_CharSequence_Handler\nn_publishResults:(Ljava/lang/CharSequence;Landroid/widget/Filter$FilterResults;)V:GetPublishResults_Ljava_lang_CharSequence_Landroid_widget_Filter_FilterResults_Handler\n";
    private ArrayList refList;

    private native Filter.FilterResults n_performFiltering(CharSequence charSequence);

    private native void n_publishResults(CharSequence charSequence, Filter.FilterResults filterResults);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.ShellSearchViewAdapter+CustomFilter, Xamarin.Forms.Platform.Android", ShellSearchViewAdapter_CustomFilter.class, __md_methods);
    }

    public ShellSearchViewAdapter_CustomFilter() {
        if (getClass() == ShellSearchViewAdapter_CustomFilter.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ShellSearchViewAdapter+CustomFilter, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public ShellSearchViewAdapter_CustomFilter(BaseAdapter baseAdapter) {
        if (getClass() == ShellSearchViewAdapter_CustomFilter.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ShellSearchViewAdapter+CustomFilter, Xamarin.Forms.Platform.Android", "Android.Widget.BaseAdapter, Mono.Android", this, new Object[]{baseAdapter});
        }
    }

    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        return n_performFiltering(charSequence);
    }

    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        n_publishResults(charSequence, filterResults);
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
