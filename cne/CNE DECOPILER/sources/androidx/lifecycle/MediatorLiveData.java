package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.Map;

public class MediatorLiveData extends MutableLiveData {
    private SafeIterableMap mSources = new SafeIterableMap();

    @MainThread
    public void addSource(@NonNull LiveData liveData, @NonNull Observer observer) {
        Source source = new Source(liveData, observer);
        Source source2 = (Source) this.mSources.putIfAbsent(liveData, source);
        if (source2 != null && source2.mObserver != observer) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        } else if (source2 == null && hasActiveObservers()) {
            source.plug();
        }
    }

    @MainThread
    public void removeSource(@NonNull LiveData liveData) {
        Source source = (Source) this.mSources.remove(liveData);
        if (source != null) {
            source.unplug();
        }
    }

    /* access modifiers changed from: protected */
    public void onActive() {
        Iterator it = this.mSources.iterator();
        while (it.hasNext()) {
            ((Source) ((Map.Entry) it.next()).getValue()).plug();
        }
    }

    /* access modifiers changed from: protected */
    public void onInactive() {
        Iterator it = this.mSources.iterator();
        while (it.hasNext()) {
            ((Source) ((Map.Entry) it.next()).getValue()).unplug();
        }
    }

    class Source implements Observer {
        final LiveData mLiveData;
        final Observer mObserver;
        int mVersion = -1;

        Source(LiveData liveData, Observer observer) {
            this.mLiveData = liveData;
            this.mObserver = observer;
        }

        /* access modifiers changed from: package-private */
        public void plug() {
            this.mLiveData.observeForever(this);
        }

        /* access modifiers changed from: package-private */
        public void unplug() {
            this.mLiveData.removeObserver(this);
        }

        public void onChanged(Object obj) {
            if (this.mVersion != this.mLiveData.getVersion()) {
                this.mVersion = this.mLiveData.getVersion();
                this.mObserver.onChanged(obj);
            }
        }
    }
}
