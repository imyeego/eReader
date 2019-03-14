package com.a16lao.wyh.ui.main.activity;

import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.main.pv.RegisterPresenter;
import com.a16lao.wyh.utils.EditTextChangeListener;

/**
 * date:   2018/5/25 0028
 * author: caoyan
 * description:
 */
public class RegisterActivity extends BaseActivity implements RegisterPresenter.RegisterView {
    private EditText edit_register_phone;
    private TextView text_register_next;
    private RegisterPresenter mPresenter = new RegisterPresenter(this);

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

        setTitleMiddleText(getString(R.string.text_register_quick));

        RelativeLayout view_register_phone = findViewById(R.id.view_register_phone);
        edit_register_phone = view_register_phone.findViewById(R.id.edit_login_item);
        edit_register_phone.setHint(getString(R.string.text_hint_login_phone));
        edit_register_phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_register_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});

        ImageView image_login_item = view_register_phone.findViewById(R.id.image_login_item);
        image_login_item.setImageResource(R.drawable.phone);

        text_register_next = findViewById(R.id.text_register_next);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

        edit_register_phone.addTextChangedListener(new EditTextChangeListener(text_register_next, edit_register_phone));
        text_register_next.setOnClickListener(v -> {
            toOtherActivity(Register2Activity.class, null, false);
            mPresenter.checkNumber(edit_register_phone.getText().toString().trim());
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return mPresenter;
    }


}
