package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.annotation.AnimRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.BundleCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public final class CustomTabsIntent {
    public final Intent intent;
    public final Bundle startAnimationBundle;

    public static int getMaxToolbarItems() {
        return 5;
    }

    public void launchUrl(@NonNull Context context, @NonNull Uri uri) {
        this.intent.setData(uri);
        ContextCompat.startActivity(context, this.intent, this.startAnimationBundle);
    }

    CustomTabsIntent(Intent intent2, Bundle bundle) {
        this.intent = intent2;
        this.startAnimationBundle = bundle;
    }

    public final class Builder {
        private ArrayList mActionButtons;
        private SparseArray mColorSchemeParamBundles;
        private final CustomTabColorSchemeParams.Builder mDefaultColorSchemeBuilder = new CustomTabColorSchemeParams.Builder();
        private Bundle mDefaultColorSchemeBundle;
        private boolean mInstantAppsEnabled = true;
        private final Intent mIntent = new Intent("android.intent.action.VIEW");
        private ArrayList mMenuItems;
        private int mShareState = 0;
        private Bundle mStartAnimationBundle;

        public Builder() {
        }

        public Builder(@Nullable CustomTabsSession customTabsSession) {
            if (customTabsSession != null) {
                setSession(customTabsSession);
            }
        }

        @NonNull
        public Builder setSession(@NonNull CustomTabsSession customTabsSession) {
            this.mIntent.setPackage(customTabsSession.getComponentName().getPackageName());
            setSessionParameters(customTabsSession.getBinder(), customTabsSession.getId());
            return this;
        }

        @NonNull
        @RestrictTo
        public Builder setPendingSession(@NonNull CustomTabsSession.PendingSession pendingSession) {
            setSessionParameters((IBinder) null, pendingSession.getId());
            return this;
        }

        private void setSessionParameters(IBinder iBinder, PendingIntent pendingIntent) {
            Bundle bundle = new Bundle();
            BundleCompat.putBinder(bundle, "android.support.customtabs.extra.SESSION", iBinder);
            if (pendingIntent != null) {
                bundle.putParcelable("android.support.customtabs.extra.SESSION_ID", pendingIntent);
            }
            this.mIntent.putExtras(bundle);
        }

        @NonNull
        @Deprecated
        public Builder setToolbarColor(@ColorInt int i) {
            this.mDefaultColorSchemeBuilder.setToolbarColor(i);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder enableUrlBarHiding() {
            this.mIntent.putExtra("android.support.customtabs.extra.ENABLE_URLBAR_HIDING", true);
            return this;
        }

        @NonNull
        public Builder setUrlBarHidingEnabled(boolean z) {
            this.mIntent.putExtra("android.support.customtabs.extra.ENABLE_URLBAR_HIDING", z);
            return this;
        }

        @NonNull
        public Builder setCloseButtonIcon(@NonNull Bitmap bitmap) {
            this.mIntent.putExtra("android.support.customtabs.extra.CLOSE_BUTTON_ICON", bitmap);
            return this;
        }

        @NonNull
        public Builder setShowTitle(boolean z) {
            this.mIntent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", z ? 1 : 0);
            return this;
        }

        @NonNull
        public Builder addMenuItem(@NonNull String str, @NonNull PendingIntent pendingIntent) {
            if (this.mMenuItems == null) {
                this.mMenuItems = new ArrayList();
            }
            Bundle bundle = new Bundle();
            bundle.putString("android.support.customtabs.customaction.MENU_ITEM_TITLE", str);
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", pendingIntent);
            this.mMenuItems.add(bundle);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder addDefaultShareMenuItem() {
            setShareState(1);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setDefaultShareMenuItemEnabled(boolean z) {
            if (z) {
                setShareState(1);
            } else {
                setShareState(2);
            }
            return this;
        }

        @NonNull
        public Builder setShareState(int i) {
            if (i < 0 || i > 2) {
                throw new IllegalArgumentException("Invalid value for the shareState argument");
            }
            this.mShareState = i;
            if (i == 1) {
                this.mIntent.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", true);
            } else if (i == 2) {
                this.mIntent.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", false);
            } else {
                this.mIntent.removeExtra("android.support.customtabs.extra.SHARE_MENU_ITEM");
            }
            return this;
        }

        @NonNull
        public Builder setActionButton(@NonNull Bitmap bitmap, @NonNull String str, @NonNull PendingIntent pendingIntent, boolean z) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.customtabs.customaction.ID", 0);
            bundle.putParcelable("android.support.customtabs.customaction.ICON", bitmap);
            bundle.putString("android.support.customtabs.customaction.DESCRIPTION", str);
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", pendingIntent);
            this.mIntent.putExtra("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle);
            this.mIntent.putExtra("android.support.customtabs.extra.TINT_ACTION_BUTTON", z);
            return this;
        }

        @NonNull
        public Builder setActionButton(@NonNull Bitmap bitmap, @NonNull String str, @NonNull PendingIntent pendingIntent) {
            return setActionButton(bitmap, str, pendingIntent, false);
        }

        @NonNull
        @Deprecated
        public Builder addToolbarItem(int i, @NonNull Bitmap bitmap, @NonNull String str, @NonNull PendingIntent pendingIntent) {
            if (this.mActionButtons == null) {
                this.mActionButtons = new ArrayList();
            }
            if (this.mActionButtons.size() < 5) {
                Bundle bundle = new Bundle();
                bundle.putInt("android.support.customtabs.customaction.ID", i);
                bundle.putParcelable("android.support.customtabs.customaction.ICON", bitmap);
                bundle.putString("android.support.customtabs.customaction.DESCRIPTION", str);
                bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", pendingIntent);
                this.mActionButtons.add(bundle);
                return this;
            }
            throw new IllegalStateException("Exceeded maximum toolbar item count of 5");
        }

        @NonNull
        @Deprecated
        public Builder setSecondaryToolbarColor(@ColorInt int i) {
            this.mDefaultColorSchemeBuilder.setSecondaryToolbarColor(i);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setNavigationBarColor(@ColorInt int i) {
            this.mDefaultColorSchemeBuilder.setNavigationBarColor(i);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setNavigationBarDividerColor(@ColorInt int i) {
            this.mDefaultColorSchemeBuilder.setNavigationBarDividerColor(i);
            return this;
        }

        @NonNull
        public Builder setSecondaryToolbarViews(@NonNull RemoteViews remoteViews, @Nullable int[] iArr, @Nullable PendingIntent pendingIntent) {
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", remoteViews);
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", iArr);
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", pendingIntent);
            return this;
        }

        @NonNull
        public Builder setInstantAppsEnabled(boolean z) {
            this.mInstantAppsEnabled = z;
            return this;
        }

        @NonNull
        public Builder setStartAnimations(@NonNull Context context, @AnimRes int i, @AnimRes int i2) {
            this.mStartAnimationBundle = ActivityOptionsCompat.makeCustomAnimation(context, i, i2).toBundle();
            return this;
        }

        @NonNull
        public Builder setExitAnimations(@NonNull Context context, @AnimRes int i, @AnimRes int i2) {
            this.mIntent.putExtra("android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE", ActivityOptionsCompat.makeCustomAnimation(context, i, i2).toBundle());
            return this;
        }

        @NonNull
        public Builder setColorScheme(int i) {
            if (i < 0 || i > 2) {
                throw new IllegalArgumentException("Invalid value for the colorScheme argument");
            }
            this.mIntent.putExtra("androidx.browser.customtabs.extra.COLOR_SCHEME", i);
            return this;
        }

        @NonNull
        public Builder setColorSchemeParams(int i, @NonNull CustomTabColorSchemeParams customTabColorSchemeParams) {
            if (i < 0 || i > 2 || i == 0) {
                throw new IllegalArgumentException("Invalid colorScheme: " + i);
            }
            if (this.mColorSchemeParamBundles == null) {
                this.mColorSchemeParamBundles = new SparseArray();
            }
            this.mColorSchemeParamBundles.put(i, customTabColorSchemeParams.toBundle());
            return this;
        }

        @NonNull
        public Builder setDefaultColorSchemeParams(@NonNull CustomTabColorSchemeParams customTabColorSchemeParams) {
            this.mDefaultColorSchemeBundle = customTabColorSchemeParams.toBundle();
            return this;
        }

        @NonNull
        public CustomTabsIntent build() {
            if (!this.mIntent.hasExtra("android.support.customtabs.extra.SESSION")) {
                setSessionParameters((IBinder) null, (PendingIntent) null);
            }
            ArrayList arrayList = this.mMenuItems;
            if (arrayList != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", arrayList);
            }
            ArrayList arrayList2 = this.mActionButtons;
            if (arrayList2 != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", arrayList2);
            }
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.mInstantAppsEnabled);
            this.mIntent.putExtras(this.mDefaultColorSchemeBuilder.build().toBundle());
            Bundle bundle = this.mDefaultColorSchemeBundle;
            if (bundle != null) {
                this.mIntent.putExtras(bundle);
            }
            if (this.mColorSchemeParamBundles != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putSparseParcelableArray("androidx.browser.customtabs.extra.COLOR_SCHEME_PARAMS", this.mColorSchemeParamBundles);
                this.mIntent.putExtras(bundle2);
            }
            this.mIntent.putExtra("androidx.browser.customtabs.extra.SHARE_STATE", this.mShareState);
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle);
        }
    }

    @NonNull
    public static Intent setAlwaysUseBrowserUI(@Nullable Intent intent2) {
        if (intent2 == null) {
            intent2 = new Intent("android.intent.action.VIEW");
        }
        intent2.addFlags(268435456);
        intent2.putExtra("android.support.customtabs.extra.user_opt_out", true);
        return intent2;
    }

    public static boolean shouldAlwaysUseBrowserUI(@NonNull Intent intent2) {
        if (!intent2.getBooleanExtra("android.support.customtabs.extra.user_opt_out", false) || (intent2.getFlags() & 268435456) == 0) {
            return false;
        }
        return true;
    }

    @NonNull
    public static CustomTabColorSchemeParams getColorSchemeParams(@NonNull Intent intent2, int i) {
        Bundle bundle;
        if (i < 0 || i > 2 || i == 0) {
            throw new IllegalArgumentException("Invalid colorScheme: " + i);
        }
        Bundle extras = intent2.getExtras();
        if (extras == null) {
            return CustomTabColorSchemeParams.fromBundle((Bundle) null);
        }
        CustomTabColorSchemeParams fromBundle = CustomTabColorSchemeParams.fromBundle(extras);
        SparseArray sparseParcelableArray = extras.getSparseParcelableArray("androidx.browser.customtabs.extra.COLOR_SCHEME_PARAMS");
        if (sparseParcelableArray == null || (bundle = (Bundle) sparseParcelableArray.get(i)) == null) {
            return fromBundle;
        }
        return CustomTabColorSchemeParams.fromBundle(bundle).withDefaults(fromBundle);
    }
}
