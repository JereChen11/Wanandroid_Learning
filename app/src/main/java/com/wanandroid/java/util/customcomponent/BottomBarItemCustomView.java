package com.wanandroid.java.util.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.wanandroid.java.R;
import com.wanandroid.java.databinding.BottomBarItemCustomViewBinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class BottomBarItemCustomView extends ConstraintLayout {

    public BottomBarItemCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //use dataBinding on custom view.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        BottomBarItemCustomViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.bottom_bar_item_custom_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomBarItemCustomView);
        int iconResourceId = typedArray.getResourceId(R.styleable.BottomBarItemCustomView_bottomBarIconResource, R.drawable.vector_drawable_me);
        binding.setIconResource(iconResourceId);

        String contentString = typedArray.getString(R.styleable.BottomBarItemCustomView_bottomBarContentText);
        if (contentString != null) {
            binding.setContentText(contentString);
        }

        typedArray.recycle();
    }
}
