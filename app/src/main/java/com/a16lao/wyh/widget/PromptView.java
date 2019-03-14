package com.a16lao.wyh.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;

/**
 * date:   2018/5/28 0028 上午 11:11
 * author: caoyan
 * description:空白页
 */

public class PromptView extends LinearLayout {
    public static final int PROMPT_NO_DATA = 1;
    public static final int PROMPT_NET_ERROR = 2;
    public static final int PROMPT_RESULT_EMPTY = 0;
    public static final int PROMPT_BOOK_EMPTY = 3;


    private static final int STATE_SUCCESS = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_NETWORK_ERROR = 2;
    private static final int STATE_NO_NETWORK = 3;
    private static final int STATE_EMPTY = 4;
    private ImageView mPromptView;
    private TextView mPromptText;
    //当前的加载状态
    private int mCurrentState;


    public PromptView(Context context) {

        this(context, null);

    }

    public PromptView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PromptView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        init();
        showSuccess();

    }

    /**
     * 加载成功，隐藏加载状态的View
     */
    public void showSuccess() {
        mCurrentState = STATE_SUCCESS;
        showViewByState(mCurrentState);
    }

    public void showEmpty() {
        mCurrentState = STATE_EMPTY;
        showViewByState(mCurrentState);
        showPrompt(PROMPT_RESULT_EMPTY);
    }


    protected void showPrompt(int flag) {
        int imgId = 0;
        String errorText = "";
        switch (flag) {
            case PROMPT_RESULT_EMPTY:
                errorText = "结果为空，试试其他组";
                imgId = R.drawable.result_nothing;
                break;
            case PROMPT_NO_DATA:
                imgId = R.drawable.nothing;
                errorText = "空空如也";
                break;
            case PROMPT_NET_ERROR:
                imgId = R.drawable.load_again;
                errorText = "点击屏幕，重新加载";
                break;

            case PROMPT_BOOK_EMPTY:
                errorText = "哎呀！作品下架了";
                imgId = R.drawable.book_nothing;
                break;

        }
        setLoadingImg(imgId);
        setLoadingText(errorText);
    }

    private void showViewByState(int state) {

        //如果当前状态为加载成功，隐藏此View，反之显示
        this.setVisibility(state == STATE_SUCCESS ? View.GONE : View.VISIBLE);
    }


    private void init() {
        mPromptView = (ImageView) getChildAt(0);
        mPromptText = (TextView) getChildAt(1);
    }

    /**
     * 设置加载文字。
     *
     * @param topString
     */
    public void setLoadingText(String topString) {
        if (mPromptText != null && !TextUtils.isEmpty(topString)) {
            mPromptText.setText(topString);
            mPromptText.setVisibility(View.VISIBLE);

        }
    }

    public void setLoadingImg(int resImg) {
        if (mPromptView != null) {
            mPromptView.setImageResource(resImg);
            mPromptView.setVisibility(View.VISIBLE);
        }
    }


}
