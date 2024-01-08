package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.Serializable;

public abstract class NavType {
    public static final NavType BoolArrayType = new NavType(true) {
        public String getName() {
            return "boolean[]";
        }

        public void put(Bundle bundle, String str, boolean[] zArr) {
            bundle.putBooleanArray(str, zArr);
        }

        public boolean[] get(Bundle bundle, String str) {
            return (boolean[]) bundle.get(str);
        }

        public boolean[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType BoolType = new NavType(false) {
        public String getName() {
            return "boolean";
        }

        public void put(Bundle bundle, String str, Boolean bool) {
            bundle.putBoolean(str, bool.booleanValue());
        }

        public Boolean get(Bundle bundle, String str) {
            return (Boolean) bundle.get(str);
        }

        public Boolean parseValue(String str) {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
        }
    };
    public static final NavType FloatArrayType = new NavType(true) {
        public String getName() {
            return "float[]";
        }

        public void put(Bundle bundle, String str, float[] fArr) {
            bundle.putFloatArray(str, fArr);
        }

        public float[] get(Bundle bundle, String str) {
            return (float[]) bundle.get(str);
        }

        public float[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType FloatType = new NavType(false) {
        public String getName() {
            return "float";
        }

        public void put(Bundle bundle, String str, Float f) {
            bundle.putFloat(str, f.floatValue());
        }

        public Float get(Bundle bundle, String str) {
            return (Float) bundle.get(str);
        }

        public Float parseValue(String str) {
            return Float.valueOf(Float.parseFloat(str));
        }
    };
    public static final NavType IntArrayType = new NavType(true) {
        public String getName() {
            return "integer[]";
        }

        public void put(Bundle bundle, String str, int[] iArr) {
            bundle.putIntArray(str, iArr);
        }

        public int[] get(Bundle bundle, String str) {
            return (int[]) bundle.get(str);
        }

        public int[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType IntType = new NavType(false) {
        public String getName() {
            return "integer";
        }

        public void put(Bundle bundle, String str, Integer num) {
            bundle.putInt(str, num.intValue());
        }

        public Integer get(Bundle bundle, String str) {
            return (Integer) bundle.get(str);
        }

        public Integer parseValue(String str) {
            if (str.startsWith("0x")) {
                return Integer.valueOf(Integer.parseInt(str.substring(2), 16));
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
    };
    public static final NavType LongArrayType = new NavType(true) {
        public String getName() {
            return "long[]";
        }

        public void put(Bundle bundle, String str, long[] jArr) {
            bundle.putLongArray(str, jArr);
        }

        public long[] get(Bundle bundle, String str) {
            return (long[]) bundle.get(str);
        }

        public long[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType LongType = new NavType(false) {
        public String getName() {
            return "long";
        }

        public void put(Bundle bundle, String str, Long l) {
            bundle.putLong(str, l.longValue());
        }

        public Long get(Bundle bundle, String str) {
            return (Long) bundle.get(str);
        }

        public Long parseValue(String str) {
            if (str.endsWith("L")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.startsWith("0x")) {
                return Long.valueOf(Long.parseLong(str.substring(2), 16));
            }
            return Long.valueOf(Long.parseLong(str));
        }
    };
    public static final NavType ReferenceType = new NavType(false) {
        public String getName() {
            return "reference";
        }

        public void put(Bundle bundle, String str, Integer num) {
            bundle.putInt(str, num.intValue());
        }

        public Integer get(Bundle bundle, String str) {
            return (Integer) bundle.get(str);
        }

        public Integer parseValue(String str) {
            if (str.startsWith("0x")) {
                return Integer.valueOf(Integer.parseInt(str.substring(2), 16));
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
    };
    public static final NavType StringArrayType = new NavType(true) {
        public String getName() {
            return "string[]";
        }

        public void put(Bundle bundle, String str, String[] strArr) {
            bundle.putStringArray(str, strArr);
        }

        public String[] get(Bundle bundle, String str) {
            return (String[]) bundle.get(str);
        }

        public String[] parseValue(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    public static final NavType StringType = new NavType(true) {
        public String getName() {
            return "string";
        }

        public String parseValue(String str) {
            return str;
        }

        public void put(Bundle bundle, String str, String str2) {
            bundle.putString(str, str2);
        }

        public String get(Bundle bundle, String str) {
            return (String) bundle.get(str);
        }
    };
    private final boolean mNullableAllowed;

    @Nullable
    public abstract Object get(@NonNull Bundle bundle, @NonNull String str);

    @NonNull
    public abstract String getName();

    @NonNull
    public abstract Object parseValue(@NonNull String str);

    public abstract void put(Bundle bundle, String str, Object obj);

    NavType(boolean z) {
        this.mNullableAllowed = z;
    }

    public boolean isNullableAllowed() {
        return this.mNullableAllowed;
    }

    /* access modifiers changed from: package-private */
    public Object parseAndPut(Bundle bundle, String str, String str2) {
        Object parseValue = parseValue(str2);
        put(bundle, str, parseValue);
        return parseValue;
    }

    public String toString() {
        return getName();
    }

    @NonNull
    public static NavType fromArgType(@Nullable String str, @Nullable String str2) {
        String str3;
        NavType navType = IntType;
        if (navType.getName().equals(str)) {
            return navType;
        }
        NavType navType2 = IntArrayType;
        if (navType2.getName().equals(str)) {
            return navType2;
        }
        NavType navType3 = LongType;
        if (navType3.getName().equals(str)) {
            return navType3;
        }
        NavType navType4 = LongArrayType;
        if (navType4.getName().equals(str)) {
            return navType4;
        }
        NavType navType5 = BoolType;
        if (navType5.getName().equals(str)) {
            return navType5;
        }
        NavType navType6 = BoolArrayType;
        if (navType6.getName().equals(str)) {
            return navType6;
        }
        NavType navType7 = StringType;
        if (navType7.getName().equals(str)) {
            return navType7;
        }
        NavType navType8 = StringArrayType;
        if (navType8.getName().equals(str)) {
            return navType8;
        }
        NavType navType9 = FloatType;
        if (navType9.getName().equals(str)) {
            return navType9;
        }
        NavType navType10 = FloatArrayType;
        if (navType10.getName().equals(str)) {
            return navType10;
        }
        NavType navType11 = ReferenceType;
        if (navType11.getName().equals(str)) {
            return navType11;
        }
        if (str == null || str.isEmpty()) {
            return navType7;
        }
        try {
            if (!str.startsWith(".") || str2 == null) {
                str3 = str;
            } else {
                str3 = str2 + str;
            }
            if (str.endsWith("[]")) {
                str3 = str3.substring(0, str3.length() - 2);
                Class<?> cls = Class.forName(str3);
                if (Parcelable.class.isAssignableFrom(cls)) {
                    return new ParcelableArrayType(cls);
                }
                if (Serializable.class.isAssignableFrom(cls)) {
                    return new SerializableArrayType(cls);
                }
            } else {
                Class<?> cls2 = Class.forName(str3);
                if (Parcelable.class.isAssignableFrom(cls2)) {
                    return new ParcelableType(cls2);
                }
                if (Enum.class.isAssignableFrom(cls2)) {
                    return new EnumType(cls2);
                }
                if (Serializable.class.isAssignableFrom(cls2)) {
                    return new SerializableType(cls2);
                }
            }
            throw new IllegalArgumentException(str3 + " is not Serializable or Parcelable.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0 = BoolType;
        r0.parseValue(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        return StringType;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r0 = LongType;
        r0.parseValue(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000b, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r0 = FloatType;
        r0.parseValue(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0006 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.navigation.NavType inferFromValue(java.lang.String r1) {
        /*
            androidx.navigation.NavType r0 = IntType     // Catch:{ IllegalArgumentException -> 0x0006 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0006 }
            return r0
        L_0x0006:
            androidx.navigation.NavType r0 = LongType     // Catch:{ IllegalArgumentException -> 0x000c }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x000c }
            return r0
        L_0x000c:
            androidx.navigation.NavType r0 = FloatType     // Catch:{ IllegalArgumentException -> 0x0012 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0012 }
            return r0
        L_0x0012:
            androidx.navigation.NavType r0 = BoolType     // Catch:{ IllegalArgumentException -> 0x0018 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0018 }
            return r0
        L_0x0018:
            androidx.navigation.NavType r1 = StringType
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType.inferFromValue(java.lang.String):androidx.navigation.NavType");
    }

    static NavType inferFromValueType(Object obj) {
        if (obj instanceof Integer) {
            return IntType;
        }
        if (obj instanceof int[]) {
            return IntArrayType;
        }
        if (obj instanceof Long) {
            return LongType;
        }
        if (obj instanceof long[]) {
            return LongArrayType;
        }
        if (obj instanceof Float) {
            return FloatType;
        }
        if (obj instanceof float[]) {
            return FloatArrayType;
        }
        if (obj instanceof Boolean) {
            return BoolType;
        }
        if (obj instanceof boolean[]) {
            return BoolArrayType;
        }
        if ((obj instanceof String) || obj == null) {
            return StringType;
        }
        if (obj instanceof String[]) {
            return StringArrayType;
        }
        if (obj.getClass().isArray() && Parcelable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new ParcelableArrayType(obj.getClass().getComponentType());
        }
        if (obj.getClass().isArray() && Serializable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new SerializableArrayType(obj.getClass().getComponentType());
        }
        if (obj instanceof Parcelable) {
            return new ParcelableType(obj.getClass());
        }
        if (obj instanceof Enum) {
            return new EnumType(obj.getClass());
        }
        if (obj instanceof Serializable) {
            return new SerializableType(obj.getClass());
        }
        throw new IllegalArgumentException("Object of type " + obj.getClass().getName() + " is not supported for navigation arguments.");
    }

    public final class ParcelableType extends NavType {
        private final Class mType;

        public ParcelableType(@NonNull Class cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls) || Serializable.class.isAssignableFrom(cls)) {
                this.mType = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Parcelable or Serializable.");
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable Object obj) {
            this.mType.cast(obj);
            if (obj == null || (obj instanceof Parcelable)) {
                bundle.putParcelable(str, (Parcelable) obj);
            } else if (obj instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) obj);
            }
        }

        @Nullable
        public Object get(@NonNull Bundle bundle, @NonNull String str) {
            return bundle.get(str);
        }

        @NonNull
        public Object parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        @NonNull
        public String getName() {
            return this.mType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ParcelableType.class != obj.getClass()) {
                return false;
            }
            return this.mType.equals(((ParcelableType) obj).mType);
        }

        public int hashCode() {
            return this.mType.hashCode();
        }
    }

    public final class ParcelableArrayType extends NavType {
        private final Class mArrayType;

        public ParcelableArrayType(@NonNull Class cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls)) {
                try {
                    this.mArrayType = Class.forName("[L" + cls.getName() + ";");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new IllegalArgumentException(cls + " does not implement Parcelable.");
            }
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable Parcelable[] parcelableArr) {
            this.mArrayType.cast(parcelableArr);
            bundle.putParcelableArray(str, parcelableArr);
        }

        @Nullable
        public Parcelable[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (Parcelable[]) bundle.get(str);
        }

        @NonNull
        public Parcelable[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @NonNull
        public String getName() {
            return this.mArrayType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ParcelableArrayType.class != obj.getClass()) {
                return false;
            }
            return this.mArrayType.equals(((ParcelableArrayType) obj).mArrayType);
        }

        public int hashCode() {
            return this.mArrayType.hashCode();
        }
    }

    public class SerializableType extends NavType {
        private final Class mType;

        public SerializableType(@NonNull Class cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            } else if (!cls.isEnum()) {
                this.mType = cls;
            } else {
                throw new IllegalArgumentException(cls + " is an Enum. You should use EnumType instead.");
            }
        }

        SerializableType(boolean z, @NonNull Class cls) {
            super(z);
            if (Serializable.class.isAssignableFrom(cls)) {
                this.mType = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Serializable.");
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable Serializable serializable) {
            this.mType.cast(serializable);
            bundle.putSerializable(str, serializable);
        }

        @Nullable
        public Serializable get(@NonNull Bundle bundle, @NonNull String str) {
            return (Serializable) bundle.get(str);
        }

        @NonNull
        public Serializable parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        @NonNull
        public String getName() {
            return this.mType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SerializableType)) {
                return false;
            }
            return this.mType.equals(((SerializableType) obj).mType);
        }

        public int hashCode() {
            return this.mType.hashCode();
        }
    }

    public final class EnumType extends SerializableType {
        private final Class mType;

        public EnumType(@NonNull Class cls) {
            super(false, cls);
            if (cls.isEnum()) {
                this.mType = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " is not an Enum type.");
        }

        public Enum parseValue(String str) {
            for (Enum enumR : (Enum[]) this.mType.getEnumConstants()) {
                if (enumR.name().equals(str)) {
                    return enumR;
                }
            }
            throw new IllegalArgumentException("Enum value " + str + " not found for type " + this.mType.getName() + ".");
        }

        public String getName() {
            return this.mType.getName();
        }
    }

    public final class SerializableArrayType extends NavType {
        private final Class mArrayType;

        public SerializableArrayType(@NonNull Class cls) {
            super(true);
            if (Serializable.class.isAssignableFrom(cls)) {
                try {
                    this.mArrayType = Class.forName("[L" + cls.getName() + ";");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            }
        }

        /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Object, java.io.Serializable[], java.io.Serializable] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void put(@androidx.annotation.NonNull android.os.Bundle r2, @androidx.annotation.NonNull java.lang.String r3, @androidx.annotation.Nullable java.io.Serializable[] r4) {
            /*
                r1 = this;
                java.lang.Class r0 = r1.mArrayType
                r0.cast(r4)
                r2.putSerializable(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType.SerializableArrayType.put(android.os.Bundle, java.lang.String, java.io.Serializable[]):void");
        }

        @Nullable
        public Serializable[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (Serializable[]) bundle.get(str);
        }

        @NonNull
        public Serializable[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @NonNull
        public String getName() {
            return this.mArrayType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || SerializableArrayType.class != obj.getClass()) {
                return false;
            }
            return this.mArrayType.equals(((SerializableArrayType) obj).mArrayType);
        }

        public int hashCode() {
            return this.mArrayType.hashCode();
        }
    }
}
