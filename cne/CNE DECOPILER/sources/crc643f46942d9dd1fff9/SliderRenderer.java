package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SliderRenderer extends ViewRenderer_2 implements SeekBar.OnSeekBarChangeListener {
    public static final String __md_methods = "n_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\nn_onProgressChanged:(Landroid/widget/SeekBar;IZ)V:GetOnProgressChanged_Landroid_widget_SeekBar_IZHandler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStartTrackingTouch:(Landroid/widget/SeekBar;)V:GetOnStartTrackingTouch_Landroid_widget_SeekBar_Handler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStopTrackingTouch:(Landroid/widget/SeekBar;)V:GetOnStopTrackingTouch_Landroid_widget_SeekBar_Handler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onLayout(boolean z, int i, int i2, int i3, int i4);

    private native void n_onProgressChanged(SeekBar seekBar, int i, boolean z);

    private native void n_onStartTrackingTouch(SeekBar seekBar);

    private native void n_onStopTrackingTouch(SeekBar seekBar);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.SliderRenderer, Xamarin.Forms.Platform.Android", SliderRenderer.class, __md_methods);
    }

    public SliderRenderer(Context context) {
        super(context);
        if (getClass() == SliderRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.SliderRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public SliderRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == SliderRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.SliderRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public SliderRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == SliderRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.SliderRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        n_onLayout(z, i, i2, i3, i4);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        n_onProgressChanged(seekBar, i, z);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        n_onStartTrackingTouch(seekBar);
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        n_onStopTrackingTouch(seekBar);
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
