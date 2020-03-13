package com.jere.test.util.customcomponent;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.jere.test.R;
import com.jere.test.databinding.TitleBarCustomViewBinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class TitleBarCustomView extends ConstraintLayout {

    public TitleBarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TitleBarCustomViewBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.title_bar_custom_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarCustomView);

        String titleString = typedArray.getString(R.styleable.TitleBarCustomView_titleText);
        binding.setTitleTextContent(titleString);
        float textSize = typedArray.getDimension(R.styleable.TitleBarCustomView_titleTextSize, 25);
        binding.setTitleTextSize(textSize);

        int backIconId = typedArray.getResourceId(R.styleable.TitleBarCustomView_titleBackIcon, R.drawable.back_icon);
        binding.setBackIconResource(backIconId);
        binding.setBackEventHandler(new BackEventHandler());

        typedArray.recycle();
    }

    public class BackEventHandler {
        public void onClick(View view) {
            ((Activity) view.getContext()).onBackPressed();
        }
    }


}
