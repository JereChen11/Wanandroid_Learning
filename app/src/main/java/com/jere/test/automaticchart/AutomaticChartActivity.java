package com.jere.test.automaticchart;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
    private ArrayList<String> messageArrayList;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_chart);

        RecyclerView recyclerView = findViewById(R.id.chart_recycle_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        messageArrayList = new ArrayList<>();
        messageArrayList.add("Hi, Im Jere");
        mAdapter = new MyAdapter(messageArrayList);
        recyclerView.setAdapter(mAdapter);

        final EditText inputEt = findViewById(R.id.input_et);
        Button sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageArrayList.add(inputEt.getText().toString());
                mAdapter.notifyDataSetChanged();
                inputEt.setText("");
                inputEt.setFocusable(false);
            }
        });

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
                    .inflate(R.layout.item_view_recycler_list_chart, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            String messageContent = dataList.get(i);
            myViewHolder.messgeContentTv.setText(messageContent);
//            myViewHolder.avatarIv.setImageResource(R.drawable.avatar_circle);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView avatarIv;
            private TextView messgeContentTv;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                avatarIv = itemView.findViewById(R.id.avatar_iv);
                messgeContentTv = itemView.findViewById(R.id.message_content_tv);
            }
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
