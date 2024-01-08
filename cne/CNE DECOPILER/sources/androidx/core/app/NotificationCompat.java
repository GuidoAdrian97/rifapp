package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.R$dimen;
import androidx.core.R$drawable;
import androidx.core.R$id;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;

public abstract class NotificationCompat {

    public abstract class BubbleMetadata {
        public static Notification.BubbleMetadata toPlatform(BubbleMetadata bubbleMetadata) {
            return null;
        }
    }

    public class Builder {
        public ArrayList mActions;
        boolean mAllowSystemGeneratedContextualActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        String mCategory;
        String mChannelId;
        boolean mChronometerCountDown;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList mInvisibleActions;
        Bitmap mLargeIcon;
        boolean mLocalOnly;
        LocusIdCompat mLocusId;
        Notification mNotification;
        int mNumber;
        public ArrayList mPeople;
        public ArrayList mPersonList;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        CharSequence mSettingsText;
        String mShortcutId;
        boolean mShowWhen;
        boolean mSilent;
        Icon mSmallIcon;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        public Builder(Context context, String str) {
            this.mActions = new ArrayList();
            this.mPersonList = new ArrayList();
            this.mInvisibleActions = new ArrayList();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            Notification notification = new Notification();
            this.mNotification = notification;
            this.mContext = context;
            this.mChannelId = str;
            notification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList();
            this.mAllowSystemGeneratedContextualActions = true;
        }

        public Builder(Context context) {
            this(context, (String) null);
        }

        public Builder setWhen(long j) {
            this.mNotification.when = j;
            return this;
        }

        public Builder setSmallIcon(int i) {
            this.mNotification.icon = i;
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.mContentTitle = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.mContentText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public Builder setTicker(CharSequence charSequence) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setAutoCancel(boolean z) {
            setFlag(16, z);
            return this;
        }

        public Builder setLocalOnly(boolean z) {
            this.mLocalOnly = z;
            return this;
        }

        private void setFlag(int i, boolean z) {
            if (z) {
                Notification notification = this.mNotification;
                notification.flags = i | notification.flags;
                return;
            }
            Notification notification2 = this.mNotification;
            notification2.flags = (~i) & notification2.flags;
        }

        public Builder setPriority(int i) {
            this.mPriority = i;
            return this;
        }

        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        public Builder addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Action(i, charSequence, pendingIntent));
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.mStyle != style) {
                this.mStyle = style;
                if (style != null) {
                    style.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setChannelId(String str) {
            this.mChannelId = str;
            return this;
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        protected static CharSequence limitCharSequenceLength(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public RemoteViews getContentView() {
            return this.mContentView;
        }

        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        public long getWhenIfShowing() {
            if (this.mShowWhen) {
                return this.mNotification.when;
            }
            return 0;
        }

        public int getPriority() {
            return this.mPriority;
        }

        public int getColor() {
            return this.mColor;
        }
    }

    public abstract class Style {
        CharSequence mBigContentTitle;
        protected Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet = false;

        private static float constrain(float f, float f2, float f3) {
            return f < f2 ? f2 : f > f3 ? f3 : f;
        }

        public abstract void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor);

        /* access modifiers changed from: protected */
        public String getClassName() {
            return null;
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public void setBuilder(Builder builder) {
            if (this.mBuilder != builder) {
                this.mBuilder = builder;
                if (builder != null) {
                    builder.setStyle(this);
                }
            }
        }

        public void addCompatExtras(Bundle bundle) {
            if (this.mSummaryTextSet) {
                bundle.putCharSequence("android.summaryText", this.mSummaryText);
            }
            CharSequence charSequence = this.mBigContentTitle;
            if (charSequence != null) {
                bundle.putCharSequence("android.title.big", charSequence);
            }
            String className = getClassName();
            if (className != null) {
                bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", className);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:32:0x00fe  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x011b  */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x013f  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0183  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0188  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x018a  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0194  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.widget.RemoteViews applyStandardTemplate(boolean r13, int r14, boolean r15) {
            /*
                r12 = this;
                androidx.core.app.NotificationCompat$Builder r0 = r12.mBuilder
                android.content.Context r0 = r0.mContext
                android.content.res.Resources r0 = r0.getResources()
                android.widget.RemoteViews r7 = new android.widget.RemoteViews
                androidx.core.app.NotificationCompat$Builder r1 = r12.mBuilder
                android.content.Context r1 = r1.mContext
                java.lang.String r1 = r1.getPackageName()
                r7.<init>(r1, r14)
                androidx.core.app.NotificationCompat$Builder r14 = r12.mBuilder
                r14.getPriority()
                int r14 = android.os.Build.VERSION.SDK_INT
                androidx.core.app.NotificationCompat$Builder r1 = r12.mBuilder
                android.graphics.Bitmap r2 = r1.mLargeIcon
                r8 = 0
                if (r2 == 0) goto L_0x0060
                int r1 = androidx.core.R$id.icon
                r7.setViewVisibility(r1, r8)
                androidx.core.app.NotificationCompat$Builder r2 = r12.mBuilder
                android.graphics.Bitmap r2 = r2.mLargeIcon
                r7.setImageViewBitmap(r1, r2)
                if (r13 == 0) goto L_0x0091
                androidx.core.app.NotificationCompat$Builder r13 = r12.mBuilder
                android.app.Notification r13 = r13.mNotification
                int r13 = r13.icon
                if (r13 == 0) goto L_0x0091
                int r13 = androidx.core.R$dimen.notification_right_icon_size
                int r13 = r0.getDimensionPixelSize(r13)
                int r1 = androidx.core.R$dimen.notification_small_icon_background_padding
                int r1 = r0.getDimensionPixelSize(r1)
                int r1 = r1 * 2
                int r1 = r13 - r1
                androidx.core.app.NotificationCompat$Builder r2 = r12.mBuilder
                android.app.Notification r3 = r2.mNotification
                int r3 = r3.icon
                int r2 = r2.getColor()
                android.graphics.Bitmap r13 = r12.createIconWithBackground(r3, r13, r1, r2)
                int r1 = androidx.core.R$id.right_icon
                r7.setImageViewBitmap(r1, r13)
                r7.setViewVisibility(r1, r8)
                goto L_0x0091
            L_0x0060:
                if (r13 == 0) goto L_0x0091
                android.app.Notification r13 = r1.mNotification
                int r13 = r13.icon
                if (r13 == 0) goto L_0x0091
                int r13 = androidx.core.R$id.icon
                r7.setViewVisibility(r13, r8)
                int r1 = androidx.core.R$dimen.notification_large_icon_width
                int r1 = r0.getDimensionPixelSize(r1)
                int r2 = androidx.core.R$dimen.notification_big_circle_margin
                int r2 = r0.getDimensionPixelSize(r2)
                int r1 = r1 - r2
                int r2 = androidx.core.R$dimen.notification_small_icon_size_as_large
                int r2 = r0.getDimensionPixelSize(r2)
                androidx.core.app.NotificationCompat$Builder r3 = r12.mBuilder
                android.app.Notification r4 = r3.mNotification
                int r4 = r4.icon
                int r3 = r3.getColor()
                android.graphics.Bitmap r1 = r12.createIconWithBackground(r4, r1, r2, r3)
                r7.setImageViewBitmap(r13, r1)
            L_0x0091:
                androidx.core.app.NotificationCompat$Builder r13 = r12.mBuilder
                java.lang.CharSequence r13 = r13.mContentTitle
                if (r13 == 0) goto L_0x009c
                int r1 = androidx.core.R$id.title
                r7.setTextViewText(r1, r13)
            L_0x009c:
                androidx.core.app.NotificationCompat$Builder r13 = r12.mBuilder
                java.lang.CharSequence r13 = r13.mContentText
                r9 = 1
                if (r13 == 0) goto L_0x00aa
                int r1 = androidx.core.R$id.text
                r7.setTextViewText(r1, r13)
                r13 = 1
                goto L_0x00ab
            L_0x00aa:
                r13 = 0
            L_0x00ab:
                androidx.core.app.NotificationCompat$Builder r1 = r12.mBuilder
                java.lang.CharSequence r2 = r1.mContentInfo
                r10 = 8
                if (r2 == 0) goto L_0x00be
                int r13 = androidx.core.R$id.info
                r7.setTextViewText(r13, r2)
                r7.setViewVisibility(r13, r8)
            L_0x00bb:
                r13 = 1
                r11 = 1
                goto L_0x00f8
            L_0x00be:
                int r1 = r1.mNumber
                if (r1 <= 0) goto L_0x00f2
                int r13 = androidx.core.R$integer.status_bar_notification_info_maxnum
                int r13 = r0.getInteger(r13)
                androidx.core.app.NotificationCompat$Builder r1 = r12.mBuilder
                int r1 = r1.mNumber
                if (r1 <= r13) goto L_0x00da
                int r13 = androidx.core.R$id.info
                int r1 = androidx.core.R$string.status_bar_notification_info_overflow
                java.lang.String r1 = r0.getString(r1)
                r7.setTextViewText(r13, r1)
                goto L_0x00ec
            L_0x00da:
                java.text.NumberFormat r13 = java.text.NumberFormat.getIntegerInstance()
                int r1 = androidx.core.R$id.info
                androidx.core.app.NotificationCompat$Builder r2 = r12.mBuilder
                int r2 = r2.mNumber
                long r2 = (long) r2
                java.lang.String r13 = r13.format(r2)
                r7.setTextViewText(r1, r13)
            L_0x00ec:
                int r13 = androidx.core.R$id.info
                r7.setViewVisibility(r13, r8)
                goto L_0x00bb
            L_0x00f2:
                int r1 = androidx.core.R$id.info
                r7.setViewVisibility(r1, r10)
                r11 = 0
            L_0x00f8:
                androidx.core.app.NotificationCompat$Builder r1 = r12.mBuilder
                java.lang.CharSequence r1 = r1.mSubText
                if (r1 == 0) goto L_0x0118
                int r2 = androidx.core.R$id.text
                r7.setTextViewText(r2, r1)
                androidx.core.app.NotificationCompat$Builder r1 = r12.mBuilder
                java.lang.CharSequence r1 = r1.mContentText
                if (r1 == 0) goto L_0x0113
                int r2 = androidx.core.R$id.text2
                r7.setTextViewText(r2, r1)
                r7.setViewVisibility(r2, r8)
                r1 = 1
                goto L_0x0119
            L_0x0113:
                int r1 = androidx.core.R$id.text2
                r7.setViewVisibility(r1, r10)
            L_0x0118:
                r1 = 0
            L_0x0119:
                if (r1 == 0) goto L_0x0133
                if (r15 == 0) goto L_0x0129
                int r15 = androidx.core.R$dimen.notification_subtext_size
                int r15 = r0.getDimensionPixelSize(r15)
                float r15 = (float) r15
                int r0 = androidx.core.R$id.text
                r7.setTextViewTextSize(r0, r8, r15)
            L_0x0129:
                int r2 = androidx.core.R$id.line1
                r3 = 0
                r4 = 0
                r5 = 0
                r6 = 0
                r1 = r7
                r1.setViewPadding(r2, r3, r4, r5, r6)
            L_0x0133:
                androidx.core.app.NotificationCompat$Builder r15 = r12.mBuilder
                long r0 = r15.getWhenIfShowing()
                r2 = 0
                int r15 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r15 == 0) goto L_0x0183
                androidx.core.app.NotificationCompat$Builder r15 = r12.mBuilder
                boolean r15 = r15.mUseChronometer
                if (r15 == 0) goto L_0x0172
                int r15 = androidx.core.R$id.chronometer
                r7.setViewVisibility(r15, r8)
                androidx.core.app.NotificationCompat$Builder r0 = r12.mBuilder
                long r0 = r0.getWhenIfShowing()
                long r2 = android.os.SystemClock.elapsedRealtime()
                long r4 = java.lang.System.currentTimeMillis()
                long r2 = r2 - r4
                long r0 = r0 + r2
                java.lang.String r2 = "setBase"
                r7.setLong(r15, r2, r0)
                java.lang.String r0 = "setStarted"
                r7.setBoolean(r15, r0, r9)
                androidx.core.app.NotificationCompat$Builder r0 = r12.mBuilder
                boolean r0 = r0.mChronometerCountDown
                if (r0 == 0) goto L_0x0184
                r1 = 24
                if (r14 < r1) goto L_0x0184
                r7.setChronometerCountDown(r15, r0)
                goto L_0x0184
            L_0x0172:
                int r14 = androidx.core.R$id.time
                r7.setViewVisibility(r14, r8)
                androidx.core.app.NotificationCompat$Builder r15 = r12.mBuilder
                long r0 = r15.getWhenIfShowing()
                java.lang.String r15 = "setTime"
                r7.setLong(r14, r15, r0)
                goto L_0x0184
            L_0x0183:
                r9 = r11
            L_0x0184:
                int r14 = androidx.core.R$id.right_side
                if (r9 == 0) goto L_0x018a
                r15 = 0
                goto L_0x018c
            L_0x018a:
                r15 = 8
            L_0x018c:
                r7.setViewVisibility(r14, r15)
                int r14 = androidx.core.R$id.line3
                if (r13 == 0) goto L_0x0194
                goto L_0x0196
            L_0x0194:
                r8 = 8
            L_0x0196:
                r7.setViewVisibility(r14, r8)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.Style.applyStandardTemplate(boolean, int, boolean):android.widget.RemoteViews");
        }

        private Bitmap createColoredBitmap(int i, int i2, int i3) {
            return createColoredBitmap(IconCompat.createWithResource(this.mBuilder.mContext, i), i2, i3);
        }

        private Bitmap createColoredBitmap(IconCompat iconCompat, int i, int i2) {
            Drawable loadDrawable = iconCompat.loadDrawable(this.mBuilder.mContext);
            int intrinsicWidth = i2 == 0 ? loadDrawable.getIntrinsicWidth() : i2;
            if (i2 == 0) {
                i2 = loadDrawable.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i2, Bitmap.Config.ARGB_8888);
            loadDrawable.setBounds(0, 0, intrinsicWidth, i2);
            if (i != 0) {
                loadDrawable.mutate().setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
            }
            loadDrawable.draw(new Canvas(createBitmap));
            return createBitmap;
        }

        private Bitmap createIconWithBackground(int i, int i2, int i3, int i4) {
            int i5 = R$drawable.notification_icon_background;
            if (i4 == 0) {
                i4 = 0;
            }
            Bitmap createColoredBitmap = createColoredBitmap(i5, i4, i2);
            Canvas canvas = new Canvas(createColoredBitmap);
            Drawable mutate = this.mBuilder.mContext.getResources().getDrawable(i).mutate();
            mutate.setFilterBitmap(true);
            int i6 = (i2 - i3) / 2;
            int i7 = i3 + i6;
            mutate.setBounds(i6, i6, i7, i7);
            mutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            mutate.draw(canvas);
            return createColoredBitmap;
        }

        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
            hideNormalContent(remoteViews);
            int i = R$id.notification_main_column;
            remoteViews.removeAllViews(i);
            remoteViews.addView(i, remoteViews2.clone());
            remoteViews.setViewVisibility(i, 0);
            remoteViews.setViewPadding(R$id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
        }

        private void hideNormalContent(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(R$id.title, 8);
            remoteViews.setViewVisibility(R$id.text2, 8);
            remoteViews.setViewVisibility(R$id.text, 8);
        }

        private int calculateTopPadding() {
            Resources resources = this.mBuilder.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.notification_top_pad_large_text);
            float constrain = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round(((1.0f - constrain) * ((float) dimensionPixelSize)) + (constrain * ((float) dimensionPixelSize2)));
        }
    }

    public class BigTextStyle extends Style {
        private CharSequence mBigText;

        /* access modifiers changed from: protected */
        public String getClassName() {
            return "androidx.core.app.NotificationCompat$BigTextStyle";
        }

        public BigTextStyle bigText(CharSequence charSequence) {
            this.mBigText = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigTextStyle bigText = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
            if (this.mSummaryTextSet) {
                bigText.setSummaryText(this.mSummaryText);
            }
        }

        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
        }
    }

    public class Action {
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private IconCompat mIcon;
        private final boolean mIsContextual;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        public CharSequence title;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Action(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i != 0 ? IconCompat.createWithResource((Resources) null, "", i) : null, charSequence, pendingIntent);
        }

        public Action(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, (RemoteInput[]) null, true, 0, true, false);
        }

        Action(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i, boolean z2, boolean z3) {
            this.mShowsUserInterface = true;
            this.mIcon = iconCompat;
            if (iconCompat != null && iconCompat.getType() == 2) {
                this.icon = iconCompat.getResId();
            }
            this.title = Builder.limitCharSequenceLength(charSequence);
            this.actionIntent = pendingIntent;
            this.mExtras = bundle == null ? new Bundle() : bundle;
            this.mRemoteInputs = remoteInputArr;
            this.mDataOnlyRemoteInputs = remoteInputArr2;
            this.mAllowGeneratedReplies = z;
            this.mSemanticAction = i;
            this.mShowsUserInterface = z2;
            this.mIsContextual = z3;
        }

        public int getIcon() {
            return this.icon;
        }

        public IconCompat getIconCompat() {
            int i;
            if (this.mIcon == null && (i = this.icon) != 0) {
                this.mIcon = IconCompat.createWithResource((Resources) null, "", i);
            }
            return this.mIcon;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean isContextual() {
            return this.mIsContextual;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }
    }

    public static Bundle getExtras(Notification notification) {
        return notification.extras;
    }
}
