.class public Lcrc646957603ea1820544/MediaPickerActivity;
.super Landroid/app/Activity;
.source "MediaPickerActivity.java"

# interfaces
.implements Lmono/android/IGCUserPeer;
.implements Landroid/media/MediaScannerConnection$OnScanCompletedListener;


# static fields
.field public static final __md_methods:Ljava/lang/String; = "n_onSaveInstanceState:(Landroid/os/Bundle;)V:GetOnSaveInstanceState_Landroid_os_Bundle_Handler\nn_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onScanCompleted:(Ljava/lang/String;Landroid/net/Uri;)V:GetOnScanCompleted_Ljava_lang_String_Landroid_net_Uri_Handler:Android.Media.MediaScannerConnection/IOnScanCompletedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n"


# instance fields
.field private refList:Ljava/util/ArrayList;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 20
    const-class v0, Lcrc646957603ea1820544/MediaPickerActivity;

    const-string v1, "Plugin.Media.MediaPickerActivity, Plugin.Media"

    const-string v2, "n_onSaveInstanceState:(Landroid/os/Bundle;)V:GetOnSaveInstanceState_Landroid_os_Bundle_Handler\nn_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onScanCompleted:(Ljava/lang/String;Landroid/net/Uri;)V:GetOnScanCompleted_Ljava_lang_String_Landroid_net_Uri_Handler:Android.Media.MediaScannerConnection/IOnScanCompletedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n"

    invoke-static {v1, v0, v2}, Lmono/android/Runtime;->register(Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;)V

    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .line 26
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    .line 27
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-class v1, Lcrc646957603ea1820544/MediaPickerActivity;

    if-ne v0, v1, :cond_0

    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/Object;

    const-string v1, "Plugin.Media.MediaPickerActivity, Plugin.Media"

    const-string v2, ""

    .line 28
    invoke-static {v1, v2, p0, v0}, Lmono/android/TypeManager;->Activate(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Object;)V

    :cond_0
    return-void
.end method

.method private native n_onActivityResult(IILandroid/content/Intent;)V
.end method

.method private native n_onCreate(Landroid/os/Bundle;)V
.end method

.method private native n_onDestroy()V
.end method

.method private native n_onSaveInstanceState(Landroid/os/Bundle;)V
.end method

.method private native n_onScanCompleted(Ljava/lang/String;Landroid/net/Uri;)V
.end method


# virtual methods
.method public monodroidAddReference(Ljava/lang/Object;)V
    .locals 1

    .line 75
    iget-object v0, p0, Lcrc646957603ea1820544/MediaPickerActivity;->refList:Ljava/util/ArrayList;

    if-nez v0, :cond_0

    .line 76
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcrc646957603ea1820544/MediaPickerActivity;->refList:Ljava/util/ArrayList;

    .line 77
    :cond_0
    iget-object v0, p0, Lcrc646957603ea1820544/MediaPickerActivity;->refList:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public monodroidClearReferences()V
    .locals 1

    .line 82
    iget-object v0, p0, Lcrc646957603ea1820544/MediaPickerActivity;->refList:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    .line 83
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    :cond_0
    return-void
.end method

.method public onActivityResult(IILandroid/content/Intent;)V
    .locals 0

    .line 51
    invoke-direct {p0, p1, p2, p3}, Lcrc646957603ea1820544/MediaPickerActivity;->n_onActivityResult(IILandroid/content/Intent;)V

    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 0

    .line 43
    invoke-direct {p0, p1}, Lcrc646957603ea1820544/MediaPickerActivity;->n_onCreate(Landroid/os/Bundle;)V

    return-void
.end method

.method public onDestroy()V
    .locals 0

    .line 59
    invoke-direct {p0}, Lcrc646957603ea1820544/MediaPickerActivity;->n_onDestroy()V

    return-void
.end method

.method public onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 0

    .line 35
    invoke-direct {p0, p1}, Lcrc646957603ea1820544/MediaPickerActivity;->n_onSaveInstanceState(Landroid/os/Bundle;)V

    return-void
.end method

.method public onScanCompleted(Ljava/lang/String;Landroid/net/Uri;)V
    .locals 0

    .line 67
    invoke-direct {p0, p1, p2}, Lcrc646957603ea1820544/MediaPickerActivity;->n_onScanCompleted(Ljava/lang/String;Landroid/net/Uri;)V

    return-void
.end method
