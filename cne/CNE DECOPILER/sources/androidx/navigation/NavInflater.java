package androidx.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgument;
import androidx.navigation.NavDeepLink;
import androidx.navigation.NavOptions;
import androidx.navigation.common.R$styleable;
import org.xmlpull.v1.XmlPullParserException;

public final class NavInflater {
    private static final ThreadLocal sTmpValue = new ThreadLocal();
    private Context mContext;
    private NavigatorProvider mNavigatorProvider;

    public NavInflater(@NonNull Context context, @NonNull NavigatorProvider navigatorProvider) {
        this.mContext = context;
        this.mNavigatorProvider = navigatorProvider;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049 A[Catch:{ Exception -> 0x0053, all -> 0x0051 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b A[Catch:{ Exception -> 0x0053, all -> 0x0051 }] */
    @android.annotation.SuppressLint({"ResourceType"})
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.navigation.NavGraph inflate(@androidx.annotation.NavigationRes int r7) {
        /*
            r6 = this;
            android.content.Context r0 = r6.mContext
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.XmlResourceParser r1 = r0.getXml(r7)
            android.util.AttributeSet r2 = android.util.Xml.asAttributeSet(r1)
        L_0x000e:
            int r3 = r1.next()     // Catch:{ Exception -> 0x0053 }
            r4 = 2
            if (r3 == r4) goto L_0x0019
            r5 = 1
            if (r3 == r5) goto L_0x0019
            goto L_0x000e
        L_0x0019:
            if (r3 != r4) goto L_0x0049
            java.lang.String r3 = r1.getName()     // Catch:{ Exception -> 0x0053 }
            androidx.navigation.NavDestination r2 = r6.inflate(r0, r1, r2, r7)     // Catch:{ Exception -> 0x0053 }
            boolean r4 = r2 instanceof androidx.navigation.NavGraph     // Catch:{ Exception -> 0x0053 }
            if (r4 == 0) goto L_0x002d
            androidx.navigation.NavGraph r2 = (androidx.navigation.NavGraph) r2     // Catch:{ Exception -> 0x0053 }
            r1.close()
            return r2
        L_0x002d:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0053 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0053 }
            r4.<init>()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r5 = "Root element <"
            r4.append(r5)     // Catch:{ Exception -> 0x0053 }
            r4.append(r3)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r3 = "> did not inflate into a NavGraph"
            r4.append(r3)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x0053 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0053 }
            throw r2     // Catch:{ Exception -> 0x0053 }
        L_0x0049:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x0053 }
            java.lang.String r3 = "No start tag found"
            r2.<init>(r3)     // Catch:{ Exception -> 0x0053 }
            throw r2     // Catch:{ Exception -> 0x0053 }
        L_0x0051:
            r7 = move-exception
            goto L_0x007b
        L_0x0053:
            r2 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x0051 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0051 }
            r4.<init>()     // Catch:{ all -> 0x0051 }
            java.lang.String r5 = "Exception inflating "
            r4.append(r5)     // Catch:{ all -> 0x0051 }
            java.lang.String r7 = r0.getResourceName(r7)     // Catch:{ all -> 0x0051 }
            r4.append(r7)     // Catch:{ all -> 0x0051 }
            java.lang.String r7 = " line "
            r4.append(r7)     // Catch:{ all -> 0x0051 }
            int r7 = r1.getLineNumber()     // Catch:{ all -> 0x0051 }
            r4.append(r7)     // Catch:{ all -> 0x0051 }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x0051 }
            r3.<init>(r7, r2)     // Catch:{ all -> 0x0051 }
            throw r3     // Catch:{ all -> 0x0051 }
        L_0x007b:
            r1.close()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavInflater.inflate(int):androidx.navigation.NavGraph");
    }

    @NonNull
    private NavDestination inflate(@NonNull Resources resources, @NonNull XmlResourceParser xmlResourceParser, @NonNull AttributeSet attributeSet, int i) {
        int depth;
        NavDestination createDestination = this.mNavigatorProvider.getNavigator(xmlResourceParser.getName()).createDestination();
        createDestination.onInflate(this.mContext, attributeSet);
        int depth2 = xmlResourceParser.getDepth() + 1;
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1 || ((depth = xmlResourceParser.getDepth()) < depth2 && next == 3)) {
                return createDestination;
            }
            if (next == 2 && depth <= depth2) {
                String name = xmlResourceParser.getName();
                if ("argument".equals(name)) {
                    inflateArgumentForDestination(resources, createDestination, attributeSet, i);
                } else if ("deepLink".equals(name)) {
                    inflateDeepLink(resources, createDestination, attributeSet);
                } else if ("action".equals(name)) {
                    inflateAction(resources, createDestination, attributeSet, xmlResourceParser, i);
                } else if ("include".equals(name) && (createDestination instanceof NavGraph)) {
                    TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R$styleable.NavInclude);
                    ((NavGraph) createDestination).addDestination(inflate(obtainAttributes.getResourceId(R$styleable.NavInclude_graph, 0)));
                    obtainAttributes.recycle();
                } else if (createDestination instanceof NavGraph) {
                    ((NavGraph) createDestination).addDestination(inflate(resources, xmlResourceParser, attributeSet, i));
                }
            }
        }
        return createDestination;
    }

    private void inflateArgumentForDestination(Resources resources, NavDestination navDestination, AttributeSet attributeSet, int i) {
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R$styleable.NavArgument);
        String string = obtainAttributes.getString(R$styleable.NavArgument_android_name);
        if (string != null) {
            navDestination.addArgument(string, inflateArgument(obtainAttributes, resources, i));
            obtainAttributes.recycle();
            return;
        }
        throw new XmlPullParserException("Arguments must have a name");
    }

    private void inflateArgumentForBundle(Resources resources, Bundle bundle, AttributeSet attributeSet, int i) {
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R$styleable.NavArgument);
        String string = obtainAttributes.getString(R$styleable.NavArgument_android_name);
        if (string != null) {
            NavArgument inflateArgument = inflateArgument(obtainAttributes, resources, i);
            if (inflateArgument.isDefaultValuePresent()) {
                inflateArgument.putDefaultValue(string, bundle);
            }
            obtainAttributes.recycle();
            return;
        }
        throw new XmlPullParserException("Arguments must have a name");
    }

    private NavArgument inflateArgument(TypedArray typedArray, Resources resources, int i) {
        NavArgument.Builder builder = new NavArgument.Builder();
        boolean z = false;
        builder.setIsNullable(typedArray.getBoolean(R$styleable.NavArgument_nullable, false));
        ThreadLocal threadLocal = sTmpValue;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        String string = typedArray.getString(R$styleable.NavArgument_argType);
        int i2 = null;
        NavType fromArgType = string != null ? NavType.fromArgType(string, resources.getResourcePackageName(i)) : null;
        int i3 = R$styleable.NavArgument_android_defaultValue;
        if (typedArray.getValue(i3, typedValue)) {
            NavType navType = NavType.ReferenceType;
            if (fromArgType == navType) {
                int i4 = typedValue.resourceId;
                if (i4 != 0) {
                    i2 = Integer.valueOf(i4);
                } else if (typedValue.type == 16 && typedValue.data == 0) {
                    i2 = 0;
                } else {
                    throw new XmlPullParserException("unsupported value '" + typedValue.string + "' for " + fromArgType.getName() + ". Must be a reference to a resource.");
                }
            } else {
                int i5 = typedValue.resourceId;
                if (i5 != 0) {
                    if (fromArgType == null) {
                        fromArgType = navType;
                        i2 = Integer.valueOf(i5);
                    } else {
                        throw new XmlPullParserException("unsupported value '" + typedValue.string + "' for " + fromArgType.getName() + ". You must use a \"" + navType.getName() + "\" type to reference other resources.");
                    }
                } else if (fromArgType == NavType.StringType) {
                    i2 = typedArray.getString(i3);
                } else {
                    int i6 = typedValue.type;
                    if (i6 == 3) {
                        String charSequence = typedValue.string.toString();
                        if (fromArgType == null) {
                            fromArgType = NavType.inferFromValue(charSequence);
                        }
                        i2 = fromArgType.parseValue(charSequence);
                    } else if (i6 == 4) {
                        fromArgType = checkNavType(typedValue, fromArgType, NavType.FloatType, string, "float");
                        i2 = Float.valueOf(typedValue.getFloat());
                    } else if (i6 == 5) {
                        fromArgType = checkNavType(typedValue, fromArgType, NavType.IntType, string, "dimension");
                        i2 = Integer.valueOf((int) typedValue.getDimension(resources.getDisplayMetrics()));
                    } else if (i6 == 18) {
                        fromArgType = checkNavType(typedValue, fromArgType, NavType.BoolType, string, "boolean");
                        if (typedValue.data != 0) {
                            z = true;
                        }
                        i2 = Boolean.valueOf(z);
                    } else if (i6 < 16 || i6 > 31) {
                        throw new XmlPullParserException("unsupported argument type " + typedValue.type);
                    } else {
                        NavType navType2 = NavType.FloatType;
                        if (fromArgType == navType2) {
                            fromArgType = checkNavType(typedValue, fromArgType, navType2, string, "float");
                            i2 = Float.valueOf((float) typedValue.data);
                        } else {
                            fromArgType = checkNavType(typedValue, fromArgType, NavType.IntType, string, "integer");
                            i2 = Integer.valueOf(typedValue.data);
                        }
                    }
                }
            }
        }
        if (i2 != null) {
            builder.setDefaultValue(i2);
        }
        if (fromArgType != null) {
            builder.setType(fromArgType);
        }
        return builder.build();
    }

    private static NavType checkNavType(TypedValue typedValue, NavType navType, NavType navType2, String str, String str2) {
        if (navType == null || navType == navType2) {
            return navType != null ? navType : navType2;
        }
        throw new XmlPullParserException("Type is " + str + " but found " + str2 + ": " + typedValue.data);
    }

    private void inflateDeepLink(Resources resources, NavDestination navDestination, AttributeSet attributeSet) {
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R$styleable.NavDeepLink);
        String string = obtainAttributes.getString(R$styleable.NavDeepLink_uri);
        String string2 = obtainAttributes.getString(R$styleable.NavDeepLink_action);
        String string3 = obtainAttributes.getString(R$styleable.NavDeepLink_mimeType);
        if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2) || !TextUtils.isEmpty(string3)) {
            NavDeepLink.Builder builder = new NavDeepLink.Builder();
            if (string != null) {
                builder.setUriPattern(string.replace("${applicationId}", this.mContext.getPackageName()));
            }
            if (!TextUtils.isEmpty(string2)) {
                builder.setAction(string2.replace("${applicationId}", this.mContext.getPackageName()));
            }
            if (string3 != null) {
                builder.setMimeType(string3.replace("${applicationId}", this.mContext.getPackageName()));
            }
            navDestination.addDeepLink(builder.build());
            obtainAttributes.recycle();
            return;
        }
        throw new XmlPullParserException("Every <deepLink> must include at least one of app:uri, app:action, or app:mimeType");
    }

    private void inflateAction(Resources resources, NavDestination navDestination, AttributeSet attributeSet, XmlResourceParser xmlResourceParser, int i) {
        int depth;
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R$styleable.NavAction);
        int resourceId = obtainAttributes.getResourceId(R$styleable.NavAction_android_id, 0);
        NavAction navAction = new NavAction(obtainAttributes.getResourceId(R$styleable.NavAction_destination, 0));
        NavOptions.Builder builder = new NavOptions.Builder();
        builder.setLaunchSingleTop(obtainAttributes.getBoolean(R$styleable.NavAction_launchSingleTop, false));
        builder.setPopUpTo(obtainAttributes.getResourceId(R$styleable.NavAction_popUpTo, -1), obtainAttributes.getBoolean(R$styleable.NavAction_popUpToInclusive, false));
        builder.setEnterAnim(obtainAttributes.getResourceId(R$styleable.NavAction_enterAnim, -1));
        builder.setExitAnim(obtainAttributes.getResourceId(R$styleable.NavAction_exitAnim, -1));
        builder.setPopEnterAnim(obtainAttributes.getResourceId(R$styleable.NavAction_popEnterAnim, -1));
        builder.setPopExitAnim(obtainAttributes.getResourceId(R$styleable.NavAction_popExitAnim, -1));
        navAction.setNavOptions(builder.build());
        Bundle bundle = new Bundle();
        int depth2 = xmlResourceParser.getDepth() + 1;
        while (true) {
            int next = xmlResourceParser.next();
            if (next != 1 && ((depth = xmlResourceParser.getDepth()) >= depth2 || next != 3)) {
                if (next == 2 && depth <= depth2 && "argument".equals(xmlResourceParser.getName())) {
                    inflateArgumentForBundle(resources, bundle, attributeSet, i);
                }
            }
        }
        if (!bundle.isEmpty()) {
            navAction.setDefaultArguments(bundle);
        }
        navDestination.putAction(resourceId, navAction);
        obtainAttributes.recycle();
    }
}
