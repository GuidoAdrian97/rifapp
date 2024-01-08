.class public Landroidx/browser/customtabs/CustomTabsSession$PendingSession;
.super Ljava/lang/Object;
.source "CustomTabsSession.java"


# annotations
.annotation build Landroidx/annotation/RestrictTo;
.end annotation


# instance fields
.field private final mId:Landroid/app/PendingIntent;


# virtual methods
.method getId()Landroid/app/PendingIntent;
    .locals 1

    .line 344
    iget-object v0, p0, Landroidx/browser/customtabs/CustomTabsSession$PendingSession;->mId:Landroid/app/PendingIntent;

    return-object v0
.end method
