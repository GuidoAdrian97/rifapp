.class public final Landroidx/concurrent/futures/ResolvableFuture;
.super Landroidx/concurrent/futures/AbstractResolvableFuture;
.source "ResolvableFuture.java"


# annotations
.annotation build Landroidx/annotation/RestrictTo;
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 63
    invoke-direct {p0}, Landroidx/concurrent/futures/AbstractResolvableFuture;-><init>()V

    return-void
.end method

.method public static create()Landroidx/concurrent/futures/ResolvableFuture;
    .locals 1

    .line 45
    new-instance v0, Landroidx/concurrent/futures/ResolvableFuture;

    invoke-direct {v0}, Landroidx/concurrent/futures/ResolvableFuture;-><init>()V

    return-object v0
.end method


# virtual methods
.method public set(Ljava/lang/Object;)Z
    .locals 0
    .param p1    # Ljava/lang/Object;
        .annotation build Landroidx/annotation/Nullable;
        .end annotation
    .end param

    .line 50
    invoke-super {p0, p1}, Landroidx/concurrent/futures/AbstractResolvableFuture;->set(Ljava/lang/Object;)Z

    move-result p1

    return p1
.end method

.method public setException(Ljava/lang/Throwable;)Z
    .locals 0

    .line 55
    invoke-super {p0, p1}, Landroidx/concurrent/futures/AbstractResolvableFuture;->setException(Ljava/lang/Throwable;)Z

    move-result p1

    return p1
.end method

.method public setFuture(Lcom/google/common/util/concurrent/ListenableFuture;)Z
    .locals 0

    .line 60
    invoke-super {p0, p1}, Landroidx/concurrent/futures/AbstractResolvableFuture;->setFuture(Lcom/google/common/util/concurrent/ListenableFuture;)Z

    move-result p1

    return p1
.end method
