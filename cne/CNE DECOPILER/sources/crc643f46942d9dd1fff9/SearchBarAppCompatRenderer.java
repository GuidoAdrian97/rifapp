package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchBarAppCompatRenderer extends ViewRenderer_2 implements SearchView.OnQueryTextListener {
    public static final String __md_methods = "n_onQueryTextChange:(Ljava/lang/String;)Z:GetOnQueryTextChange_Ljava_lang_String_Handler:AndroidX.AppCompat.Widget.SearchView/IOnQueryTextListenerInvoker, Xamarin.AndroidX.AppCompat\nn_onQueryTextSubmit:(Ljava/lang/String;)Z:GetOnQueryTextSubmit_Ljava_lang_String_Handler:AndroidX.AppCompat.Widget.SearchView/IOnQueryTextListenerInvoker, Xamarin.AndroidX.AppCompat\n";
    private ArrayList refList;

    private native boolean n_onQueryTextChange(String str);

    private native boolean n_onQueryTextSubmit(String str);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.SearchBarAppCompatRenderer, Xamarin.Forms.Platform.Android", SearchBarAppCompatRenderer.class, "n_onQueryTextChange:(Ljava/lang/String;)Z:GetOnQueryTextChange_Ljava_lang_String_Handler:AndroidX.AppCompat.Widget.SearchView/IOnQueryTextListenerInvoker, Xamarin.AndroidX.AppCompat\nn_onQueryTextSubmit:(Ljava/lang/String;)Z:GetOnQueryTextSubmit_Ljava_lang_String_Handler:AndroidX.AppCompat.Widget.SearchView/IOnQueryTextListenerInvoker, Xamarin.AndroidX.AppCompat\n");
    }

    public SearchBarAppCompatRenderer(Context context) {
        super(context);
        if (getClass() == SearchBarAppCompatRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.SearchBarAppCompatRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public SearchBarAppCompatRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == SearchBarAppCompatRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.SearchBarAppCompatRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public SearchBarAppCompatRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == SearchBarAppCompatRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.SearchBarAppCompatRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public boolean onQueryTextChange(String str) {
        return n_onQueryTextChange(str);
    }

    public boolean onQueryTextSubmit(String str) {
        return n_onQueryTextSubmit(str);
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
