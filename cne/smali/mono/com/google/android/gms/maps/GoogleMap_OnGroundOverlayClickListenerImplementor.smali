.class public Lmono/com/google/android/gms/maps/GoogleMap_OnGroundOverlayClickListenerImplementor;
.super Ljava/lang/Object;
.source "GoogleMap_OnGroundOverlayClickListenerImplementor.java"

# interfaces
.implements Lmono/android/IGCUserPeer;
.implements Lcom/google/android/gms/maps/GoogleMap$OnGroundOverlayClickListener;


# static fields
.field public static final __md_methods:Ljava/lang/String; = "n_onGroundOverlayClick:(Lcom/google/android/gms/maps/model/GroundOverlay;)V:GetOnGroundOverlayClick_Lcom_google_android_gms_maps_model_GroundOverlay_Handler:Android.Gms.Maps.GoogleMap/IOnGroundOverlayClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n"


# instance fields
.field private refList:Ljava/util/ArrayList;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 16
    const-class v0, Lmono/com/google/android/gms/maps/GoogleMap_OnGroundOverlayClickListenerImplementor;

    const-string v1, "Android.Gms.Maps.GoogleMap+IOnGroundOverlayClickListenerImplementor, Xamarin.GooglePlayServices.Maps"

    const-string v2, "n_onGroundOverlayClick:(Lcom/google/android/gms/maps/model/GroundOverlay;)V:GetOnGroundOverlayClick_Lcom_google_android_gms_maps_model_GroundOverlay_Handler:Android.Gms.Maps.GoogleMap/IOnGroundOverlayClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n"

    invoke-static {v1, v0, v2}, Lmono/android/Runtime;->register(Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;)V

    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .line 22
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 23
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-class v1, Lmono/com/google/android/gms/maps/GoogleMap_OnGroundOverlayClickListenerImplementor;

    if-ne v0, v1, :cond_0

    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/Object;

    const-string v1, "Android.Gms.Maps.GoogleMap+IOnGroundOverlayClickListenerImplementor, Xamarin.GooglePlayServices.Maps"

    const-string v2, ""

    .line 24
    invoke-static {v1, v2, p0, v0}, Lmono/android/TypeManager;->Activate(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Object;)V

    :cond_0
    return-void
.end method

.method private native n_onGroundOverlayClick(Lcom/google/android/gms/maps/model/GroundOverlay;)V
.end method


# virtual methods
.method public monodroidAddReference(Ljava/lang/Object;)V
    .locals 1

    .line 39
    iget-object v0, p0, Lmono/com/google/android/gms/maps/GoogleMap_OnGroundOverlayClickListenerImplementor;->refList:Ljava/util/ArrayList;

    if-nez v0, :cond_0

    .line 40
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lmono/com/google/android/gms/maps/GoogleMap_OnGroundOverlayClickListenerImplementor;->refList:Ljava/util/ArrayList;

    .line 41
    :cond_0
    iget-object v0, p0, Lmono/com/google/android/gms/maps/GoogleMap_OnGroundOverlayClickListenerImplementor;->refList:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public monodroidClearReferences()V
    .locals 1

    .line 46
    iget-object v0, p0, Lmono/com/google/android/gms/maps/GoogleMap_OnGroundOverlayClickListenerImplementor;->refList:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    .line 47
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    :cond_0
    return-void
.end method

.method public onGroundOverlayClick(Lcom/google/android/gms/maps/model/GroundOverlay;)V
    .locals 0

    .line 31
    invoke-direct {p0, p1}, Lmono/com/google/android/gms/maps/GoogleMap_OnGroundOverlayClickListenerImplementor;->n_onGroundOverlayClick(Lcom/google/android/gms/maps/model/GroundOverlay;)V

    return-void
.end method
