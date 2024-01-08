.class abstract Landroidx/media/AudioManagerCompat$Api28Impl;
.super Ljava/lang/Object;
.source "AudioManagerCompat.java"


# direct methods
.method static getStreamMinVolume(Landroid/media/AudioManager;I)I
    .locals 0
    .annotation build Landroidx/annotation/DoNotInline;
    .end annotation

    .line 182
    invoke-virtual {p0, p1}, Landroid/media/AudioManager;->getStreamMinVolume(I)I

    move-result p0

    return p0
.end method
