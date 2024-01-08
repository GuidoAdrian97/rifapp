package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimationUtils;

public class HideBottomViewOnScrollBehavior extends CoordinatorLayout.Behavior {
    protected static final int ENTER_ANIMATION_DURATION = 225;
    protected static final int EXIT_ANIMATION_DURATION = 175;
    private static final int STATE_SCROLLED_DOWN = 1;
    private static final int STATE_SCROLLED_UP = 2;
    private int additionalHiddenOffsetY = 0;
    /* access modifiers changed from: private */
    @Nullable
    public ViewPropertyAnimator currentAnimator;
    private int currentState = 2;
    private int height = 0;

    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull View view2, @NonNull View view3, int i, int i2) {
        return i == 2;
    }

    public HideBottomViewOnScrollBehavior() {
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, int i) {
        this.height = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        return super.onLayoutChild(coordinatorLayout, view, i);
    }

    public void setAdditionalHiddenOffsetY(@NonNull View view, @Dimension int i) {
        this.additionalHiddenOffsetY = i;
        if (this.currentState == 1) {
            view.setTranslationY((float) (this.height + i));
        }
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull View view2, int i, int i2, int i3, int i4, int i5, @NonNull int[] iArr) {
        if (i2 > 0) {
            slideDown(view);
        } else if (i2 < 0) {
            slideUp(view);
        }
    }

    public void slideUp(@NonNull View view) {
        if (this.currentState != 2) {
            ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                view.clearAnimation();
            }
            this.currentState = 2;
            animateChildTo(view, 0, 225, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        }
    }

    public void slideDown(@NonNull View view) {
        if (this.currentState != 1) {
            ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                view.clearAnimation();
            }
            this.currentState = 1;
            animateChildTo(view, this.height + this.additionalHiddenOffsetY, 175, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        }
    }

    private void animateChildTo(@NonNull View view, int i, long j, TimeInterpolator timeInterpolator) {
        this.currentAnimator = view.animate().translationY((float) i).setInterpolator(timeInterpolator).setDuration(j).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ViewPropertyAnimator unused = HideBottomViewOnScrollBehavior.this.currentAnimator = null;
            }
        });
    }
}
