package crc643f46942d9dd1fff9;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class CenterSnapHelper extends NongreedySnapHelper {
    public static final String __md_methods = "n_findSnapView:(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)Landroid/view/View;:GetFindSnapView_Landroidx_recyclerview_widget_RecyclerView_LayoutManager_Handler\n";
    private ArrayList refList;

    private native View n_findSnapView(RecyclerView.LayoutManager layoutManager);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.CenterSnapHelper, Xamarin.Forms.Platform.Android", CenterSnapHelper.class, __md_methods);
    }

    public CenterSnapHelper() {
        if (getClass() == CenterSnapHelper.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.CenterSnapHelper, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
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
