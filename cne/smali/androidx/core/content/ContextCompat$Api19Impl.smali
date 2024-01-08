.class abstract Landroidx/core/content/ContextCompat$Api19Impl;
.super Ljava/lang/Object;
.source "ContextCompat.java"


# direct methods
.method static getExternalCacheDirs(Landroid/content/Context;)[Ljava/io/File;
    .locals 0
    .annotation build Landroidx/annotation/DoNotInline;
    .end annotation

    .line 840
    invoke-virtual {p0}, Landroid/content/Context;->getExternalCacheDirs()[Ljava/io/File;

    move-result-object p0

    return-object p0
.end method

.method static getExternalFilesDirs(Landroid/content/Context;Ljava/lang/String;)[Ljava/io/File;
    .locals 0
    .annotation build Landroidx/annotation/DoNotInline;
    .end annotation

    .line 845
    invoke-virtual {p0, p1}, Landroid/content/Context;->getExternalFilesDirs(Ljava/lang/String;)[Ljava/io/File;

    move-result-object p0

    return-object p0
.end method

.method static getObbDirs(Landroid/content/Context;)[Ljava/io/File;
    .locals 0
    .annotation build Landroidx/annotation/DoNotInline;
    .end annotation

    .line 850
    invoke-virtual {p0}, Landroid/content/Context;->getObbDirs()[Ljava/io/File;

    move-result-object p0

    return-object p0
.end method
