package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RefreshViewRenderer extends SwipeRefreshLayout implements IGCUserPeer, SwipeRefreshLayout.OnRefreshListener {
    public static final String __md_methods = "n_isRefreshing:()Z:GetIsRefreshingHandler\nn_setRefreshing:(Z)V:GetSetRefreshing_ZHandler\nn_canChildScrollUp:()Z:GetCanChildScrollUpHandler\nn_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\nn_onRefresh:()V:GetOnRefreshHandler:AndroidX.SwipeRefreshLayout.Widget.SwipeRefreshLayout/IOnRefreshListenerInvoker, Xamarin.AndroidX.SwipeRefreshLayout\n";
    private ArrayList refList;

    private native boolean n_canChildScrollUp();

    private native boolean n_isRefreshing();

    private native void n_onLayout(boolean z, int i, int i2, int i3, int i4);

    private native void n_onRefresh();

    private native void n_setRefreshing(boolean z);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.RefreshViewRenderer, Xamarin.Forms.Platform.Android", RefreshViewRenderer.class, __md_methods);
    }

    public RefreshViewRenderer(Context context) {
        super(context);
        if (getClass() == RefreshViewRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.RefreshViewRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public RefreshViewRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == RefreshViewRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.RefreshViewRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public boolean isRefreshing() {
        return n_isRefreshing();
    }

    public void setRefreshing(boolean z) {
        n_setRefreshing(z);
    }

    public boolean canChildScrollUp() {
        return n_canChildScrollUp();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        n_onLayout(z, i, i2, i3, i4);
    }

    public void onRefresh() {
        n_onRefresh();
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
