package com.wanandroid.java.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.wanandroid.java.R;
import com.wanandroid.java.ui.aboutme.AboutMeActivity;
import com.wanandroid.java.ui.account.MyAccountFragment;
import com.wanandroid.java.ui.knowledgesystem.KnowledgeSystemFragment;
import com.wanandroid.java.ui.project.ProjectArticleFragment;
import com.wanandroid.java.ui.wechat.WeChatBlogArticleFragment;
import com.wanandroid.java.databinding.ActivityHomeBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author jere
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private NavigationView navigationView;
    private ImageView navHeaderBgIv, avatarIv;
    private TextView nameTv, emailTv;

    // index to identify current nav menu item
    public static int navItemIndex = 0;
    private static final int HOME_INDEX = 0;
    private static final int COMPLETE_PROJECT_INDEX = 1;
    private static final int WECHAT_BLOG_INDEX = 2;
    private static final int KNOWLEDGE_SYSTEM_INDEX = 3;
    private static final int MY_INDEX = 4;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_COMPLETE_PROJECT = "completeProject";
    private static final String TAG_WECHAT_BLOG = "weChatBlog";
    private static final String TAG_KNOWLEDGE_SYSTEM = "knowledgeSystem";
    private static final String TAG_MY = "my";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ActivityHomeBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);


//        startTutorialActivity();

        initComponents();

        setSupportActionBar(mBinding.homePageContent.toolbar);
        mBinding.homePageContent.toolbar.setBackground(null);
        mBinding.homePageContent.toolbar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        navigationView = mBinding.navigationView;

        // Navigation view header
        View navHeader = navigationView.getHeaderView(0);
        nameTv = navHeader.findViewById(R.id.nameTv);
        emailTv = navHeader.findViewById(R.id.emailTv);
        navHeaderBgIv = navHeader.findViewById(R.id.headerBackgroundIv);
        avatarIv = navHeader.findViewById(R.id.avatarIv);
        avatarIv.setOnClickListener(this);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // load nav menu header data
        loadNavHeaderView();

        // initializing notifications menu
        setNavigationMenuView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    private void initComponents() {
        mBinding.homePageContent.homeBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        boolean result = false;
                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                navItemIndex = 0;
                                replaceFragment(new HomeFragment());
                                result = true;
                                break;
                            case R.id.nav_complete_project:
                                navItemIndex = 1;
                                replaceFragment(new ProjectArticleFragment());
                                result = true;
                                break;
                            case R.id.nav_wechat_blog:
                                navItemIndex = 2;
                                replaceFragment(new WeChatBlogArticleFragment());
                                result = true;
                                break;
                            case R.id.nav_my:
                                navItemIndex = 4;
                                replaceFragment(new MyAccountFragment());
                                result = true;
                                break;
                            case R.id.nav_knowledge_system:
                                navItemIndex = 3;
                                replaceFragment(new KnowledgeSystemFragment());
                                result = true;
                                break;
                            default:
                                break;
                        }
                        setToolbarTitle();
                        selectNavMenu();

                        return result;
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatarIv:
                navItemIndex = 4;
                replaceFragment(new MyAccountFragment());
                mBinding.drawerLayout.closeDrawers();
                break;
            default:
                break;
        }
        setToolbarTitle();
    }


    private void replaceFragment(Fragment replaceFragment) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.replace(R.id.frame, replaceFragment);
        fragmentTransaction.commit();
    }

    private FragmentTransaction getFragmentTransaction() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.beginTransaction();
    }


    private void loadNavHeaderView() {
        // name, website
        nameTv.setText("Jere Chen");
        emailTv.setText("jerechen11@gmail.com");
        // loading header background image
        Glide.with(this).load(R.color.gray)
                .into(navHeaderBgIv);

        // Loading profile image
        Glide.with(this).load(R.drawable.default_portrait)
                .thumbnail(0.5f)
                .into(avatarIv);

    }

    /***
     * Returns respected fragment that user
     * selected from notifications menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current notifications menu again, don't do anything
        // just close the notifications drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            mBinding.drawerLayout.closeDrawers();

//            showOrHideFloatingActionBtn();
            return;
        }

        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();

        //Closing drawer on item click
        mBinding.drawerLayout.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case HOME_INDEX:
                return new HomeFragment();
            case COMPLETE_PROJECT_INDEX:
                return new ProjectArticleFragment();
            case WECHAT_BLOG_INDEX:
                return new WeChatBlogArticleFragment();
            case KNOWLEDGE_SYSTEM_INDEX:
                return new KnowledgeSystemFragment();
            case MY_INDEX:
                return new MyAccountFragment();
            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setNavigationMenuView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the notifications menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_complete_project:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_COMPLETE_PROJECT;
                        break;
                    case R.id.nav_wechat_blog:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_WECHAT_BLOG;
                        break;
                    case R.id.nav_knowledge_system:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_KNOWLEDGE_SYSTEM;
                        break;
                    case R.id.nav_my:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_MY;
                        break;
                    case R.id.nav_about_us:
                        startActivity(new Intent(HomeActivity.this, AboutMeActivity.class));
                        mBinding.drawerLayout.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                        break;
                }
                if (menuItem.getItemId() != R.id.nav_about_us) {
                    mBinding.homePageContent.homeBottomNavigationView.setSelectedItemId(menuItem.getItemId());
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mBinding.drawerLayout,
                mBinding.homePageContent.toolbar,
                R.string.openDrawer,
                R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        mBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other notifications menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
