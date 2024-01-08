.class final Lcom/google/android/material/transition/platform/ScaleProvider$1;
.super Landroid/animation/AnimatorListenerAdapter;
.source "ScaleProvider.java"


# instance fields
.field final synthetic val$originalScaleX:F

.field final synthetic val$originalScaleY:F

.field final synthetic val$view:Landroid/view/View;


# direct methods
.method constructor <init>(Landroid/view/View;FF)V
    .locals 0

    .line 187
    iput-object p1, p0, Lcom/google/android/material/transition/platform/ScaleProvider$1;->val$view:Landroid/view/View;

    iput p2, p0, Lcom/google/android/material/transition/platform/ScaleProvider$1;->val$originalScaleX:F

    iput p3, p0, Lcom/google/android/material/transition/platform/ScaleProvider$1;->val$originalScaleY:F

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 1

    .line 190
    iget-object p1, p0, Lcom/google/android/material/transition/platform/ScaleProvider$1;->val$view:Landroid/view/View;

    iget v0, p0, Lcom/google/android/material/transition/platform/ScaleProvider$1;->val$originalScaleX:F

    invoke-virtual {p1, v0}, Landroid/view/View;->setScaleX(F)V

    .line 191
    iget-object p1, p0, Lcom/google/android/material/transition/platform/ScaleProvider$1;->val$view:Landroid/view/View;

    iget v0, p0, Lcom/google/android/material/transition/platform/ScaleProvider$1;->val$originalScaleY:F

    invoke-virtual {p1, v0}, Landroid/view/View;->setScaleY(F)V

    return-void
.end method
