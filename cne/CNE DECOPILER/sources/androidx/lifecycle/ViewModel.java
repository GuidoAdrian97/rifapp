package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewModel {
    private final Map mBagOfTags = new HashMap();
    private volatile boolean mCleared = false;

    /* access modifiers changed from: protected */
    public void onCleared() {
    }

    /* access modifiers changed from: package-private */
    public final void clear() {
        this.mCleared = true;
        Map map = this.mBagOfTags;
        if (map != null) {
            synchronized (map) {
                for (Object closeWithRuntimeException : this.mBagOfTags.values()) {
                    closeWithRuntimeException(closeWithRuntimeException);
                }
            }
        }
        onCleared();
    }

    /* access modifiers changed from: package-private */
    public Object setTagIfAbsent(String str, Object obj) {
        Object obj2;
        synchronized (this.mBagOfTags) {
            obj2 = this.mBagOfTags.get(str);
            if (obj2 == null) {
                this.mBagOfTags.put(str, obj);
            }
        }
        if (obj2 != null) {
            obj = obj2;
        }
        if (this.mCleared) {
            closeWithRuntimeException(obj);
        }
        return obj;
    }

    /* access modifiers changed from: package-private */
    public Object getTag(String str) {
        Object obj;
        Map map = this.mBagOfTags;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            obj = this.mBagOfTags.get(str);
        }
        return obj;
    }

    private static void closeWithRuntimeException(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
