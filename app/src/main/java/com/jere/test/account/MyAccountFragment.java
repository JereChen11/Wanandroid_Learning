package com.jere.test.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jere.test.R;
import com.jere.test.databinding.FragmentMyAccountBinding;

import androidx.fragment.app.Fragment;

/**
 * @author jere
 */
public class MyAccountFragment extends Fragment implements View.OnClickListener {
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
            //todo more case situation
            default:
                break;
        }
    }
}
