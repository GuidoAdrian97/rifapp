package androidx.core.util;

public class Pools$SynchronizedPool extends Pools$SimplePool {
    private final Object mLock = new Object();

    public Pools$SynchronizedPool(int i) {
        super(i);
    }

    public Object acquire() {
        Object acquire;
        synchronized (this.mLock) {
            acquire = super.acquire();
        }
        return acquire;
    }

    public boolean release(Object obj) {
        boolean release;
        synchronized (this.mLock) {
            release = super.release(obj);
        }
        return release;
    }
}
