.class Landroidx/fragment/app/DefaultSpecialEffectsController$7;
.super Ljava/lang/Object;
.source "DefaultSpecialEffectsController.java"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field final synthetic this$0:Landroidx/fragment/app/DefaultSpecialEffectsController;

.field final synthetic val$impl:Landroidx/fragment/app/FragmentTransitionImpl;

.field final synthetic val$lastInEpicenterRect:Landroid/graphics/Rect;

.field final synthetic val$lastInEpicenterView:Landroid/view/View;


# direct methods
.method constructor <init>(Landroidx/fragment/app/DefaultSpecialEffectsController;Landroidx/fragment/app/FragmentTransitionImpl;Landroid/view/View;Landroid/graphics/Rect;)V
    .locals 0

    .line 485
    iput-object p1, p0, Landroidx/fragment/app/DefaultSpecialEffectsController$7;->this$0:Landroidx/fragment/app/DefaultSpecialEffectsController;

    iput-object p2, p0, Landroidx/fragment/app/DefaultSpecialEffectsController$7;->val$impl:Landroidx/fragment/app/FragmentTransitionImpl;

    iput-object p3, p0, Landroidx/fragment/app/DefaultSpecialEffectsController$7;->val$lastInEpicenterView:Landroid/view/View;

    iput-object p4, p0, Landroidx/fragment/app/DefaultSpecialEffectsController$7;->val$lastInEpicenterRect:Landroid/graphics/Rect;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .line 488
    iget-object v0, p0, Landroidx/fragment/app/DefaultSpecialEffectsController$7;->val$impl:Landroidx/fragment/app/FragmentTransitionImpl;

    iget-object v1, p0, Landroidx/fragment/app/DefaultSpecialEffectsController$7;->val$lastInEpicenterView:Landroid/view/View;

    iget-object v2, p0, Landroidx/fragment/app/DefaultSpecialEffectsController$7;->val$lastInEpicenterRect:Landroid/graphics/Rect;

    invoke-virtual {v0, v1, v2}, Landroidx/fragment/app/FragmentTransitionImpl;->getBoundsOnScreen(Landroid/view/View;Landroid/graphics/Rect;)V

    return-void
.end method
