package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.collection.SparseArrayCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager {
    static boolean DEBUG = false;
    private final LifecycleOwner mLifecycleOwner;
    private final LoaderViewModel mLoaderViewModel;

    public class LoaderInfo extends MutableLiveData implements Loader.OnLoadCompleteListener {
        private final Bundle mArgs;
        private final int mId;
        private LifecycleOwner mLifecycleOwner;
        private final Loader mLoader;
        private LoaderObserver mObserver;
        private Loader mPriorLoader;

        LoaderInfo(int i, Bundle bundle, Loader loader, Loader loader2) {
            this.mId = i;
            this.mArgs = bundle;
            this.mLoader = loader;
            this.mPriorLoader = loader2;
            loader.registerListener(i, this);
        }

        /* access modifiers changed from: package-private */
        public Loader getLoader() {
            return this.mLoader;
        }

        /* access modifiers changed from: protected */
        public void onActive() {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.mLoader.startLoading();
        }

        /* access modifiers changed from: protected */
        public void onInactive() {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.mLoader.stopLoading();
        }

        /* access modifiers changed from: package-private */
        public Loader setCallback(LifecycleOwner lifecycleOwner, LoaderManager.LoaderCallbacks loaderCallbacks) {
            LoaderObserver loaderObserver = new LoaderObserver(this.mLoader, loaderCallbacks);
            observe(lifecycleOwner, loaderObserver);
            LoaderObserver loaderObserver2 = this.mObserver;
            if (loaderObserver2 != null) {
                removeObserver(loaderObserver2);
            }
            this.mLifecycleOwner = lifecycleOwner;
            this.mObserver = loaderObserver;
            return this.mLoader;
        }

        /* access modifiers changed from: package-private */
        public void markForRedelivery() {
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            LoaderObserver loaderObserver = this.mObserver;
            if (lifecycleOwner != null && loaderObserver != null) {
                super.removeObserver(loaderObserver);
                observe(lifecycleOwner, loaderObserver);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isCallbackWaitingForData() {
            LoaderObserver loaderObserver;
            if (hasActiveObservers() && (loaderObserver = this.mObserver) != null && !loaderObserver.hasDeliveredData()) {
                return true;
            }
            return false;
        }

        public void removeObserver(Observer observer) {
            super.removeObserver(observer);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        /* access modifiers changed from: package-private */
        public Loader destroy(boolean z) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver loaderObserver = this.mObserver;
            if (loaderObserver != null) {
                removeObserver(loaderObserver);
                if (z) {
                    loaderObserver.reset();
                }
            }
            this.mLoader.unregisterListener(this);
            if ((loaderObserver == null || loaderObserver.hasDeliveredData()) && !z) {
                return this.mLoader;
            }
            this.mLoader.reset();
            return this.mPriorLoader;
        }

        public void onLoadComplete(Loader loader, Object obj) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(obj);
                return;
            }
            if (LoaderManagerImpl.DEBUG) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            postValue(obj);
        }

        public void setValue(Object obj) {
            super.setValue(obj);
            Loader loader = this.mPriorLoader;
            if (loader != null) {
                loader.reset();
                this.mPriorLoader = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.mId);
            sb.append(" : ");
            Class<?> cls = this.mLoader.getClass();
            sb.append(cls.getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(cls)));
            sb.append("}}");
            return sb.toString();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.mId);
            printWriter.print(" mArgs=");
            printWriter.println(this.mArgs);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.mLoader);
            Loader loader = this.mLoader;
            loader.dump(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.mObserver != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.mObserver);
                LoaderObserver loaderObserver = this.mObserver;
                loaderObserver.dump(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(getLoader().dataToString(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }
    }

    class LoaderObserver implements Observer {
        private final LoaderManager.LoaderCallbacks mCallback;
        private boolean mDeliveredData = false;
        private final Loader mLoader;

        LoaderObserver(Loader loader, LoaderManager.LoaderCallbacks loaderCallbacks) {
            this.mLoader = loader;
            this.mCallback = loaderCallbacks;
        }

        public void onChanged(Object obj) {
            if (LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.mLoader + ": " + this.mLoader.dataToString(obj));
            }
            this.mDeliveredData = true;
            this.mCallback.onLoadFinished(this.mLoader, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            if (this.mDeliveredData) {
                if (LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Resetting: " + this.mLoader);
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        public String toString() {
            return this.mCallback.toString();
        }

        public void dump(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.mDeliveredData);
        }
    }

    class LoaderViewModel extends ViewModel {
        private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() {
            public ViewModel create(Class cls) {
                return new LoaderViewModel();
            }
        };
        private boolean mCreatingLoader = false;
        private SparseArrayCompat mLoaders = new SparseArrayCompat();

        LoaderViewModel() {
        }

        static LoaderViewModel getInstance(ViewModelStore viewModelStore) {
            return (LoaderViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(LoaderViewModel.class);
        }

        /* access modifiers changed from: package-private */
        public void startCreatingLoader() {
            this.mCreatingLoader = true;
        }

        /* access modifiers changed from: package-private */
        public boolean isCreatingLoader() {
            return this.mCreatingLoader;
        }

        /* access modifiers changed from: package-private */
        public void finishCreatingLoader() {
            this.mCreatingLoader = false;
        }

        /* access modifiers changed from: package-private */
        public void putLoader(int i, LoaderInfo loaderInfo) {
            this.mLoaders.put(i, loaderInfo);
        }

        /* access modifiers changed from: package-private */
        public LoaderInfo getLoader(int i) {
            return (LoaderInfo) this.mLoaders.get(i);
        }

        /* access modifiers changed from: package-private */
        public void removeLoader(int i) {
            this.mLoaders.remove(i);
        }

        /* access modifiers changed from: package-private */
        public boolean hasRunningLoaders() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                if (((LoaderInfo) this.mLoaders.valueAt(i)).isCallbackWaitingForData()) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void markForRedelivery() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                ((LoaderInfo) this.mLoaders.valueAt(i)).markForRedelivery();
            }
        }

        /* access modifiers changed from: protected */
        public void onCleared() {
            super.onCleared();
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                ((LoaderInfo) this.mLoaders.valueAt(i)).destroy(true);
            }
            this.mLoaders.clear();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.mLoaders.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.mLoaders.size(); i++) {
                    LoaderInfo loaderInfo = (LoaderInfo) this.mLoaders.valueAt(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.mLoaders.keyAt(i));
                    printWriter.print(": ");
                    printWriter.println(loaderInfo.toString());
                    loaderInfo.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
    }

    LoaderManagerImpl(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mLoaderViewModel = LoaderViewModel.getInstance(viewModelStore);
    }

    /* JADX INFO: finally extract failed */
    private Loader createAndInstallLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loaderCallbacks, Loader loader) {
        try {
            this.mLoaderViewModel.startCreatingLoader();
            Loader onCreateLoader = loaderCallbacks.onCreateLoader(i, bundle);
            if (onCreateLoader != null) {
                if (onCreateLoader.getClass().isMemberClass()) {
                    if (!Modifier.isStatic(onCreateLoader.getClass().getModifiers())) {
                        throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + onCreateLoader);
                    }
                }
                LoaderInfo loaderInfo = new LoaderInfo(i, bundle, onCreateLoader, loader);
                if (DEBUG) {
                    Log.v("LoaderManager", "  Created new loader " + loaderInfo);
                }
                this.mLoaderViewModel.putLoader(i, loaderInfo);
                this.mLoaderViewModel.finishCreatingLoader();
                return loaderInfo.setCallback(this.mLifecycleOwner, loaderCallbacks);
            }
            throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
        } catch (Throwable th) {
            this.mLoaderViewModel.finishCreatingLoader();
            throw th;
        }
    }

    public Loader initLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loaderCallbacks) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
            if (DEBUG) {
                Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
            }
            if (loader == null) {
                return createAndInstallLoader(i, bundle, loaderCallbacks, (Loader) null);
            }
            if (DEBUG) {
                Log.v("LoaderManager", "  Re-using existing loader " + loader);
            }
            return loader.setCallback(this.mLifecycleOwner, loaderCallbacks);
        } else {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
    }

    public Loader restartLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loaderCallbacks) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            if (DEBUG) {
                Log.v("LoaderManager", "restartLoader in " + this + ": args=" + bundle);
            }
            LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
            Loader loader2 = null;
            if (loader != null) {
                loader2 = loader.destroy(false);
            }
            return createAndInstallLoader(i, bundle, loaderCallbacks, loader2);
        } else {
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
    }

    public void destroyLoader(int i) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            if (DEBUG) {
                Log.v("LoaderManager", "destroyLoader in " + this + " of " + i);
            }
            LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
            if (loader != null) {
                loader.destroy(true);
                this.mLoaderViewModel.removeLoader(i);
            }
        } else {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
    }

    public Loader getLoader(int i) {
        if (!this.mLoaderViewModel.isCreatingLoader()) {
            LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
            if (loader != null) {
                return loader.getLoader();
            }
            return null;
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Class<?> cls = this.mLifecycleOwner.getClass();
        sb.append(cls.getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(cls)));
        sb.append("}}");
        return sb.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLoaderViewModel.dump(str, fileDescriptor, printWriter, strArr);
    }

    public boolean hasRunningLoaders() {
        return this.mLoaderViewModel.hasRunningLoaders();
    }
}
