package com.jere.test.util.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.jere.test.R;
import com.jere.test.databinding.MyAccountInfoItemCustomViewBinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class MyAccountInfoItemCustomView extends ConstraintLayout {

    public MyAccountInfoItemCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyAccountInfoItemCustomViewBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.my_account_info_item_custom_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyAccountInfoItemCustomView);
        String categoryTextString = typedArray.getString(R.styleable.MyAccountInfoItemCustomView_categoryItemText);
        binding.setCategoryContentText(categoryTextString);

        int iconResourceId = typedArray.getResourceId(R.styleable.MyAccountInfoItemCustomView_iconResource, R.drawable.my_account_icon);
        binding.setCategoryIconResource(iconResourceId);

        typedArray.recycle();
    }
}
