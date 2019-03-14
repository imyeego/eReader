package com.a16lao.wyh.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;


/**
 * date:   2018/6/15 0015 上午 9:27
 * author: caoyan
 * description:底部导航
 */

public class BottomLayout extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private TextView text_bot_one, text_bot_two, text_bot_three, text_bot_four;
    private OnItemClickListener listener;
    private int currentPosition = 1;

    public BottomLayout(Context context) {
        this(context, null);
    }

    public BottomLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_bottom, this);
        text_bot_one = findViewById(R.id.text_bot_one);
        text_bot_two = findViewById(R.id.text_bot_two);
        text_bot_three = findViewById(R.id.text_bot_three);
        text_bot_four = findViewById(R.id.text_bot_four);
        setListener();
    }

    private void setListener() {
        text_bot_one.setOnClickListener(this);
        text_bot_two.setOnClickListener(this);
        text_bot_three.setOnClickListener(this);
        text_bot_four.setOnClickListener(this);
    }

    public void changePosition(int position) {
        text_bot_one.setSelected(position == 0);
        text_bot_two.setSelected(position == 1);
        text_bot_three.setSelected(position == 2);
        text_bot_four.setSelected(position == 3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_bot_one:
                currentPosition = 0;
                break;
            case R.id.text_bot_two:
                currentPosition = 1;
                break;
            case R.id.text_bot_three:
                currentPosition = 2;
                break;
            case R.id.text_bot_four:
                currentPosition = 3;
                break;
        }
        if (listener == null) {
            return;
        }
        listener.onItemClick(currentPosition);
        changePosition(currentPosition);

    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
