.class final Lcom/google/android/gms/maps/MapView$zzb;
.super Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;


# instance fields
.field private zzbd:Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;

.field private final zzbf:Ljava/util/List;

.field private final zzbj:Landroid/view/ViewGroup;

.field private final zzbk:Landroid/content/Context;

.field private final zzbl:Lcom/google/android/gms/maps/GoogleMapOptions;


# direct methods
.method constructor <init>(Landroid/view/ViewGroup;Landroid/content/Context;Lcom/google/android/gms/maps/GoogleMapOptions;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;-><init>()V

    .line 2
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbf:Ljava/util/List;

    .line 3
    iput-object p1, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbj:Landroid/view/ViewGroup;

    .line 4
    iput-object p2, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbk:Landroid/content/Context;

    .line 5
    iput-object p3, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbl:Lcom/google/android/gms/maps/GoogleMapOptions;

    return-void
.end method


# virtual methods
.method protected final createDelegate(Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;)V
    .locals 3

    .line 7
    iput-object p1, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbd:Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;

    if-eqz p1, :cond_2

    .line 9
    invoke-virtual {p0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->getDelegate()Lcom/google/android/gms/dynamic/LifecycleDelegate;

    move-result-object p1

    if-nez p1, :cond_2

    .line 10
    :try_start_0
    iget-object p1, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbk:Landroid/content/Context;

    invoke-static {p1}, Lcom/google/android/gms/maps/MapsInitializer;->initialize(Landroid/content/Context;)I

    .line 11
    iget-object p1, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbk:Landroid/content/Context;

    .line 12
    invoke-static {p1}, Lcom/google/android/gms/maps/internal/zzbz;->zza(Landroid/content/Context;)Lcom/google/android/gms/maps/internal/zze;

    move-result-object p1

    iget-object v0, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbk:Landroid/content/Context;

    .line 13
    invoke-static {v0}, Lcom/google/android/gms/dynamic/ObjectWrapper;->wrap(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbl:Lcom/google/android/gms/maps/GoogleMapOptions;

    invoke-interface {p1, v0, v1}, Lcom/google/android/gms/maps/internal/zze;->zza(Lcom/google/android/gms/dynamic/IObjectWrapper;Lcom/google/android/gms/maps/GoogleMapOptions;)Lcom/google/android/gms/maps/internal/IMapViewDelegate;

    move-result-object p1

    if-nez p1, :cond_0

    return-void

    .line 16
    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbd:Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;

    new-instance v1, Lcom/google/android/gms/maps/MapView$zza;

    iget-object v2, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbj:Landroid/view/ViewGroup;

    invoke-direct {v1, v2, p1}, Lcom/google/android/gms/maps/MapView$zza;-><init>(Landroid/view/ViewGroup;Lcom/google/android/gms/maps/internal/IMapViewDelegate;)V

    invoke-interface {v0, v1}, Lcom/google/android/gms/dynamic/OnDelegateCreatedListener;->onDelegateCreated(Lcom/google/android/gms/dynamic/LifecycleDelegate;)V

    .line 17
    iget-object p1, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbf:Ljava/util/List;

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/maps/OnMapReadyCallback;

    .line 18
    invoke-virtual {p0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->getDelegate()Lcom/google/android/gms/dynamic/LifecycleDelegate;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/maps/MapView$zza;

    invoke-virtual {v1, v0}, Lcom/google/android/gms/maps/MapView$zza;->getMapAsync(Lcom/google/android/gms/maps/OnMapReadyCallback;)V

    goto :goto_0

    .line 20
    :cond_1
    iget-object p1, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbf:Ljava/util/List;

    invoke-interface {p1}, Ljava/util/List;->clear()V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lcom/google/android/gms/common/GooglePlayServicesNotAvailableException; {:try_start_0 .. :try_end_0} :catch_1

    return-void

    :catch_0
    move-exception p1

    .line 23
    new-instance v0, Lcom/google/android/gms/maps/model/RuntimeRemoteException;

    invoke-direct {v0, p1}, Lcom/google/android/gms/maps/model/RuntimeRemoteException;-><init>(Landroid/os/RemoteException;)V

    throw v0

    :catch_1
    :cond_2
    return-void
.end method

.method public final getMapAsync(Lcom/google/android/gms/maps/OnMapReadyCallback;)V
    .locals 1

    .line 26
    invoke-virtual {p0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->getDelegate()Lcom/google/android/gms/dynamic/LifecycleDelegate;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 27
    invoke-virtual {p0}, Lcom/google/android/gms/dynamic/DeferredLifecycleHelper;->getDelegate()Lcom/google/android/gms/dynamic/LifecycleDelegate;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/maps/MapView$zza;

    invoke-virtual {v0, p1}, Lcom/google/android/gms/maps/MapView$zza;->getMapAsync(Lcom/google/android/gms/maps/OnMapReadyCallback;)V

    return-void

    .line 28
    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/maps/MapView$zzb;->zzbf:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void
.end method
