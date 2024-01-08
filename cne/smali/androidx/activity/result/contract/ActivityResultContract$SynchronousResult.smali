.class public final Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;
.super Ljava/lang/Object;
.source "ActivityResultContract.java"


# instance fields
.field private final mValue:Ljava/lang/Object;


# direct methods
.method public constructor <init>(Ljava/lang/Object;)V
    .locals 0
    .param p1    # Ljava/lang/Object;
        .annotation build Landroid/annotation/SuppressLint;
            value = {
                "UnknownNullness"
            }
        .end annotation
    .end param

    .line 75
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 76
    iput-object p1, p0, Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;->mValue:Ljava/lang/Object;

    return-void
.end method


# virtual methods
.method public getValue()Ljava/lang/Object;
    .locals 1
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "UnknownNullness"
        }
    .end annotation

    .line 83
    iget-object v0, p0, Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;->mValue:Ljava/lang/Object;

    return-object v0
.end method
