package com.jere.test.article.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.ProjectItemList;
import com.jere.test.article.viewmodel.ProjectItemListViewModel;
import com.jere.test.util.RecyclerItemClickListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author jere
 */
public class ProjectItemListActivity extends AppCompatActivity {
    private static final String TAG = "ProjectItemListActivity";
    public static final String ARTICLE_DETAIL_WEB_LINK_KEY = "ARTICLE_DETAIL_WEB_LINK";
    private RecyclerView mRecyclerView;
    private ProjectItemList mProjectItemList;
    private Observer<ProjectItemList> observer = new Observer<ProjectItemList>() {
        @Override
        public void onChanged(@Nullable ProjectItemList projectItemList) {
            if (projectItemList != null) {
                Log.e(TAG, "onChanged: " + projectItemList.getData().getDatas().get(0).getTitle());
                mProjectItemList = projectItemList;
                MyAdapter adapter = new MyAdapter(ProjectItemListActivity.this, projectItemList);
                mRecyclerView.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_item_list);

        int clickItemProjectId = getIntent().getIntExtra(ArticleListFragment.PROJECT_ITEM_ID_KEY, -1);

        ProjectItemListViewModel projectItemListVm = ViewModelProviders.of(this, new ViewModelFactory()).get(ProjectItemListViewModel.class);
        if (clickItemProjectId > -1) {
            projectItemListVm.setProjectItemListLd(0, clickItemProjectId);
        }
        projectItemListVm.getProjectItemListLd().observe(this, observer);


        mRecyclerView = findViewById(R.id.project_item_list_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ProjectItemList projectItemList = projectItemListVm.getProjectItemListLd().getValue();
        if (projectItemList != null) {
            mProjectItemList = projectItemList;
            MyAdapter adapter = new MyAdapter(this, projectItemList);
            mRecyclerView.setAdapter(adapter);
        }
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ProjectItemList.DataBean.DatasBean data = mProjectItemList.getData().getDatas().get(position);
                        String link = data.getLink();

                        Intent articleDetailWebViewIntent = new Intent(ProjectItemListActivity.this, ArticleDetailWebViewActivity.class);
                        articleDetailWebViewIntent.putExtra(ARTICLE_DETAIL_WEB_LINK_KEY, link);
                        startActivity(articleDetailWebViewIntent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));


    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ArrayList<ProjectItemList.DataBean.DatasBean> dataList;
        private WeakReference<ProjectItemListActivity> weakReference;

        MyAdapter(ProjectItemListActivity activity, ProjectItemList projectItemList) {
            weakReference = new WeakReference<>(activity);
            this.dataList = projectItemList.getData().getDatas();
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.recycle_list_item_view_project_item_list, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            ProjectItemList.DataBean.DatasBean item = dataList.get(i);
//            myViewHolder.envelopIv.setImageResource(item.getEnvelopePic());
            if (weakReference != null && weakReference.get() != null && !weakReference.get().isFinishing()) {
                Glide.with(weakReference.get())
                        .load(item.getEnvelopePic())
                        .into(myViewHolder.envelopIv);
                myViewHolder.titleTv.setText(item.getTitle());
                myViewHolder.describeContentTv.setText(item.getDesc());
                myViewHolder.shareDateTv.setText(item.getNiceShareDate());
                myViewHolder.authorTv.setText(item.getAuthor());
            }
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView envelopIv;
            private TextView titleTv;
            private TextView describeContentTv;
            private TextView shareDateTv;
            private TextView authorTv;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                envelopIv = itemView.findViewById(R.id.project_item_list_envelop_iv);
                titleTv = itemView.findViewById(R.id.project_item_list_title_tv);
                describeContentTv = itemView.findViewById(R.id.project_item_list_describe_content_tv);
                shareDateTv = itemView.findViewById(R.id.project_item_list_share_date_tv);
                authorTv = itemView.findViewById(R.id.project_item_list_author_tv);
            }
        }
    }

    class ViewModelFactory implements ViewModelProvider.Factory {

        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ProjectItemListViewModel.class)) {
                return (T) new ProjectItemListViewModel();
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
