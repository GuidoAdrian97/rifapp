package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public abstract class ArrayUtils {
    public static boolean contains(Object[] objArr, Object obj) {
        int length = objArr != null ? objArr.length : 0;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (Objects.equal(objArr[i], obj)) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            return true;
        }
        return false;
    }
}
