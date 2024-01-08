package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.app.TaskStackBuilder;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class NavController {
    private Activity mActivity;
    final Deque mBackStack = new ArrayDeque();
    private Parcelable[] mBackStackToRestore;
    private final Context mContext;
    private boolean mDeepLinkHandled;
    private boolean mEnableOnBackPressedCallback = true;
    NavGraph mGraph;
    private NavInflater mInflater;
    private final LifecycleObserver mLifecycleObserver = new LifecycleEventObserver() {
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            NavController navController = NavController.this;
            if (navController.mGraph != null) {
                for (NavBackStackEntry handleLifecycleEvent : navController.mBackStack) {
                    handleLifecycleEvent.handleLifecycleEvent(event);
                }
            }
        }
    };
    private LifecycleOwner mLifecycleOwner;
    private NavigatorProvider mNavigatorProvider = new NavigatorProvider();
    private Bundle mNavigatorStateToRestore;
    private final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(false) {
        public void handleOnBackPressed() {
            NavController.this.popBackStack();
        }
    };
    private final CopyOnWriteArrayList mOnDestinationChangedListeners = new CopyOnWriteArrayList();
    private NavControllerViewModel mViewModel;

    public interface OnDestinationChangedListener {
        void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle);
    }

    public NavController(@NonNull Context context) {
        this.mContext = context;
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                break;
            } else if (context instanceof Activity) {
                this.mActivity = (Activity) context;
                break;
            } else {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        NavigatorProvider navigatorProvider = this.mNavigatorProvider;
        navigatorProvider.addNavigator(new NavGraphNavigator(navigatorProvider));
        this.mNavigatorProvider.addNavigator(new ActivityNavigator(this.mContext));
    }

    @NonNull
    @RestrictTo
    public Deque getBackStack() {
        return this.mBackStack;
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    @NonNull
    public NavigatorProvider getNavigatorProvider() {
        return this.mNavigatorProvider;
    }

    @RestrictTo
    public void setNavigatorProvider(@NonNull NavigatorProvider navigatorProvider) {
        if (this.mBackStack.isEmpty()) {
            this.mNavigatorProvider = navigatorProvider;
            return;
        }
        throw new IllegalStateException("NavigatorProvider must be set before setGraph call");
    }

    public void addOnDestinationChangedListener(@NonNull OnDestinationChangedListener onDestinationChangedListener) {
        if (!this.mBackStack.isEmpty()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.mBackStack.peekLast();
            onDestinationChangedListener.onDestinationChanged(this, navBackStackEntry.getDestination(), navBackStackEntry.getArguments());
        }
        this.mOnDestinationChangedListeners.add(onDestinationChangedListener);
    }

    public void removeOnDestinationChangedListener(@NonNull OnDestinationChangedListener onDestinationChangedListener) {
        this.mOnDestinationChangedListeners.remove(onDestinationChangedListener);
    }

    public boolean popBackStack() {
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        return popBackStack(getCurrentDestination().getId(), true);
    }

    public boolean popBackStack(@IdRes int i, boolean z) {
        return popBackStackInternal(i, z) && dispatchOnDestinationChanged();
    }

    /* access modifiers changed from: package-private */
    public boolean popBackStackInternal(int i, boolean z) {
        boolean z2;
        boolean z3 = false;
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator descendingIterator = this.mBackStack.descendingIterator();
        while (true) {
            if (!descendingIterator.hasNext()) {
                z2 = false;
                break;
            }
            NavDestination destination = ((NavBackStackEntry) descendingIterator.next()).getDestination();
            Navigator navigator = this.mNavigatorProvider.getNavigator(destination.getNavigatorName());
            if (z || destination.getId() != i) {
                arrayList.add(navigator);
            }
            if (destination.getId() == i) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            String displayName = NavDestination.getDisplayName(this.mContext, i);
            Log.i("NavController", "Ignoring popBackStack to destination " + displayName + " as it was not found on the current back stack");
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext() && ((Navigator) it.next()).popBackStack()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) this.mBackStack.removeLast();
            if (navBackStackEntry.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                navBackStackEntry.setMaxLifecycle(Lifecycle.State.DESTROYED);
            }
            NavControllerViewModel navControllerViewModel = this.mViewModel;
            if (navControllerViewModel != null) {
                navControllerViewModel.clear(navBackStackEntry.mId);
            }
            z3 = true;
        }
        updateOnBackPressedCallbackEnabled();
        return z3;
    }

    public boolean navigateUp() {
        if (getDestinationCountOnBackStack() != 1) {
            return popBackStack();
        }
        NavDestination currentDestination = getCurrentDestination();
        int id = currentDestination.getId();
        for (NavGraph parent = currentDestination.getParent(); parent != null; parent = parent.getParent()) {
            if (parent.getStartDestination() != id) {
                Bundle bundle = new Bundle();
                Activity activity = this.mActivity;
                if (!(activity == null || activity.getIntent() == null || this.mActivity.getIntent().getData() == null)) {
                    bundle.putParcelable("android-support-nav:controller:deepLinkIntent", this.mActivity.getIntent());
                    NavDestination.DeepLinkMatch matchDeepLink = this.mGraph.matchDeepLink(new NavDeepLinkRequest(this.mActivity.getIntent()));
                    if (matchDeepLink != null) {
                        bundle.putAll(matchDeepLink.getDestination().addInDefaultArgs(matchDeepLink.getMatchingArgs()));
                    }
                }
                new NavDeepLinkBuilder(this).setDestination(parent.getId()).setArguments(bundle).createTaskStackBuilder().startActivities();
                Activity activity2 = this.mActivity;
                if (activity2 != null) {
                    activity2.finish();
                }
                return true;
            }
            id = parent.getId();
        }
        return false;
    }

    private int getDestinationCountOnBackStack() {
        int i = 0;
        for (NavBackStackEntry destination : this.mBackStack) {
            if (!(destination.getDestination() instanceof NavGraph)) {
                i++;
            }
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:5:0x002d, LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean dispatchOnDestinationChanged() {
        /*
            r10 = this;
        L_0x0000:
            java.util.Deque r0 = r10.mBackStack
            boolean r0 = r0.isEmpty()
            r1 = 1
            if (r0 != 0) goto L_0x0030
            java.util.Deque r0 = r10.mBackStack
            java.lang.Object r0 = r0.peekLast()
            androidx.navigation.NavBackStackEntry r0 = (androidx.navigation.NavBackStackEntry) r0
            androidx.navigation.NavDestination r0 = r0.getDestination()
            boolean r0 = r0 instanceof androidx.navigation.NavGraph
            if (r0 == 0) goto L_0x0030
            java.util.Deque r0 = r10.mBackStack
            java.lang.Object r0 = r0.peekLast()
            androidx.navigation.NavBackStackEntry r0 = (androidx.navigation.NavBackStackEntry) r0
            androidx.navigation.NavDestination r0 = r0.getDestination()
            int r0 = r0.getId()
            boolean r0 = r10.popBackStackInternal(r0, r1)
            if (r0 == 0) goto L_0x0030
            goto L_0x0000
        L_0x0030:
            java.util.Deque r0 = r10.mBackStack
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0110
            java.util.Deque r0 = r10.mBackStack
            java.lang.Object r0 = r0.peekLast()
            androidx.navigation.NavBackStackEntry r0 = (androidx.navigation.NavBackStackEntry) r0
            androidx.navigation.NavDestination r0 = r0.getDestination()
            r2 = 0
            boolean r3 = r0 instanceof androidx.navigation.FloatingWindow
            if (r3 == 0) goto L_0x0068
            java.util.Deque r3 = r10.mBackStack
            java.util.Iterator r3 = r3.descendingIterator()
        L_0x004f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0068
            java.lang.Object r4 = r3.next()
            androidx.navigation.NavBackStackEntry r4 = (androidx.navigation.NavBackStackEntry) r4
            androidx.navigation.NavDestination r4 = r4.getDestination()
            boolean r5 = r4 instanceof androidx.navigation.NavGraph
            if (r5 != 0) goto L_0x004f
            boolean r5 = r4 instanceof androidx.navigation.FloatingWindow
            if (r5 != 0) goto L_0x004f
            r2 = r4
        L_0x0068:
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.util.Deque r4 = r10.mBackStack
            java.util.Iterator r4 = r4.descendingIterator()
        L_0x0073:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00c7
            java.lang.Object r5 = r4.next()
            androidx.navigation.NavBackStackEntry r5 = (androidx.navigation.NavBackStackEntry) r5
            androidx.lifecycle.Lifecycle$State r6 = r5.getMaxLifecycle()
            androidx.navigation.NavDestination r7 = r5.getDestination()
            if (r0 == 0) goto L_0x009f
            int r8 = r7.getId()
            int r9 = r0.getId()
            if (r8 != r9) goto L_0x009f
            androidx.lifecycle.Lifecycle$State r7 = androidx.lifecycle.Lifecycle.State.RESUMED
            if (r6 == r7) goto L_0x009a
            r3.put(r5, r7)
        L_0x009a:
            androidx.navigation.NavGraph r0 = r0.getParent()
            goto L_0x0073
        L_0x009f:
            if (r2 == 0) goto L_0x00c1
            int r7 = r7.getId()
            int r8 = r2.getId()
            if (r7 != r8) goto L_0x00c1
            androidx.lifecycle.Lifecycle$State r7 = androidx.lifecycle.Lifecycle.State.RESUMED
            if (r6 != r7) goto L_0x00b5
            androidx.lifecycle.Lifecycle$State r6 = androidx.lifecycle.Lifecycle.State.STARTED
            r5.setMaxLifecycle(r6)
            goto L_0x00bc
        L_0x00b5:
            androidx.lifecycle.Lifecycle$State r7 = androidx.lifecycle.Lifecycle.State.STARTED
            if (r6 == r7) goto L_0x00bc
            r3.put(r5, r7)
        L_0x00bc:
            androidx.navigation.NavGraph r2 = r2.getParent()
            goto L_0x0073
        L_0x00c1:
            androidx.lifecycle.Lifecycle$State r6 = androidx.lifecycle.Lifecycle.State.CREATED
            r5.setMaxLifecycle(r6)
            goto L_0x0073
        L_0x00c7:
            java.util.Deque r0 = r10.mBackStack
            java.util.Iterator r0 = r0.iterator()
        L_0x00cd:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00e9
            java.lang.Object r2 = r0.next()
            androidx.navigation.NavBackStackEntry r2 = (androidx.navigation.NavBackStackEntry) r2
            java.lang.Object r4 = r3.get(r2)
            androidx.lifecycle.Lifecycle$State r4 = (androidx.lifecycle.Lifecycle.State) r4
            if (r4 == 0) goto L_0x00e5
            r2.setMaxLifecycle(r4)
            goto L_0x00cd
        L_0x00e5:
            r2.updateState()
            goto L_0x00cd
        L_0x00e9:
            java.util.Deque r0 = r10.mBackStack
            java.lang.Object r0 = r0.peekLast()
            androidx.navigation.NavBackStackEntry r0 = (androidx.navigation.NavBackStackEntry) r0
            java.util.concurrent.CopyOnWriteArrayList r2 = r10.mOnDestinationChangedListeners
            java.util.Iterator r2 = r2.iterator()
        L_0x00f7:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x010f
            java.lang.Object r3 = r2.next()
            androidx.navigation.NavController$OnDestinationChangedListener r3 = (androidx.navigation.NavController.OnDestinationChangedListener) r3
            androidx.navigation.NavDestination r4 = r0.getDestination()
            android.os.Bundle r5 = r0.getArguments()
            r3.onDestinationChanged(r10, r4, r5)
            goto L_0x00f7
        L_0x010f:
            return r1
        L_0x0110:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.dispatchOnDestinationChanged():boolean");
    }

    @NonNull
    public NavInflater getNavInflater() {
        if (this.mInflater == null) {
            this.mInflater = new NavInflater(this.mContext, this.mNavigatorProvider);
        }
        return this.mInflater;
    }

    @CallSuper
    public void setGraph(@NavigationRes int i) {
        setGraph(i, (Bundle) null);
    }

    @CallSuper
    public void setGraph(@NavigationRes int i, @Nullable Bundle bundle) {
        setGraph(getNavInflater().inflate(i), bundle);
    }

    @CallSuper
    public void setGraph(@NonNull NavGraph navGraph) {
        setGraph(navGraph, (Bundle) null);
    }

    @CallSuper
    public void setGraph(@NonNull NavGraph navGraph, @Nullable Bundle bundle) {
        NavGraph navGraph2 = this.mGraph;
        if (navGraph2 != null) {
            popBackStackInternal(navGraph2.getId(), true);
        }
        this.mGraph = navGraph;
        onGraphCreated(bundle);
    }

    private void onGraphCreated(Bundle bundle) {
        Activity activity;
        ArrayList<String> stringArrayList;
        Bundle bundle2 = this.mNavigatorStateToRestore;
        if (!(bundle2 == null || (stringArrayList = bundle2.getStringArrayList("android-support-nav:controller:navigatorState:names")) == null)) {
            Iterator<String> it = stringArrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                Navigator navigator = this.mNavigatorProvider.getNavigator(next);
                Bundle bundle3 = this.mNavigatorStateToRestore.getBundle(next);
                if (bundle3 != null) {
                    navigator.onRestoreState(bundle3);
                }
            }
        }
        Parcelable[] parcelableArr = this.mBackStackToRestore;
        boolean z = false;
        if (parcelableArr != null) {
            int length = parcelableArr.length;
            int i = 0;
            while (i < length) {
                NavBackStackEntryState navBackStackEntryState = (NavBackStackEntryState) parcelableArr[i];
                NavDestination findDestination = findDestination(navBackStackEntryState.getDestinationId());
                if (findDestination != null) {
                    Bundle args = navBackStackEntryState.getArgs();
                    if (args != null) {
                        args.setClassLoader(this.mContext.getClassLoader());
                    }
                    this.mBackStack.add(new NavBackStackEntry(this.mContext, findDestination, args, this.mLifecycleOwner, this.mViewModel, navBackStackEntryState.getUUID(), navBackStackEntryState.getSavedState()));
                    i++;
                } else {
                    String displayName = NavDestination.getDisplayName(this.mContext, navBackStackEntryState.getDestinationId());
                    throw new IllegalStateException("Restoring the Navigation back stack failed: destination " + displayName + " cannot be found from the current destination " + getCurrentDestination());
                }
            }
            updateOnBackPressedCallbackEnabled();
            this.mBackStackToRestore = null;
        }
        if (this.mGraph == null || !this.mBackStack.isEmpty()) {
            dispatchOnDestinationChanged();
            return;
        }
        if (!this.mDeepLinkHandled && (activity = this.mActivity) != null && handleDeepLink(activity.getIntent())) {
            z = true;
        }
        if (!z) {
            navigate((NavDestination) this.mGraph, bundle, (NavOptions) null, (Navigator.Extras) null);
        }
    }

    public boolean handleDeepLink(@Nullable Intent intent) {
        NavGraph navGraph;
        NavDestination.DeepLinkMatch matchDeepLink;
        if (intent == null) {
            return false;
        }
        Bundle extras = intent.getExtras();
        int[] intArray = extras != null ? extras.getIntArray("android-support-nav:controller:deepLinkIds") : null;
        Bundle bundle = new Bundle();
        Bundle bundle2 = extras != null ? extras.getBundle("android-support-nav:controller:deepLinkExtras") : null;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if (!((intArray != null && intArray.length != 0) || intent.getData() == null || (matchDeepLink = this.mGraph.matchDeepLink(new NavDeepLinkRequest(intent))) == null)) {
            NavDestination destination = matchDeepLink.getDestination();
            int[] buildDeepLinkIds = destination.buildDeepLinkIds();
            bundle.putAll(destination.addInDefaultArgs(matchDeepLink.getMatchingArgs()));
            intArray = buildDeepLinkIds;
        }
        if (intArray == null || intArray.length == 0) {
            return false;
        }
        String findInvalidDestinationDisplayNameInDeepLink = findInvalidDestinationDisplayNameInDeepLink(intArray);
        if (findInvalidDestinationDisplayNameInDeepLink != null) {
            Log.i("NavController", "Could not find destination " + findInvalidDestinationDisplayNameInDeepLink + " in the navigation graph, ignoring the deep link from " + intent);
            return false;
        }
        bundle.putParcelable("android-support-nav:controller:deepLinkIntent", intent);
        int flags = intent.getFlags();
        int i = 268435456 & flags;
        if (i != 0 && (flags & 32768) == 0) {
            intent.addFlags(32768);
            TaskStackBuilder.create(this.mContext).addNextIntentWithParentStack(intent).startActivities();
            Activity activity = this.mActivity;
            if (activity != null) {
                activity.finish();
                this.mActivity.overridePendingTransition(0, 0);
            }
            return true;
        } else if (i != 0) {
            if (!this.mBackStack.isEmpty()) {
                popBackStackInternal(this.mGraph.getId(), true);
            }
            int i2 = 0;
            while (i2 < intArray.length) {
                int i3 = i2 + 1;
                int i4 = intArray[i2];
                NavDestination findDestination = findDestination(i4);
                if (findDestination != null) {
                    navigate(findDestination, bundle, new NavOptions.Builder().setEnterAnim(0).setExitAnim(0).build(), (Navigator.Extras) null);
                    i2 = i3;
                } else {
                    String displayName = NavDestination.getDisplayName(this.mContext, i4);
                    throw new IllegalStateException("Deep Linking failed: destination " + displayName + " cannot be found from the current destination " + getCurrentDestination());
                }
            }
            return true;
        } else {
            NavGraph navGraph2 = this.mGraph;
            int i5 = 0;
            while (i5 < intArray.length) {
                int i6 = intArray[i5];
                NavDestination findNode = i5 == 0 ? this.mGraph : navGraph2.findNode(i6);
                if (findNode != null) {
                    if (i5 != intArray.length - 1) {
                        while (true) {
                            navGraph = (NavGraph) findNode;
                            if (!(navGraph.findNode(navGraph.getStartDestination()) instanceof NavGraph)) {
                                break;
                            }
                            findNode = navGraph.findNode(navGraph.getStartDestination());
                        }
                        navGraph2 = navGraph;
                    } else {
                        navigate(findNode, findNode.addInDefaultArgs(bundle), new NavOptions.Builder().setPopUpTo(this.mGraph.getId(), true).setEnterAnim(0).setExitAnim(0).build(), (Navigator.Extras) null);
                    }
                    i5++;
                } else {
                    String displayName2 = NavDestination.getDisplayName(this.mContext, i6);
                    throw new IllegalStateException("Deep Linking failed: destination " + displayName2 + " cannot be found in graph " + navGraph2);
                }
            }
            this.mDeepLinkHandled = true;
            return true;
        }
    }

    private String findInvalidDestinationDisplayNameInDeepLink(int[] iArr) {
        NavGraph navGraph;
        NavGraph navGraph2 = this.mGraph;
        int i = 0;
        while (true) {
            NavDestination navDestination = null;
            if (i >= iArr.length) {
                return null;
            }
            int i2 = iArr[i];
            if (i != 0) {
                navDestination = navGraph2.findNode(i2);
            } else if (this.mGraph.getId() == i2) {
                navDestination = this.mGraph;
            }
            if (navDestination == null) {
                return NavDestination.getDisplayName(this.mContext, i2);
            }
            if (i != iArr.length - 1) {
                while (true) {
                    navGraph = (NavGraph) navDestination;
                    if (!(navGraph.findNode(navGraph.getStartDestination()) instanceof NavGraph)) {
                        break;
                    }
                    navDestination = navGraph.findNode(navGraph.getStartDestination());
                }
                navGraph2 = navGraph;
            }
            i++;
        }
    }

    @NonNull
    public NavGraph getGraph() {
        NavGraph navGraph = this.mGraph;
        if (navGraph != null) {
            return navGraph;
        }
        throw new IllegalStateException("You must call setGraph() before calling getGraph()");
    }

    @Nullable
    public NavDestination getCurrentDestination() {
        NavBackStackEntry currentBackStackEntry = getCurrentBackStackEntry();
        if (currentBackStackEntry != null) {
            return currentBackStackEntry.getDestination();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public NavDestination findDestination(int i) {
        NavDestination navDestination;
        NavGraph navGraph;
        NavGraph navGraph2 = this.mGraph;
        if (navGraph2 == null) {
            return null;
        }
        if (navGraph2.getId() == i) {
            return this.mGraph;
        }
        if (this.mBackStack.isEmpty()) {
            navDestination = this.mGraph;
        } else {
            navDestination = ((NavBackStackEntry) this.mBackStack.getLast()).getDestination();
        }
        if (navDestination instanceof NavGraph) {
            navGraph = (NavGraph) navDestination;
        } else {
            navGraph = navDestination.getParent();
        }
        return navGraph.findNode(i);
    }

    public void navigate(@IdRes int i) {
        navigate(i, (Bundle) null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle) {
        navigate(i, bundle, (NavOptions) null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle, @Nullable NavOptions navOptions) {
        navigate(i, bundle, navOptions, (Navigator.Extras) null);
    }

    public void navigate(@IdRes int i, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        NavDestination navDestination;
        int i2;
        if (this.mBackStack.isEmpty()) {
            navDestination = this.mGraph;
        } else {
            navDestination = ((NavBackStackEntry) this.mBackStack.getLast()).getDestination();
        }
        if (navDestination != null) {
            NavAction action = navDestination.getAction(i);
            Bundle bundle2 = null;
            if (action != null) {
                if (navOptions == null) {
                    navOptions = action.getNavOptions();
                }
                i2 = action.getDestinationId();
                Bundle defaultArguments = action.getDefaultArguments();
                if (defaultArguments != null) {
                    bundle2 = new Bundle();
                    bundle2.putAll(defaultArguments);
                }
            } else {
                i2 = i;
            }
            if (bundle != null) {
                if (bundle2 == null) {
                    bundle2 = new Bundle();
                }
                bundle2.putAll(bundle);
            }
            if (i2 == 0 && navOptions != null && navOptions.getPopUpTo() != -1) {
                popBackStack(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
            } else if (i2 != 0) {
                NavDestination findDestination = findDestination(i2);
                if (findDestination == null) {
                    String displayName = NavDestination.getDisplayName(this.mContext, i2);
                    if (action != null) {
                        throw new IllegalArgumentException("Navigation destination " + displayName + " referenced from action " + NavDestination.getDisplayName(this.mContext, i) + " cannot be found from the current destination " + navDestination);
                    }
                    throw new IllegalArgumentException("Navigation action/destination " + displayName + " cannot be found from the current destination " + navDestination);
                }
                navigate(findDestination, bundle2, navOptions, extras);
            } else {
                throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo");
            }
        } else {
            throw new IllegalStateException("no current navigation node");
        }
    }

    public void navigate(@NonNull Uri uri) {
        navigate(new NavDeepLinkRequest(uri, (String) null, (String) null));
    }

    public void navigate(@NonNull Uri uri, @Nullable NavOptions navOptions) {
        navigate(new NavDeepLinkRequest(uri, (String) null, (String) null), navOptions);
    }

    public void navigate(@NonNull Uri uri, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        navigate(new NavDeepLinkRequest(uri, (String) null, (String) null), navOptions, extras);
    }

    public void navigate(@NonNull NavDeepLinkRequest navDeepLinkRequest) {
        navigate(navDeepLinkRequest, (NavOptions) null);
    }

    public void navigate(@NonNull NavDeepLinkRequest navDeepLinkRequest, @Nullable NavOptions navOptions) {
        navigate(navDeepLinkRequest, navOptions, (Navigator.Extras) null);
    }

    public void navigate(@NonNull NavDeepLinkRequest navDeepLinkRequest, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        NavDestination.DeepLinkMatch matchDeepLink = this.mGraph.matchDeepLink(navDeepLinkRequest);
        if (matchDeepLink != null) {
            Bundle addInDefaultArgs = matchDeepLink.getDestination().addInDefaultArgs(matchDeepLink.getMatchingArgs());
            if (addInDefaultArgs == null) {
                addInDefaultArgs = new Bundle();
            }
            NavDestination destination = matchDeepLink.getDestination();
            Intent intent = new Intent();
            intent.setDataAndType(navDeepLinkRequest.getUri(), navDeepLinkRequest.getMimeType());
            intent.setAction(navDeepLinkRequest.getAction());
            addInDefaultArgs.putParcelable("android-support-nav:controller:deepLinkIntent", intent);
            navigate(destination, addInDefaultArgs, navOptions, extras);
            return;
        }
        throw new IllegalArgumentException("Navigation destination that matches request " + navDeepLinkRequest + " cannot be found in the navigation graph " + this.mGraph);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0031 A[LOOP:0: B:10:0x0031->B:15:0x005d, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ed A[LOOP:3: B:43:0x00ed->B:50:0x0131, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void navigate(@androidx.annotation.NonNull androidx.navigation.NavDestination r12, @androidx.annotation.Nullable android.os.Bundle r13, @androidx.annotation.Nullable androidx.navigation.NavOptions r14, @androidx.annotation.Nullable androidx.navigation.Navigator.Extras r15) {
        /*
            r11 = this;
            r0 = 0
            if (r14 == 0) goto L_0x0017
            int r1 = r14.getPopUpTo()
            r2 = -1
            if (r1 == r2) goto L_0x0017
            int r1 = r14.getPopUpTo()
            boolean r2 = r14.isPopUpToInclusive()
            boolean r1 = r11.popBackStackInternal(r1, r2)
            goto L_0x0018
        L_0x0017:
            r1 = 0
        L_0x0018:
            androidx.navigation.NavigatorProvider r2 = r11.mNavigatorProvider
            java.lang.String r3 = r12.getNavigatorName()
            androidx.navigation.Navigator r2 = r2.getNavigator((java.lang.String) r3)
            android.os.Bundle r13 = r12.addInDefaultArgs(r13)
            androidx.navigation.NavDestination r15 = r2.navigate(r12, r13, r14, r15)
            r2 = 1
            if (r15 == 0) goto L_0x017c
            boolean r14 = r15 instanceof androidx.navigation.FloatingWindow
            if (r14 != 0) goto L_0x0060
        L_0x0031:
            java.util.Deque r14 = r11.mBackStack
            boolean r14 = r14.isEmpty()
            if (r14 != 0) goto L_0x0060
            java.util.Deque r14 = r11.mBackStack
            java.lang.Object r14 = r14.peekLast()
            androidx.navigation.NavBackStackEntry r14 = (androidx.navigation.NavBackStackEntry) r14
            androidx.navigation.NavDestination r14 = r14.getDestination()
            boolean r14 = r14 instanceof androidx.navigation.FloatingWindow
            if (r14 == 0) goto L_0x0060
            java.util.Deque r14 = r11.mBackStack
            java.lang.Object r14 = r14.peekLast()
            androidx.navigation.NavBackStackEntry r14 = (androidx.navigation.NavBackStackEntry) r14
            androidx.navigation.NavDestination r14 = r14.getDestination()
            int r14 = r14.getId()
            boolean r14 = r11.popBackStackInternal(r14, r2)
            if (r14 == 0) goto L_0x0060
            goto L_0x0031
        L_0x0060:
            java.util.ArrayDeque r14 = new java.util.ArrayDeque
            r14.<init>()
            boolean r3 = r12 instanceof androidx.navigation.NavGraph
            if (r3 == 0) goto L_0x00a5
            r3 = r15
        L_0x006a:
            androidx.navigation.NavGraph r9 = r3.getParent()
            if (r9 == 0) goto L_0x009e
            androidx.navigation.NavBackStackEntry r10 = new androidx.navigation.NavBackStackEntry
            android.content.Context r4 = r11.mContext
            androidx.lifecycle.LifecycleOwner r7 = r11.mLifecycleOwner
            androidx.navigation.NavControllerViewModel r8 = r11.mViewModel
            r3 = r10
            r5 = r9
            r6 = r13
            r3.<init>(r4, r5, r6, r7, r8)
            r14.addFirst(r10)
            java.util.Deque r3 = r11.mBackStack
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x009e
            java.util.Deque r3 = r11.mBackStack
            java.lang.Object r3 = r3.getLast()
            androidx.navigation.NavBackStackEntry r3 = (androidx.navigation.NavBackStackEntry) r3
            androidx.navigation.NavDestination r3 = r3.getDestination()
            if (r3 != r9) goto L_0x009e
            int r3 = r9.getId()
            r11.popBackStackInternal(r3, r2)
        L_0x009e:
            if (r9 == 0) goto L_0x00a5
            if (r9 != r12) goto L_0x00a3
            goto L_0x00a5
        L_0x00a3:
            r3 = r9
            goto L_0x006a
        L_0x00a5:
            boolean r12 = r14.isEmpty()
            if (r12 == 0) goto L_0x00ad
            r12 = r15
            goto L_0x00b7
        L_0x00ad:
            java.lang.Object r12 = r14.getFirst()
            androidx.navigation.NavBackStackEntry r12 = (androidx.navigation.NavBackStackEntry) r12
            androidx.navigation.NavDestination r12 = r12.getDestination()
        L_0x00b7:
            if (r12 == 0) goto L_0x00db
            int r3 = r12.getId()
            androidx.navigation.NavDestination r3 = r11.findDestination(r3)
            if (r3 != 0) goto L_0x00db
            androidx.navigation.NavGraph r12 = r12.getParent()
            if (r12 == 0) goto L_0x00b7
            androidx.navigation.NavBackStackEntry r9 = new androidx.navigation.NavBackStackEntry
            android.content.Context r4 = r11.mContext
            androidx.lifecycle.LifecycleOwner r7 = r11.mLifecycleOwner
            androidx.navigation.NavControllerViewModel r8 = r11.mViewModel
            r3 = r9
            r5 = r12
            r6 = r13
            r3.<init>(r4, r5, r6, r7, r8)
            r14.addFirst(r9)
            goto L_0x00b7
        L_0x00db:
            boolean r12 = r14.isEmpty()
            if (r12 == 0) goto L_0x00e3
            r12 = r15
            goto L_0x00ed
        L_0x00e3:
            java.lang.Object r12 = r14.getLast()
            androidx.navigation.NavBackStackEntry r12 = (androidx.navigation.NavBackStackEntry) r12
            androidx.navigation.NavDestination r12 = r12.getDestination()
        L_0x00ed:
            java.util.Deque r3 = r11.mBackStack
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0134
            java.util.Deque r3 = r11.mBackStack
            java.lang.Object r3 = r3.getLast()
            androidx.navigation.NavBackStackEntry r3 = (androidx.navigation.NavBackStackEntry) r3
            androidx.navigation.NavDestination r3 = r3.getDestination()
            boolean r3 = r3 instanceof androidx.navigation.NavGraph
            if (r3 == 0) goto L_0x0134
            java.util.Deque r3 = r11.mBackStack
            java.lang.Object r3 = r3.getLast()
            androidx.navigation.NavBackStackEntry r3 = (androidx.navigation.NavBackStackEntry) r3
            androidx.navigation.NavDestination r3 = r3.getDestination()
            androidx.navigation.NavGraph r3 = (androidx.navigation.NavGraph) r3
            int r4 = r12.getId()
            androidx.navigation.NavDestination r3 = r3.findNode(r4, r0)
            if (r3 != 0) goto L_0x0134
            java.util.Deque r3 = r11.mBackStack
            java.lang.Object r3 = r3.getLast()
            androidx.navigation.NavBackStackEntry r3 = (androidx.navigation.NavBackStackEntry) r3
            androidx.navigation.NavDestination r3 = r3.getDestination()
            int r3 = r3.getId()
            boolean r3 = r11.popBackStackInternal(r3, r2)
            if (r3 == 0) goto L_0x0134
            goto L_0x00ed
        L_0x0134:
            java.util.Deque r12 = r11.mBackStack
            r12.addAll(r14)
            java.util.Deque r12 = r11.mBackStack
            boolean r12 = r12.isEmpty()
            if (r12 != 0) goto L_0x0151
            java.util.Deque r12 = r11.mBackStack
            java.lang.Object r12 = r12.getFirst()
            androidx.navigation.NavBackStackEntry r12 = (androidx.navigation.NavBackStackEntry) r12
            androidx.navigation.NavDestination r12 = r12.getDestination()
            androidx.navigation.NavGraph r14 = r11.mGraph
            if (r12 == r14) goto L_0x0165
        L_0x0151:
            androidx.navigation.NavBackStackEntry r12 = new androidx.navigation.NavBackStackEntry
            android.content.Context r4 = r11.mContext
            androidx.navigation.NavGraph r5 = r11.mGraph
            androidx.lifecycle.LifecycleOwner r7 = r11.mLifecycleOwner
            androidx.navigation.NavControllerViewModel r8 = r11.mViewModel
            r3 = r12
            r6 = r13
            r3.<init>(r4, r5, r6, r7, r8)
            java.util.Deque r14 = r11.mBackStack
            r14.addFirst(r12)
        L_0x0165:
            androidx.navigation.NavBackStackEntry r12 = new androidx.navigation.NavBackStackEntry
            android.content.Context r4 = r11.mContext
            android.os.Bundle r6 = r15.addInDefaultArgs(r13)
            androidx.lifecycle.LifecycleOwner r7 = r11.mLifecycleOwner
            androidx.navigation.NavControllerViewModel r8 = r11.mViewModel
            r3 = r12
            r5 = r15
            r3.<init>(r4, r5, r6, r7, r8)
            java.util.Deque r13 = r11.mBackStack
            r13.add(r12)
            goto L_0x0192
        L_0x017c:
            if (r14 == 0) goto L_0x0192
            boolean r12 = r14.shouldLaunchSingleTop()
            if (r12 == 0) goto L_0x0192
            java.util.Deque r12 = r11.mBackStack
            java.lang.Object r12 = r12.peekLast()
            androidx.navigation.NavBackStackEntry r12 = (androidx.navigation.NavBackStackEntry) r12
            if (r12 == 0) goto L_0x0191
            r12.replaceArguments(r13)
        L_0x0191:
            r0 = 1
        L_0x0192:
            r11.updateOnBackPressedCallbackEnabled()
            if (r1 != 0) goto L_0x019b
            if (r15 != 0) goto L_0x019b
            if (r0 == 0) goto L_0x019e
        L_0x019b:
            r11.dispatchOnDestinationChanged()
        L_0x019e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavController.navigate(androidx.navigation.NavDestination, android.os.Bundle, androidx.navigation.NavOptions, androidx.navigation.Navigator$Extras):void");
    }

    public void navigate(@NonNull NavDirections navDirections) {
        navigate(navDirections.getActionId(), navDirections.getArguments());
    }

    public void navigate(@NonNull NavDirections navDirections, @Nullable NavOptions navOptions) {
        navigate(navDirections.getActionId(), navDirections.getArguments(), navOptions);
    }

    public void navigate(@NonNull NavDirections navDirections, @NonNull Navigator.Extras extras) {
        navigate(navDirections.getActionId(), navDirections.getArguments(), (NavOptions) null, extras);
    }

    @NonNull
    public NavDeepLinkBuilder createDeepLink() {
        return new NavDeepLinkBuilder(this);
    }

    @CallSuper
    @Nullable
    public Bundle saveState() {
        Bundle bundle;
        ArrayList arrayList = new ArrayList();
        Bundle bundle2 = new Bundle();
        for (Map.Entry entry : this.mNavigatorProvider.getNavigators().entrySet()) {
            String str = (String) entry.getKey();
            Bundle onSaveState = ((Navigator) entry.getValue()).onSaveState();
            if (onSaveState != null) {
                arrayList.add(str);
                bundle2.putBundle(str, onSaveState);
            }
        }
        if (!arrayList.isEmpty()) {
            bundle = new Bundle();
            bundle2.putStringArrayList("android-support-nav:controller:navigatorState:names", arrayList);
            bundle.putBundle("android-support-nav:controller:navigatorState", bundle2);
        } else {
            bundle = null;
        }
        if (!this.mBackStack.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Parcelable[] parcelableArr = new Parcelable[this.mBackStack.size()];
            int i = 0;
            for (NavBackStackEntry navBackStackEntryState : this.mBackStack) {
                parcelableArr[i] = new NavBackStackEntryState(navBackStackEntryState);
                i++;
            }
            bundle.putParcelableArray("android-support-nav:controller:backStack", parcelableArr);
        }
        if (this.mDeepLinkHandled) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android-support-nav:controller:deepLinkHandled", this.mDeepLinkHandled);
        }
        return bundle;
    }

    @CallSuper
    public void restoreState(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(this.mContext.getClassLoader());
            this.mNavigatorStateToRestore = bundle.getBundle("android-support-nav:controller:navigatorState");
            this.mBackStackToRestore = bundle.getParcelableArray("android-support-nav:controller:backStack");
            this.mDeepLinkHandled = bundle.getBoolean("android-support-nav:controller:deepLinkHandled");
        }
    }

    /* access modifiers changed from: package-private */
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner != this.mLifecycleOwner) {
            this.mLifecycleOwner = lifecycleOwner;
            lifecycleOwner.getLifecycle().addObserver(this.mLifecycleObserver);
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnBackPressedDispatcher(OnBackPressedDispatcher onBackPressedDispatcher) {
        if (this.mLifecycleOwner != null) {
            this.mOnBackPressedCallback.remove();
            onBackPressedDispatcher.addCallback(this.mLifecycleOwner, this.mOnBackPressedCallback);
            this.mLifecycleOwner.getLifecycle().removeObserver(this.mLifecycleObserver);
            this.mLifecycleOwner.getLifecycle().addObserver(this.mLifecycleObserver);
            return;
        }
        throw new IllegalStateException("You must call setLifecycleOwner() before calling setOnBackPressedDispatcher()");
    }

    /* access modifiers changed from: package-private */
    public void enableOnBackPressed(boolean z) {
        this.mEnableOnBackPressedCallback = z;
        updateOnBackPressedCallbackEnabled();
    }

    private void updateOnBackPressedCallbackEnabled() {
        OnBackPressedCallback onBackPressedCallback = this.mOnBackPressedCallback;
        boolean z = true;
        if (!this.mEnableOnBackPressedCallback || getDestinationCountOnBackStack() <= 1) {
            z = false;
        }
        onBackPressedCallback.setEnabled(z);
    }

    /* access modifiers changed from: package-private */
    public void setViewModelStore(ViewModelStore viewModelStore) {
        if (this.mViewModel != NavControllerViewModel.getInstance(viewModelStore)) {
            if (this.mBackStack.isEmpty()) {
                this.mViewModel = NavControllerViewModel.getInstance(viewModelStore);
                return;
            }
            throw new IllegalStateException("ViewModelStore should be set before setGraph call");
        }
    }

    @NonNull
    public ViewModelStoreOwner getViewModelStoreOwner(@IdRes int i) {
        if (this.mViewModel != null) {
            NavBackStackEntry backStackEntry = getBackStackEntry(i);
            if (backStackEntry.getDestination() instanceof NavGraph) {
                return backStackEntry;
            }
            throw new IllegalArgumentException("No NavGraph with ID " + i + " is on the NavController's back stack");
        }
        throw new IllegalStateException("You must call setViewModelStore() before calling getViewModelStoreOwner().");
    }

    @NonNull
    public NavBackStackEntry getBackStackEntry(@IdRes int i) {
        NavBackStackEntry navBackStackEntry;
        Iterator descendingIterator = this.mBackStack.descendingIterator();
        while (true) {
            if (!descendingIterator.hasNext()) {
                navBackStackEntry = null;
                break;
            }
            navBackStackEntry = (NavBackStackEntry) descendingIterator.next();
            if (navBackStackEntry.getDestination().getId() == i) {
                break;
            }
        }
        if (navBackStackEntry != null) {
            return navBackStackEntry;
        }
        throw new IllegalArgumentException("No destination with ID " + i + " is on the NavController's back stack. The current destination is " + getCurrentDestination());
    }

    @Nullable
    public NavBackStackEntry getCurrentBackStackEntry() {
        if (this.mBackStack.isEmpty()) {
            return null;
        }
        return (NavBackStackEntry) this.mBackStack.getLast();
    }

    @Nullable
    public NavBackStackEntry getPreviousBackStackEntry() {
        Iterator descendingIterator = this.mBackStack.descendingIterator();
        if (descendingIterator.hasNext()) {
            descendingIterator.next();
        }
        while (descendingIterator.hasNext()) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) descendingIterator.next();
            if (!(navBackStackEntry.getDestination() instanceof NavGraph)) {
                return navBackStackEntry;
            }
        }
        return null;
    }
}
