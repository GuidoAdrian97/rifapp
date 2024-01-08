.class abstract Landroidx/core/view/WindowCompat$Impl30;
.super Ljava/lang/Object;
.source "WindowCompat.java"


# direct methods
.method static getInsetsController(Landroid/view/Window;)Landroidx/core/view/WindowInsetsControllerCompat;
    .locals 0

    .line 166
    invoke-virtual {p0}, Landroid/view/Window;->getInsetsController()Landroid/view/WindowInsetsController;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 168
    invoke-static {p0}, Landroidx/core/view/WindowInsetsControllerCompat;->toWindowInsetsControllerCompat(Landroid/view/WindowInsetsController;)Landroidx/core/view/WindowInsetsControllerCompat;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method
