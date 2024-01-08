package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.motion.widget.ViewTransition;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.SharedValues;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ViewTransitionController {
    private String TAG = "ViewTransitionController";
    ArrayList animations;
    /* access modifiers changed from: private */
    public final MotionLayout mMotionLayout;
    private HashSet mRelatedViews;
    ArrayList removeList = new ArrayList();
    private ArrayList viewTransitions = new ArrayList();

    public ViewTransitionController(MotionLayout motionLayout) {
        this.mMotionLayout = motionLayout;
    }

    public void add(ViewTransition viewTransition) {
        this.viewTransitions.add(viewTransition);
        this.mRelatedViews = null;
        if (viewTransition.getStateTransition() == 4) {
            listenForSharedVariable(viewTransition, true);
        } else if (viewTransition.getStateTransition() == 5) {
            listenForSharedVariable(viewTransition, false);
        }
    }

    private void viewTransition(ViewTransition viewTransition, View... viewArr) {
        int currentState = this.mMotionLayout.getCurrentState();
        if (viewTransition.mViewTransitionMode == 2) {
            viewTransition.applyTransition(this, this.mMotionLayout, currentState, (ConstraintSet) null, viewArr);
        } else if (currentState == -1) {
            String str = this.TAG;
            Log.w(str, "No support for ViewTransition within transition yet. Currently: " + this.mMotionLayout.toString());
        } else {
            ConstraintSet constraintSet = this.mMotionLayout.getConstraintSet(currentState);
            if (constraintSet != null) {
                viewTransition.applyTransition(this, this.mMotionLayout, currentState, constraintSet, viewArr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void enableViewTransition(int i, boolean z) {
        Iterator it = this.viewTransitions.iterator();
        while (it.hasNext()) {
            ViewTransition viewTransition = (ViewTransition) it.next();
            if (viewTransition.getId() == i) {
                viewTransition.setEnabled(z);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isViewTransitionEnabled(int i) {
        Iterator it = this.viewTransitions.iterator();
        while (it.hasNext()) {
            ViewTransition viewTransition = (ViewTransition) it.next();
            if (viewTransition.getId() == i) {
                return viewTransition.isEnabled();
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void viewTransition(int i, View... viewArr) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.viewTransitions.iterator();
        ViewTransition viewTransition = null;
        while (it.hasNext()) {
            ViewTransition viewTransition2 = (ViewTransition) it.next();
            if (viewTransition2.getId() == i) {
                for (View view : viewArr) {
                    if (viewTransition2.checkTags(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    viewTransition(viewTransition2, (View[]) arrayList.toArray(new View[0]));
                    arrayList.clear();
                }
                viewTransition = viewTransition2;
            }
        }
        if (viewTransition == null) {
            Log.e(this.TAG, " Could not find ViewTransition");
        }
    }

    /* access modifiers changed from: package-private */
    public void touchEvent(MotionEvent motionEvent) {
        ViewTransition viewTransition;
        int currentState = this.mMotionLayout.getCurrentState();
        if (currentState != -1) {
            if (this.mRelatedViews == null) {
                this.mRelatedViews = new HashSet();
                Iterator it = this.viewTransitions.iterator();
                while (it.hasNext()) {
                    ViewTransition viewTransition2 = (ViewTransition) it.next();
                    int childCount = this.mMotionLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = this.mMotionLayout.getChildAt(i);
                        if (viewTransition2.matchesView(childAt)) {
                            childAt.getId();
                            this.mRelatedViews.add(childAt);
                        }
                    }
                }
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            Rect rect = new Rect();
            int action = motionEvent.getAction();
            ArrayList arrayList = this.animations;
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator it2 = this.animations.iterator();
                while (it2.hasNext()) {
                    ((ViewTransition.Animate) it2.next()).reactTo(action, x, y);
                }
            }
            if (action == 0 || action == 1) {
                ConstraintSet constraintSet = this.mMotionLayout.getConstraintSet(currentState);
                Iterator it3 = this.viewTransitions.iterator();
                while (it3.hasNext()) {
                    ViewTransition viewTransition3 = (ViewTransition) it3.next();
                    if (viewTransition3.supports(action)) {
                        Iterator it4 = this.mRelatedViews.iterator();
                        while (it4.hasNext()) {
                            View view = (View) it4.next();
                            if (viewTransition3.matchesView(view)) {
                                view.getHitRect(rect);
                                if (rect.contains((int) x, (int) y)) {
                                    viewTransition = viewTransition3;
                                    viewTransition3.applyTransition(this, this.mMotionLayout, currentState, constraintSet, view);
                                } else {
                                    viewTransition = viewTransition3;
                                }
                                viewTransition3 = viewTransition;
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addAnimation(ViewTransition.Animate animate) {
        if (this.animations == null) {
            this.animations = new ArrayList();
        }
        this.animations.add(animate);
    }

    /* access modifiers changed from: package-private */
    public void removeAnimation(ViewTransition.Animate animate) {
        this.removeList.add(animate);
    }

    /* access modifiers changed from: package-private */
    public void animate() {
        ArrayList arrayList = this.animations;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((ViewTransition.Animate) it.next()).mutate();
            }
            this.animations.removeAll(this.removeList);
            this.removeList.clear();
            if (this.animations.isEmpty()) {
                this.animations = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void invalidate() {
        this.mMotionLayout.invalidate();
    }

    /* access modifiers changed from: package-private */
    public boolean applyViewTransition(int i, MotionController motionController) {
        Iterator it = this.viewTransitions.iterator();
        while (it.hasNext()) {
            ViewTransition viewTransition = (ViewTransition) it.next();
            if (viewTransition.getId() == i) {
                viewTransition.mKeyFrames.addAllFrames(motionController);
                return true;
            }
        }
        return false;
    }

    private void listenForSharedVariable(ViewTransition viewTransition, boolean z) {
        final int sharedValueID = viewTransition.getSharedValueID();
        final int sharedValue = viewTransition.getSharedValue();
        final ViewTransition viewTransition2 = viewTransition;
        final boolean z2 = z;
        ConstraintLayout.getSharedValues().addListener(viewTransition.getSharedValueID(), new SharedValues.SharedValuesListener() {
            public void onNewValue(int i, int i2, int i3) {
                int sharedValueCurrent = viewTransition2.getSharedValueCurrent();
                viewTransition2.setSharedValueCurrent(i2);
                if (sharedValueID == i && sharedValueCurrent != i2) {
                    if (z2) {
                        if (sharedValue == i2) {
                            int childCount = ViewTransitionController.this.mMotionLayout.getChildCount();
                            for (int i4 = 0; i4 < childCount; i4++) {
                                View childAt = ViewTransitionController.this.mMotionLayout.getChildAt(i4);
                                if (viewTransition2.matchesView(childAt)) {
                                    int currentState = ViewTransitionController.this.mMotionLayout.getCurrentState();
                                    ConstraintSet constraintSet = ViewTransitionController.this.mMotionLayout.getConstraintSet(currentState);
                                    ViewTransition viewTransition = viewTransition2;
                                    ViewTransitionController viewTransitionController = ViewTransitionController.this;
                                    viewTransition.applyTransition(viewTransitionController, viewTransitionController.mMotionLayout, currentState, constraintSet, childAt);
                                }
                            }
                        }
                    } else if (sharedValue != i2) {
                        int childCount2 = ViewTransitionController.this.mMotionLayout.getChildCount();
                        for (int i5 = 0; i5 < childCount2; i5++) {
                            View childAt2 = ViewTransitionController.this.mMotionLayout.getChildAt(i5);
                            if (viewTransition2.matchesView(childAt2)) {
                                int currentState2 = ViewTransitionController.this.mMotionLayout.getCurrentState();
                                ConstraintSet constraintSet2 = ViewTransitionController.this.mMotionLayout.getConstraintSet(currentState2);
                                ViewTransition viewTransition2 = viewTransition2;
                                ViewTransitionController viewTransitionController2 = ViewTransitionController.this;
                                viewTransition2.applyTransition(viewTransitionController2, viewTransitionController2.mMotionLayout, currentState2, constraintSet2, childAt2);
                            }
                        }
                    }
                }
            }
        });
    }
}
