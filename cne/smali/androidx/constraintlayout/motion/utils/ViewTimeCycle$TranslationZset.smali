.class Landroidx/constraintlayout/motion/utils/ViewTimeCycle$TranslationZset;
.super Landroidx/constraintlayout/motion/utils/ViewTimeCycle;
.source "ViewTimeCycle.java"


# direct methods
.method constructor <init>()V
    .locals 0

    .line 204
    invoke-direct {p0}, Landroidx/constraintlayout/motion/utils/ViewTimeCycle;-><init>()V

    return-void
.end method


# virtual methods
.method public setProperty(Landroid/view/View;FJLandroidx/constraintlayout/core/motion/utils/KeyCache;)Z
    .locals 6

    move-object v0, p0

    move v1, p2

    move-wide v2, p3

    move-object v4, p1

    move-object v5, p5

    .line 208
    invoke-virtual/range {v0 .. v5}, Landroidx/constraintlayout/motion/utils/ViewTimeCycle;->get(FJLandroid/view/View;Landroidx/constraintlayout/core/motion/utils/KeyCache;)F

    move-result p2

    invoke-virtual {p1, p2}, Landroid/view/View;->setTranslationZ(F)V

    .line 210
    iget-boolean p1, p0, Landroidx/constraintlayout/core/motion/utils/TimeCycleSplineSet;->mContinue:Z

    return p1
.end method
