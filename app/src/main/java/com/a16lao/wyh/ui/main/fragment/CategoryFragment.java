package com.a16lao.wyh.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.category.fragment.ComicCategoryFragment;
import com.a16lao.wyh.ui.category.fragment.EssayCategoryFragment;
import com.a16lao.wyh.ui.category.fragment.FemaleCategoryFragment;
import com.a16lao.wyh.ui.category.fragment.MaleCategoryFragment;
import com.a16lao.wyh.ui.city.adapter.WeekRankLeftAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * date:   2018/5/23 0023 上午 11:32
 * author: caoyan
 * description:
 */

public class CategoryFragment extends LazyFragment {
    private ListView list_category_left;
    private String[] mListLeft = {"男频", "女频", "短文", "听书", "漫画"};
    private WeekRankLeftAdapter mWeekRankLeftAdapter;
    private List<Fragment> mFragments;
    private Fragment currentFragment = new Fragment();
    private Fragment maleFragment, femaleFragment, essayFragment, comicFragment;


    @Override
    public Object setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        setTitleMiddleText("全站分类");
        setTitleRightNavigator(R.drawable.search, v -> {

        });
        list_category_left = rootView.findViewById(R.id.list_category_left);

    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        maleFragment = new MaleCategoryFragment();
        femaleFragment = new FemaleCategoryFragment();
        essayFragment = new EssayCategoryFragment();
        comicFragment = new ComicCategoryFragment();
        mFragments.add(maleFragment);
        mFragments.add(femaleFragment);
        mFragments.add(essayFragment);
        mFragments.add(comicFragment);
        mFragments.add(comicFragment);
        mWeekRankLeftAdapter = new WeekRankLeftAdapter(mActivity, Arrays.asList(mListLeft));
        list_category_left.setAdapter(mWeekRankLeftAdapter);
        mWeekRankLeftAdapter.setSelectedPosition(0);
        switchFragment(maleFragment).commit();
        setListener();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void setListener() {


        list_category_left.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    switchFragment(maleFragment).commit();
                    break;
                case 1:
                    switchFragment(femaleFragment).commit();
                    break;
                case 2:
                    switchFragment(essayFragment).commit();
                    break;
                case 3:
                    switchFragment(comicFragment).commit();
                    break;
                case 4:
                    switchFragment(comicFragment).commit();
                    break;
            }
            mWeekRankLeftAdapter.setSelectedPosition(position);
        });

    }


    private FragmentTransaction switchFragment(Fragment targetFragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {

            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fragment_container, targetFragment, targetFragment.getClass().getName());

        } else {
            transaction.hide(currentFragment).show(targetFragment);

        }
        currentFragment = targetFragment;
        return transaction;
    }
}
