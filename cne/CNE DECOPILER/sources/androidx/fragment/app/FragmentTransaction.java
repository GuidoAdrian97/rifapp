package androidx.fragment.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public abstract class FragmentTransaction {
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    private final ClassLoader mClassLoader;
    ArrayList mCommitRunnables;
    int mEnterAnim;
    int mExitAnim;
    private final FragmentFactory mFragmentFactory;
    String mName;
    ArrayList mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    @NonNull
    @Deprecated
    public FragmentTransaction setTransitionStyle(@StyleRes int i) {
        return this;
    }

    final class Op {
        int mCmd;
        Lifecycle.State mCurrentMaxState;
        int mEnterAnim;
        int mExitAnim;
        Fragment mFragment;
        Lifecycle.State mOldMaxState;
        int mPopEnterAnim;
        int mPopExitAnim;

        Op() {
        }

        Op(int i, Fragment fragment) {
            this.mCmd = i;
            this.mFragment = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.mOldMaxState = state;
            this.mCurrentMaxState = state;
        }

        Op(int i, Fragment fragment, Lifecycle.State state) {
            this.mCmd = i;
            this.mFragment = fragment;
            this.mOldMaxState = fragment.mMaxState;
            this.mCurrentMaxState = state;
        }
    }

    @Deprecated
    public FragmentTransaction() {
        this.mOps = new ArrayList();
        this.mAllowAddToBackStack = true;
        this.mReorderingAllowed = false;
        this.mFragmentFactory = null;
        this.mClassLoader = null;
    }

    FragmentTransaction(@NonNull FragmentFactory fragmentFactory, @Nullable ClassLoader classLoader) {
        this.mOps = new ArrayList();
        this.mAllowAddToBackStack = true;
        this.mReorderingAllowed = false;
        this.mFragmentFactory = fragmentFactory;
        this.mClassLoader = classLoader;
    }

    /* access modifiers changed from: package-private */
    public void addOp(Op op) {
        this.mOps.add(op);
        op.mEnterAnim = this.mEnterAnim;
        op.mExitAnim = this.mExitAnim;
        op.mPopEnterAnim = this.mPopEnterAnim;
        op.mPopExitAnim = this.mPopExitAnim;
    }

    private Fragment createFragment(Class cls, Bundle bundle) {
        FragmentFactory fragmentFactory = this.mFragmentFactory;
        if (fragmentFactory != null) {
            ClassLoader classLoader = this.mClassLoader;
            if (classLoader != null) {
                Fragment instantiate = fragmentFactory.instantiate(classLoader, cls.getName());
                if (bundle != null) {
                    instantiate.setArguments(bundle);
                }
                return instantiate;
            }
            throw new IllegalStateException("The FragmentManager must be attached to itshost to create a Fragment");
        }
        throw new IllegalStateException("Creating a Fragment requires that this FragmentTransaction was built with FragmentManager.beginTransaction()");
    }

    @NonNull
    public final FragmentTransaction add(@NonNull Class cls, @Nullable Bundle bundle, @Nullable String str) {
        return add(createFragment(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction add(@NonNull Fragment fragment, @Nullable String str) {
        doAddOp(0, fragment, str, 1);
        return this;
    }

    @NonNull
    public final FragmentTransaction add(@IdRes int i, @NonNull Class cls, @Nullable Bundle bundle) {
        return add(i, createFragment(cls, bundle));
    }

    @NonNull
    public FragmentTransaction add(@IdRes int i, @NonNull Fragment fragment) {
        doAddOp(i, fragment, (String) null, 1);
        return this;
    }

    @NonNull
    public final FragmentTransaction add(@IdRes int i, @NonNull Class cls, @Nullable Bundle bundle, @Nullable String str) {
        return add(i, createFragment(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction add(@IdRes int i, @NonNull Fragment fragment, @Nullable String str) {
        doAddOp(i, fragment, str, 1);
        return this;
    }

    /* access modifiers changed from: package-private */
    public FragmentTransaction add(@NonNull ViewGroup viewGroup, @NonNull Fragment fragment, @Nullable String str) {
        fragment.mContainer = viewGroup;
        return add(viewGroup.getId(), fragment, str);
    }

    /* access modifiers changed from: package-private */
    public void doAddOp(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.mTag;
            if (str2 == null || str.equals(str2)) {
                fragment.mTag = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
        }
        if (i != 0) {
            if (i != -1) {
                int i3 = fragment.mFragmentId;
                if (i3 == 0 || i3 == i) {
                    fragment.mFragmentId = i;
                    fragment.mContainerId = i;
                } else {
                    throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
                }
            } else {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
        }
        addOp(new Op(i2, fragment));
    }

    @NonNull
    public final FragmentTransaction replace(@IdRes int i, @NonNull Class cls, @Nullable Bundle bundle) {
        return replace(i, cls, bundle, (String) null);
    }

    @NonNull
    public FragmentTransaction replace(@IdRes int i, @NonNull Fragment fragment) {
        return replace(i, fragment, (String) null);
    }

    @NonNull
    public final FragmentTransaction replace(@IdRes int i, @NonNull Class cls, @Nullable Bundle bundle, @Nullable String str) {
        return replace(i, createFragment(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction replace(@IdRes int i, @NonNull Fragment fragment, @Nullable String str) {
        if (i != 0) {
            doAddOp(i, fragment, str, 2);
            return this;
        }
        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    @NonNull
    public FragmentTransaction remove(@NonNull Fragment fragment) {
        addOp(new Op(3, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction hide(@NonNull Fragment fragment) {
        addOp(new Op(4, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction show(@NonNull Fragment fragment) {
        addOp(new Op(5, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction detach(@NonNull Fragment fragment) {
        addOp(new Op(6, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction attach(@NonNull Fragment fragment) {
        addOp(new Op(7, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        addOp(new Op(8, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction setMaxLifecycle(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        addOp(new Op(10, fragment, state));
        return this;
    }

    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    @NonNull
    public FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2) {
        return setCustomAnimations(i, i2, 0, 0);
    }

    @NonNull
    public FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int i, @AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4) {
        this.mEnterAnim = i;
        this.mExitAnim = i2;
        this.mPopEnterAnim = i3;
        this.mPopExitAnim = i4;
        return this;
    }

    @NonNull
    public FragmentTransaction addSharedElement(@NonNull View view, @NonNull String str) {
        if (FragmentTransition.supportsTransition()) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName != null) {
                if (this.mSharedElementSourceNames == null) {
                    this.mSharedElementSourceNames = new ArrayList();
                    this.mSharedElementTargetNames = new ArrayList();
                } else if (this.mSharedElementTargetNames.contains(str)) {
                    throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
                } else if (this.mSharedElementSourceNames.contains(transitionName)) {
                    throw new IllegalArgumentException("A shared element with the source name '" + transitionName + "' has already been added to the transaction.");
                }
                this.mSharedElementSourceNames.add(transitionName);
                this.mSharedElementTargetNames.add(str);
            } else {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
        }
        return this;
    }

    @NonNull
    public FragmentTransaction setTransition(int i) {
        this.mTransition = i;
        return this;
    }

    @NonNull
    public FragmentTransaction addToBackStack(@Nullable String str) {
        if (this.mAllowAddToBackStack) {
            this.mAddToBackStack = true;
            this.mName = str;
            return this;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }

    @NonNull
    public FragmentTransaction disallowAddToBackStack() {
        if (!this.mAddToBackStack) {
            this.mAllowAddToBackStack = false;
            return this;
        }
        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(@StringRes int i) {
        this.mBreadCrumbTitleRes = i;
        this.mBreadCrumbTitleText = null;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence charSequence) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = charSequence;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(@StringRes int i) {
        this.mBreadCrumbShortTitleRes = i;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence charSequence) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = charSequence;
        return this;
    }

    @NonNull
    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.mReorderingAllowed = z;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setAllowOptimization(boolean z) {
        return setReorderingAllowed(z);
    }

    @NonNull
    public FragmentTransaction runOnCommit(@NonNull Runnable runnable) {
        disallowAddToBackStack();
        if (this.mCommitRunnables == null) {
            this.mCommitRunnables = new ArrayList();
        }
        this.mCommitRunnables.add(runnable);
        return this;
    }
}
