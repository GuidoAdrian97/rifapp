.class abstract Landroidx/transition/Slide$CalculateSlideHorizontal;
.super Ljava/lang/Object;
.source "Slide.java"

# interfaces
.implements Landroidx/transition/Slide$CalculateSlide;


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Landroidx/transition/Slide$1;)V
    .locals 0

    .line 78
    invoke-direct {p0}, Landroidx/transition/Slide$CalculateSlideHorizontal;-><init>()V

    return-void
.end method


# virtual methods
.method public getGoneY(Landroid/view/ViewGroup;Landroid/view/View;)F
    .locals 0

    .line 82
    invoke-virtual {p2}, Landroid/view/View;->getTranslationY()F

    move-result p1

    return p1
.end method
