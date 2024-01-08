.class final Landroidx/preference/EditTextPreference$SavedState$1;
.super Ljava/lang/Object;
.source "EditTextPreference.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public createFromParcel(Landroid/os/Parcel;)Landroidx/preference/EditTextPreference$SavedState;
    .locals 1

    .line 185
    new-instance v0, Landroidx/preference/EditTextPreference$SavedState;

    invoke-direct {v0, p1}, Landroidx/preference/EditTextPreference$SavedState;-><init>(Landroid/os/Parcel;)V

    return-object v0
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 0

    .line 182
    invoke-virtual {p0, p1}, Landroidx/preference/EditTextPreference$SavedState$1;->createFromParcel(Landroid/os/Parcel;)Landroidx/preference/EditTextPreference$SavedState;

    move-result-object p1

    return-object p1
.end method

.method public newArray(I)[Landroidx/preference/EditTextPreference$SavedState;
    .locals 0

    .line 190
    new-array p1, p1, [Landroidx/preference/EditTextPreference$SavedState;

    return-object p1
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 0

    .line 182
    invoke-virtual {p0, p1}, Landroidx/preference/EditTextPreference$SavedState$1;->newArray(I)[Landroidx/preference/EditTextPreference$SavedState;

    move-result-object p1

    return-object p1
.end method
