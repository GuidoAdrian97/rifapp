.class Landroidx/transition/Slide$3;
.super Landroidx/transition/Slide$CalculateSlideVertical;
.source "Slide.java"


# direct methods
.method constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    .line 116
    invoke-direct {p0, v0}, Landroidx/transition/Slide$CalculateSlideVertical;-><init>(Landroidx/transition/Slide$1;)V

    return-void
.end method


# virtual methods
.method public getGoneY(Landroid/view/ViewGroup;Landroid/view/View;)F
    .locals 0

    .line 119
    invoke-virtual {p2}, Landroid/view/View;->getTranslationY()F

    move-result p2

    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result p1

    int-to-float p1, p1

    sub-float/2addr p2, p1

    return p2
.end method
