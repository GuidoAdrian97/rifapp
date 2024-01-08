.class Lcom/google/android/material/timepicker/RadialViewGroup$1;
.super Ljava/lang/Object;
.source "RadialViewGroup.java"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field final synthetic this$0:Lcom/google/android/material/timepicker/RadialViewGroup;


# direct methods
.method constructor <init>(Lcom/google/android/material/timepicker/RadialViewGroup;)V
    .locals 0

    .line 70
    iput-object p1, p0, Lcom/google/android/material/timepicker/RadialViewGroup$1;->this$0:Lcom/google/android/material/timepicker/RadialViewGroup;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    .line 73
    iget-object v0, p0, Lcom/google/android/material/timepicker/RadialViewGroup$1;->this$0:Lcom/google/android/material/timepicker/RadialViewGroup;

    invoke-virtual {v0}, Lcom/google/android/material/timepicker/RadialViewGroup;->updateLayoutParams()V

    return-void
.end method
