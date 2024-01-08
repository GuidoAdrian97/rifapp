package crc64720bb2db43a66fe9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FragmentContainer extends Fragment implements IGCUserPeer {
    public static final String __md_methods = "n_onCreateView:(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;:GetOnCreateView_Landroid_view_LayoutInflater_Landroid_view_ViewGroup_Landroid_os_Bundle_Handler\nn_onDestroyView:()V:GetOnDestroyViewHandler\nn_onHiddenChanged:(Z)V:GetOnHiddenChanged_ZHandler\nn_onPause:()V:GetOnPauseHandler\nn_onResume:()V:GetOnResumeHandler\n";
    private ArrayList refList;

    private native View n_onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    private native void n_onDestroyView();

    private native void n_onHiddenChanged(boolean z);

    private native void n_onPause();

    private native void n_onResume();

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.AppCompat.FragmentContainer, Xamarin.Forms.Platform.Android", FragmentContainer.class, __md_methods);
    }

    public FragmentContainer() {
        if (getClass() == FragmentContainer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.FragmentContainer, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public FragmentContainer(int i) {
        super(i);
        if (getClass() == FragmentContainer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.FragmentContainer, Xamarin.Forms.Platform.Android", "System.Int32, mscorlib", this, new Object[]{Integer.valueOf(i)});
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return n_onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        n_onDestroyView();
    }

    public void onHiddenChanged(boolean z) {
        n_onHiddenChanged(z);
    }

    public void onPause() {
        n_onPause();
    }

    public void onResume() {
        n_onResume();
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
