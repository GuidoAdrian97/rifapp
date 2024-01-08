package androidx.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.SparseArrayCompat;
import androidx.navigation.NavDeepLink;
import androidx.navigation.common.R$styleable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NavDestination {
    private static final HashMap sClasses = new HashMap();
    private SparseArrayCompat mActions;
    private HashMap mArguments;
    private ArrayList mDeepLinks;
    private int mId;
    private String mIdName;
    private CharSequence mLabel;
    private final String mNavigatorName;
    private NavGraph mParent;

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    public @interface ClassType {
    }

    /* access modifiers changed from: package-private */
    public boolean supportsActions() {
        return true;
    }

    class DeepLinkMatch implements Comparable {
        private final NavDestination mDestination;
        private final boolean mHasMatchingAction;
        private final boolean mIsExactDeepLink;
        private final Bundle mMatchingArgs;
        private final int mMimeTypeMatchLevel;

        DeepLinkMatch(NavDestination navDestination, Bundle bundle, boolean z, boolean z2, int i) {
            this.mDestination = navDestination;
            this.mMatchingArgs = bundle;
            this.mIsExactDeepLink = z;
            this.mHasMatchingAction = z2;
            this.mMimeTypeMatchLevel = i;
        }

        /* access modifiers changed from: package-private */
        public NavDestination getDestination() {
            return this.mDestination;
        }

        /* access modifiers changed from: package-private */
        public Bundle getMatchingArgs() {
            return this.mMatchingArgs;
        }

        public int compareTo(DeepLinkMatch deepLinkMatch) {
            boolean z = this.mIsExactDeepLink;
            if (z && !deepLinkMatch.mIsExactDeepLink) {
                return 1;
            }
            if (!z && deepLinkMatch.mIsExactDeepLink) {
                return -1;
            }
            Bundle bundle = this.mMatchingArgs;
            if (bundle != null && deepLinkMatch.mMatchingArgs == null) {
                return 1;
            }
            if (bundle == null && deepLinkMatch.mMatchingArgs != null) {
                return -1;
            }
            if (bundle != null) {
                int size = bundle.size() - deepLinkMatch.mMatchingArgs.size();
                if (size > 0) {
                    return 1;
                }
                if (size < 0) {
                    return -1;
                }
            }
            boolean z2 = this.mHasMatchingAction;
            if (z2 && !deepLinkMatch.mHasMatchingAction) {
                return 1;
            }
            if (z2 || !deepLinkMatch.mHasMatchingAction) {
                return this.mMimeTypeMatchLevel - deepLinkMatch.mMimeTypeMatchLevel;
            }
            return -1;
        }
    }

    @NonNull
    protected static Class parseClassFromName(@NonNull Context context, @NonNull String str, @NonNull Class cls) {
        if (str.charAt(0) == '.') {
            str = context.getPackageName() + str;
        }
        HashMap hashMap = sClasses;
        Class<?> cls2 = (Class) hashMap.get(str);
        if (cls2 == null) {
            try {
                cls2 = Class.forName(str, true, context.getClassLoader());
                hashMap.put(str, cls2);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
        if (cls.isAssignableFrom(cls2)) {
            return cls2;
        }
        throw new IllegalArgumentException(str + " must be a subclass of " + cls);
    }

    @NonNull
    @RestrictTo
    static String getDisplayName(@NonNull Context context, int i) {
        if (i <= 16777215) {
            return Integer.toString(i);
        }
        try {
            return context.getResources().getResourceName(i);
        } catch (Resources.NotFoundException unused) {
            return Integer.toString(i);
        }
    }

    @NonNull
    public final Map getArguments() {
        HashMap hashMap = this.mArguments;
        if (hashMap == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public NavDestination(@NonNull Navigator navigator) {
        this(NavigatorProvider.getNameForNavigator(navigator.getClass()));
    }

    public NavDestination(@NonNull String str) {
        this.mNavigatorName = str;
    }

    @CallSuper
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R$styleable.Navigator);
        setId(obtainAttributes.getResourceId(R$styleable.Navigator_android_id, 0));
        this.mIdName = getDisplayName(context, this.mId);
        setLabel(obtainAttributes.getText(R$styleable.Navigator_android_label));
        obtainAttributes.recycle();
    }

    /* access modifiers changed from: package-private */
    public final void setParent(NavGraph navGraph) {
        this.mParent = navGraph;
    }

    @Nullable
    public final NavGraph getParent() {
        return this.mParent;
    }

    @IdRes
    public final int getId() {
        return this.mId;
    }

    public final void setId(@IdRes int i) {
        this.mId = i;
        this.mIdName = null;
    }

    @NonNull
    @RestrictTo
    public String getDisplayName() {
        if (this.mIdName == null) {
            this.mIdName = Integer.toString(this.mId);
        }
        return this.mIdName;
    }

    public final void setLabel(@Nullable CharSequence charSequence) {
        this.mLabel = charSequence;
    }

    @Nullable
    public final CharSequence getLabel() {
        return this.mLabel;
    }

    @NonNull
    public final String getNavigatorName() {
        return this.mNavigatorName;
    }

    public boolean hasDeepLink(@NonNull Uri uri) {
        return hasDeepLink(new NavDeepLinkRequest(uri, (String) null, (String) null));
    }

    public boolean hasDeepLink(@NonNull NavDeepLinkRequest navDeepLinkRequest) {
        return matchDeepLink(navDeepLinkRequest) != null;
    }

    public final void addDeepLink(@NonNull String str) {
        addDeepLink(new NavDeepLink.Builder().setUriPattern(str).build());
    }

    public final void addDeepLink(@NonNull NavDeepLink navDeepLink) {
        if (this.mDeepLinks == null) {
            this.mDeepLinks = new ArrayList();
        }
        this.mDeepLinks.add(navDeepLink);
    }

    /* access modifiers changed from: package-private */
    public DeepLinkMatch matchDeepLink(NavDeepLinkRequest navDeepLinkRequest) {
        ArrayList arrayList = this.mDeepLinks;
        if (arrayList == null) {
            return null;
        }
        Iterator it = arrayList.iterator();
        DeepLinkMatch deepLinkMatch = null;
        while (it.hasNext()) {
            NavDeepLink navDeepLink = (NavDeepLink) it.next();
            Uri uri = navDeepLinkRequest.getUri();
            Bundle matchingArguments = uri != null ? navDeepLink.getMatchingArguments(uri, getArguments()) : null;
            String action = navDeepLinkRequest.getAction();
            boolean z = action != null && action.equals(navDeepLink.getAction());
            String mimeType = navDeepLinkRequest.getMimeType();
            int mimeTypeMatchRating = mimeType != null ? navDeepLink.getMimeTypeMatchRating(mimeType) : -1;
            if (matchingArguments != null || z || mimeTypeMatchRating > -1) {
                DeepLinkMatch deepLinkMatch2 = new DeepLinkMatch(this, matchingArguments, navDeepLink.isExactDeepLink(), z, mimeTypeMatchRating);
                if (deepLinkMatch == null || deepLinkMatch2.compareTo(deepLinkMatch) > 0) {
                    deepLinkMatch = deepLinkMatch2;
                }
            }
        }
        return deepLinkMatch;
    }

    /* access modifiers changed from: package-private */
    public int[] buildDeepLinkIds() {
        ArrayDeque arrayDeque = new ArrayDeque();
        NavGraph navGraph = this;
        while (true) {
            NavGraph parent = navGraph.getParent();
            if (parent == null || parent.getStartDestination() != navGraph.getId()) {
                arrayDeque.addFirst(navGraph);
            }
            if (parent == null) {
                break;
            }
            navGraph = parent;
        }
        int[] iArr = new int[arrayDeque.size()];
        int i = 0;
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            iArr[i] = ((NavDestination) it.next()).getId();
            i++;
        }
        return iArr;
    }

    @Nullable
    public final NavAction getAction(@IdRes int i) {
        SparseArrayCompat sparseArrayCompat = this.mActions;
        NavAction navAction = sparseArrayCompat == null ? null : (NavAction) sparseArrayCompat.get(i);
        if (navAction != null) {
            return navAction;
        }
        if (getParent() != null) {
            return getParent().getAction(i);
        }
        return null;
    }

    public final void putAction(@IdRes int i, @IdRes int i2) {
        putAction(i, new NavAction(i2));
    }

    public final void putAction(@IdRes int i, @NonNull NavAction navAction) {
        if (!supportsActions()) {
            throw new UnsupportedOperationException("Cannot add action " + i + " to " + this + " as it does not support actions, indicating that it is a terminal destination in your navigation graph and will never trigger actions.");
        } else if (i != 0) {
            if (this.mActions == null) {
                this.mActions = new SparseArrayCompat();
            }
            this.mActions.put(i, navAction);
        } else {
            throw new IllegalArgumentException("Cannot have an action with actionId 0");
        }
    }

    public final void removeAction(@IdRes int i) {
        SparseArrayCompat sparseArrayCompat = this.mActions;
        if (sparseArrayCompat != null) {
            sparseArrayCompat.remove(i);
        }
    }

    public final void addArgument(@NonNull String str, @NonNull NavArgument navArgument) {
        if (this.mArguments == null) {
            this.mArguments = new HashMap();
        }
        this.mArguments.put(str, navArgument);
    }

    public final void removeArgument(@NonNull String str) {
        HashMap hashMap = this.mArguments;
        if (hashMap != null) {
            hashMap.remove(str);
        }
    }

    /* access modifiers changed from: package-private */
    public Bundle addInDefaultArgs(Bundle bundle) {
        HashMap hashMap;
        if (bundle == null && ((hashMap = this.mArguments) == null || hashMap.isEmpty())) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        HashMap hashMap2 = this.mArguments;
        if (hashMap2 != null) {
            for (Map.Entry entry : hashMap2.entrySet()) {
                ((NavArgument) entry.getValue()).putDefaultValue((String) entry.getKey(), bundle2);
            }
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
            HashMap hashMap3 = this.mArguments;
            if (hashMap3 != null) {
                for (Map.Entry entry2 : hashMap3.entrySet()) {
                    if (!((NavArgument) entry2.getValue()).verify((String) entry2.getKey(), bundle2)) {
                        throw new IllegalArgumentException("Wrong argument type for '" + ((String) entry2.getKey()) + "' in argument bundle. " + ((NavArgument) entry2.getValue()).getType().getName() + " expected.");
                    }
                }
            }
        }
        return bundle2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        String str = this.mIdName;
        if (str == null) {
            sb.append("0x");
            sb.append(Integer.toHexString(this.mId));
        } else {
            sb.append(str);
        }
        sb.append(")");
        if (this.mLabel != null) {
            sb.append(" label=");
            sb.append(this.mLabel);
        }
        return sb.toString();
    }
}
