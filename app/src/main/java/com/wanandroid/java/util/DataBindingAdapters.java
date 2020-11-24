package com.wanandroid.java.util;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * @author jere
 */
public class DataBindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageUrr(ImageView iv, String imageUri) {
        if (imageUri == null) {
            iv.setImageURI(null);
        } else {
            iv.setImageURI(Uri.parse(imageUri));
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView iv, Uri imageUri) {
        iv.setImageURI(imageUri);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView iv, Drawable drawable) {
        iv.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }


}
