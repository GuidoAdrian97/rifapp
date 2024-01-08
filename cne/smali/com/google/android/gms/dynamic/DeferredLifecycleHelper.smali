.class public abstract Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;
.super Ljava/lang/Object;
.source "com.google.android.gms:play-services-base@@17.1.0"


# instance fields
.field private zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

.field private zarv:Landroid/os/Bundle;

.field private zarw:Ljava/util/LinkedList;

.field private final zarx:Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    new-instance v0, Lcom/google/android/gms/dynamic/zaa;

    invoke-direct {v0, p0}, Lcom/google/android/gms/dynamic/zaa;-><init>(Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;)V

    iput-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarx:Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;

    return-void
.end method

.method public static showGooglePlayUnavailableMessage(Landroid/widget/FrameLayout;)V
    .locals 8

    .line 31
    invoke-static {}, Lcom/google/android/gms/common/GoogleApiAvailability;->getInstance()Lcom/google/android/gms/common/GoogleApiAvailability;

    move-result-object v0

    .line 32
    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    .line 33
    invoke-virtual {v0, v1}, Lcom/google/android/gms/common/GoogleApiAvailability;->isGooglePlayServicesAvailable(Landroid/content/Context;)I

    move-result v2

    .line 34
    invoke-static {v1, v2}, Lcom/google/android/gms/common/internal/ConnectionErrorMessages;->getErrorMessage(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object v3

    .line 35
    invoke-static {v1, v2}, Lcom/google/android/gms/common/internal/ConnectionErrorMessages;->getErrorDialogButtonMessage(Landroid/content/Context;I)Ljava/lang/String;

    move-result-object v4

    .line 36
    new-instance v5, Landroid/widget/LinearLayout;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v6

    invoke-direct {v5, v6}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 v6, 0x1

    .line 37
    invoke-virtual {v5, v6}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 38
    new-instance v6, Landroid/widget/FrameLayout$LayoutParams;

    const/4 v7, -0x2

    invoke-direct {v6, v7, v7}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v5, v6}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 39
    invoke-virtual {p0, v5}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 40
    new-instance v6, Landroid/widget/TextView;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-direct {v6, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 41
    new-instance p0, Landroid/widget/FrameLayout$LayoutParams;

    invoke-direct {p0, v7, v7}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v6, p0}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 42
    invoke-virtual {v6, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 43
    invoke-virtual {v5, v6}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    const/4 p0, 0x0

    .line 45
    invoke-virtual {v0, v1, v2, p0}, Lcom/google/android/gms/common/GoogleApiAvailability;->getErrorResolutionIntent(Landroid/content/Context;ILjava/lang/String;)Landroid/content/Intent;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 47
    new-instance v0, Landroid/widget/Button;

    invoke-direct {v0, v1}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    const v2, 0x1020019

    .line 48
    invoke-virtual {v0, v2}, Landroid/view/View;->setId(I)V

    .line 49
    new-instance v2, Landroid/widget/FrameLayout$LayoutParams;

    invoke-direct {v2, v7, v7}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v0, v2}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 50
    invoke-virtual {v0, v4}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 51
    invoke-virtual {v5, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 52
    new-instance v2, Lcom/google/android/gms/dynamic/zad;

    invoke-direct {v2, v1, p0}, Lcom/google/android/gms/dynamic/zad;-><init>(Landroid/content/Context;Landroid/content/Intent;)V

    invoke-virtual {v0, v2}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    :cond_0
    return-void
.end method

.method static synthetic zaa(Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;Landroid/os/Bundle;)Landroid/os/Bundle;
    .locals 0

    const/4 p1, 0x0

    .line 85
    iput-object p1, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarv:Landroid/os/Bundle;

    return-object p1
.end method

.method static synthetic zaa(Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;Lcom/google/android/gms/dynamic/LifecycleDelegate;)Lcom/google/android/gms/dynamic/LifecycleDelegate;
    .locals 0

    .line 82
    iput-object p1, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    return-object p1
.end method

.method static synthetic zaa(Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;)Ljava/util/LinkedList;
    .locals 0

    .line 83
    iget-object p0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarw:Ljava/util/LinkedList;

    return-object p0
.end method

.method private final zaa(Landroid/os/Bundle;Lcom/google/android/gms/dynamic/DeferredLifecycleHelper$zaa;)V
    .locals 1

    .line 8
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    if-eqz v0, :cond_0

    .line 9
    invoke-interface {p2, v0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper$zaa;->zaa(Lcom/google/android/gms/dynamic/LifecycleDelegate;)V

    return-void

    .line 11
    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarw:Ljava/util/LinkedList;

    if-nez v0, :cond_1

    .line 12
    new-instance v0, Ljava/util/LinkedList;

    invoke-direct {v0}, Ljava/util/LinkedList;-><init>()V

    iput-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarw:Ljava/util/LinkedList;

    .line 13
    :cond_1
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarw:Ljava/util/LinkedList;

    invoke-virtual {v0, p2}, Ljava/util/LinkedList;->add(Ljava/lang/Object;)Z

    if-eqz p1, :cond_3

    .line 15
    iget-object p2, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarv:Landroid/os/Bundle;

    if-nez p2, :cond_2

    .line 16
    invoke-virtual {p1}, Landroid/os/Bundle;->clone()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/os/Bundle;

    iput-object p1, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarv:Landroid/os/Bundle;

    goto :goto_0

    .line 17
    :cond_2
    invoke-virtual {p2, p1}, Landroid/os/Bundle;->putAll(Landroid/os/Bundle;)V

    .line 18
    :cond_3
    :goto_0
    iget-object p1, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarx:Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->createDelegate(Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;)V

    return-void
.end method

.method static synthetic zab(Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;)Lcom/google/android/gms/dynamic/LifecycleDelegate;
    .locals 0

    .line 84
    iget-object p0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    return-object p0
.end method

.method private final zal(I)V
    .locals 1

    .line 5
    :goto_0
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarw:Ljava/util/LinkedList;

    invoke-virtual {v0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarw:Ljava/util/LinkedList;

    invoke-virtual {v0}, Ljava/util/LinkedList;->getLast()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper$zaa;

    invoke-interface {v0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper$zaa;->getState()I

    move-result v0

    if-lt v0, p1, :cond_0

    .line 6
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarw:Ljava/util/LinkedList;

    invoke-virtual {v0}, Ljava/util/LinkedList;->removeLast()Ljava/lang/Object;

    goto :goto_0

    :cond_0
    return-void
.end method


# virtual methods
.method protected abstract createDelegate(Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;)V
.end method

.method public getDelegate()Lcom/google/android/gms/dynamic/LifecycleDelegate;
    .locals 1

    .line 4
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    return-object v0
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    .line 22
    new-instance v0, Lcom/google/android/gms/dynamic/zab;

    invoke-direct {v0, p0, p1}, Lcom/google/android/gms/dynamic/zab;-><init>(Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;Landroid/os/Bundle;)V

    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaa(Landroid/os/Bundle;Lcom/google/android/gms/dynamic/DeferredLifecycleHelper$zaa;)V

    return-void
.end method

.method public onDestroy()V
    .locals 1

    .line 70
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    if-eqz v0, :cond_0

    .line 71
    invoke-interface {v0}, Lcom/google/android/gms/dynamic/LifecycleDelegate;->onDestroy()V

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 72
    invoke-direct {p0, v0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zal(I)V

    return-void
.end method

.method public onLowMemory()V
    .locals 1

    .line 79
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    if-eqz v0, :cond_0

    .line 80
    invoke-interface {v0}, Lcom/google/android/gms/dynamic/LifecycleDelegate;->onLowMemory()V

    :cond_0
    return-void
.end method

.method public onPause()V
    .locals 1

    .line 58
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    if-eqz v0, :cond_0

    .line 59
    invoke-interface {v0}, Lcom/google/android/gms/dynamic/LifecycleDelegate;->onPause()V

    return-void

    :cond_0
    const/4 v0, 0x5

    .line 60
    invoke-direct {p0, v0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zal(I)V

    return-void
.end method

.method public onResume()V
    .locals 2

    .line 56
    new-instance v0, Lcom/google/android/gms/dynamic/zaf;

    invoke-direct {v0, p0}, Lcom/google/android/gms/dynamic/zaf;-><init>(Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;)V

    const/4 v1, 0x0

    invoke-direct {p0, v1, v0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaa(Landroid/os/Bundle;Lcom/google/android/gms/dynamic/DeferredLifecycleHelper$zaa;)V

    return-void
.end method

.method public onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 1

    .line 74
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    if-eqz v0, :cond_0

    .line 75
    invoke-interface {v0, p1}, Lcom/google/android/gms/dynamic/LifecycleDelegate;->onSaveInstanceState(Landroid/os/Bundle;)V

    return-void

    .line 76
    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zarv:Landroid/os/Bundle;

    if-eqz v0, :cond_1

    .line 77
    invoke-virtual {p1, v0}, Landroid/os/Bundle;->putAll(Landroid/os/Bundle;)V

    :cond_1
    return-void
.end method

.method public onStart()V
    .locals 2

    .line 54
    new-instance v0, Lcom/google/android/gms/dynamic/zag;

    invoke-direct {v0, p0}, Lcom/google/android/gms/dynamic/zag;-><init>(Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;)V

    const/4 v1, 0x0

    invoke-direct {p0, v1, v0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaa(Landroid/os/Bundle;Lcom/google/android/gms/dynamic/DeferredLifecycleHelper$zaa;)V

    return-void
.end method

.method public onStop()V
    .locals 1

    .line 62
    iget-object v0, p0, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zaru:Lcom/google/android/gms/dynamic/LifecycleDelegate;

    if-eqz v0, :cond_0

    .line 63
    invoke-interface {v0}, Lcom/google/android/gms/dynamic/LifecycleDelegate;->onStop()V

    return-void

    :cond_0
    const/4 v0, 0x4

    .line 64
    invoke-direct {p0, v0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->zal(I)V

    return-void
.end method
