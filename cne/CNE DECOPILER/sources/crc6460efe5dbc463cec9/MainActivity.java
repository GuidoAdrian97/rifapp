package crc6460efe5dbc463cec9;

import android.os.Bundle;
import crc643f46942d9dd1fff9.FormsAppCompatActivity;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MainActivity extends FormsAppCompatActivity {
    public static final String __md_methods = "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onRequestPermissionsResult:(I[Ljava/lang/String;[I)V:GetOnRequestPermissionsResult_IarrayLjava_lang_String_arrayIHandler\n";
    private ArrayList refList;

    private native void n_onCreate(Bundle bundle);

    private native void n_onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    static {
        Runtime.register("_00_PSIDM.Droid.MainActivity, 00-PSIDM.Android", MainActivity.class, __md_methods);
    }

    public MainActivity() {
        if (getClass() == MainActivity.class) {
            TypeManager.Activate("_00_PSIDM.Droid.MainActivity, 00-PSIDM.Android", "", this, new Object[0]);
        }
    }

    public MainActivity(int i) {
        super(i);
        if (getClass() == MainActivity.class) {
            TypeManager.Activate("_00_PSIDM.Droid.MainActivity, 00-PSIDM.Android", "System.Int32, mscorlib", this, new Object[]{Integer.valueOf(i)});
        }
    }

    public void onCreate(Bundle bundle) {
        n_onCreate(bundle);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        n_onRequestPermissionsResult(i, strArr, iArr);
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
