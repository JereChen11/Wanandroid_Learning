package com.jere.test.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jere.test.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author jere
 */
public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

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
        ViewPager2 bannerVp2 = view.findViewById(R.id.home_banner_vp2);
        ArrayList<String> bannerUrls = new ArrayList<>();
        bannerUrls.add("https://wanandroid.com/blogimgs/cd53b340-1d94-4810-b864-567574e85de7.jpeg");
        bannerUrls.add("https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png");
        bannerUrls.add("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png");

        MyBannerVpAdapter adapter = new MyBannerVpAdapter(this, bannerUrls);
        bannerVp2.setAdapter(adapter);
//        bannerVp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//            }
//        });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
            if (weakReference.get() != null && !weakReference.get().isHidden() && !TextUtils.isEmpty(url)) {
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
