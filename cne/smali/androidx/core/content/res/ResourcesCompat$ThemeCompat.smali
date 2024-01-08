.class public abstract Landroidx/core/content/res/ResourcesCompat$ThemeCompat;
.super Ljava/lang/Object;
.source "ResourcesCompat.java"


# direct methods
.method public static rebase(Landroid/content/res/Resources$Theme;)V
    .locals 2

    .line 653
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1d

    if-lt v0, v1, :cond_0

    .line 654
    invoke-static {p0}, Landroidx/core/content/res/ResourcesCompat$ThemeCompat$ImplApi29;->rebase(Landroid/content/res/Resources$Theme;)V

    goto :goto_0

    :cond_0
    const/16 v1, 0x17

    if-lt v0, v1, :cond_1

    .line 656
    invoke-static {p0}, Landroidx/core/content/res/ResourcesCompat$ThemeCompat$ImplApi23;->rebase(Landroid/content/res/Resources$Theme;)V

    :cond_1
    :goto_0
    return-void
.end method
