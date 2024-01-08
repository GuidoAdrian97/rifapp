package androidx.activity.result;

import android.annotation.SuppressLint;

public interface ActivityResultCallback {
    void onActivityResult(@SuppressLint({"UnknownNullness"}) Object obj);
}
