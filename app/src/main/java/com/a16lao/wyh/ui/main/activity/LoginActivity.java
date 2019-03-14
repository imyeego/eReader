package com.a16lao.wyh.ui.main.activity;

import android.text.InputFilter;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.main.pv.LoginPresenter;
import com.a16lao.wyh.utils.EditTextChangeListener;

/**
 * author: caoyan
 * time:2018/5/29
 * description:登录页
 */
public class LoginActivity extends BaseActivity implements LoginPresenter.LoginView {
    private EditText edit_login_phone, edit_login_password;
    private TextView text_login_login, text_go_register, text_login_forget;
    private LoginPresenter mPresenter = new LoginPresenter(this);
    private ImageView icon_login_eye;
    private boolean isEyeOpen;
    private LinearLayout layout_login_item;
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_title_login));

        RelativeLayout view_login_phone = findViewById(R.id.view_login_phone);
        edit_login_phone = view_login_phone.findViewById(R.id.edit_login_item);
        edit_login_phone.setHint(getString(R.string.text_hint_login_phone));
        edit_login_phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_login_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});

        ImageView image_login_item = view_login_phone.findViewById(R.id.image_login_item);
        image_login_item.setImageResource(R.drawable.phone);


        RelativeLayout view_login_password = findViewById(R.id.view_login_password);
        edit_login_password = view_login_password.findViewById(R.id.edit_login_item);
        edit_login_password.setHint(getString(R.string.text_hint_login_password));
        edit_login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        edit_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        edit_login_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});

        layout_login_item = view_login_password.findViewById(R.id.layout_login_item);
        layout_login_item.setVisibility(View.VISIBLE);
        image_login_item = view_login_password.findViewById(R.id.image_login_item);
        image_login_item.setImageResource(R.drawable.password);
        icon_login_eye=view_login_password.findViewById(R.id.image_login_eye);
        icon_login_eye.setImageResource(R.drawable.see_no);

        text_login_login = findViewById(R.id.text_login_login);
        text_go_register = findViewById(R.id.text_go_register);
        text_login_forget = findViewById(R.id.text_login_forget);

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void setListener() {

        edit_login_phone.addTextChangedListener(new EditTextChangeListener(text_login_login, edit_login_phone, edit_login_password));
        edit_login_password.addTextChangedListener(new EditTextChangeListener(text_login_login, edit_login_phone, edit_login_password));
        //登录
        text_login_login.setOnClickListener(v -> {
            String phone = edit_login_phone.getText().toString().trim();
            String password = edit_login_password.getText().toString().trim();
            mPresenter.login(phone, password);
        });
        //快速注册
        text_go_register.setOnClickListener(v ->
                toOtherActivity(RegisterActivity.class, null, false));

        //忘记密码
        text_login_forget.setOnClickListener(v -> {
            toOtherActivity(ForgetPasswordActivity.class, null, false);
        });

        //眼睛
        layout_login_item.setOnClickListener(v -> {

            edit_login_password.setTransformationMethod(isEyeOpen ? PasswordTransformationMethod.getInstance()
                    : HideReturnsTransformationMethod.getInstance());
            icon_login_eye.setImageResource(isEyeOpen ? R.drawable.see_no :  R.drawable.see);
            isEyeOpen = !isEyeOpen;

        });
    }


    @Override
    public void onLoginSuccess() {
        toOtherActivity(MainActivity.class, null, false);
        finish();
    }

    @Override
    public void onLoginFail() {

    }

    @Override
    public BasePresenter getPresenter() {
        return mPresenter;
    }
}
