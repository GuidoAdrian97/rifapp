.class Landroidx/navigation/NavControllerViewModel$1;
.super Ljava/lang/Object;
.source "NavControllerViewModel.java"

# interfaces
.implements Landroidx/lifecycle/ViewModelProvider$Factory;


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public create(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;
    .locals 0

    .line 39
    new-instance p1, Landroidx/navigation/NavControllerViewModel;

    invoke-direct {p1}, Landroidx/navigation/NavControllerViewModel;-><init>()V

    return-object p1
.end method
