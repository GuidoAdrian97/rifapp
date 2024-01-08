package crc64564d9fd9e9d37347;

import android.content.Context;
import android.util.AttributeSet;
import crc643f46942d9dd1fff9.DatePickerRenderer;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DatePickerCtrlRenderer extends DatePickerRenderer {
    public static final String __md_methods = "";
    private ArrayList refList;

    static {
        Runtime.register("_00_PSIDM.Droid.Renderer.DatePickerCtrlRenderer, 00-PSIDM.Android", DatePickerCtrlRenderer.class, "");
    }

    public DatePickerCtrlRenderer(Context context) {
        super(context);
        if (getClass() == DatePickerCtrlRenderer.class) {
            TypeManager.Activate("_00_PSIDM.Droid.Renderer.DatePickerCtrlRenderer, 00-PSIDM.Android", "Android.Content.Context, Mono.Android", this, new Object[]{context});
        }
    }

    public DatePickerCtrlRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (getClass() == DatePickerCtrlRenderer.class) {
            TypeManager.Activate("_00_PSIDM.Droid.Renderer.DatePickerCtrlRenderer, 00-PSIDM.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[]{context, attributeSet});
        }
    }

    public DatePickerCtrlRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (getClass() == DatePickerCtrlRenderer.class) {
            TypeManager.Activate("_00_PSIDM.Droid.Renderer.DatePickerCtrlRenderer, 00-PSIDM.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[]{context, attributeSet, Integer.valueOf(i)});
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
