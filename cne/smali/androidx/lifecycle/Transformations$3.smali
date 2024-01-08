.class Landroidx/lifecycle/Transformations$3;
.super Ljava/lang/Object;
.source "Transformations.java"

# interfaces
.implements Landroidx/lifecycle/Observer;


# instance fields
.field mFirstTime:Z

.field final synthetic val$outputLiveData:Landroidx/lifecycle/MediatorLiveData;


# direct methods
.method constructor <init>(Landroidx/lifecycle/MediatorLiveData;)V
    .locals 0

    .line 177
    iput-object p1, p0, Landroidx/lifecycle/Transformations$3;->val$outputLiveData:Landroidx/lifecycle/MediatorLiveData;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x1

    .line 179
    iput-boolean p1, p0, Landroidx/lifecycle/Transformations$3;->mFirstTime:Z

    return-void
.end method


# virtual methods
.method public onChanged(Ljava/lang/Object;)V
    .locals 2

    .line 183
    iget-object v0, p0, Landroidx/lifecycle/Transformations$3;->val$outputLiveData:Landroidx/lifecycle/MediatorLiveData;

    invoke-virtual {v0}, Landroidx/lifecycle/LiveData;->getValue()Ljava/lang/Object;

    move-result-object v0

    .line 184
    iget-boolean v1, p0, Landroidx/lifecycle/Transformations$3;->mFirstTime:Z

    if-nez v1, :cond_1

    if-nez v0, :cond_0

    if-nez p1, :cond_1

    :cond_0
    if-eqz v0, :cond_2

    .line 186
    invoke-virtual {v0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    :cond_1
    const/4 v0, 0x0

    .line 187
    iput-boolean v0, p0, Landroidx/lifecycle/Transformations$3;->mFirstTime:Z

    .line 188
    iget-object v0, p0, Landroidx/lifecycle/Transformations$3;->val$outputLiveData:Landroidx/lifecycle/MediatorLiveData;

    invoke-virtual {v0, p1}, Landroidx/lifecycle/MutableLiveData;->setValue(Ljava/lang/Object;)V

    :cond_2
    return-void
.end method
