package androidx.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.navigation.NavDestination;
import androidx.navigation.common.R$styleable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NavGraph extends NavDestination implements Iterable {
    final SparseArrayCompat mNodes = new SparseArrayCompat();
    private int mStartDestId;
    private String mStartDestIdName;

    public NavGraph(@NonNull Navigator navigator) {
        super(navigator);
    }

    public void onInflate(Context context, AttributeSet attributeSet) {
        super.onInflate(context, attributeSet);
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R$styleable.NavGraphNavigator);
        setStartDestination(obtainAttributes.getResourceId(R$styleable.NavGraphNavigator_startDestination, 0));
        this.mStartDestIdName = NavDestination.getDisplayName(context, this.mStartDestId);
        obtainAttributes.recycle();
    }

    /* access modifiers changed from: package-private */
    public NavDestination.DeepLinkMatch matchDeepLink(NavDeepLinkRequest navDeepLinkRequest) {
        NavDestination.DeepLinkMatch matchDeepLink = super.matchDeepLink(navDeepLinkRequest);
        Iterator it = iterator();
        while (it.hasNext()) {
            NavDestination.DeepLinkMatch matchDeepLink2 = ((NavDestination) it.next()).matchDeepLink(navDeepLinkRequest);
            if (matchDeepLink2 != null && (matchDeepLink == null || matchDeepLink2.compareTo(matchDeepLink) > 0)) {
                matchDeepLink = matchDeepLink2;
            }
        }
        return matchDeepLink;
    }

    public final void addDestination(@NonNull NavDestination navDestination) {
        int id = navDestination.getId();
        if (id == 0) {
            throw new IllegalArgumentException("Destinations must have an id. Call setId() or include an android:id in your navigation XML.");
        } else if (id != getId()) {
            NavDestination navDestination2 = (NavDestination) this.mNodes.get(id);
            if (navDestination2 != navDestination) {
                if (navDestination.getParent() == null) {
                    if (navDestination2 != null) {
                        navDestination2.setParent((NavGraph) null);
                    }
                    navDestination.setParent(this);
                    this.mNodes.put(navDestination.getId(), navDestination);
                    return;
                }
                throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.");
            }
        } else {
            throw new IllegalArgumentException("Destination " + navDestination + " cannot have the same id as graph " + this);
        }
    }

    public final void addDestinations(@NonNull Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            NavDestination navDestination = (NavDestination) it.next();
            if (navDestination != null) {
                addDestination(navDestination);
            }
        }
    }

    public final void addDestinations(@NonNull NavDestination... navDestinationArr) {
        for (NavDestination navDestination : navDestinationArr) {
            if (navDestination != null) {
                addDestination(navDestination);
            }
        }
    }

    @Nullable
    public final NavDestination findNode(@IdRes int i) {
        return findNode(i, true);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final NavDestination findNode(@IdRes int i, boolean z) {
        NavDestination navDestination = (NavDestination) this.mNodes.get(i);
        if (navDestination != null) {
            return navDestination;
        }
        if (!z || getParent() == null) {
            return null;
        }
        return getParent().findNode(i);
    }

    @NonNull
    public final Iterator iterator() {
        return new Iterator() {
            private int mIndex = -1;
            private boolean mWentToNext = false;

            public boolean hasNext() {
                return this.mIndex + 1 < NavGraph.this.mNodes.size();
            }

            public NavDestination next() {
                if (hasNext()) {
                    this.mWentToNext = true;
                    SparseArrayCompat sparseArrayCompat = NavGraph.this.mNodes;
                    int i = this.mIndex + 1;
                    this.mIndex = i;
                    return (NavDestination) sparseArrayCompat.valueAt(i);
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                if (this.mWentToNext) {
                    ((NavDestination) NavGraph.this.mNodes.valueAt(this.mIndex)).setParent((NavGraph) null);
                    NavGraph.this.mNodes.removeAt(this.mIndex);
                    this.mIndex--;
                    this.mWentToNext = false;
                    return;
                }
                throw new IllegalStateException("You must call next() before you can remove an element");
            }
        };
    }

    public final void addAll(@NonNull NavGraph navGraph) {
        Iterator it = navGraph.iterator();
        while (it.hasNext()) {
            it.remove();
            addDestination((NavDestination) it.next());
        }
    }

    public final void remove(@NonNull NavDestination navDestination) {
        int indexOfKey = this.mNodes.indexOfKey(navDestination.getId());
        if (indexOfKey >= 0) {
            ((NavDestination) this.mNodes.valueAt(indexOfKey)).setParent((NavGraph) null);
            this.mNodes.removeAt(indexOfKey);
        }
    }

    public final void clear() {
        Iterator it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public String getDisplayName() {
        return getId() != 0 ? super.getDisplayName() : "the root navigation";
    }

    @IdRes
    public final int getStartDestination() {
        return this.mStartDestId;
    }

    public final void setStartDestination(@IdRes int i) {
        if (i != getId()) {
            this.mStartDestId = i;
            this.mStartDestIdName = null;
            return;
        }
        throw new IllegalArgumentException("Start destination " + i + " cannot use the same id as the graph " + this);
    }

    /* access modifiers changed from: package-private */
    public String getStartDestDisplayName() {
        if (this.mStartDestIdName == null) {
            this.mStartDestIdName = Integer.toString(this.mStartDestId);
        }
        return this.mStartDestIdName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" startDestination=");
        NavDestination findNode = findNode(getStartDestination());
        if (findNode == null) {
            String str = this.mStartDestIdName;
            if (str == null) {
                sb.append("0x");
                sb.append(Integer.toHexString(this.mStartDestId));
            } else {
                sb.append(str);
            }
        } else {
            sb.append("{");
            sb.append(findNode.toString());
            sb.append("}");
        }
        return sb.toString();
    }
}
