package com.jere.test.util.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.jere.test.R;
import com.jere.test.databinding.PersonalInfoItemCustomViewBinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class PersonalInfoItemCustomView extends ConstraintLayout {


    public PersonalInfoItemCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PersonalInfoItemCustomViewBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.personal_info_item_custom_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PersonalInfoItemCustomView);
        String categoryTextString = typedArray.getString(R.styleable.PersonalInfoItemCustomView_categoryText);
        binding.setCategoryText(categoryTextString);
        String contentTextString = typedArray.getString(R.styleable.PersonalInfoItemCustomView_contentText);
        binding.setContentText(contentTextString);
        boolean isContainAvatar = typedArray.getBoolean(R.styleable.PersonalInfoItemCustomView_isDisplayImage, false);
        binding.setIsShowAvatarIv(isContainAvatar);
        int imageResourceId = typedArray.getResourceId(R.styleable.PersonalInfoItemCustomView_imageResource, R.drawable.default_portrait);
        binding.setAvatarResource(imageResourceId);

        typedArray.recycle();
    }

}