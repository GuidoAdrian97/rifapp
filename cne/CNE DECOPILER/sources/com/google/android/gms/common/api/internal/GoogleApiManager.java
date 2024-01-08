package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompatBuilder$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class GoogleApiManager implements Handler.Callback {
    /* access modifiers changed from: private */
    public static final Object lock = new Object();
    public static final Status zaib = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zaic = new Status(4, "The user must be signed in to make this API call.");
    private static GoogleApiManager zaig;
    /* access modifiers changed from: private */
    public final Handler handler;
    /* access modifiers changed from: private */
    public long zaid = 5000;
    /* access modifiers changed from: private */
    public long zaie = 120000;
    private long zaif = 10000;
    private final Context zaih;
    private final GoogleApiAvailability zaii;
    private final GoogleApiAvailabilityCache zaij;
    private final AtomicInteger zaik = new AtomicInteger(1);
    private final AtomicInteger zail = new AtomicInteger(0);
    private final Map zaim = new ConcurrentHashMap(5, 0.75f, 1);
    private final Set zaio = new ArraySet();
    private final Set zaip = new ArraySet();

    public static GoogleApiManager zab(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (zaig == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zaig = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zaig;
        }
        return googleApiManager;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    final class zac {
        /* access modifiers changed from: private */
        public final Feature zaji;

        private zac(ApiKey apiKey, Feature feature) {
            this.zaji = feature;
        }

        public final boolean equals(Object obj) {
            if (obj != null && (obj instanceof zac)) {
                zac zac = (zac) obj;
                if (!Objects.equal((Object) null, (Object) null) || !Objects.equal(this.zaji, zac.zaji)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(null, this.zaji);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("key", (Object) null).add("feature", this.zaji).toString();
        }

        /* synthetic */ zac(ApiKey apiKey, Feature feature, zabh zabh) {
            this(apiKey, feature);
        }
    }

    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zaih = context;
        zar zar = new zar(looper, this);
        this.handler = zar;
        this.zaii = googleApiAvailability;
        this.zaij = new GoogleApiAvailabilityCache(googleApiAvailability);
        zar.sendMessage(zar.obtainMessage(6));
    }

    private final void zab(GoogleApi googleApi) {
        throw null;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    public abstract class zaa {
        final /* synthetic */ GoogleApiManager zaia;
        private final Queue zair;
        private final Api$Client zais;
        private final int zaix;
        private boolean zaiz;
        private final List zaja;

        public abstract void connect();

        public abstract int getInstanceId();

        public abstract void resume();

        public abstract void zaat();

        public abstract void zabh();

        public abstract void zabj();

        public abstract boolean zabn();

        public abstract void zac(Status status);

        private final boolean zah(ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                zaad unused = this.zaia.getClass();
            }
            return false;
        }

        private final void zabg() {
            ArrayList arrayList = new ArrayList(this.zair);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                zac zac = (zac) obj;
                if (!this.zais.isConnected()) {
                    return;
                }
                if (zab(zac)) {
                    this.zair.remove(zac);
                }
            }
        }

        private final boolean zab(zac zac) {
            if (!(zac instanceof zab)) {
                zac(zac);
                return true;
            }
            zab zab = (zab) zac;
            Feature zaa = zaa(zab.zaa(this));
            if (zaa == null) {
                zac(zac);
                return true;
            } else if (zab.zab(this)) {
                zac zac2 = new zac((ApiKey) null, zaa, (zabh) null);
                int indexOf = this.zaja.indexOf(zac2);
                if (indexOf >= 0) {
                    zac zac3 = (zac) this.zaja.get(indexOf);
                    this.zaia.handler.removeMessages(15, zac3);
                    this.zaia.handler.sendMessageDelayed(Message.obtain(this.zaia.handler, 15, zac3), this.zaia.zaid);
                    return false;
                }
                this.zaja.add(zac2);
                this.zaia.handler.sendMessageDelayed(Message.obtain(this.zaia.handler, 15, zac2), this.zaia.zaid);
                this.zaia.handler.sendMessageDelayed(Message.obtain(this.zaia.handler, 16, zac2), this.zaia.zaie);
                ConnectionResult connectionResult = new ConnectionResult(2, (PendingIntent) null);
                if (zah(connectionResult)) {
                    return false;
                }
                this.zaia.zac(connectionResult, this.zaix);
                return false;
            } else {
                zab.zaa(new UnsupportedApiCallException(zaa));
                return false;
            }
        }

        private final void zac(zac zac) {
            throw null;
        }

        private final Feature zaa(Feature[] featureArr) {
            if (!(featureArr == null || featureArr.length == 0)) {
                Feature[] availableFeatures = this.zais.getAvailableFeatures();
                if (availableFeatures == null) {
                    availableFeatures = new Feature[0];
                }
                ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
                for (Feature feature : availableFeatures) {
                    arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
                }
                for (Feature feature2 : featureArr) {
                    if (!arrayMap.containsKey(feature2.getName()) || ((Long) arrayMap.get(feature2.getName())).longValue() < feature2.getVersion()) {
                        return feature2;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        public final void zaa(zac zac) {
            if (!this.zaja.contains(zac) || this.zaiz) {
                return;
            }
            if (!this.zais.isConnected()) {
                connect();
            } else {
                zabg();
            }
        }

        /* access modifiers changed from: private */
        public final void zab(zac zac) {
            Feature[] zaa;
            if (this.zaja.remove(zac)) {
                this.zaia.handler.removeMessages(15, zac);
                this.zaia.handler.removeMessages(16, zac);
                Feature zad = zac.zaji;
                ArrayList arrayList = new ArrayList(this.zair.size());
                for (zac zac2 : this.zair) {
                    if ((zac2 instanceof zab) && (zaa = ((zab) zac2).zaa(this)) != null && ArrayUtils.contains(zaa, zad)) {
                        arrayList.add(zac2);
                    }
                }
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zac zac3 = (zac) obj;
                    this.zair.remove(zac3);
                    zac3.zaa(new UnsupportedApiCallException(zad));
                }
            }
        }
    }

    public final void zam() {
        Handler handler2 = this.handler;
        handler2.sendMessage(handler2.obtainMessage(3));
    }

    public boolean handleMessage(Message message) {
        int i = message.what;
        long j = 300000;
        zaa zaa2 = null;
        switch (i) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.zaif = j;
                this.handler.removeMessages(12);
                for (Object m : this.zaim.keySet()) {
                    NotificationCompatBuilder$$ExternalSyntheticThrowCCEIfNotNull0.m(m);
                    Handler handler2 = this.handler;
                    handler2.sendMessageDelayed(handler2.obtainMessage(12, (Object) null), this.zaif);
                }
                break;
            case 2:
                NotificationCompatBuilder$$ExternalSyntheticThrowCCEIfNotNull0.m(message.obj);
                throw null;
            case 3:
                for (zaa zaa3 : this.zaim.values()) {
                    zaa3.zabj();
                    zaa3.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                NotificationCompatBuilder$$ExternalSyntheticThrowCCEIfNotNull0.m(message.obj);
                throw null;
            case 5:
                int i2 = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator it = this.zaim.values().iterator();
                while (true) {
                    if (it.hasNext()) {
                        zaa zaa4 = (zaa) it.next();
                        if (zaa4.getInstanceId() == i2) {
                            zaa2 = zaa4;
                        }
                    }
                }
                if (zaa2 == null) {
                    StringBuilder sb = new StringBuilder(76);
                    sb.append("Could not find API instance ");
                    sb.append(i2);
                    sb.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb.toString(), new Exception());
                    break;
                } else {
                    String errorString = this.zaii.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length());
                    sb2.append("Error resolution was canceled by the user, original error message: ");
                    sb2.append(errorString);
                    sb2.append(": ");
                    sb2.append(errorMessage);
                    zaa2.zac(new Status(17, sb2.toString()));
                    break;
                }
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zaih.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zaih.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabh(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zaif = 300000;
                        break;
                    }
                }
                break;
            case 7:
                NotificationCompatBuilder$$ExternalSyntheticThrowCCEIfNotNull0.m(message.obj);
                zab((GoogleApi) null);
                break;
            case 9:
                if (this.zaim.containsKey(message.obj)) {
                    ((zaa) this.zaim.get(message.obj)).resume();
                    break;
                }
                break;
            case 10:
                for (Object m2 : this.zaip) {
                    NotificationCompatBuilder$$ExternalSyntheticThrowCCEIfNotNull0.m(m2);
                    ((zaa) this.zaim.remove((Object) null)).zabh();
                }
                this.zaip.clear();
                break;
            case 11:
                if (this.zaim.containsKey(message.obj)) {
                    ((zaa) this.zaim.get(message.obj)).zaat();
                    break;
                }
                break;
            case 12:
                if (this.zaim.containsKey(message.obj)) {
                    ((zaa) this.zaim.get(message.obj)).zabn();
                    break;
                }
                break;
            case 14:
                NotificationCompatBuilder$$ExternalSyntheticThrowCCEIfNotNull0.m(message.obj);
                throw null;
            case 15:
                zac zac2 = (zac) message.obj;
                Map map = this.zaim;
                ApiKey unused = zac2.getClass();
                if (map.containsKey((Object) null)) {
                    Map map2 = this.zaim;
                    ApiKey unused2 = zac2.getClass();
                    ((zaa) map2.get((Object) null)).zaa(zac2);
                    break;
                }
                break;
            case 16:
                zac zac3 = (zac) message.obj;
                Map map3 = this.zaim;
                ApiKey unused3 = zac3.getClass();
                if (map3.containsKey((Object) null)) {
                    Map map4 = this.zaim;
                    ApiKey unused4 = zac3.getClass();
                    ((zaa) map4.get((Object) null)).zab(zac3);
                    break;
                }
                break;
            default:
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zac(ConnectionResult connectionResult, int i) {
        return this.zaii.zaa(this.zaih, connectionResult, i);
    }

    public final void zaa(ConnectionResult connectionResult, int i) {
        if (!zac(connectionResult, i)) {
            Handler handler2 = this.handler;
            handler2.sendMessage(handler2.obtainMessage(5, i, 0, connectionResult));
        }
    }
}
