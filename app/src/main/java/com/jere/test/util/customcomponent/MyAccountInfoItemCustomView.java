package com.jere.test.util.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.jere.test.R;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author jere
 */
public class MyAccountInfoItemCustomView extends ConstraintLayout {

    public MyAccountInfoItemCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.my_account_info_item_custom_view, this);

        TextView categoryTv = findViewById(R.id.account_info_item_category_tv);
        ImageView categoryIconIv = findViewById(R.id.account_info_item_category_icon_iv);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyAccountInfoItemCustomView);
        String categoryTextString = typedArray.getString(R.styleable.MyAccountInfoItemCustomView_categoryItemText);
        categoryTv.setText(categoryTextString);

        int iconResourceId = typedArray.getResourceId(R.styleable.MyAccountInfoItemCustomView_iconResource, R.drawable.my_account_icon);
        categoryIconIv.setImageResource(iconResourceId);

        typedArray.recycle();
    }
}
