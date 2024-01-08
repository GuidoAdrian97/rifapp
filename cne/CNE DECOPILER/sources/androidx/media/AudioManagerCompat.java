package androidx.media;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

public final class AudioManagerCompat {
    private AudioManagerCompat() {
    }

    public static int requestAudioFocus(@NonNull AudioManager audioManager, @NonNull AudioFocusRequestCompat audioFocusRequestCompat) {
        if (audioManager == null) {
            throw new IllegalArgumentException("AudioManager must not be null");
        } else if (audioFocusRequestCompat == null) {
            throw new IllegalArgumentException("AudioFocusRequestCompat must not be null");
        } else if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.requestAudioFocus(audioManager, audioFocusRequestCompat.getAudioFocusRequest());
        } else {
            return audioManager.requestAudioFocus(audioFocusRequestCompat.getOnAudioFocusChangeListener(), audioFocusRequestCompat.getAudioAttributesCompat().getLegacyStreamType(), audioFocusRequestCompat.getFocusGain());
        }
    }

    public static int abandonAudioFocusRequest(@NonNull AudioManager audioManager, @NonNull AudioFocusRequestCompat audioFocusRequestCompat) {
        if (audioManager == null) {
            throw new IllegalArgumentException("AudioManager must not be null");
        } else if (audioFocusRequestCompat == null) {
            throw new IllegalArgumentException("AudioFocusRequestCompat must not be null");
        } else if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.abandonAudioFocusRequest(audioManager, audioFocusRequestCompat.getAudioFocusRequest());
        } else {
            return audioManager.abandonAudioFocus(audioFocusRequestCompat.getOnAudioFocusChangeListener());
        }
    }

    @IntRange
    public static int getStreamMaxVolume(@NonNull AudioManager audioManager, int i) {
        return audioManager.getStreamMaxVolume(i);
    }

    @IntRange
    public static int getStreamMinVolume(@NonNull AudioManager audioManager, int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getStreamMinVolume(audioManager, i);
        }
        return 0;
    }

    abstract class Api26Impl {
        @DoNotInline
        static int abandonAudioFocusRequest(AudioManager audioManager, AudioFocusRequest audioFocusRequest) {
            return audioManager.abandonAudioFocusRequest(audioFocusRequest);
        }

        @DoNotInline
        static int requestAudioFocus(AudioManager audioManager, AudioFocusRequest audioFocusRequest) {
            return audioManager.requestAudioFocus(audioFocusRequest);
        }
    }

    abstract class Api28Impl {
        @DoNotInline
        static int getStreamMinVolume(AudioManager audioManager, int i) {
            return audioManager.getStreamMinVolume(i);
        }
    }
}
