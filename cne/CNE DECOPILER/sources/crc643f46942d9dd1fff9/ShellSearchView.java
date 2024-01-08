package crc643f46942d9dd1fff9;

import android.content.Context;
import android.text.Editable;
import android.text.NoCopySpan;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ShellSearchView extends FrameLayout implements IGCUserPeer, TextView.OnEditorActionListener, TextWatcher, NoCopySpan {
    public static final String __md_methods = "n_onAttachedToWindow:()V:GetOnAttachedToWindowHandler\nn_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\nn_onMeasure:(II)V:GetOnMeasure_IIHandler\nn_onEditorAction:(Landroid/widget/TextView;ILandroid/view/KeyEvent;)Z:GetOnEditorAction_Landroid_widget_TextView_ILandroid_view_KeyEvent_Handler:Android.Widget.TextView/IOnEditorActionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_afterTextChanged:(Landroid/text/Editable;)V:GetAfterTextChanged_Landroid_text_Editable_Handler:Android.Text.ITextWatcherInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_beforeTextChanged:(Ljava/lang/CharSequence;III)V:GetBeforeTextChanged_Ljava_lang_CharSequence_IIIHandler:Android.Text.ITextWatcherInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onTextChanged:(Ljava/lang/CharSequence;III)V:GetOnTextChanged_Ljava_lang_CharSequence_IIIHandler:Android.Text.ITextWatcherInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_afterTextChanged(Editable editable);

    private native void n_beforeTextChanged(CharSequence charSequence, int i, int i2, int i3);

    private native void n_onAttachedToWindow();

    private native boolean n_onEditorAction(TextView textView, int i, KeyEvent keyEvent);

    private native void n_onLayout(boolean z, int i, int i2, int i3, int i4);

    private native void n_onMeasure(int i, int i2);

    private native void n_onTextChanged(CharSequence charSequence, int i, int i2, int i3);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.ShellSearchView, Xamarin.Forms.Platform.Android", ShellSearchView.class, __md_methods);
    }

    public ShellSearchView(Context context) {
        super(context);
        if (getClass() == ShellSearchView.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ShellSearchView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public ShellSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == ShellSearchView.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ShellSearchView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public ShellSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == ShellSearchView.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ShellSearchView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public ShellSearchView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        if (getClass() == ShellSearchView.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.ShellSearchView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i), Integer.valueOf(i2)});
        }
    }

    public void onAttachedToWindow() {
        n_onAttachedToWindow();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        n_onLayout(z, i, i2, i3, i4);
    }

    public void onMeasure(int i, int i2) {
        n_onMeasure(i, i2);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return n_onEditorAction(textView, i, keyEvent);
    }

    public void afterTextChanged(Editable editable) {
        n_afterTextChanged(editable);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        n_beforeTextChanged(charSequence, i, i2, i3);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        n_onTextChanged(charSequence, i, i2, i3);
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
