package com.wanandroid.java.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.wanandroid.java.R;
import com.wanandroid.java.databinding.MyAccountInfoItemCustomViewBinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class MyAccountInfoItemCustomView extends ConstraintLayout {
    private String categoryTextString;
    private MyAccountInfoItemCustomViewBinding binding;

    public MyAccountInfoItemCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.my_account_info_item_custom_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyAccountInfoItemCustomView);
        categoryTextString = typedArray.getString(R.styleable.MyAccountInfoItemCustomView_categoryItemText);
        binding.setCategoryContentText(categoryTextString);

        int iconResourceId = typedArray.getResourceId(R.styleable.MyAccountInfoItemCustomView_iconResource, R.drawable.vector_drawable_me);
        binding.setCategoryIconResource(iconResourceId);

        typedArray.recycle();
    }

    public String getCategoryTextString() {
        return categoryTextString;
    }

    public void setCategoryTextString(String categoryTextString) {
        this.categoryTextString = categoryTextString;
        binding.setCategoryContentText(categoryTextString);
        invalidate();
    }
}
