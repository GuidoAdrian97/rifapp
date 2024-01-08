package crc6460efe5dbc463cec9;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SplashActivity extends Activity implements IGCUserPeer {
    public static final String __md_methods = "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\n";
    private ArrayList refList;

    private native void n_onCreate(Bundle bundle);

    static {
        Runtime.register("_00_PSIDM.Droid.SplashActivity, 00-PSIDM.Android", SplashActivity.class, __md_methods);
    }

    public SplashActivity() {
        if (getClass() == SplashActivity.class) {
            TypeManager.Activate("_00_PSIDM.Droid.SplashActivity, 00-PSIDM.Android", "", this, new Object[0]);
        }
    }

    public void onCreate(Bundle bundle) {
        n_onCreate(bundle);
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
