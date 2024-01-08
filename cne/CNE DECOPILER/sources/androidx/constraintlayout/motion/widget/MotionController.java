package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MotionController {
    private int MAX_DIMENSION = 4;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    private HashMap mAttributesMap;
    String mConstraintTag;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private int mCurveFitType = -1;
    private HashMap mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList mKeyList = new ArrayList();
    private KeyTrigger[] mKeyTriggers;
    private ArrayList mMotionPaths = new ArrayList();
    float mMotionStagger = Float.NaN;
    private boolean mNoMovement;
    private int mPathMotionArc;
    private Interpolator mQuantizeMotionInterpolator;
    private float mQuantizeMotionPhase;
    private int mQuantizeMotionSteps;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    Rect mTempRect = new Rect();
    private HashMap mTimeCycleAttributesMap;
    private int mTransformPivotTarget;
    private View mTransformPivotView;
    private float[] mValuesBuff = new float[4];
    private float[] mVelocity = new float[1];
    View mView;

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public void setTransformPivotTarget(int i) {
        this.mTransformPivotTarget = i;
        this.mTransformPivotView = null;
    }

    /* access modifiers changed from: package-private */
    public MotionPaths getKeyFrame(int i) {
        return (MotionPaths) this.mMotionPaths.get(i);
    }

    MotionController(View view) {
        int i = Key.UNSET;
        this.mPathMotionArc = i;
        this.mTransformPivotTarget = i;
        this.mTransformPivotView = null;
        this.mQuantizeMotionSteps = i;
        this.mQuantizeMotionPhase = Float.NaN;
        this.mQuantizeMotionInterpolator = null;
        this.mNoMovement = false;
        setView(view);
    }

    public float getStartX() {
        return this.mStartMotionPath.x;
    }

    public float getStartY() {
        return this.mStartMotionPath.y;
    }

    public float getFinalX() {
        return this.mEndMotionPath.x;
    }

    public float getFinalY() {
        return this.mEndMotionPath.y;
    }

    public float getStartWidth() {
        return this.mStartMotionPath.width;
    }

    public float getStartHeight() {
        return this.mStartMotionPath.height;
    }

    public float getFinalWidth() {
        return this.mEndMotionPath.width;
    }

    public float getFinalHeight() {
        return this.mEndMotionPath.height;
    }

    public int getAnimateRelativeTo() {
        return this.mStartMotionPath.mAnimateRelativeTo;
    }

    public void setupRelative(MotionController motionController) {
        this.mStartMotionPath.setupRelative(motionController, motionController.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motionController, motionController.mEndMotionPath);
    }

    public float getCenterX() {
        return this.mCurrentCenterX;
    }

    public float getCenterY() {
        return this.mCurrentCenterY;
    }

    public void getCenter(double d, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.mSpline[0].getPos(d, dArr);
        this.mSpline[0].getSlope(d, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, dArr, fArr, dArr2, fArr2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: androidx.constraintlayout.motion.utils.ViewOscillator} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildPath(float[] r22, int r23) {
        /*
            r21 = this;
            r0 = r21
            r8 = r23
            int r1 = r8 + -1
            float r1 = (float) r1
            r9 = 1065353216(0x3f800000, float:1.0)
            float r10 = r9 / r1
            java.util.HashMap r1 = r0.mAttributesMap
            java.lang.String r2 = "translationX"
            r3 = 0
            if (r1 != 0) goto L_0x0014
            r11 = r3
            goto L_0x001b
        L_0x0014:
            java.lang.Object r1 = r1.get(r2)
            androidx.constraintlayout.core.motion.utils.SplineSet r1 = (androidx.constraintlayout.core.motion.utils.SplineSet) r1
            r11 = r1
        L_0x001b:
            java.util.HashMap r1 = r0.mAttributesMap
            java.lang.String r4 = "translationY"
            if (r1 != 0) goto L_0x0023
            r12 = r3
            goto L_0x002a
        L_0x0023:
            java.lang.Object r1 = r1.get(r4)
            androidx.constraintlayout.core.motion.utils.SplineSet r1 = (androidx.constraintlayout.core.motion.utils.SplineSet) r1
            r12 = r1
        L_0x002a:
            java.util.HashMap r1 = r0.mCycleMap
            if (r1 != 0) goto L_0x0030
            r13 = r3
            goto L_0x0037
        L_0x0030:
            java.lang.Object r1 = r1.get(r2)
            androidx.constraintlayout.motion.utils.ViewOscillator r1 = (androidx.constraintlayout.motion.utils.ViewOscillator) r1
            r13 = r1
        L_0x0037:
            java.util.HashMap r1 = r0.mCycleMap
            if (r1 != 0) goto L_0x003c
            goto L_0x0043
        L_0x003c:
            java.lang.Object r1 = r1.get(r4)
            r3 = r1
            androidx.constraintlayout.motion.utils.ViewOscillator r3 = (androidx.constraintlayout.motion.utils.ViewOscillator) r3
        L_0x0043:
            r14 = r3
            r7 = 0
        L_0x0045:
            if (r7 >= r8) goto L_0x0124
            float r1 = (float) r7
            float r1 = r1 * r10
            float r2 = r0.mStaggerScale
            int r4 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r4 == 0) goto L_0x0069
            float r4 = r0.mStaggerOffset
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r5 >= 0) goto L_0x0057
            r1 = 0
        L_0x0057:
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r5 <= 0) goto L_0x0069
            double r5 = (double) r1
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r18 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r18 >= 0) goto L_0x0069
            float r1 = r1 - r4
            float r1 = r1 * r2
            float r1 = java.lang.Math.min(r1, r9)
        L_0x0069:
            r6 = r1
            double r1 = (double) r6
            androidx.constraintlayout.motion.widget.MotionPaths r4 = r0.mStartMotionPath
            androidx.constraintlayout.core.motion.utils.Easing r4 = r4.mKeyFrameEasing
            r5 = 2143289344(0x7fc00000, float:NaN)
            java.util.ArrayList r3 = r0.mMotionPaths
            java.util.Iterator r3 = r3.iterator()
            r16 = 0
        L_0x0079:
            boolean r17 = r3.hasNext()
            if (r17 == 0) goto L_0x00a5
            java.lang.Object r17 = r3.next()
            r9 = r17
            androidx.constraintlayout.motion.widget.MotionPaths r9 = (androidx.constraintlayout.motion.widget.MotionPaths) r9
            androidx.constraintlayout.core.motion.utils.Easing r15 = r9.mKeyFrameEasing
            r19 = r1
            if (r15 == 0) goto L_0x00a0
            float r1 = r9.time
            int r2 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x0097
            r16 = r1
            r4 = r15
            goto L_0x00a0
        L_0x0097:
            boolean r1 = java.lang.Float.isNaN(r5)
            if (r1 == 0) goto L_0x00a0
            float r1 = r9.time
            r5 = r1
        L_0x00a0:
            r1 = r19
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0079
        L_0x00a5:
            r19 = r1
            if (r4 == 0) goto L_0x00c3
            boolean r1 = java.lang.Float.isNaN(r5)
            if (r1 == 0) goto L_0x00b1
            r5 = 1065353216(0x3f800000, float:1.0)
        L_0x00b1:
            float r1 = r6 - r16
            float r5 = r5 - r16
            float r1 = r1 / r5
            double r1 = (double) r1
            double r1 = r4.get(r1)
            float r1 = (float) r1
            float r1 = r1 * r5
            float r1 = r1 + r16
            double r1 = (double) r1
            r2 = r1
            goto L_0x00c5
        L_0x00c3:
            r2 = r19
        L_0x00c5:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            r9 = 0
            r1 = r1[r9]
            double[] r4 = r0.mInterpolateData
            r1.getPos((double) r2, (double[]) r4)
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = r0.mArcSpline
            if (r1 == 0) goto L_0x00db
            double[] r4 = r0.mInterpolateData
            int r5 = r4.length
            if (r5 <= 0) goto L_0x00db
            r1.getPos((double) r2, (double[]) r4)
        L_0x00db:
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            int[] r4 = r0.mInterpolateVariables
            double[] r5 = r0.mInterpolateData
            int r15 = r7 * 2
            r9 = r6
            r6 = r22
            r16 = r7
            r7 = r15
            r1.getCenter(r2, r4, r5, r6, r7)
            if (r13 == 0) goto L_0x00f8
            r1 = r22[r15]
            float r2 = r13.get(r9)
            float r1 = r1 + r2
            r22[r15] = r1
            goto L_0x0103
        L_0x00f8:
            if (r11 == 0) goto L_0x0103
            r1 = r22[r15]
            float r2 = r11.get(r9)
            float r1 = r1 + r2
            r22[r15] = r1
        L_0x0103:
            if (r14 == 0) goto L_0x0111
            int r15 = r15 + 1
            r1 = r22[r15]
            float r2 = r14.get(r9)
            float r1 = r1 + r2
            r22[r15] = r1
            goto L_0x011e
        L_0x0111:
            if (r12 == 0) goto L_0x011e
            int r15 = r15 + 1
            r1 = r22[r15]
            float r2 = r12.get(r9)
            float r1 = r1 + r2
            r22[r15] = r1
        L_0x011e:
            int r7 = r16 + 1
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0045
        L_0x0124:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.buildPath(float[], int):void");
    }

    private float getPreCycleDistance() {
        char c;
        float f;
        float[] fArr = new float[2];
        float f2 = 1.0f / ((float) 99);
        double d = 0.0d;
        double d2 = 0.0d;
        float f3 = 0.0f;
        int i = 0;
        while (i < 100) {
            float f4 = ((float) i) * f2;
            double d3 = (double) f4;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator it = this.mMotionPaths.iterator();
            float f5 = Float.NaN;
            float f6 = 0.0f;
            while (it.hasNext()) {
                MotionPaths motionPaths = (MotionPaths) it.next();
                Easing easing2 = motionPaths.mKeyFrameEasing;
                if (easing2 != null) {
                    float f7 = motionPaths.time;
                    if (f7 < f4) {
                        easing = easing2;
                        f6 = f7;
                    } else if (Float.isNaN(f5)) {
                        f5 = motionPaths.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f5)) {
                    f5 = 1.0f;
                }
                float f8 = f5 - f6;
                d3 = (double) ((((float) easing.get((double) ((f4 - f6) / f8))) * f8) + f6);
            }
            this.mSpline[0].getPos(d3, this.mInterpolateData);
            float f9 = f3;
            int i2 = i;
            this.mStartMotionPath.getCenter(d3, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
            if (i2 > 0) {
                c = 0;
                f = (float) (((double) f9) + Math.hypot(d2 - ((double) fArr[1]), d - ((double) fArr[0])));
            } else {
                c = 0;
                f = f9;
            }
            d = (double) fArr[c];
            i = i2 + 1;
            f3 = f;
            d2 = (double) fArr[1];
        }
        return f3;
    }

    /* access modifiers changed from: package-private */
    public KeyPositionBase getPositionKeyframe(int i, int i2, float f, float f2) {
        RectF rectF = new RectF();
        MotionPaths motionPaths = this.mStartMotionPath;
        float f3 = motionPaths.x;
        rectF.left = f3;
        float f4 = motionPaths.y;
        rectF.top = f4;
        rectF.right = f3 + motionPaths.width;
        rectF.bottom = f4 + motionPaths.height;
        RectF rectF2 = new RectF();
        MotionPaths motionPaths2 = this.mEndMotionPath;
        float f5 = motionPaths2.x;
        rectF2.left = f5;
        float f6 = motionPaths2.y;
        rectF2.top = f6;
        rectF2.right = f5 + motionPaths2.width;
        rectF2.bottom = f6 + motionPaths2.height;
        Iterator it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key key = (Key) it.next();
            if (key instanceof KeyPositionBase) {
                KeyPositionBase keyPositionBase = (KeyPositionBase) key;
                if (keyPositionBase.intersects(i, i2, rectF, rectF2, f, f2)) {
                    return keyPositionBase;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyFrames(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator it = this.mMotionPaths.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = ((MotionPaths) it.next()).mMode;
                i++;
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 < timePoints.length; i3++) {
            this.mSpline[0].getPos(timePoints[i3], this.mInterpolateData);
            this.mStartMotionPath.getCenter(timePoints[i3], this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    /* access modifiers changed from: package-private */
    public int getAttributeValues(String str, float[] fArr, int i) {
        SplineSet splineSet = (SplineSet) this.mAttributesMap.get(str);
        if (splineSet == null) {
            return -1;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = splineSet.get((float) (i2 / (fArr.length - 1)));
        }
        return fArr.length;
    }

    /* access modifiers changed from: package-private */
    public void buildRect(float f, float[] fArr, int i) {
        this.mSpline[0].getPos((double) getAdjustedPosition(f, (float[]) null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i);
    }

    /* access modifiers changed from: package-private */
    public void buildRectangles(float[] fArr, int i) {
        float f = 1.0f / ((float) (i - 1));
        for (int i2 = 0; i2 < i; i2++) {
            this.mSpline[0].getPos((double) getAdjustedPosition(((float) i2) * f, (float[]) null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i2 * 8);
        }
    }

    /* access modifiers changed from: package-private */
    public float getKeyFrameParameter(int i, float f, float f2) {
        MotionPaths motionPaths = this.mEndMotionPath;
        float f3 = motionPaths.x;
        MotionPaths motionPaths2 = this.mStartMotionPath;
        float f4 = motionPaths2.x;
        float f5 = f3 - f4;
        float f6 = motionPaths.y;
        float f7 = motionPaths2.y;
        float f8 = f6 - f7;
        float f9 = f4 + (motionPaths2.width / 2.0f);
        float f10 = f7 + (motionPaths2.height / 2.0f);
        float hypot = (float) Math.hypot((double) f5, (double) f8);
        if (((double) hypot) < 1.0E-7d) {
            return Float.NaN;
        }
        float f11 = f - f9;
        float f12 = f2 - f10;
        if (((float) Math.hypot((double) f11, (double) f12)) == 0.0f) {
            return 0.0f;
        }
        float f13 = (f11 * f5) + (f12 * f8);
        if (i == 0) {
            return f13 / hypot;
        }
        if (i == 1) {
            return (float) Math.sqrt((double) ((hypot * hypot) - (f13 * f13)));
        }
        if (i == 2) {
            return f11 / f5;
        }
        if (i == 3) {
            return f12 / f5;
        }
        if (i == 4) {
            return f11 / f8;
        }
        if (i != 5) {
            return 0.0f;
        }
        return f12 / f8;
    }

    private void insertKey(MotionPaths motionPaths) {
        int binarySearch = Collections.binarySearch(this.mMotionPaths, motionPaths);
        if (binarySearch == 0) {
            Log.e("MotionController", " KeyPath position \"" + motionPaths.position + "\" outside of range");
        }
        this.mMotionPaths.add((-binarySearch) - 1, motionPaths);
    }

    /* access modifiers changed from: package-private */
    public void addKeys(ArrayList arrayList) {
        this.mKeyList.addAll(arrayList);
    }

    public void addKey(Key key) {
        this.mKeyList.add(key);
    }

    public void setPathMotionArc(int i) {
        this.mPathMotionArc = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x014f, code lost:
        r11 = (java.lang.Integer) r4.get(r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(int r17, int r18, float r19, long r20) {
        /*
            r16 = this;
            r0 = r16
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            int r5 = r0.mPathMotionArc
            int r6 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r5 == r6) goto L_0x0025
            androidx.constraintlayout.motion.widget.MotionPaths r6 = r0.mStartMotionPath
            r6.mPathMotionArc = r5
        L_0x0025:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r5 = r0.mStartPoint
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r6 = r0.mEndPoint
            r5.different(r6, r2)
            java.util.ArrayList r5 = r0.mKeyList
            if (r5 == 0) goto L_0x008a
            java.util.Iterator r5 = r5.iterator()
            r7 = 0
        L_0x0035:
            boolean r8 = r5.hasNext()
            if (r8 == 0) goto L_0x008b
            java.lang.Object r8 = r5.next()
            androidx.constraintlayout.motion.widget.Key r8 = (androidx.constraintlayout.motion.widget.Key) r8
            boolean r9 = r8 instanceof androidx.constraintlayout.motion.widget.KeyPosition
            if (r9 == 0) goto L_0x0062
            androidx.constraintlayout.motion.widget.KeyPosition r8 = (androidx.constraintlayout.motion.widget.KeyPosition) r8
            androidx.constraintlayout.motion.widget.MotionPaths r9 = new androidx.constraintlayout.motion.widget.MotionPaths
            androidx.constraintlayout.motion.widget.MotionPaths r14 = r0.mStartMotionPath
            androidx.constraintlayout.motion.widget.MotionPaths r15 = r0.mEndMotionPath
            r10 = r9
            r11 = r17
            r12 = r18
            r13 = r8
            r10.<init>(r11, r12, r13, r14, r15)
            r0.insertKey(r9)
            int r8 = r8.mCurveFit
            int r9 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r8 == r9) goto L_0x0035
            r0.mCurveFitType = r8
            goto L_0x0035
        L_0x0062:
            boolean r9 = r8 instanceof androidx.constraintlayout.motion.widget.KeyCycle
            if (r9 == 0) goto L_0x006a
            r8.getAttributeNames(r3)
            goto L_0x0035
        L_0x006a:
            boolean r9 = r8 instanceof androidx.constraintlayout.motion.widget.KeyTimeCycle
            if (r9 == 0) goto L_0x0072
            r8.getAttributeNames(r1)
            goto L_0x0035
        L_0x0072:
            boolean r9 = r8 instanceof androidx.constraintlayout.motion.widget.KeyTrigger
            if (r9 == 0) goto L_0x0083
            if (r7 != 0) goto L_0x007d
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x007d:
            androidx.constraintlayout.motion.widget.KeyTrigger r8 = (androidx.constraintlayout.motion.widget.KeyTrigger) r8
            r7.add(r8)
            goto L_0x0035
        L_0x0083:
            r8.setInterpolation(r4)
            r8.getAttributeNames(r2)
            goto L_0x0035
        L_0x008a:
            r7 = 0
        L_0x008b:
            r5 = 0
            if (r7 == 0) goto L_0x0098
            androidx.constraintlayout.motion.widget.KeyTrigger[] r8 = new androidx.constraintlayout.motion.widget.KeyTrigger[r5]
            java.lang.Object[] r7 = r7.toArray(r8)
            androidx.constraintlayout.motion.widget.KeyTrigger[] r7 = (androidx.constraintlayout.motion.widget.KeyTrigger[]) r7
            r0.mKeyTriggers = r7
        L_0x0098:
            boolean r7 = r2.isEmpty()
            java.lang.String r8 = ","
            java.lang.String r9 = "CUSTOM,"
            r10 = 1
            if (r7 != 0) goto L_0x016b
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            r0.mAttributesMap = r7
            java.util.Iterator r7 = r2.iterator()
        L_0x00ae:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x0105
            java.lang.Object r11 = r7.next()
            java.lang.String r11 = (java.lang.String) r11
            boolean r12 = r11.startsWith(r9)
            if (r12 == 0) goto L_0x00f5
            android.util.SparseArray r12 = new android.util.SparseArray
            r12.<init>()
            java.lang.String[] r13 = r11.split(r8)
            r13 = r13[r10]
            java.util.ArrayList r14 = r0.mKeyList
            java.util.Iterator r14 = r14.iterator()
        L_0x00d1:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x00f0
            java.lang.Object r15 = r14.next()
            androidx.constraintlayout.motion.widget.Key r15 = (androidx.constraintlayout.motion.widget.Key) r15
            java.util.HashMap r6 = r15.mCustomConstraints
            if (r6 != 0) goto L_0x00e2
            goto L_0x00d1
        L_0x00e2:
            java.lang.Object r6 = r6.get(r13)
            androidx.constraintlayout.widget.ConstraintAttribute r6 = (androidx.constraintlayout.widget.ConstraintAttribute) r6
            if (r6 == 0) goto L_0x00d1
            int r15 = r15.mFramePosition
            r12.append(r15, r6)
            goto L_0x00d1
        L_0x00f0:
            androidx.constraintlayout.motion.utils.ViewSpline r6 = androidx.constraintlayout.motion.utils.ViewSpline.makeCustomSpline(r11, r12)
            goto L_0x00f9
        L_0x00f5:
            androidx.constraintlayout.motion.utils.ViewSpline r6 = androidx.constraintlayout.motion.utils.ViewSpline.makeSpline(r11)
        L_0x00f9:
            if (r6 != 0) goto L_0x00fc
            goto L_0x00ae
        L_0x00fc:
            r6.setType(r11)
            java.util.HashMap r12 = r0.mAttributesMap
            r12.put(r11, r6)
            goto L_0x00ae
        L_0x0105:
            java.util.ArrayList r6 = r0.mKeyList
            if (r6 == 0) goto L_0x0123
            java.util.Iterator r6 = r6.iterator()
        L_0x010d:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0123
            java.lang.Object r7 = r6.next()
            androidx.constraintlayout.motion.widget.Key r7 = (androidx.constraintlayout.motion.widget.Key) r7
            boolean r11 = r7 instanceof androidx.constraintlayout.motion.widget.KeyAttributes
            if (r11 == 0) goto L_0x010d
            java.util.HashMap r11 = r0.mAttributesMap
            r7.addValues(r11)
            goto L_0x010d
        L_0x0123:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r6 = r0.mStartPoint
            java.util.HashMap r7 = r0.mAttributesMap
            r6.addValues(r7, r5)
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r6 = r0.mEndPoint
            java.util.HashMap r7 = r0.mAttributesMap
            r11 = 100
            r6.addValues(r7, r11)
            java.util.HashMap r6 = r0.mAttributesMap
            java.util.Set r6 = r6.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x013d:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x016b
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            boolean r11 = r4.containsKey(r7)
            if (r11 == 0) goto L_0x015c
            java.lang.Object r11 = r4.get(r7)
            java.lang.Integer r11 = (java.lang.Integer) r11
            if (r11 == 0) goto L_0x015c
            int r11 = r11.intValue()
            goto L_0x015d
        L_0x015c:
            r11 = 0
        L_0x015d:
            java.util.HashMap r12 = r0.mAttributesMap
            java.lang.Object r7 = r12.get(r7)
            androidx.constraintlayout.core.motion.utils.SplineSet r7 = (androidx.constraintlayout.core.motion.utils.SplineSet) r7
            if (r7 == 0) goto L_0x013d
            r7.setup(r11)
            goto L_0x013d
        L_0x016b:
            boolean r6 = r1.isEmpty()
            if (r6 != 0) goto L_0x0238
            java.util.HashMap r6 = r0.mTimeCycleAttributesMap
            if (r6 != 0) goto L_0x017c
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            r0.mTimeCycleAttributesMap = r6
        L_0x017c:
            java.util.Iterator r1 = r1.iterator()
        L_0x0180:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x01e4
            java.lang.Object r6 = r1.next()
            java.lang.String r6 = (java.lang.String) r6
            java.util.HashMap r7 = r0.mTimeCycleAttributesMap
            boolean r7 = r7.containsKey(r6)
            if (r7 == 0) goto L_0x0195
            goto L_0x0180
        L_0x0195:
            boolean r7 = r6.startsWith(r9)
            if (r7 == 0) goto L_0x01d2
            android.util.SparseArray r7 = new android.util.SparseArray
            r7.<init>()
            java.lang.String[] r11 = r6.split(r8)
            r11 = r11[r10]
            java.util.ArrayList r12 = r0.mKeyList
            java.util.Iterator r12 = r12.iterator()
        L_0x01ac:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x01cb
            java.lang.Object r13 = r12.next()
            androidx.constraintlayout.motion.widget.Key r13 = (androidx.constraintlayout.motion.widget.Key) r13
            java.util.HashMap r14 = r13.mCustomConstraints
            if (r14 != 0) goto L_0x01bd
            goto L_0x01ac
        L_0x01bd:
            java.lang.Object r14 = r14.get(r11)
            androidx.constraintlayout.widget.ConstraintAttribute r14 = (androidx.constraintlayout.widget.ConstraintAttribute) r14
            if (r14 == 0) goto L_0x01ac
            int r13 = r13.mFramePosition
            r7.append(r13, r14)
            goto L_0x01ac
        L_0x01cb:
            androidx.constraintlayout.motion.utils.ViewTimeCycle r7 = androidx.constraintlayout.motion.utils.ViewTimeCycle.makeCustomSpline(r6, r7)
            r11 = r20
            goto L_0x01d8
        L_0x01d2:
            r11 = r20
            androidx.constraintlayout.motion.utils.ViewTimeCycle r7 = androidx.constraintlayout.motion.utils.ViewTimeCycle.makeSpline(r6, r11)
        L_0x01d8:
            if (r7 != 0) goto L_0x01db
            goto L_0x0180
        L_0x01db:
            r7.setType(r6)
            java.util.HashMap r13 = r0.mTimeCycleAttributesMap
            r13.put(r6, r7)
            goto L_0x0180
        L_0x01e4:
            java.util.ArrayList r1 = r0.mKeyList
            if (r1 == 0) goto L_0x0204
            java.util.Iterator r1 = r1.iterator()
        L_0x01ec:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x0204
            java.lang.Object r6 = r1.next()
            androidx.constraintlayout.motion.widget.Key r6 = (androidx.constraintlayout.motion.widget.Key) r6
            boolean r7 = r6 instanceof androidx.constraintlayout.motion.widget.KeyTimeCycle
            if (r7 == 0) goto L_0x01ec
            androidx.constraintlayout.motion.widget.KeyTimeCycle r6 = (androidx.constraintlayout.motion.widget.KeyTimeCycle) r6
            java.util.HashMap r7 = r0.mTimeCycleAttributesMap
            r6.addTimeValues(r7)
            goto L_0x01ec
        L_0x0204:
            java.util.HashMap r1 = r0.mTimeCycleAttributesMap
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x020e:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x0238
            java.lang.Object r6 = r1.next()
            java.lang.String r6 = (java.lang.String) r6
            boolean r7 = r4.containsKey(r6)
            if (r7 == 0) goto L_0x022b
            java.lang.Object r7 = r4.get(r6)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            goto L_0x022c
        L_0x022b:
            r7 = 0
        L_0x022c:
            java.util.HashMap r8 = r0.mTimeCycleAttributesMap
            java.lang.Object r6 = r8.get(r6)
            androidx.constraintlayout.motion.utils.ViewTimeCycle r6 = (androidx.constraintlayout.motion.utils.ViewTimeCycle) r6
            r6.setup(r7)
            goto L_0x020e
        L_0x0238:
            java.util.ArrayList r1 = r0.mMotionPaths
            int r1 = r1.size()
            r4 = 2
            int r1 = r1 + r4
            androidx.constraintlayout.motion.widget.MotionPaths[] r6 = new androidx.constraintlayout.motion.widget.MotionPaths[r1]
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mStartMotionPath
            r6[r5] = r7
            int r7 = r1 + -1
            androidx.constraintlayout.motion.widget.MotionPaths r8 = r0.mEndMotionPath
            r6[r7] = r8
            java.util.ArrayList r7 = r0.mMotionPaths
            int r7 = r7.size()
            if (r7 <= 0) goto L_0x025b
            int r7 = r0.mCurveFitType
            r8 = -1
            if (r7 != r8) goto L_0x025b
            r0.mCurveFitType = r5
        L_0x025b:
            java.util.ArrayList r7 = r0.mMotionPaths
            java.util.Iterator r7 = r7.iterator()
            r8 = 1
        L_0x0262:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x0274
            java.lang.Object r11 = r7.next()
            androidx.constraintlayout.motion.widget.MotionPaths r11 = (androidx.constraintlayout.motion.widget.MotionPaths) r11
            int r12 = r8 + 1
            r6[r8] = r11
            r8 = r12
            goto L_0x0262
        L_0x0274:
            r7 = 18
            java.util.HashSet r8 = new java.util.HashSet
            r8.<init>()
            androidx.constraintlayout.motion.widget.MotionPaths r11 = r0.mEndMotionPath
            java.util.LinkedHashMap r11 = r11.attributes
            java.util.Set r11 = r11.keySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x0287:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x02b6
            java.lang.Object r12 = r11.next()
            java.lang.String r12 = (java.lang.String) r12
            androidx.constraintlayout.motion.widget.MotionPaths r13 = r0.mStartMotionPath
            java.util.LinkedHashMap r13 = r13.attributes
            boolean r13 = r13.containsKey(r12)
            if (r13 == 0) goto L_0x0287
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r9)
            r13.append(r12)
            java.lang.String r13 = r13.toString()
            boolean r13 = r2.contains(r13)
            if (r13 != 0) goto L_0x0287
            r8.add(r12)
            goto L_0x0287
        L_0x02b6:
            java.lang.String[] r2 = new java.lang.String[r5]
            java.lang.Object[] r2 = r8.toArray(r2)
            java.lang.String[] r2 = (java.lang.String[]) r2
            r0.mAttributeNames = r2
            int r2 = r2.length
            int[] r2 = new int[r2]
            r0.mAttributeInterpolatorCount = r2
            r2 = 0
        L_0x02c6:
            java.lang.String[] r8 = r0.mAttributeNames
            int r9 = r8.length
            if (r2 >= r9) goto L_0x02fc
            r8 = r8[r2]
            int[] r9 = r0.mAttributeInterpolatorCount
            r9[r2] = r5
            r9 = 0
        L_0x02d2:
            if (r9 >= r1) goto L_0x02f9
            r11 = r6[r9]
            java.util.LinkedHashMap r11 = r11.attributes
            boolean r11 = r11.containsKey(r8)
            if (r11 == 0) goto L_0x02f6
            r11 = r6[r9]
            java.util.LinkedHashMap r11 = r11.attributes
            java.lang.Object r11 = r11.get(r8)
            androidx.constraintlayout.widget.ConstraintAttribute r11 = (androidx.constraintlayout.widget.ConstraintAttribute) r11
            if (r11 == 0) goto L_0x02f6
            int[] r8 = r0.mAttributeInterpolatorCount
            r9 = r8[r2]
            int r11 = r11.numberOfInterpolatedValues()
            int r9 = r9 + r11
            r8[r2] = r9
            goto L_0x02f9
        L_0x02f6:
            int r9 = r9 + 1
            goto L_0x02d2
        L_0x02f9:
            int r2 = r2 + 1
            goto L_0x02c6
        L_0x02fc:
            r2 = r6[r5]
            int r2 = r2.mPathMotionArc
            int r9 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r2 == r9) goto L_0x0306
            r2 = 1
            goto L_0x0307
        L_0x0306:
            r2 = 0
        L_0x0307:
            int r8 = r8.length
            int r7 = r7 + r8
            boolean[] r8 = new boolean[r7]
            r9 = 1
        L_0x030c:
            if (r9 >= r1) goto L_0x031c
            r11 = r6[r9]
            int r12 = r9 + -1
            r12 = r6[r12]
            java.lang.String[] r13 = r0.mAttributeNames
            r11.different(r12, r8, r13, r2)
            int r9 = r9 + 1
            goto L_0x030c
        L_0x031c:
            r2 = 1
            r9 = 0
        L_0x031e:
            if (r2 >= r7) goto L_0x0329
            boolean r11 = r8[r2]
            if (r11 == 0) goto L_0x0326
            int r9 = r9 + 1
        L_0x0326:
            int r2 = r2 + 1
            goto L_0x031e
        L_0x0329:
            int[] r2 = new int[r9]
            r0.mInterpolateVariables = r2
            int r2 = java.lang.Math.max(r4, r9)
            double[] r9 = new double[r2]
            r0.mInterpolateData = r9
            double[] r2 = new double[r2]
            r0.mInterpolateVelocity = r2
            r2 = 1
            r9 = 0
        L_0x033b:
            if (r2 >= r7) goto L_0x034b
            boolean r11 = r8[r2]
            if (r11 == 0) goto L_0x0348
            int[] r11 = r0.mInterpolateVariables
            int r12 = r9 + 1
            r11[r9] = r2
            r9 = r12
        L_0x0348:
            int r2 = r2 + 1
            goto L_0x033b
        L_0x034b:
            int[] r2 = r0.mInterpolateVariables
            int r2 = r2.length
            int[] r7 = new int[r4]
            r7[r10] = r2
            r7[r5] = r1
            java.lang.Class r2 = java.lang.Double.TYPE
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r2, r7)
            double[][] r2 = (double[][]) r2
            double[] r7 = new double[r1]
            r8 = 0
        L_0x035f:
            if (r8 >= r1) goto L_0x0374
            r9 = r6[r8]
            r11 = r2[r8]
            int[] r12 = r0.mInterpolateVariables
            r9.fillStandard(r11, r12)
            r9 = r6[r8]
            float r9 = r9.time
            double r11 = (double) r9
            r7[r8] = r11
            int r8 = r8 + 1
            goto L_0x035f
        L_0x0374:
            r8 = 0
        L_0x0375:
            int[] r9 = r0.mInterpolateVariables
            int r11 = r9.length
            if (r8 >= r11) goto L_0x03b6
            r9 = r9[r8]
            java.lang.String[] r11 = androidx.constraintlayout.motion.widget.MotionPaths.names
            int r11 = r11.length
            if (r9 >= r11) goto L_0x03b3
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String[] r11 = androidx.constraintlayout.motion.widget.MotionPaths.names
            int[] r12 = r0.mInterpolateVariables
            r12 = r12[r8]
            r11 = r11[r12]
            r9.append(r11)
            java.lang.String r11 = " ["
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            r11 = 0
        L_0x039b:
            if (r11 >= r1) goto L_0x03b3
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r9)
            r9 = r2[r11]
            r13 = r9[r8]
            r12.append(r13)
            java.lang.String r9 = r12.toString()
            int r11 = r11 + 1
            goto L_0x039b
        L_0x03b3:
            int r8 = r8 + 1
            goto L_0x0375
        L_0x03b6:
            java.lang.String[] r8 = r0.mAttributeNames
            int r8 = r8.length
            int r8 = r8 + r10
            androidx.constraintlayout.core.motion.utils.CurveFit[] r8 = new androidx.constraintlayout.core.motion.utils.CurveFit[r8]
            r0.mSpline = r8
            r8 = 0
        L_0x03bf:
            java.lang.String[] r9 = r0.mAttributeNames
            int r11 = r9.length
            if (r8 >= r11) goto L_0x041b
            r9 = r9[r8]
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x03ca:
            if (r11 >= r1) goto L_0x0401
            r15 = r6[r11]
            boolean r15 = r15.hasCustomData(r9)
            if (r15 == 0) goto L_0x03fb
            if (r14 != 0) goto L_0x03ec
            double[] r12 = new double[r1]
            r14 = r6[r11]
            int r14 = r14.getCustomDataCount(r9)
            int[] r15 = new int[r4]
            r15[r10] = r14
            r15[r5] = r1
            java.lang.Class r14 = java.lang.Double.TYPE
            java.lang.Object r14 = java.lang.reflect.Array.newInstance(r14, r15)
            double[][] r14 = (double[][]) r14
        L_0x03ec:
            r15 = r6[r11]
            float r10 = r15.time
            double r4 = (double) r10
            r12[r13] = r4
            r4 = r14[r13]
            r5 = 0
            r15.getCustomData(r9, r4, r5)
            int r13 = r13 + 1
        L_0x03fb:
            int r11 = r11 + 1
            r4 = 2
            r5 = 0
            r10 = 1
            goto L_0x03ca
        L_0x0401:
            double[] r4 = java.util.Arrays.copyOf(r12, r13)
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r14, r13)
            double[][] r5 = (double[][]) r5
            androidx.constraintlayout.core.motion.utils.CurveFit[] r9 = r0.mSpline
            int r8 = r8 + 1
            int r10 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r4 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r10, r4, r5)
            r9[r8] = r4
            r4 = 2
            r5 = 0
            r10 = 1
            goto L_0x03bf
        L_0x041b:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r4 = r0.mSpline
            int r5 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r2 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r5, r7, r2)
            r5 = 0
            r4[r5] = r2
            r2 = r6[r5]
            int r2 = r2.mPathMotionArc
            int r4 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r2 == r4) goto L_0x0467
            int[] r2 = new int[r1]
            double[] r4 = new double[r1]
            r7 = 2
            int[] r8 = new int[r7]
            r9 = 1
            r8[r9] = r7
            r8[r5] = r1
            java.lang.Class r5 = java.lang.Double.TYPE
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r5, r8)
            double[][] r5 = (double[][]) r5
            r7 = 0
        L_0x0443:
            if (r7 >= r1) goto L_0x0461
            r8 = r6[r7]
            int r9 = r8.mPathMotionArc
            r2[r7] = r9
            float r9 = r8.time
            double r9 = (double) r9
            r4[r7] = r9
            r9 = r5[r7]
            float r10 = r8.x
            double r10 = (double) r10
            r12 = 0
            r9[r12] = r10
            float r8 = r8.y
            double r10 = (double) r8
            r8 = 1
            r9[r8] = r10
            int r7 = r7 + 1
            goto L_0x0443
        L_0x0461:
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = androidx.constraintlayout.core.motion.utils.CurveFit.getArc(r2, r4, r5)
            r0.mArcSpline = r1
        L_0x0467:
            r1 = 2143289344(0x7fc00000, float:NaN)
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.mCycleMap = r2
            java.util.ArrayList r2 = r0.mKeyList
            if (r2 == 0) goto L_0x04dc
            java.util.Iterator r2 = r3.iterator()
        L_0x0478:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04a4
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            androidx.constraintlayout.motion.utils.ViewOscillator r4 = androidx.constraintlayout.motion.utils.ViewOscillator.makeSpline(r3)
            if (r4 != 0) goto L_0x048b
            goto L_0x0478
        L_0x048b:
            boolean r5 = r4.variesByPath()
            if (r5 == 0) goto L_0x049b
            boolean r5 = java.lang.Float.isNaN(r1)
            if (r5 == 0) goto L_0x049b
            float r1 = r16.getPreCycleDistance()
        L_0x049b:
            r4.setType(r3)
            java.util.HashMap r5 = r0.mCycleMap
            r5.put(r3, r4)
            goto L_0x0478
        L_0x04a4:
            java.util.ArrayList r2 = r0.mKeyList
            java.util.Iterator r2 = r2.iterator()
        L_0x04aa:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04c2
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.motion.widget.Key r3 = (androidx.constraintlayout.motion.widget.Key) r3
            boolean r4 = r3 instanceof androidx.constraintlayout.motion.widget.KeyCycle
            if (r4 == 0) goto L_0x04aa
            androidx.constraintlayout.motion.widget.KeyCycle r3 = (androidx.constraintlayout.motion.widget.KeyCycle) r3
            java.util.HashMap r4 = r0.mCycleMap
            r3.addCycleValues(r4)
            goto L_0x04aa
        L_0x04c2:
            java.util.HashMap r2 = r0.mCycleMap
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x04cc:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04dc
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.motion.utils.ViewOscillator r3 = (androidx.constraintlayout.motion.utils.ViewOscillator) r3
            r3.setup(r1)
            goto L_0x04cc
        L_0x04dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.setup(int, int, float, long):void");
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.x + " y: " + this.mStartMotionPath.y + " end: x: " + this.mEndMotionPath.x + " y: " + this.mEndMotionPath.y;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) ((int) this.mView.getX()), (float) ((int) this.mView.getY()), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(View view) {
        this.mView = view;
        this.mId = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            this.mConstraintTag = ((ConstraintLayout.LayoutParams) layoutParams).getConstraintTag();
        }
    }

    public View getView() {
        return this.mView;
    }

    /* access modifiers changed from: package-private */
    public void setStartCurrentState(View view) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        motionPaths.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mStartPoint.setState(view);
    }

    public void setStartState(ViewState viewState, View view, int i, int i2, int i3) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        Rect rect = new Rect();
        if (i == 1) {
            int i4 = viewState.left + viewState.right;
            rect.left = ((viewState.top + viewState.bottom) - viewState.width()) / 2;
            rect.top = i2 - ((i4 + viewState.height()) / 2);
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        } else if (i == 2) {
            int i5 = viewState.left + viewState.right;
            rect.left = i3 - (((viewState.top + viewState.bottom) + viewState.width()) / 2);
            rect.top = (i5 - viewState.height()) / 2;
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        }
        this.mStartMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        this.mStartPoint.setState(rect, view, i, viewState.rotation);
    }

    /* access modifiers changed from: package-private */
    public void rotate(Rect rect, Rect rect2, int i, int i2, int i3) {
        if (i == 1) {
            int i4 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i3 - ((i4 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 2) {
            int i5 = rect.left + rect.right;
            rect2.left = i2 - (((rect.top + rect.bottom) + rect.width()) / 2);
            rect2.top = (i5 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 3) {
            int i6 = rect.left + rect.right;
            rect2.left = ((rect.height() / 2) + rect.top) - (i6 / 2);
            rect2.top = i3 - ((i6 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 4) {
            int i7 = rect.left + rect.right;
            rect2.left = i2 - (((rect.bottom + rect.top) + rect.width()) / 2);
            rect2.top = (i7 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        }
    }

    /* access modifiers changed from: package-private */
    public void setStartState(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        int i3 = constraintSet.mRotate;
        if (i3 != 0) {
            rotate(rect, this.mTempRect, i3, i, i2);
        }
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        readView(motionPaths);
        this.mStartMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        ConstraintSet.Constraint parameters = constraintSet.getParameters(this.mId);
        this.mStartMotionPath.applyParameters(parameters);
        this.mMotionStagger = parameters.motion.mMotionStagger;
        this.mStartPoint.setState(rect, constraintSet, i3, this.mId);
        this.mTransformPivotTarget = parameters.transform.transformPivotTarget;
        ConstraintSet.Motion motion = parameters.motion;
        this.mQuantizeMotionSteps = motion.mQuantizeMotionSteps;
        this.mQuantizeMotionPhase = motion.mQuantizeMotionPhase;
        Context context = this.mView.getContext();
        ConstraintSet.Motion motion2 = parameters.motion;
        this.mQuantizeMotionInterpolator = getInterpolator(context, motion2.mQuantizeInterpolatorType, motion2.mQuantizeInterpolatorString, motion2.mQuantizeInterpolatorID);
    }

    private static Interpolator getInterpolator(Context context, int i, String str, int i2) {
        if (i == -2) {
            return AnimationUtils.loadInterpolator(context, i2);
        }
        if (i == -1) {
            final Easing interpolator = Easing.getInterpolator(str);
            return new Interpolator() {
                public float getInterpolation(float f) {
                    return (float) Easing.this.get((double) f);
                }
            };
        } else if (i == 0) {
            return new AccelerateDecelerateInterpolator();
        } else {
            if (i == 1) {
                return new AccelerateInterpolator();
            }
            if (i == 2) {
                return new DecelerateInterpolator();
            }
            if (i == 4) {
                return new BounceInterpolator();
            }
            if (i != 5) {
                return null;
            }
            return new OvershootInterpolator();
        }
    }

    /* access modifiers changed from: package-private */
    public void setEndState(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        int i3 = constraintSet.mRotate;
        if (i3 != 0) {
            rotate(rect, this.mTempRect, i3, i, i2);
            rect = this.mTempRect;
        }
        MotionPaths motionPaths = this.mEndMotionPath;
        motionPaths.time = 1.0f;
        motionPaths.position = 1.0f;
        readView(motionPaths);
        this.mEndMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        this.mEndMotionPath.applyParameters(constraintSet.getParameters(this.mId));
        this.mEndPoint.setState(rect, constraintSet, i3, this.mId);
    }

    /* access modifiers changed from: package-private */
    public void setBothStates(View view) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        this.mNoMovement = true;
        motionPaths.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mEndMotionPath.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mStartPoint.setState(view);
        this.mEndPoint.setState(view);
    }

    private float getAdjustedPosition(float f, float[] fArr) {
        float f2 = 0.0f;
        float f3 = 1.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f4 = this.mStaggerScale;
            if (((double) f4) != 1.0d) {
                float f5 = this.mStaggerOffset;
                if (f < f5) {
                    f = 0.0f;
                }
                if (f > f5 && ((double) f) < 1.0d) {
                    f = Math.min((f - f5) * f4, 1.0f);
                }
            }
        }
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        float f6 = Float.NaN;
        Iterator it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths motionPaths = (MotionPaths) it.next();
            Easing easing2 = motionPaths.mKeyFrameEasing;
            if (easing2 != null) {
                float f7 = motionPaths.time;
                if (f7 < f) {
                    easing = easing2;
                    f2 = f7;
                } else if (Float.isNaN(f6)) {
                    f6 = motionPaths.time;
                }
            }
        }
        if (easing != null) {
            if (!Float.isNaN(f6)) {
                f3 = f6;
            }
            float f8 = f3 - f2;
            double d = (double) ((f - f2) / f8);
            f = (((float) easing.get(d)) * f8) + f2;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d);
            }
        }
        return f;
    }

    /* access modifiers changed from: package-private */
    public void endTrigger(boolean z) {
        if ("button".equals(Debug.getName(this.mView)) && this.mKeyTriggers != null) {
            int i = 0;
            while (true) {
                KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                if (i < keyTriggerArr.length) {
                    keyTriggerArr[i].conditionallyFire(z ? -100.0f : 100.0f, this.mView);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: androidx.constraintlayout.motion.utils.ViewTimeCycle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: androidx.constraintlayout.motion.utils.ViewTimeCycle$PathRotate} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean interpolate(android.view.View r23, float r24, long r25, androidx.constraintlayout.core.motion.utils.KeyCache r27) {
        /*
            r22 = this;
            r0 = r22
            r11 = r23
            r1 = 0
            r2 = r24
            float r2 = r0.getAdjustedPosition(r2, r1)
            int r3 = r0.mQuantizeMotionSteps
            int r4 = androidx.constraintlayout.motion.widget.Key.UNSET
            r13 = 1065353216(0x3f800000, float:1.0)
            if (r3 == r4) goto L_0x0045
            float r3 = (float) r3
            float r3 = r13 / r3
            float r4 = r2 / r3
            double r4 = (double) r4
            double r4 = java.lang.Math.floor(r4)
            float r4 = (float) r4
            float r4 = r4 * r3
            float r2 = r2 % r3
            float r2 = r2 / r3
            float r5 = r0.mQuantizeMotionPhase
            boolean r5 = java.lang.Float.isNaN(r5)
            if (r5 != 0) goto L_0x002e
            float r5 = r0.mQuantizeMotionPhase
            float r2 = r2 + r5
            float r2 = r2 % r13
        L_0x002e:
            android.view.animation.Interpolator r5 = r0.mQuantizeMotionInterpolator
            if (r5 == 0) goto L_0x0037
            float r2 = r5.getInterpolation(r2)
            goto L_0x0042
        L_0x0037:
            double r5 = (double) r2
            r7 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 <= 0) goto L_0x0041
            r2 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0042
        L_0x0041:
            r2 = 0
        L_0x0042:
            float r2 = r2 * r3
            float r2 = r2 + r4
        L_0x0045:
            r14 = r2
            java.util.HashMap r2 = r0.mAttributesMap
            if (r2 == 0) goto L_0x0062
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x0052:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0062
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.motion.utils.ViewSpline r3 = (androidx.constraintlayout.motion.utils.ViewSpline) r3
            r3.setProperty(r11, r14)
            goto L_0x0052
        L_0x0062:
            java.util.HashMap r2 = r0.mTimeCycleAttributesMap
            r15 = 0
            if (r2 == 0) goto L_0x0095
            java.util.Collection r2 = r2.values()
            java.util.Iterator r7 = r2.iterator()
            r8 = r1
            r9 = 0
        L_0x0071:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0092
            java.lang.Object r1 = r7.next()
            androidx.constraintlayout.motion.utils.ViewTimeCycle r1 = (androidx.constraintlayout.motion.utils.ViewTimeCycle) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewTimeCycle.PathRotate
            if (r2 == 0) goto L_0x0085
            r8 = r1
            androidx.constraintlayout.motion.utils.ViewTimeCycle$PathRotate r8 = (androidx.constraintlayout.motion.utils.ViewTimeCycle.PathRotate) r8
            goto L_0x0071
        L_0x0085:
            r2 = r23
            r3 = r14
            r4 = r25
            r6 = r27
            boolean r1 = r1.setProperty(r2, r3, r4, r6)
            r9 = r9 | r1
            goto L_0x0071
        L_0x0092:
            r16 = r9
            goto L_0x0098
        L_0x0095:
            r8 = r1
            r16 = 0
        L_0x0098:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            r9 = 1
            if (r1 == 0) goto L_0x01ed
            r1 = r1[r15]
            double r6 = (double) r14
            double[] r2 = r0.mInterpolateData
            r1.getPos((double) r6, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            r1 = r1[r15]
            double[] r2 = r0.mInterpolateVelocity
            r1.getSlope((double) r6, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = r0.mArcSpline
            if (r1 == 0) goto L_0x00c1
            double[] r2 = r0.mInterpolateData
            int r3 = r2.length
            if (r3 <= 0) goto L_0x00c1
            r1.getPos((double) r6, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = r0.mArcSpline
            double[] r2 = r0.mInterpolateVelocity
            r1.getSlope((double) r6, (double[]) r2)
        L_0x00c1:
            boolean r1 = r0.mNoMovement
            if (r1 != 0) goto L_0x00da
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            int[] r4 = r0.mInterpolateVariables
            double[] r5 = r0.mInterpolateData
            double[] r10 = r0.mInterpolateVelocity
            r17 = 0
            r2 = r14
            r3 = r23
            r12 = r6
            r6 = r10
            r7 = r17
            r1.setView(r2, r3, r4, r5, r6, r7)
            goto L_0x00db
        L_0x00da:
            r12 = r6
        L_0x00db:
            int r1 = r0.mTransformPivotTarget
            int r2 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r1 == r2) goto L_0x013d
            android.view.View r1 = r0.mTransformPivotView
            if (r1 != 0) goto L_0x00f3
            android.view.ViewParent r1 = r23.getParent()
            android.view.View r1 = (android.view.View) r1
            int r2 = r0.mTransformPivotTarget
            android.view.View r1 = r1.findViewById(r2)
            r0.mTransformPivotView = r1
        L_0x00f3:
            android.view.View r1 = r0.mTransformPivotView
            if (r1 == 0) goto L_0x013d
            int r1 = r1.getTop()
            android.view.View r2 = r0.mTransformPivotView
            int r2 = r2.getBottom()
            int r1 = r1 + r2
            float r1 = (float) r1
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            android.view.View r3 = r0.mTransformPivotView
            int r3 = r3.getLeft()
            android.view.View r4 = r0.mTransformPivotView
            int r4 = r4.getRight()
            int r3 = r3 + r4
            float r3 = (float) r3
            float r3 = r3 / r2
            int r2 = r23.getRight()
            int r4 = r23.getLeft()
            int r2 = r2 - r4
            if (r2 <= 0) goto L_0x013d
            int r2 = r23.getBottom()
            int r4 = r23.getTop()
            int r2 = r2 - r4
            if (r2 <= 0) goto L_0x013d
            int r2 = r23.getLeft()
            float r2 = (float) r2
            float r3 = r3 - r2
            int r2 = r23.getTop()
            float r2 = (float) r2
            float r1 = r1 - r2
            r11.setPivotX(r3)
            r11.setPivotY(r1)
        L_0x013d:
            java.util.HashMap r1 = r0.mAttributesMap
            if (r1 == 0) goto L_0x016b
            java.util.Collection r1 = r1.values()
            java.util.Iterator r10 = r1.iterator()
        L_0x0149:
            boolean r1 = r10.hasNext()
            if (r1 == 0) goto L_0x016b
            java.lang.Object r1 = r10.next()
            androidx.constraintlayout.core.motion.utils.SplineSet r1 = (androidx.constraintlayout.core.motion.utils.SplineSet) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewSpline.PathRotate
            if (r2 == 0) goto L_0x0149
            double[] r2 = r0.mInterpolateVelocity
            int r3 = r2.length
            if (r3 <= r9) goto L_0x0149
            androidx.constraintlayout.motion.utils.ViewSpline$PathRotate r1 = (androidx.constraintlayout.motion.utils.ViewSpline.PathRotate) r1
            r4 = r2[r15]
            r6 = r2[r9]
            r2 = r23
            r3 = r14
            r1.setPathRotate(r2, r3, r4, r6)
            goto L_0x0149
        L_0x016b:
            if (r8 == 0) goto L_0x018a
            double[] r1 = r0.mInterpolateVelocity
            r18 = r1[r15]
            r20 = r1[r9]
            r1 = r8
            r2 = r23
            r3 = r27
            r4 = r14
            r5 = r25
            r7 = r18
            r17 = 1
            r9 = r20
            boolean r1 = r1.setPathRotate(r2, r3, r4, r5, r7, r9)
            r1 = r16 | r1
            r16 = r1
            goto L_0x018c
        L_0x018a:
            r17 = 1
        L_0x018c:
            r9 = 1
        L_0x018d:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            int r2 = r1.length
            if (r9 >= r2) goto L_0x01b1
            r1 = r1[r9]
            float[] r2 = r0.mValuesBuff
            r1.getPos((double) r12, (float[]) r2)
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            java.util.LinkedHashMap r1 = r1.attributes
            java.lang.String[] r2 = r0.mAttributeNames
            int r3 = r9 + -1
            r2 = r2[r3]
            java.lang.Object r1 = r1.get(r2)
            androidx.constraintlayout.widget.ConstraintAttribute r1 = (androidx.constraintlayout.widget.ConstraintAttribute) r1
            float[] r2 = r0.mValuesBuff
            r1.setInterpolatedValue(r11, r2)
            int r9 = r9 + 1
            goto L_0x018d
        L_0x01b1:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mStartPoint
            int r2 = r1.mVisibilityMode
            if (r2 != 0) goto L_0x01db
            r2 = 0
            int r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x01c2
            int r1 = r1.visibility
            r11.setVisibility(r1)
            goto L_0x01db
        L_0x01c2:
            r2 = 1065353216(0x3f800000, float:1.0)
            int r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x01d0
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mEndPoint
            int r1 = r1.visibility
            r11.setVisibility(r1)
            goto L_0x01db
        L_0x01d0:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r2 = r0.mEndPoint
            int r2 = r2.visibility
            int r1 = r1.visibility
            if (r2 == r1) goto L_0x01db
            r11.setVisibility(r15)
        L_0x01db:
            androidx.constraintlayout.motion.widget.KeyTrigger[] r1 = r0.mKeyTriggers
            if (r1 == 0) goto L_0x023b
            r1 = 0
        L_0x01e0:
            androidx.constraintlayout.motion.widget.KeyTrigger[] r2 = r0.mKeyTriggers
            int r3 = r2.length
            if (r1 >= r3) goto L_0x023b
            r2 = r2[r1]
            r2.conditionallyFire(r14, r11)
            int r1 = r1 + 1
            goto L_0x01e0
        L_0x01ed:
            r17 = 1
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            float r2 = r1.x
            androidx.constraintlayout.motion.widget.MotionPaths r3 = r0.mEndMotionPath
            float r4 = r3.x
            float r4 = r4 - r2
            float r4 = r4 * r14
            float r2 = r2 + r4
            float r4 = r1.y
            float r5 = r3.y
            float r5 = r5 - r4
            float r5 = r5 * r14
            float r4 = r4 + r5
            float r5 = r1.width
            float r6 = r3.width
            float r7 = r6 - r5
            float r7 = r7 * r14
            float r7 = r7 + r5
            float r1 = r1.height
            float r3 = r3.height
            float r8 = r3 - r1
            float r8 = r8 * r14
            float r8 = r8 + r1
            r9 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r9
            int r10 = (int) r2
            float r4 = r4 + r9
            int r9 = (int) r4
            float r2 = r2 + r7
            int r2 = (int) r2
            float r4 = r4 + r8
            int r4 = (int) r4
            int r7 = r2 - r10
            int r8 = r4 - r9
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x022b
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0238
        L_0x022b:
            r1 = 1073741824(0x40000000, float:2.0)
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r1)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r1)
            r11.measure(r3, r1)
        L_0x0238:
            r11.layout(r10, r9, r2, r4)
        L_0x023b:
            java.util.HashMap r1 = r0.mCycleMap
            if (r1 == 0) goto L_0x026a
            java.util.Collection r1 = r1.values()
            java.util.Iterator r8 = r1.iterator()
        L_0x0247:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x026a
            java.lang.Object r1 = r8.next()
            androidx.constraintlayout.motion.utils.ViewOscillator r1 = (androidx.constraintlayout.motion.utils.ViewOscillator) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewOscillator.PathRotateSet
            if (r2 == 0) goto L_0x0266
            androidx.constraintlayout.motion.utils.ViewOscillator$PathRotateSet r1 = (androidx.constraintlayout.motion.utils.ViewOscillator.PathRotateSet) r1
            double[] r2 = r0.mInterpolateVelocity
            r4 = r2[r15]
            r6 = r2[r17]
            r2 = r23
            r3 = r14
            r1.setPathRotate(r2, r3, r4, r6)
            goto L_0x0247
        L_0x0266:
            r1.setProperty(r11, r14)
            goto L_0x0247
        L_0x026a:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.interpolate(android.view.View, float, long, androidx.constraintlayout.core.motion.utils.KeyCache):boolean");
    }

    /* access modifiers changed from: package-private */
    public void getDpDt(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        int i = 0;
        if (curveFitArr != null) {
            double d = (double) adjustedPosition;
            curveFitArr[0].getSlope(d, this.mInterpolateVelocity);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                dArr = this.mInterpolateVelocity;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * ((double) f4);
                i++;
            }
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr2 = this.mInterpolateData;
                if (dArr2.length > 0) {
                    curveFit.getPos(d, dArr2);
                    this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                    this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                    return;
                }
                return;
            }
            this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        MotionPaths motionPaths = this.mEndMotionPath;
        float f5 = motionPaths.x;
        MotionPaths motionPaths2 = this.mStartMotionPath;
        float f6 = f5 - motionPaths2.x;
        float f7 = motionPaths.y - motionPaths2.y;
        float f8 = (motionPaths.width - motionPaths2.width) + f6;
        float f9 = (motionPaths.height - motionPaths2.height) + f7;
        fArr[0] = (f6 * (1.0f - f2)) + (f8 * f2);
        fArr[1] = (f7 * (1.0f - f3)) + (f9 * f3);
    }

    /* access modifiers changed from: package-private */
    public void getPostLayoutDvDp(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        HashMap hashMap = this.mAttributesMap;
        ViewOscillator viewOscillator = null;
        SplineSet splineSet = hashMap == null ? null : (SplineSet) hashMap.get("translationX");
        HashMap hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : (SplineSet) hashMap2.get("translationY");
        HashMap hashMap3 = this.mAttributesMap;
        SplineSet splineSet3 = hashMap3 == null ? null : (SplineSet) hashMap3.get("rotation");
        HashMap hashMap4 = this.mAttributesMap;
        SplineSet splineSet4 = hashMap4 == null ? null : (SplineSet) hashMap4.get("scaleX");
        HashMap hashMap5 = this.mAttributesMap;
        SplineSet splineSet5 = hashMap5 == null ? null : (SplineSet) hashMap5.get("scaleY");
        HashMap hashMap6 = this.mCycleMap;
        ViewOscillator viewOscillator2 = hashMap6 == null ? null : (ViewOscillator) hashMap6.get("translationX");
        HashMap hashMap7 = this.mCycleMap;
        ViewOscillator viewOscillator3 = hashMap7 == null ? null : (ViewOscillator) hashMap7.get("translationY");
        HashMap hashMap8 = this.mCycleMap;
        ViewOscillator viewOscillator4 = hashMap8 == null ? null : (ViewOscillator) hashMap8.get("rotation");
        HashMap hashMap9 = this.mCycleMap;
        ViewOscillator viewOscillator5 = hashMap9 == null ? null : (ViewOscillator) hashMap9.get("scaleX");
        HashMap hashMap10 = this.mCycleMap;
        if (hashMap10 != null) {
            viewOscillator = (ViewOscillator) hashMap10.get("scaleY");
        }
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
        velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
        velocityMatrix.setRotationVelocity((KeyCycleOscillator) viewOscillator4, adjustedPosition);
        velocityMatrix.setTranslationVelocity((KeyCycleOscillator) viewOscillator2, (KeyCycleOscillator) viewOscillator3, adjustedPosition);
        velocityMatrix.setScaleVelocity((KeyCycleOscillator) viewOscillator5, (KeyCycleOscillator) viewOscillator, adjustedPosition);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                double d = (double) adjustedPosition;
                curveFit.getPos(d, dArr);
                this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            }
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.mSpline != null) {
            double adjustedPosition2 = (double) getAdjustedPosition(adjustedPosition, this.mVelocity);
            this.mSpline[0].getSlope(adjustedPosition2, this.mInterpolateVelocity);
            this.mSpline[0].getPos(adjustedPosition2, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                double[] dArr2 = this.mInterpolateVelocity;
                if (i3 < dArr2.length) {
                    dArr2[i3] = dArr2[i3] * ((double) f4);
                    i3++;
                } else {
                    float f5 = f2;
                    float f6 = f3;
                    this.mStartMotionPath.setDpDt(f5, f6, fArr, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                    velocityMatrix.applyTransform(f5, f6, i, i2, fArr);
                    return;
                }
            }
        } else {
            MotionPaths motionPaths = this.mEndMotionPath;
            float f7 = motionPaths.x;
            MotionPaths motionPaths2 = this.mStartMotionPath;
            float f8 = f7 - motionPaths2.x;
            float f9 = motionPaths.y - motionPaths2.y;
            ViewOscillator viewOscillator6 = viewOscillator5;
            float f10 = (motionPaths.height - motionPaths2.height) + f9;
            fArr[0] = (f8 * (1.0f - f2)) + (((motionPaths.width - motionPaths2.width) + f8) * f2);
            fArr[1] = (f9 * (1.0f - f3)) + (f10 * f3);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
            velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
            velocityMatrix.setRotationVelocity((KeyCycleOscillator) viewOscillator4, adjustedPosition);
            velocityMatrix.setTranslationVelocity((KeyCycleOscillator) viewOscillator2, (KeyCycleOscillator) viewOscillator3, adjustedPosition);
            velocityMatrix.setScaleVelocity((KeyCycleOscillator) viewOscillator6, (KeyCycleOscillator) viewOscillator, adjustedPosition);
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
        }
    }

    public int getDrawPath() {
        int i = this.mStartMotionPath.mDrawPath;
        Iterator it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            i = Math.max(i, ((MotionPaths) it.next()).mDrawPath);
        }
        return Math.max(i, this.mEndMotionPath.mDrawPath);
    }

    public void setDrawPath(int i) {
        this.mStartMotionPath.mDrawPath = i;
    }

    /* access modifiers changed from: package-private */
    public void positionKeyframe(View view, KeyPositionBase keyPositionBase, float f, float f2, String[] strArr, float[] fArr) {
        RectF rectF = new RectF();
        MotionPaths motionPaths = this.mStartMotionPath;
        float f3 = motionPaths.x;
        rectF.left = f3;
        float f4 = motionPaths.y;
        rectF.top = f4;
        rectF.right = f3 + motionPaths.width;
        rectF.bottom = f4 + motionPaths.height;
        RectF rectF2 = new RectF();
        MotionPaths motionPaths2 = this.mEndMotionPath;
        float f5 = motionPaths2.x;
        rectF2.left = f5;
        float f6 = motionPaths2.y;
        rectF2.top = f6;
        rectF2.right = f5 + motionPaths2.width;
        rectF2.bottom = f6 + motionPaths2.height;
        keyPositionBase.positionAttributes(view, rectF, rectF2, f, f2, strArr, fArr);
    }

    public int getKeyFramePositions(int[] iArr, float[] fArr) {
        Iterator it = this.mKeyList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Key key = (Key) it.next();
            int i3 = key.mFramePosition;
            iArr[i] = (key.mType * 1000) + i3;
            double d = (double) (((float) i3) / 100.0f);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
            i++;
        }
        return i;
    }

    public int getKeyFrameInfo(int i, int[] iArr) {
        int i2 = i;
        float[] fArr = new float[2];
        Iterator it = this.mKeyList.iterator();
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            Key key = (Key) it.next();
            int i5 = key.mType;
            if (i5 == i2 || i2 != -1) {
                iArr[i4] = 0;
                int i6 = i4 + 1;
                iArr[i6] = i5;
                int i7 = i6 + 1;
                int i8 = key.mFramePosition;
                iArr[i7] = i8;
                double d = (double) (((float) i8) / 100.0f);
                this.mSpline[0].getPos(d, this.mInterpolateData);
                this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
                int i9 = i7 + 1;
                iArr[i9] = Float.floatToIntBits(fArr[0]);
                int i10 = i9 + 1;
                iArr[i10] = Float.floatToIntBits(fArr[1]);
                if (key instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) key;
                    int i11 = i10 + 1;
                    iArr[i11] = keyPosition.mPositionType;
                    int i12 = i11 + 1;
                    iArr[i12] = Float.floatToIntBits(keyPosition.mPercentX);
                    i10 = i12 + 1;
                    iArr[i10] = Float.floatToIntBits(keyPosition.mPercentY);
                }
                int i13 = i10 + 1;
                iArr[i4] = i13 - i4;
                i3++;
                i4 = i13;
            }
        }
        return i3;
    }
}
