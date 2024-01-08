package androidx.concurrent.futures;

import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo
public enum DirectExecutor implements Executor {
    INSTANCE;

    public String toString() {
        return "DirectExecutor";
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
