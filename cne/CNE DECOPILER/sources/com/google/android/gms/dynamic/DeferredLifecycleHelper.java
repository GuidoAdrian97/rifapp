package com.google.android.gms.dynamic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class DeferredLifecycleHelper {
    /* access modifiers changed from: private */
    public LifecycleDelegate zaru;
    /* access modifiers changed from: private */
    public Bundle zarv;
    /* access modifiers changed from: private */
    public LinkedList zarw;
    private final OnDelegateCreatedListener zarx = new zaa(this);

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    interface zaa {
        int getState();

        void zaa(LifecycleDelegate lifecycleDelegate);
    }

    /* access modifiers changed from: protected */
    public abstract void createDelegate(OnDelegateCreatedListener onDelegateCreatedListener);

    public LifecycleDelegate getDelegate() {
        return this.zaru;
    }

    private final void zal(int i) {
        while (!this.zarw.isEmpty() && ((zaa) this.zarw.getLast()).getState() >= i) {
            this.zarw.removeLast();
        }
    }

    private final void zaa(Bundle bundle, zaa zaa2) {
        LifecycleDelegate lifecycleDelegate = this.zaru;
        if (lifecycleDelegate != null) {
            zaa2.zaa(lifecycleDelegate);
            return;
        }
        if (this.zarw == null) {
            this.zarw = new LinkedList();
        }
        this.zarw.add(zaa2);
        if (bundle != null) {
            Bundle bundle2 = this.zarv;
            if (bundle2 == null) {
                this.zarv = (Bundle) bundle.clone();
            } else {
                bundle2.putAll(bundle);
            }
        }
        createDelegate(this.zarx);
    }

    public void onCreate(Bundle bundle) {
        zaa(bundle, (zaa) new zab(this, bundle));
    }

    public static void showGooglePlayUnavailableMessage(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String errorMessage = ConnectionErrorMessages.getErrorMessage(context, isGooglePlayServicesAvailable);
        String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(errorMessage);
        linearLayout.addView(textView);
        Intent errorResolutionIntent = instance.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, (String) null);
        if (errorResolutionIntent != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(errorDialogButtonMessage);
            linearLayout.addView(button);
            button.setOnClickListener(new zad(context, errorResolutionIntent));
        }
    }

    public void onStart() {
        zaa((Bundle) null, (zaa) new zag(this));
    }

    public void onResume() {
        zaa((Bundle) null, (zaa) new zaf(this));
    }

    public void onPause() {
        LifecycleDelegate lifecycleDelegate = this.zaru;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onPause();
        } else {
            zal(5);
        }
    }

    public void onStop() {
        LifecycleDelegate lifecycleDelegate = this.zaru;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onStop();
        } else {
            zal(4);
        }
    }

    public void onDestroy() {
        LifecycleDelegate lifecycleDelegate = this.zaru;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onDestroy();
        } else {
            zal(1);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        LifecycleDelegate lifecycleDelegate = this.zaru;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onSaveInstanceState(bundle);
            return;
        }
        Bundle bundle2 = this.zarv;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }

    public void onLowMemory() {
        LifecycleDelegate lifecycleDelegate = this.zaru;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onLowMemory();
        }
    }
}
