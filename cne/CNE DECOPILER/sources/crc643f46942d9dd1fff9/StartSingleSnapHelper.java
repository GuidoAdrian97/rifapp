package crc643f46942d9dd1fff9;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class StartSingleSnapHelper extends SingleSnapHelper {
    public static final String __md_methods = "n_calculateDistanceToFinalSnap:(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;Landroid/view/View;)[I:GetCalculateDistanceToFinalSnap_Landroidx_recyclerview_widget_RecyclerView_LayoutManager_Landroid_view_View_Handler\n";
    private ArrayList refList;

    private native int[] n_calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.StartSingleSnapHelper, Xamarin.Forms.Platform.Android", StartSingleSnapHelper.class, "n_calculateDistanceToFinalSnap:(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;Landroid/view/View;)[I:GetCalculateDistanceToFinalSnap_Landroidx_recyclerview_widget_RecyclerView_LayoutManager_Landroid_view_View_Handler\n");
    }

    public StartSingleSnapHelper() {
        if (getClass() == StartSingleSnapHelper.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.StartSingleSnapHelper, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        return n_calculateDistanceToFinalSnap(layoutManager, view);
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
