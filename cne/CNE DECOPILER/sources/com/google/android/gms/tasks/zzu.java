package com.google.android.gms.tasks;

import android.app.Activity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

final class zzu extends Task {
    private final Object mLock = new Object();
    private Object zzaa;
    private Exception zzab;
    private final zzr zzx = new zzr();
    private boolean zzy;
    private volatile boolean zzz;

    zzu() {
    }

    public final boolean isComplete() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzy;
        }
        return z;
    }

    class zza extends LifecycleCallback {
        private final List zzac = new ArrayList();

        public static zza zza(Activity activity) {
            LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
            zza zza = (zza) fragment.getCallbackOrNull("TaskOnStopCallback", zza.class);
            return zza == null ? new zza(fragment) : zza;
        }

        private zza(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("TaskOnStopCallback", this);
        }

        public final void zzb(zzq zzq) {
            synchronized (this.zzac) {
                this.zzac.add(new WeakReference(zzq));
            }
        }

        public void onStop() {
            synchronized (this.zzac) {
                for (WeakReference weakReference : this.zzac) {
                    zzq zzq = (zzq) weakReference.get();
                    if (zzq != null) {
                        zzq.cancel();
                    }
                }
                this.zzac.clear();
            }
        }
    }

    public final boolean isCanceled() {
        return this.zzz;
    }

    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzy && !this.zzz && this.zzab == null;
        }
        return z;
    }

    public final Object getResult() {
        Object obj;
        synchronized (this.mLock) {
            zzb();
            zzd();
            if (this.zzab == null) {
                obj = this.zzaa;
            } else {
                throw new RuntimeExecutionException(this.zzab);
            }
        }
        return obj;
    }

    public final Object getResult(Class cls) {
        Object obj;
        synchronized (this.mLock) {
            zzb();
            zzd();
            if (cls.isInstance(this.zzab)) {
                throw ((Throwable) cls.cast(this.zzab));
            } else if (this.zzab == null) {
                obj = this.zzaa;
            } else {
                throw new RuntimeExecutionException(this.zzab);
            }
        }
        return obj;
    }

    public final Exception getException() {
        Exception exc;
        synchronized (this.mLock) {
            exc = this.zzab;
        }
        return exc;
    }

    public final Task addOnSuccessListener(OnSuccessListener onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    public final Task addOnSuccessListener(Executor executor, OnSuccessListener onSuccessListener) {
        this.zzx.zza((zzq) new zzm(executor, onSuccessListener));
        zze();
        return this;
    }

    public final Task addOnSuccessListener(Activity activity, OnSuccessListener onSuccessListener) {
        zzm zzm = new zzm(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.zzx.zza((zzq) zzm);
        zza.zza(activity).zzb(zzm);
        zze();
        return this;
    }

    public final Task addOnFailureListener(OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    public final Task addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        this.zzx.zza((zzq) new zzk(executor, onFailureListener));
        zze();
        return this;
    }

    public final Task addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        zzk zzk = new zzk(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.zzx.zza((zzq) zzk);
        zza.zza(activity).zzb(zzk);
        zze();
        return this;
    }

    public final Task addOnCompleteListener(OnCompleteListener onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    public final Task addOnCompleteListener(Executor executor, OnCompleteListener onCompleteListener) {
        this.zzx.zza((zzq) new zzi(executor, onCompleteListener));
        zze();
        return this;
    }

    public final Task addOnCompleteListener(Activity activity, OnCompleteListener onCompleteListener) {
        zzi zzi = new zzi(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.zzx.zza((zzq) zzi);
        zza.zza(activity).zzb(zzi);
        zze();
        return this;
    }

    public final Task continueWith(Continuation continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    public final Task continueWith(Executor executor, Continuation continuation) {
        zzu zzu = new zzu();
        this.zzx.zza((zzq) new zzc(executor, continuation, zzu));
        zze();
        return zzu;
    }

    public final Task continueWithTask(Continuation continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    public final Task addOnCanceledListener(OnCanceledListener onCanceledListener) {
        return addOnCanceledListener(TaskExecutors.MAIN_THREAD, onCanceledListener);
    }

    public final Task addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        this.zzx.zza((zzq) new zzg(executor, onCanceledListener));
        zze();
        return this;
    }

    public final Task addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        zzg zzg = new zzg(TaskExecutors.MAIN_THREAD, onCanceledListener);
        this.zzx.zza((zzq) zzg);
        zza.zza(activity).zzb(zzg);
        zze();
        return this;
    }

    public final Task continueWithTask(Executor executor, Continuation continuation) {
        zzu zzu = new zzu();
        this.zzx.zza((zzq) new zze(executor, continuation, zzu));
        zze();
        return zzu;
    }

    public final Task onSuccessTask(Executor executor, SuccessContinuation successContinuation) {
        zzu zzu = new zzu();
        this.zzx.zza((zzq) new zzo(executor, successContinuation, zzu));
        zze();
        return zzu;
    }

    public final Task onSuccessTask(SuccessContinuation successContinuation) {
        return onSuccessTask(TaskExecutors.MAIN_THREAD, successContinuation);
    }

    public final void setResult(Object obj) {
        synchronized (this.mLock) {
            zzc();
            this.zzy = true;
            this.zzaa = obj;
        }
        this.zzx.zza((Task) this);
    }

    public final boolean trySetResult(Object obj) {
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzaa = obj;
            this.zzx.zza((Task) this);
            return true;
        }
    }

    public final void setException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            zzc();
            this.zzy = true;
            this.zzab = exc;
        }
        this.zzx.zza((Task) this);
    }

    public final boolean trySetException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzab = exc;
            this.zzx.zza((Task) this);
            return true;
        }
    }

    public final boolean zza() {
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzz = true;
            this.zzx.zza((Task) this);
            return true;
        }
    }

    private final void zzb() {
        Preconditions.checkState(this.zzy, "Task is not yet complete");
    }

    private final void zzc() {
        Preconditions.checkState(!this.zzy, "Task is already complete");
    }

    private final void zzd() {
        if (this.zzz) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void zze() {
        synchronized (this.mLock) {
            if (this.zzy) {
                this.zzx.zza((Task) this);
            }
        }
    }
}
