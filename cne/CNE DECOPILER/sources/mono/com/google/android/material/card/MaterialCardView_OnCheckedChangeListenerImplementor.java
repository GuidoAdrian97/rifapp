package mono.com.google.android.material.card;

import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialCardView_OnCheckedChangeListenerImplementor implements IGCUserPeer, MaterialCardView.OnCheckedChangeListener {
    public static final String __md_methods = "n_onCheckedChanged:(Lcom/google/android/material/card/MaterialCardView;Z)V:GetOnCheckedChanged_Lcom_google_android_material_card_MaterialCardView_ZHandler:Google.Android.Material.Card.MaterialCardView/IOnCheckedChangeListenerInvoker, Xamarin.Google.Android.Material\n";
    private ArrayList refList;

    private native void n_onCheckedChanged(MaterialCardView materialCardView, boolean z);

    static {
        Runtime.register("Google.Android.Material.Card.MaterialCardView+IOnCheckedChangeListenerImplementor, Xamarin.Google.Android.Material", MaterialCardView_OnCheckedChangeListenerImplementor.class, __md_methods);
    }

    public MaterialCardView_OnCheckedChangeListenerImplementor() {
        if (getClass() == MaterialCardView_OnCheckedChangeListenerImplementor.class) {
            TypeManager.Activate("Google.Android.Material.Card.MaterialCardView+IOnCheckedChangeListenerImplementor, Xamarin.Google.Android.Material", "", this, new Object[0]);
        }
    }

    public void onCheckedChanged(MaterialCardView materialCardView, boolean z) {
        n_onCheckedChanged(materialCardView, z);
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
