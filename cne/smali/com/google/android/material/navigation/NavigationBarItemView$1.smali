.class Lcom/google/android/material/navigation/NavigationBarItemView$1;
.super Ljava/lang/Object;
.source "NavigationBarItemView.java"

# interfaces
.implements Landroid/view/View$OnLayoutChangeListener;


# instance fields
.field final synthetic this$0:Lcom/google/android/material/navigation/NavigationBarItemView;


# direct methods
.method constructor <init>(Lcom/google/android/material/navigation/NavigationBarItemView;)V
    .locals 0

    .line 119
    iput-object p1, p0, Lcom/google/android/material/navigation/NavigationBarItemView$1;->this$0:Lcom/google/android/material/navigation/NavigationBarItemView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onLayoutChange(Landroid/view/View;IIIIIIII)V
    .locals 0

    .line 131
    iget-object p1, p0, Lcom/google/android/material/navigation/NavigationBarItemView$1;->this$0:Lcom/google/android/material/navigation/NavigationBarItemView;

    invoke-static {p1}, Lcom/google/android/material/navigation/NavigationBarItemView;->access$000(Lcom/google/android/material/navigation/NavigationBarItemView;)Landroid/widget/ImageView;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/View;->getVisibility()I

    move-result p1

    if-nez p1, :cond_0

    .line 132
    iget-object p1, p0, Lcom/google/android/material/navigation/NavigationBarItemView$1;->this$0:Lcom/google/android/material/navigation/NavigationBarItemView;

    invoke-static {p1}, Lcom/google/android/material/navigation/NavigationBarItemView;->access$000(Lcom/google/android/material/navigation/NavigationBarItemView;)Landroid/widget/ImageView;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/google/android/material/navigation/NavigationBarItemView;->access$100(Lcom/google/android/material/navigation/NavigationBarItemView;Landroid/view/View;)V

    :cond_0
    return-void
.end method
