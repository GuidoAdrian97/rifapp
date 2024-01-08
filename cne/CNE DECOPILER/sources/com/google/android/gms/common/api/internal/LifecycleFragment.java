package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public interface LifecycleFragment {
    void addCallback(String str, LifecycleCallback lifecycleCallback);

    LifecycleCallback getCallbackOrNull(String str, Class cls);
}
