package com.jere.test.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.jere.test.R;
import com.jere.test.article.ArticleListFragment;
import com.jere.test.automaticchart.AutomaticChartActivity;

/**
 * @author jere
 */
public class HomeActivity extends FragmentActivity implements View.OnClickListener,
        ArticleListFragment.OnFragmentInteractionListener,
        Page2Fragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_fragment, new HomeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initComponents() {
        Button homePageBtn = findViewById(R.id.btn_home_page);
        Button page1Btn = findViewById(R.id.btn_fragment_1);
        Button page2Btn = findViewById(R.id.btn_fragment_2);
        Button page3Btn = findViewById(R.id.btn_fragment_3);
        homePageBtn.setOnClickListener(this);
        page1Btn.setOnClickListener(this);
        page2Btn.setOnClickListener(this);
        page3Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home_page:
//                Intent homeIntent = new Intent(this, HomeActivity.class);
//                startActivity(homeIntent);
                HomeFragment homeFragment = HomeFragment.newInstance("Home Fragment", "jere test home");
                replaceFragment(homeFragment);
                break;
            case R.id.btn_fragment_1:
                ArticleListFragment page1Fragment = ArticleListFragment.newInstance("page 1 Fragment", "jere test 1");
                replaceFragment(page1Fragment);
                break;
            case R.id.btn_fragment_2:
                Page2Fragment page2Fragment = Page2Fragment.newInstance("page 2 Fragment", "jere test 2");
                replaceFragment(page2Fragment);
                break;
            case R.id.btn_fragment_3:
//                Intent page3Intent = new Intent(this, Page3Activity.class);
//                startActivity(page3Intent);
                Intent autoChartActivity = new Intent(HomeActivity.this, AutomaticChartActivity.class);
                startActivity(autoChartActivity);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void replaceFragment(Fragment replaceFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, replaceFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
