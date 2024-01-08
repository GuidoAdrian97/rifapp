package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzm implements zzq {
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final Executor zzd;
    /* access modifiers changed from: private */
    public OnSuccessListener zzp;

    public zzm(Executor executor, OnSuccessListener onSuccessListener) {
        this.zzd = executor;
        this.zzp = onSuccessListener;
    }

    public final void onComplete(Task task) {
        if (task.isSuccessful()) {
            synchronized (this.mLock) {
                if (this.zzp != null) {
                    this.zzd.execute(new zzn(this, task));
                }
            }
        }
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzp = null;
        }
    }
}
