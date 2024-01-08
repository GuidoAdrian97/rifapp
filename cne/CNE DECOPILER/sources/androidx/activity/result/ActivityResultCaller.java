package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;

public interface ActivityResultCaller {
    @NonNull
    ActivityResultLauncher registerForActivityResult(@NonNull ActivityResultContract activityResultContract, @NonNull ActivityResultCallback activityResultCallback);

    @NonNull
    ActivityResultLauncher registerForActivityResult(@NonNull ActivityResultContract activityResultContract, @NonNull ActivityResultRegistry activityResultRegistry, @NonNull ActivityResultCallback activityResultCallback);
}
