package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import java.util.ArrayList;
import java.util.List;

public final class ContentInfoCompat {
    final ClipData mClip;
    final Bundle mExtras;
    final int mFlags;
    final Uri mLinkUri;
    final int mSource;

    static String sourceToString(int i) {
        if (i == 0) {
            return "SOURCE_APP";
        }
        if (i == 1) {
            return "SOURCE_CLIPBOARD";
        }
        if (i != 2) {
            return i != 3 ? String.valueOf(i) : "SOURCE_DRAG_AND_DROP";
        }
        return "SOURCE_INPUT_METHOD";
    }

    static String flagsToString(int i) {
        return (i & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i);
    }

    ContentInfoCompat(Builder builder) {
        this.mClip = (ClipData) Preconditions.checkNotNull(builder.mClip);
        this.mSource = Preconditions.checkArgumentInRange(builder.mSource, 0, 3, "source");
        this.mFlags = Preconditions.checkFlagsArgument(builder.mFlags, 1);
        this.mLinkUri = builder.mLinkUri;
        this.mExtras = builder.mExtras;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("ContentInfoCompat{clip=");
        sb.append(this.mClip.getDescription());
        sb.append(", source=");
        sb.append(sourceToString(this.mSource));
        sb.append(", flags=");
        sb.append(flagsToString(this.mFlags));
        String str2 = "";
        if (this.mLinkUri == null) {
            str = str2;
        } else {
            str = ", hasLinkUri(" + this.mLinkUri.toString().length() + ")";
        }
        sb.append(str);
        if (this.mExtras != null) {
            str2 = ", hasExtras";
        }
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }

    @NonNull
    public ClipData getClip() {
        return this.mClip;
    }

    public int getSource() {
        return this.mSource;
    }

    public int getFlags() {
        return this.mFlags;
    }

    @Nullable
    public Uri getLinkUri() {
        return this.mLinkUri;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    @NonNull
    public Pair partition(@NonNull Predicate predicate) {
        ContentInfoCompat contentInfoCompat = null;
        if (this.mClip.getItemCount() == 1) {
            boolean test = predicate.test(this.mClip.getItemAt(0));
            ContentInfoCompat contentInfoCompat2 = test ? this : null;
            if (!test) {
                contentInfoCompat = this;
            }
            return Pair.create(contentInfoCompat2, contentInfoCompat);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < this.mClip.getItemCount(); i++) {
            ClipData.Item itemAt = this.mClip.getItemAt(i);
            if (predicate.test(itemAt)) {
                arrayList.add(itemAt);
            } else {
                arrayList2.add(itemAt);
            }
        }
        if (arrayList.isEmpty()) {
            return Pair.create((Object) null, this);
        }
        if (arrayList2.isEmpty()) {
            return Pair.create(this, (Object) null);
        }
        return Pair.create(new Builder(this).setClip(buildClipData(this.mClip.getDescription(), arrayList)).build(), new Builder(this).setClip(buildClipData(this.mClip.getDescription(), arrayList2)).build());
    }

    private static ClipData buildClipData(ClipDescription clipDescription, List list) {
        ClipData clipData = new ClipData(new ClipDescription(clipDescription), (ClipData.Item) list.get(0));
        for (int i = 1; i < list.size(); i++) {
            clipData.addItem((ClipData.Item) list.get(i));
        }
        return clipData;
    }

    public final class Builder {
        ClipData mClip;
        Bundle mExtras;
        int mFlags;
        Uri mLinkUri;
        int mSource;

        public Builder(ContentInfoCompat contentInfoCompat) {
            this.mClip = contentInfoCompat.mClip;
            this.mSource = contentInfoCompat.mSource;
            this.mFlags = contentInfoCompat.mFlags;
            this.mLinkUri = contentInfoCompat.mLinkUri;
            this.mExtras = contentInfoCompat.mExtras;
        }

        public Builder(ClipData clipData, int i) {
            this.mClip = clipData;
            this.mSource = i;
        }

        public Builder setClip(ClipData clipData) {
            this.mClip = clipData;
            return this;
        }

        public Builder setFlags(int i) {
            this.mFlags = i;
            return this;
        }

        public Builder setLinkUri(Uri uri) {
            this.mLinkUri = uri;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public ContentInfoCompat build() {
            return new ContentInfoCompat(this);
        }
    }
}
