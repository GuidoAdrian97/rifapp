package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzw extends IInterface {
    int getFillColor();

    List getHoles();

    String getId();

    List getPoints();

    int getStrokeColor();

    int getStrokeJointType();

    List getStrokePattern();

    float getStrokeWidth();

    float getZIndex();

    boolean isClickable();

    boolean isGeodesic();

    boolean isVisible();

    void remove();

    void setClickable(boolean z);

    void setFillColor(int i);

    void setGeodesic(boolean z);

    void setHoles(List list);

    void setPoints(List list);

    void setStrokeColor(int i);

    void setStrokeJointType(int i);

    void setStrokePattern(List list);

    void setStrokeWidth(float f);

    void setVisible(boolean z);

    void setZIndex(float f);

    boolean zzb(zzw zzw);

    void zze(IObjectWrapper iObjectWrapper);

    int zzj();

    IObjectWrapper zzk();
}
