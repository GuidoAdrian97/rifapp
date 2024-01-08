package androidx.constraintlayout.motion.widget;

import android.graphics.RectF;
import android.view.View;
import java.util.HashSet;

abstract class KeyPositionBase extends Key {
    int mCurveFit = Key.UNSET;

    /* access modifiers changed from: package-private */
    public void getAttributeNames(HashSet hashSet) {
    }

    public abstract boolean intersects(int i, int i2, RectF rectF, RectF rectF2, float f, float f2);

    /* access modifiers changed from: package-private */
    public abstract void positionAttributes(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr);

    KeyPositionBase() {
    }
}
