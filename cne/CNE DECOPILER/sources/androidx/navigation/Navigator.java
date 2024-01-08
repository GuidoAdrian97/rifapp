package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class Navigator {

    public interface Extras {
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Name {
        String value();
    }

    @NonNull
    public abstract NavDestination createDestination();

    @Nullable
    public abstract NavDestination navigate(@NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Extras extras);

    public void onRestoreState(@NonNull Bundle bundle) {
    }

    @Nullable
    public Bundle onSaveState() {
        return null;
    }

    public abstract boolean popBackStack();
}
