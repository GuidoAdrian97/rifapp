package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;
import java.util.HashSet;

public abstract class MotionKey implements TypedValues {
    public static int UNSET = -1;
    public HashMap mCustom;
    public int mFramePosition;
    int mTargetId;
    String mTargetString = null;
    public int mType;

    public abstract void addValues(HashMap hashMap);

    public abstract MotionKey clone();

    public abstract void getAttributeNames(HashSet hashSet);

    public abstract /* synthetic */ int getId(String str);

    public void setInterpolation(HashMap hashMap) {
    }

    public boolean setValue(int i, float f) {
        return false;
    }

    public boolean setValue(int i, boolean z) {
        return false;
    }

    public MotionKey() {
        int i = UNSET;
        this.mFramePosition = i;
        this.mTargetId = i;
    }

    /* access modifiers changed from: package-private */
    public float toFloat(Object obj) {
        return obj instanceof Float ? ((Float) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    /* access modifiers changed from: package-private */
    public int toInt(Object obj) {
        return obj instanceof Integer ? ((Integer) obj).intValue() : Integer.parseInt(obj.toString());
    }

    public MotionKey copy(MotionKey motionKey) {
        this.mFramePosition = motionKey.mFramePosition;
        this.mTargetId = motionKey.mTargetId;
        this.mTargetString = motionKey.mTargetString;
        this.mType = motionKey.mType;
        return this;
    }

    public MotionKey setViewId(int i) {
        this.mTargetId = i;
        return this;
    }

    public void setFramePosition(int i) {
        this.mFramePosition = i;
    }

    public int getFramePosition() {
        return this.mFramePosition;
    }

    public boolean setValue(int i, int i2) {
        if (i != 100) {
            return false;
        }
        this.mFramePosition = i2;
        return true;
    }

    public boolean setValue(int i, String str) {
        if (i != 101) {
            return false;
        }
        this.mTargetString = str;
        return true;
    }

    public void setCustomAttribute(String str, int i, float f) {
        this.mCustom.put(str, new CustomVariable(str, i, f));
    }

    public void setCustomAttribute(String str, int i, int i2) {
        this.mCustom.put(str, new CustomVariable(str, i, i2));
    }

    public void setCustomAttribute(String str, int i, boolean z) {
        this.mCustom.put(str, new CustomVariable(str, i, z));
    }

    public void setCustomAttribute(String str, int i, String str2) {
        this.mCustom.put(str, new CustomVariable(str, i, str2));
    }
}
