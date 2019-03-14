package com.a16lao.wyh.ui.mine.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class UpdatePasswordActivity extends BaseActivity implements ITimerListener {
    private EditText edit_update_verify, edit_update_password;
    private TextView text_update_ok, text_update_get;
    private ImageView icon_update_eye;
    private Timer mTimer = null;
    private BaseTimerTask task = null;
    private int mCount = GlobalParam.VERIFY_TIME;
    private boolean isEyeOpen;
    private LinearLayout layout_login_item;

    @Override
    protected int setLayout() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_update_password));
        edit_update_verify = findViewById(R.id.edit_update_verify);
        edit_update_password = findViewById(R.id.edit_update_password);
        text_update_ok = findViewById(R.id.text_update_ok);
        text_update_get = findViewById(R.id.text_update_get);
        icon_update_eye = findViewById(R.id.icon_update_eye);
        layout_login_item = findViewById(R.id.layout_login_item);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        edit_update_verify.addTextChangedListener(new EditTextChangeListener(text_update_ok, edit_update_verify, edit_update_password));
        edit_update_password.addTextChangedListener(new EditTextChangeListener(text_update_ok, edit_update_verify, edit_update_password));
        text_update_ok.setOnClickListener(v -> {

        });
        text_update_get.setOnClickListener(v ->
                {
                    mCount = GlobalParam.VERIFY_TIME;
                    initTimer();
                }

        );
        layout_login_item.setOnClickListener(v -> {
            edit_update_password.setTransformationMethod(isEyeOpen ? PasswordTransformationMethod.getInstance()
                    : HideReturnsTransformationMethod.getInstance());
            icon_update_eye.setImageResource(isEyeOpen ? R.drawable.see_no : R.drawable.see);
            isEyeOpen = !isEyeOpen;
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onTimer() {
        runOnUiThread(() -> {
            if (text_update_get != null) {
                text_update_get.setText(MessageFormat.format("{0}s", mCount));
                text_update_get.setEnabled(false);
                mCount--;
                if (mCount < 0) {
                    text_update_get.setText(MessageFormat.format("重新获取", mCount));
                    text_update_get.setEnabled(true);
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
