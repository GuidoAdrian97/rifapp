.class Lcom/google/android/material/transformation/FabTransformationBehavior$4;
.super Landroid/animation/AnimatorListenerAdapter;
.source "FabTransformationBehavior.java"


# instance fields
.field final synthetic this$0:Lcom/google/android/material/transformation/FabTransformationBehavior;

.field final synthetic val$circularRevealChild:Lcom/google/android/material/circularreveal/CircularRevealWidget;


# direct methods
.method constructor <init>(Lcom/google/android/material/transformation/FabTransformationBehavior;Lcom/google/android/material/circularreveal/CircularRevealWidget;)V
    .locals 0

    .line 405
    iput-object p1, p0, Lcom/google/android/material/transformation/FabTransformationBehavior$4;->this$0:Lcom/google/android/material/transformation/FabTransformationBehavior;

    iput-object p2, p0, Lcom/google/android/material/transformation/FabTransformationBehavior$4;->val$circularRevealChild:Lcom/google/android/material/circularreveal/CircularRevealWidget;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 1

    .line 410
    iget-object p1, p0, Lcom/google/android/material/transformation/FabTransformationBehavior$4;->val$circularRevealChild:Lcom/google/android/material/circularreveal/CircularRevealWidget;

    invoke-interface {p1}, Lcom/google/android/material/circularreveal/CircularRevealWidget;->getRevealInfo()Lcom/google/android/material/circularreveal/CircularRevealWidget$RevealInfo;

    move-result-object p1

    const v0, 0x7f7fffff    # Float.MAX_VALUE

    .line 411
    iput v0, p1, Lcom/google/android/material/circularreveal/CircularRevealWidget$RevealInfo;->radius:F

    .line 412
    iget-object v0, p0, Lcom/google/android/material/transformation/FabTransformationBehavior$4;->val$circularRevealChild:Lcom/google/android/material/circularreveal/CircularRevealWidget;

    invoke-interface {v0, p1}, Lcom/google/android/material/circularreveal/CircularRevealWidget;->setRevealInfo(Lcom/google/android/material/circularreveal/CircularRevealWidget$RevealInfo;)V

    return-void
.end method
