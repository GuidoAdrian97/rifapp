.class Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;
.super Landroidx/lifecycle/MutableLiveData;
.source "SavedStateHandle.java"


# instance fields
.field private mHandle:Landroidx/lifecycle/SavedStateHandle;

.field private mKey:Ljava/lang/String;


# direct methods
.method constructor <init>(Landroidx/lifecycle/SavedStateHandle;Ljava/lang/String;)V
    .locals 0

    .line 357
    invoke-direct {p0}, Landroidx/lifecycle/MutableLiveData;-><init>()V

    .line 358
    iput-object p2, p0, Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;->mKey:Ljava/lang/String;

    .line 359
    iput-object p1, p0, Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;->mHandle:Landroidx/lifecycle/SavedStateHandle;

    return-void
.end method

.method constructor <init>(Landroidx/lifecycle/SavedStateHandle;Ljava/lang/String;Ljava/lang/Object;)V
    .locals 0

    .line 351
    invoke-direct {p0, p3}, Landroidx/lifecycle/MutableLiveData;-><init>(Ljava/lang/Object;)V

    .line 352
    iput-object p2, p0, Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;->mKey:Ljava/lang/String;

    .line 353
    iput-object p1, p0, Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;->mHandle:Landroidx/lifecycle/SavedStateHandle;

    return-void
.end method


# virtual methods
.method detach()V
    .locals 1

    const/4 v0, 0x0

    .line 371
    iput-object v0, p0, Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;->mHandle:Landroidx/lifecycle/SavedStateHandle;

    return-void
.end method

.method public setValue(Ljava/lang/Object;)V
    .locals 2

    .line 364
    iget-object v0, p0, Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;->mHandle:Landroidx/lifecycle/SavedStateHandle;

    if-eqz v0, :cond_0

    .line 365
    iget-object v0, v0, Landroidx/lifecycle/SavedStateHandle;->mRegular:Ljava/util/Map;

    iget-object v1, p0, Landroidx/lifecycle/SavedStateHandle$SavingStateLiveData;->mKey:Ljava/lang/String;

    invoke-interface {v0, v1, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 367
    :cond_0
    invoke-super {p0, p1}, Landroidx/lifecycle/MutableLiveData;->setValue(Ljava/lang/Object;)V

    return-void
.end method
