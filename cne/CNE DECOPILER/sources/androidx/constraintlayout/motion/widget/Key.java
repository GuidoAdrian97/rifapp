package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Key {
    public static int UNSET = -1;
    HashMap mCustomConstraints;
    int mFramePosition;
    int mTargetId;
    String mTargetString = null;
    protected int mType;

    public abstract void addValues(HashMap hashMap);

    public abstract Key clone();

    /* access modifiers changed from: package-private */
    public abstract void getAttributeNames(HashSet hashSet);

    /* access modifiers changed from: package-private */
    public abstract void load(Context context, AttributeSet attributeSet);

    public void setInterpolation(HashMap hashMap) {
    }

    public abstract void setValue(String str, Object obj);

    public Key() {
        int i = UNSET;
        this.mFramePosition = i;
        this.mTargetId = i;
    }

    /* access modifiers changed from: package-private */
    public boolean matches(String str) {
        String str2 = this.mTargetString;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    /* access modifiers changed from: package-private */
    public float toFloat(Object obj) {
        return obj instanceof Float ? ((Float) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    /* access modifiers changed from: package-private */
    public int toInt(Object obj) {
        return obj instanceof Integer ? ((Integer) obj).intValue() : Integer.parseInt(obj.toString());
    }

    /* access modifiers changed from: package-private */
    public boolean toBoolean(Object obj) {
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : Boolean.parseBoolean(obj.toString());
    }

    public Key copy(Key key) {
        this.mFramePosition = key.mFramePosition;
        this.mTargetId = key.mTargetId;
        this.mTargetString = key.mTargetString;
        this.mType = key.mType;
        this.mCustomConstraints = key.mCustomConstraints;
        return this;
    }

    public Key setViewId(int i) {
        this.mTargetId = i;
        return this;
    }

    public void setFramePosition(int i) {
        this.mFramePosition = i;
    }

    public int getFramePosition() {
        return this.mFramePosition;
    }
}
