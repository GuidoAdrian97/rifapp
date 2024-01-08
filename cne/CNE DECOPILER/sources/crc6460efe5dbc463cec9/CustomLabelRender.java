package crc6460efe5dbc463cec9;

import android.content.Context;
import android.util.AttributeSet;
import crc643f46942d9dd1fff9.LabelRenderer;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class CustomLabelRender extends LabelRenderer {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("_00_PSIDM.Droid.CustomLabelRender, 00-PSIDM.Android", CustomLabelRender.class, "");
    }

    public CustomLabelRender(Context context) {
        super(context);
        if (getClass() == CustomLabelRender.class) {
            TypeManager.Activate("_00_PSIDM.Droid.CustomLabelRender, 00-PSIDM.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public CustomLabelRender(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == CustomLabelRender.class) {
            TypeManager.Activate("_00_PSIDM.Droid.CustomLabelRender, 00-PSIDM.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public CustomLabelRender(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == CustomLabelRender.class) {
            TypeManager.Activate("_00_PSIDM.Droid.CustomLabelRender, 00-PSIDM.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
        }
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
