package mono.androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RecyclerView_OnChildAttachStateChangeListenerImplementor implements IGCUserPeer, RecyclerView.OnChildAttachStateChangeListener {
    public static final String __md_methods = "n_onChildViewAttachedToWindow:(Landroid/view/View;)V:GetOnChildViewAttachedToWindow_Landroid_view_View_Handler:AndroidX.RecyclerView.Widget.RecyclerView/IOnChildAttachStateChangeListenerInvoker, Xamarin.AndroidX.RecyclerView\nn_onChildViewDetachedFromWindow:(Landroid/view/View;)V:GetOnChildViewDetachedFromWindow_Landroid_view_View_Handler:AndroidX.RecyclerView.Widget.RecyclerView/IOnChildAttachStateChangeListenerInvoker, Xamarin.AndroidX.RecyclerView\n";
    private ArrayList refList;

    private native void n_onChildViewAttachedToWindow(View view);

    private native void n_onChildViewDetachedFromWindow(View view);

    static {
        Runtime.register("AndroidX.RecyclerView.Widget.RecyclerView+IOnChildAttachStateChangeListenerImplementor, Xamarin.AndroidX.RecyclerView", RecyclerView_OnChildAttachStateChangeListenerImplementor.class, __md_methods);
    }

    public RecyclerView_OnChildAttachStateChangeListenerImplementor() {
        if (getClass() == RecyclerView_OnChildAttachStateChangeListenerImplementor.class) {
            TypeManager.Activate("AndroidX.RecyclerView.Widget.RecyclerView+IOnChildAttachStateChangeListenerImplementor, Xamarin.AndroidX.RecyclerView", "", this, new Object[0]);
        }
    }

    public void onChildViewAttachedToWindow(View view) {
        n_onChildViewAttachedToWindow(view);
    }

    public void onChildViewDetachedFromWindow(View view) {
        n_onChildViewDetachedFromWindow(view);
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
