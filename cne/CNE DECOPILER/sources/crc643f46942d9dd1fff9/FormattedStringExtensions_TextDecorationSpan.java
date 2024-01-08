package crc643f46942d9dd1fff9;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormattedStringExtensions_TextDecorationSpan extends MetricAffectingSpan implements IGCUserPeer {
    public static final String __md_methods = "n_updateDrawState:(Landroid/text/TextPaint;)V:GetUpdateDrawState_Landroid_text_TextPaint_Handler\nn_updateMeasureState:(Landroid/text/TextPaint;)V:GetUpdateMeasureState_Landroid_text_TextPaint_Handler\n";
    private ArrayList refList;

    private native void n_updateDrawState(TextPaint textPaint);

    private native void n_updateMeasureState(TextPaint textPaint);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.FormattedStringExtensions+TextDecorationSpan, Xamarin.Forms.Platform.Android", FormattedStringExtensions_TextDecorationSpan.class, "n_updateDrawState:(Landroid/text/TextPaint;)V:GetUpdateDrawState_Landroid_text_TextPaint_Handler\nn_updateMeasureState:(Landroid/text/TextPaint;)V:GetUpdateMeasureState_Landroid_text_TextPaint_Handler\n");
    }

    public FormattedStringExtensions_TextDecorationSpan() {
        if (getClass() == FormattedStringExtensions_TextDecorationSpan.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.FormattedStringExtensions+TextDecorationSpan, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        n_updateDrawState(textPaint);
    }

    public void updateMeasureState(TextPaint textPaint) {
        n_updateMeasureState(textPaint);
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
