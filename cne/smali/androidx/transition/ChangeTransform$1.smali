.class Landroidx/transition/ChangeTransform$1;
.super Landroid/util/Property;
.source "ChangeTransform.java"


# direct methods
.method constructor <init>(Ljava/lang/Class;Ljava/lang/String;)V
    .locals 0

    .line 71
    invoke-direct {p0, p1, p2}, Landroid/util/Property;-><init>(Ljava/lang/Class;Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic get(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 71
    check-cast p1, Landroidx/transition/ChangeTransform$PathAnimatorMatrix;

    invoke-virtual {p0, p1}, Landroidx/transition/ChangeTransform$1;->get(Landroidx/transition/ChangeTransform$PathAnimatorMatrix;)[F

    move-result-object p1

    return-object p1
.end method

.method public get(Landroidx/transition/ChangeTransform$PathAnimatorMatrix;)[F
    .locals 0

    const/4 p1, 0x0

    return-object p1
.end method

.method public set(Landroidx/transition/ChangeTransform$PathAnimatorMatrix;[F)V
    .locals 0

    .line 79
    invoke-virtual {p1, p2}, Landroidx/transition/ChangeTransform$PathAnimatorMatrix;->setValues([F)V

    return-void
.end method

.method public bridge synthetic set(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 0

    .line 71
    check-cast p1, Landroidx/transition/ChangeTransform$PathAnimatorMatrix;

    check-cast p2, [F

    invoke-virtual {p0, p1, p2}, Landroidx/transition/ChangeTransform$1;->set(Landroidx/transition/ChangeTransform$PathAnimatorMatrix;[F)V

    return-void
.end method
