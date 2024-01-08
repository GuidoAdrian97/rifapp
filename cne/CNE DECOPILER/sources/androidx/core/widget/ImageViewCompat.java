package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

public abstract class ImageViewCompat {
    public static ColorStateList getImageTintList(ImageView imageView) {
        return imageView.getImageTintList();
    }

    public static void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
        Drawable drawable;
        int i = Build.VERSION.SDK_INT;
        imageView.setImageTintList(colorStateList);
        if (i == 21 && (drawable = imageView.getDrawable()) != null && imageView.getImageTintList() != null) {
            if (drawable.isStateful()) {
                drawable.setState(imageView.getDrawableState());
            }
            imageView.setImageDrawable(drawable);
        }
    }

    public static PorterDuff.Mode getImageTintMode(ImageView imageView) {
        return imageView.getImageTintMode();
    }

    public static void setImageTintMode(ImageView imageView, PorterDuff.Mode mode) {
        Drawable drawable;
        int i = Build.VERSION.SDK_INT;
        imageView.setImageTintMode(mode);
        if (i == 21 && (drawable = imageView.getDrawable()) != null && imageView.getImageTintList() != null) {
            if (drawable.isStateful()) {
                drawable.setState(imageView.getDrawableState());
            }
            imageView.setImageDrawable(drawable);
        }
    }
}
