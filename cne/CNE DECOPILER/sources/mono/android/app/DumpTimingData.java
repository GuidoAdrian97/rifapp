package mono.android.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import mono.android.Runtime;

public class DumpTimingData extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Runtime.dumpTimingData();
    }
}
