package com.jere.test.customcomponent;

import android.content.Context;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jere.test.R;

public class TitleBarCustomView extends ConstraintLayout implements View.OnClickListener {
    private ImageView backIv;
    private TextView titleTv;

    public TitleBarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.title_bar_custom_view, this);


        backIv = findViewById(R.id.back_iv);
        titleTv = findViewById(R.id.title_tv);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                //back
                break;
            default:
                break;
        }
    }
}
