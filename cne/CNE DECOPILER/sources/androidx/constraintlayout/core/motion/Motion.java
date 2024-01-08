package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.key.MotionKey;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.key.MotionKeyTrigger;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.DifferentialInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.constraintlayout.core.motion.utils.ViewState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Motion {
    private int MAX_DIMENSION = 4;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    private HashMap mAttributesMap;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private int mCurveFitType = -1;
    private HashMap mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList mKeyList = new ArrayList();
    private MotionKeyTrigger[] mKeyTriggers;
    private ArrayList mMotionPaths = new ArrayList();
    float mMotionStagger = Float.NaN;
    private boolean mNoMovement = false;
    private int mPathMotionArc = -1;
    private DifferentialInterpolator mQuantizeMotionInterpolator = null;
    private float mQuantizeMotionPhase = Float.NaN;
    private int mQuantizeMotionSteps = -1;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    Rect mTempRect = new Rect();
    private HashMap mTimeCycleAttributesMap;
    private int mTransformPivotTarget = -1;
    private MotionWidget mTransformPivotView = null;
    private float[] mValuesBuff = new float[4];
    private float[] mVelocity = new float[1];
    MotionWidget mView;

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public void setTransformPivotTarget(int i) {
        this.mTransformPivotTarget = i;
        this.mTransformPivotView = null;
    }

    public MotionPaths getKeyFrame(int i) {
        return (MotionPaths) this.mMotionPaths.get(i);
    }

    public Motion(MotionWidget motionWidget) {
        setView(motionWidget);
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

    public void setupRelative(Motion motion) {
        this.mStartMotionPath.setupRelative(motion, motion.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motion, motion.mEndMotionPath);
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
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: androidx.constraintlayout.core.motion.utils.KeyCycleOscillator} */
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
            androidx.constraintlayout.core.motion.utils.KeyCycleOscillator r1 = (androidx.constraintlayout.core.motion.utils.KeyCycleOscillator) r1
            r13 = r1
        L_0x0037:
            java.util.HashMap r1 = r0.mCycleMap
            if (r1 != 0) goto L_0x003c
            goto L_0x0043
        L_0x003c:
            java.lang.Object r1 = r1.get(r4)
            r3 = r1
            androidx.constraintlayout.core.motion.utils.KeyCycleOscillator r3 = (androidx.constraintlayout.core.motion.utils.KeyCycleOscillator) r3
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
            androidx.constraintlayout.core.motion.MotionPaths r4 = r0.mStartMotionPath
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
            androidx.constraintlayout.core.motion.MotionPaths r9 = (androidx.constraintlayout.core.motion.MotionPaths) r9
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
            androidx.constraintlayout.core.motion.MotionPaths r1 = r0.mStartMotionPath
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.Motion.buildPath(float[], int):void");
    }

    /* access modifiers changed from: package-private */
    public double[] getPos(double d) {
        this.mSpline[0].getPos(d, this.mInterpolateData);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos(d, dArr);
            }
        }
        return this.mInterpolateData;
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

    public int buildKeyFrames(float[] fArr, int[] iArr, int[] iArr2) {
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
        if (iArr2 != null) {
            Iterator it2 = this.mMotionPaths.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                iArr2[i2] = (int) (((MotionPaths) it2.next()).position * 100.0f);
                i2++;
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < timePoints.length; i4++) {
            this.mSpline[0].getPos(timePoints[i4], this.mInterpolateData);
            this.mStartMotionPath.getCenter(timePoints[i4], this.mInterpolateVariables, this.mInterpolateData, fArr, i3);
            i3 += 2;
        }
        return i3 / 2;
    }

    public void buildRect(float f, float[] fArr, int i) {
        this.mSpline[0].getPos((double) getAdjustedPosition(f, (float[]) null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i);
    }

    private void insertKey(MotionPaths motionPaths) {
        Iterator it = this.mMotionPaths.iterator();
        MotionPaths motionPaths2 = null;
        while (it.hasNext()) {
            MotionPaths motionPaths3 = (MotionPaths) it.next();
            if (motionPaths.position == motionPaths3.position) {
                motionPaths2 = motionPaths3;
            }
        }
        if (motionPaths2 != null) {
            this.mMotionPaths.remove(motionPaths2);
        }
        int binarySearch = Collections.binarySearch(this.mMotionPaths, motionPaths);
        if (binarySearch == 0) {
            Utils.loge("MotionController", " KeyPath position \"" + motionPaths.position + "\" outside of range");
        }
        this.mMotionPaths.add((-binarySearch) - 1, motionPaths);
    }

    public void addKey(MotionKey motionKey) {
        this.mKeyList.add(motionKey);
    }

    public void setPathMotionArc(int i) {
        this.mPathMotionArc = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x015b, code lost:
        r9 = (java.lang.Integer) r6.get(r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(int r20, int r21, float r22, long r23) {
        /*
            r19 = this;
            r0 = r19
            r1 = r23
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            int r7 = r0.mPathMotionArc
            r8 = -1
            if (r7 == r8) goto L_0x0026
            androidx.constraintlayout.core.motion.MotionPaths r9 = r0.mStartMotionPath
            r9.mPathMotionArc = r7
        L_0x0026:
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r7 = r0.mStartPoint
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r9 = r0.mEndPoint
            r7.different(r9, r4)
            java.util.ArrayList r7 = r0.mKeyList
            if (r7 == 0) goto L_0x008e
            java.util.Iterator r7 = r7.iterator()
            r10 = 0
        L_0x0036:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x008f
            java.lang.Object r11 = r7.next()
            androidx.constraintlayout.core.motion.key.MotionKey r11 = (androidx.constraintlayout.core.motion.key.MotionKey) r11
            boolean r12 = r11 instanceof androidx.constraintlayout.core.motion.key.MotionKeyPosition
            if (r12 == 0) goto L_0x0066
            androidx.constraintlayout.core.motion.key.MotionKeyPosition r11 = (androidx.constraintlayout.core.motion.key.MotionKeyPosition) r11
            androidx.constraintlayout.core.motion.MotionPaths r12 = new androidx.constraintlayout.core.motion.MotionPaths
            androidx.constraintlayout.core.motion.MotionPaths r15 = r0.mStartMotionPath
            androidx.constraintlayout.core.motion.MotionPaths r14 = r0.mEndMotionPath
            r13 = r12
            r18 = r14
            r14 = r20
            r17 = r15
            r15 = r21
            r16 = r11
            r13.<init>(r14, r15, r16, r17, r18)
            r0.insertKey(r12)
            int r11 = r11.mCurveFit
            if (r11 == r8) goto L_0x0036
            r0.mCurveFitType = r11
            goto L_0x0036
        L_0x0066:
            boolean r12 = r11 instanceof androidx.constraintlayout.core.motion.key.MotionKeyCycle
            if (r12 == 0) goto L_0x006e
            r11.getAttributeNames(r5)
            goto L_0x0036
        L_0x006e:
            boolean r12 = r11 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle
            if (r12 == 0) goto L_0x0076
            r11.getAttributeNames(r3)
            goto L_0x0036
        L_0x0076:
            boolean r12 = r11 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTrigger
            if (r12 == 0) goto L_0x0087
            if (r10 != 0) goto L_0x0081
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
        L_0x0081:
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger r11 = (androidx.constraintlayout.core.motion.key.MotionKeyTrigger) r11
            r10.add(r11)
            goto L_0x0036
        L_0x0087:
            r11.setInterpolation(r6)
            r11.getAttributeNames(r4)
            goto L_0x0036
        L_0x008e:
            r10 = 0
        L_0x008f:
            r7 = 0
            if (r10 == 0) goto L_0x009c
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger[] r11 = new androidx.constraintlayout.core.motion.key.MotionKeyTrigger[r7]
            java.lang.Object[] r10 = r10.toArray(r11)
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger[] r10 = (androidx.constraintlayout.core.motion.key.MotionKeyTrigger[]) r10
            r0.mKeyTriggers = r10
        L_0x009c:
            boolean r10 = r4.isEmpty()
            java.lang.String r11 = ","
            java.lang.String r12 = "CUSTOM,"
            r13 = 1
            if (r10 != 0) goto L_0x0177
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            r0.mAttributesMap = r10
            java.util.Iterator r10 = r4.iterator()
        L_0x00b2:
            boolean r14 = r10.hasNext()
            if (r14 == 0) goto L_0x0110
            java.lang.Object r14 = r10.next()
            java.lang.String r14 = (java.lang.String) r14
            boolean r15 = r14.startsWith(r12)
            if (r15 == 0) goto L_0x00fd
            androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar r15 = new androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar
            r15.<init>()
            java.lang.String[] r16 = r14.split(r11)
            r9 = r16[r13]
            java.util.ArrayList r8 = r0.mKeyList
            java.util.Iterator r8 = r8.iterator()
        L_0x00d5:
            boolean r17 = r8.hasNext()
            if (r17 == 0) goto L_0x00f8
            java.lang.Object r17 = r8.next()
            r13 = r17
            androidx.constraintlayout.core.motion.key.MotionKey r13 = (androidx.constraintlayout.core.motion.key.MotionKey) r13
            java.util.HashMap r7 = r13.mCustom
            if (r7 != 0) goto L_0x00ea
        L_0x00e7:
            r7 = 0
            r13 = 1
            goto L_0x00d5
        L_0x00ea:
            java.lang.Object r7 = r7.get(r9)
            androidx.constraintlayout.core.motion.CustomVariable r7 = (androidx.constraintlayout.core.motion.CustomVariable) r7
            if (r7 == 0) goto L_0x00e7
            int r13 = r13.mFramePosition
            r15.append(r13, r7)
            goto L_0x00e7
        L_0x00f8:
            androidx.constraintlayout.core.motion.utils.SplineSet r7 = androidx.constraintlayout.core.motion.utils.SplineSet.makeCustomSplineSet(r14, r15)
            goto L_0x0101
        L_0x00fd:
            androidx.constraintlayout.core.motion.utils.SplineSet r7 = androidx.constraintlayout.core.motion.utils.SplineSet.makeSpline(r14, r1)
        L_0x0101:
            if (r7 != 0) goto L_0x0104
            goto L_0x010c
        L_0x0104:
            r7.setType(r14)
            java.util.HashMap r8 = r0.mAttributesMap
            r8.put(r14, r7)
        L_0x010c:
            r7 = 0
            r8 = -1
            r13 = 1
            goto L_0x00b2
        L_0x0110:
            java.util.ArrayList r7 = r0.mKeyList
            if (r7 == 0) goto L_0x012e
            java.util.Iterator r7 = r7.iterator()
        L_0x0118:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x012e
            java.lang.Object r8 = r7.next()
            androidx.constraintlayout.core.motion.key.MotionKey r8 = (androidx.constraintlayout.core.motion.key.MotionKey) r8
            boolean r9 = r8 instanceof androidx.constraintlayout.core.motion.key.MotionKeyAttributes
            if (r9 == 0) goto L_0x0118
            java.util.HashMap r9 = r0.mAttributesMap
            r8.addValues(r9)
            goto L_0x0118
        L_0x012e:
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r7 = r0.mStartPoint
            java.util.HashMap r8 = r0.mAttributesMap
            r9 = 0
            r7.addValues(r8, r9)
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r7 = r0.mEndPoint
            java.util.HashMap r8 = r0.mAttributesMap
            r9 = 100
            r7.addValues(r8, r9)
            java.util.HashMap r7 = r0.mAttributesMap
            java.util.Set r7 = r7.keySet()
            java.util.Iterator r7 = r7.iterator()
        L_0x0149:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0177
            java.lang.Object r8 = r7.next()
            java.lang.String r8 = (java.lang.String) r8
            boolean r9 = r6.containsKey(r8)
            if (r9 == 0) goto L_0x0168
            java.lang.Object r9 = r6.get(r8)
            java.lang.Integer r9 = (java.lang.Integer) r9
            if (r9 == 0) goto L_0x0168
            int r9 = r9.intValue()
            goto L_0x0169
        L_0x0168:
            r9 = 0
        L_0x0169:
            java.util.HashMap r10 = r0.mAttributesMap
            java.lang.Object r8 = r10.get(r8)
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = (androidx.constraintlayout.core.motion.utils.SplineSet) r8
            if (r8 == 0) goto L_0x0149
            r8.setup(r9)
            goto L_0x0149
        L_0x0177:
            boolean r7 = r3.isEmpty()
            if (r7 != 0) goto L_0x023c
            java.util.HashMap r7 = r0.mTimeCycleAttributesMap
            if (r7 != 0) goto L_0x0188
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            r0.mTimeCycleAttributesMap = r7
        L_0x0188:
            java.util.Iterator r3 = r3.iterator()
        L_0x018c:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x01e8
            java.lang.Object r7 = r3.next()
            java.lang.String r7 = (java.lang.String) r7
            java.util.HashMap r8 = r0.mTimeCycleAttributesMap
            boolean r8 = r8.containsKey(r7)
            if (r8 == 0) goto L_0x01a1
            goto L_0x018c
        L_0x01a1:
            boolean r8 = r7.startsWith(r12)
            if (r8 == 0) goto L_0x01dd
            androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar r8 = new androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar
            r8.<init>()
            java.lang.String[] r9 = r7.split(r11)
            r10 = 1
            r9 = r9[r10]
            java.util.ArrayList r10 = r0.mKeyList
            java.util.Iterator r10 = r10.iterator()
        L_0x01b9:
            boolean r13 = r10.hasNext()
            if (r13 == 0) goto L_0x01d8
            java.lang.Object r13 = r10.next()
            androidx.constraintlayout.core.motion.key.MotionKey r13 = (androidx.constraintlayout.core.motion.key.MotionKey) r13
            java.util.HashMap r14 = r13.mCustom
            if (r14 != 0) goto L_0x01ca
            goto L_0x01b9
        L_0x01ca:
            java.lang.Object r14 = r14.get(r9)
            androidx.constraintlayout.core.motion.CustomVariable r14 = (androidx.constraintlayout.core.motion.CustomVariable) r14
            if (r14 == 0) goto L_0x01b9
            int r13 = r13.mFramePosition
            r8.append(r13, r14)
            goto L_0x01b9
        L_0x01d8:
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = androidx.constraintlayout.core.motion.utils.SplineSet.makeCustomSplineSet(r7, r8)
            goto L_0x01e1
        L_0x01dd:
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = androidx.constraintlayout.core.motion.utils.SplineSet.makeSpline(r7, r1)
        L_0x01e1:
            if (r8 != 0) goto L_0x01e4
            goto L_0x018c
        L_0x01e4:
            r8.setType(r7)
            goto L_0x018c
        L_0x01e8:
            java.util.ArrayList r1 = r0.mKeyList
            if (r1 == 0) goto L_0x0208
            java.util.Iterator r1 = r1.iterator()
        L_0x01f0:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0208
            java.lang.Object r2 = r1.next()
            androidx.constraintlayout.core.motion.key.MotionKey r2 = (androidx.constraintlayout.core.motion.key.MotionKey) r2
            boolean r3 = r2 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle
            if (r3 == 0) goto L_0x01f0
            androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle r2 = (androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle) r2
            java.util.HashMap r3 = r0.mTimeCycleAttributesMap
            r2.addTimeValues(r3)
            goto L_0x01f0
        L_0x0208:
            java.util.HashMap r1 = r0.mTimeCycleAttributesMap
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0212:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x023c
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = r6.containsKey(r2)
            if (r3 == 0) goto L_0x022f
            java.lang.Object r3 = r6.get(r2)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            goto L_0x0230
        L_0x022f:
            r3 = 0
        L_0x0230:
            java.util.HashMap r7 = r0.mTimeCycleAttributesMap
            java.lang.Object r2 = r7.get(r2)
            androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet r2 = (androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet) r2
            r2.setup(r3)
            goto L_0x0212
        L_0x023c:
            java.util.ArrayList r1 = r0.mMotionPaths
            int r1 = r1.size()
            r2 = 2
            int r1 = r1 + r2
            androidx.constraintlayout.core.motion.MotionPaths[] r3 = new androidx.constraintlayout.core.motion.MotionPaths[r1]
            androidx.constraintlayout.core.motion.MotionPaths r6 = r0.mStartMotionPath
            r7 = 0
            r3[r7] = r6
            int r6 = r1 + -1
            androidx.constraintlayout.core.motion.MotionPaths r8 = r0.mEndMotionPath
            r3[r6] = r8
            java.util.ArrayList r6 = r0.mMotionPaths
            int r6 = r6.size()
            if (r6 <= 0) goto L_0x0261
            int r6 = r0.mCurveFitType
            int r8 = androidx.constraintlayout.core.motion.key.MotionKey.UNSET
            if (r6 != r8) goto L_0x0261
            r0.mCurveFitType = r7
        L_0x0261:
            java.util.ArrayList r6 = r0.mMotionPaths
            java.util.Iterator r6 = r6.iterator()
            r7 = 1
        L_0x0268:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x027a
            java.lang.Object r8 = r6.next()
            androidx.constraintlayout.core.motion.MotionPaths r8 = (androidx.constraintlayout.core.motion.MotionPaths) r8
            int r9 = r7 + 1
            r3[r7] = r8
            r7 = r9
            goto L_0x0268
        L_0x027a:
            r6 = 18
            java.util.HashSet r7 = new java.util.HashSet
            r7.<init>()
            androidx.constraintlayout.core.motion.MotionPaths r8 = r0.mEndMotionPath
            java.util.HashMap r8 = r8.customAttributes
            java.util.Set r8 = r8.keySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x028d:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x02bc
            java.lang.Object r9 = r8.next()
            java.lang.String r9 = (java.lang.String) r9
            androidx.constraintlayout.core.motion.MotionPaths r10 = r0.mStartMotionPath
            java.util.HashMap r10 = r10.customAttributes
            boolean r10 = r10.containsKey(r9)
            if (r10 == 0) goto L_0x028d
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r12)
            r10.append(r9)
            java.lang.String r10 = r10.toString()
            boolean r10 = r4.contains(r10)
            if (r10 != 0) goto L_0x028d
            r7.add(r9)
            goto L_0x028d
        L_0x02bc:
            r9 = 0
            java.lang.String[] r4 = new java.lang.String[r9]
            java.lang.Object[] r4 = r7.toArray(r4)
            java.lang.String[] r4 = (java.lang.String[]) r4
            r0.mAttributeNames = r4
            int r4 = r4.length
            int[] r4 = new int[r4]
            r0.mAttributeInterpolatorCount = r4
            r4 = 0
        L_0x02cd:
            java.lang.String[] r7 = r0.mAttributeNames
            int r8 = r7.length
            if (r4 >= r8) goto L_0x0304
            r7 = r7[r4]
            int[] r8 = r0.mAttributeInterpolatorCount
            r9 = 0
            r8[r4] = r9
            r8 = 0
        L_0x02da:
            if (r8 >= r1) goto L_0x0301
            r9 = r3[r8]
            java.util.HashMap r9 = r9.customAttributes
            boolean r9 = r9.containsKey(r7)
            if (r9 == 0) goto L_0x02fe
            r9 = r3[r8]
            java.util.HashMap r9 = r9.customAttributes
            java.lang.Object r9 = r9.get(r7)
            androidx.constraintlayout.core.motion.CustomVariable r9 = (androidx.constraintlayout.core.motion.CustomVariable) r9
            if (r9 == 0) goto L_0x02fe
            int[] r7 = r0.mAttributeInterpolatorCount
            r8 = r7[r4]
            int r9 = r9.numberOfInterpolatedValues()
            int r8 = r8 + r9
            r7[r4] = r8
            goto L_0x0301
        L_0x02fe:
            int r8 = r8 + 1
            goto L_0x02da
        L_0x0301:
            int r4 = r4 + 1
            goto L_0x02cd
        L_0x0304:
            r4 = 0
            r8 = r3[r4]
            int r4 = r8.mPathMotionArc
            r8 = -1
            if (r4 == r8) goto L_0x030e
            r4 = 1
            goto L_0x030f
        L_0x030e:
            r4 = 0
        L_0x030f:
            int r7 = r7.length
            int r6 = r6 + r7
            boolean[] r7 = new boolean[r6]
            r8 = 1
        L_0x0314:
            if (r8 >= r1) goto L_0x0324
            r9 = r3[r8]
            int r10 = r8 + -1
            r10 = r3[r10]
            java.lang.String[] r11 = r0.mAttributeNames
            r9.different(r10, r7, r11, r4)
            int r8 = r8 + 1
            goto L_0x0314
        L_0x0324:
            r4 = 1
            r8 = 0
        L_0x0326:
            if (r4 >= r6) goto L_0x0331
            boolean r9 = r7[r4]
            if (r9 == 0) goto L_0x032e
            int r8 = r8 + 1
        L_0x032e:
            int r4 = r4 + 1
            goto L_0x0326
        L_0x0331:
            int[] r4 = new int[r8]
            r0.mInterpolateVariables = r4
            int r4 = java.lang.Math.max(r2, r8)
            double[] r8 = new double[r4]
            r0.mInterpolateData = r8
            double[] r4 = new double[r4]
            r0.mInterpolateVelocity = r4
            r4 = 1
            r8 = 0
        L_0x0343:
            if (r4 >= r6) goto L_0x0353
            boolean r9 = r7[r4]
            if (r9 == 0) goto L_0x0350
            int[] r9 = r0.mInterpolateVariables
            int r10 = r8 + 1
            r9[r8] = r4
            r8 = r10
        L_0x0350:
            int r4 = r4 + 1
            goto L_0x0343
        L_0x0353:
            int[] r4 = r0.mInterpolateVariables
            int r4 = r4.length
            int[] r6 = new int[r2]
            r7 = 1
            r6[r7] = r4
            r4 = 0
            r6[r4] = r1
            java.lang.Class r4 = java.lang.Double.TYPE
            java.lang.Object r4 = java.lang.reflect.Array.newInstance(r4, r6)
            double[][] r4 = (double[][]) r4
            double[] r6 = new double[r1]
            r7 = 0
        L_0x0369:
            if (r7 >= r1) goto L_0x037e
            r8 = r3[r7]
            r9 = r4[r7]
            int[] r10 = r0.mInterpolateVariables
            r8.fillStandard(r9, r10)
            r8 = r3[r7]
            float r8 = r8.time
            double r8 = (double) r8
            r6[r7] = r8
            int r7 = r7 + 1
            goto L_0x0369
        L_0x037e:
            r7 = 0
        L_0x037f:
            int[] r8 = r0.mInterpolateVariables
            int r9 = r8.length
            if (r7 >= r9) goto L_0x03c1
            r8 = r8[r7]
            java.lang.String[] r9 = androidx.constraintlayout.core.motion.MotionPaths.names
            int r9 = r9.length
            if (r8 >= r9) goto L_0x03be
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String[] r9 = androidx.constraintlayout.core.motion.MotionPaths.names
            int[] r10 = r0.mInterpolateVariables
            r10 = r10[r7]
            r9 = r9[r10]
            r8.append(r9)
            java.lang.String r9 = " ["
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r9 = r8
            r8 = 0
        L_0x03a6:
            if (r8 >= r1) goto L_0x03be
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r9)
            r9 = r4[r8]
            r11 = r9[r7]
            r10.append(r11)
            java.lang.String r9 = r10.toString()
            int r8 = r8 + 1
            goto L_0x03a6
        L_0x03be:
            int r7 = r7 + 1
            goto L_0x037f
        L_0x03c1:
            java.lang.String[] r7 = r0.mAttributeNames
            int r7 = r7.length
            r8 = 1
            int r7 = r7 + r8
            androidx.constraintlayout.core.motion.utils.CurveFit[] r7 = new androidx.constraintlayout.core.motion.utils.CurveFit[r7]
            r0.mSpline = r7
            r7 = 0
        L_0x03cb:
            java.lang.String[] r8 = r0.mAttributeNames
            int r9 = r8.length
            if (r7 >= r9) goto L_0x0431
            r8 = r8[r7]
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x03d6:
            if (r9 >= r1) goto L_0x0415
            r13 = r3[r9]
            boolean r13 = r13.hasCustomData(r8)
            if (r13 == 0) goto L_0x040d
            if (r12 != 0) goto L_0x03fb
            double[] r11 = new double[r1]
            r12 = r3[r9]
            int r12 = r12.getCustomDataCount(r8)
            int[] r13 = new int[r2]
            r14 = 1
            r13[r14] = r12
            r14 = 0
            r13[r14] = r1
            java.lang.Class r12 = java.lang.Double.TYPE
            java.lang.Object r12 = java.lang.reflect.Array.newInstance(r12, r13)
            double[][] r12 = (double[][]) r12
            goto L_0x03fc
        L_0x03fb:
            r14 = 0
        L_0x03fc:
            r13 = r3[r9]
            float r15 = r13.time
            r17 = r3
            double r2 = (double) r15
            r11[r10] = r2
            r2 = r12[r10]
            r13.getCustomData(r8, r2, r14)
            int r10 = r10 + 1
            goto L_0x040f
        L_0x040d:
            r17 = r3
        L_0x040f:
            int r9 = r9 + 1
            r3 = r17
            r2 = 2
            goto L_0x03d6
        L_0x0415:
            r17 = r3
            double[] r2 = java.util.Arrays.copyOf(r11, r10)
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r12, r10)
            double[][] r3 = (double[][]) r3
            androidx.constraintlayout.core.motion.utils.CurveFit[] r8 = r0.mSpline
            int r7 = r7 + 1
            int r9 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r2 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r9, r2, r3)
            r8[r7] = r2
            r3 = r17
            r2 = 2
            goto L_0x03cb
        L_0x0431:
            r17 = r3
            androidx.constraintlayout.core.motion.utils.CurveFit[] r2 = r0.mSpline
            int r3 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r3 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r3, r6, r4)
            r4 = 0
            r2[r4] = r3
            r2 = r17[r4]
            int r2 = r2.mPathMotionArc
            r3 = -1
            if (r2 == r3) goto L_0x047e
            int[] r2 = new int[r1]
            double[] r3 = new double[r1]
            r6 = 2
            int[] r7 = new int[r6]
            r8 = 1
            r7[r8] = r6
            r7[r4] = r1
            java.lang.Class r4 = java.lang.Double.TYPE
            java.lang.Object r4 = java.lang.reflect.Array.newInstance(r4, r7)
            double[][] r4 = (double[][]) r4
            r9 = 0
        L_0x045a:
            if (r9 >= r1) goto L_0x0478
            r6 = r17[r9]
            int r7 = r6.mPathMotionArc
            r2[r9] = r7
            float r7 = r6.time
            double r7 = (double) r7
            r3[r9] = r7
            r7 = r4[r9]
            float r8 = r6.x
            double r10 = (double) r8
            r8 = 0
            r7[r8] = r10
            float r6 = r6.y
            double r10 = (double) r6
            r6 = 1
            r7[r6] = r10
            int r9 = r9 + 1
            goto L_0x045a
        L_0x0478:
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = androidx.constraintlayout.core.motion.utils.CurveFit.getArc(r2, r3, r4)
            r0.mArcSpline = r1
        L_0x047e:
            r1 = 2143289344(0x7fc00000, float:NaN)
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.mCycleMap = r2
            java.util.ArrayList r2 = r0.mKeyList
            if (r2 == 0) goto L_0x04f3
            java.util.Iterator r2 = r5.iterator()
        L_0x048f:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04bb
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            androidx.constraintlayout.core.motion.utils.KeyCycleOscillator r4 = androidx.constraintlayout.core.motion.utils.KeyCycleOscillator.makeWidgetCycle(r3)
            if (r4 != 0) goto L_0x04a2
            goto L_0x048f
        L_0x04a2:
            boolean r5 = r4.variesByPath()
            if (r5 == 0) goto L_0x04b2
            boolean r5 = java.lang.Float.isNaN(r1)
            if (r5 == 0) goto L_0x04b2
            float r1 = r19.getPreCycleDistance()
        L_0x04b2:
            r4.setType(r3)
            java.util.HashMap r5 = r0.mCycleMap
            r5.put(r3, r4)
            goto L_0x048f
        L_0x04bb:
            java.util.ArrayList r2 = r0.mKeyList
            java.util.Iterator r2 = r2.iterator()
        L_0x04c1:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04d9
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.core.motion.key.MotionKey r3 = (androidx.constraintlayout.core.motion.key.MotionKey) r3
            boolean r4 = r3 instanceof androidx.constraintlayout.core.motion.key.MotionKeyCycle
            if (r4 == 0) goto L_0x04c1
            androidx.constraintlayout.core.motion.key.MotionKeyCycle r3 = (androidx.constraintlayout.core.motion.key.MotionKeyCycle) r3
            java.util.HashMap r4 = r0.mCycleMap
            r3.addCycleValues(r4)
            goto L_0x04c1
        L_0x04d9:
            java.util.HashMap r2 = r0.mCycleMap
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x04e3:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04f3
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.core.motion.utils.KeyCycleOscillator r3 = (androidx.constraintlayout.core.motion.utils.KeyCycleOscillator) r3
            r3.setup(r1)
            goto L_0x04e3
        L_0x04f3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.Motion.setup(int, int, float, long):void");
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.x + " y: " + this.mStartMotionPath.y + " end: x: " + this.mEndMotionPath.x + " y: " + this.mEndMotionPath.y;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) this.mView.getX(), (float) this.mView.getY(), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(MotionWidget motionWidget) {
        this.mView = motionWidget;
    }

    public MotionWidget getView() {
        return this.mView;
    }

    public void setStart(MotionWidget motionWidget) {
        MotionPaths motionPaths = this.mStartMotionPath;
        motionPaths.time = 0.0f;
        motionPaths.position = 0.0f;
        motionPaths.setBounds((float) motionWidget.getX(), (float) motionWidget.getY(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mStartMotionPath.applyParameters(motionWidget);
        this.mStartPoint.setState(motionWidget);
    }

    public void setEnd(MotionWidget motionWidget) {
        MotionPaths motionPaths = this.mEndMotionPath;
        motionPaths.time = 1.0f;
        motionPaths.position = 1.0f;
        readView(motionPaths);
        this.mEndMotionPath.setBounds((float) motionWidget.getLeft(), (float) motionWidget.getTop(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mEndMotionPath.applyParameters(motionWidget);
        this.mEndPoint.setState(motionWidget);
    }

    public void setStartState(ViewState viewState, MotionWidget motionWidget, int i, int i2, int i3) {
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
        this.mStartPoint.setState(rect, motionWidget, i, viewState.rotation);
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

    public boolean interpolate(MotionWidget motionWidget, float f, long j, KeyCache keyCache) {
        double d;
        MotionWidget motionWidget2 = motionWidget;
        float adjustedPosition = getAdjustedPosition(f, (float[]) null);
        int i = this.mQuantizeMotionSteps;
        if (i != -1) {
            float f2 = 1.0f / ((float) i);
            float floor = ((float) Math.floor((double) (adjustedPosition / f2))) * f2;
            float f3 = (adjustedPosition % f2) / f2;
            if (!Float.isNaN(this.mQuantizeMotionPhase)) {
                f3 = (f3 + this.mQuantizeMotionPhase) % 1.0f;
            }
            DifferentialInterpolator differentialInterpolator = this.mQuantizeMotionInterpolator;
            adjustedPosition = ((differentialInterpolator != null ? differentialInterpolator.getInterpolation(f3) : ((double) f3) > 0.5d ? 1.0f : 0.0f) * f2) + floor;
        }
        float f4 = adjustedPosition;
        HashMap hashMap = this.mAttributesMap;
        if (hashMap != null) {
            for (SplineSet property : hashMap.values()) {
                property.setProperty(motionWidget2, f4);
            }
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            double d2 = (double) f4;
            curveFitArr[0].getPos(d2, this.mInterpolateData);
            this.mSpline[0].getSlope(d2, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d2, dArr);
                    this.mArcSpline.getSlope(d2, this.mInterpolateVelocity);
                }
            }
            if (!this.mNoMovement) {
                d = d2;
                this.mStartMotionPath.setView(f4, motionWidget, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, (double[]) null);
            } else {
                d = d2;
            }
            if (this.mTransformPivotTarget != -1) {
                if (this.mTransformPivotView == null) {
                    this.mTransformPivotView = motionWidget.getParent().findViewById(this.mTransformPivotTarget);
                }
                MotionWidget motionWidget3 = this.mTransformPivotView;
                if (motionWidget3 != null) {
                    float top = ((float) (motionWidget3.getTop() + this.mTransformPivotView.getBottom())) / 2.0f;
                    float left = ((float) (this.mTransformPivotView.getLeft() + this.mTransformPivotView.getRight())) / 2.0f;
                    if (motionWidget.getRight() - motionWidget.getLeft() > 0 && motionWidget.getBottom() - motionWidget.getTop() > 0) {
                        motionWidget2.setPivotX(left - ((float) motionWidget.getLeft()));
                        motionWidget2.setPivotY(top - ((float) motionWidget.getTop()));
                    }
                }
            }
            int i2 = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i2 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i2].getPos(d, this.mValuesBuff);
                ((CustomVariable) this.mStartMotionPath.customAttributes.get(this.mAttributeNames[i2 - 1])).setInterpolatedValue(motionWidget2, this.mValuesBuff);
                i2++;
            }
            MotionConstrainedPoint motionConstrainedPoint = this.mStartPoint;
            if (motionConstrainedPoint.mVisibilityMode == 0) {
                if (f4 <= 0.0f) {
                    motionWidget2.setVisibility(motionConstrainedPoint.visibility);
                } else if (f4 >= 1.0f) {
                    motionWidget2.setVisibility(this.mEndPoint.visibility);
                } else if (this.mEndPoint.visibility != motionConstrainedPoint.visibility) {
                    motionWidget2.setVisibility(4);
                }
            }
            if (this.mKeyTriggers != null) {
                int i3 = 0;
                while (true) {
                    MotionKeyTrigger[] motionKeyTriggerArr = this.mKeyTriggers;
                    if (i3 >= motionKeyTriggerArr.length) {
                        break;
                    }
                    motionKeyTriggerArr[i3].conditionallyFire(f4, motionWidget2);
                    i3++;
                }
            }
        } else {
            MotionPaths motionPaths = this.mStartMotionPath;
            float f5 = motionPaths.x;
            MotionPaths motionPaths2 = this.mEndMotionPath;
            float f6 = f5 + ((motionPaths2.x - f5) * f4);
            float f7 = motionPaths.y;
            float f8 = f7 + ((motionPaths2.y - f7) * f4);
            float f9 = motionPaths.width;
            float f10 = f9 + ((motionPaths2.width - f9) * f4);
            float f11 = motionPaths.height;
            float f12 = f6 + 0.5f;
            float f13 = f8 + 0.5f;
            motionWidget2.layout((int) f12, (int) f13, (int) (f12 + f10), (int) (f13 + f11 + ((motionPaths2.height - f11) * f4)));
        }
        HashMap hashMap2 = this.mCycleMap;
        if (hashMap2 == null) {
            return false;
        }
        for (KeyCycleOscillator keyCycleOscillator : hashMap2.values()) {
            if (keyCycleOscillator instanceof KeyCycleOscillator.PathRotateSet) {
                double[] dArr2 = this.mInterpolateVelocity;
                ((KeyCycleOscillator.PathRotateSet) keyCycleOscillator).setPathRotate(motionWidget, f4, dArr2[0], dArr2[1]);
            } else {
                keyCycleOscillator.setProperty(motionWidget2, f4);
            }
        }
        return false;
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

    public int getKeyFramePositions(int[] iArr, float[] fArr) {
        Iterator it = this.mKeyList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            MotionKey motionKey = (MotionKey) it.next();
            int i3 = motionKey.mFramePosition;
            iArr[i] = (motionKey.mType * 1000) + i3;
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
            MotionKey motionKey = (MotionKey) it.next();
            int i5 = motionKey.mType;
            if (i5 == i2 || i2 != -1) {
                iArr[i4] = 0;
                int i6 = i4 + 1;
                iArr[i6] = i5;
                int i7 = i6 + 1;
                int i8 = motionKey.mFramePosition;
                iArr[i7] = i8;
                double d = (double) (((float) i8) / 100.0f);
                this.mSpline[0].getPos(d, this.mInterpolateData);
                this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
                int i9 = i7 + 1;
                iArr[i9] = Float.floatToIntBits(fArr[0]);
                int i10 = i9 + 1;
                iArr[i10] = Float.floatToIntBits(fArr[1]);
                if (motionKey instanceof MotionKeyPosition) {
                    MotionKeyPosition motionKeyPosition = (MotionKeyPosition) motionKey;
                    int i11 = i10 + 1;
                    iArr[i11] = motionKeyPosition.mPositionType;
                    int i12 = i11 + 1;
                    iArr[i12] = Float.floatToIntBits(motionKeyPosition.mPercentX);
                    i10 = i12 + 1;
                    iArr[i10] = Float.floatToIntBits(motionKeyPosition.mPercentY);
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
