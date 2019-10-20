package com.jere.test.tutorial;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jere.test.R;

/**
 * @author jere
 */
public class TutorialActivity extends AppCompatActivity {
    private View[] navigationPoints;
    private ViewPager viewPager;
    private TextView introduceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutotial);

        findView();
        viewPager.setAdapter(new MyPagerAdapter(this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < navigationPoints.length; i++) {
                    if (position == i) {
                        navigationPoints[i].setBackgroundResource(R.drawable.banner_navigation_point_highlight_shape);
                        introduceTv.setText(R.string.hi);
                    } else if (position == navigationPoints.length) {
                        navigationPoints[0].setBackgroundResource(R.drawable.banner_navigation_point_highlight_shape);
                        navigationPoints[2].setBackgroundResource(R.drawable.banner_navigation_point_gray_shape);
                        introduceTv.setText(R.string.jere_test_app);
                    } else {
                        navigationPoints[i].setBackgroundResource(R.drawable.banner_navigation_point_gray_shape);
                        introduceTv.setText(R.string.welcome_to);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void findView() {
        View leftNavigationPoint = findViewById(R.id.navigation_point_left);
        View centerNavigationPoint = findViewById(R.id.navigation_point_center);
        View rightNavigationPoint = findViewById(R.id.navigation_point_right);
        navigationPoints = new View[] {leftNavigationPoint, centerNavigationPoint, rightNavigationPoint};
        viewPager = findViewById(R.id.tutorial_vp);
        introduceTv = findViewById(R.id.introduce_tv);
    }


}
