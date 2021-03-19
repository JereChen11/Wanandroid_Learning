package com.wanandroid.java.ui.customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.wanandroid.java.R;
import com.wanandroid.java.databinding.TitleBarCustomViewBinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class TitleBarCustomView extends ConstraintLayout {
    TitleBarCustomViewBinding mBinding;

    public TitleBarCustomView(Context context) {
        this(context, null);
    }

    public TitleBarCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.title_bar_custom_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarCustomView);

        String titleString = typedArray.getString(R.styleable.TitleBarCustomView_titleText);
        mBinding.setTitleTextContent(titleString);
        float textSize = typedArray.getDimension(R.styleable.TitleBarCustomView_titleTextSize, 40);
        mBinding.setTitleTextSize(textSize);

        int backIconId = typedArray.getResourceId(R.styleable.TitleBarCustomView_titleBackIcon, R.drawable.vector_drawable_left_arrow);
        mBinding.setBackIconResource(backIconId);
        mBinding.setBackEventHandler(new BackEventHandler());

        typedArray.recycle();
    }

    public void setTitle(String newTitle) {
        mBinding.setTitleTextContent(newTitle);
    }

    public static class BackEventHandler {
        public void onClick(View view) {
            ((Activity) view.getContext()).onBackPressed();
        }
    }


}
