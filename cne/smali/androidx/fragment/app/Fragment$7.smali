.class Landroidx/fragment/app/Fragment$7;
.super Ljava/lang/Object;
.source "Fragment.java"

# interfaces
.implements Landroidx/arch/core/util/Function;


# instance fields
.field final synthetic this$0:Landroidx/fragment/app/Fragment;

.field final synthetic val$registry:Landroidx/activity/result/ActivityResultRegistry;


# direct methods
.method constructor <init>(Landroidx/fragment/app/Fragment;Landroidx/activity/result/ActivityResultRegistry;)V
    .locals 0

    .line 3467
    iput-object p1, p0, Landroidx/fragment/app/Fragment$7;->this$0:Landroidx/fragment/app/Fragment;

    iput-object p2, p0, Landroidx/fragment/app/Fragment$7;->val$registry:Landroidx/activity/result/ActivityResultRegistry;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public apply(Ljava/lang/Void;)Landroidx/activity/result/ActivityResultRegistry;
    .locals 0

    .line 3470
    iget-object p1, p0, Landroidx/fragment/app/Fragment$7;->val$registry:Landroidx/activity/result/ActivityResultRegistry;

    return-object p1
.end method

.method public bridge synthetic apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 3467
    check-cast p1, Ljava/lang/Void;

    invoke-virtual {p0, p1}, Landroidx/fragment/app/Fragment$7;->apply(Ljava/lang/Void;)Landroidx/activity/result/ActivityResultRegistry;

    move-result-object p1

    return-object p1
.end method
