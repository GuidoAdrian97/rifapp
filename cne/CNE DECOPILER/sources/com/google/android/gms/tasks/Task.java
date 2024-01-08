package com.google.android.gms.tasks;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.concurrent.Executor;

public abstract class Task {
    @NonNull
    public abstract Task addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener);

    @NonNull
    public abstract Task addOnFailureListener(@NonNull OnFailureListener onFailureListener);

    @NonNull
    public abstract Task addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener);

    @NonNull
    public abstract Task addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener onSuccessListener);

    @NonNull
    public abstract Task addOnSuccessListener(@NonNull OnSuccessListener onSuccessListener);

    @NonNull
    public abstract Task addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener onSuccessListener);

    @Nullable
    public abstract Exception getException();

    @Nullable
    public abstract Object getResult();

    @Nullable
    public abstract Object getResult(@NonNull Class cls);

    public abstract boolean isCanceled();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();

    @NonNull
    public Task addOnCompleteListener(@NonNull OnCompleteListener onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public Task addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public Task addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public Task addOnCanceledListener(@NonNull OnCanceledListener onCanceledListener) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }

    @NonNull
    public Task addOnCanceledListener(@NonNull Executor executor, @NonNull OnCanceledListener onCanceledListener) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
    }

    @NonNull
    public Task addOnCanceledListener(@NonNull Activity activity, @NonNull OnCanceledListener onCanceledListener) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }

    @NonNull
    public Task continueWith(@NonNull Continuation continuation) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    @NonNull
    public Task continueWith(@NonNull Executor executor, @NonNull Continuation continuation) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    @NonNull
    public Task continueWithTask(@NonNull Continuation continuation) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    @NonNull
    public Task continueWithTask(@NonNull Executor executor, @NonNull Continuation continuation) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    @NonNull
    public Task onSuccessTask(@NonNull SuccessContinuation successContinuation) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }

    @NonNull
    public Task onSuccessTask(@NonNull Executor executor, @NonNull SuccessContinuation successContinuation) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }
}
