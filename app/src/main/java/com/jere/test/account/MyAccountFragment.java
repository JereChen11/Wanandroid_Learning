package com.jere.test.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jere.test.R;
import com.jere.test.article.view.collection.CollectionActivity;
import com.jere.test.databinding.FragmentMyAccountBinding;
import com.jere.test.login.view.RegisterLoginActivity;
import com.jere.test.util.Settings;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                startActivity(new Intent(getActivity(), CollectionActivity.class));
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
