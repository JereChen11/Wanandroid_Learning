package com.jere.test.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
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
        mBinding.myAccountSettingsItem.setOnClickListener(this);
        mBinding.myAccountCollectionFolderItem.setOnClickListener(this);

        if (Settings.getInstance().getIsLogin()) {
            mBinding.myAccountSettingsItem.setCategoryTextString(getString(R.string.logout_cn));
        } else {
            mBinding.myAccountSettingsItem.setCategoryTextString(getString(R.string.login_cn));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.turnRightArrow:
                Intent turnRightArrow = new Intent(getContext(), PersonalInformationActivity.class);
                startActivity(turnRightArrow);
                break;
            case R.id.myAccountSettingsItem:
                if (Settings.getInstance().getIsLogin()) {
                    Toast.makeText(getContext(), "logout", Toast.LENGTH_SHORT).show();
                    Settings.getInstance().setIsLogin(false);
                    Settings.getInstance().setUserName("");
                    refreshCurrentFragment();
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
        //todo refresh Fragment not working
        Log.e(TAG, "refreshCurrentFragment: ");
        Fragment fragment = new MyAccountFragment();
        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();
    }

}
