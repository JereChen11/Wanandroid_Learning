package com.jere.test.home.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jere.test.AboutMeActivity;
import com.jere.test.R;
import com.jere.test.account.MyAccountFragment;
import com.jere.test.article.view.ArticleListFragment;
import com.jere.test.automaticchart.AutomaticChartActivity;
import com.jere.test.customcomponent.BottomBarItemCustomView;
import com.jere.test.home.Page2Fragment;
import com.jere.test.tutorial.TutorialActivity;

/**
 * @author jere
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener,
        ArticleListFragment.OnFragmentInteractionListener,
        Page2Fragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ImageView navHeaderBgIv, avatarIv;
    private TextView nameTv, emailTv;
    private Toolbar toolbar;
    private FloatingActionButton chatFloatingActionBtn;

    // index to identify current nav menu item
    public static int navItemIndex = 0;
    private static final int HOME_INDEX = 0;
    private static final int LEARNING_INDEX = 1;
    private static final int INTEREST_INDEX = 2;
    private static final int ENTERTAINMENT_INDEX = 3;
    private static final int MY_INDEX = 4;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_LEARNING = "learning";
    private static final String TAG_INTEREST = "interest";
    private static final String TAG_ENTERTAINMENT = "entertainment";
    private static final String TAG_MY = "my";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

//        startTutorialActivity();

        initComponents();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //hide chat floating action button
        chatFloatingActionBtn = findViewById(R.id.chat_floating_action_btn);
        chatFloatingActionBtn.hide();

        // Navigation view header
        View navHeader = navigationView.getHeaderView(0);
        nameTv = navHeader.findViewById(R.id.name_tv);
        emailTv = navHeader.findViewById(R.id.email_tv);
        navHeaderBgIv = navHeader.findViewById(R.id.img_header_bg);
        avatarIv = navHeader.findViewById(R.id.avatar_iv);
        avatarIv.setOnClickListener(this);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        chatFloatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chartMsgIntent = new Intent(HomeActivity.this, AutomaticChartActivity.class);
                startActivity(chartMsgIntent);
            }
        });

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

    private void startTutorialActivity() {
        Intent tutorialIntent = new Intent(this, TutorialActivity.class);
        startActivity(tutorialIntent);
    }

    private void initComponents() {
        BottomBarItemCustomView homePageItem = findViewById(R.id.bottom_bar_home_page_item);
        BottomBarItemCustomView learningItem = findViewById(R.id.bottom_bar_learning_item);
        BottomBarItemCustomView interestItem = findViewById(R.id.bottom_bar_interest_item);
        BottomBarItemCustomView entertainment = findViewById(R.id.bottom_bar_entertainment_item);
        BottomBarItemCustomView myAccountItem = findViewById(R.id.bottom_bar_my_account_item);
        homePageItem.setOnClickListener(this);
        learningItem.setOnClickListener(this);
        interestItem.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        myAccountItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_bar_home_page_item:
                HomeFragment homeFragment = HomeFragment.newInstance();
                replaceFragmentAddToBackStack(homeFragment);
                break;
            case R.id.bottom_bar_learning_item:
                ArticleListFragment page1Fragment = ArticleListFragment.newInstance("page 1 Fragment", "jere test 1");
                replaceFragment(page1Fragment);
                break;
            case R.id.bottom_bar_interest_item:
                Page2Fragment page2Fragment = Page2Fragment.newInstance("page 2 Fragment", "jere test 2");
                replaceFragment(page2Fragment);
                break;
            case R.id.avatar_iv:
            case R.id.bottom_bar_my_account_item:
                MyAccountFragment myAccountFragment = MyAccountFragment.newInstance();
                replaceFragment(myAccountFragment);
                drawer.closeDrawers();
                break;
            case R.id.bottom_bar_entertainment_item:
                //todo play activity or fragment
                break;
            default:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void replaceFragmentAddToBackStack(Fragment replaceFragment) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.replace(R.id.frame, replaceFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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

        // showing dot next to notifications label
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
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
            drawer.closeDrawers();

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
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case HOME_INDEX:
                return new HomeFragment();
            case LEARNING_INDEX:
                return new ArticleListFragment();
            case INTEREST_INDEX:
//                MoviesFragment moviesFragment = new MoviesFragment();
//                return moviesFragment;
            case ENTERTAINMENT_INDEX:
//                NotificationsFragment notificationsFragment = new NotificationsFragment();
//                return notificationsFragment;
            case MY_INDEX:
                return new MyAccountFragment();
            default:
                return new ArticleListFragment();
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
                    case R.id.nav_learning:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_LEARNING;
                        break;
                    case R.id.nav_interest:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_INTEREST;
                        break;
                    case R.id.nav_entertainment:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_ENTERTAINMENT;
                        break;
                    case R.id.nav_my:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_MY;
                        break;
                    case R.id.nav_about_us:
                        startActivity(new Intent(HomeActivity.this, AboutMeActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_tutorial:
                        startActivity(new Intent(HomeActivity.this, TutorialActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                        break;
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


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

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
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
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
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        // user is in notifications fragment
        // and selected 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        // user is in notifications fragment
        // and selected 'Clear All'
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showOrHideFloatingActionBtn() {
        if (navItemIndex == 0) {
            chatFloatingActionBtn.show();
        } else {
            chatFloatingActionBtn.hide();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            avatarIv.setImageBitmap(imageBitmap);
        }
    }

}
