package com.jere.test.article;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.ArticleBean;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<ArticleBean> dataList;

    public MyAdapter(ArrayList<ArticleBean> arrayList) {
        this.dataList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view_recycley_list_article, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ArticleBean articleBean = dataList.get(i);
        myViewHolder.iv.setImageResource(articleBean.getBackground()); //set imageView
        myViewHolder.timeTv.setText(articleBean.getPublishDate());//set time text
        myViewHolder.titleTv.setText(articleBean.getTitle());// set title text
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView timeTv;
        private TextView titleTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.cover_iv);
            timeTv = itemView.findViewById(R.id.time_tv);
            titleTv = itemView.findViewById(R.id.title_tv);
        }
    }
}