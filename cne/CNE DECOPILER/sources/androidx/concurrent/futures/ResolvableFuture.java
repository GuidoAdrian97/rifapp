package androidx.concurrent.futures;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.common.util.concurrent.ListenableFuture;

@RestrictTo
public final class ResolvableFuture extends AbstractResolvableFuture {
    public static ResolvableFuture create() {
        return new ResolvableFuture();
    }

    public boolean set(@Nullable Object obj) {
        return super.set(obj);
    }

    public boolean setException(Throwable th) {
        return super.setException(th);
    }

    public boolean setFuture(ListenableFuture listenableFuture) {
        return super.setFuture(listenableFuture);
    }

    private ResolvableFuture() {
    }
}
