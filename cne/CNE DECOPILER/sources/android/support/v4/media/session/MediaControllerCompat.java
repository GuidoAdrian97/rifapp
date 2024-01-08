package android.support.v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.BundleCompat;
import androidx.media.AudioAttributesCompat;
import androidx.media.R$id;
import androidx.versionedparcelable.ParcelUtils;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class MediaControllerCompat {
    private final MediaControllerImpl mImpl;
    private final ConcurrentHashMap mRegisteredCallbacks = new ConcurrentHashMap();
    private final MediaSessionCompat.Token mToken;

    interface MediaControllerImpl {
        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i);

        void adjustVolume(int i, int i2);

        boolean dispatchMediaButtonEvent(KeyEvent keyEvent);

        Bundle getExtras();

        long getFlags();

        Object getMediaController();

        MediaMetadataCompat getMetadata();

        String getPackageName();

        PlaybackInfo getPlaybackInfo();

        PlaybackStateCompat getPlaybackState();

        List getQueue();

        CharSequence getQueueTitle();

        int getRatingType();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        Bundle getSessionInfo();

        int getShuffleMode();

        TransportControls getTransportControls();

        boolean isCaptioningEnabled();

        boolean isSessionReady();

        void registerCallback(Callback callback, Handler handler);

        void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void setVolumeTo(int i, int i2);

        void unregisterCallback(Callback callback);
    }

    public static void setMediaController(@NonNull Activity activity, MediaControllerCompat mediaControllerCompat) {
        activity.getWindow().getDecorView().setTag(R$id.media_controller_compat_view_tag, mediaControllerCompat);
        MediaControllerImplApi21.setMediaController(activity, mediaControllerCompat);
    }

    public static MediaControllerCompat getMediaController(@NonNull Activity activity) {
        Object tag = activity.getWindow().getDecorView().getTag(R$id.media_controller_compat_view_tag);
        if (tag instanceof MediaControllerCompat) {
            return (MediaControllerCompat) tag;
        }
        return MediaControllerImplApi21.getMediaController(activity);
    }

    static void validateCustomAction(String str, Bundle bundle) {
        if (str != null) {
            if (!str.equals("android.support.v4.media.session.action.FOLLOW") && !str.equals("android.support.v4.media.session.action.UNFOLLOW")) {
                return;
            }
            if (bundle == null || !bundle.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE")) {
                throw new IllegalArgumentException("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action " + str + ".");
            }
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat != null) {
            MediaSessionCompat.Token sessionToken = mediaSessionCompat.getSessionToken();
            this.mToken = sessionToken;
            if (Build.VERSION.SDK_INT >= 29) {
                this.mImpl = new MediaControllerImplApi29(context, sessionToken);
            } else {
                this.mImpl = new MediaControllerImplApi21(context, sessionToken);
            }
        } else {
            throw new IllegalArgumentException("session must not be null");
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat.Token token) {
        if (token != null) {
            this.mToken = token;
            this.mImpl = new MediaControllerImplApi21(context, token);
            return;
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }

    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.mImpl.dispatchMediaButtonEvent(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }

    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }

    public List getQueue() {
        return this.mImpl.getQueue();
    }

    public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        this.mImpl.addQueueItem(mediaDescriptionCompat);
    }

    public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        this.mImpl.addQueueItem(mediaDescriptionCompat, i);
    }

    public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        this.mImpl.removeQueueItem(mediaDescriptionCompat);
    }

    @Deprecated
    public void removeQueueItemAt(int i) {
        MediaSessionCompat.QueueItem queueItem;
        List queue = getQueue();
        if (queue != null && i >= 0 && i < queue.size() && (queueItem = (MediaSessionCompat.QueueItem) queue.get(i)) != null) {
            removeQueueItem(queueItem.getDescription());
        }
    }

    public CharSequence getQueueTitle() {
        return this.mImpl.getQueueTitle();
    }

    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public int getRatingType() {
        return this.mImpl.getRatingType();
    }

    public boolean isCaptioningEnabled() {
        return this.mImpl.isCaptioningEnabled();
    }

    public int getRepeatMode() {
        return this.mImpl.getRepeatMode();
    }

    public int getShuffleMode() {
        return this.mImpl.getShuffleMode();
    }

    public long getFlags() {
        return this.mImpl.getFlags();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }

    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.mToken;
    }

    @Nullable
    @RestrictTo
    public VersionedParcelable getSession2Token() {
        return this.mToken.getSession2Token();
    }

    public void setVolumeTo(int i, int i2) {
        this.mImpl.setVolumeTo(i, i2);
    }

    public void adjustVolume(int i, int i2) {
        this.mImpl.adjustVolume(i, i2);
    }

    public void registerCallback(@NonNull Callback callback) {
        registerCallback(callback, (Handler) null);
    }

    public void registerCallback(@NonNull Callback callback, Handler handler) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        } else if (this.mRegisteredCallbacks.putIfAbsent(callback, Boolean.TRUE) != null) {
            Log.w("MediaControllerCompat", "the callback has already been registered");
        } else {
            if (handler == null) {
                handler = new Handler();
            }
            callback.setHandler(handler);
            this.mImpl.registerCallback(callback, handler);
        }
    }

    public void unregisterCallback(@NonNull Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        } else if (this.mRegisteredCallbacks.remove(callback) == null) {
            Log.w("MediaControllerCompat", "the callback has never been registered");
        } else {
            try {
                this.mImpl.unregisterCallback(callback);
            } finally {
                callback.setHandler((Handler) null);
            }
        }
    }

    public void sendCommand(@NonNull String str, @Nullable Bundle bundle, @Nullable ResultReceiver resultReceiver) {
        if (!TextUtils.isEmpty(str)) {
            this.mImpl.sendCommand(str, bundle, resultReceiver);
            return;
        }
        throw new IllegalArgumentException("command must neither be null nor empty");
    }

    public boolean isSessionReady() {
        return this.mImpl.isSessionReady();
    }

    public String getPackageName() {
        return this.mImpl.getPackageName();
    }

    @NonNull
    public Bundle getSessionInfo() {
        return this.mImpl.getSessionInfo();
    }

    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }

    public abstract class Callback implements IBinder.DeathRecipient {
        final MediaController.Callback mCallbackFwk = new MediaControllerCallbackApi21(this);
        MessageHandler mHandler;
        IMediaControllerCallback mIControllerCallback;

        public void onAudioInfoChanged(PlaybackInfo playbackInfo) {
        }

        public void onCaptioningEnabledChanged(boolean z) {
        }

        public void onExtrasChanged(Bundle bundle) {
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        }

        public void onQueueChanged(List list) {
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
        }

        public void onRepeatModeChanged(int i) {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String str, Bundle bundle) {
        }

        public void onSessionReady() {
        }

        public void onShuffleModeChanged(int i) {
        }

        @RestrictTo
        public IMediaControllerCallback getIControllerCallback() {
            return this.mIControllerCallback;
        }

        public void binderDied() {
            postToHandler(8, (Object) null, (Bundle) null);
        }

        /* access modifiers changed from: package-private */
        public void setHandler(Handler handler) {
            if (handler == null) {
                MessageHandler messageHandler = this.mHandler;
                if (messageHandler != null) {
                    messageHandler.mRegistered = false;
                    messageHandler.removeCallbacksAndMessages((Object) null);
                    this.mHandler = null;
                    return;
                }
                return;
            }
            MessageHandler messageHandler2 = new MessageHandler(handler.getLooper());
            this.mHandler = messageHandler2;
            messageHandler2.mRegistered = true;
        }

        /* access modifiers changed from: package-private */
        public void postToHandler(int i, Object obj, Bundle bundle) {
            MessageHandler messageHandler = this.mHandler;
            if (messageHandler != null) {
                Message obtainMessage = messageHandler.obtainMessage(i, obj);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }
        }

        class MediaControllerCallbackApi21 extends MediaController.Callback {
            private final WeakReference mCallback;

            MediaControllerCallbackApi21(Callback callback) {
                this.mCallback = new WeakReference(callback);
            }

            public void onSessionDestroyed() {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onSessionDestroyed();
                }
            }

            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                Callback callback = (Callback) this.mCallback.get();
                if (callback == null) {
                    return;
                }
                if (callback.mIControllerCallback == null || Build.VERSION.SDK_INT >= 23) {
                    callback.onSessionEvent(str, bundle);
                }
            }

            public void onPlaybackStateChanged(PlaybackState playbackState) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null && callback.mIControllerCallback == null) {
                    callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(playbackState));
                }
            }

            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(mediaMetadata));
                }
            }

            public void onQueueChanged(List list) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(list));
                }
            }

            public void onQueueTitleChanged(CharSequence charSequence) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onQueueTitleChanged(charSequence);
                }
            }

            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onExtrasChanged(bundle);
                }
            }

            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onAudioInfoChanged(new PlaybackInfo(playbackInfo.getPlaybackType(), AudioAttributesCompat.wrap(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume()));
                }
            }
        }

        abstract class StubCompat extends IMediaControllerCallback.Stub {
            private final WeakReference mCallback;

            public void onShuffleModeChangedRemoved(boolean z) {
            }

            StubCompat(Callback callback) {
                this.mCallback = new WeakReference(callback);
            }

            public void onEvent(String str, Bundle bundle) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(1, str, bundle);
                }
            }

            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(2, playbackStateCompat, (Bundle) null);
                }
            }

            public void onCaptioningEnabledChanged(boolean z) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(11, Boolean.valueOf(z), (Bundle) null);
                }
            }

            public void onRepeatModeChanged(int i) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(9, Integer.valueOf(i), (Bundle) null);
                }
            }

            public void onShuffleModeChanged(int i) {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(12, Integer.valueOf(i), (Bundle) null);
                }
            }

            public void onSessionReady() {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(13, (Object) null, (Bundle) null);
                }
            }
        }

        class MessageHandler extends Handler {
            boolean mRegistered = false;

            MessageHandler(Looper looper) {
                super(looper);
            }

            public void handleMessage(Message message) {
                if (this.mRegistered) {
                    switch (message.what) {
                        case 1:
                            Bundle data = message.getData();
                            MediaSessionCompat.ensureClassLoader(data);
                            Callback.this.onSessionEvent((String) message.obj, data);
                            return;
                        case 2:
                            Callback.this.onPlaybackStateChanged((PlaybackStateCompat) message.obj);
                            return;
                        case 3:
                            Callback.this.onMetadataChanged((MediaMetadataCompat) message.obj);
                            return;
                        case 4:
                            Callback.this.onAudioInfoChanged((PlaybackInfo) message.obj);
                            return;
                        case 5:
                            Callback.this.onQueueChanged((List) message.obj);
                            return;
                        case 6:
                            Callback.this.onQueueTitleChanged((CharSequence) message.obj);
                            return;
                        case 7:
                            Bundle bundle = (Bundle) message.obj;
                            MediaSessionCompat.ensureClassLoader(bundle);
                            Callback.this.onExtrasChanged(bundle);
                            return;
                        case 8:
                            Callback.this.onSessionDestroyed();
                            return;
                        case 9:
                            Callback.this.onRepeatModeChanged(((Integer) message.obj).intValue());
                            return;
                        case 11:
                            Callback.this.onCaptioningEnabledChanged(((Boolean) message.obj).booleanValue());
                            return;
                        case 12:
                            Callback.this.onShuffleModeChanged(((Integer) message.obj).intValue());
                            return;
                        case 13:
                            Callback.this.onSessionReady();
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    public abstract class TransportControls {
        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String str, Bundle bundle);

        public abstract void playFromSearch(String str, Bundle bundle);

        public abstract void playFromUri(Uri uri, Bundle bundle);

        public abstract void prepare();

        public abstract void prepareFromMediaId(String str, Bundle bundle);

        public abstract void prepareFromSearch(String str, Bundle bundle);

        public abstract void prepareFromUri(Uri uri, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long j);

        public abstract void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle);

        public abstract void sendCustomAction(String str, Bundle bundle);

        public abstract void setCaptioningEnabled(boolean z);

        public void setPlaybackSpeed(float f) {
        }

        public abstract void setRating(RatingCompat ratingCompat);

        public abstract void setRating(RatingCompat ratingCompat, Bundle bundle);

        public abstract void setRepeatMode(int i);

        public abstract void setShuffleMode(int i);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long j);

        public abstract void stop();

        TransportControls() {
        }
    }

    public final class PlaybackInfo {
        private final AudioAttributesCompat mAudioAttrsCompat;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int i, int i2, int i3, int i4, int i5) {
            this(i, new AudioAttributesCompat.Builder().setLegacyStreamType(i2).build(), i3, i4, i5);
        }

        PlaybackInfo(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            this.mPlaybackType = i;
            this.mAudioAttrsCompat = audioAttributesCompat;
            this.mVolumeControl = i2;
            this.mMaxVolume = i3;
            this.mCurrentVolume = i4;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        @Deprecated
        public int getAudioStream() {
            return this.mAudioAttrsCompat.getLegacyStreamType();
        }

        @NonNull
        public AudioAttributesCompat getAudioAttributes() {
            return this.mAudioAttrsCompat;
        }

        public int getVolumeControl() {
            return this.mVolumeControl;
        }

        public int getMaxVolume() {
            return this.mMaxVolume;
        }

        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }
    }

    class MediaControllerImplBase implements MediaControllerImpl {
        private IMediaSession mBinder;
        private Bundle mSessionInfo;
        private TransportControls mTransportControls;

        public Object getMediaController() {
            return null;
        }

        public boolean isSessionReady() {
            return true;
        }

        MediaControllerImplBase(MediaSessionCompat.Token token) {
            this.mBinder = IMediaSession.Stub.asInterface((IBinder) token.getToken());
        }

        public void registerCallback(Callback callback, Handler handler) {
            if (callback != null) {
                try {
                    this.mBinder.asBinder().linkToDeath(callback, 0);
                    this.mBinder.registerCallbackListener(callback.mIControllerCallback);
                    callback.postToHandler(13, (Object) null, (Bundle) null);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    callback.postToHandler(8, (Object) null, (Bundle) null);
                }
            } else {
                throw new IllegalArgumentException("callback may not be null.");
            }
        }

        public void unregisterCallback(Callback callback) {
            if (callback != null) {
                try {
                    this.mBinder.unregisterCallbackListener(callback.mIControllerCallback);
                    this.mBinder.asBinder().unlinkToDeath(callback, 0);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
                }
            } else {
                throw new IllegalArgumentException("callback may not be null.");
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            if (keyEvent != null) {
                try {
                    this.mBinder.sendMediaButton(keyEvent);
                    return false;
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e);
                    return false;
                }
            } else {
                throw new IllegalArgumentException("event may not be null.");
            }
        }

        public TransportControls getTransportControls() {
            if (this.mTransportControls == null) {
                this.mTransportControls = new TransportControlsBase(this.mBinder);
            }
            return this.mTransportControls;
        }

        public PlaybackStateCompat getPlaybackState() {
            try {
                return this.mBinder.getPlaybackState();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                return null;
            }
        }

        public MediaMetadataCompat getMetadata() {
            try {
                return this.mBinder.getMetadata();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getMetadata.", e);
                return null;
            }
        }

        public List getQueue() {
            try {
                return this.mBinder.getQueue();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getQueue.", e);
                return null;
            }
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            try {
                if ((this.mBinder.getFlags() & 4) != 0) {
                    this.mBinder.addQueueItem(mediaDescriptionCompat);
                    return;
                }
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in addQueueItem.", e);
            }
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            try {
                if ((this.mBinder.getFlags() & 4) != 0) {
                    this.mBinder.addQueueItemAt(mediaDescriptionCompat, i);
                    return;
                }
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in addQueueItemAt.", e);
            }
        }

        public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            try {
                if ((this.mBinder.getFlags() & 4) != 0) {
                    this.mBinder.removeQueueItem(mediaDescriptionCompat);
                    return;
                }
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in removeQueueItem.", e);
            }
        }

        public CharSequence getQueueTitle() {
            try {
                return this.mBinder.getQueueTitle();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getQueueTitle.", e);
                return null;
            }
        }

        public Bundle getExtras() {
            try {
                return this.mBinder.getExtras();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getExtras.", e);
                return null;
            }
        }

        public int getRatingType() {
            try {
                return this.mBinder.getRatingType();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getRatingType.", e);
                return 0;
            }
        }

        public boolean isCaptioningEnabled() {
            try {
                return this.mBinder.isCaptioningEnabled();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", e);
                return false;
            }
        }

        public int getRepeatMode() {
            try {
                return this.mBinder.getRepeatMode();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", e);
                return -1;
            }
        }

        public int getShuffleMode() {
            try {
                return this.mBinder.getShuffleMode();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", e);
                return -1;
            }
        }

        public long getFlags() {
            try {
                return this.mBinder.getFlags();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getFlags.", e);
                return 0;
            }
        }

        public PlaybackInfo getPlaybackInfo() {
            try {
                ParcelableVolumeInfo volumeAttributes = this.mBinder.getVolumeAttributes();
                return new PlaybackInfo(volumeAttributes.volumeType, volumeAttributes.audioStream, volumeAttributes.controlType, volumeAttributes.maxVolume, volumeAttributes.currentVolume);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo.", e);
                return null;
            }
        }

        public PendingIntent getSessionActivity() {
            try {
                return this.mBinder.getLaunchPendingIntent();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", e);
                return null;
            }
        }

        public void setVolumeTo(int i, int i2) {
            try {
                this.mBinder.setVolumeTo(i, i2, (String) null);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in setVolumeTo.", e);
            }
        }

        public void adjustVolume(int i, int i2) {
            try {
                this.mBinder.adjustVolume(i, i2, (String) null);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in adjustVolume.", e);
            }
        }

        public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper;
            try {
                IMediaSession iMediaSession = this.mBinder;
                if (resultReceiver == null) {
                    resultReceiverWrapper = null;
                } else {
                    resultReceiverWrapper = new MediaSessionCompat.ResultReceiverWrapper(resultReceiver);
                }
                iMediaSession.sendCommand(str, bundle, resultReceiverWrapper);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in sendCommand.", e);
            }
        }

        public String getPackageName() {
            try {
                return this.mBinder.getPackageName();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getPackageName.", e);
                return null;
            }
        }

        public Bundle getSessionInfo() {
            try {
                this.mSessionInfo = this.mBinder.getSessionInfo();
            } catch (RemoteException e) {
                Log.d("MediaControllerCompat", "Dead object in getSessionInfo.", e);
            }
            Bundle unparcelWithClassLoader = MediaSessionCompat.unparcelWithClassLoader(this.mSessionInfo);
            this.mSessionInfo = unparcelWithClassLoader;
            return unparcelWithClassLoader == null ? Bundle.EMPTY : new Bundle(this.mSessionInfo);
        }
    }

    class TransportControlsBase extends TransportControls {
        private IMediaSession mBinder;

        public TransportControlsBase(IMediaSession iMediaSession) {
            this.mBinder = iMediaSession;
        }

        public void prepare() {
            try {
                this.mBinder.prepare();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in prepare.", e);
            }
        }

        public void prepareFromMediaId(String str, Bundle bundle) {
            try {
                this.mBinder.prepareFromMediaId(str, bundle);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromMediaId.", e);
            }
        }

        public void prepareFromSearch(String str, Bundle bundle) {
            try {
                this.mBinder.prepareFromSearch(str, bundle);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromSearch.", e);
            }
        }

        public void prepareFromUri(Uri uri, Bundle bundle) {
            try {
                this.mBinder.prepareFromUri(uri, bundle);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromUri.", e);
            }
        }

        public void play() {
            try {
                this.mBinder.play();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in play.", e);
            }
        }

        public void playFromMediaId(String str, Bundle bundle) {
            try {
                this.mBinder.playFromMediaId(str, bundle);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in playFromMediaId.", e);
            }
        }

        public void playFromSearch(String str, Bundle bundle) {
            try {
                this.mBinder.playFromSearch(str, bundle);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in playFromSearch.", e);
            }
        }

        public void playFromUri(Uri uri, Bundle bundle) {
            try {
                this.mBinder.playFromUri(uri, bundle);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in playFromUri.", e);
            }
        }

        public void skipToQueueItem(long j) {
            try {
                this.mBinder.skipToQueueItem(j);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in skipToQueueItem.", e);
            }
        }

        public void pause() {
            try {
                this.mBinder.pause();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in pause.", e);
            }
        }

        public void stop() {
            try {
                this.mBinder.stop();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in stop.", e);
            }
        }

        public void seekTo(long j) {
            try {
                this.mBinder.seekTo(j);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in seekTo.", e);
            }
        }

        public void fastForward() {
            try {
                this.mBinder.fastForward();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in fastForward.", e);
            }
        }

        public void skipToNext() {
            try {
                this.mBinder.next();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in skipToNext.", e);
            }
        }

        public void rewind() {
            try {
                this.mBinder.rewind();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in rewind.", e);
            }
        }

        public void skipToPrevious() {
            try {
                this.mBinder.previous();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in skipToPrevious.", e);
            }
        }

        public void setRating(RatingCompat ratingCompat) {
            try {
                this.mBinder.rate(ratingCompat);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in setRating.", e);
            }
        }

        public void setRating(RatingCompat ratingCompat, Bundle bundle) {
            try {
                this.mBinder.rateWithExtras(ratingCompat, bundle);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in setRating.", e);
            }
        }

        public void setPlaybackSpeed(float f) {
            if (f != 0.0f) {
                try {
                    this.mBinder.setPlaybackSpeed(f);
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in setPlaybackSpeed.", e);
                }
            } else {
                throw new IllegalArgumentException("speed must not be zero");
            }
        }

        public void setCaptioningEnabled(boolean z) {
            try {
                this.mBinder.setCaptioningEnabled(z);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in setCaptioningEnabled.", e);
            }
        }

        public void setRepeatMode(int i) {
            try {
                this.mBinder.setRepeatMode(i);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in setRepeatMode.", e);
            }
        }

        public void setShuffleMode(int i) {
            try {
                this.mBinder.setShuffleMode(i);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in setShuffleMode.", e);
            }
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle) {
            sendCustomAction(customAction.getAction(), bundle);
        }

        public void sendCustomAction(String str, Bundle bundle) {
            MediaControllerCompat.validateCustomAction(str, bundle);
            try {
                this.mBinder.sendCustomAction(str, bundle);
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in sendCustomAction.", e);
            }
        }
    }

    @RequiresApi
    class MediaControllerImplApi21 implements MediaControllerImpl {
        private HashMap mCallbackMap = new HashMap();
        protected final MediaController mControllerFwk;
        final Object mLock = new Object();
        private final List mPendingCallbacks = new ArrayList();
        protected Bundle mSessionInfo;
        final MediaSessionCompat.Token mSessionToken;

        MediaControllerImplApi21(Context context, MediaSessionCompat.Token token) {
            this.mSessionToken = token;
            this.mControllerFwk = new MediaController(context, (MediaSession.Token) token.getToken());
            if (token.getExtraBinder() == null) {
                requestExtraBinder();
            }
        }

        public final void registerCallback(Callback callback, Handler handler) {
            this.mControllerFwk.registerCallback(callback.mCallbackFwk, handler);
            synchronized (this.mLock) {
                if (this.mSessionToken.getExtraBinder() != null) {
                    ExtraCallback extraCallback = new ExtraCallback(callback);
                    this.mCallbackMap.put(callback, extraCallback);
                    callback.mIControllerCallback = extraCallback;
                    try {
                        this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback);
                        callback.postToHandler(13, (Object) null, (Bundle) null);
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                } else {
                    callback.mIControllerCallback = null;
                    this.mPendingCallbacks.add(callback);
                }
            }
        }

        public final void unregisterCallback(Callback callback) {
            this.mControllerFwk.unregisterCallback(callback.mCallbackFwk);
            synchronized (this.mLock) {
                if (this.mSessionToken.getExtraBinder() != null) {
                    try {
                        ExtraCallback extraCallback = (ExtraCallback) this.mCallbackMap.remove(callback);
                        if (extraCallback != null) {
                            callback.mIControllerCallback = null;
                            this.mSessionToken.getExtraBinder().unregisterCallbackListener(extraCallback);
                        }
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
                    }
                } else {
                    this.mPendingCallbacks.remove(callback);
                }
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            return this.mControllerFwk.dispatchMediaButtonEvent(keyEvent);
        }

        public TransportControls getTransportControls() {
            MediaController.TransportControls transportControls = this.mControllerFwk.getTransportControls();
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                return new TransportControlsApi29(transportControls);
            }
            if (i >= 24) {
                return new TransportControlsApi24(transportControls);
            }
            if (i >= 23) {
                return new TransportControlsApi23(transportControls);
            }
            return new TransportControlsApi21(transportControls);
        }

        public PlaybackStateCompat getPlaybackState() {
            if (this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getPlaybackState();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                }
            }
            PlaybackState playbackState = this.mControllerFwk.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.fromPlaybackState(playbackState);
            }
            return null;
        }

        public MediaMetadataCompat getMetadata() {
            MediaMetadata metadata = this.mControllerFwk.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.fromMediaMetadata(metadata);
            }
            return null;
        }

        public List getQueue() {
            List<MediaSession.QueueItem> queue = this.mControllerFwk.getQueue();
            if (queue != null) {
                return MediaSessionCompat.QueueItem.fromQueueItemList(queue);
            }
            return null;
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((getFlags() & 4) != 0) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediaDescriptionCompat);
                sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM", bundle, (ResultReceiver) null);
                return;
            }
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if ((getFlags() & 4) != 0) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediaDescriptionCompat);
                bundle.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", i);
                sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", bundle, (ResultReceiver) null);
                return;
            }
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }

        public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((getFlags() & 4) != 0) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediaDescriptionCompat);
                sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", bundle, (ResultReceiver) null);
                return;
            }
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }

        public CharSequence getQueueTitle() {
            return this.mControllerFwk.getQueueTitle();
        }

        public Bundle getExtras() {
            return this.mControllerFwk.getExtras();
        }

        public int getRatingType() {
            if (Build.VERSION.SDK_INT < 22 && this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getRatingType();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in getRatingType.", e);
                }
            }
            return this.mControllerFwk.getRatingType();
        }

        public boolean isCaptioningEnabled() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return false;
            }
            try {
                return this.mSessionToken.getExtraBinder().isCaptioningEnabled();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", e);
                return false;
            }
        }

        public int getRepeatMode() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return -1;
            }
            try {
                return this.mSessionToken.getExtraBinder().getRepeatMode();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", e);
                return -1;
            }
        }

        public int getShuffleMode() {
            if (this.mSessionToken.getExtraBinder() == null) {
                return -1;
            }
            try {
                return this.mSessionToken.getExtraBinder().getShuffleMode();
            } catch (RemoteException e) {
                Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", e);
                return -1;
            }
        }

        public long getFlags() {
            return this.mControllerFwk.getFlags();
        }

        public PlaybackInfo getPlaybackInfo() {
            MediaController.PlaybackInfo playbackInfo = this.mControllerFwk.getPlaybackInfo();
            if (playbackInfo != null) {
                return new PlaybackInfo(playbackInfo.getPlaybackType(), AudioAttributesCompat.wrap(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
            }
            return null;
        }

        public PendingIntent getSessionActivity() {
            return this.mControllerFwk.getSessionActivity();
        }

        public void setVolumeTo(int i, int i2) {
            this.mControllerFwk.setVolumeTo(i, i2);
        }

        public void adjustVolume(int i, int i2) {
            this.mControllerFwk.adjustVolume(i, i2);
        }

        public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.mControllerFwk.sendCommand(str, bundle, resultReceiver);
        }

        public boolean isSessionReady() {
            return this.mSessionToken.getExtraBinder() != null;
        }

        public String getPackageName() {
            return this.mControllerFwk.getPackageName();
        }

        public Bundle getSessionInfo() {
            if (this.mSessionInfo != null) {
                return new Bundle(this.mSessionInfo);
            }
            if (this.mSessionToken.getExtraBinder() != null) {
                try {
                    this.mSessionInfo = this.mSessionToken.getExtraBinder().getSessionInfo();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in getSessionInfo.", e);
                    this.mSessionInfo = Bundle.EMPTY;
                }
            }
            Bundle unparcelWithClassLoader = MediaSessionCompat.unparcelWithClassLoader(this.mSessionInfo);
            this.mSessionInfo = unparcelWithClassLoader;
            return unparcelWithClassLoader == null ? Bundle.EMPTY : new Bundle(this.mSessionInfo);
        }

        public Object getMediaController() {
            return this.mControllerFwk;
        }

        private void requestExtraBinder() {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", (Bundle) null, new ExtraBinderRequestResultReceiver(this));
        }

        /* access modifiers changed from: package-private */
        public void processPendingCallbacksLocked() {
            if (this.mSessionToken.getExtraBinder() != null) {
                for (Callback callback : this.mPendingCallbacks) {
                    ExtraCallback extraCallback = new ExtraCallback(callback);
                    this.mCallbackMap.put(callback, extraCallback);
                    callback.mIControllerCallback = extraCallback;
                    try {
                        this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback);
                        callback.postToHandler(13, (Object) null, (Bundle) null);
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                }
                this.mPendingCallbacks.clear();
            }
        }

        static void setMediaController(Activity activity, MediaControllerCompat mediaControllerCompat) {
            activity.setMediaController(mediaControllerCompat != null ? new MediaController(activity, (MediaSession.Token) mediaControllerCompat.getSessionToken().getToken()) : null);
        }

        @Nullable
        static MediaControllerCompat getMediaController(@NonNull Activity activity) {
            MediaController mediaController = activity.getMediaController();
            if (mediaController == null) {
                return null;
            }
            return new MediaControllerCompat((Context) activity, MediaSessionCompat.Token.fromToken(mediaController.getSessionToken()));
        }

        class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference mMediaControllerImpl;

            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21) {
                super((Handler) null);
                this.mMediaControllerImpl = new WeakReference(mediaControllerImplApi21);
            }

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = (MediaControllerImplApi21) this.mMediaControllerImpl.get();
                if (mediaControllerImplApi21 != null && bundle != null) {
                    synchronized (mediaControllerImplApi21.mLock) {
                        mediaControllerImplApi21.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER")));
                        mediaControllerImplApi21.mSessionToken.setSession2Token(ParcelUtils.getVersionedParcelable(bundle, "android.support.v4.media.session.SESSION_TOKEN2"));
                        mediaControllerImplApi21.processPendingCallbacksLocked();
                    }
                }
            }
        }

        class ExtraCallback extends Callback.StubCompat {
            ExtraCallback(Callback callback) {
                super(callback);
            }

            public void onSessionDestroyed() {
                throw new AssertionError();
            }

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
                throw new AssertionError();
            }

            public void onQueueChanged(List list) {
                throw new AssertionError();
            }

            public void onQueueTitleChanged(CharSequence charSequence) {
                throw new AssertionError();
            }

            public void onExtrasChanged(Bundle bundle) {
                throw new AssertionError();
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) {
                throw new AssertionError();
            }
        }
    }

    class MediaControllerImplApi29 extends MediaControllerImplApi21 {
        MediaControllerImplApi29(Context context, MediaSessionCompat.Token token) {
            super(context, token);
        }

        public Bundle getSessionInfo() {
            if (this.mSessionInfo != null) {
                return new Bundle(this.mSessionInfo);
            }
            Bundle sessionInfo = this.mControllerFwk.getSessionInfo();
            this.mSessionInfo = sessionInfo;
            Bundle unparcelWithClassLoader = MediaSessionCompat.unparcelWithClassLoader(sessionInfo);
            this.mSessionInfo = unparcelWithClassLoader;
            return unparcelWithClassLoader == null ? Bundle.EMPTY : new Bundle(this.mSessionInfo);
        }
    }

    @RequiresApi
    class TransportControlsApi21 extends TransportControls {
        protected final MediaController.TransportControls mControlsFwk;

        TransportControlsApi21(MediaController.TransportControls transportControls) {
            this.mControlsFwk = transportControls;
        }

        public void prepare() {
            sendCustomAction("android.support.v4.media.session.action.PREPARE", (Bundle) null);
        }

        public void prepareFromMediaId(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", str);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", bundle2);
        }

        public void prepareFromSearch(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", str);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", bundle2);
        }

        public void prepareFromUri(Uri uri, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", bundle2);
        }

        public void play() {
            this.mControlsFwk.play();
        }

        public void pause() {
            this.mControlsFwk.pause();
        }

        public void stop() {
            this.mControlsFwk.stop();
        }

        public void seekTo(long j) {
            this.mControlsFwk.seekTo(j);
        }

        public void fastForward() {
            this.mControlsFwk.fastForward();
        }

        public void rewind() {
            this.mControlsFwk.rewind();
        }

        public void skipToNext() {
            this.mControlsFwk.skipToNext();
        }

        public void skipToPrevious() {
            this.mControlsFwk.skipToPrevious();
        }

        public void setRating(RatingCompat ratingCompat) {
            this.mControlsFwk.setRating(ratingCompat != null ? (Rating) ratingCompat.getRating() : null);
        }

        public void setRating(RatingCompat ratingCompat, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_RATING", ratingCompat);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.SET_RATING", bundle2);
        }

        public void setPlaybackSpeed(float f) {
            if (f != 0.0f) {
                Bundle bundle = new Bundle();
                bundle.putFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", f);
                sendCustomAction("android.support.v4.media.session.action.SET_PLAYBACK_SPEED", bundle);
                return;
            }
            throw new IllegalArgumentException("speed must not be zero");
        }

        public void setCaptioningEnabled(boolean z) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED", z);
            sendCustomAction("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED", bundle);
        }

        public void setRepeatMode(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", bundle);
        }

        public void setShuffleMode(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE", bundle);
        }

        public void playFromMediaId(String str, Bundle bundle) {
            this.mControlsFwk.playFromMediaId(str, bundle);
        }

        public void playFromSearch(String str, Bundle bundle) {
            this.mControlsFwk.playFromSearch(str, bundle);
        }

        public void playFromUri(Uri uri, Bundle bundle) {
            if (uri == null || Uri.EMPTY.equals(uri)) {
                throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", bundle2);
        }

        public void skipToQueueItem(long j) {
            this.mControlsFwk.skipToQueueItem(j);
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle) {
            MediaControllerCompat.validateCustomAction(customAction.getAction(), bundle);
            this.mControlsFwk.sendCustomAction(customAction.getAction(), bundle);
        }

        public void sendCustomAction(String str, Bundle bundle) {
            MediaControllerCompat.validateCustomAction(str, bundle);
            this.mControlsFwk.sendCustomAction(str, bundle);
        }
    }

    @RequiresApi
    class TransportControlsApi23 extends TransportControlsApi21 {
        TransportControlsApi23(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        public void playFromUri(Uri uri, Bundle bundle) {
            this.mControlsFwk.playFromUri(uri, bundle);
        }
    }

    @RequiresApi
    class TransportControlsApi24 extends TransportControlsApi23 {
        TransportControlsApi24(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        public void prepare() {
            this.mControlsFwk.prepare();
        }

        public void prepareFromMediaId(String str, Bundle bundle) {
            this.mControlsFwk.prepareFromMediaId(str, bundle);
        }

        public void prepareFromSearch(String str, Bundle bundle) {
            this.mControlsFwk.prepareFromSearch(str, bundle);
        }

        public void prepareFromUri(Uri uri, Bundle bundle) {
            this.mControlsFwk.prepareFromUri(uri, bundle);
        }
    }

    class TransportControlsApi29 extends TransportControlsApi24 {
        TransportControlsApi29(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        public void setPlaybackSpeed(float f) {
            if (f != 0.0f) {
                this.mControlsFwk.setPlaybackSpeed(f);
                return;
            }
            throw new IllegalArgumentException("speed must not be zero");
        }
    }
}
