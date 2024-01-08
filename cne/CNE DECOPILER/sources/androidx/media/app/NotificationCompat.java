package androidx.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;
import androidx.annotation.DoNotInline;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.media.R$color;
import androidx.media.R$id;
import androidx.media.R$integer;
import androidx.media.R$layout;

public class NotificationCompat {
    private NotificationCompat() {
    }

    public class MediaStyle extends NotificationCompat.Style {
        int[] mActionsToShowInCompact = null;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        MediaSessionCompat.Token mToken;

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public MediaStyle setShowCancelButton(boolean z) {
            return this;
        }

        public static MediaSessionCompat.Token getMediaSession(Notification notification) {
            Parcelable parcelable;
            Bundle extras = androidx.core.app.NotificationCompat.getExtras(notification);
            if (extras == null || (parcelable = extras.getParcelable("android.mediaSession")) == null) {
                return null;
            }
            return MediaSessionCompat.Token.fromToken(parcelable);
        }

        public MediaStyle() {
        }

        public MediaStyle(NotificationCompat.Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.mActionsToShowInCompact = iArr;
            return this;
        }

        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            this.mToken = token;
            return this;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Api21Impl.setStyle(notificationBuilderWithBuilderAccessor.getBuilder(), Api21Impl.fillInMediaStyle(Api21Impl.createMediaStyle(), this.mActionsToShowInCompact, this.mToken));
        }

        /* access modifiers changed from: package-private */
        public RemoteViews generateContentView() {
            int i;
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int size = this.mBuilder.mActions.size();
            int[] iArr = this.mActionsToShowInCompact;
            if (iArr == null) {
                i = 0;
            } else {
                i = Math.min(iArr.length, 3);
            }
            applyStandardTemplate.removeAllViews(R$id.media_actions);
            if (i > 0) {
                int i2 = 0;
                while (i2 < i) {
                    if (i2 < size) {
                        applyStandardTemplate.addView(R$id.media_actions, generateMediaActionButton((NotificationCompat.Action) this.mBuilder.mActions.get(this.mActionsToShowInCompact[i2])));
                        i2++;
                    } else {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[]{Integer.valueOf(i2), Integer.valueOf(size - 1)}));
                    }
                }
            }
            if (this.mShowCancelButton) {
                applyStandardTemplate.setViewVisibility(R$id.end_padder, 8);
                int i3 = R$id.cancel_action;
                applyStandardTemplate.setViewVisibility(i3, 0);
                applyStandardTemplate.setOnClickPendingIntent(i3, this.mCancelButtonIntent);
                applyStandardTemplate.setInt(i3, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R$integer.cancel_button_image_alpha));
            } else {
                applyStandardTemplate.setViewVisibility(R$id.end_padder, 0);
                applyStandardTemplate.setViewVisibility(R$id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        private RemoteViews generateMediaActionButton(NotificationCompat.Action action) {
            boolean z = action.getActionIntent() == null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), R$layout.notification_media_action);
            int i = R$id.action0;
            remoteViews.setImageViewResource(i, action.getIcon());
            if (!z) {
                remoteViews.setOnClickPendingIntent(i, action.getActionIntent());
            }
            Api15Impl.setContentDescription(remoteViews, i, action.getTitle());
            return remoteViews;
        }

        /* access modifiers changed from: package-private */
        public int getContentViewLayoutResource() {
            return R$layout.notification_template_media;
        }

        /* access modifiers changed from: package-private */
        public RemoteViews generateBigContentView() {
            int min = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, getBigContentViewLayoutResource(min), false);
            applyStandardTemplate.removeAllViews(R$id.media_actions);
            if (min > 0) {
                for (int i = 0; i < min; i++) {
                    applyStandardTemplate.addView(R$id.media_actions, generateMediaActionButton((NotificationCompat.Action) this.mBuilder.mActions.get(i)));
                }
            }
            if (this.mShowCancelButton) {
                int i2 = R$id.cancel_action;
                applyStandardTemplate.setViewVisibility(i2, 0);
                applyStandardTemplate.setInt(i2, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R$integer.cancel_button_image_alpha));
                applyStandardTemplate.setOnClickPendingIntent(i2, this.mCancelButtonIntent);
            } else {
                applyStandardTemplate.setViewVisibility(R$id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        /* access modifiers changed from: package-private */
        public int getBigContentViewLayoutResource(int i) {
            if (i <= 3) {
                return R$layout.notification_template_big_media_narrow;
            }
            return R$layout.notification_template_big_media;
        }
    }

    public class DecoratedMediaCustomViewStyle extends MediaStyle {
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                Api21Impl.setStyle(notificationBuilderWithBuilderAccessor.getBuilder(), Api21Impl.fillInMediaStyle(Api24Impl.createDecoratedMediaCustomViewStyle(), this.mActionsToShowInCompact, this.mToken));
            } else {
                super.apply(notificationBuilderWithBuilderAccessor);
            }
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews = null;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            boolean z = true;
            boolean z2 = this.mBuilder.getContentView() != null;
            if (!z2 && this.mBuilder.getBigContentView() == null) {
                z = false;
            }
            if (z) {
                remoteViews = generateContentView();
                if (z2) {
                    buildIntoRemoteViews(remoteViews, this.mBuilder.getContentView());
                }
                setBackgroundColor(remoteViews);
            }
            return remoteViews;
        }

        /* access modifiers changed from: package-private */
        public int getContentViewLayoutResource() {
            if (this.mBuilder.getContentView() != null) {
                return R$layout.notification_template_media_custom;
            }
            return super.getContentViewLayoutResource();
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getBigContentView() != null) {
                remoteViews = this.mBuilder.getBigContentView();
            } else {
                remoteViews = this.mBuilder.getContentView();
            }
            if (remoteViews == null) {
                return null;
            }
            RemoteViews generateBigContentView = generateBigContentView();
            buildIntoRemoteViews(generateBigContentView, remoteViews);
            setBackgroundColor(generateBigContentView);
            return generateBigContentView;
        }

        /* access modifiers changed from: package-private */
        public int getBigContentViewLayoutResource(int i) {
            if (i <= 3) {
                return R$layout.notification_template_big_media_narrow_custom;
            }
            return R$layout.notification_template_big_media_custom;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getHeadsUpContentView() != null) {
                remoteViews = this.mBuilder.getHeadsUpContentView();
            } else {
                remoteViews = this.mBuilder.getContentView();
            }
            if (remoteViews == null) {
                return null;
            }
            RemoteViews generateBigContentView = generateBigContentView();
            buildIntoRemoteViews(generateBigContentView, remoteViews);
            setBackgroundColor(generateBigContentView);
            return generateBigContentView;
        }

        private void setBackgroundColor(RemoteViews remoteViews) {
            int i;
            if (this.mBuilder.getColor() != 0) {
                i = this.mBuilder.getColor();
            } else {
                i = this.mBuilder.mContext.getResources().getColor(R$color.notification_material_background_media_default_color);
            }
            remoteViews.setInt(R$id.status_bar_latest_event_content, "setBackgroundColor", i);
        }
    }

    abstract class Api15Impl {
        @DoNotInline
        static void setContentDescription(RemoteViews remoteViews, int i, CharSequence charSequence) {
            remoteViews.setContentDescription(i, charSequence);
        }
    }

    abstract class Api21Impl {
        @DoNotInline
        static void setStyle(Notification.Builder builder, Notification.Style style) {
            builder.setStyle(style);
        }

        @DoNotInline
        static Notification.MediaStyle createMediaStyle() {
            return new Notification.MediaStyle();
        }

        @DoNotInline
        static Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle mediaStyle, int[] iArr, MediaSessionCompat.Token token) {
            if (iArr != null) {
                setShowActionsInCompactView(mediaStyle, iArr);
            }
            if (token != null) {
                setMediaSession(mediaStyle, (MediaSession.Token) token.getToken());
            }
            return mediaStyle;
        }

        @DoNotInline
        static void setShowActionsInCompactView(Notification.MediaStyle mediaStyle, int... iArr) {
            mediaStyle.setShowActionsInCompactView(iArr);
        }

        @DoNotInline
        static void setMediaSession(Notification.MediaStyle mediaStyle, MediaSession.Token token) {
            mediaStyle.setMediaSession(token);
        }
    }

    abstract class Api24Impl {
        @DoNotInline
        static Notification.DecoratedMediaCustomViewStyle createDecoratedMediaCustomViewStyle() {
            return new Notification.DecoratedMediaCustomViewStyle();
        }
    }
}
