.class abstract Landroidx/core/content/ContextCompat$Api28Impl;
.super Ljava/lang/Object;
.source "ContextCompat.java"


# direct methods
.method static getMainExecutor(Landroid/content/Context;)Ljava/util/concurrent/Executor;
    .locals 0
    .annotation build Landroidx/annotation/DoNotInline;
    .end annotation

    .line 945
    invoke-virtual {p0}, Landroid/content/Context;->getMainExecutor()Ljava/util/concurrent/Executor;

    move-result-object p0

    return-object p0
.end method
