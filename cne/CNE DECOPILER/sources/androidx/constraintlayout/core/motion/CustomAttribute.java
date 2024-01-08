package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.Utils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CustomAttribute {
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    private static int clamp(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public static int hsvToRgb(float f, float f2, float f3) {
        float f4 = f * 6.0f;
        int i = (int) f4;
        float f5 = f4 - ((float) i);
        float f6 = f3 * 255.0f;
        int i2 = (int) (((1.0f - f2) * f6) + 0.5f);
        int i3 = (int) (((1.0f - (f5 * f2)) * f6) + 0.5f);
        int i4 = (int) (((1.0f - ((1.0f - f5) * f2)) * f6) + 0.5f);
        int i5 = (int) (f6 + 0.5f);
        if (i == 0) {
            return ((i5 << 16) + (i4 << 8) + i2) | -16777216;
        }
        if (i == 1) {
            return ((i3 << 16) + (i5 << 8) + i2) | -16777216;
        }
        if (i == 2) {
            return ((i2 << 16) + (i5 << 8) + i4) | -16777216;
        }
        if (i == 3) {
            return ((i2 << 16) + (i3 << 8) + i5) | -16777216;
        }
        if (i == 4) {
            return ((i4 << 16) + (i2 << 8) + i5) | -16777216;
        }
        if (i != 5) {
            return 0;
        }
        return ((i5 << 16) + (i2 << 8) + i3) | -16777216;
    }

    public AttributeType getType() {
        return this.mType;
    }

    /* renamed from: androidx.constraintlayout.core.motion.CustomAttribute$1  reason: invalid class name */
    abstract /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType[] r0 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType = r0
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType r1 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.REFERENCE_TYPE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType r1 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.BOOLEAN_TYPE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType r1 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.STRING_TYPE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType r1 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.COLOR_TYPE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType r1 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.COLOR_DRAWABLE_TYPE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType     // Catch:{ NoSuchFieldError -> 0x0049 }
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType r1 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.INT_TYPE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType     // Catch:{ NoSuchFieldError -> 0x0054 }
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType r1 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.FLOAT_TYPE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType     // Catch:{ NoSuchFieldError -> 0x0060 }
                androidx.constraintlayout.core.motion.CustomAttribute$AttributeType r1 = androidx.constraintlayout.core.motion.CustomAttribute.AttributeType.DIMENSION_TYPE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.CustomAttribute.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean isContinuous() {
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? false : true;
    }

    public void setFloatValue(float f) {
        this.mFloatValue = f;
    }

    public void setColorValue(int i) {
        this.mColorValue = i;
    }

    public void setIntValue(int i) {
        this.mIntegerValue = i;
    }

    public void setStringValue(String str) {
        this.mStringValue = str;
    }

    public int numberOfInterpolatedValues() {
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()];
        return (i == 4 || i == 5) ? 4 : 1;
    }

    public float getValueToInterpolate() {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
            case 2:
                return this.mBooleanValue ? 1.0f : 0.0f;
            case 3:
                throw new RuntimeException("Cannot interpolate String");
            case 4:
            case 5:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 6:
                return (float) this.mIntegerValue;
            case 7:
                return this.mFloatValue;
            case 8:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
            case 2:
                fArr[0] = this.mBooleanValue ? 1.0f : 0.0f;
                return;
            case 3:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 4:
            case 5:
                int i = this.mColorValue;
                float pow = (float) Math.pow((double) (((float) ((i >> 16) & 255)) / 255.0f), 2.2d);
                float pow2 = (float) Math.pow((double) (((float) ((i >> 8) & 255)) / 255.0f), 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = (float) Math.pow((double) (((float) (i & 255)) / 255.0f), 2.2d);
                fArr[3] = ((float) ((i >> 24) & 255)) / 255.0f;
                return;
            case 6:
                fArr[0] = (float) this.mIntegerValue;
                return;
            case 7:
                fArr[0] = this.mFloatValue;
                return;
            case 8:
                fArr[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public void setValue(float[] fArr) {
        boolean z = true;
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 6:
                this.mIntegerValue = (int) fArr[0];
                return;
            case 2:
                if (((double) fArr[0]) <= 0.5d) {
                    z = false;
                }
                this.mBooleanValue = z;
                return;
            case 3:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 4:
            case 5:
                int hsvToRgb = hsvToRgb(fArr[0], fArr[1], fArr[2]);
                this.mColorValue = hsvToRgb;
                this.mColorValue = (clamp((int) (fArr[3] * 255.0f)) << 24) | (hsvToRgb & 16777215);
                return;
            case 7:
                this.mFloatValue = fArr[0];
                return;
            case 8:
                this.mFloatValue = fArr[0];
                return;
            default:
                return;
        }
    }

    public boolean diff(CustomAttribute customAttribute) {
        AttributeType attributeType;
        if (customAttribute == null || (attributeType = this.mType) != customAttribute.mType) {
            return false;
        }
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[attributeType.ordinal()]) {
            case 1:
            case 6:
                if (this.mIntegerValue == customAttribute.mIntegerValue) {
                    return true;
                }
                return false;
            case 2:
                if (this.mBooleanValue == customAttribute.mBooleanValue) {
                    return true;
                }
                return false;
            case 3:
                if (this.mIntegerValue == customAttribute.mIntegerValue) {
                    return true;
                }
                return false;
            case 4:
            case 5:
                if (this.mColorValue == customAttribute.mColorValue) {
                    return true;
                }
                return false;
            case 7:
                if (this.mFloatValue == customAttribute.mFloatValue) {
                    return true;
                }
                return false;
            case 8:
                if (this.mFloatValue == customAttribute.mFloatValue) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public CustomAttribute(String str, AttributeType attributeType) {
        this.mMethod = false;
        this.mName = str;
        this.mType = attributeType;
    }

    public CustomAttribute(String str, AttributeType attributeType, Object obj, boolean z) {
        this.mName = str;
        this.mType = attributeType;
        this.mMethod = z;
        setValue(obj);
    }

    public CustomAttribute(CustomAttribute customAttribute, Object obj) {
        this.mMethod = false;
        this.mName = customAttribute.mName;
        this.mType = customAttribute.mType;
        setValue(obj);
    }

    public void setValue(Object obj) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 6:
                this.mIntegerValue = ((Integer) obj).intValue();
                return;
            case 2:
                this.mBooleanValue = ((Boolean) obj).booleanValue();
                return;
            case 3:
                this.mStringValue = (String) obj;
                return;
            case 4:
            case 5:
                this.mColorValue = ((Integer) obj).intValue();
                return;
            case 7:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            case 8:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }

    public static HashMap extractAttributes(HashMap hashMap, Object obj) {
        HashMap hashMap2 = new HashMap();
        Class<?> cls = obj.getClass();
        for (String str : hashMap.keySet()) {
            CustomAttribute customAttribute = (CustomAttribute) hashMap.get(str);
            try {
                hashMap2.put(str, new CustomAttribute(customAttribute, cls.getMethod("getMap" + str, new Class[0]).invoke(obj, new Object[0])));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return hashMap2;
    }

    public static void setAttributes(Object obj, HashMap hashMap) {
        String str;
        Class<?> cls = obj.getClass();
        for (String str2 : hashMap.keySet()) {
            CustomAttribute customAttribute = (CustomAttribute) hashMap.get(str2);
            if (!customAttribute.mMethod) {
                str = "set" + str2;
            } else {
                str = str2;
            }
            try {
                switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[customAttribute.mType.ordinal()]) {
                    case 1:
                        cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(customAttribute.mIntegerValue)});
                        break;
                    case 2:
                        cls.getMethod(str, new Class[]{Boolean.TYPE}).invoke(obj, new Object[]{Boolean.valueOf(customAttribute.mBooleanValue)});
                        break;
                    case 3:
                        cls.getMethod(str, new Class[]{CharSequence.class}).invoke(obj, new Object[]{customAttribute.mStringValue});
                        break;
                    case 4:
                        cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(customAttribute.mColorValue)});
                        break;
                    case 6:
                        cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(customAttribute.mIntegerValue)});
                        break;
                    case 7:
                        cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(customAttribute.mFloatValue)});
                        break;
                    case 8:
                        cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(customAttribute.mFloatValue)});
                        break;
                }
            } catch (NoSuchMethodException e) {
                Utils.loge("TransitionLayout", e.getMessage());
                Utils.loge("TransitionLayout", " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
                StringBuilder sb = new StringBuilder();
                sb.append(cls.getName());
                sb.append(" must have a method ");
                sb.append(str);
                Utils.loge("TransitionLayout", sb.toString());
            } catch (IllegalAccessException e2) {
                Utils.loge("TransitionLayout", " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                Utils.loge("TransitionLayout", " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
                e3.printStackTrace();
            }
        }
    }

    public void applyCustom(Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        String str2 = this.mName;
        if (!this.mMethod) {
            str = "set" + str2;
        } else {
            str = str2;
        }
        try {
            switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
                case 1:
                case 6:
                    cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(this.mIntegerValue)});
                    return;
                case 2:
                    cls.getMethod(str, new Class[]{Boolean.TYPE}).invoke(obj, new Object[]{Boolean.valueOf(this.mBooleanValue)});
                    return;
                case 3:
                    cls.getMethod(str, new Class[]{CharSequence.class}).invoke(obj, new Object[]{this.mStringValue});
                    return;
                case 4:
                    cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(this.mColorValue)});
                    return;
                case 7:
                    cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(this.mFloatValue)});
                    return;
                case 8:
                    cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(this.mFloatValue)});
                    return;
                default:
                    return;
            }
        } catch (NoSuchMethodException e) {
            Utils.loge("TransitionLayout", e.getMessage());
            Utils.loge("TransitionLayout", " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            sb.append(" must have a method ");
            sb.append(str);
            Utils.loge("TransitionLayout", sb.toString());
        } catch (IllegalAccessException e2) {
            Utils.loge("TransitionLayout", " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            Utils.loge("TransitionLayout", " Custom Attribute \"" + str2 + "\" not found on " + cls.getName());
            e3.printStackTrace();
        }
    }

    public void setInterpolatedValue(Object obj, float[] fArr) {
        Object obj2 = obj;
        Class<?> cls = obj.getClass();
        String str = "set" + this.mName;
        try {
            int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()];
            boolean z = true;
            if (i == 2) {
                Method method = cls.getMethod(str, new Class[]{Boolean.TYPE});
                Object[] objArr = new Object[1];
                if (fArr[0] <= 0.5f) {
                    z = false;
                }
                objArr[0] = Boolean.valueOf(z);
                method.invoke(obj2, objArr);
            } else if (i == 3) {
                throw new RuntimeException("unable to interpolate strings " + this.mName);
            } else if (i == 4) {
                Method method2 = cls.getMethod(str, new Class[]{Integer.TYPE});
                int clamp = clamp((int) (((float) Math.pow((double) fArr[0], 0.45454545454545453d)) * 255.0f));
                method2.invoke(obj2, new Object[]{Integer.valueOf((clamp((int) (((float) Math.pow((double) fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | (clamp((int) (fArr[3] * 255.0f)) << 24) | (clamp << 16) | clamp((int) (((float) Math.pow((double) fArr[2], 0.45454545454545453d)) * 255.0f)))});
            } else if (i == 6) {
                cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj2, new Object[]{Integer.valueOf((int) fArr[0])});
            } else if (i == 7) {
                cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj2, new Object[]{Float.valueOf(fArr[0])});
            } else if (i == 8) {
                cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj2, new Object[]{Float.valueOf(fArr[0])});
            }
        } catch (NoSuchMethodException e) {
            Utils.loge("TransitionLayout", "no method " + str + " on View \"" + obj.getClass().getName() + "\"");
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            Utils.loge("TransitionLayout", "cannot access method " + str + " on View \"" + obj.getClass().getName() + "\"");
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }
}
