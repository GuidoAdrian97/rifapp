.class public abstract Lcom/google/android/gms/maps/model/BitmapDescriptorFactory;
.super Ljava/lang/Object;


# static fields
.field private static zzcm:Lcom/google/android/gms/internal/maps/zze;


# direct methods
.method public static zza(Lcom/google/android/gms/internal/maps/zze;)V
    .locals 1

    .line 3
    sget-object v0, Lcom/google/android/gms/maps/model/BitmapDescriptorFactory;->zzcm:Lcom/google/android/gms/internal/maps/zze;

    if-eqz v0, :cond_0

    return-void

    .line 5
    :cond_0
    invoke-static {p0}, Lcom/google/android/gms/common/internal/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/maps/zze;

    sput-object p0, Lcom/google/android/gms/maps/model/BitmapDescriptorFactory;->zzcm:Lcom/google/android/gms/internal/maps/zze;

    return-void
.end method
