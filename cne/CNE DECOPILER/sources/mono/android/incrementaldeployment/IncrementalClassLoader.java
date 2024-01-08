package mono.android.incrementaldeployment;

import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class IncrementalClassLoader extends ClassLoader {
    private final DelegateClassLoader delegateClassLoader;

    public IncrementalClassLoader(ClassLoader classLoader, String str, File file, String str2, List list) {
        super(classLoader.getParent());
        this.delegateClassLoader = createDelegateClassLoader(file, str2, list, classLoader);
    }

    public Class findClass(String str) {
        return this.delegateClassLoader.findClass(str);
    }

    class DelegateClassLoader extends BaseDexClassLoader {
        private DelegateClassLoader(String str, File file, String str2, ClassLoader classLoader) {
            super(str, file, str2, classLoader);
        }

        public Class findClass(String str) {
            return super.findClass(str);
        }
    }

    private static DelegateClassLoader createDelegateClassLoader(File file, String str, List list, ClassLoader classLoader) {
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(File.pathSeparator);
            }
            sb.append(str2);
        }
        Log.v("IncrementalClassLoader", "Incremental dex path is " + sb);
        Log.v("IncrementalClassLoader", "Native lib dir is " + str);
        return new DelegateClassLoader(sb.toString(), file, str, classLoader);
    }

    private static void setParent(ClassLoader classLoader, ClassLoader classLoader2) {
        try {
            Field declaredField = ClassLoader.class.getDeclaredField("parent");
            declaredField.setAccessible(true);
            declaredField.set(classLoader, classLoader2);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchFieldException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static void inject(ClassLoader classLoader, String str, File file, String str2, List list) {
        setParent(classLoader, new IncrementalClassLoader(classLoader, str, file, str2, list));
    }
}
