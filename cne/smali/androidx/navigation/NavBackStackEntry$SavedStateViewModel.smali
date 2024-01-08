.class Landroidx/navigation/NavBackStackEntry$SavedStateViewModel;
.super Landroidx/lifecycle/ViewModel;
.source "NavBackStackEntry.java"


# instance fields
.field private mHandle:Landroidx/lifecycle/SavedStateHandle;


# direct methods
.method constructor <init>(Landroidx/lifecycle/SavedStateHandle;)V
    .locals 0

    .line 251
    invoke-direct {p0}, Landroidx/lifecycle/ViewModel;-><init>()V

    .line 252
    iput-object p1, p0, Landroidx/navigation/NavBackStackEntry$SavedStateViewModel;->mHandle:Landroidx/lifecycle/SavedStateHandle;

    return-void
.end method


# virtual methods
.method public getHandle()Landroidx/lifecycle/SavedStateHandle;
    .locals 1

    .line 256
    iget-object v0, p0, Landroidx/navigation/NavBackStackEntry$SavedStateViewModel;->mHandle:Landroidx/lifecycle/SavedStateHandle;

    return-object v0
.end method
