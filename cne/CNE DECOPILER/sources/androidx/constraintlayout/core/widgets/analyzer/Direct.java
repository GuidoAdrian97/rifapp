package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;

public class Direct {
    private static int hcount = 0;
    private static BasicMeasure.Measure measure = new BasicMeasure.Measure();
    private static int vcount = 0;

    public static void solvingPass(ConstraintWidgetContainer constraintWidgetContainer, BasicMeasure.Measurer measurer) {
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidgetContainer.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidgetContainer.getVerticalDimensionBehaviour();
        hcount = 0;
        vcount = 0;
        constraintWidgetContainer.resetFinalResolution();
        ArrayList children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) children.get(i)).resetFinalResolution();
        }
        boolean isRtl = constraintWidgetContainer.isRtl();
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalHorizontal(0, constraintWidgetContainer.getWidth());
        } else {
            constraintWidgetContainer.setFinalLeft(0);
        }
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) children.get(i2);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    if (guideline.getRelativeBegin() != -1) {
                        guideline.setFinalValue(guideline.getRelativeBegin());
                    } else if (guideline.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue(constraintWidgetContainer.getWidth() - guideline.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue((int) ((guideline.getRelativePercent() * ((float) constraintWidgetContainer.getWidth())) + 0.5f));
                    }
                    z = true;
                }
            } else if ((constraintWidget instanceof Barrier) && ((Barrier) constraintWidget).getOrientation() == 0) {
                z2 = true;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) children.get(i3);
                if (constraintWidget2 instanceof Guideline) {
                    Guideline guideline2 = (Guideline) constraintWidget2;
                    if (guideline2.getOrientation() == 1) {
                        horizontalSolvingPass(0, guideline2, measurer, isRtl);
                    }
                }
            }
        }
        horizontalSolvingPass(0, constraintWidgetContainer, measurer, isRtl);
        if (z2) {
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) children.get(i4);
                if (constraintWidget3 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget3;
                    if (barrier.getOrientation() == 0) {
                        solveBarrier(0, barrier, measurer, 0, isRtl);
                    }
                }
            }
        }
        if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalVertical(0, constraintWidgetContainer.getHeight());
        } else {
            constraintWidgetContainer.setFinalTop(0);
        }
        boolean z3 = false;
        boolean z4 = false;
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget4 = (ConstraintWidget) children.get(i5);
            if (constraintWidget4 instanceof Guideline) {
                Guideline guideline3 = (Guideline) constraintWidget4;
                if (guideline3.getOrientation() == 0) {
                    if (guideline3.getRelativeBegin() != -1) {
                        guideline3.setFinalValue(guideline3.getRelativeBegin());
                    } else if (guideline3.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue(constraintWidgetContainer.getHeight() - guideline3.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue((int) ((guideline3.getRelativePercent() * ((float) constraintWidgetContainer.getHeight())) + 0.5f));
                    }
                    z3 = true;
                }
            } else if ((constraintWidget4 instanceof Barrier) && ((Barrier) constraintWidget4).getOrientation() == 1) {
                z4 = true;
            }
        }
        if (z3) {
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget) children.get(i6);
                if (constraintWidget5 instanceof Guideline) {
                    Guideline guideline4 = (Guideline) constraintWidget5;
                    if (guideline4.getOrientation() == 0) {
                        verticalSolvingPass(1, guideline4, measurer);
                    }
                }
            }
        }
        verticalSolvingPass(0, constraintWidgetContainer, measurer);
        if (z4) {
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget6 = (ConstraintWidget) children.get(i7);
                if (constraintWidget6 instanceof Barrier) {
                    Barrier barrier2 = (Barrier) constraintWidget6;
                    if (barrier2.getOrientation() == 1) {
                        solveBarrier(0, barrier2, measurer, 1, isRtl);
                    }
                }
            }
        }
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget7 = (ConstraintWidget) children.get(i8);
            if (constraintWidget7.isMeasureRequested() && canMeasure(0, constraintWidget7)) {
                ConstraintWidgetContainer.measure(0, constraintWidget7, measurer, measure, BasicMeasure.Measure.SELF_DIMENSIONS);
                if (!(constraintWidget7 instanceof Guideline)) {
                    horizontalSolvingPass(0, constraintWidget7, measurer, isRtl);
                    verticalSolvingPass(0, constraintWidget7, measurer);
                } else if (((Guideline) constraintWidget7).getOrientation() == 0) {
                    verticalSolvingPass(0, constraintWidget7, measurer);
                } else {
                    horizontalSolvingPass(0, constraintWidget7, measurer, isRtl);
                }
            }
        }
    }

    private static void solveBarrier(int i, Barrier barrier, BasicMeasure.Measurer measurer, int i2, boolean z) {
        if (!barrier.allSolved()) {
            return;
        }
        if (i2 == 0) {
            horizontalSolvingPass(i + 1, barrier, measurer, z);
        } else {
            verticalSolvingPass(i + 1, barrier, measurer);
        }
    }

    public static String ls(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
        sb.append("+-(" + i + ") ");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bb, code lost:
        r11 = r12.mRight.mTarget;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00cb, code lost:
        r8 = r12.mLeft.mTarget;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void horizontalSolvingPass(int r16, androidx.constraintlayout.core.widgets.ConstraintWidget r17, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r18, boolean r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r17.isHorizontalSolvingPassDone()
            if (r3 == 0) goto L_0x000d
            return
        L_0x000d:
            int r3 = hcount
            r4 = 1
            int r3 = r3 + r4
            hcount = r3
            boolean r3 = r0 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r3 != 0) goto L_0x002f
            boolean r3 = r17.isMeasureRequested()
            if (r3 == 0) goto L_0x002f
            int r3 = r16 + 1
            boolean r5 = canMeasure(r3, r0)
            if (r5 == 0) goto L_0x002f
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r5 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r5.<init>()
            int r6 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r3, r0, r1, r5, r6)
        L_0x002f:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r0.getAnchor(r3)
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r0.getAnchor(r5)
            int r6 = r3.getFinalValue()
            int r7 = r5.getFinalValue()
            java.util.HashSet r8 = r3.getDependents()
            r9 = 0
            r10 = 8
            if (r8 == 0) goto L_0x013e
            boolean r8 = r3.hasFinalValue()
            if (r8 == 0) goto L_0x013e
            java.util.HashSet r3 = r3.getDependents()
            java.util.Iterator r3 = r3.iterator()
        L_0x005a:
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x013e
            java.lang.Object r8 = r3.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r8
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r8.mOwner
            int r13 = r16 + 1
            boolean r14 = canMeasure(r13, r12)
            boolean r15 = r12.isMeasureRequested()
            if (r15 == 0) goto L_0x0080
            if (r14 == 0) goto L_0x0080
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r15 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r15.<init>()
            int r11 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r13, r12, r1, r15, r11)
        L_0x0080:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r12.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r15) goto L_0x00e7
            if (r14 == 0) goto L_0x008b
            goto L_0x00e7
        L_0x008b:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r12.getHorizontalDimensionBehaviour()
            if (r11 != r15) goto L_0x005a
            int r11 = r12.mMatchConstraintMaxWidth
            if (r11 < 0) goto L_0x005a
            int r11 = r12.mMatchConstraintMinWidth
            if (r11 < 0) goto L_0x005a
            int r11 = r12.getVisibility()
            if (r11 == r10) goto L_0x00ab
            int r11 = r12.mMatchConstraintDefaultWidth
            if (r11 != 0) goto L_0x005a
            float r11 = r12.getDimensionRatio()
            int r11 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x005a
        L_0x00ab:
            boolean r11 = r12.isInHorizontalChain()
            if (r11 != 0) goto L_0x005a
            boolean r11 = r12.isInVirtualLayout()
            if (r11 != 0) goto L_0x005a
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r12.mLeft
            if (r8 != r11) goto L_0x00c7
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r12.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x00c7
            boolean r11 = r11.hasFinalValue()
            if (r11 != 0) goto L_0x00d7
        L_0x00c7:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r12.mRight
            if (r8 != r11) goto L_0x00d9
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r12.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r8.mTarget
            if (r8 == 0) goto L_0x00d9
            boolean r8 = r8.hasFinalValue()
            if (r8 == 0) goto L_0x00d9
        L_0x00d7:
            r8 = 1
            goto L_0x00da
        L_0x00d9:
            r8 = 0
        L_0x00da:
            if (r8 == 0) goto L_0x005a
            boolean r8 = r12.isInHorizontalChain()
            if (r8 != 0) goto L_0x005a
            solveHorizontalMatchConstraint(r13, r0, r1, r12, r2)
            goto L_0x005a
        L_0x00e7:
            boolean r11 = r12.isMeasureRequested()
            if (r11 == 0) goto L_0x00ef
            goto L_0x005a
        L_0x00ef:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r12.mLeft
            if (r8 != r11) goto L_0x010b
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r12.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r14.mTarget
            if (r14 != 0) goto L_0x010b
            int r8 = r11.getMargin()
            int r8 = r8 + r6
            int r11 = r12.getWidth()
            int r11 = r11 + r8
            r12.setFinalHorizontal(r8, r11)
            horizontalSolvingPass(r13, r12, r1, r2)
            goto L_0x005a
        L_0x010b:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r12.mRight
            if (r8 != r14) goto L_0x0127
            androidx.constraintlayout.core.widgets.ConstraintAnchor r15 = r11.mTarget
            if (r15 != 0) goto L_0x0127
            int r8 = r14.getMargin()
            int r8 = r6 - r8
            int r11 = r12.getWidth()
            int r11 = r8 - r11
            r12.setFinalHorizontal(r11, r8)
            horizontalSolvingPass(r13, r12, r1, r2)
            goto L_0x005a
        L_0x0127:
            if (r8 != r11) goto L_0x005a
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r14.mTarget
            if (r8 == 0) goto L_0x005a
            boolean r8 = r8.hasFinalValue()
            if (r8 == 0) goto L_0x005a
            boolean r8 = r12.isInHorizontalChain()
            if (r8 != 0) goto L_0x005a
            solveHorizontalCenterConstraints(r13, r1, r12, r2)
            goto L_0x005a
        L_0x013e:
            boolean r3 = r0 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r3 == 0) goto L_0x0143
            return
        L_0x0143:
            java.util.HashSet r3 = r5.getDependents()
            if (r3 == 0) goto L_0x0231
            boolean r3 = r5.hasFinalValue()
            if (r3 == 0) goto L_0x0231
            java.util.HashSet r3 = r5.getDependents()
            java.util.Iterator r3 = r3.iterator()
        L_0x0157:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0231
            java.lang.Object r5 = r3.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r5.mOwner
            int r8 = r16 + 1
            boolean r11 = canMeasure(r8, r6)
            boolean r12 = r6.isMeasureRequested()
            if (r12 == 0) goto L_0x017d
            if (r11 == 0) goto L_0x017d
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r12 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r12.<init>()
            int r13 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r8, r6, r1, r12, r13)
        L_0x017d:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r6.mLeft
            if (r5 != r12) goto L_0x018d
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r6.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 == 0) goto L_0x018d
            boolean r12 = r12.hasFinalValue()
            if (r12 != 0) goto L_0x019d
        L_0x018d:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r6.mRight
            if (r5 != r12) goto L_0x019f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r6.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 == 0) goto L_0x019f
            boolean r12 = r12.hasFinalValue()
            if (r12 == 0) goto L_0x019f
        L_0x019d:
            r12 = 1
            goto L_0x01a0
        L_0x019f:
            r12 = 0
        L_0x01a0:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = r6.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r13 != r14) goto L_0x01e4
            if (r11 == 0) goto L_0x01ab
            goto L_0x01e4
        L_0x01ab:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = r6.getHorizontalDimensionBehaviour()
            if (r5 != r14) goto L_0x0157
            int r5 = r6.mMatchConstraintMaxWidth
            if (r5 < 0) goto L_0x0157
            int r5 = r6.mMatchConstraintMinWidth
            if (r5 < 0) goto L_0x0157
            int r5 = r6.getVisibility()
            if (r5 == r10) goto L_0x01cb
            int r5 = r6.mMatchConstraintDefaultWidth
            if (r5 != 0) goto L_0x0157
            float r5 = r6.getDimensionRatio()
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 != 0) goto L_0x0157
        L_0x01cb:
            boolean r5 = r6.isInHorizontalChain()
            if (r5 != 0) goto L_0x0157
            boolean r5 = r6.isInVirtualLayout()
            if (r5 != 0) goto L_0x0157
            if (r12 == 0) goto L_0x0157
            boolean r5 = r6.isInHorizontalChain()
            if (r5 != 0) goto L_0x0157
            solveHorizontalMatchConstraint(r8, r0, r1, r6, r2)
            goto L_0x0157
        L_0x01e4:
            boolean r11 = r6.isMeasureRequested()
            if (r11 == 0) goto L_0x01ec
            goto L_0x0157
        L_0x01ec:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r6.mLeft
            if (r5 != r11) goto L_0x0208
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r6.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 != 0) goto L_0x0208
            int r5 = r11.getMargin()
            int r5 = r5 + r7
            int r11 = r6.getWidth()
            int r11 = r11 + r5
            r6.setFinalHorizontal(r5, r11)
            horizontalSolvingPass(r8, r6, r1, r2)
            goto L_0x0157
        L_0x0208:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r6.mRight
            if (r5 != r13) goto L_0x0224
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r11.mTarget
            if (r5 != 0) goto L_0x0224
            int r5 = r13.getMargin()
            int r5 = r7 - r5
            int r11 = r6.getWidth()
            int r11 = r5 - r11
            r6.setFinalHorizontal(r11, r5)
            horizontalSolvingPass(r8, r6, r1, r2)
            goto L_0x0157
        L_0x0224:
            if (r12 == 0) goto L_0x0157
            boolean r5 = r6.isInHorizontalChain()
            if (r5 != 0) goto L_0x0157
            solveHorizontalCenterConstraints(r8, r1, r6, r2)
            goto L_0x0157
        L_0x0231:
            r17.markHorizontalSolvingPassDone()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Direct.horizontalSolvingPass(int, androidx.constraintlayout.core.widgets.ConstraintWidget, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ba, code lost:
        r13 = r11.mBottom.mTarget;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ca, code lost:
        r7 = r11.mTop.mTarget;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void verticalSolvingPass(int r16, androidx.constraintlayout.core.widgets.ConstraintWidget r17, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r18) {
        /*
            r0 = r17
            r1 = r18
            boolean r2 = r17.isVerticalSolvingPassDone()
            if (r2 == 0) goto L_0x000b
            return
        L_0x000b:
            int r2 = vcount
            r3 = 1
            int r2 = r2 + r3
            vcount = r2
            boolean r2 = r0 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r2 != 0) goto L_0x002d
            boolean r2 = r17.isMeasureRequested()
            if (r2 == 0) goto L_0x002d
            int r2 = r16 + 1
            boolean r4 = canMeasure(r2, r0)
            if (r4 == 0) goto L_0x002d
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r4 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r4.<init>()
            int r5 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r2, r0, r1, r4, r5)
        L_0x002d:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.getAnchor(r2)
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            int r5 = r2.getFinalValue()
            int r6 = r4.getFinalValue()
            java.util.HashSet r7 = r2.getDependents()
            r8 = 0
            r9 = 8
            r10 = 0
            if (r7 == 0) goto L_0x0137
            boolean r7 = r2.hasFinalValue()
            if (r7 == 0) goto L_0x0137
            java.util.HashSet r2 = r2.getDependents()
            java.util.Iterator r2 = r2.iterator()
        L_0x0059:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0137
            java.lang.Object r7 = r2.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r7
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r7.mOwner
            int r12 = r16 + 1
            boolean r13 = canMeasure(r12, r11)
            boolean r14 = r11.isMeasureRequested()
            if (r14 == 0) goto L_0x007f
            if (r13 == 0) goto L_0x007f
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r14 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r14.<init>()
            int r15 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r12, r11, r1, r14, r15)
        L_0x007f:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = r11.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r14 != r15) goto L_0x00e6
            if (r13 == 0) goto L_0x008a
            goto L_0x00e6
        L_0x008a:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = r11.getVerticalDimensionBehaviour()
            if (r13 != r15) goto L_0x0059
            int r13 = r11.mMatchConstraintMaxHeight
            if (r13 < 0) goto L_0x0059
            int r13 = r11.mMatchConstraintMinHeight
            if (r13 < 0) goto L_0x0059
            int r13 = r11.getVisibility()
            if (r13 == r9) goto L_0x00aa
            int r13 = r11.mMatchConstraintDefaultHeight
            if (r13 != 0) goto L_0x0059
            float r13 = r11.getDimensionRatio()
            int r13 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r13 != 0) goto L_0x0059
        L_0x00aa:
            boolean r13 = r11.isInVerticalChain()
            if (r13 != 0) goto L_0x0059
            boolean r13 = r11.isInVirtualLayout()
            if (r13 != 0) goto L_0x0059
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r11.mTop
            if (r7 != r13) goto L_0x00c6
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r11.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 == 0) goto L_0x00c6
            boolean r13 = r13.hasFinalValue()
            if (r13 != 0) goto L_0x00d6
        L_0x00c6:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r11.mBottom
            if (r7 != r13) goto L_0x00d8
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r11.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x00d8
            boolean r7 = r7.hasFinalValue()
            if (r7 == 0) goto L_0x00d8
        L_0x00d6:
            r7 = 1
            goto L_0x00d9
        L_0x00d8:
            r7 = 0
        L_0x00d9:
            if (r7 == 0) goto L_0x0059
            boolean r7 = r11.isInVerticalChain()
            if (r7 != 0) goto L_0x0059
            solveVerticalMatchConstraint(r12, r0, r1, r11)
            goto L_0x0059
        L_0x00e6:
            boolean r13 = r11.isMeasureRequested()
            if (r13 == 0) goto L_0x00ee
            goto L_0x0059
        L_0x00ee:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r11.mTop
            if (r7 != r13) goto L_0x010a
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r11.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r14.mTarget
            if (r14 != 0) goto L_0x010a
            int r7 = r13.getMargin()
            int r7 = r7 + r5
            int r13 = r11.getHeight()
            int r13 = r13 + r7
            r11.setFinalVertical(r7, r13)
            verticalSolvingPass(r12, r11, r1)
            goto L_0x0059
        L_0x010a:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r11.mBottom
            if (r7 != r14) goto L_0x0126
            androidx.constraintlayout.core.widgets.ConstraintAnchor r15 = r14.mTarget
            if (r15 != 0) goto L_0x0126
            int r7 = r14.getMargin()
            int r7 = r5 - r7
            int r13 = r11.getHeight()
            int r13 = r7 - r13
            r11.setFinalVertical(r13, r7)
            verticalSolvingPass(r12, r11, r1)
            goto L_0x0059
        L_0x0126:
            if (r7 != r13) goto L_0x0059
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r14.mTarget
            if (r7 == 0) goto L_0x0059
            boolean r7 = r7.hasFinalValue()
            if (r7 == 0) goto L_0x0059
            solveVerticalCenterConstraints(r12, r1, r11)
            goto L_0x0059
        L_0x0137:
            boolean r2 = r0 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r2 == 0) goto L_0x013c
            return
        L_0x013c:
            java.util.HashSet r2 = r4.getDependents()
            if (r2 == 0) goto L_0x022a
            boolean r2 = r4.hasFinalValue()
            if (r2 == 0) goto L_0x022a
            java.util.HashSet r2 = r4.getDependents()
            java.util.Iterator r2 = r2.iterator()
        L_0x0150:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x022a
            java.lang.Object r4 = r2.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r4
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r4.mOwner
            int r7 = r16 + 1
            boolean r11 = canMeasure(r7, r5)
            boolean r12 = r5.isMeasureRequested()
            if (r12 == 0) goto L_0x0176
            if (r11 == 0) goto L_0x0176
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r12 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r12.<init>()
            int r13 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r7, r5, r1, r12, r13)
        L_0x0176:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mTop
            if (r4 != r12) goto L_0x0186
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 == 0) goto L_0x0186
            boolean r12 = r12.hasFinalValue()
            if (r12 != 0) goto L_0x0196
        L_0x0186:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mBottom
            if (r4 != r12) goto L_0x0198
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 == 0) goto L_0x0198
            boolean r12 = r12.hasFinalValue()
            if (r12 == 0) goto L_0x0198
        L_0x0196:
            r12 = 1
            goto L_0x0199
        L_0x0198:
            r12 = 0
        L_0x0199:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = r5.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r13 != r14) goto L_0x01dd
            if (r11 == 0) goto L_0x01a4
            goto L_0x01dd
        L_0x01a4:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = r5.getVerticalDimensionBehaviour()
            if (r4 != r14) goto L_0x0150
            int r4 = r5.mMatchConstraintMaxHeight
            if (r4 < 0) goto L_0x0150
            int r4 = r5.mMatchConstraintMinHeight
            if (r4 < 0) goto L_0x0150
            int r4 = r5.getVisibility()
            if (r4 == r9) goto L_0x01c4
            int r4 = r5.mMatchConstraintDefaultHeight
            if (r4 != 0) goto L_0x0150
            float r4 = r5.getDimensionRatio()
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 != 0) goto L_0x0150
        L_0x01c4:
            boolean r4 = r5.isInVerticalChain()
            if (r4 != 0) goto L_0x0150
            boolean r4 = r5.isInVirtualLayout()
            if (r4 != 0) goto L_0x0150
            if (r12 == 0) goto L_0x0150
            boolean r4 = r5.isInVerticalChain()
            if (r4 != 0) goto L_0x0150
            solveVerticalMatchConstraint(r7, r0, r1, r5)
            goto L_0x0150
        L_0x01dd:
            boolean r11 = r5.isMeasureRequested()
            if (r11 == 0) goto L_0x01e5
            goto L_0x0150
        L_0x01e5:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r5.mTop
            if (r4 != r11) goto L_0x0201
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r5.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 != 0) goto L_0x0201
            int r4 = r11.getMargin()
            int r4 = r4 + r6
            int r11 = r5.getHeight()
            int r11 = r11 + r4
            r5.setFinalVertical(r4, r11)
            verticalSolvingPass(r7, r5, r1)
            goto L_0x0150
        L_0x0201:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r5.mBottom
            if (r4 != r13) goto L_0x021d
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r11.mTarget
            if (r4 != 0) goto L_0x021d
            int r4 = r13.getMargin()
            int r4 = r6 - r4
            int r11 = r5.getHeight()
            int r11 = r4 - r11
            r5.setFinalVertical(r11, r4)
            verticalSolvingPass(r7, r5, r1)
            goto L_0x0150
        L_0x021d:
            if (r12 == 0) goto L_0x0150
            boolean r4 = r5.isInVerticalChain()
            if (r4 != 0) goto L_0x0150
            solveVerticalCenterConstraints(r7, r1, r5)
            goto L_0x0150
        L_0x022a:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.getAnchor(r2)
            java.util.HashSet r4 = r2.getDependents()
            if (r4 == 0) goto L_0x028f
            boolean r4 = r2.hasFinalValue()
            if (r4 == 0) goto L_0x028f
            int r4 = r2.getFinalValue()
            java.util.HashSet r2 = r2.getDependents()
            java.util.Iterator r2 = r2.iterator()
        L_0x0248:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x028f
            java.lang.Object r5 = r2.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r5.mOwner
            int r7 = r16 + 1
            boolean r8 = canMeasure(r7, r6)
            boolean r9 = r6.isMeasureRequested()
            if (r9 == 0) goto L_0x026e
            if (r8 == 0) goto L_0x026e
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r9 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r9.<init>()
            int r10 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r7, r6, r1, r9, r10)
        L_0x026e:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = r6.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 != r10) goto L_0x0278
            if (r8 == 0) goto L_0x0248
        L_0x0278:
            boolean r8 = r6.isMeasureRequested()
            if (r8 == 0) goto L_0x027f
            goto L_0x0248
        L_0x027f:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r6.mBaseline
            if (r5 != r8) goto L_0x0248
            int r5 = r5.getMargin()
            int r5 = r5 + r4
            r6.setFinalBaseline(r5)
            verticalSolvingPass(r7, r6, r1)     // Catch:{ all -> 0x0293 }
            goto L_0x0248
        L_0x028f:
            r17.markVerticalSolvingPassDone()
            return
        L_0x0293:
            r0 = move-exception
            r1 = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Direct.verticalSolvingPass(int, androidx.constraintlayout.core.widgets.ConstraintWidget, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer):void");
    }

    private static void solveHorizontalCenterConstraints(int i, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        float horizontalBiasPercent = constraintWidget.getHorizontalBiasPercent();
        int finalValue = constraintWidget.mLeft.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mRight.mTarget.getFinalValue();
        int margin = constraintWidget.mLeft.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mRight.getMargin();
        if (finalValue == finalValue2) {
            horizontalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int width = constraintWidget.getWidth();
        int i2 = (finalValue2 - finalValue) - width;
        if (finalValue > finalValue2) {
            i2 = (finalValue - finalValue2) - width;
        }
        int i3 = ((int) (i2 > 0 ? (horizontalBiasPercent * ((float) i2)) + 0.5f : horizontalBiasPercent * ((float) i2))) + finalValue;
        int i4 = i3 + width;
        if (finalValue > finalValue2) {
            i4 = i3 - width;
        }
        constraintWidget.setFinalHorizontal(i3, i4);
        horizontalSolvingPass(i + 1, constraintWidget, measurer, z);
    }

    private static void solveVerticalCenterConstraints(int i, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget) {
        float verticalBiasPercent = constraintWidget.getVerticalBiasPercent();
        int finalValue = constraintWidget.mTop.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mBottom.mTarget.getFinalValue();
        int margin = constraintWidget.mTop.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mBottom.getMargin();
        if (finalValue == finalValue2) {
            verticalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int height = constraintWidget.getHeight();
        int i2 = (finalValue2 - finalValue) - height;
        if (finalValue > finalValue2) {
            i2 = (finalValue - finalValue2) - height;
        }
        int i3 = (int) (i2 > 0 ? (verticalBiasPercent * ((float) i2)) + 0.5f : verticalBiasPercent * ((float) i2));
        int i4 = finalValue + i3;
        int i5 = i4 + height;
        if (finalValue > finalValue2) {
            i4 = finalValue - i3;
            i5 = i4 - height;
        }
        constraintWidget.setFinalVertical(i4, i5);
        verticalSolvingPass(i + 1, constraintWidget, measurer);
    }

    private static void solveHorizontalMatchConstraint(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2, boolean z) {
        int i2;
        float horizontalBiasPercent = constraintWidget2.getHorizontalBiasPercent();
        int finalValue = constraintWidget2.mLeft.mTarget.getFinalValue() + constraintWidget2.mLeft.getMargin();
        int finalValue2 = constraintWidget2.mRight.mTarget.getFinalValue() - constraintWidget2.mRight.getMargin();
        if (finalValue2 >= finalValue) {
            int width = constraintWidget2.getWidth();
            if (constraintWidget2.getVisibility() != 8) {
                int i3 = constraintWidget2.mMatchConstraintDefaultWidth;
                if (i3 == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        i2 = constraintWidget.getWidth();
                    } else {
                        i2 = constraintWidget.getParent().getWidth();
                    }
                    width = (int) (constraintWidget2.getHorizontalBiasPercent() * 0.5f * ((float) i2));
                } else if (i3 == 0) {
                    width = finalValue2 - finalValue;
                }
                width = Math.max(constraintWidget2.mMatchConstraintMinWidth, width);
                int i4 = constraintWidget2.mMatchConstraintMaxWidth;
                if (i4 > 0) {
                    width = Math.min(i4, width);
                }
            }
            int i5 = finalValue + ((int) ((horizontalBiasPercent * ((float) ((finalValue2 - finalValue) - width))) + 0.5f));
            constraintWidget2.setFinalHorizontal(i5, width + i5);
            horizontalSolvingPass(i + 1, constraintWidget2, measurer, z);
        }
    }

    private static void solveVerticalMatchConstraint(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2) {
        int i2;
        float verticalBiasPercent = constraintWidget2.getVerticalBiasPercent();
        int finalValue = constraintWidget2.mTop.mTarget.getFinalValue() + constraintWidget2.mTop.getMargin();
        int finalValue2 = constraintWidget2.mBottom.mTarget.getFinalValue() - constraintWidget2.mBottom.getMargin();
        if (finalValue2 >= finalValue) {
            int height = constraintWidget2.getHeight();
            if (constraintWidget2.getVisibility() != 8) {
                int i3 = constraintWidget2.mMatchConstraintDefaultHeight;
                if (i3 == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        i2 = constraintWidget.getHeight();
                    } else {
                        i2 = constraintWidget.getParent().getHeight();
                    }
                    height = (int) (verticalBiasPercent * 0.5f * ((float) i2));
                } else if (i3 == 0) {
                    height = finalValue2 - finalValue;
                }
                height = Math.max(constraintWidget2.mMatchConstraintMinHeight, height);
                int i4 = constraintWidget2.mMatchConstraintMaxHeight;
                if (i4 > 0) {
                    height = Math.min(i4, height);
                }
            }
            int i5 = finalValue + ((int) ((verticalBiasPercent * ((float) ((finalValue2 - finalValue) - height))) + 0.5f));
            constraintWidget2.setFinalVertical(i5, height + i5);
            verticalSolvingPass(i + 1, constraintWidget2, measurer);
        }
    }

    private static boolean canMeasure(int i, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
        ConstraintWidgetContainer constraintWidgetContainer = constraintWidget.getParent() != null ? (ConstraintWidgetContainer) constraintWidget.getParent() : null;
        if (constraintWidgetContainer != null) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidgetContainer.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (constraintWidgetContainer != null) {
            ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour2 = constraintWidgetContainer.getVerticalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        boolean z = horizontalDimensionBehaviour == dimensionBehaviour5 || constraintWidget.isResolvedHorizontally() || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (horizontalDimensionBehaviour == (dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(0)) || (horizontalDimensionBehaviour == dimensionBehaviour2 && constraintWidget.mMatchConstraintDefaultWidth == 1 && constraintWidget.hasResolvedTargets(0, constraintWidget.getWidth()));
        boolean z2 = verticalDimensionBehaviour == dimensionBehaviour5 || constraintWidget.isResolvedVertically() || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (verticalDimensionBehaviour == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(1)) || (horizontalDimensionBehaviour == dimensionBehaviour && constraintWidget.mMatchConstraintDefaultHeight == 1 && constraintWidget.hasResolvedTargets(1, constraintWidget.getHeight()));
        if (constraintWidget.mDimensionRatio > 0.0f && (z || z2)) {
            return true;
        }
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        r7 = r7.mTarget.getFinalValue() + r4.mListAnchors[r23].getMargin();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01d2, code lost:
        if (r6.mOwner == r0) goto L_0x01d6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean solveChain(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r20, androidx.constraintlayout.core.LinearSystem r21, int r22, int r23, androidx.constraintlayout.core.widgets.ChainHead r24, boolean r25, boolean r26, boolean r27) {
        /*
            r0 = 0
            if (r27 == 0) goto L_0x0004
            return r0
        L_0x0004:
            if (r22 != 0) goto L_0x000d
            boolean r1 = r20.isResolvedHorizontally()
            if (r1 != 0) goto L_0x0014
            return r0
        L_0x000d:
            boolean r1 = r20.isResolvedVertically()
            if (r1 != 0) goto L_0x0014
            return r0
        L_0x0014:
            boolean r1 = r20.isRtl()
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r24.getFirst()
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r24.getLast()
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r24.getFirstVisibleWidget()
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r24.getLastVisibleWidget()
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r24.getHead()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r7 = r2.mListAnchors
            r7 = r7[r23]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r3.mListAnchors
            int r8 = r23 + 1
            r3 = r3[r8]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r7.mTarget
            if (r9 == 0) goto L_0x022a
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r3.mTarget
            if (r10 != 0) goto L_0x0040
            goto L_0x022a
        L_0x0040:
            boolean r9 = r9.hasFinalValue()
            if (r9 == 0) goto L_0x022a
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r3.mTarget
            boolean r9 = r9.hasFinalValue()
            if (r9 != 0) goto L_0x0050
            goto L_0x022a
        L_0x0050:
            if (r4 == 0) goto L_0x022a
            if (r5 != 0) goto L_0x0056
            goto L_0x022a
        L_0x0056:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r7.mTarget
            int r7 = r7.getFinalValue()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r9 = r4.mListAnchors
            r9 = r9[r23]
            int r9 = r9.getMargin()
            int r7 = r7 + r9
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            int r3 = r3.getFinalValue()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r9 = r5.mListAnchors
            r9 = r9[r8]
            int r9 = r9.getMargin()
            int r3 = r3 - r9
            int r9 = r3 - r7
            if (r9 > 0) goto L_0x0079
            return r0
        L_0x0079:
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r10 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r10.<init>()
            r13 = r2
            r11 = 0
            r12 = 0
            r14 = 0
            r15 = 0
        L_0x0083:
            r17 = 0
            r0 = 1
            if (r11 != 0) goto L_0x0101
            boolean r18 = canMeasure(r0, r13)
            if (r18 != 0) goto L_0x0091
            r16 = 0
            return r16
        L_0x0091:
            r16 = 0
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r13.mListDimensionBehaviors
            r0 = r0[r22]
            r18 = r2
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r2) goto L_0x009e
            return r16
        L_0x009e:
            boolean r0 = r13.isMeasureRequested()
            if (r0 == 0) goto L_0x00b1
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            int r2 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r19 = r11
            r11 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r11, r13, r0, r10, r2)
            goto L_0x00b3
        L_0x00b1:
            r19 = r11
        L_0x00b3:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r23]
            int r0 = r0.getMargin()
            int r15 = r15 + r0
            if (r22 != 0) goto L_0x00c3
            int r0 = r13.getWidth()
            goto L_0x00c7
        L_0x00c3:
            int r0 = r13.getHeight()
        L_0x00c7:
            int r15 = r15 + r0
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r8]
            int r0 = r0.getMargin()
            int r15 = r15 + r0
            int r14 = r14 + 1
            int r0 = r13.getVisibility()
            r2 = 8
            if (r0 == r2) goto L_0x00dd
            int r12 = r12 + 1
        L_0x00dd:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r8]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x00f6
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r0.mOwner
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r23]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x00f6
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r2.mOwner
            if (r2 == r13) goto L_0x00f4
            goto L_0x00f6
        L_0x00f4:
            r17 = r0
        L_0x00f6:
            if (r17 == 0) goto L_0x00fd
            r13 = r17
            r11 = r19
            goto L_0x00fe
        L_0x00fd:
            r11 = 1
        L_0x00fe:
            r2 = r18
            goto L_0x0083
        L_0x0101:
            r18 = r2
            if (r12 != 0) goto L_0x0107
            r0 = 0
            return r0
        L_0x0107:
            r0 = 0
            if (r12 == r14) goto L_0x010b
            return r0
        L_0x010b:
            if (r9 >= r15) goto L_0x010e
            return r0
        L_0x010e:
            int r9 = r9 - r15
            r0 = 2
            if (r25 == 0) goto L_0x0117
            int r2 = r12 + 1
            int r9 = r9 / r2
        L_0x0115:
            r2 = 1
            goto L_0x011e
        L_0x0117:
            if (r26 == 0) goto L_0x0115
            if (r12 <= r0) goto L_0x0115
            int r9 = r9 / r12
            r2 = 1
            int r9 = r9 - r2
        L_0x011e:
            if (r12 != r2) goto L_0x0150
            if (r22 != 0) goto L_0x0127
            float r0 = r6.getHorizontalBiasPercent()
            goto L_0x012b
        L_0x0127:
            float r0 = r6.getVerticalBiasPercent()
        L_0x012b:
            r2 = 1056964608(0x3f000000, float:0.5)
            float r3 = (float) r7
            float r3 = r3 + r2
            float r2 = (float) r9
            float r2 = r2 * r0
            float r3 = r3 + r2
            int r0 = (int) r3
            if (r22 != 0) goto L_0x013f
            int r2 = r4.getWidth()
            int r2 = r2 + r0
            r4.setFinalHorizontal(r0, r2)
            goto L_0x0147
        L_0x013f:
            int r2 = r4.getHeight()
            int r2 = r2 + r0
            r4.setFinalVertical(r0, r2)
        L_0x0147:
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            r2 = 1
            horizontalSolvingPass(r2, r4, r0, r1)
            return r2
        L_0x0150:
            if (r25 == 0) goto L_0x01de
            int r7 = r7 + r9
            r0 = r18
            r3 = 0
        L_0x0156:
            if (r3 != 0) goto L_0x0229
            int r4 = r0.getVisibility()
            r5 = 8
            if (r4 != r5) goto L_0x017b
            if (r22 != 0) goto L_0x016d
            r0.setFinalHorizontal(r7, r7)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r4 = r20.getMeasurer()
            horizontalSolvingPass(r2, r0, r4, r1)
            goto L_0x0177
        L_0x016d:
            r0.setFinalVertical(r7, r7)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r4 = r20.getMeasurer()
            verticalSolvingPass(r2, r0, r4)
        L_0x0177:
            r2 = r21
            r4 = 0
            goto L_0x01bb
        L_0x017b:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r23]
            int r2 = r2.getMargin()
            int r7 = r7 + r2
            if (r22 != 0) goto L_0x019b
            int r2 = r0.getWidth()
            int r2 = r2 + r7
            r0.setFinalHorizontal(r7, r2)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r2 = r20.getMeasurer()
            r4 = 1
            horizontalSolvingPass(r4, r0, r2, r1)
            int r2 = r0.getWidth()
            goto L_0x01af
        L_0x019b:
            r4 = 1
            int r2 = r0.getHeight()
            int r2 = r2 + r7
            r0.setFinalVertical(r7, r2)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r2 = r20.getMeasurer()
            verticalSolvingPass(r4, r0, r2)
            int r2 = r0.getHeight()
        L_0x01af:
            int r7 = r7 + r2
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r8]
            int r2 = r2.getMargin()
            int r7 = r7 + r2
            int r7 = r7 + r9
            goto L_0x0177
        L_0x01bb:
            r0.addToSolver(r2, r4)
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r0.mListAnchors
            r4 = r4[r8]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x01d4
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r4.mOwner
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r23]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x01d4
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r6.mOwner
            if (r6 == r0) goto L_0x01d6
        L_0x01d4:
            r4 = r17
        L_0x01d6:
            if (r4 == 0) goto L_0x01da
            r0 = r4
            goto L_0x01db
        L_0x01da:
            r3 = 1
        L_0x01db:
            r2 = 1
            goto L_0x0156
        L_0x01de:
            if (r26 == 0) goto L_0x0228
            if (r12 != r0) goto L_0x0226
            if (r22 != 0) goto L_0x0205
            int r0 = r4.getWidth()
            int r0 = r0 + r7
            r4.setFinalHorizontal(r7, r0)
            int r0 = r5.getWidth()
            int r0 = r3 - r0
            r5.setFinalHorizontal(r0, r3)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            r2 = 1
            horizontalSolvingPass(r2, r4, r0, r1)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            horizontalSolvingPass(r2, r5, r0, r1)
            goto L_0x0225
        L_0x0205:
            r2 = 1
            int r0 = r4.getHeight()
            int r0 = r0 + r7
            r4.setFinalVertical(r7, r0)
            int r0 = r5.getHeight()
            int r0 = r3 - r0
            r5.setFinalVertical(r0, r3)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            verticalSolvingPass(r2, r4, r0)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            verticalSolvingPass(r2, r5, r0)
        L_0x0225:
            return r2
        L_0x0226:
            r0 = 0
            return r0
        L_0x0228:
            r2 = 1
        L_0x0229:
            return r2
        L_0x022a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Direct.solveChain(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, int, int, androidx.constraintlayout.core.widgets.ChainHead, boolean, boolean, boolean):boolean");
    }
}
