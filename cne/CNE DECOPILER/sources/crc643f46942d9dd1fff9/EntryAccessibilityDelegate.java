package crc643f46942d9dd1fff9;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EntryAccessibilityDelegate extends AccessibilityDelegateAutomationId {
    public static final String __md_methods = "n_onInitializeAccessibilityNodeInfo:(Landroid/view/View;Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;)V:GetOnInitializeAccessibilityNodeInfo_Landroid_view_View_Landroidx_core_view_accessibility_AccessibilityNodeInfoCompat_Handler\n";
    private ArrayList refList;

    private native void n_onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    static {
        Runtime.register("Xamarin.Forms.Platform.Android.EntryAccessibilityDelegate, Xamarin.Forms.Platform.Android", EntryAccessibilityDelegate.class, "n_onInitializeAccessibilityNodeInfo:(Landroid/view/View;Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;)V:GetOnInitializeAccessibilityNodeInfo_Landroid_view_View_Landroidx_core_view_accessibility_AccessibilityNodeInfoCompat_Handler\n");
    }

    public EntryAccessibilityDelegate() {
        if (getClass() == EntryAccessibilityDelegate.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.EntryAccessibilityDelegate, Xamarin.Forms.Platform.Android", "", this, new Object[0]);
        }
    }

    public EntryAccessibilityDelegate(View.AccessibilityDelegate accessibilityDelegate) {
        super(accessibilityDelegate);
        if (getClass() == EntryAccessibilityDelegate.class) {
            TypeManager.Activate("Xamarin.Forms.Platform.Android.EntryAccessibilityDelegate, Xamarin.Forms.Platform.Android", "Android.Views.View+AccessibilityDelegate, Mono.Android", this, new Object[]{accessibilityDelegate});
        }
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        n_onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
    }

    public void monodroidAddReference(Object obj) {
        if (this.refList == null) {
            this.refList = new ArrayList();
        }
        this.refList.add(obj);
    }

    public void monodroidClearReferences() {
        ArrayList arrayList = this.refList;
        if (arrayList != null) {
            arrayList.clear();
        }
    }
}
