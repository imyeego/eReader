package com.a16lao.wyh.ui.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.DefaultFrgPagerAdapter;
import com.a16lao.wyh.bean.shelf.TabGoneBean;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.ui.main.fragment.BookCityFragment;
import com.a16lao.wyh.ui.main.fragment.BookShelfFragment;
import com.a16lao.wyh.ui.main.fragment.CategoryFragment;
import com.a16lao.wyh.ui.main.fragment.MineFragment;
import com.a16lao.wyh.ui.main.pv.MainPresenter;
import com.a16lao.wyh.ui.mine.dialog.PushDialogFragment;
import com.a16lao.wyh.ui.shelf.event.AllSelected;
import com.a16lao.wyh.ui.shelf.event.BookMultiSelector;
import com.a16lao.wyh.ui.shelf.event.EssayMultiSelector;
import com.a16lao.wyh.ui.shelf.event.ItemSelected;
import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.utils.ToastUtils;
import com.a16lao.wyh.widget.BottomLayout;
import com.a16lao.wyh.widget.MultiOptionView.MultiOptionView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements MainPresenter.MainView {

    private MainPresenter mPresenter = new MainPresenter(this);
    private ViewPager vp;
    private List<Fragment> mList = new ArrayList<>();
    private long mExitTime = 0L;
    private BottomLayout layout_bottom;
    private RelativeLayout rlRoot, rlOptionTop;
    private TextView tvFinish, tvAllSelect;
    private MultiOptionView optionView;

    @Override
    public boolean isTranslucent() {
        return true;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void getFromIntentData() {

    }


    @Override
    protected void initView() {

        layout_bottom = findViewById(R.id.layout_bottom);
        rlRoot = findViewById(R.id.rl_root);
        rlOptionTop = findViewById(R.id.rl_option_top);
        tvFinish = findViewById(R.id.text_toolbar_right);
        tvAllSelect = findViewById(R.id.text_toolbar_left);
        vp = findViewById(R.id.vp);

        vp.setOffscreenPageLimit(1);

        mSubscriptions.add(RxBus.getInstance().toObservableSticky(TabGoneBean.class).subscribe(tabGoneBean -> {
            if (tabGoneBean.isFlag()) {
                layout_bottom.setVisibility(View.GONE);
            } else {
                layout_bottom.setVisibility(View.VISIBLE);
            }
        }));


        mSubscriptions.add(RxBus.getInstance().toObservableSticky(MultiOptionView.class).subscribe(multiOptionView -> {

            multiOptionView.setRoot(rlRoot);
            multiOptionView.show();
            rlOptionTop.setVisibility(View.VISIBLE);
            optionView = multiOptionView;
        }));

        mSubscriptions.add(RxBus.getInstance().toObservableSticky(ItemSelected.class).subscribe(itemSelected -> {
            optionView.updateDeleteCount();
        }));
        new PushDialogFragment().show(getFragmentManager(), "");

    }


    @Override
    protected void initData() {

        mList.add(new BookShelfFragment());
        mList.add(new BookCityFragment());
        mList.add(new CategoryFragment());
        mList.add(new MineFragment());
        vp.setAdapter(new DefaultFrgPagerAdapter(getSupportFragmentManager(), mList));
        vp.setCurrentItem(1);
        layout_bottom.changePosition(1);
        mPresenter.showDialog();
    }

    @Override
    protected void setListener() {
        layout_bottom.setOnItemClickListener(position -> vp.setCurrentItem(position));
        tvFinish.setOnClickListener(view -> {
            rlOptionTop.setVisibility(View.GONE);
            optionView.dismiss();

            EventBus.getDefault().post(new BookMultiSelector(false));
            EventBus.getDefault().post(new EssayMultiSelector(false));
        });

        tvAllSelect.setOnClickListener(view -> {
            EventBus.getDefault().post(new AllSelected());
            optionView.updateDeleteCount();
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                layout_bottom.changePosition(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }


    @Override
    public BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                ToastUtils.showToast(Latte.getApplication(), "再按一次退出");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }



}
