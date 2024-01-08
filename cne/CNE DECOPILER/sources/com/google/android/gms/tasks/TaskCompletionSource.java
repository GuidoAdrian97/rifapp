package com.google.android.gms.tasks;

import androidx.annotation.NonNull;

public class TaskCompletionSource {
    /* access modifiers changed from: private */
    public final zzu zza = new zzu();

    public TaskCompletionSource() {
    }

    public TaskCompletionSource(@NonNull CancellationToken cancellationToken) {
        cancellationToken.onCanceledRequested(new zzs(this));
    }

    public void setResult(Object obj) {
        this.zza.setResult(obj);
    }

    public boolean trySetResult(Object obj) {
        return this.zza.trySetResult(obj);
    }

    public void setException(@NonNull Exception exc) {
        this.zza.setException(exc);
    }

    public boolean trySetException(@NonNull Exception exc) {
        return this.zza.trySetException(exc);
    }

    @NonNull
    public Task getTask() {
        return this.zza;
    }
}
