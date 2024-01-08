package mono.android.media;

import android.media.AudioMetadataReadMap;
import android.media.AudioTrack;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AudioTrack_OnCodecFormatChangedListenerImplementor implements IGCUserPeer, AudioTrack.OnCodecFormatChangedListener {
    public static final String __md_methods = "n_onCodecFormatChanged:(Landroid/media/AudioTrack;Landroid/media/AudioMetadataReadMap;)V:GetOnCodecFormatChanged_Landroid_media_AudioTrack_Landroid_media_AudioMetadataReadMap_Handler:Android.Media.AudioTrack/IOnCodecFormatChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
    private ArrayList refList;

    private native void n_onCodecFormatChanged(AudioTrack audioTrack, AudioMetadataReadMap audioMetadataReadMap);

    static {
        Runtime.register("Android.Media.AudioTrack+IOnCodecFormatChangedListenerImplementor, Mono.Android", AudioTrack_OnCodecFormatChangedListenerImplementor.class, __md_methods);
    }

    public AudioTrack_OnCodecFormatChangedListenerImplementor() {
        if (getClass() == AudioTrack_OnCodecFormatChangedListenerImplementor.class) {
            TypeManager.Activate("Android.Media.AudioTrack+IOnCodecFormatChangedListenerImplementor, Mono.Android", "", this, new Object[0]);
        }
    }

    public void onCodecFormatChanged(AudioTrack audioTrack, AudioMetadataReadMap audioMetadataReadMap) {
        n_onCodecFormatChanged(audioTrack, audioMetadataReadMap);
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
