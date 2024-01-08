package com.google.android.gms.common.api.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class zzd extends Fragment implements LifecycleFragment {
    private static WeakHashMap zzbq = new WeakHashMap();
    private Map zzbr = new ArrayMap();
    /* access modifiers changed from: private */
    public int zzbs = 0;
    /* access modifiers changed from: private */
    public Bundle zzbt;

    public static zzd zza(FragmentActivity fragmentActivity) {
        zzd zzd;
        WeakReference weakReference = (WeakReference) zzbq.get(fragmentActivity);
        if (weakReference != null && (zzd = (zzd) weakReference.get()) != null) {
            return zzd;
        }
        try {
            zzd zzd2 = (zzd) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (zzd2 == null || zzd2.isRemoving()) {
                zzd2 = new zzd();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) zzd2, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzbq.put(fragmentActivity, new WeakReference(zzd2));
            return zzd2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        }
    }

    public final LifecycleCallback getCallbackOrNull(String str, Class cls) {
        return (LifecycleCallback) cls.cast(this.zzbr.get(str));
    }

    public final void addCallback(String str, LifecycleCallback lifecycleCallback) {
        if (!this.zzbr.containsKey(str)) {
            this.zzbr.put(str, lifecycleCallback);
            if (this.zzbs > 0) {
                new zzi(Looper.getMainLooper()).post(new zzc(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 59);
        sb.append("LifecycleCallback with tag ");
        sb.append(str);
        sb.append(" already added to this fragment.");
        throw new IllegalArgumentException(sb.toString());
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzbs = 1;
        this.zzbt = bundle;
        for (Map.Entry entry : this.zzbr.entrySet()) {
            ((LifecycleCallback) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzbs = 2;
        for (LifecycleCallback onStart : this.zzbr.values()) {
            onStart.onStart();
        }
    }

    public final void onResume() {
        super.onResume();
        this.zzbs = 3;
        for (LifecycleCallback onResume : this.zzbr.values()) {
            onResume.onResume();
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback onActivityResult : this.zzbr.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Map.Entry entry : this.zzbr.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((LifecycleCallback) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public final void onStop() {
        super.onStop();
        this.zzbs = 4;
        for (LifecycleCallback onStop : this.zzbr.values()) {
            onStop.onStop();
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzbs = 5;
        for (LifecycleCallback onDestroy : this.zzbr.values()) {
            onDestroy.onDestroy();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback dump : this.zzbr.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }
}
