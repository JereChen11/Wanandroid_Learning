package com.jere.test.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jere.test.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author jere
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private OnFragmentInteractionListener mListener;
    private ViewPager2 mBannerVp2;
    private View[] indicateViews;
    private BannerHandler mBannerHandler;
    private ScheduledExecutorService mBannerScheduledExecutorService;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBannerVp2 = view.findViewById(R.id.home_banner_vp2);
        ArrayList<String> bannerUrls = new ArrayList<>();
        bannerUrls.add("");
        bannerUrls.add("https://wanandroid.com/blogimgs/cd53b340-1d94-4810-b864-567574e85de7.jpeg");
        bannerUrls.add("https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png");
        bannerUrls.add("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png");
        bannerUrls.add("https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png");
        bannerUrls.add("");

        View firstIndicateView = view.findViewById(R.id.firstIndicateView);
        View secondIndicateView = view.findViewById(R.id.secondIndicateView);
        View thirdIndicateView = view.findViewById(R.id.thirdIndicateView);
        View fourthIndicateView = view.findViewById(R.id.fourthIndicateView);
        indicateViews = new View[]{firstIndicateView, secondIndicateView, thirdIndicateView, fourthIndicateView};

        MyBannerVpAdapter adapter = new MyBannerVpAdapter(this, bannerUrls);
        mBannerVp2.setAdapter(adapter);
        mBannerVp2.setCurrentItem(1);
        mBannerVp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.e(TAG, "onPageSelected: position = " + position);
                if (position == 5) {
                    mBannerVp2.setCurrentItem(1);
                    position = 1;
                } else if (position == 0) {
                    mBannerVp2.setCurrentItem(4);
                    position = 4;
                }
                for (int i = 1; i < 5; i++) {
                    if (position == i) {
                        indicateViews[i - 1].setBackgroundResource(R.drawable.banner_navigation_point_highlight_shape);
                    } else {
                        indicateViews[i - 1].setBackgroundResource(R.drawable.banner_navigation_point_gray_shape);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

//        mBannerVp2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                return false;
//
//
//            }
//        });

        mBannerHandler = new BannerHandler(this);
        autoLoopBanner();
    }

    private void autoLoopBanner() {
        mBannerScheduledExecutorService = Executors.newScheduledThreadPool(1);
        mBannerScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1;
                mBannerHandler.sendMessage(msg);
            }
        }, 10, 1, TimeUnit.SECONDS);
    }

    public static class BannerHandler extends Handler {
        private WeakReference<HomeFragment> weakReference;

        BannerHandler(HomeFragment homeFragment) {
            weakReference = new WeakReference<>(homeFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeFragment homeFragment = weakReference.get();
            if (msg.what == 1) {
                if (homeFragment != null && !homeFragment.isHidden()) {
                    int currentItem = homeFragment.mBannerVp2.getCurrentItem();
                    if (currentItem == 4) {
                        homeFragment.mBannerVp2.setCurrentItem(1);
                    } else {
                        homeFragment.mBannerVp2.setCurrentItem(currentItem + 1);
                    }
                }
            }
        }
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroy() {
        mBannerScheduledExecutorService.shutdown();
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class MyBannerVpAdapter extends RecyclerView.Adapter<MyBannerVpAdapter.MyViewHolder> {
        private ArrayList<String> bannerUrls;
        private WeakReference<HomeFragment> weakReference;

        MyBannerVpAdapter(HomeFragment fragment, ArrayList<String> urls) {
            this.weakReference = new WeakReference<>(fragment);
            this.bannerUrls = urls;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_view_page_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String url = bannerUrls.get(position);
            if (!TextUtils.isEmpty(url) && weakReference.get() != null
                    && !weakReference.get().isHidden()
                    && !TextUtils.isEmpty(url)) {
                Glide.with(weakReference.get()).load(url).into(holder.bannerItemIv);
            }
        }

        @Override
        public int getItemCount() {
            return bannerUrls.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView bannerItemIv;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                bannerItemIv = itemView.findViewById(R.id.banner_item_iv);
            }
        }
    }
}
