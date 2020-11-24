package com.wanandroid.java.util.customcomponent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wanandroid.java.R;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author jere
 */
public class PullUpRefreshView extends ConstraintLayout {
    private ProgressBar progressBar;
    private TextView promptTv;

    public PullUpRefreshView(Context context) {
        this(context, null);
    }

    public PullUpRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullUpRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_view_pull_up_refresh_view, this, true);

        progressBar = view.findViewById(R.id.progressBar);
        promptTv = view.findViewById(R.id.promptTv);
    }

    public void setPromptTv(String promptString) {
        promptTv.setText(promptString);
    }

    public void setProgressBarStatus(int status) {
        progressBar.setVisibility(status);
    }

}
