.class interface abstract Lcom/google/android/material/timepicker/TimePickerControls;
.super Ljava/lang/Object;
.source "TimePickerControls.java"


# virtual methods
.method public abstract setActiveSelection(I)V
.end method

.method public abstract setHandRotation(F)V
.end method

.method public abstract setValues([Ljava/lang/String;I)V
    .param p2    # I
        .annotation build Landroidx/annotation/StringRes;
        .end annotation
    .end param
.end method

.method public abstract updateTime(III)V
    .param p3    # I
        .annotation build Landroidx/annotation/IntRange;
        .end annotation
    .end param
.end method
