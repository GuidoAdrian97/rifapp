package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzc implements zzq {
    private final Executor zzd;
    /* access modifiers changed from: private */
    public final Continuation zze;
    /* access modifiers changed from: private */
    public final zzu zzf;

    public zzc(Executor executor, Continuation continuation, zzu zzu) {
        this.zzd = executor;
        this.zze = continuation;
        this.zzf = zzu;
    }

    public final void onComplete(Task task) {
        this.zzd.execute(new zzd(this, task));
    }

    public final void cancel() {
        throw new UnsupportedOperationException();
    }
}
