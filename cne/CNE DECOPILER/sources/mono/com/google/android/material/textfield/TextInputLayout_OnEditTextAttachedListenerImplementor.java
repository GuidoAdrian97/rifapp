package mono.com.google.android.material.textfield;

import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TextInputLayout_OnEditTextAttachedListenerImplementor implements IGCUserPeer, TextInputLayout.OnEditTextAttachedListener {
    public static final String __md_methods = "n_onEditTextAttached:(Lcom/google/android/material/textfield/TextInputLayout;)V:GetOnEditTextAttached_Lcom_google_android_material_textfield_TextInputLayout_Handler:Google.Android.Material.TextField.TextInputLayout/IOnEditTextAttachedListenerInvoker, Xamarin.Google.Android.Material\n";
    private ArrayList refList;

    private native void n_onEditTextAttached(TextInputLayout textInputLayout);

    static {
        Runtime.register("Google.Android.Material.TextField.TextInputLayout+IOnEditTextAttachedListenerImplementor, Xamarin.Google.Android.Material", TextInputLayout_OnEditTextAttachedListenerImplementor.class, __md_methods);
    }

    public TextInputLayout_OnEditTextAttachedListenerImplementor() {
        if (getClass() == TextInputLayout_OnEditTextAttachedListenerImplementor.class) {
            TypeManager.Activate("Google.Android.Material.TextField.TextInputLayout+IOnEditTextAttachedListenerImplementor, Xamarin.Google.Android.Material", "", this, new Object[0]);
        }
    }

    public void onEditTextAttached(TextInputLayout textInputLayout) {
        n_onEditTextAttached(textInputLayout);
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
