package com.a16lao.wyh.ui.city.activity;

import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.dialog.RewardDialogFragment;

public class EssayDetailActivity extends BaseActivity {
    private TextView text_essay_detail;
    private ImageView icon_essay_add, icon_essay_fabulous, icon_essay_reward, icon_essay_comment;
    private boolean isAdd, isFabulous;
    private RewardDialogFragment mRewardDialogFragment;
    private RelativeLayout view_essay_recommend;
    //    private String HTML_CONTENT = "<font color=#E61A6B>legougo.com</font><span style=\"color:#E53333;\">" +
//            "legou</span><h2><span style=\"background-color:#006600;\"></span>www.legougo.com</h2>" +
//            "<h2><span style=\"background-color:#006600;\"></span>www.legougo.com</h2>" +
//            "<h1 style=\"color:red;\">文章详情图片发大效果</h1><a href=\"http://www.hongshengpeng.com/\">乐够go</a>，" +
//            "<span style=\"color:red; font-size:18px\">可以有多张图片:</span><br />" +
//            "<img src=\"http://www.hongshengpeng.com/UpLoadFiles/20131015/2013101522313185.jpg\">" +
//            "<h2><span style=\"background-color:#006600;\"></span>www.hongshengpeng.com</h2>" +
//            "<img src=\"http://hongshengpeng.com/UpLoadFiles/20130925/2013092523025821.jpg\">";
    private String HTML_CONTENT = "<font color=#E61A6B>legougo.com</font><span style=\"color:#E53333;\">" +
            "legou</span><p><span style=\"background-color:#006600;\"></span>www.legougo.com</p>" +
            "<p><span style=\"background-color:#006600;\"></span>www.legougo.com</p>" +
            "<p style=\"color:red;\">文章详情图片发大效果</p><a href=\"http://www.hongshengpeng.com/\">乐够go</p>，" +
            "<span style=\"color:red; font-size:18px\">可以有多张图片:</span><br />" +
            "<img src=\"http://www.hongshengpeng.com/UpLoadFiles/20131015/2013101522313185.jpg\">" +
            "<p><span style=\"background-color:#006600;\"></span>www.hongshengpeng.com</p>" +
            "<img src=\"http://hongshengpeng.com/UpLoadFiles/20130925/2013092523025821.jpg\">";

    @Override
    protected int setLayout() {
        return R.layout.activity_essay_detail;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {

        text_essay_detail = findViewById(R.id.text_essay_detail);
        icon_essay_add = findViewById(R.id.icon_essay_add);
        icon_essay_fabulous = findViewById(R.id.icon_essay_fabulous);
        icon_essay_reward = findViewById(R.id.icon_essay_reward);
        icon_essay_comment = findViewById(R.id.icon_essay_comment);
        view_essay_recommend = findViewById(R.id.view_essay_recommend);
    }

    @Override
    protected void initData() {
        text_essay_detail.setText(Html.fromHtml(HTML_CONTENT));
        text_essay_detail.setMovementMethod(ScrollingMovementMethod.getInstance());
        mRewardDialogFragment = new RewardDialogFragment();
    }


    @Override
    protected void setListener() {
        promptView_view.setOnClickListener(v1 -> {
            promptView_view.showSuccess();
        });
        icon_essay_add.setOnClickListener(v -> {
            promptView_view.showEmpty();

//            if (isAdd) {
//                icon_essay_add.setImageResource(R.drawable.article_add_sel);
//                isAdd = false;
//            } else {
//                icon_essay_add.setImageResource(R.drawable.article_add);
//                isAdd = true;
//            }
        });
        icon_essay_fabulous.setOnClickListener(v -> {
            if (isFabulous) {
                icon_essay_fabulous.setImageResource(R.drawable.fabulous_sel);
                isFabulous = false;
            } else {
                icon_essay_fabulous.setImageResource(R.drawable.fabulous);
                isFabulous = true;
            }
        });
        icon_essay_reward.setOnClickListener(v -> {
            if (mRewardDialogFragment.isAdded()) {
                mRewardDialogFragment.getDialog().show();
            } else {
                mRewardDialogFragment.show(getFragmentManager(), "reward");
            }
//            if (isLogin()) {

//        }
        });
        view_essay_recommend.setOnClickListener(v -> toOtherActivity(BookRecommendActivity.class, null, false));

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
