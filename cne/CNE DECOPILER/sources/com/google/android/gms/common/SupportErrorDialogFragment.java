package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class SupportErrorDialogFragment extends DialogFragment {
    private Dialog mDialog = null;
    private DialogInterface.OnCancelListener zaaq = null;

    public Dialog onCreateDialog(Bundle bundle) {
        if (this.mDialog == null) {
            setShowsDialog(false);
        }
        return this.mDialog;
    }

    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.zaaq;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    public static SupportErrorDialogFragment newInstance(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        Dialog dialog2 = (Dialog) Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener((DialogInterface.OnCancelListener) null);
        dialog2.setOnDismissListener((DialogInterface.OnDismissListener) null);
        supportErrorDialogFragment.mDialog = dialog2;
        if (onCancelListener != null) {
            supportErrorDialogFragment.zaaq = onCancelListener;
        }
        return supportErrorDialogFragment;
    }

    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
