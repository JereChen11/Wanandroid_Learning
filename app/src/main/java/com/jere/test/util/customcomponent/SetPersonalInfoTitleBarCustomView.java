package com.jere.test.util.customcomponent;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.jere.test.R;
import com.jere.test.databinding.SetPersonalInfoTitleBarCustomViewBinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class SetPersonalInfoTitleBarCustomView extends ConstraintLayout {

    public SetPersonalInfoTitleBarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SetPersonalInfoTitleBarCustomViewBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.set_personal_info_title_bar_custom_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SetPersonalInfoTitleBarCustomView);
        String titleContentString = typedArray.getString(R.styleable.SetPersonalInfoTitleBarCustomView_titleContentText);
        binding.setTitleContentTv(titleContentString);
        binding.setBackEventHandler(new BackEventHandler());

        typedArray.recycle();
    }

    public class BackEventHandler {
        public void onClick(View view) {
            ((Activity) view.getContext()).onBackPressed();
        }
    }
}
