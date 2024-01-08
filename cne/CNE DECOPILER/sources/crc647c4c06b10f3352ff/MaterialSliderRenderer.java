package crc647c4c06b10f3352ff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MaterialSliderRenderer extends SeekBar implements IGCUserPeer, SeekBar.OnSeekBarChangeListener {
    public static final String __md_methods = "n_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onProgressChanged:(Landroid/widget/SeekBar;IZ)V:GetOnProgressChanged_Landroid_widget_SeekBar_IZHandler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStartTrackingTouch:(Landroid/widget/SeekBar;)V:GetOnStartTrackingTouch_Landroid_widget_SeekBar_Handler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStopTrackingTouch:(Landroid/widget/SeekBar;)V:GetOnStopTrackingTouch_Landroid_widget_SeekBar_Handler:Android.Widget.SeekBar/IOnSeekBarChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onProgressChanged(SeekBar seekBar, int i, boolean z);

    private native void n_onStartTrackingTouch(SeekBar seekBar);

    private native void n_onStopTrackingTouch(SeekBar seekBar);

    private native boolean n_onTouchEvent(MotionEvent motionEvent);

    static {
        Runtime.register("Xamarin.Forms.Material.Android.MaterialSliderRenderer, Xamarin.Forms.Material", MaterialSliderRenderer.class, __md_methods);
    }

    public MaterialSliderRenderer(Context context) {
        super(context);
        if (getClass() == MaterialSliderRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialSliderRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public MaterialSliderRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == MaterialSliderRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialSliderRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public MaterialSliderRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == MaterialSliderRenderer.class) {
            TypeManager.Activate("Xamarin.Forms.Material.Android.MaterialSliderRenderer, Xamarin.Forms.Material", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return n_onTouchEvent(motionEvent);
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
