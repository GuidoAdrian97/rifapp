package crc6480997b3ef81bf9b2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ZXingSurfaceView extends SurfaceView implements IGCUserPeer, SurfaceHolder.Callback {
    public static final String __md_methods = "n_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onAttachedToWindow:()V:GetOnAttachedToWindowHandler\nn_onWindowVisibilityChanged:(I)V:GetOnWindowVisibilityChanged_IHandler\nn_onWindowFocusChanged:(Z)V:GetOnWindowFocusChanged_ZHandler\nn_surfaceChanged:(Landroid/view/SurfaceHolder;III)V:GetSurfaceChanged_Landroid_view_SurfaceHolder_IIIHandler:Android.Views.ISurfaceHolderCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_surfaceCreated:(Landroid/view/SurfaceHolder;)V:GetSurfaceCreated_Landroid_view_SurfaceHolder_Handler:Android.Views.ISurfaceHolderCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_surfaceDestroyed:(Landroid/view/SurfaceHolder;)V:GetSurfaceDestroyed_Landroid_view_SurfaceHolder_Handler:Android.Views.ISurfaceHolderCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onAttachedToWindow();

    private native boolean n_onTouchEvent(MotionEvent motionEvent);

    private native void n_onWindowFocusChanged(boolean z);

    private native void n_onWindowVisibilityChanged(int i);

    private native void n_surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3);

    private native void n_surfaceCreated(SurfaceHolder surfaceHolder);

    private native void n_surfaceDestroyed(SurfaceHolder surfaceHolder);

    static {
        Runtime.register("ZXing.Mobile.ZXingSurfaceView, ZXingNetMobile", ZXingSurfaceView.class, __md_methods);
    }

    public ZXingSurfaceView(Context context) {
        super(context);
        if (getClass() == ZXingSurfaceView.class) {
            TypeManager.Activate("ZXing.Mobile.ZXingSurfaceView, ZXingNetMobile", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public ZXingSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == ZXingSurfaceView.class) {
            TypeManager.Activate("ZXing.Mobile.ZXingSurfaceView, ZXingNetMobile", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public ZXingSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == ZXingSurfaceView.class) {
            TypeManager.Activate("ZXing.Mobile.ZXingSurfaceView, ZXingNetMobile", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return n_onTouchEvent(motionEvent);
    }

    public void onAttachedToWindow() {
        n_onAttachedToWindow();
    }

    public void onWindowVisibilityChanged(int i) {
        n_onWindowVisibilityChanged(i);
    }

    public void onWindowFocusChanged(boolean z) {
        n_onWindowFocusChanged(z);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        n_surfaceChanged(surfaceHolder, i, i2, i3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        n_surfaceCreated(surfaceHolder);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        n_surfaceDestroyed(surfaceHolder);
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
