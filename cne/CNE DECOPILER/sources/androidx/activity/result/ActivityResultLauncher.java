package androidx.activity.result;

import android.annotation.SuppressLint;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

public abstract class ActivityResultLauncher {
    @NonNull
    public abstract ActivityResultContract getContract();

    public abstract void launch(@SuppressLint({"UnknownNullness"}) Object obj, @Nullable ActivityOptionsCompat activityOptionsCompat);

    @MainThread
    public abstract void unregister();

    public void launch(@SuppressLint({"UnknownNullness"}) Object obj) {
        launch(obj, (ActivityOptionsCompat) null);
    }
}
