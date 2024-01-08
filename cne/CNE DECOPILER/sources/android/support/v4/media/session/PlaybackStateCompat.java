package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"BanParcelableUsage"})
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        public PlaybackStateCompat[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }
    };
    final long mActions;
    final long mActiveItemId;
    final long mBufferedPosition;
    List mCustomActions;
    final int mErrorCode;
    final CharSequence mErrorMessage;
    final Bundle mExtras;
    final long mPosition;
    final float mSpeed;
    final int mState;
    private PlaybackState mStateFwk;
    final long mUpdateTime;

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface Actions {
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaKeyAction {
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShuffleMode {
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public static int toKeyCode(long j) {
        if (j == 4) {
            return 126;
        }
        if (j == 2) {
            return 127;
        }
        if (j == 32) {
            return 87;
        }
        if (j == 16) {
            return 88;
        }
        if (j == 1) {
            return 86;
        }
        if (j == 64) {
            return 90;
        }
        if (j == 8) {
            return 89;
        }
        return j == 512 ? 85 : 0;
    }

    public int describeContents() {
        return 0;
    }

    PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, List list, long j5, Bundle bundle) {
        this.mState = i;
        this.mPosition = j;
        this.mBufferedPosition = j2;
        this.mSpeed = f;
        this.mActions = j3;
        this.mErrorCode = i2;
        this.mErrorMessage = charSequence;
        this.mUpdateTime = j4;
        this.mCustomActions = new ArrayList(list);
        this.mActiveItemId = j5;
        this.mExtras = bundle;
    }

    PlaybackStateCompat(Parcel parcel) {
        this.mState = parcel.readInt();
        this.mPosition = parcel.readLong();
        this.mSpeed = parcel.readFloat();
        this.mUpdateTime = parcel.readLong();
        this.mBufferedPosition = parcel.readLong();
        this.mActions = parcel.readLong();
        this.mErrorMessage = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mCustomActions = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.mActiveItemId = parcel.readLong();
        this.mExtras = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.mErrorCode = parcel.readInt();
    }

    public String toString() {
        return "PlaybackState {" + "state=" + this.mState + ", position=" + this.mPosition + ", buffered position=" + this.mBufferedPosition + ", speed=" + this.mSpeed + ", updated=" + this.mUpdateTime + ", actions=" + this.mActions + ", error code=" + this.mErrorCode + ", error message=" + this.mErrorMessage + ", custom actions=" + this.mCustomActions + ", active item id=" + this.mActiveItemId + "}";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mState);
        parcel.writeLong(this.mPosition);
        parcel.writeFloat(this.mSpeed);
        parcel.writeLong(this.mUpdateTime);
        parcel.writeLong(this.mBufferedPosition);
        parcel.writeLong(this.mActions);
        TextUtils.writeToParcel(this.mErrorMessage, parcel, i);
        parcel.writeTypedList(this.mCustomActions);
        parcel.writeLong(this.mActiveItemId);
        parcel.writeBundle(this.mExtras);
        parcel.writeInt(this.mErrorCode);
    }

    public int getState() {
        return this.mState;
    }

    public long getPosition() {
        return this.mPosition;
    }

    public long getLastPositionUpdateTime() {
        return this.mUpdateTime;
    }

    @RestrictTo
    public long getCurrentPosition(Long l) {
        return Math.max(0, this.mPosition + ((long) (this.mSpeed * ((float) (l != null ? l.longValue() : SystemClock.elapsedRealtime() - this.mUpdateTime)))));
    }

    public long getBufferedPosition() {
        return this.mBufferedPosition;
    }

    public float getPlaybackSpeed() {
        return this.mSpeed;
    }

    public long getActions() {
        return this.mActions;
    }

    public List getCustomActions() {
        return this.mCustomActions;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }

    public long getActiveQueueItemId() {
        return this.mActiveItemId;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    public static PlaybackStateCompat fromPlaybackState(Object obj) {
        ArrayList arrayList;
        Bundle bundle = null;
        if (obj == null) {
            return null;
        }
        PlaybackState playbackState = (PlaybackState) obj;
        List<Object> customActions = Api21Impl.getCustomActions(playbackState);
        if (customActions != null) {
            ArrayList arrayList2 = new ArrayList(customActions.size());
            for (Object fromCustomAction : customActions) {
                arrayList2.add(CustomAction.fromCustomAction(fromCustomAction));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        if (Build.VERSION.SDK_INT >= 22) {
            bundle = Api22Impl.getExtras(playbackState);
            MediaSessionCompat.ensureClassLoader(bundle);
        }
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(Api21Impl.getState(playbackState), Api21Impl.getPosition(playbackState), Api21Impl.getBufferedPosition(playbackState), Api21Impl.getPlaybackSpeed(playbackState), Api21Impl.getActions(playbackState), 0, Api21Impl.getErrorMessage(playbackState), Api21Impl.getLastPositionUpdateTime(playbackState), arrayList, Api21Impl.getActiveQueueItemId(playbackState), bundle);
        playbackStateCompat.mStateFwk = playbackState;
        return playbackStateCompat;
    }

    public Object getPlaybackState() {
        if (this.mStateFwk == null) {
            PlaybackState.Builder createBuilder = Api21Impl.createBuilder();
            Api21Impl.setState(createBuilder, this.mState, this.mPosition, this.mSpeed, this.mUpdateTime);
            Api21Impl.setBufferedPosition(createBuilder, this.mBufferedPosition);
            Api21Impl.setActions(createBuilder, this.mActions);
            Api21Impl.setErrorMessage(createBuilder, this.mErrorMessage);
            for (CustomAction customAction : this.mCustomActions) {
                Api21Impl.addCustomAction(createBuilder, (PlaybackState.CustomAction) customAction.getCustomAction());
            }
            Api21Impl.setActiveQueueItemId(createBuilder, this.mActiveItemId);
            if (Build.VERSION.SDK_INT >= 22) {
                Api22Impl.setExtras(createBuilder, this.mExtras);
            }
            this.mStateFwk = Api21Impl.build(createBuilder);
        }
        return this.mStateFwk;
    }

    public final class CustomAction implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            public CustomAction[] newArray(int i) {
                return new CustomAction[i];
            }
        };
        private final String mAction;
        private PlaybackState.CustomAction mCustomActionFwk;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        public int describeContents() {
            return 0;
        }

        CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.mAction = str;
            this.mName = charSequence;
            this.mIcon = i;
            this.mExtras = bundle;
        }

        CustomAction(Parcel parcel) {
            this.mAction = parcel.readString();
            this.mName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.mIcon = parcel.readInt();
            this.mExtras = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mAction);
            TextUtils.writeToParcel(this.mName, parcel, i);
            parcel.writeInt(this.mIcon);
            parcel.writeBundle(this.mExtras);
        }

        public static CustomAction fromCustomAction(Object obj) {
            if (obj == null) {
                return null;
            }
            PlaybackState.CustomAction customAction = (PlaybackState.CustomAction) obj;
            Bundle extras = Api21Impl.getExtras(customAction);
            MediaSessionCompat.ensureClassLoader(extras);
            CustomAction customAction2 = new CustomAction(Api21Impl.getAction(customAction), Api21Impl.getName(customAction), Api21Impl.getIcon(customAction), extras);
            customAction2.mCustomActionFwk = customAction;
            return customAction2;
        }

        public Object getCustomAction() {
            PlaybackState.CustomAction customAction = this.mCustomActionFwk;
            if (customAction != null) {
                return customAction;
            }
            PlaybackState.CustomAction.Builder createCustomActionBuilder = Api21Impl.createCustomActionBuilder(this.mAction, this.mName, this.mIcon);
            Api21Impl.setExtras(createCustomActionBuilder, this.mExtras);
            return Api21Impl.build(createCustomActionBuilder);
        }

        public String getAction() {
            return this.mAction;
        }

        public CharSequence getName() {
            return this.mName;
        }

        public int getIcon() {
            return this.mIcon;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public String toString() {
            return "Action:mName='" + this.mName + ", mIcon=" + this.mIcon + ", mExtras=" + this.mExtras;
        }

        public final class Builder {
            private final String mAction;
            private Bundle mExtras;
            private final int mIcon;
            private final CharSequence mName;

            public Builder(String str, CharSequence charSequence, int i) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("You must specify an action to build a CustomAction");
                } else if (TextUtils.isEmpty(charSequence)) {
                    throw new IllegalArgumentException("You must specify a name to build a CustomAction");
                } else if (i != 0) {
                    this.mAction = str;
                    this.mName = charSequence;
                    this.mIcon = i;
                } else {
                    throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction");
                }
            }

            public Builder setExtras(Bundle bundle) {
                this.mExtras = bundle;
                return this;
            }

            public CustomAction build() {
                return new CustomAction(this.mAction, this.mName, this.mIcon, this.mExtras);
            }
        }
    }

    public final class Builder {
        private long mActions;
        private long mActiveItemId;
        private long mBufferedPosition;
        private final List mCustomActions;
        private int mErrorCode;
        private CharSequence mErrorMessage;
        private Bundle mExtras;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;

        public Builder() {
            this.mCustomActions = new ArrayList();
            this.mActiveItemId = -1;
        }

        public Builder(PlaybackStateCompat playbackStateCompat) {
            ArrayList arrayList = new ArrayList();
            this.mCustomActions = arrayList;
            this.mActiveItemId = -1;
            this.mState = playbackStateCompat.mState;
            this.mPosition = playbackStateCompat.mPosition;
            this.mRate = playbackStateCompat.mSpeed;
            this.mUpdateTime = playbackStateCompat.mUpdateTime;
            this.mBufferedPosition = playbackStateCompat.mBufferedPosition;
            this.mActions = playbackStateCompat.mActions;
            this.mErrorCode = playbackStateCompat.mErrorCode;
            this.mErrorMessage = playbackStateCompat.mErrorMessage;
            List list = playbackStateCompat.mCustomActions;
            if (list != null) {
                arrayList.addAll(list);
            }
            this.mActiveItemId = playbackStateCompat.mActiveItemId;
            this.mExtras = playbackStateCompat.mExtras;
        }

        public Builder setState(int i, long j, float f) {
            return setState(i, j, f, SystemClock.elapsedRealtime());
        }

        public Builder setState(int i, long j, float f, long j2) {
            this.mState = i;
            this.mPosition = j;
            this.mUpdateTime = j2;
            this.mRate = f;
            return this;
        }

        public Builder setBufferedPosition(long j) {
            this.mBufferedPosition = j;
            return this;
        }

        public Builder setActions(long j) {
            this.mActions = j;
            return this;
        }

        public Builder addCustomAction(String str, String str2, int i) {
            return addCustomAction(new CustomAction(str, str2, i, (Bundle) null));
        }

        public Builder addCustomAction(CustomAction customAction) {
            if (customAction != null) {
                this.mCustomActions.add(customAction);
                return this;
            }
            throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat");
        }

        public Builder setActiveQueueItemId(long j) {
            this.mActiveItemId = j;
            return this;
        }

        @Deprecated
        public Builder setErrorMessage(CharSequence charSequence) {
            this.mErrorMessage = charSequence;
            return this;
        }

        public Builder setErrorMessage(int i, CharSequence charSequence) {
            this.mErrorCode = i;
            this.mErrorMessage = charSequence;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public PlaybackStateCompat build() {
            return new PlaybackStateCompat(this.mState, this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorCode, this.mErrorMessage, this.mUpdateTime, this.mCustomActions, this.mActiveItemId, this.mExtras);
        }
    }

    abstract class Api21Impl {
        @DoNotInline
        static PlaybackState.Builder createBuilder() {
            return new PlaybackState.Builder();
        }

        @DoNotInline
        static void setState(PlaybackState.Builder builder, int i, long j, float f, long j2) {
            builder.setState(i, j, f, j2);
        }

        @DoNotInline
        static void setBufferedPosition(PlaybackState.Builder builder, long j) {
            builder.setBufferedPosition(j);
        }

        @DoNotInline
        static void setActions(PlaybackState.Builder builder, long j) {
            builder.setActions(j);
        }

        @DoNotInline
        static void setErrorMessage(PlaybackState.Builder builder, CharSequence charSequence) {
            builder.setErrorMessage(charSequence);
        }

        @DoNotInline
        static void addCustomAction(PlaybackState.Builder builder, PlaybackState.CustomAction customAction) {
            builder.addCustomAction(customAction);
        }

        @DoNotInline
        static void setActiveQueueItemId(PlaybackState.Builder builder, long j) {
            builder.setActiveQueueItemId(j);
        }

        @DoNotInline
        static List getCustomActions(PlaybackState playbackState) {
            return playbackState.getCustomActions();
        }

        @DoNotInline
        static PlaybackState build(PlaybackState.Builder builder) {
            return builder.build();
        }

        @DoNotInline
        static int getState(PlaybackState playbackState) {
            return playbackState.getState();
        }

        @DoNotInline
        static long getPosition(PlaybackState playbackState) {
            return playbackState.getPosition();
        }

        @DoNotInline
        static long getBufferedPosition(PlaybackState playbackState) {
            return playbackState.getBufferedPosition();
        }

        @DoNotInline
        static float getPlaybackSpeed(PlaybackState playbackState) {
            return playbackState.getPlaybackSpeed();
        }

        @DoNotInline
        static long getActions(PlaybackState playbackState) {
            return playbackState.getActions();
        }

        @DoNotInline
        static CharSequence getErrorMessage(PlaybackState playbackState) {
            return playbackState.getErrorMessage();
        }

        @DoNotInline
        static long getLastPositionUpdateTime(PlaybackState playbackState) {
            return playbackState.getLastPositionUpdateTime();
        }

        @DoNotInline
        static long getActiveQueueItemId(PlaybackState playbackState) {
            return playbackState.getActiveQueueItemId();
        }

        @DoNotInline
        static PlaybackState.CustomAction.Builder createCustomActionBuilder(String str, CharSequence charSequence, int i) {
            return new PlaybackState.CustomAction.Builder(str, charSequence, i);
        }

        @DoNotInline
        static void setExtras(PlaybackState.CustomAction.Builder builder, Bundle bundle) {
            builder.setExtras(bundle);
        }

        @DoNotInline
        static PlaybackState.CustomAction build(PlaybackState.CustomAction.Builder builder) {
            return builder.build();
        }

        @DoNotInline
        static Bundle getExtras(PlaybackState.CustomAction customAction) {
            return customAction.getExtras();
        }

        @DoNotInline
        static String getAction(PlaybackState.CustomAction customAction) {
            return customAction.getAction();
        }

        @DoNotInline
        static CharSequence getName(PlaybackState.CustomAction customAction) {
            return customAction.getName();
        }

        @DoNotInline
        static int getIcon(PlaybackState.CustomAction customAction) {
            return customAction.getIcon();
        }
    }

    abstract class Api22Impl {
        @DoNotInline
        static void setExtras(PlaybackState.Builder builder, Bundle bundle) {
            builder.setExtras(bundle);
        }

        @DoNotInline
        static Bundle getExtras(PlaybackState playbackState) {
            return playbackState.getExtras();
        }
    }
}
