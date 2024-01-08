package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzf implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zze zzi;

    zzf(zze zze, Task task) {
        this.zzi = zze;
        this.zzg = task;
    }

    public final void run() {
        try {
            Task task = (Task) this.zzi.zze.then(this.zzg);
            if (task == null) {
                this.zzi.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            Executor executor = TaskExecutors.zzw;
            task.addOnSuccessListener(executor, (OnSuccessListener) this.zzi);
            task.addOnFailureListener(executor, (OnFailureListener) this.zzi);
            task.addOnCanceledListener(executor, (OnCanceledListener) this.zzi);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzi.zzf.setException((Exception) e.getCause());
            } else {
                this.zzi.zzf.setException(e);
            }
        } catch (Exception e2) {
            this.zzi.zzf.setException(e2);
        }
    }
}
