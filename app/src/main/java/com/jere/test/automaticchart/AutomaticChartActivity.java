package com.jere.test.automaticchart;

import android.app.Activity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
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
import com.jere.test.automaticchart.model.MessageContent;

import java.util.ArrayList;

/**
 * @author jere
 */
public class AutomaticChartActivity extends AppCompatActivity {
    private static final String TAG = "AutomaticChartActivity";
    private ChartMessageViewModel chartMessageVm;
    private ArrayList<MessageContent> messageArrayList;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_chart);

        RecyclerView recyclerView = findViewById(R.id.chart_recycle_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        messageArrayList = new ArrayList<>();
        MessageContent messageContent = new MessageContent();
        messageContent.setMessage("Hi, I'm robot, what can I help you.");
        messageContent.setUserSend(false);
        messageArrayList.add(messageContent);
        mAdapter = new MyAdapter(messageArrayList);
        recyclerView.setAdapter(mAdapter);

        final EditText inputEt = findViewById(R.id.input_et);
        Button sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputContentString = inputEt.getText().toString();
                if (TextUtils.isEmpty(inputContentString)) {
                    return;
                }
                MessageContent userMsgCont = new MessageContent();
                userMsgCont.setUserSend(true);
                userMsgCont.setMessage(inputContentString);
                messageArrayList.add(userMsgCont);
                mAdapter.notifyDataSetChanged();
                inputEt.setText("");
                hideKeyboard(AutomaticChartActivity.this);
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
        private ArrayList<MessageContent> dataList;

        public MyAdapter(ArrayList<MessageContent> arrayList) {
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
            MessageContent messageContent = dataList.get(i);
            if (messageContent.isUserSend()) {
                myViewHolder.userMessageContentTv.setText(messageContent.getMessage());
            } else {
                myViewHolder.robotAvatarIv.setVisibility(View.VISIBLE);
                myViewHolder.robotMessageContentTv.setVisibility(View.VISIBLE);
                myViewHolder.robotMessageContentTv.setText(messageContent.getMessage());
                myViewHolder.userAvatarIv.setVisibility(View.GONE);
                myViewHolder.userMessageContentTv.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView userAvatarIv;
            private TextView userMessageContentTv;
            private ImageView robotAvatarIv;
            private TextView robotMessageContentTv;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                userAvatarIv = itemView.findViewById(R.id.user_avatar_iv);
                userMessageContentTv = itemView.findViewById(R.id.user_message_content_tv);
                robotAvatarIv = itemView.findViewById(R.id.robot_avatar_iv);
                robotMessageContentTv = itemView.findViewById(R.id.robot_message_content_tv);
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
