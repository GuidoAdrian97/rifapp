package androidx.core.view.accessibility;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

public interface AccessibilityViewCommand {

    public abstract class MoveAtGranularityArguments extends CommandArguments {
    }

    public abstract class MoveHtmlArguments extends CommandArguments {
    }

    public abstract class MoveWindowArguments extends CommandArguments {
    }

    public abstract class ScrollToPositionArguments extends CommandArguments {
    }

    public abstract class SetProgressArguments extends CommandArguments {
    }

    public abstract class SetSelectionArguments extends CommandArguments {
    }

    public abstract class SetTextArguments extends CommandArguments {
    }

    boolean perform(@NonNull View view, @Nullable CommandArguments commandArguments);

    public abstract class CommandArguments {
        Bundle mBundle;

        @RestrictTo
        public void setBundle(Bundle bundle) {
            this.mBundle = bundle;
        }
    }
}
