package androidx.core.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder implements Iterable {
    private final ArrayList mIntents = new ArrayList();
    private final Context mSourceContext;

    public interface SupportParentable {
        @Nullable
        Intent getSupportParentActivityIntent();
    }

    private TaskStackBuilder(Context context) {
        this.mSourceContext = context;
    }

    @NonNull
    public static TaskStackBuilder create(@NonNull Context context) {
        return new TaskStackBuilder(context);
    }

    @Deprecated
    public static TaskStackBuilder from(Context context) {
        return create(context);
    }

    @NonNull
    public TaskStackBuilder addNextIntent(@NonNull Intent intent) {
        this.mIntents.add(intent);
        return this;
    }

    @NonNull
    public TaskStackBuilder addNextIntentWithParentStack(@NonNull Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            component = intent.resolveActivity(this.mSourceContext.getPackageManager());
        }
        if (component != null) {
            addParentStack(component);
        }
        addNextIntent(intent);
        return this;
    }

    @NonNull
    public TaskStackBuilder addParentStack(@NonNull Activity activity) {
        Intent supportParentActivityIntent = activity instanceof SupportParentable ? ((SupportParentable) activity).getSupportParentActivityIntent() : null;
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = NavUtils.getParentActivityIntent(activity);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(this.mSourceContext.getPackageManager());
            }
            addParentStack(component);
            addNextIntent(supportParentActivityIntent);
        }
        return this;
    }

    @NonNull
    public TaskStackBuilder addParentStack(@NonNull Class cls) {
        return addParentStack(new ComponentName(this.mSourceContext, cls));
    }

    public TaskStackBuilder addParentStack(ComponentName componentName) {
        int size = this.mIntents.size();
        try {
            Intent parentActivityIntent = NavUtils.getParentActivityIntent(this.mSourceContext, componentName);
            while (parentActivityIntent != null) {
                this.mIntents.add(size, parentActivityIntent);
                parentActivityIntent = NavUtils.getParentActivityIntent(this.mSourceContext, parentActivityIntent.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    public int getIntentCount() {
        return this.mIntents.size();
    }

    @Deprecated
    public Intent getIntent(int i) {
        return editIntentAt(i);
    }

    @Nullable
    public Intent editIntentAt(int i) {
        return (Intent) this.mIntents.get(i);
    }

    @Deprecated
    public Iterator iterator() {
        return this.mIntents.iterator();
    }

    public void startActivities() {
        startActivities((Bundle) null);
    }

    public void startActivities(@Nullable Bundle bundle) {
        if (!this.mIntents.isEmpty()) {
            ArrayList arrayList = this.mIntents;
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            if (!ContextCompat.startActivities(this.mSourceContext, intentArr, bundle)) {
                Intent intent = new Intent(intentArr[intentArr.length - 1]);
                intent.addFlags(268435456);
                this.mSourceContext.startActivity(intent);
                return;
            }
            return;
        }
        throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }

    @Nullable
    public PendingIntent getPendingIntent(int i, int i2) {
        return getPendingIntent(i, i2, (Bundle) null);
    }

    @Nullable
    public PendingIntent getPendingIntent(int i, int i2, @Nullable Bundle bundle) {
        if (!this.mIntents.isEmpty()) {
            ArrayList arrayList = this.mIntents;
            Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
            intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
            return PendingIntent.getActivities(this.mSourceContext, i, intentArr, i2, bundle);
        }
        throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
    }

    @NonNull
    public Intent[] getIntents() {
        int size = this.mIntents.size();
        Intent[] intentArr = new Intent[size];
        if (size == 0) {
            return intentArr;
        }
        intentArr[0] = new Intent((Intent) this.mIntents.get(0)).addFlags(268484608);
        for (int i = 1; i < size; i++) {
            intentArr[i] = new Intent((Intent) this.mIntents.get(i));
        }
        return intentArr;
    }
}
