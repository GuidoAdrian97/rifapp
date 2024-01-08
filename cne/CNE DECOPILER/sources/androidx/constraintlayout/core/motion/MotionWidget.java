package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Set;

public class MotionWidget implements TypedValues {
    private float mProgress;
    float mTransitionPathRotate;
    Motion motion = new Motion();
    PropertySet propertySet = new PropertySet();
    WidgetFrame widgetFrame = new WidgetFrame();

    public class Motion {
        public int mAnimateCircleAngleTo = 0;
        public int mAnimateRelativeTo = -1;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public int mPathMotionArc = -1;
        public float mPathRotate = Float.NaN;
        public int mPolarRelativeTo = -1;
        public int mQuantizeInterpolatorID = -1;
        public String mQuantizeInterpolatorString = null;
        public int mQuantizeInterpolatorType = -3;
        public float mQuantizeMotionPhase = Float.NaN;
        public int mQuantizeMotionSteps = -1;
        public String mTransitionEasing = null;
    }

    public class PropertySet {
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;
        public int mVisibilityMode = 0;
        public int visibility = 4;
    }

    public MotionWidget findViewById(int i) {
        return null;
    }

    public MotionWidget getParent() {
        return null;
    }

    public boolean setValue(int i, boolean z) {
        return false;
    }

    public MotionWidget() {
    }

    public void setVisibility(int i) {
        this.propertySet.visibility = i;
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    public void layout(int i, int i2, int i3, int i4) {
        setBounds(i, i2, i3, i4);
    }

    public String toString() {
        return this.widgetFrame.left + ", " + this.widgetFrame.top + ", " + this.widgetFrame.right + ", " + this.widgetFrame.bottom;
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        if (this.widgetFrame == null) {
            this.widgetFrame = new WidgetFrame((ConstraintWidget) null);
        }
        WidgetFrame widgetFrame2 = this.widgetFrame;
        widgetFrame2.top = i2;
        widgetFrame2.left = i;
        widgetFrame2.right = i3;
        widgetFrame2.bottom = i4;
    }

    public MotionWidget(WidgetFrame widgetFrame2) {
        this.widgetFrame = widgetFrame2;
    }

    public boolean setValue(int i, int i2) {
        return setValueAttributes(i, (float) i2);
    }

    public boolean setValue(int i, float f) {
        if (setValueAttributes(i, f)) {
            return true;
        }
        return setValueMotion(i, f);
    }

    public boolean setValue(int i, String str) {
        return setValueMotion(i, str);
    }

    public boolean setValueMotion(int i, int i2) {
        switch (i) {
            case 605:
                this.motion.mAnimateRelativeTo = i2;
                return true;
            case 606:
                this.motion.mAnimateCircleAngleTo = i2;
                return true;
            case 607:
                this.motion.mPathMotionArc = i2;
                return true;
            case 608:
                this.motion.mDrawPath = i2;
                return true;
            case 609:
                this.motion.mPolarRelativeTo = i2;
                return true;
            case 610:
                this.motion.mQuantizeMotionSteps = i2;
                return true;
            case 611:
                this.motion.mQuantizeInterpolatorType = i2;
                return true;
            case 612:
                this.motion.mQuantizeInterpolatorID = i2;
                return true;
            default:
                return false;
        }
    }

    public boolean setValueMotion(int i, String str) {
        if (i == 603) {
            this.motion.mTransitionEasing = str;
            return true;
        } else if (i != 604) {
            return false;
        } else {
            this.motion.mQuantizeInterpolatorString = str;
            return true;
        }
    }

    public boolean setValueMotion(int i, float f) {
        switch (i) {
            case 600:
                this.motion.mMotionStagger = f;
                return true;
            case 601:
                this.motion.mPathRotate = f;
                return true;
            case 602:
                this.motion.mQuantizeMotionPhase = f;
                return true;
            default:
                return false;
        }
    }

    public boolean setValueAttributes(int i, float f) {
        switch (i) {
            case 303:
                this.widgetFrame.alpha = f;
                return true;
            case 304:
                this.widgetFrame.translationX = f;
                return true;
            case 305:
                this.widgetFrame.translationY = f;
                return true;
            case 306:
                this.widgetFrame.translationZ = f;
                return true;
            case 308:
                this.widgetFrame.rotationX = f;
                return true;
            case 309:
                this.widgetFrame.rotationY = f;
                return true;
            case 310:
                this.widgetFrame.rotationZ = f;
                return true;
            case 311:
                this.widgetFrame.scaleX = f;
                return true;
            case 312:
                this.widgetFrame.scaleY = f;
                return true;
            case 313:
                this.widgetFrame.pivotX = f;
                return true;
            case 314:
                this.widgetFrame.pivotY = f;
                return true;
            case 315:
                this.mProgress = f;
                return true;
            case 316:
                this.mTransitionPathRotate = f;
                return true;
            default:
                return false;
        }
    }

    public float getValueAttributes(int i) {
        switch (i) {
            case 303:
                return this.widgetFrame.alpha;
            case 304:
                return this.widgetFrame.translationX;
            case 305:
                return this.widgetFrame.translationY;
            case 306:
                return this.widgetFrame.translationZ;
            case 308:
                return this.widgetFrame.rotationX;
            case 309:
                return this.widgetFrame.rotationY;
            case 310:
                return this.widgetFrame.rotationZ;
            case 311:
                return this.widgetFrame.scaleX;
            case 312:
                return this.widgetFrame.scaleY;
            case 313:
                return this.widgetFrame.pivotX;
            case 314:
                return this.widgetFrame.pivotY;
            case 315:
                return this.mProgress;
            case 316:
                return this.mTransitionPathRotate;
            default:
                return Float.NaN;
        }
    }

    public int getId(String str) {
        int id = TypedValues.Attributes.CC.getId(str);
        if (id != -1) {
            return id;
        }
        return TypedValues.Motion.CC.getId(str);
    }

    public int getTop() {
        return this.widgetFrame.top;
    }

    public int getLeft() {
        return this.widgetFrame.left;
    }

    public int getBottom() {
        return this.widgetFrame.bottom;
    }

    public int getRight() {
        return this.widgetFrame.right;
    }

    public void setPivotX(float f) {
        this.widgetFrame.pivotX = f;
    }

    public void setPivotY(float f) {
        this.widgetFrame.pivotY = f;
    }

    public float getRotationX() {
        return this.widgetFrame.rotationX;
    }

    public void setRotationX(float f) {
        this.widgetFrame.rotationX = f;
    }

    public float getRotationY() {
        return this.widgetFrame.rotationY;
    }

    public void setRotationY(float f) {
        this.widgetFrame.rotationY = f;
    }

    public float getRotationZ() {
        return this.widgetFrame.rotationZ;
    }

    public void setRotationZ(float f) {
        this.widgetFrame.rotationZ = f;
    }

    public float getTranslationX() {
        return this.widgetFrame.translationX;
    }

    public void setTranslationX(float f) {
        this.widgetFrame.translationX = f;
    }

    public float getTranslationY() {
        return this.widgetFrame.translationY;
    }

    public void setTranslationY(float f) {
        this.widgetFrame.translationY = f;
    }

    public void setTranslationZ(float f) {
        this.widgetFrame.translationZ = f;
    }

    public float getTranslationZ() {
        return this.widgetFrame.translationZ;
    }

    public float getScaleX() {
        return this.widgetFrame.scaleX;
    }

    public void setScaleX(float f) {
        this.widgetFrame.scaleX = f;
    }

    public float getScaleY() {
        return this.widgetFrame.scaleY;
    }

    public void setScaleY(float f) {
        this.widgetFrame.scaleY = f;
    }

    public int getVisibility() {
        return this.propertySet.visibility;
    }

    public float getPivotX() {
        return this.widgetFrame.pivotX;
    }

    public float getPivotY() {
        return this.widgetFrame.pivotY;
    }

    public float getAlpha() {
        return this.propertySet.alpha;
    }

    public int getX() {
        return this.widgetFrame.left;
    }

    public int getY() {
        return this.widgetFrame.top;
    }

    public int getWidth() {
        WidgetFrame widgetFrame2 = this.widgetFrame;
        return widgetFrame2.right - widgetFrame2.left;
    }

    public int getHeight() {
        WidgetFrame widgetFrame2 = this.widgetFrame;
        return widgetFrame2.bottom - widgetFrame2.top;
    }

    public WidgetFrame getWidgetFrame() {
        return this.widgetFrame;
    }

    public Set getCustomAttributeNames() {
        return this.widgetFrame.getCustomAttributeNames();
    }

    public void setCustomAttribute(String str, int i, float f) {
        this.widgetFrame.setCustomAttribute(str, i, f);
    }

    public void setCustomAttribute(String str, int i, int i2) {
        this.widgetFrame.setCustomAttribute(str, i, i2);
    }

    public void setCustomAttribute(String str, int i, boolean z) {
        this.widgetFrame.setCustomAttribute(str, i, z);
    }

    public void setCustomAttribute(String str, int i, String str2) {
        this.widgetFrame.setCustomAttribute(str, i, str2);
    }

    public CustomVariable getCustomAttribute(String str) {
        return this.widgetFrame.getCustomAttribute(str);
    }
}
