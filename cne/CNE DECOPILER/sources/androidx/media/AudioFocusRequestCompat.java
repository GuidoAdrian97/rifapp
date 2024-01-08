package androidx.media;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import androidx.media.AudioAttributesCompat;

public class AudioFocusRequestCompat {
    static final AudioAttributesCompat FOCUS_DEFAULT_ATTR = new AudioAttributesCompat.Builder().setUsage(1).build();
    private final AudioAttributesCompat mAudioAttributesCompat;
    private final Handler mFocusChangeHandler;
    private final int mFocusGain;
    private final Object mFrameworkAudioFocusRequest;
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    private final boolean mPauseOnDuck;

    AudioFocusRequestCompat(int i, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, Handler handler, AudioAttributesCompat audioAttributesCompat, boolean z) {
        this.mFocusGain = i;
        this.mFocusChangeHandler = handler;
        this.mAudioAttributesCompat = audioAttributesCompat;
        this.mPauseOnDuck = z;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26 || handler.getLooper() == Looper.getMainLooper()) {
            this.mOnAudioFocusChangeListener = onAudioFocusChangeListener;
        } else {
            this.mOnAudioFocusChangeListener = new OnAudioFocusChangeListenerHandlerCompat(onAudioFocusChangeListener, handler);
        }
        if (i2 >= 26) {
            this.mFrameworkAudioFocusRequest = Api26Impl.createInstance(i, getAudioAttributes(), z, this.mOnAudioFocusChangeListener, handler);
        } else {
            this.mFrameworkAudioFocusRequest = null;
        }
    }

    public int getFocusGain() {
        return this.mFocusGain;
    }

    @NonNull
    public AudioAttributesCompat getAudioAttributesCompat() {
        return this.mAudioAttributesCompat;
    }

    public boolean willPauseWhenDucked() {
        return this.mPauseOnDuck;
    }

    @NonNull
    public AudioManager.OnAudioFocusChangeListener getOnAudioFocusChangeListener() {
        return this.mOnAudioFocusChangeListener;
    }

    @NonNull
    public Handler getFocusChangeHandler() {
        return this.mFocusChangeHandler;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioFocusRequestCompat)) {
            return false;
        }
        AudioFocusRequestCompat audioFocusRequestCompat = (AudioFocusRequestCompat) obj;
        if (this.mFocusGain != audioFocusRequestCompat.mFocusGain || this.mPauseOnDuck != audioFocusRequestCompat.mPauseOnDuck || !ObjectsCompat.equals(this.mOnAudioFocusChangeListener, audioFocusRequestCompat.mOnAudioFocusChangeListener) || !ObjectsCompat.equals(this.mFocusChangeHandler, audioFocusRequestCompat.mFocusChangeHandler) || !ObjectsCompat.equals(this.mAudioAttributesCompat, audioFocusRequestCompat.mAudioAttributesCompat)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(this.mFocusGain), this.mOnAudioFocusChangeListener, this.mFocusChangeHandler, this.mAudioAttributesCompat, Boolean.valueOf(this.mPauseOnDuck));
    }

    /* access modifiers changed from: package-private */
    public AudioAttributes getAudioAttributes() {
        AudioAttributesCompat audioAttributesCompat = this.mAudioAttributesCompat;
        if (audioAttributesCompat != null) {
            return (AudioAttributes) audioAttributesCompat.unwrap();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public AudioFocusRequest getAudioFocusRequest() {
        return (AudioFocusRequest) this.mFrameworkAudioFocusRequest;
    }

    public final class Builder {
        private AudioAttributesCompat mAudioAttributesCompat = AudioFocusRequestCompat.FOCUS_DEFAULT_ATTR;
        private Handler mFocusChangeHandler;
        private int mFocusGain;
        private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
        private boolean mPauseOnDuck;

        private static boolean isValidFocusGain(int i) {
            return i == 1 || i == 2 || i == 3 || i == 4;
        }

        public Builder(int i) {
            setFocusGain(i);
        }

        public Builder(@NonNull AudioFocusRequestCompat audioFocusRequestCompat) {
            if (audioFocusRequestCompat != null) {
                this.mFocusGain = audioFocusRequestCompat.getFocusGain();
                this.mOnAudioFocusChangeListener = audioFocusRequestCompat.getOnAudioFocusChangeListener();
                this.mFocusChangeHandler = audioFocusRequestCompat.getFocusChangeHandler();
                this.mAudioAttributesCompat = audioFocusRequestCompat.getAudioAttributesCompat();
                this.mPauseOnDuck = audioFocusRequestCompat.willPauseWhenDucked();
                return;
            }
            throw new IllegalArgumentException("AudioFocusRequestCompat to copy must not be null");
        }

        @NonNull
        public Builder setFocusGain(int i) {
            if (isValidFocusGain(i)) {
                this.mFocusGain = i;
                return this;
            }
            throw new IllegalArgumentException("Illegal audio focus gain type " + i);
        }

        @NonNull
        public Builder setOnAudioFocusChangeListener(@NonNull AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
            return setOnAudioFocusChangeListener(onAudioFocusChangeListener, new Handler(Looper.getMainLooper()));
        }

        @NonNull
        public Builder setOnAudioFocusChangeListener(@NonNull AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, @NonNull Handler handler) {
            if (onAudioFocusChangeListener == null) {
                throw new IllegalArgumentException("OnAudioFocusChangeListener must not be null");
            } else if (handler != null) {
                this.mOnAudioFocusChangeListener = onAudioFocusChangeListener;
                this.mFocusChangeHandler = handler;
                return this;
            } else {
                throw new IllegalArgumentException("Handler must not be null");
            }
        }

        @NonNull
        public Builder setAudioAttributes(@NonNull AudioAttributesCompat audioAttributesCompat) {
            if (audioAttributesCompat != null) {
                this.mAudioAttributesCompat = audioAttributesCompat;
                return this;
            }
            throw new NullPointerException("Illegal null AudioAttributes");
        }

        @NonNull
        public Builder setWillPauseWhenDucked(boolean z) {
            this.mPauseOnDuck = z;
            return this;
        }

        public AudioFocusRequestCompat build() {
            if (this.mOnAudioFocusChangeListener != null) {
                return new AudioFocusRequestCompat(this.mFocusGain, this.mOnAudioFocusChangeListener, this.mFocusChangeHandler, this.mAudioAttributesCompat, this.mPauseOnDuck);
            }
            throw new IllegalStateException("Can't build an AudioFocusRequestCompat instance without a listener");
        }
    }

    class OnAudioFocusChangeListenerHandlerCompat implements Handler.Callback, AudioManager.OnAudioFocusChangeListener {
        private final Handler mHandler;
        private final AudioManager.OnAudioFocusChangeListener mListener;

        OnAudioFocusChangeListenerHandlerCompat(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, Handler handler) {
            this.mListener = onAudioFocusChangeListener;
            this.mHandler = new Handler(handler.getLooper(), this);
        }

        public void onAudioFocusChange(int i) {
            Handler handler = this.mHandler;
            handler.sendMessage(Message.obtain(handler, 2782386, i, 0));
        }

        public boolean handleMessage(Message message) {
            if (message.what != 2782386) {
                return false;
            }
            this.mListener.onAudioFocusChange(message.arg1);
            return true;
        }
    }

    abstract class Api26Impl {
        @DoNotInline
        static AudioFocusRequest createInstance(int i, AudioAttributes audioAttributes, boolean z, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, Handler handler) {
            return new AudioFocusRequest.Builder(i).setAudioAttributes(audioAttributes).setWillPauseWhenDucked(z).setOnAudioFocusChangeListener(onAudioFocusChangeListener, handler).build();
        }
    }
}
