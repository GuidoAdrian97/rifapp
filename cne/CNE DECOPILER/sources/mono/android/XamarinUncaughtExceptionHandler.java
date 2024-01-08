package mono.android;

import java.lang.Thread;

/* compiled from: Runtime */
final class XamarinUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    Thread.UncaughtExceptionHandler defaultHandler;

    public XamarinUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.defaultHandler = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        Runtime.propagateUncaughtException(thread, th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
