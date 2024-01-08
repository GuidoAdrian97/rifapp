package com.google.android.gms.common;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
abstract class zzc {
    private static Context zzaa;
    private static final Object zzz = new Object();

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized void zza(android.content.Context r2) {
        /*
            java.lang.Class<com.google.android.gms.common.zzc> r0 = com.google.android.gms.common.zzc.class
            monitor-enter(r0)
            android.content.Context r1 = zzaa     // Catch:{ all -> 0x001a }
            if (r1 != 0) goto L_0x0011
            if (r2 == 0) goto L_0x0018
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x001a }
            zzaa = r2     // Catch:{ all -> 0x001a }
            monitor-exit(r0)
            return
        L_0x0011:
            java.lang.String r2 = "GoogleCertificates"
            java.lang.String r1 = "GoogleCertificates has been initialized already"
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r0)
            return
        L_0x001a:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzc.zza(android.content.Context):void");
    }
}
