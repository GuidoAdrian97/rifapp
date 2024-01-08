package androidx.navigation;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

public final class NavOptions {
    private int mEnterAnim;
    private int mExitAnim;
    private int mPopEnterAnim;
    private int mPopExitAnim;
    private int mPopUpTo;
    private boolean mPopUpToInclusive;
    private boolean mSingleTop;

    NavOptions(boolean z, int i, boolean z2, int i2, int i3, int i4, int i5) {
        this.mSingleTop = z;
        this.mPopUpTo = i;
        this.mPopUpToInclusive = z2;
        this.mEnterAnim = i2;
        this.mExitAnim = i3;
        this.mPopEnterAnim = i4;
        this.mPopExitAnim = i5;
    }

    public boolean shouldLaunchSingleTop() {
        return this.mSingleTop;
    }

    @IdRes
    public int getPopUpTo() {
        return this.mPopUpTo;
    }

    public boolean isPopUpToInclusive() {
        return this.mPopUpToInclusive;
    }

    @AnimRes
    @AnimatorRes
    public int getEnterAnim() {
        return this.mEnterAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getExitAnim() {
        return this.mExitAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getPopEnterAnim() {
        return this.mPopEnterAnim;
    }

    @AnimRes
    @AnimatorRes
    public int getPopExitAnim() {
        return this.mPopExitAnim;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NavOptions.class != obj.getClass()) {
            return false;
        }
        NavOptions navOptions = (NavOptions) obj;
        if (this.mSingleTop == navOptions.mSingleTop && this.mPopUpTo == navOptions.mPopUpTo && this.mPopUpToInclusive == navOptions.mPopUpToInclusive && this.mEnterAnim == navOptions.mEnterAnim && this.mExitAnim == navOptions.mExitAnim && this.mPopEnterAnim == navOptions.mPopEnterAnim && this.mPopExitAnim == navOptions.mPopExitAnim) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((shouldLaunchSingleTop() ? 1 : 0) * true) + getPopUpTo()) * 31) + (isPopUpToInclusive() ? 1 : 0)) * 31) + getEnterAnim()) * 31) + getExitAnim()) * 31) + getPopEnterAnim()) * 31) + getPopExitAnim();
    }

    public final class Builder {
        int mEnterAnim = -1;
        int mExitAnim = -1;
        int mPopEnterAnim = -1;
        int mPopExitAnim = -1;
        int mPopUpTo = -1;
        boolean mPopUpToInclusive;
        boolean mSingleTop;

        @NonNull
        public Builder setLaunchSingleTop(boolean z) {
            this.mSingleTop = z;
            return this;
        }

        @NonNull
        public Builder setPopUpTo(@IdRes int i, boolean z) {
            this.mPopUpTo = i;
            this.mPopUpToInclusive = z;
            return this;
        }

        @NonNull
        public Builder setEnterAnim(@AnimRes @AnimatorRes int i) {
            this.mEnterAnim = i;
            return this;
        }

        @NonNull
        public Builder setExitAnim(@AnimRes @AnimatorRes int i) {
            this.mExitAnim = i;
            return this;
        }

        @NonNull
        public Builder setPopEnterAnim(@AnimRes @AnimatorRes int i) {
            this.mPopEnterAnim = i;
            return this;
        }

        @NonNull
        public Builder setPopExitAnim(@AnimRes @AnimatorRes int i) {
            this.mPopExitAnim = i;
            return this;
        }

        @NonNull
        public NavOptions build() {
            return new NavOptions(this.mSingleTop, this.mPopUpTo, this.mPopUpToInclusive, this.mEnterAnim, this.mExitAnim, this.mPopEnterAnim, this.mPopExitAnim);
        }
    }
}
