package androidx.navigation;

import android.annotation.SuppressLint;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigator;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"TypeParameterUnusedInFormals"})
public class NavigatorProvider {
    private static final HashMap sAnnotationNames = new HashMap();
    private final HashMap mNavigators = new HashMap();

    private static boolean validateName(String str) {
        return str != null && !str.isEmpty();
    }

    static String getNameForNavigator(Class cls) {
        HashMap hashMap = sAnnotationNames;
        String str = (String) hashMap.get(cls);
        if (str == null) {
            Navigator.Name name = (Navigator.Name) cls.getAnnotation(Navigator.Name.class);
            str = name != null ? name.value() : null;
            if (validateName(str)) {
                hashMap.put(cls, str);
            } else {
                throw new IllegalArgumentException("No @Navigator.Name annotation found for " + cls.getSimpleName());
            }
        }
        return str;
    }

    @NonNull
    public final Navigator getNavigator(@NonNull Class cls) {
        return getNavigator(getNameForNavigator(cls));
    }

    @CallSuper
    @NonNull
    public Navigator getNavigator(@NonNull String str) {
        if (validateName(str)) {
            Navigator navigator = (Navigator) this.mNavigators.get(str);
            if (navigator != null) {
                return navigator;
            }
            throw new IllegalStateException("Could not find Navigator with name \"" + str + "\". You must call NavController.addNavigator() for each navigation type.");
        }
        throw new IllegalArgumentException("navigator name cannot be an empty string");
    }

    @Nullable
    public final Navigator addNavigator(@NonNull Navigator navigator) {
        return addNavigator(getNameForNavigator(navigator.getClass()), navigator);
    }

    @CallSuper
    @Nullable
    public Navigator addNavigator(@NonNull String str, @NonNull Navigator navigator) {
        if (validateName(str)) {
            return (Navigator) this.mNavigators.put(str, navigator);
        }
        throw new IllegalArgumentException("navigator name cannot be an empty string");
    }

    /* access modifiers changed from: package-private */
    public Map getNavigators() {
        return this.mNavigators;
    }
}
