package crc648e35430423bd4943;

import java.io.Writer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GLTextureView_LogWriter extends Writer implements IGCUserPeer {
    public static final String __md_methods = "n_close:()V:GetCloseHandler\nn_flush:()V:GetFlushHandler\nn_write:([CII)V:GetWrite_arrayCIIHandler\n";
    private ArrayList refList;

    private native void n_close();

    private native void n_flush();

    private native void n_write(char[] cArr, int i, int i2);

    static {
        Runtime.register("SkiaSharp.Views.Android.GLTextureView+LogWriter, SkiaSharp.Views.Android", GLTextureView_LogWriter.class, __md_methods);
    }

    public GLTextureView_LogWriter() {
        if (getClass() == GLTextureView_LogWriter.class) {
            TypeManager.Activate("SkiaSharp.Views.Android.GLTextureView+LogWriter, SkiaSharp.Views.Android", "", this, new Object[0]);
        }
    }

    public void close() {
        n_close();
    }

    public void flush() {
        n_flush();
    }

    public void write(char[] cArr, int i, int i2) {
        n_write(cArr, i, i2);
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
