.class Landroidx/core/provider/FontProvider$1;
.super Ljava/lang/Object;
.source "FontProvider.java"

# interfaces
.implements Ljava/util/Comparator;


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic compare(Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 0

    .line 197
    check-cast p1, [B

    check-cast p2, [B

    invoke-virtual {p0, p1, p2}, Landroidx/core/provider/FontProvider$1;->compare([B[B)I

    move-result p1

    return p1
.end method

.method public compare([B[B)I
    .locals 4

    .line 200
    array-length v0, p1

    array-length v1, p2

    if-eq v0, v1, :cond_0

    .line 201
    array-length p1, p1

    array-length p2, p2

    sub-int/2addr p1, p2

    return p1

    :cond_0
    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 203
    :goto_0
    array-length v2, p1

    if-ge v1, v2, :cond_2

    .line 204
    aget-byte v2, p1, v1

    aget-byte v3, p2, v1

    if-eq v2, v3, :cond_1

    sub-int/2addr v2, v3

    return v2

    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    return v0
.end method
