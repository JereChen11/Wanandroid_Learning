package com.jere.test;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * @author jere
 */
public class HomeBottomBarCustomView extends LinearLayout implements View.OnClickListener {
    private Button homePageBtn;
    private Button page1Btn;
    private Button page2Btn;
    private Button page3Btn;

    public HomeBottomBarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.home_bottom_bar_custom_view, this);

//        int[] sets = {R.attr.artistText, R.attr.trackText, R.attr.buyButton};
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
//        CharSequence artist = typedArray.getText(index0);
//        CharSequence track = typedArray.getText(index1);
//        CharSequence buyButton = typedArray.getText(index2);
//        typedArray.recycle();

        initComponents();

    }

    private void initComponents() {
        homePageBtn = findViewById(R.id.btn_home_page);
        page1Btn = findViewById(R.id.btn_page_1);
        page2Btn = findViewById(R.id.btn_page_2);
        page3Btn = findViewById(R.id.btn_page_3);
        homePageBtn.setOnClickListener(this);
        page1Btn.setOnClickListener(this);
        page2Btn.setOnClickListener(this);
        page3Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home_page:
                Intent homePageIntent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(homePageIntent);
                break;
            case R.id.btn_page_1:
                Intent page1Intent = new Intent(getContext(), Page1Activity.class);
                getContext().startActivity(page1Intent);
                break;
            case R.id.btn_page_2:
                Intent page2Intent = new Intent(getContext(), Page2Activity.class);
                getContext().startActivity(page2Intent);
                break;
            case R.id.btn_page_3:
                Intent page3Intent = new Intent(getContext(), Page3Activity.class);
                getContext().startActivity(page3Intent);
                break;
            default:
                break;
        }
    }
}
