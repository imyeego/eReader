package com.a16lao.wyh.ui.main.activity;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.a16lao.wyh.ui.main.pv.RegisterPresenter;
import com.a16lao.wyh.utils.timer.BaseTimerTask;
import com.a16lao.wyh.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

/**
 * date:   2018/5/28 0028
 * author: caoyan
 * description:
 */
public class Register2Activity extends BaseActivity implements ITimerListener, TextWatcher, RegisterPresenter.RegisterView {
    private EditText edit_register_verify, edit_register_password;
    private TextView text_register_done, text_login_verification, text_register_protocol, text_register_protocol_selected;
    private ImageView image_register_eye;
    private boolean isSelected, isEyeOpen;
    private RegisterPresenter mPresenter = new RegisterPresenter(this);
    private Timer mTimer = null;
    private BaseTimerTask task = null;
    private int mCount = GlobalParam.VERIFY_TIME;
    private LinearLayout layout_login_item;

    @Override
    protected void initView() {

        setTitleMiddleText(getString(R.string.text_register_quick));
        RelativeLayout view_register_verify = findViewById(R.id.view_register_verify);

        edit_register_verify = view_register_verify.findViewById(R.id.edit_login_item);
        edit_register_verify.setHint(R.string.text_register_verify);
        edit_register_verify.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_register_verify.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        ImageView image_register_verify = view_register_verify.findViewById(R.id.image_login_item);
        image_register_verify.setImageResource(R.drawable.verification);

        text_login_verification = view_register_verify.findViewById(R.id.text_login_verification);
        text_login_verification.setVisibility(View.VISIBLE);
        text_login_verification.setText(R.string.text_register_verify_get);

        RelativeLayout view_register_password = findViewById(R.id.view_register_password);

        edit_register_password = view_register_password.findViewById(R.id.edit_login_item);
        edit_register_password.setHint(R.string.text_hint_login_password);
        edit_register_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        edit_register_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        edit_register_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});

        layout_login_item = view_register_password.findViewById(R.id.layout_login_item);
        layout_login_item.setVisibility(View.VISIBLE);
        ImageView image_register_password = view_register_password.findViewById(R.id.image_login_item);
        image_register_password.setImageResource(R.drawable.password);

        image_register_eye = view_register_password.findViewById(R.id.image_login_eye);
        image_register_eye.setImageResource(R.drawable.see_no);
        text_register_done = findViewById(R.id.text_register_done);


        text_register_protocol = findViewById(R.id.text_register_protocol);

        text_register_protocol_selected = findViewById(R.id.text_register_protocol_selected);


    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_register_two;
    }

    @Override
    protected void setListener() {
        //获取验证码
        text_login_verification.setOnClickListener(v -> {
            mCount = GlobalParam.VERIFY_TIME;
            initTimer();
        });

        //眼睛
        layout_login_item.setOnClickListener(v -> {

            edit_register_password.setTransformationMethod(isEyeOpen ? PasswordTransformationMethod.getInstance()
                    : HideReturnsTransformationMethod.getInstance());
            image_register_eye.setImageResource(isEyeOpen ? R.drawable.see_no :  R.drawable.see);
            isEyeOpen = !isEyeOpen;
        });

        text_register_protocol_selected.setOnClickListener(v -> {
            if (!isSelected) {
                text_register_protocol_selected.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.protocol),
                        null, null, null);
                isSelected = true;
            } else {
                text_register_protocol_selected.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.protocol_sel),
                        null, null, null);
                isSelected = false;

            }
            if (!isSelected && !TextUtils.isEmpty(edit_register_verify.getText().toString().trim())
                    && !TextUtils.isEmpty(edit_register_password.getText().toString().trim())) {
                text_register_done.setEnabled(true);
            } else {
                text_register_done.setEnabled(false);
            }
        });
        //用户协议
        text_register_protocol.setOnClickListener(v -> {

        });

        edit_register_verify.addTextChangedListener(this);
        edit_register_password.addTextChangedListener(this);
        //注册
        text_register_done.setOnClickListener(v ->
                mPresenter.register(edit_register_verify.getText().toString().trim(),
                        edit_register_password.getText().toString().trim())

        );
    }

    @Override
    public void onTimer() {
        runOnUiThread(() -> {
            if (text_login_verification != null) {
                text_login_verification.setText(MessageFormat.format("{0}s", mCount));
                text_login_verification.setEnabled(false);
                mCount--;
                if (mCount < 0) {
                    text_login_verification.setText(MessageFormat.format("重新获取", mCount));
                    text_login_verification.setEnabled(true);
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
        return mPresenter;
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!isSelected && !TextUtils.isEmpty(edit_register_verify.getText().toString().trim())
                && !TextUtils.isEmpty(edit_register_password.getText().toString().trim())) {
            text_register_done.setEnabled(true);
        } else {
            text_register_done.setEnabled(false);
        }

    }
}
