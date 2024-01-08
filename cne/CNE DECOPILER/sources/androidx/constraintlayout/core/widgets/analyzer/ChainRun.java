package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList widgets = new ArrayList();

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.orientation = i;
        build();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append((WidgetRun) it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!((WidgetRun) this.widgets.get(i)).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public long getWrapDimension() {
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = (WidgetRun) this.widgets.get(i);
            j = j + ((long) widgetRun.start.margin) + widgetRun.getWrapDimension() + ((long) widgetRun.end.margin);
        }
        return j;
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            }
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it.next();
            int i = this.orientation;
            if (i == 0) {
                widgetRun.widget.horizontalChainRun = this;
            } else if (i == 1) {
                widgetRun.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList arrayList = this.widgets;
            this.widget = ((WidgetRun) arrayList.get(arrayList.size() - 1)).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.runGroup = null;
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            ((WidgetRun) it.next()).clear();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r27) {
        /*
            r26 = this;
            r0 = r26
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x0429
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.end
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0010
            goto L_0x0429
        L_0x0010:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r0.widget
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.getParent()
            boolean r2 = r1 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r2 == 0) goto L_0x0021
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1
            boolean r1 = r1.isRtl()
            goto L_0x0022
        L_0x0021:
            r1 = 0
        L_0x0022:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r0.end
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r0.start
            int r4 = r4.value
            int r2 = r2 - r4
            java.util.ArrayList r4 = r0.widgets
            int r4 = r4.size()
            r5 = 0
        L_0x0032:
            r6 = -1
            r7 = 8
            if (r5 >= r4) goto L_0x004a
            java.util.ArrayList r8 = r0.widgets
            java.lang.Object r8 = r8.get(r5)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r8 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r8
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r8.widget
            int r8 = r8.getVisibility()
            if (r8 != r7) goto L_0x004b
            int r5 = r5 + 1
            goto L_0x0032
        L_0x004a:
            r5 = -1
        L_0x004b:
            int r8 = r4 + -1
            r9 = r8
        L_0x004e:
            if (r9 < 0) goto L_0x0064
            java.util.ArrayList r10 = r0.widgets
            java.lang.Object r10 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r10.widget
            int r10 = r10.getVisibility()
            if (r10 != r7) goto L_0x0063
            int r9 = r9 + -1
            goto L_0x004e
        L_0x0063:
            r6 = r9
        L_0x0064:
            r9 = 0
        L_0x0065:
            r11 = 2
            if (r9 >= r11) goto L_0x0109
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
        L_0x006f:
            if (r13 >= r4) goto L_0x00fb
            java.util.ArrayList r3 = r0.widgets
            java.lang.Object r3 = r3.get(r13)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r3 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r3
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r3.widget
            int r11 = r11.getVisibility()
            if (r11 != r7) goto L_0x0083
            goto L_0x00f4
        L_0x0083:
            int r16 = r16 + 1
            if (r13 <= 0) goto L_0x008e
            if (r13 < r5) goto L_0x008e
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r3.start
            int r11 = r11.margin
            int r14 = r14 + r11
        L_0x008e:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r3.dimension
            int r7 = r11.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = r3.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 == r12) goto L_0x009a
            r10 = 1
            goto L_0x009b
        L_0x009a:
            r10 = 0
        L_0x009b:
            if (r10 == 0) goto L_0x00bd
            int r11 = r0.orientation
            if (r11 != 0) goto L_0x00ac
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r3.widget
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r12 = r12.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r12 = r12.dimension
            boolean r12 = r12.resolved
            if (r12 != 0) goto L_0x00ac
            return
        L_0x00ac:
            r12 = 1
            if (r11 != r12) goto L_0x00ba
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r3.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r11 = r11.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r11.dimension
            boolean r11 = r11.resolved
            if (r11 != 0) goto L_0x00ba
            return
        L_0x00ba:
            r19 = r7
            goto L_0x00d3
        L_0x00bd:
            r19 = r7
            r12 = 1
            int r7 = r3.matchConstraintsType
            if (r7 != r12) goto L_0x00cb
            if (r9 != 0) goto L_0x00cb
            int r7 = r11.wrapValue
            int r15 = r15 + 1
            goto L_0x00d1
        L_0x00cb:
            boolean r7 = r11.resolved
            if (r7 == 0) goto L_0x00d3
            r7 = r19
        L_0x00d1:
            r10 = 1
            goto L_0x00d5
        L_0x00d3:
            r7 = r19
        L_0x00d5:
            if (r10 != 0) goto L_0x00e9
            int r15 = r15 + 1
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r3.widget
            float[] r7 = r7.mWeight
            int r10 = r0.orientation
            r7 = r7[r10]
            r10 = 0
            int r11 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r11 < 0) goto L_0x00ea
            float r17 = r17 + r7
            goto L_0x00ea
        L_0x00e9:
            int r14 = r14 + r7
        L_0x00ea:
            if (r13 >= r8) goto L_0x00f4
            if (r13 >= r6) goto L_0x00f4
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.margin
            int r3 = -r3
            int r14 = r14 + r3
        L_0x00f4:
            int r13 = r13 + 1
            r7 = 8
            r11 = 2
            goto L_0x006f
        L_0x00fb:
            if (r14 < r2) goto L_0x0106
            if (r15 != 0) goto L_0x0100
            goto L_0x0106
        L_0x0100:
            int r9 = r9 + 1
            r7 = 8
            goto L_0x0065
        L_0x0106:
            r3 = r16
            goto L_0x010e
        L_0x0109:
            r3 = 0
            r14 = 0
            r15 = 0
            r17 = 0
        L_0x010e:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.start
            int r7 = r7.value
            if (r1 == 0) goto L_0x0118
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.end
            int r7 = r7.value
        L_0x0118:
            r9 = 1056964608(0x3f000000, float:0.5)
            if (r14 <= r2) goto L_0x012f
            r10 = 1073741824(0x40000000, float:2.0)
            if (r1 == 0) goto L_0x0128
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 + r10
            goto L_0x012f
        L_0x0128:
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 - r10
        L_0x012f:
            if (r15 <= 0) goto L_0x0227
            int r10 = r2 - r14
            float r10 = (float) r10
            float r11 = (float) r15
            float r11 = r10 / r11
            float r11 = r11 + r9
            int r11 = (int) r11
            r12 = 0
            r13 = 0
        L_0x013b:
            if (r12 >= r4) goto L_0x01d9
            java.util.ArrayList r9 = r0.widgets
            java.lang.Object r9 = r9.get(r12)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r9
            r19 = r11
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r9.widget
            int r11 = r11.getVisibility()
            r20 = r14
            r14 = 8
            if (r11 != r14) goto L_0x0155
            goto L_0x01bf
        L_0x0155:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r14) goto L_0x01bf
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r9.dimension
            boolean r14 = r11.resolved
            if (r14 != 0) goto L_0x01bf
            r14 = 0
            int r18 = (r17 > r14 ? 1 : (r17 == r14 ? 0 : -1))
            if (r18 <= 0) goto L_0x0179
            androidx.constraintlayout.core.widgets.ConstraintWidget r14 = r9.widget
            float[] r14 = r14.mWeight
            r21 = r7
            int r7 = r0.orientation
            r7 = r14[r7]
            float r7 = r7 * r10
            float r7 = r7 / r17
            r14 = 1056964608(0x3f000000, float:0.5)
            float r7 = r7 + r14
            int r7 = (int) r7
            goto L_0x017d
        L_0x0179:
            r21 = r7
            r7 = r19
        L_0x017d:
            int r14 = r0.orientation
            if (r14 != 0) goto L_0x018c
            androidx.constraintlayout.core.widgets.ConstraintWidget r14 = r9.widget
            r22 = r10
            int r10 = r14.mMatchConstraintMaxWidth
            int r14 = r14.mMatchConstraintMinWidth
            r23 = r1
            goto L_0x019b
        L_0x018c:
            r22 = r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r9.widget
            int r14 = r10.mMatchConstraintMaxHeight
            int r10 = r10.mMatchConstraintMinHeight
            r23 = r1
            r25 = r14
            r14 = r10
            r10 = r25
        L_0x019b:
            int r1 = r9.matchConstraintsType
            r24 = r3
            r3 = 1
            if (r1 != r3) goto L_0x01a9
            int r1 = r11.wrapValue
            int r1 = java.lang.Math.min(r7, r1)
            goto L_0x01aa
        L_0x01a9:
            r1 = r7
        L_0x01aa:
            int r1 = java.lang.Math.max(r14, r1)
            if (r10 <= 0) goto L_0x01b4
            int r1 = java.lang.Math.min(r10, r1)
        L_0x01b4:
            if (r1 == r7) goto L_0x01b9
            int r13 = r13 + 1
            r7 = r1
        L_0x01b9:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r9.dimension
            r1.resolve(r7)
            goto L_0x01c7
        L_0x01bf:
            r23 = r1
            r24 = r3
            r21 = r7
            r22 = r10
        L_0x01c7:
            int r12 = r12 + 1
            r11 = r19
            r14 = r20
            r7 = r21
            r10 = r22
            r1 = r23
            r3 = r24
            r9 = 1056964608(0x3f000000, float:0.5)
            goto L_0x013b
        L_0x01d9:
            r23 = r1
            r24 = r3
            r21 = r7
            r20 = r14
            if (r13 <= 0) goto L_0x0218
            int r15 = r15 - r13
            r1 = 0
            r3 = 0
        L_0x01e6:
            if (r1 >= r4) goto L_0x0216
            java.util.ArrayList r7 = r0.widgets
            java.lang.Object r7 = r7.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r7
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r7.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x01fb
            goto L_0x0213
        L_0x01fb:
            if (r1 <= 0) goto L_0x0204
            if (r1 < r5) goto L_0x0204
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r3 = r3 + r9
        L_0x0204:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r7.dimension
            int r9 = r9.value
            int r3 = r3 + r9
            if (r1 >= r8) goto L_0x0213
            if (r1 >= r6) goto L_0x0213
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r3 = r3 + r7
        L_0x0213:
            int r1 = r1 + 1
            goto L_0x01e6
        L_0x0216:
            r14 = r3
            goto L_0x021a
        L_0x0218:
            r14 = r20
        L_0x021a:
            int r1 = r0.chainStyle
            r3 = 2
            if (r1 != r3) goto L_0x0225
            if (r13 != 0) goto L_0x0225
            r1 = 0
            r0.chainStyle = r1
            goto L_0x0231
        L_0x0225:
            r1 = 0
            goto L_0x0231
        L_0x0227:
            r23 = r1
            r24 = r3
            r21 = r7
            r20 = r14
            r1 = 0
            r3 = 2
        L_0x0231:
            if (r14 <= r2) goto L_0x0235
            r0.chainStyle = r3
        L_0x0235:
            if (r24 <= 0) goto L_0x023d
            if (r15 != 0) goto L_0x023d
            if (r5 != r6) goto L_0x023d
            r0.chainStyle = r3
        L_0x023d:
            int r3 = r0.chainStyle
            r7 = 1
            if (r3 != r7) goto L_0x02e1
            r9 = r24
            if (r9 <= r7) goto L_0x024b
            int r2 = r2 - r14
            int r3 = r9 + -1
            int r2 = r2 / r3
            goto L_0x0252
        L_0x024b:
            if (r9 != r7) goto L_0x0251
            int r2 = r2 - r14
            r3 = 2
            int r2 = r2 / r3
            goto L_0x0252
        L_0x0251:
            r2 = 0
        L_0x0252:
            if (r15 <= 0) goto L_0x0255
            r2 = 0
        L_0x0255:
            r7 = r21
            r3 = 0
        L_0x0258:
            if (r3 >= r4) goto L_0x0429
            if (r23 == 0) goto L_0x0261
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x0262
        L_0x0261:
            r1 = r3
        L_0x0262:
            java.util.ArrayList r9 = r0.widgets
            java.lang.Object r1 = r9.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r1.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x027f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            goto L_0x02dd
        L_0x027f:
            if (r3 <= 0) goto L_0x0286
            if (r23 == 0) goto L_0x0285
            int r7 = r7 - r2
            goto L_0x0286
        L_0x0285:
            int r7 = r7 + r2
        L_0x0286:
            if (r3 <= 0) goto L_0x0297
            if (r3 < r5) goto L_0x0297
            if (r23 == 0) goto L_0x0292
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 - r9
            goto L_0x0297
        L_0x0292:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 + r9
        L_0x0297:
            if (r23 == 0) goto L_0x029f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
            goto L_0x02a4
        L_0x029f:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
        L_0x02a4:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r1.dimension
            int r10 = r9.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x02b5
            int r11 = r1.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x02b5
            int r10 = r9.wrapValue
        L_0x02b5:
            if (r23 == 0) goto L_0x02b9
            int r7 = r7 - r10
            goto L_0x02ba
        L_0x02b9:
            int r7 = r7 + r10
        L_0x02ba:
            if (r23 == 0) goto L_0x02c2
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            goto L_0x02c7
        L_0x02c2:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
        L_0x02c7:
            r9 = 1
            r1.resolved = r9
            if (r3 >= r8) goto L_0x02dd
            if (r3 >= r6) goto L_0x02dd
            if (r23 == 0) goto L_0x02d7
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x02dd
        L_0x02d7:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x02dd:
            int r3 = r3 + 1
            goto L_0x0258
        L_0x02e1:
            r9 = r24
            if (r3 != 0) goto L_0x0378
            int r2 = r2 - r14
            r3 = 1
            int r7 = r9 + 1
            int r2 = r2 / r7
            if (r15 <= 0) goto L_0x02ed
            r2 = 0
        L_0x02ed:
            r7 = r21
            r3 = 0
        L_0x02f0:
            if (r3 >= r4) goto L_0x0429
            if (r23 == 0) goto L_0x02f9
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x02fa
        L_0x02f9:
            r1 = r3
        L_0x02fa:
            java.util.ArrayList r9 = r0.widgets
            java.lang.Object r1 = r9.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r1.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x0317
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            goto L_0x0374
        L_0x0317:
            if (r23 == 0) goto L_0x031b
            int r7 = r7 - r2
            goto L_0x031c
        L_0x031b:
            int r7 = r7 + r2
        L_0x031c:
            if (r3 <= 0) goto L_0x032d
            if (r3 < r5) goto L_0x032d
            if (r23 == 0) goto L_0x0328
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 - r9
            goto L_0x032d
        L_0x0328:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 + r9
        L_0x032d:
            if (r23 == 0) goto L_0x0335
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
            goto L_0x033a
        L_0x0335:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
        L_0x033a:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r1.dimension
            int r10 = r9.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x034f
            int r11 = r1.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x034f
            int r9 = r9.wrapValue
            int r10 = java.lang.Math.min(r10, r9)
        L_0x034f:
            if (r23 == 0) goto L_0x0353
            int r7 = r7 - r10
            goto L_0x0354
        L_0x0353:
            int r7 = r7 + r10
        L_0x0354:
            if (r23 == 0) goto L_0x035c
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            goto L_0x0361
        L_0x035c:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
        L_0x0361:
            if (r3 >= r8) goto L_0x0374
            if (r3 >= r6) goto L_0x0374
            if (r23 == 0) goto L_0x036e
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x0374
        L_0x036e:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x0374:
            int r3 = r3 + 1
            goto L_0x02f0
        L_0x0378:
            r7 = 2
            if (r3 != r7) goto L_0x0429
            int r3 = r0.orientation
            if (r3 != 0) goto L_0x0386
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getHorizontalBiasPercent()
            goto L_0x038c
        L_0x0386:
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getVerticalBiasPercent()
        L_0x038c:
            if (r23 == 0) goto L_0x0392
            r7 = 1065353216(0x3f800000, float:1.0)
            float r3 = r7 - r3
        L_0x0392:
            int r2 = r2 - r14
            float r2 = (float) r2
            float r2 = r2 * r3
            r3 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r3
            int r2 = (int) r2
            if (r2 < 0) goto L_0x039e
            if (r15 <= 0) goto L_0x039f
        L_0x039e:
            r2 = 0
        L_0x039f:
            if (r23 == 0) goto L_0x03a4
            int r7 = r21 - r2
            goto L_0x03a6
        L_0x03a4:
            int r7 = r21 + r2
        L_0x03a6:
            r3 = 0
        L_0x03a7:
            if (r3 >= r4) goto L_0x0429
            if (r23 == 0) goto L_0x03b0
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x03b1
        L_0x03b0:
            r1 = r3
        L_0x03b1:
            java.util.ArrayList r2 = r0.widgets
            java.lang.Object r1 = r2.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r1.widget
            int r2 = r2.getVisibility()
            r9 = 8
            if (r2 != r9) goto L_0x03cf
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            r12 = 1
            goto L_0x0425
        L_0x03cf:
            if (r3 <= 0) goto L_0x03e0
            if (r3 < r5) goto L_0x03e0
            if (r23 == 0) goto L_0x03db
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            int r2 = r2.margin
            int r7 = r7 - r2
            goto L_0x03e0
        L_0x03db:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            int r2 = r2.margin
            int r7 = r7 + r2
        L_0x03e0:
            if (r23 == 0) goto L_0x03e8
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.end
            r2.resolve(r7)
            goto L_0x03ed
        L_0x03e8:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
        L_0x03ed:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r2 = r1.dimension
            int r10 = r2.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x03ff
            int r11 = r1.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x0400
            int r10 = r2.wrapValue
            goto L_0x0400
        L_0x03ff:
            r12 = 1
        L_0x0400:
            if (r23 == 0) goto L_0x0404
            int r7 = r7 - r10
            goto L_0x0405
        L_0x0404:
            int r7 = r7 + r10
        L_0x0405:
            if (r23 == 0) goto L_0x040d
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
            goto L_0x0412
        L_0x040d:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.end
            r2.resolve(r7)
        L_0x0412:
            if (r3 >= r8) goto L_0x0425
            if (r3 >= r6) goto L_0x0425
            if (r23 == 0) goto L_0x041f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x0425
        L_0x041f:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x0425:
            int r3 = r3 + 1
            goto L_0x03a7
        L_0x0429:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }

    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            ((WidgetRun) this.widgets.get(i)).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = (WidgetRun) this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = (WidgetRun) this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void apply() {
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            ((WidgetRun) it.next()).apply();
        }
        int size = this.widgets.size();
        if (size >= 1) {
            ConstraintWidget constraintWidget = ((WidgetRun) this.widgets.get(0)).widget;
            ConstraintWidget constraintWidget2 = ((WidgetRun) this.widgets.get(size - 1)).widget;
            if (this.orientation == 0) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
                ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
                DependencyNode target = getTarget(constraintAnchor, 0);
                int margin = constraintAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    margin = firstVisibleWidget.mLeft.getMargin();
                }
                if (target != null) {
                    addTarget(this.start, target, margin);
                }
                DependencyNode target2 = getTarget(constraintAnchor2, 0);
                int margin2 = constraintAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    margin2 = lastVisibleWidget.mRight.getMargin();
                }
                if (target2 != null) {
                    addTarget(this.end, target2, -margin2);
                }
            } else {
                ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
                ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
                DependencyNode target3 = getTarget(constraintAnchor3, 1);
                int margin3 = constraintAnchor3.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    margin3 = firstVisibleWidget2.mTop.getMargin();
                }
                if (target3 != null) {
                    addTarget(this.start, target3, margin3);
                }
                DependencyNode target4 = getTarget(constraintAnchor4, 1);
                int margin4 = constraintAnchor4.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    margin4 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (target4 != null) {
                    addTarget(this.end, target4, -margin4);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }
}
