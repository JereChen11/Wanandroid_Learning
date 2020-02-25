package com.jere.test.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jere.test.R;

/**
 * @author jere
 */
public class MyAccountFragment extends Fragment implements View.OnClickListener {

    public MyAccountFragment() {

    }

    public static MyAccountFragment newInstance() {
        MyAccountFragment fragment = new MyAccountFragment();
        //todo fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView turnRightArrow = view.findViewById(R.id.turn_right_arrow);
        turnRightArrow.setOnClickListener(this);
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
            case R.id.turn_right_arrow:
                Intent turnRightArrow = new Intent(getContext(), PersonalInformationActivity.class);
                startActivity(turnRightArrow);
                break;
                //todo more case situation
            default:
                break;
        }
    }
}
