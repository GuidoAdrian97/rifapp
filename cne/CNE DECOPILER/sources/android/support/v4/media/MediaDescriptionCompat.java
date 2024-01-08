package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;

@SuppressLint({"BanParcelableUsage"})
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return MediaDescriptionCompat.fromMediaDescription(MediaDescription.CREATOR.createFromParcel(parcel));
        }

        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    };
    private final CharSequence mDescription;
    private MediaDescription mDescriptionFwk;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final Uri mMediaUri;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

    public int describeContents() {
        return 0;
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.mMediaId = str;
        this.mTitle = charSequence;
        this.mSubtitle = charSequence2;
        this.mDescription = charSequence3;
        this.mIcon = bitmap;
        this.mIconUri = uri;
        this.mExtras = bundle;
        this.mMediaUri = uri2;
    }

    @Nullable
    public String getMediaId() {
        return this.mMediaId;
    }

    @Nullable
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Nullable
    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    @Nullable
    public CharSequence getDescription() {
        return this.mDescription;
    }

    @Nullable
    public Bitmap getIconBitmap() {
        return this.mIcon;
    }

    @Nullable
    public Uri getIconUri() {
        return this.mIconUri;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    @Nullable
    public Uri getMediaUri() {
        return this.mMediaUri;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ((MediaDescription) getMediaDescription()).writeToParcel(parcel, i);
    }

    public String toString() {
        return this.mTitle + ", " + this.mSubtitle + ", " + this.mDescription;
    }

    public Object getMediaDescription() {
        MediaDescription mediaDescription = this.mDescriptionFwk;
        if (mediaDescription != null) {
            return mediaDescription;
        }
        int i = Build.VERSION.SDK_INT;
        MediaDescription.Builder createBuilder = Api21Impl.createBuilder();
        Api21Impl.setMediaId(createBuilder, this.mMediaId);
        Api21Impl.setTitle(createBuilder, this.mTitle);
        Api21Impl.setSubtitle(createBuilder, this.mSubtitle);
        Api21Impl.setDescription(createBuilder, this.mDescription);
        Api21Impl.setIconBitmap(createBuilder, this.mIcon);
        Api21Impl.setIconUri(createBuilder, this.mIconUri);
        Bundle bundle = this.mExtras;
        if (i < 23 && this.mMediaUri != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.mMediaUri);
        }
        Api21Impl.setExtras(createBuilder, bundle);
        if (i >= 23) {
            Api23Impl.setMediaUri(createBuilder, this.mMediaUri);
        }
        MediaDescription build = Api21Impl.build(createBuilder);
        this.mDescriptionFwk = build;
        return build;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v4.media.MediaDescriptionCompat fromMediaDescription(java.lang.Object r9) {
        /*
            r0 = 0
            if (r9 == 0) goto L_0x007f
            int r1 = android.os.Build.VERSION.SDK_INT
            android.support.v4.media.MediaDescriptionCompat$Builder r2 = new android.support.v4.media.MediaDescriptionCompat$Builder
            r2.<init>()
            android.media.MediaDescription r9 = (android.media.MediaDescription) r9
            java.lang.String r3 = android.support.v4.media.MediaDescriptionCompat.Api21Impl.getMediaId(r9)
            r2.setMediaId(r3)
            java.lang.CharSequence r3 = android.support.v4.media.MediaDescriptionCompat.Api21Impl.getTitle(r9)
            r2.setTitle(r3)
            java.lang.CharSequence r3 = android.support.v4.media.MediaDescriptionCompat.Api21Impl.getSubtitle(r9)
            r2.setSubtitle(r3)
            java.lang.CharSequence r3 = android.support.v4.media.MediaDescriptionCompat.Api21Impl.getDescription(r9)
            r2.setDescription(r3)
            android.graphics.Bitmap r3 = android.support.v4.media.MediaDescriptionCompat.Api21Impl.getIconBitmap(r9)
            r2.setIconBitmap(r3)
            android.net.Uri r3 = android.support.v4.media.MediaDescriptionCompat.Api21Impl.getIconUri(r9)
            r2.setIconUri(r3)
            android.os.Bundle r3 = android.support.v4.media.MediaDescriptionCompat.Api21Impl.getExtras(r9)
            if (r3 == 0) goto L_0x0040
            android.os.Bundle r3 = android.support.v4.media.session.MediaSessionCompat.unparcelWithClassLoader(r3)
        L_0x0040:
            java.lang.String r4 = "android.support.v4.media.description.MEDIA_URI"
            if (r3 == 0) goto L_0x004b
            android.os.Parcelable r5 = r3.getParcelable(r4)
            android.net.Uri r5 = (android.net.Uri) r5
            goto L_0x004c
        L_0x004b:
            r5 = r0
        L_0x004c:
            if (r5 == 0) goto L_0x0064
            java.lang.String r6 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r7 = r3.containsKey(r6)
            if (r7 == 0) goto L_0x005e
            int r7 = r3.size()
            r8 = 2
            if (r7 != r8) goto L_0x005e
            goto L_0x0065
        L_0x005e:
            r3.remove(r4)
            r3.remove(r6)
        L_0x0064:
            r0 = r3
        L_0x0065:
            r2.setExtras(r0)
            if (r5 == 0) goto L_0x006e
            r2.setMediaUri(r5)
            goto L_0x0079
        L_0x006e:
            r0 = 23
            if (r1 < r0) goto L_0x0079
            android.net.Uri r0 = android.support.v4.media.MediaDescriptionCompat.Api23Impl.getMediaUri(r9)
            r2.setMediaUri(r0)
        L_0x0079:
            android.support.v4.media.MediaDescriptionCompat r0 = r2.build()
            r0.mDescriptionFwk = r9
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.fromMediaDescription(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    public final class Builder {
        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private Uri mMediaUri;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public Builder setMediaId(@Nullable String str) {
            this.mMediaId = str;
            return this;
        }

        public Builder setTitle(@Nullable CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public Builder setSubtitle(@Nullable CharSequence charSequence) {
            this.mSubtitle = charSequence;
            return this;
        }

        public Builder setDescription(@Nullable CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public Builder setIconBitmap(@Nullable Bitmap bitmap) {
            this.mIcon = bitmap;
            return this;
        }

        public Builder setIconUri(@Nullable Uri uri) {
            this.mIconUri = uri;
            return this;
        }

        public Builder setExtras(@Nullable Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setMediaUri(@Nullable Uri uri) {
            this.mMediaUri = uri;
            return this;
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
        }
    }

    abstract class Api21Impl {
        @DoNotInline
        static MediaDescription.Builder createBuilder() {
            return new MediaDescription.Builder();
        }

        @DoNotInline
        static void setMediaId(MediaDescription.Builder builder, @Nullable String str) {
            builder.setMediaId(str);
        }

        @DoNotInline
        static void setTitle(MediaDescription.Builder builder, @Nullable CharSequence charSequence) {
            builder.setTitle(charSequence);
        }

        @DoNotInline
        static void setSubtitle(MediaDescription.Builder builder, @Nullable CharSequence charSequence) {
            builder.setSubtitle(charSequence);
        }

        @DoNotInline
        static void setDescription(MediaDescription.Builder builder, @Nullable CharSequence charSequence) {
            builder.setDescription(charSequence);
        }

        @DoNotInline
        static void setIconBitmap(MediaDescription.Builder builder, @Nullable Bitmap bitmap) {
            builder.setIconBitmap(bitmap);
        }

        @DoNotInline
        static void setIconUri(MediaDescription.Builder builder, @Nullable Uri uri) {
            builder.setIconUri(uri);
        }

        @DoNotInline
        static void setExtras(MediaDescription.Builder builder, @Nullable Bundle bundle) {
            builder.setExtras(bundle);
        }

        @DoNotInline
        static MediaDescription build(MediaDescription.Builder builder) {
            return builder.build();
        }

        @DoNotInline
        @Nullable
        static String getMediaId(MediaDescription mediaDescription) {
            return mediaDescription.getMediaId();
        }

        @DoNotInline
        @Nullable
        static CharSequence getTitle(MediaDescription mediaDescription) {
            return mediaDescription.getTitle();
        }

        @DoNotInline
        @Nullable
        static CharSequence getSubtitle(MediaDescription mediaDescription) {
            return mediaDescription.getSubtitle();
        }

        @DoNotInline
        @Nullable
        static CharSequence getDescription(MediaDescription mediaDescription) {
            return mediaDescription.getDescription();
        }

        @DoNotInline
        @Nullable
        static Bitmap getIconBitmap(MediaDescription mediaDescription) {
            return mediaDescription.getIconBitmap();
        }

        @DoNotInline
        @Nullable
        static Uri getIconUri(MediaDescription mediaDescription) {
            return mediaDescription.getIconUri();
        }

        @DoNotInline
        @Nullable
        static Bundle getExtras(MediaDescription mediaDescription) {
            return mediaDescription.getExtras();
        }
    }

    abstract class Api23Impl {
        @DoNotInline
        static void setMediaUri(MediaDescription.Builder builder, @Nullable Uri uri) {
            builder.setMediaUri(uri);
        }

        @DoNotInline
        @Nullable
        static Uri getMediaUri(MediaDescription mediaDescription) {
            return mediaDescription.getMediaUri();
        }
    }
}
