package com.jere.test.util.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.jere.test.R;

/**
 * @author jere
 */
public class BottomBarItemCustomView extends ConstraintLayout {

    public BottomBarItemCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.bottom_bar_item_custom_view, this);

        ImageView iconIv = findViewById(R.id.bottom_bar_item_icon_iv);
        TextView contentTv = findViewById(R.id.bottom_bar_item_content_tv);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomBarItemCustomView);
        int iconResourceId = typedArray.getResourceId(R.styleable.BottomBarItemCustomView_bottomBarIconResource, R.drawable.my_account_icon);
        iconIv.setImageResource(iconResourceId);

        String contentString = typedArray.getString(R.styleable.BottomBarItemCustomView_bottomBarContentText);
        if (contentString != null) {
            contentTv.setText(contentString);
        }

        typedArray.recycle();
    }

}
