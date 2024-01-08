.class Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState$1;
.super Ljava/lang/Object;
.source "SwipeRefreshLayout.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public createFromParcel(Landroid/os/Parcel;)Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState;
    .locals 1

    .line 269
    new-instance v0, Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState;

    invoke-direct {v0, p1}, Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 0

    .line 266
    invoke-virtual {p0, p1}, Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState$1;->createFromParcel(Landroid/os/Parcel;)Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState;

    move-result-object p1

    return-object p1
.end method

.method public newArray(I)[Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState;
    .locals 0

    .line 274
    new-array p1, p1, [Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState;

    return-object p1
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 0

    .line 266
    invoke-virtual {p0, p1}, Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState$1;->newArray(I)[Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$SavedState;

    move-result-object p1

    return-object p1
.end method
