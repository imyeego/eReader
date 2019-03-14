package com.a16lao.wyh.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;

/**
 * date:   2018/5/25 0025 下午 1:16
 * author: caoyan
 * description:
 */

public class LoginItemView extends FrameLayout implements View.OnClickListener, TextWatcher {
    private LoginItemState state;
    private View itemView;
    private ImageView image_login_item, image_login_eye;
    private TextView text_login_verification;
    private EditText edit_login_item;
    private boolean isEyeOpen;

    public LoginItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public LoginItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoginItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }




    public enum LoginItemState {
        NORMAL, CODE, EYE
    }

    private void init() {
        state = LoginItemState.NORMAL;
        itemView = LayoutInflater.from(getContext()).inflate(R.layout.view_login_item, null);
        image_login_item = itemView.findViewById(R.id.image_login_item);
        edit_login_item = itemView.findViewById(R.id.edit_login_item);
        text_login_verification = itemView.findViewById(R.id.text_login_verification);
        text_login_verification.setOnClickListener(this);
        image_login_eye = itemView.findViewById(R.id.image_login_eye);
        image_login_eye.setOnClickListener(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(itemView);
        setLayoutParams(layoutParams);
    }


    public void setLoginItemState(LoginItemState state) {
        switch (state) {
            case NORMAL:
                text_login_verification.setVisibility(View.GONE);
                image_login_eye.setVisibility(View.GONE);
                break;
            case EYE:
                text_login_verification.setVisibility(View.GONE);
                image_login_eye.setVisibility(View.VISIBLE);
                edit_login_item.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case CODE:
                image_login_eye.setVisibility(View.GONE);
                text_login_verification.setVisibility(View.VISIBLE);

                break;
        }
    }

    public String getText() {
        return edit_login_item.getText().toString();
    }

    public void setHint(String hint) {
        edit_login_item.setHint(hint);
    }

    public void setInputType(int inputType) {
        edit_login_item.setInputType(inputType);
    }

    public void setMaxLength(int max) {
        edit_login_item.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
    }


    public void addTextChangedListener(){
        edit_login_item.addTextChangedListener(this);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public void clearFocus() {
        super.clearFocus();
        edit_login_item.clearFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_login_eye:
                isEyeOpen = !isEyeOpen;
                edit_login_item.setTransformationMethod(isEyeOpen ? HideReturnsTransformationMethod.getInstance()
                        : PasswordTransformationMethod.getInstance());
                image_login_eye.setImageResource(isEyeOpen ? R.drawable.see_no :  R.drawable.see);
                break;
            case R.id.text_login_verification:
                if (couldSendSMS) {
                    startCountDown();
                }
                break;
        }
    }


    private int countDown = 60;
    private int _countDown = countDown;
    private boolean couldSendSMS = true;

    public void startCountDown() {
        countDownHandler.sendEmptyMessage(1);
    }

    private Handler countDownHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0://cancel
                    _countDown = countDown;
                    couldSendSMS = true;
                    text_login_verification.setText("重新发送验证码");
                    break;
                case 1://countdown
                    _countDown--;
                    couldSendSMS = false;
                    if (_countDown == 0) {
                        countDownHandler.sendEmptyMessage(0);
                    } else {
                        text_login_verification.setText(_countDown + "s");
                        countDownHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
            }
            return false;
        }
    });

}
