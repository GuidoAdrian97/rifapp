package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;

public class Transformations {
    private Transformations() {
    }

    @MainThread
    @NonNull
    public static LiveData map(@NonNull LiveData liveData, @NonNull final Function function) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer() {
            public void onChanged(Object obj) {
                MediatorLiveData.this.setValue(function.apply(obj));
            }
        });
        return mediatorLiveData;
    }

    @MainThread
    @NonNull
    public static LiveData switchMap(@NonNull LiveData liveData, @NonNull final Function function) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer() {
            LiveData mSource;

            public void onChanged(Object obj) {
                LiveData liveData = (LiveData) Function.this.apply(obj);
                LiveData liveData2 = this.mSource;
                if (liveData2 != liveData) {
                    if (liveData2 != null) {
                        mediatorLiveData.removeSource(liveData2);
                    }
                    this.mSource = liveData;
                    if (liveData != null) {
                        mediatorLiveData.addSource(liveData, new Observer() {
                            public void onChanged(Object obj) {
                                mediatorLiveData.setValue(obj);
                            }
                        });
                    }
                }
            }
        });
        return mediatorLiveData;
    }

    @MainThread
    @NonNull
    public static LiveData distinctUntilChanged(@NonNull LiveData liveData) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer() {
            boolean mFirstTime = true;

            public void onChanged(Object obj) {
                Object value = MediatorLiveData.this.getValue();
                if (this.mFirstTime || ((value == null && obj != null) || (value != null && !value.equals(obj)))) {
                    this.mFirstTime = false;
                    MediatorLiveData.this.setValue(obj);
                }
            }
        });
        return mediatorLiveData;
    }
}
