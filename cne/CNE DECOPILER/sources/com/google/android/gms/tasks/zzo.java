package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzo implements OnCanceledListener, OnFailureListener, OnSuccessListener, zzq {
    private final Executor zzd;
    private final zzu zzf;
    /* access modifiers changed from: private */
    public final SuccessContinuation zzr;

    public zzo(Executor executor, SuccessContinuation successContinuation, zzu zzu) {
        this.zzd = executor;
        this.zzr = successContinuation;
        this.zzf = zzu;
    }

    public final void onComplete(Task task) {
        this.zzd.execute(new zzp(this, task));
    }

    public final void cancel() {
        throw new UnsupportedOperationException();
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
}
