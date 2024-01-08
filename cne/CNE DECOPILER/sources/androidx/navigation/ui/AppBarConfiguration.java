package androidx.navigation.ui;

import android.annotation.SuppressLint;
import android.view.Menu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavGraph;
import java.util.HashSet;
import java.util.Set;

public final class AppBarConfiguration {
    private final OnNavigateUpListener mFallbackOnNavigateUpListener;
    private final Openable mOpenableLayout;
    private final Set mTopLevelDestinations;

    public interface OnNavigateUpListener {
        boolean onNavigateUp();
    }

    private AppBarConfiguration(Set set, Openable openable, OnNavigateUpListener onNavigateUpListener) {
        this.mTopLevelDestinations = set;
        this.mOpenableLayout = openable;
        this.mFallbackOnNavigateUpListener = onNavigateUpListener;
    }

    @NonNull
    public Set getTopLevelDestinations() {
        return this.mTopLevelDestinations;
    }

    @Nullable
    public Openable getOpenableLayout() {
        return this.mOpenableLayout;
    }

    @Deprecated
    @Nullable
    public DrawerLayout getDrawerLayout() {
        Openable openable = this.mOpenableLayout;
        if (openable instanceof DrawerLayout) {
            return (DrawerLayout) openable;
        }
        return null;
    }

    @Nullable
    public OnNavigateUpListener getFallbackOnNavigateUpListener() {
        return this.mFallbackOnNavigateUpListener;
    }

    public final class Builder {
        private OnNavigateUpListener mFallbackOnNavigateUpListener;
        private Openable mOpenableLayout;
        private final Set mTopLevelDestinations;

        public Builder(@NonNull NavGraph navGraph) {
            HashSet hashSet = new HashSet();
            this.mTopLevelDestinations = hashSet;
            hashSet.add(Integer.valueOf(NavigationUI.findStartDestination(navGraph).getId()));
        }

        public Builder(@NonNull Menu menu) {
            this.mTopLevelDestinations = new HashSet();
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                this.mTopLevelDestinations.add(Integer.valueOf(menu.getItem(i).getItemId()));
            }
        }

        public Builder(@NonNull int... iArr) {
            this.mTopLevelDestinations = new HashSet();
            for (int valueOf : iArr) {
                this.mTopLevelDestinations.add(Integer.valueOf(valueOf));
            }
        }

        public Builder(@NonNull Set set) {
            HashSet hashSet = new HashSet();
            this.mTopLevelDestinations = hashSet;
            hashSet.addAll(set);
        }

        @NonNull
        @Deprecated
        public Builder setDrawerLayout(@Nullable DrawerLayout drawerLayout) {
            this.mOpenableLayout = drawerLayout;
            return this;
        }

        @NonNull
        public Builder setOpenableLayout(@Nullable Openable openable) {
            this.mOpenableLayout = openable;
            return this;
        }

        @NonNull
        public Builder setFallbackOnNavigateUpListener(@Nullable OnNavigateUpListener onNavigateUpListener) {
            this.mFallbackOnNavigateUpListener = onNavigateUpListener;
            return this;
        }

        @SuppressLint({"SyntheticAccessor"})
        @NonNull
        public AppBarConfiguration build() {
            return new AppBarConfiguration(this.mTopLevelDestinations, this.mOpenableLayout, this.mFallbackOnNavigateUpListener);
        }
    }
}
