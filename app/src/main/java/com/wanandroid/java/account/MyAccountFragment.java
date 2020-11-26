package com.wanandroid.java.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wanandroid.java.R;
import com.wanandroid.java.article.view.collection.CollectionActivity;
import com.wanandroid.java.databinding.FragmentMyAccountBinding;
import com.wanandroid.java.login.view.RegisterLoginActivity;
import com.wanandroid.java.util.Settings;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


/**
 * @author jere
 */
public class MyAccountFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MyAccountFragment";
    private FragmentMyAccountBinding mBinding;

    public MyAccountFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMyAccountBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.portraitIv.setOnClickListener(this);
        mBinding.myAccountNameTv.setOnClickListener(this);
        mBinding.myAccountEmailTv.setOnClickListener(this);
        mBinding.turnRightArrow.setOnClickListener(this);
        mBinding.myAccountLoginItem.setOnClickListener(this);
        mBinding.myAccountCollectionFolderItem.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Settings.getInstance().getIsLogin()) {
            mBinding.myAccountLoginItem.setCategoryTextString(getString(R.string.logout_cn));
        } else {
            mBinding.myAccountLoginItem.setCategoryTextString(getString(R.string.login_cn));
        }

        setUsernameAndAvatar();
    }

    private void setUsernameAndAvatar() {
        mBinding.myAccountNameTv.setText(Settings.getInstance().getUserName());
        if (!TextUtils.isEmpty(Settings.getInstance().getAvatarUrl())) {
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(this).load(Uri.parse(Settings.getInstance().getAvatarUrl()))
                    .apply(requestOptions).into(mBinding.portraitIv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.portraitIv:
            case R.id.myAccountNameTv:
            case R.id.myAccountEmailTv:
            case R.id.turnRightArrow:
                Intent turnRightArrow = new Intent(getContext(), PersonalInformationActivity.class);
                startActivity(turnRightArrow);
                break;
            case R.id.myAccountLoginItem:
                if (Settings.getInstance().getIsLogin()) {
                    Toast.makeText(getContext(), "logout", Toast.LENGTH_SHORT).show();
                    Settings.getInstance().setIsLogin(false);
                    Settings.getInstance().setUserName("");
                    mBinding.myAccountLoginItem.setCategoryTextString(getString(R.string.login_cn));
//                    refreshCurrentFragment();
                } else {
                    Intent loginActivity = new Intent(getContext(), RegisterLoginActivity.class);
                    startActivity(loginActivity);
                }
                break;
            case R.id.myAccountCollectionFolderItem:
                if (Settings.getInstance().getIsLogin()) {
                    startActivity(new Intent(getActivity(), CollectionActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), RegisterLoginActivity.class));
                }
            default:
                break;
        }
    }

    private void refreshCurrentFragment() {
        Fragment fragment = new MyAccountFragment();
        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();
    }

}
