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
public class PersonalInfoItemCustomView extends ConstraintLayout {


    public PersonalInfoItemCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.personal_info_item_custom_view, this);


        TextView categoryTv = findViewById(R.id.personal_info_item_category_iv);
        TextView contentTv = findViewById(R.id.personal_info_item_content_tv);
        ImageView avatarIv = findViewById(R.id.personal_info_item_avatar_iv);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PersonalInfoItemCustomView);
        String categoryTextString = typedArray.getString(R.styleable.PersonalInfoItemCustomView_categoryText);
        categoryTv.setText(categoryTextString);
        String contentTextString = typedArray.getString(R.styleable.PersonalInfoItemCustomView_contentText);
        contentTv.setText(contentTextString);
        boolean isContainAvatar = typedArray.getBoolean(R.styleable.PersonalInfoItemCustomView_isDisplayImage, false);
        if (isContainAvatar) {
            avatarIv.setVisibility(VISIBLE);
        }
        int imageResourceId = typedArray.getResourceId(R.styleable.PersonalInfoItemCustomView_imageResource, R.drawable.default_portrait);
        avatarIv.setImageResource(imageResourceId);

        typedArray.recycle();
    }

}