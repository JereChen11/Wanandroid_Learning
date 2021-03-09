package com.wanandroid.java.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wanandroid.java.R;
import com.wanandroid.java.databinding.PersonalInfoItemCustomViewBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class PersonalInfoItemCustomView extends ConstraintLayout {
    private PersonalInfoItemCustomViewBinding binding;

    public PersonalInfoItemCustomView(@NonNull Context context) {
        this(context, null);
    }

    public PersonalInfoItemCustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PersonalInfoItemCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.personal_info_item_custom_view, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PersonalInfoItemCustomView);
        String categoryTextString = typedArray.getString(R.styleable.PersonalInfoItemCustomView_categoryText);
        binding.setCategoryText(categoryTextString);
        String contentTextString = typedArray.getString(R.styleable.PersonalInfoItemCustomView_contentText);
        binding.setContentText(contentTextString);
        boolean isContainAvatar = typedArray.getBoolean(R.styleable.PersonalInfoItemCustomView_isDisplayImage, false);
        binding.setIsShowAvatarIv(isContainAvatar);
        //int imageResourceId = typedArray.getResourceId(R.styleable.PersonalInfoItemCustomView_imageResource, R.drawable.default_portrait);
        //binding.setAvatarResource(imageResourceId);

        typedArray.recycle();
    }

    public void setContentText(String contentText) {
        binding.setContentText(contentText);
    }

    public void setCircleAvatar(String avatarUriString) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        if (!TextUtils.isEmpty(avatarUriString)) {
            Glide.with(this).load(Uri.parse(avatarUriString)).apply(requestOptions).into(binding.personalInfoItemAvatarIv);
        } else {
            Glide.with(this).load(R.drawable.default_portrait).apply(requestOptions).into(binding.personalInfoItemAvatarIv);
        }
        invalidate();
    }

}