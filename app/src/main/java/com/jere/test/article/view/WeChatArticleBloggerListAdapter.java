package com.jere.test.article.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.wechatofficialaccount.WeChatArticleBloggerList;

import java.util.List;

/**
 * @author jere
 */
public class WeChatArticleBloggerListAdapter extends RecyclerView.Adapter<WeChatArticleBloggerListAdapter.MyViewHolder> {
    private List<WeChatArticleBloggerList.DataBean> bloggerList;

    WeChatArticleBloggerListAdapter(WeChatArticleBloggerList bloggerList) {
        if (bloggerList != null) {
            this.bloggerList = bloggerList.getData();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_list_item_view_wechat_blogger_item_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String nameString = bloggerList.get(i).getName();
        if (nameString != null) {
            myViewHolder.nameTv.setText(nameString);
        }
    }

    @Override
    public int getItemCount() {
        if (bloggerList == null) {
            return 0;
        }
        return bloggerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.wechat_blogger_name_tv);
        }
    }
}
