package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

public class VectorEnabledTintResources extends Resources {
    private static boolean sCompatVectorFromResourcesEnabled = false;
    private final WeakReference mContextRef;

    public static boolean shouldBeUsed() {
        boolean isCompatVectorFromResourcesEnabled = isCompatVectorFromResourcesEnabled();
        return false;
    }

    public VectorEnabledTintResources(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mContextRef = new WeakReference(context);
    }

    public Drawable getDrawable(int i) {
        Context context = (Context) this.mContextRef.get();
        if (context != null) {
            return ResourceManagerInternal.get().onDrawableLoadedFromResources(context, this, i);
        }
        return super.getDrawable(i);
    }

    /* access modifiers changed from: package-private */
    public final Drawable superGetDrawable(int i) {
        return super.getDrawable(i);
    }

    public static void setCompatVectorFromResourcesEnabled(boolean z) {
        sCompatVectorFromResourcesEnabled = z;
    }

    public static boolean isCompatVectorFromResourcesEnabled() {
        return sCompatVectorFromResourcesEnabled;
    }
}
