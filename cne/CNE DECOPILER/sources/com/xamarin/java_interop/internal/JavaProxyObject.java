package com.xamarin.java_interop.internal;

import com.xamarin.java_interop.GCUserPeerable;
import com.xamarin.java_interop.ManagedPeer;
import java.util.ArrayList;

final class JavaProxyObject implements GCUserPeerable {
    static final String assemblyQualifiedName = "Java.Interop.JavaProxyObject, Java.Interop";
    ArrayList managedReferences = new ArrayList();

    public native boolean equals(Object obj);

    public native int hashCode();

    public native String toString();

    JavaProxyObject() {
    }

    static {
        ManagedPeer.registerNativeMembers(JavaProxyObject.class, assemblyQualifiedName, "");
    }

    public void jiAddManagedReference(Object obj) {
        this.managedReferences.add(obj);
    }

    public void jiClearManagedReferences() {
        this.managedReferences.clear();
    }
}
