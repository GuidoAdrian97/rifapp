package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.Motion;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.HashMap;

public class Transition {
    HashMap keyPositions = new HashMap();
    private int pathMotionArc = -1;
    HashMap state = new HashMap();

    public KeyPosition findPreviousPosition(String str, int i) {
        KeyPosition keyPosition;
        while (i >= 0) {
            HashMap hashMap = (HashMap) this.keyPositions.get(Integer.valueOf(i));
            if (hashMap != null && (keyPosition = (KeyPosition) hashMap.get(str)) != null) {
                return keyPosition;
            }
            i--;
        }
        return null;
    }

    public KeyPosition findNextPosition(String str, int i) {
        KeyPosition keyPosition;
        while (i <= 100) {
            HashMap hashMap = (HashMap) this.keyPositions.get(Integer.valueOf(i));
            if (hashMap != null && (keyPosition = (KeyPosition) hashMap.get(str)) != null) {
                return keyPosition;
            }
            i++;
        }
        return null;
    }

    public int getNumberKeyPositions(WidgetFrame widgetFrame) {
        int i = 0;
        for (int i2 = 0; i2 <= 100; i2++) {
            HashMap hashMap = (HashMap) this.keyPositions.get(Integer.valueOf(i2));
            if (!(hashMap == null || ((KeyPosition) hashMap.get(widgetFrame.widget.stringId)) == null)) {
                i++;
            }
        }
        return i;
    }

    public Motion getMotion(String str) {
        return getWidgetState(str, (ConstraintWidget) null, 0).motionControl;
    }

    public void fillKeyPositions(WidgetFrame widgetFrame, float[] fArr, float[] fArr2, float[] fArr3) {
        KeyPosition keyPosition;
        int i = 0;
        for (int i2 = 0; i2 <= 100; i2++) {
            HashMap hashMap = (HashMap) this.keyPositions.get(Integer.valueOf(i2));
            if (!(hashMap == null || (keyPosition = (KeyPosition) hashMap.get(widgetFrame.widget.stringId)) == null)) {
                fArr[i] = keyPosition.x;
                fArr2[i] = keyPosition.y;
                fArr3[i] = (float) keyPosition.frame;
                i++;
            }
        }
    }

    public boolean hasPositionKeyframes() {
        return this.keyPositions.size() > 0;
    }

    public void setTransitionProperties(TypedBundle typedBundle) {
        this.pathMotionArc = typedBundle.getInteger(509);
    }

    class WidgetState {
        WidgetFrame end = new WidgetFrame();
        WidgetFrame interpolated = new WidgetFrame();
        Motion motionControl;
        MotionWidget motionWidgetEnd = new MotionWidget(this.end);
        MotionWidget motionWidgetInterpolated = new MotionWidget(this.interpolated);
        MotionWidget motionWidgetStart = new MotionWidget(this.start);
        KeyCache myKeyCache = new KeyCache();
        int myParentHeight = -1;
        int myParentWidth = -1;
        WidgetFrame start = new WidgetFrame();

        public WidgetState() {
            Motion motion = new Motion(this.motionWidgetStart);
            this.motionControl = motion;
            motion.setStart(this.motionWidgetStart);
            this.motionControl.setEnd(this.motionWidgetEnd);
        }

        public void setKeyPosition(TypedBundle typedBundle) {
            MotionKeyPosition motionKeyPosition = new MotionKeyPosition();
            typedBundle.applyDelta(motionKeyPosition);
            this.motionControl.addKey(motionKeyPosition);
        }

        public void setKeyAttribute(TypedBundle typedBundle) {
            MotionKeyAttributes motionKeyAttributes = new MotionKeyAttributes();
            typedBundle.applyDelta(motionKeyAttributes);
            this.motionControl.addKey(motionKeyAttributes);
        }

        public void setKeyCycle(TypedBundle typedBundle) {
            MotionKeyCycle motionKeyCycle = new MotionKeyCycle();
            typedBundle.applyDelta(motionKeyCycle);
            this.motionControl.addKey(motionKeyCycle);
        }

        public void update(ConstraintWidget constraintWidget, int i) {
            if (i == 0) {
                this.start.update(constraintWidget);
                this.motionControl.setStart(this.motionWidgetStart);
            } else if (i == 1) {
                this.end.update(constraintWidget);
                this.motionControl.setEnd(this.motionWidgetEnd);
            }
            this.myParentWidth = -1;
        }

        public WidgetFrame getFrame(int i) {
            if (i == 0) {
                return this.start;
            }
            if (i == 1) {
                return this.end;
            }
            return this.interpolated;
        }

        public void interpolate(int i, int i2, float f, Transition transition) {
            this.myParentHeight = i2;
            this.myParentWidth = i;
            this.motionControl.setup(i, i2, 1.0f, System.nanoTime());
            WidgetFrame.interpolate(i, i2, this.interpolated, this.start, this.end, transition, f);
            this.interpolated.interpolatedPos = f;
            this.motionControl.interpolate(this.motionWidgetInterpolated, f, System.nanoTime(), this.myKeyCache);
        }
    }

    class KeyPosition {
        int frame;
        String target;
        int type;
        float x;
        float y;

        public KeyPosition(String str, int i, int i2, float f, float f2) {
            this.target = str;
            this.frame = i;
            this.type = i2;
            this.x = f;
            this.y = f2;
        }
    }

    public boolean isEmpty() {
        return this.state.isEmpty();
    }

    public void clear() {
        this.state.clear();
    }

    public boolean contains(String str) {
        return this.state.containsKey(str);
    }

    public void addKeyPosition(String str, TypedBundle typedBundle) {
        getWidgetState(str, (ConstraintWidget) null, 0).setKeyPosition(typedBundle);
    }

    public void addKeyAttribute(String str, TypedBundle typedBundle) {
        getWidgetState(str, (ConstraintWidget) null, 0).setKeyAttribute(typedBundle);
    }

    public void addKeyCycle(String str, TypedBundle typedBundle) {
        getWidgetState(str, (ConstraintWidget) null, 0).setKeyCycle(typedBundle);
    }

    public void addKeyPosition(String str, int i, int i2, float f, float f2) {
        TypedBundle typedBundle = new TypedBundle();
        typedBundle.add(510, 2);
        typedBundle.add(100, i);
        typedBundle.add(506, f);
        typedBundle.add(507, f2);
        getWidgetState(str, (ConstraintWidget) null, 0).setKeyPosition(typedBundle);
        KeyPosition keyPosition = new KeyPosition(str, i, i2, f, f2);
        HashMap hashMap = (HashMap) this.keyPositions.get(Integer.valueOf(i));
        if (hashMap == null) {
            hashMap = new HashMap();
            this.keyPositions.put(Integer.valueOf(i), hashMap);
        }
        hashMap.put(str, keyPosition);
    }

    public void addCustomFloat(int i, String str, String str2, float f) {
        getWidgetState(str, (ConstraintWidget) null, i).getFrame(i).addCustomFloat(str2, f);
    }

    public void addCustomColor(int i, String str, String str2, int i2) {
        getWidgetState(str, (ConstraintWidget) null, i).getFrame(i).addCustomColor(str2, i2);
    }

    public void updateFrom(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        ArrayList children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) children.get(i2);
            getWidgetState(constraintWidget.stringId, (ConstraintWidget) null, i).update(constraintWidget, i);
        }
    }

    public void interpolate(int i, int i2, float f) {
        for (String str : this.state.keySet()) {
            ((WidgetState) this.state.get(str)).interpolate(i, i2, f, this);
        }
    }

    public WidgetFrame getStart(String str) {
        WidgetState widgetState = (WidgetState) this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.start;
    }

    public WidgetFrame getEnd(String str) {
        WidgetState widgetState = (WidgetState) this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.end;
    }

    public WidgetFrame getInterpolated(String str) {
        WidgetState widgetState = (WidgetState) this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.interpolated;
    }

    public float[] getPath(String str) {
        float[] fArr = new float[124];
        ((WidgetState) this.state.get(str)).motionControl.buildPath(fArr, 62);
        return fArr;
    }

    public int getKeyFrames(String str, float[] fArr, int[] iArr, int[] iArr2) {
        return ((WidgetState) this.state.get(str)).motionControl.buildKeyFrames(fArr, iArr, iArr2);
    }

    private WidgetState getWidgetState(String str, ConstraintWidget constraintWidget, int i) {
        WidgetState widgetState = (WidgetState) this.state.get(str);
        if (widgetState == null) {
            widgetState = new WidgetState();
            int i2 = this.pathMotionArc;
            if (i2 != -1) {
                widgetState.motionControl.setPathMotionArc(i2);
            }
            this.state.put(str, widgetState);
            if (constraintWidget != null) {
                widgetState.update(constraintWidget, i);
            }
        }
        return widgetState;
    }

    public WidgetFrame getStart(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, (ConstraintWidget) null, 0).start;
    }

    public WidgetFrame getEnd(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, (ConstraintWidget) null, 1).end;
    }

    public WidgetFrame getInterpolated(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, (ConstraintWidget) null, 2).interpolated;
    }
}
