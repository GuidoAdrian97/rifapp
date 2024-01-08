package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class LifecycleActivity {
    private final Object zzbp;

    public LifecycleActivity(Activity activity) {
        Preconditions.checkNotNull(activity, "Activity must not be null");
        this.zzbp = activity;
    }

    public boolean isSupport() {
        return this.zzbp instanceof FragmentActivity;
    }

    public final boolean zzh() {
        return this.zzbp instanceof Activity;
    }

    public Activity asActivity() {
        return (Activity) this.zzbp;
    }

    public FragmentActivity asFragmentActivity() {
        return (FragmentActivity) this.zzbp;
    }
}
