package crc643f46942d9dd1fff9;

import android.graphics.drawable.AnimationDrawable;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormsAnimationDrawable extends AnimationDrawable implements IGCUserPeer {
    public static final String __md_methods = "n_isRunning:()Z:GetIsRunningHandler\nn_start:()V:GetStartHandler\nn_stop:()V:GetStopHandler\nn_selectDrawable:(I)Z:GetSelectDrawable_IHandler\n";
    private ArrayList refList;

    private native boolean n_isRunning();

    private native boolean n_selectDrawable(int i);

    private native void n_start();

    private native void n_stop();

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.FormsAnimationDrawable, Xamarin.Forms.Platform.Android", FormsAnimationDrawable.class, __md_methods);
    }

    public FormsAnimationDrawable() {
        if (getClass() == FormsAnimationDrawable.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsAnimationDrawable, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public boolean isRunning() {
        return n_isRunning();
    }

    public void start() {
        n_start();
    }

    public void stop() {
        n_stop();
    }

    public boolean selectDrawable(int i) {
        return n_selectDrawable(i);
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
