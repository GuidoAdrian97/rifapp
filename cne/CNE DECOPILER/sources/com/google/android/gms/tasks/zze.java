package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zze implements OnCanceledListener, OnFailureListener, OnSuccessListener, zzq {
    private final Executor zzd;
    /* access modifiers changed from: private */
    public final Continuation zze;
    /* access modifiers changed from: private */
    public final zzu zzf;

    public zze(Executor executor, Continuation continuation, zzu zzu) {
        this.zzd = executor;
        this.zze = continuation;
        this.zzf = zzu;
    }

    public final void onComplete(Task task) {
        this.zzd.execute(new zzf(this, task));
    }

    public final void onSuccess(Object obj) {
        this.zzf.setResult(obj);
    }

    public final void onFailure(Exception exc) {
        this.zzf.setException(exc);
    }

    public final void onCanceled() {
        this.zzf.zza();
    }

    public final void cancel() {
        throw new UnsupportedOperationException();
    }
}
