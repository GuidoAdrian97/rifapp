.class abstract Landroidx/appcompat/graphics/drawable/DrawableContainer$Api21Impl;
.super Ljava/lang/Object;
.source "DrawableContainer.java"


# direct methods
.method public static canApplyTheme(Landroid/graphics/drawable/Drawable$ConstantState;)Z
    .locals 0

    .line 1218
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable$ConstantState;->canApplyTheme()Z

    move-result p0

    return p0
.end method

.method public static getOutline(Landroid/graphics/drawable/Drawable;Landroid/graphics/Outline;)V
    .locals 0

    .line 1226
    invoke-virtual {p0, p1}, Landroid/graphics/drawable/Drawable;->getOutline(Landroid/graphics/Outline;)V

    return-void
.end method

.method public static getResources(Landroid/content/res/Resources$Theme;)Landroid/content/res/Resources;
    .locals 0

    .line 1222
    invoke-virtual {p0}, Landroid/content/res/Resources$Theme;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    return-object p0
.end method
