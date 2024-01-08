package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class SavedStateViewModelFactory extends ViewModelProvider.KeyedFactory {
    private static final Class[] ANDROID_VIEWMODEL_SIGNATURE;
    private static final Class[] VIEWMODEL_SIGNATURE;
    private final Application mApplication;
    private final Bundle mDefaultArgs;
    private final ViewModelProvider.Factory mFactory;
    private final Lifecycle mLifecycle;
    private final SavedStateRegistry mSavedStateRegistry;

    public SavedStateViewModelFactory(Application application, SavedStateRegistryOwner savedStateRegistryOwner, Bundle bundle) {
        ViewModelProvider.Factory factory;
        this.mSavedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
        this.mLifecycle = savedStateRegistryOwner.getLifecycle();
        this.mDefaultArgs = bundle;
        this.mApplication = application;
        if (application != null) {
            factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        } else {
            factory = ViewModelProvider.NewInstanceFactory.getInstance();
        }
        this.mFactory = factory;
    }

    public ViewModel create(String str, Class cls) {
        Constructor constructor;
        ViewModel viewModel;
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
        if (!isAssignableFrom || this.mApplication == null) {
            constructor = findMatchingConstructor(cls, VIEWMODEL_SIGNATURE);
        } else {
            constructor = findMatchingConstructor(cls, ANDROID_VIEWMODEL_SIGNATURE);
        }
        if (constructor == null) {
            return this.mFactory.create(cls);
        }
        SavedStateHandleController create = SavedStateHandleController.create(this.mSavedStateRegistry, this.mLifecycle, str, this.mDefaultArgs);
        if (isAssignableFrom) {
            try {
                Application application = this.mApplication;
                if (application != null) {
                    viewModel = (ViewModel) constructor.newInstance(new Object[]{application, create.getHandle()});
                    viewModel.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", create);
                    return viewModel;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("A " + cls + " cannot be instantiated.", e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("An exception happened in constructor of " + cls, e3.getCause());
            }
        }
        viewModel = (ViewModel) constructor.newInstance(new Object[]{create.getHandle()});
        viewModel.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", create);
        return viewModel;
    }

    public ViewModel create(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return create(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    static {
        Class<SavedStateHandle> cls = SavedStateHandle.class;
        ANDROID_VIEWMODEL_SIGNATURE = new Class[]{Application.class, cls};
        VIEWMODEL_SIGNATURE = new Class[]{cls};
    }

    private static Constructor findMatchingConstructor(Class cls, Class[] clsArr) {
        for (Constructor constructor : cls.getConstructors()) {
            if (Arrays.equals(clsArr, constructor.getParameterTypes())) {
                return constructor;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void onRequery(ViewModel viewModel) {
        SavedStateHandleController.attachHandleIfNeeded(viewModel, this.mSavedStateRegistry, this.mLifecycle);
    }
}
