package com.jere.test.automaticchart;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.MyAdapter;
import com.jere.test.article.modle.ArticleBean;

import java.util.ArrayList;

/**
 * @author jere
 */
public class AutomaticChartActivity extends AppCompatActivity {
    private static final String TAG = "AutomaticChartActivity";
    private ChartMessageViewModel chartMessageVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_chart);

        RecyclerView recyclerView = findViewById(R.id.chart_recycle_view);
        recyclerView.setAdapter();

        chartMessageVm = ViewModelProviders.of(this).get(ChartMessageViewModel.class);
        chartMessageVm.getMessageLd().observe(this, chartMessageObserver);

    }

    private Observer<String> chartMessageObserver = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            Log.d(TAG, "onChanged: " + s);
        }
    };

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ArrayList<String> dataList;

        public MyAdapter(ArrayList<String> arrayList) {
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
