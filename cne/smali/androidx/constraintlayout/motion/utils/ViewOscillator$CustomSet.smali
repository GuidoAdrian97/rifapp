.class Landroidx/constraintlayout/motion/utils/ViewOscillator$CustomSet;
.super Landroidx/constraintlayout/motion/utils/ViewOscillator;
.source "ViewOscillator.java"


# instance fields
.field protected mCustom:Landroidx/constraintlayout/widget/ConstraintAttribute;

.field value:[F


# direct methods
.method constructor <init>()V
    .locals 1

    .line 163
    invoke-direct {p0}, Landroidx/constraintlayout/motion/utils/ViewOscillator;-><init>()V

    const/4 v0, 0x1

    new-array v0, v0, [F

    .line 164
    iput-object v0, p0, Landroidx/constraintlayout/motion/utils/ViewOscillator$CustomSet;->value:[F

    return-void
.end method


# virtual methods
.method protected setCustom(Ljava/lang/Object;)V
    .locals 0

    .line 168
    check-cast p1, Landroidx/constraintlayout/widget/ConstraintAttribute;

    iput-object p1, p0, Landroidx/constraintlayout/motion/utils/ViewOscillator$CustomSet;->mCustom:Landroidx/constraintlayout/widget/ConstraintAttribute;

    return-void
.end method

.method public setProperty(Landroid/view/View;F)V
    .locals 2

    .line 173
    iget-object v0, p0, Landroidx/constraintlayout/motion/utils/ViewOscillator$CustomSet;->value:[F

    invoke-virtual {p0, p2}, Landroidx/constraintlayout/core/motion/utils/KeyCycleOscillator;->get(F)F

    move-result p2

    const/4 v1, 0x0

    aput p2, v0, v1

    .line 174
    iget-object p2, p0, Landroidx/constraintlayout/motion/utils/ViewOscillator$CustomSet;->mCustom:Landroidx/constraintlayout/widget/ConstraintAttribute;

    iget-object v0, p0, Landroidx/constraintlayout/motion/utils/ViewOscillator$CustomSet;->value:[F

    invoke-virtual {p2, p1, v0}, Landroidx/constraintlayout/widget/ConstraintAttribute;->setInterpolatedValue(Landroid/view/View;[F)V

    return-void
.end method
