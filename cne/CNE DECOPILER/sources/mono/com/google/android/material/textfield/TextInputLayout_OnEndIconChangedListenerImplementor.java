package mono.com.google.android.material.textfield;

import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TextInputLayout_OnEndIconChangedListenerImplementor implements IGCUserPeer, TextInputLayout.OnEndIconChangedListener {
    public static final String __md_methods = "n_onEndIconChanged:(Lcom/google/android/material/textfield/TextInputLayout;I)V:GetOnEndIconChanged_Lcom_google_android_material_textfield_TextInputLayout_IHandler:Google.Android.Material.TextField.TextInputLayout/IOnEndIconChangedListenerInvoker, Xamarin.Google.Android.Material\n";
    private ArrayList refList;

    private native void n_onEndIconChanged(TextInputLayout textInputLayout, int i);

    static {
        Runtime.register("Google.Android.Material.TextField.TextInputLayout+IOnEndIconChangedListenerImplementor, Xamarin.Google.Android.Material", TextInputLayout_OnEndIconChangedListenerImplementor.class, __md_methods);
    }

    public TextInputLayout_OnEndIconChangedListenerImplementor() {
        if (getClass() == TextInputLayout_OnEndIconChangedListenerImplementor.class) {
            TypeManager.Activate("Google.Android.Material.TextField.TextInputLayout+IOnEndIconChangedListenerImplementor, Xamarin.Google.Android.Material", "", this, new Object[0]);
        }
    }

    public void onEndIconChanged(TextInputLayout textInputLayout, int i) {
        n_onEndIconChanged(textInputLayout, i);
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
