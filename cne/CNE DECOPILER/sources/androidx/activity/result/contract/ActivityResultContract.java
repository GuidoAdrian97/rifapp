package androidx.activity.result.contract;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class ActivityResultContract {
    @NonNull
    public abstract Intent createIntent(@NonNull Context context, @SuppressLint({"UnknownNullness"}) Object obj);

    @Nullable
    public SynchronousResult getSynchronousResult(@NonNull Context context, @SuppressLint({"UnknownNullness"}) Object obj) {
        return null;
    }

    @SuppressLint({"UnknownNullness"})
    public abstract Object parseResult(int i, @Nullable Intent intent);

    public final class SynchronousResult {
        private final Object mValue;

        public SynchronousResult(@SuppressLint({"UnknownNullness"}) Object obj) {
            this.mValue = obj;
        }

        @SuppressLint({"UnknownNullness"})
        public Object getValue() {
            return this.mValue;
        }
    }
}
