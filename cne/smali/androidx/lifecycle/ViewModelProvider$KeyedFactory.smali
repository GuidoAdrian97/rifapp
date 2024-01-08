.class abstract Landroidx/lifecycle/ViewModelProvider$KeyedFactory;
.super Landroidx/lifecycle/ViewModelProvider$OnRequeryFactory;
.source "ViewModelProvider.java"

# interfaces
.implements Landroidx/lifecycle/ViewModelProvider$Factory;


# direct methods
.method constructor <init>()V
    .locals 0

    .line 65
    invoke-direct {p0}, Landroidx/lifecycle/ViewModelProvider$OnRequeryFactory;-><init>()V

    return-void
.end method


# virtual methods
.method public abstract create(Ljava/lang/String;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;
.end method
