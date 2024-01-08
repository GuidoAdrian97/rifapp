package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class SavedStateHandle {
    private static final Class[] ACCEPTABLE_CLASSES = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};
    private final Map mLiveDatas;
    final Map mRegular;
    private final SavedStateRegistry.SavedStateProvider mSavedStateProvider;
    final Map mSavedStateProviders;

    public SavedStateHandle(@NonNull Map map) {
        this.mSavedStateProviders = new HashMap();
        this.mLiveDatas = new HashMap();
        this.mSavedStateProvider = new SavedStateRegistry.SavedStateProvider() {
            public Bundle saveState() {
                for (Map.Entry entry : new HashMap(SavedStateHandle.this.mSavedStateProviders).entrySet()) {
                    SavedStateHandle.this.set((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
                }
                Set<String> keySet = SavedStateHandle.this.mRegular.keySet();
                ArrayList arrayList = new ArrayList(keySet.size());
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                for (String str : keySet) {
                    arrayList.add(str);
                    arrayList2.add(SavedStateHandle.this.mRegular.get(str));
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("keys", arrayList);
                bundle.putParcelableArrayList("values", arrayList2);
                return bundle;
            }
        };
        this.mRegular = new HashMap(map);
    }

    public SavedStateHandle() {
        this.mSavedStateProviders = new HashMap();
        this.mLiveDatas = new HashMap();
        this.mSavedStateProvider = new SavedStateRegistry.SavedStateProvider() {
            public Bundle saveState() {
                for (Map.Entry entry : new HashMap(SavedStateHandle.this.mSavedStateProviders).entrySet()) {
                    SavedStateHandle.this.set((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
                }
                Set<String> keySet = SavedStateHandle.this.mRegular.keySet();
                ArrayList arrayList = new ArrayList(keySet.size());
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                for (String str : keySet) {
                    arrayList.add(str);
                    arrayList2.add(SavedStateHandle.this.mRegular.get(str));
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("keys", arrayList);
                bundle.putParcelableArrayList("values", arrayList2);
                return bundle;
            }
        };
        this.mRegular = new HashMap();
    }

    static SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return new SavedStateHandle();
        }
        HashMap hashMap = new HashMap();
        if (bundle2 != null) {
            for (String next : bundle2.keySet()) {
                hashMap.put(next, bundle2.get(next));
            }
        }
        if (bundle == null) {
            return new SavedStateHandle(hashMap);
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            hashMap.put((String) parcelableArrayList.get(i), parcelableArrayList2.get(i));
        }
        return new SavedStateHandle(hashMap);
    }

    /* access modifiers changed from: package-private */
    public SavedStateRegistry.SavedStateProvider savedStateProvider() {
        return this.mSavedStateProvider;
    }

    @MainThread
    public boolean contains(@NonNull String str) {
        return this.mRegular.containsKey(str);
    }

    @MainThread
    @NonNull
    public MutableLiveData getLiveData(@NonNull String str) {
        return getLiveDataInternal(str, false, (Object) null);
    }

    @MainThread
    @NonNull
    public MutableLiveData getLiveData(@NonNull String str, @SuppressLint({"UnknownNullness"}) Object obj) {
        return getLiveDataInternal(str, true, obj);
    }

    private MutableLiveData getLiveDataInternal(String str, boolean z, Object obj) {
        SavingStateLiveData savingStateLiveData;
        MutableLiveData mutableLiveData = (MutableLiveData) this.mLiveDatas.get(str);
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        if (this.mRegular.containsKey(str)) {
            savingStateLiveData = new SavingStateLiveData(this, str, this.mRegular.get(str));
        } else if (z) {
            savingStateLiveData = new SavingStateLiveData(this, str, obj);
        } else {
            savingStateLiveData = new SavingStateLiveData(this, str);
        }
        this.mLiveDatas.put(str, savingStateLiveData);
        return savingStateLiveData;
    }

    @MainThread
    @NonNull
    public Set keys() {
        HashSet hashSet = new HashSet(this.mRegular.keySet());
        hashSet.addAll(this.mSavedStateProviders.keySet());
        hashSet.addAll(this.mLiveDatas.keySet());
        return hashSet;
    }

    @MainThread
    @Nullable
    public Object get(@NonNull String str) {
        return this.mRegular.get(str);
    }

    @MainThread
    public void set(@NonNull String str, @Nullable Object obj) {
        validateValue(obj);
        MutableLiveData mutableLiveData = (MutableLiveData) this.mLiveDatas.get(str);
        if (mutableLiveData != null) {
            mutableLiveData.setValue(obj);
        } else {
            this.mRegular.put(str, obj);
        }
    }

    private static void validateValue(Object obj) {
        if (obj != null) {
            Class[] clsArr = ACCEPTABLE_CLASSES;
            int length = clsArr.length;
            int i = 0;
            while (i < length) {
                if (!clsArr[i].isInstance(obj)) {
                    i++;
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("Can't put value with type " + obj.getClass() + " into saved state");
        }
    }

    @MainThread
    @Nullable
    public Object remove(@NonNull String str) {
        Object remove = this.mRegular.remove(str);
        SavingStateLiveData savingStateLiveData = (SavingStateLiveData) this.mLiveDatas.remove(str);
        if (savingStateLiveData != null) {
            savingStateLiveData.detach();
        }
        return remove;
    }

    @MainThread
    public void setSavedStateProvider(@NonNull String str, @NonNull SavedStateRegistry.SavedStateProvider savedStateProvider) {
        this.mSavedStateProviders.put(str, savedStateProvider);
    }

    @MainThread
    public void clearSavedStateProvider(@NonNull String str) {
        this.mSavedStateProviders.remove(str);
    }

    class SavingStateLiveData extends MutableLiveData {
        private SavedStateHandle mHandle;
        private String mKey;

        SavingStateLiveData(SavedStateHandle savedStateHandle, String str, Object obj) {
            super(obj);
            this.mKey = str;
            this.mHandle = savedStateHandle;
        }

        SavingStateLiveData(SavedStateHandle savedStateHandle, String str) {
            this.mKey = str;
            this.mHandle = savedStateHandle;
        }

        public void setValue(Object obj) {
            SavedStateHandle savedStateHandle = this.mHandle;
            if (savedStateHandle != null) {
                savedStateHandle.mRegular.put(this.mKey, obj);
            }
            super.setValue(obj);
        }

        /* access modifiers changed from: package-private */
        public void detach() {
            this.mHandle = null;
        }
    }
}
