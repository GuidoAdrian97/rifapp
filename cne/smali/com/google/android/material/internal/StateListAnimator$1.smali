.class Lcom/google/android/material/internal/StateListAnimator$1;
.super Landroid/animation/AnimatorListenerAdapter;
.source "StateListAnimator.java"


# instance fields
.field final synthetic this$0:Lcom/google/android/material/internal/StateListAnimator;


# direct methods
.method constructor <init>(Lcom/google/android/material/internal/StateListAnimator;)V
    .locals 0

    .line 45
    iput-object p1, p0, Lcom/google/android/material/internal/StateListAnimator$1;->this$0:Lcom/google/android/material/internal/StateListAnimator;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    .line 48
    iget-object v0, p0, Lcom/google/android/material/internal/StateListAnimator$1;->this$0:Lcom/google/android/material/internal/StateListAnimator;

    iget-object v1, v0, Lcom/google/android/material/internal/StateListAnimator;->runningAnimator:Landroid/animation/ValueAnimator;

    if-ne v1, p1, :cond_0

    const/4 p1, 0x0

    .line 49
    iput-object p1, v0, Lcom/google/android/material/internal/StateListAnimator;->runningAnimator:Landroid/animation/ValueAnimator;

    :cond_0
    return-void
.end method
