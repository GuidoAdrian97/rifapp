package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzi implements zzq {
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final Executor zzd;
    /* access modifiers changed from: private */
    public OnCompleteListener zzl;

    public zzi(Executor executor, OnCompleteListener onCompleteListener) {
        this.zzd = executor;
        this.zzl = onCompleteListener;
    }

    public final void onComplete(Task task) {
        synchronized (this.mLock) {
            if (this.zzl != null) {
                this.zzd.execute(new zzj(this, task));
            }
        }
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzl = null;
        }
    }
}
