package mono.androidx.appcompat.widget;

import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchView_OnSuggestionListenerImplementor implements IGCUserPeer, SearchView.OnSuggestionListener {
    public static final String __md_methods = "n_onSuggestionClick:(I)Z:GetOnSuggestionClick_IHandler:AndroidX.AppCompat.Widget.SearchView/IOnSuggestionListenerInvoker, Xamarin.AndroidX.AppCompat\nn_onSuggestionSelect:(I)Z:GetOnSuggestionSelect_IHandler:AndroidX.AppCompat.Widget.SearchView/IOnSuggestionListenerInvoker, Xamarin.AndroidX.AppCompat\n";
    private ArrayList refList;

    private native boolean n_onSuggestionClick(int i);

    private native boolean n_onSuggestionSelect(int i);

    static {
        Runtime.register("AndroidX.AppCompat.Widget.SearchView+IOnSuggestionListenerImplementor, Xamarin.AndroidX.AppCompat", SearchView_OnSuggestionListenerImplementor.class, __md_methods);
    }

    public SearchView_OnSuggestionListenerImplementor() {
        if (getClass() == SearchView_OnSuggestionListenerImplementor.class) {
            TypeManager.Activate("AndroidX.AppCompat.Widget.SearchView+IOnSuggestionListenerImplementor, Xamarin.AndroidX.AppCompat", "", this, new Object[0]);
        }
    }

    public boolean onSuggestionClick(int i) {
        return n_onSuggestionClick(i);
    }

    public boolean onSuggestionSelect(int i) {
        return n_onSuggestionSelect(i);
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
