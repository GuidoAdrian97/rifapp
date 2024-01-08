package crc643f46942d9dd1fff9;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EmptyViewAdapter extends RecyclerView.Adapter implements IGCUserPeer {
    public static final String __md_methods = "n_getItemCount:()I:GetGetItemCountHandler\nn_onViewRecycled:(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;)V:GetOnViewRecycled_Landroidx_recyclerview_widget_RecyclerView_ViewHolder_Handler\nn_onBindViewHolder:(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V:GetOnBindViewHolder_Landroidx_recyclerview_widget_RecyclerView_ViewHolder_IHandler\nn_onCreateViewHolder:(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;:GetOnCreateViewHolder_Landroid_view_ViewGroup_IHandler\nn_getItemViewType:(I)I:GetGetItemViewType_IHandler\n";
    private ArrayList refList;

    private native int n_getItemCount();

    private native int n_getItemViewType(int i);

    private native void n_onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i);

    private native RecyclerView.ViewHolder n_onCreateViewHolder(ViewGroup viewGroup, int i);

    private native void n_onViewRecycled(RecyclerView.ViewHolder viewHolder);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.EmptyViewAdapter, Xamarin.Forms.Platform.Android", EmptyViewAdapter.class, __md_methods);
    }

    public EmptyViewAdapter() {
        if (getClass() == EmptyViewAdapter.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.EmptyViewAdapter, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public int getItemCount() {
        return n_getItemCount();
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        n_onViewRecycled(viewHolder);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        n_onBindViewHolder(viewHolder, i);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return n_onCreateViewHolder(viewGroup, i);
    }

    public int getItemViewType(int i) {
        return n_getItemViewType(i);
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
