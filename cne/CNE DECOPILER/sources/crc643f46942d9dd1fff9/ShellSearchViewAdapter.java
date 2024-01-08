package crc643f46942d9dd1fff9;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ShellSearchViewAdapter extends BaseAdapter implements IGCUserPeer, Filterable {
    public static final String __md_methods = "n_getCount:()I:GetGetCountHandler\nn_getItem:(I)Ljava/lang/Object;:GetGetItem_IHandler\nn_getItemId:(I)J:GetGetItemId_IHandler\nn_getView:(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;:GetGetView_ILandroid_view_View_Landroid_view_ViewGroup_Handler\nn_getFilter:()Landroid/widget/Filter;:GetGetFilterHandler:Android.Widget.IFilterableInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native int n_getCount();

    private native Filter n_getFilter();

    private native Object n_getItem(int i);

    private native long n_getItemId(int i);

    private native View n_getView(int i, View view, ViewGroup viewGroup);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.ShellSearchViewAdapter, Xamarin.Forms.Platform.Android", ShellSearchViewAdapter.class, __md_methods);
    }

    public ShellSearchViewAdapter() {
        if (getClass() == ShellSearchViewAdapter.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ShellSearchViewAdapter, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public int getCount() {
        return n_getCount();
    }

    public Object getItem(int i) {
        return n_getItem(i);
    }

    public long getItemId(int i) {
        return n_getItemId(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return n_getView(i, view, viewGroup);
    }

    public Filter getFilter() {
        return n_getFilter();
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
