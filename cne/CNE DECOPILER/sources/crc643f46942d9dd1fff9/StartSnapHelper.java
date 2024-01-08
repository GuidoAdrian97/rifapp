package crc643f46942d9dd1fff9;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class StartSnapHelper extends EdgeSnapHelper {
    public static final String __md_methods = "n_calculateDistanceToFinalSnap:(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;Landroid/view/View;)[I:GetCalculateDistanceToFinalSnap_Landroidx_recyclerview_widget_RecyclerView_LayoutManager_Landroid_view_View_Handler\nn_findSnapView:(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)Landroid/view/View;:GetFindSnapView_Landroidx_recyclerview_widget_RecyclerView_LayoutManager_Handler\n";
    private ArrayList refList;

    private native int[] n_calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view);

    private native View n_findSnapView(RecyclerView.LayoutManager layoutManager);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.StartSnapHelper, Xamarin.Forms.Platform.Android", StartSnapHelper.class, "n_calculateDistanceToFinalSnap:(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;Landroid/view/View;)[I:GetCalculateDistanceToFinalSnap_Landroidx_recyclerview_widget_RecyclerView_LayoutManager_Landroid_view_View_Handler\nn_findSnapView:(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)Landroid/view/View;:GetFindSnapView_Landroidx_recyclerview_widget_RecyclerView_LayoutManager_Handler\n");
    }

    public StartSnapHelper() {
        if (getClass() == StartSnapHelper.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.StartSnapHelper, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        return n_calculateDistanceToFinalSnap(layoutManager, view);
    }

    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return n_findSnapView(layoutManager);
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
