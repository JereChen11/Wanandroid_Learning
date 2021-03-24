package com.wanandroid.java.ui.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wanandroid.java.R;
import com.wanandroid.java.databinding.FragmentMyAccountBinding;
import com.wanandroid.java.ui.base.BaseVmFragment;
import com.wanandroid.java.ui.collection.CollectionActivity;
import com.wanandroid.java.ui.login.RegisterLoginActivity;
import com.wanandroid.java.util.SpSettings;


/**
 * @author jere
 */
public class MyAccountFragment extends BaseVmFragment<MyAccountViewModel, FragmentMyAccountBinding> implements View.OnClickListener {
    private static final String TAG = "MyAccountFragment";

    public MyAccountFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        dataBinding.portraitIv.setOnClickListener(this);
        dataBinding.myAccountNameTv.setOnClickListener(this);
        dataBinding.myAccountEmailTv.setOnClickListener(this);
        dataBinding.turnRightArrow.setOnClickListener(this);
        dataBinding.myAccountLoginItem.setOnClickListener(this);
        dataBinding.myAccountCollectionFolderItem.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SpSettings.getInstance().getIsLogin()) {
            dataBinding.myAccountLoginItem.setCategoryTextString(getString(R.string.logout_cn));
        } else {
            dataBinding.myAccountLoginItem.setCategoryTextString(getString(R.string.login_cn));
        }

        setUsernameAndAvatar();
    }

    private void setUsernameAndAvatar() {
        dataBinding.myAccountNameTv.setText(SpSettings.getInstance().getUserName());
        if (!TextUtils.isEmpty(SpSettings.getInstance().getAvatarUrl())) {
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(this).load(Uri.parse(SpSettings.getInstance().getAvatarUrl()))
                    .apply(requestOptions).into(dataBinding.portraitIv);
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
                if (SpSettings.getInstance().getIsLogin()) {
                    Toast.makeText(getContext(), "logout", Toast.LENGTH_SHORT).show();
                    SpSettings.getInstance().setIsLogin(false);
                    SpSettings.getInstance().setUserName("");
                    dataBinding.myAccountLoginItem.setCategoryTextString(getString(R.string.login_cn));
                } else {
                    Intent loginActivity = new Intent(getContext(), RegisterLoginActivity.class);
                    startActivity(loginActivity);
                }
                break;
            case R.id.myAccountCollectionFolderItem:
                if (SpSettings.getInstance().getIsLogin()) {
                    startActivity(new Intent(getActivity(), CollectionActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), RegisterLoginActivity.class));
                }
            default:
                break;
        }
    }

}
