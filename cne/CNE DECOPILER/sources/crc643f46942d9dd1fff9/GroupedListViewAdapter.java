package crc643f46942d9dd1fff9;

import android.content.Context;
import android.widget.SectionIndexer;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GroupedListViewAdapter extends ListViewAdapter implements SectionIndexer {
    public static final String __md_methods = "n_getPositionForSection:(I)I:GetGetPositionForSection_IHandler:Android.Widget.ISectionIndexerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_getSectionForPosition:(I)I:GetGetSectionForPosition_IHandler:Android.Widget.ISectionIndexerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_getSections:()[Ljava/lang/Object;:GetGetSectionsHandler:Android.Widget.ISectionIndexerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native int n_getPositionForSection(int i);

    private native int n_getSectionForPosition(int i);

    private native Object[] n_getSections();

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.GroupedListViewAdapter, Xamarin.Forms.Platform.Android", GroupedListViewAdapter.class, __md_methods);
    }

    public GroupedListViewAdapter() {
        if (getClass() == GroupedListViewAdapter.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.GroupedListViewAdapter, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public GroupedListViewAdapter(Context context) {
        if (getClass() == GroupedListViewAdapter.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.GroupedListViewAdapter, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public int getPositionForSection(int i) {
        return n_getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        return n_getSectionForPosition(i);
    }

    public Object[] getSections() {
        return n_getSections();
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
