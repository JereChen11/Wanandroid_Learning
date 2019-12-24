package com.jere.test.article;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jere.test.R;
import com.jere.test.article.modle.ArticleBean;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArticleListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArticleListFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author jere
 */
public class ArticleListFragment extends Fragment implements RecyclerView.OnItemTouchListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ArticleListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleListFragment newInstance(String param1, String param2) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArticleBean articleBean = new ArticleBean();
        articleBean.setAuthor("jere");
        articleBean.setBackground(R.drawable.ic_launcher_background);
        articleBean.setPublishDate("2019/12/23");
        articleBean.setTitle("Android学习笔记-Activity与Fragment的联系与区别");
        articleBean.setContent("Fragment是Activity的一部分，对于Activity能构建自己的UI到相对应的Activity。可以认为Fragment是Activity的子类。但是Fragment不能独立存在，它必须和Activity相联系，有自己的生命周期，可以接受自己的输入事件，但是它的生命周期是基于对应Activity的生命周期，例如，当Activity暂停时，其中的所有Fragment也都会暂停，当Activity被销毁时，所有Fragment也都会被销毁。\n" +
                "\n" +
                "Activity是一个应用程序组件，用于构建用户交互界面，在Activity中添加Fragment是为了创建多窗口UI界面，一个Activity可以包含多个Fragment，同一个Fragment也可以在不同的Activity间重用。\n" +
                "\n" +
                "Activity和Fragment之间的生命周期中，最重要的区别就是：如何存储到各自的后台堆栈中。默认情况下，Activity会被放到系统管理Activity的后台堆栈中(以便用户可以使用后退按钮导航回该Activity)。但是对于Fragment，只有当显式地请求在删除Fragment期间通过调用addToBackStack()来保存实例时，才会将Fragment放入由对应Activity管理的后台堆栈中。\n");
        ArrayList<ArticleBean> articleBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            articleBeans.add(articleBean);
        }
        MyAdapter adapter = new MyAdapter(articleBeans);
        RecyclerView recyclerView = view.findViewById(R.id.article_recycle_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setOnClickListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
