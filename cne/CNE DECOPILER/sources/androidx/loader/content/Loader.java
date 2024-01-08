package androidx.loader.content;

import android.content.Context;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader {
    private boolean mAbandoned = false;
    private boolean mContentChanged = false;
    private Context mContext;
    private int mId;
    private OnLoadCompleteListener mListener;
    private OnLoadCanceledListener mOnLoadCanceledListener;
    private boolean mProcessingChange = false;
    private boolean mReset = true;
    private boolean mStarted = false;

    public interface OnLoadCanceledListener {
        void onLoadCanceled(@NonNull Loader loader);
    }

    public interface OnLoadCompleteListener {
        void onLoadComplete(@NonNull Loader loader, @Nullable Object obj);
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onAbandon() {
    }

    /* access modifiers changed from: protected */
    @MainThread
    public boolean onCancelLoad() {
        return false;
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onForceLoad() {
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onReset() {
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onStartLoading() {
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void onStopLoading() {
    }

    public Loader(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    @MainThread
    public void deliverResult(@Nullable Object obj) {
        OnLoadCompleteListener onLoadCompleteListener = this.mListener;
        if (onLoadCompleteListener != null) {
            onLoadCompleteListener.onLoadComplete(this, obj);
        }
    }

    @MainThread
    public void deliverCancellation() {
        OnLoadCanceledListener onLoadCanceledListener = this.mOnLoadCanceledListener;
        if (onLoadCanceledListener != null) {
            onLoadCanceledListener.onLoadCanceled(this);
        }
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    @MainThread
    public void registerListener(int i, @NonNull OnLoadCompleteListener onLoadCompleteListener) {
        if (this.mListener == null) {
            this.mListener = onLoadCompleteListener;
            this.mId = i;
            return;
        }
        throw new IllegalStateException("There is already a listener registered");
    }

    @MainThread
    public void unregisterListener(@NonNull OnLoadCompleteListener onLoadCompleteListener) {
        OnLoadCompleteListener onLoadCompleteListener2 = this.mListener;
        if (onLoadCompleteListener2 == null) {
            throw new IllegalStateException("No listener register");
        } else if (onLoadCompleteListener2 == onLoadCompleteListener) {
            this.mListener = null;
        } else {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
    }

    @MainThread
    public void registerOnLoadCanceledListener(@NonNull OnLoadCanceledListener onLoadCanceledListener) {
        if (this.mOnLoadCanceledListener == null) {
            this.mOnLoadCanceledListener = onLoadCanceledListener;
            return;
        }
        throw new IllegalStateException("There is already a listener registered");
    }

    @MainThread
    public void unregisterOnLoadCanceledListener(@NonNull OnLoadCanceledListener onLoadCanceledListener) {
        OnLoadCanceledListener onLoadCanceledListener2 = this.mOnLoadCanceledListener;
        if (onLoadCanceledListener2 == null) {
            throw new IllegalStateException("No listener register");
        } else if (onLoadCanceledListener2 == onLoadCanceledListener) {
            this.mOnLoadCanceledListener = null;
        } else {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    @MainThread
    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    @MainThread
    public boolean cancelLoad() {
        return onCancelLoad();
    }

    @MainThread
    public void forceLoad() {
        onForceLoad();
    }

    @MainThread
    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    @MainThread
    public void abandon() {
        this.mAbandoned = true;
        onAbandon();
    }

    @MainThread
    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public boolean takeContentChanged() {
        boolean z = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= z;
        return z;
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if (this.mProcessingChange) {
            onContentChanged();
        }
    }

    @MainThread
    public void onContentChanged() {
        if (this.mStarted) {
            forceLoad();
        } else {
            this.mContentChanged = true;
        }
    }

    @NonNull
    public String dataToString(@Nullable Object obj) {
        StringBuilder sb = new StringBuilder(64);
        if (obj == null) {
            sb.append("null");
        } else {
            Class<?> cls = obj.getClass();
            sb.append(cls.getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(cls)));
            sb.append("}");
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        Class<?> cls = getClass();
        sb.append(cls.getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(cls)));
        sb.append(" id=");
        sb.append(this.mId);
        sb.append("}");
        return sb.toString();
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mListener=");
        printWriter.println(this.mListener);
        if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.mStarted);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.mContentChanged);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.mProcessingChange);
        }
        if (this.mAbandoned || this.mReset) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.mAbandoned);
            printWriter.print(" mReset=");
            printWriter.println(this.mReset);
        }
    }
}
