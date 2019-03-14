package com.a16lao.wyh.ui.main.activity;

import android.text.InputFilter;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.config.GlobalParam;
import com.a16lao.wyh.utils.EditTextChangeListener;
import com.a16lao.wyh.utils.timer.BaseTimerTask;
import com.a16lao.wyh.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

/**
 * date:   2018/7/11 0011 下午 4:47
 * author: caoyan
 * description:忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity implements ITimerListener {
    private EditText edit_forget_verify, edit_forget_password;
    private TextView text_forget_done, text_forget_verification;
    private ImageView image_forget_eye;
    private boolean isEyeOpen;
    private Timer mTimer = null;
    private BaseTimerTask task = null;
    private int mCount = GlobalParam.VERIFY_TIME;
    private LinearLayout layout_login_item;

    @Override
    protected int setLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_title_forget_password));

        RelativeLayout view_forget_verify = findViewById(R.id.view_forget_verify);

        edit_forget_verify = view_forget_verify.findViewById(R.id.edit_login_item);
        edit_forget_verify.setHint(getString(R.string.text_register_verify));
        edit_forget_verify.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_forget_verify.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        ImageView image_forget_verify = view_forget_verify.findViewById(R.id.image_login_item);
        image_forget_verify.setImageResource(R.drawable.verification);

        text_forget_verification = view_forget_verify.findViewById(R.id.text_login_verification);
        text_forget_verification.setVisibility(View.VISIBLE);
        text_forget_verification.setText(getString(R.string.text_register_verify_get));

        RelativeLayout view_forget_password = findViewById(R.id.view_forget_password);

        edit_forget_password = view_forget_password.findViewById(R.id.edit_login_item);
        edit_forget_password.setHint(getString(R.string.text_hint_login_password));
        edit_forget_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edit_forget_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});

        layout_login_item = view_forget_password.findViewById(R.id.layout_login_item);
        layout_login_item.setVisibility(View.VISIBLE);
        ImageView image_register_password = view_forget_password.findViewById(R.id.image_login_item);
        image_register_password.setImageResource(R.drawable.password);

        image_forget_eye = view_forget_password.findViewById(R.id.image_login_eye);
        image_forget_eye.setImageResource(R.drawable.see_no);
        text_forget_done = findViewById(R.id.text_forget_done);


    }

    @Override
    protected void setListener() {
        //获取验证码
        text_forget_verification.setOnClickListener(v -> {
            mCount = GlobalParam.VERIFY_TIME;
            initTimer();
        });

        //眼睛
        layout_login_item.setOnClickListener(v -> {

            edit_forget_password.setTransformationMethod(isEyeOpen ? PasswordTransformationMethod.getInstance()
                    : HideReturnsTransformationMethod.getInstance());
            image_forget_eye.setImageResource(isEyeOpen ? R.drawable.see_no :  R.drawable.see);
            isEyeOpen = !isEyeOpen;
        });

        EditTextChangeListener listener = new EditTextChangeListener(text_forget_done, edit_forget_verify, edit_forget_password);
        edit_forget_verify.addTextChangedListener(listener);
        edit_forget_password.addTextChangedListener(listener);
        //重置并登录
        text_forget_done.setOnClickListener(v ->
                toOtherActivity(MainActivity.class, null, false)
        );
    }

    @Override
    public void onTimer() {
        runOnUiThread(() -> {
            if (text_forget_verification != null) {
                text_forget_verification.setText(MessageFormat.format("{0}s", mCount));
                text_forget_verification.setEnabled(false);
                mCount--;
                if (mCount < 0) {
                    text_forget_verification.setText(MessageFormat.format("重新获取", mCount));
                    text_forget_verification.setEnabled(true);
                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;
                    }
                    if (task != null) {
                        task.cancel();
                        task = null;
                    }

                }
            }
        });
    }

    private void initTimer() {
        mTimer = new Timer();
        task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, GlobalParam.DURATION_TIME);
    }


    @Override
    protected void initData() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

}
