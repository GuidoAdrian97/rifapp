.class public Lmono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor;
.super Ljava/lang/Object;
.source "MessageQueue_OnFileDescriptorEventListenerImplementor.java"

# interfaces
.implements Lmono/android/IGCUserPeer;
.implements Landroid/os/MessageQueue$OnFileDescriptorEventListener;


# static fields
.field public static final __md_methods:Ljava/lang/String; = "n_onFileDescriptorEvents:(Ljava/io/FileDescriptor;I)I:GetOnFileDescriptorEvents_Ljava_io_FileDescriptor_IHandler:Android.OS.MessageQueue/IOnFileDescriptorEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n"


# instance fields
.field private refList:Ljava/util/ArrayList;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 16
    const-class v0, Lmono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor;

    const-string v1, "Android.OS.MessageQueue+IOnFileDescriptorEventListenerImplementor, Mono.Android"

    const-string v2, "n_onFileDescriptorEvents:(Ljava/io/FileDescriptor;I)I:GetOnFileDescriptorEvents_Ljava_io_FileDescriptor_IHandler:Android.OS.MessageQueue/IOnFileDescriptorEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n"

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

    const-class v1, Lmono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor;

    if-ne v0, v1, :cond_0

    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/Object;

    const-string v1, "Android.OS.MessageQueue+IOnFileDescriptorEventListenerImplementor, Mono.Android"

    const-string v2, ""

    .line 24
    invoke-static {v1, v2, p0, v0}, Lmono/android/TypeManager;->Activate(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Object;)V

    :cond_0
    return-void
.end method

.method private native n_onFileDescriptorEvents(Ljava/io/FileDescriptor;I)I
.end method


# virtual methods
.method public monodroidAddReference(Ljava/lang/Object;)V
    .locals 1

    .line 39
    iget-object v0, p0, Lmono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor;->refList:Ljava/util/ArrayList;

    if-nez v0, :cond_0

    .line 40
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lmono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor;->refList:Ljava/util/ArrayList;

    .line 41
    :cond_0
    iget-object v0, p0, Lmono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor;->refList:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public monodroidClearReferences()V
    .locals 1

    .line 46
    iget-object v0, p0, Lmono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor;->refList:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    .line 47
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    :cond_0
    return-void
.end method

.method public onFileDescriptorEvents(Ljava/io/FileDescriptor;I)I
    .locals 0

    .line 31
    invoke-direct {p0, p1, p2}, Lmono/android/os/MessageQueue_OnFileDescriptorEventListenerImplementor;->n_onFileDescriptorEvents(Ljava/io/FileDescriptor;I)I

    move-result p1

    return p1
.end method
