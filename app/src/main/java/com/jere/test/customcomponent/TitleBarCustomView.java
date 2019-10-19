package com.jere.test.customcomponent;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jere.test.R;

/**
 * @author jere
 */
public class TitleBarCustomView extends ConstraintLayout implements View.OnClickListener {

    public TitleBarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.title_bar_custom_view, this);

        ImageView backIv = findViewById(R.id.back_iv);
        TextView titleTv = findViewById(R.id.title_tv);
        backIv.setOnClickListener(this);

        int[] sets = {R.attr.titleText};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        String titleString = typedArray.getString(0);
        titleTv.setText(titleString);
        typedArray.recycle();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                ((Activity) getContext()).onBackPressed();
                break;
            default:
                break;
        }
    }


}
