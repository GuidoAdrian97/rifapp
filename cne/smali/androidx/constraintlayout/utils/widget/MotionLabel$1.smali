.class Landroidx/constraintlayout/utils/widget/MotionLabel$1;
.super Landroid/view/ViewOutlineProvider;
.source "MotionLabel.java"


# instance fields
.field final synthetic this$0:Landroidx/constraintlayout/utils/widget/MotionLabel;


# direct methods
.method constructor <init>(Landroidx/constraintlayout/utils/widget/MotionLabel;)V
    .locals 0

    .line 698
    iput-object p1, p0, Landroidx/constraintlayout/utils/widget/MotionLabel$1;->this$0:Landroidx/constraintlayout/utils/widget/MotionLabel;

    invoke-direct {p0}, Landroid/view/ViewOutlineProvider;-><init>()V

    return-void
.end method


# virtual methods
.method public getOutline(Landroid/view/View;Landroid/graphics/Outline;)V
    .locals 6

    .line 701
    iget-object p1, p0, Landroidx/constraintlayout/utils/widget/MotionLabel$1;->this$0:Landroidx/constraintlayout/utils/widget/MotionLabel;

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v3

    .line 702
    iget-object p1, p0, Landroidx/constraintlayout/utils/widget/MotionLabel$1;->this$0:Landroidx/constraintlayout/utils/widget/MotionLabel;

    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v4

    .line 703
    invoke-static {v3, v4}, Ljava/lang/Math;->min(II)I

    move-result p1

    int-to-float p1, p1

    iget-object v0, p0, Landroidx/constraintlayout/utils/widget/MotionLabel$1;->this$0:Landroidx/constraintlayout/utils/widget/MotionLabel;

    invoke-static {v0}, Landroidx/constraintlayout/utils/widget/MotionLabel;->access$000(Landroidx/constraintlayout/utils/widget/MotionLabel;)F

    move-result v0

    mul-float p1, p1, v0

    const/high16 v0, 0x40000000    # 2.0f

    div-float v5, p1, v0

    const/4 v1, 0x0

    const/4 v2, 0x0

    move-object v0, p2

    .line 704
    invoke-virtual/range {v0 .. v5}, Landroid/graphics/Outline;->setRoundRect(IIIIF)V

    return-void
.end method
