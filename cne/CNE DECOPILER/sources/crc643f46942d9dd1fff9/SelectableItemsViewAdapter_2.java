package crc643f46942d9dd1fff9;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SelectableItemsViewAdapter_2 extends StructuredItemsViewAdapter_2 {
    public static final String __md_methods = "n_onBindViewHolder:(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V:GetOnBindViewHolder_Landroidx_recyclerview_widget_RecyclerView_ViewHolder_IHandler\nn_onViewRecycled:(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V:GetOnViewRecycled_Landroidx_recyclerview_widget_RecyclerView_ViewHolder_Handler\n";
    private ArrayList refList;

    private native void n_onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i);

    private native void n_onViewRecycled(RecyclerView.ViewHolder viewHolder);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.SelectableItemsViewAdapter`2, Xamarin.Forms.Platform.Android", SelectableItemsViewAdapter_2.class, __md_methods);
    }

    public SelectableItemsViewAdapter_2() {
        if (getClass() == SelectableItemsViewAdapter_2.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.SelectableItemsViewAdapter`2, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        n_onBindViewHolder(viewHolder, i);
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        n_onViewRecycled(viewHolder);
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
