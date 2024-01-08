package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public abstract class Objects {
    public static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public final class ToStringHelper {
        private final List zzff;
        private final Object zzfg;

        private ToStringHelper(Object obj) {
            this.zzfg = Preconditions.checkNotNull(obj);
            this.zzff = new ArrayList();
        }

        public final ToStringHelper add(String str, Object obj) {
            List list = this.zzff;
            String str2 = (String) Preconditions.checkNotNull(str);
            String valueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + valueOf.length());
            sb.append(str2);
            sb.append("=");
            sb.append(valueOf);
            list.add(sb.toString());
            return this;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.zzfg.getClass().getSimpleName());
            sb.append('{');
            int size = this.zzff.size();
            for (int i = 0; i < size; i++) {
                sb.append((String) this.zzff.get(i));
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append('}');
            return sb.toString();
        }
    }
}
