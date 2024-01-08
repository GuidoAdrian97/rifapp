package crc64720bb2db43a66fe9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ShellFragmentContainer extends FragmentContainer {
    public static final String __md_methods = "n_onCreateView:(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;:GetOnCreateView_Landroid_view_LayoutInflater_Landroid_view_ViewGroup_Landroid_os_Bundle_Handler\nn_onDestroyView:()V:GetOnDestroyViewHandler\nn_onDestroy:()V:GetOnDestroyHandler\n";
    private ArrayList refList;

    private native View n_onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    private native void n_onDestroy();

    private native void n_onDestroyView();

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.AppCompat.ShellFragmentContainer, Xamarin.Forms.Platform.Android", ShellFragmentContainer.class, __md_methods);
    }

    public ShellFragmentContainer() {
        if (getClass() == ShellFragmentContainer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.ShellFragmentContainer, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public ShellFragmentContainer(int i) {
        super(i);
        if (getClass() == ShellFragmentContainer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.ShellFragmentContainer, Xamarin.Forms.Platform.Android", "System.Int32, mscorlib", this, new Object[]{Integer.valueOf(i)});
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return n_onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        n_onDestroyView();
    }

    public void onDestroy() {
        n_onDestroy();
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
