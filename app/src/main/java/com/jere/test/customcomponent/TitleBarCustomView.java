package com.jere.test.customcomponent;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
        ConstraintLayout titleCl = findViewById(R.id.title_cl);
        backIv.setOnClickListener(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarCustomView);
        String titleString = typedArray.getString(R.styleable.TitleBarCustomView_titleText);
        titleTv.setText(titleString);
        int textColor = typedArray.getResourceId(R.styleable.TitleBarCustomView_titleTextColor, 0xffffff);
        titleTv.setTextColor(getResources().getColor(textColor));
        float textSize = typedArray.getDimension(R.styleable.TitleBarCustomView_titleTextSize, 25);
        titleTv.setTextSize(textSize);
        int backIconId = typedArray.getResourceId(R.styleable.TitleBarCustomView_titleBackIcon, R.drawable.back_icon);
        backIv.setImageResource(backIconId);
        int titleBackgroundColor = typedArray.getResourceId(R.styleable.TitleBarCustomView_titleBackgroundColor, R.color.black);
        titleCl.setBackgroundColor(getResources().getColor(titleBackgroundColor));

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
