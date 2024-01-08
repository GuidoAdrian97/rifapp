package androidx.dynamicanimation.animation;

import android.util.FloatProperty;
import androidx.annotation.RequiresApi;

public abstract class FloatPropertyCompat {
    final String mPropertyName;

    public abstract float getValue(Object obj);

    public abstract void setValue(Object obj, float f);

    public FloatPropertyCompat(String str) {
        this.mPropertyName = str;
    }

    @RequiresApi
    public static FloatPropertyCompat createFloatPropertyCompat(final FloatProperty floatProperty) {
        return new FloatPropertyCompat(floatProperty.getName()) {
            public float getValue(Object obj) {
                return ((Float) floatProperty.get(obj)).floatValue();
            }

            public void setValue(Object obj, float f) {
                floatProperty.setValue(obj, f);
            }
        };
    }
}
