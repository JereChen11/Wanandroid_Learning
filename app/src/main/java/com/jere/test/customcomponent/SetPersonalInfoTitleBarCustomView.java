package com.jere.test.customcomponent;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jere.test.R;

/**
 * @author jere
 */
public class SetPersonalInfoTitleBarCustomView extends ConstraintLayout implements View.OnClickListener {

    public SetPersonalInfoTitleBarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.set_personal_info_title_bar_custom_view, this);

        TextView cancelTv = findViewById(R.id.cancel_tv);
        TextView titleContentTv = findViewById(R.id.title_content_tv);
        Button completeBtn = findViewById(R.id.complete_btn);
        cancelTv.setOnClickListener(this);
        completeBtn.setOnClickListener(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SetPersonalInfoTitleBarCustomView);
        String titleContentString = typedArray.getString(R.styleable.SetPersonalInfoTitleBarCustomView_titleContentText);
        titleContentTv.setText(titleContentString);


        typedArray.recycle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_tv:
                ((Activity) getContext()).onBackPressed();
                break;
            case R.id.complete_btn:
                ((Activity) getContext()).finish();
                break;
            default:
                break;
        }
    }
}
