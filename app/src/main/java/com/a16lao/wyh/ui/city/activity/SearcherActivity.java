package com.a16lao.wyh.ui.city.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;

import com.a16lao.wyh.bean.city.SearcherBean;
import com.a16lao.wyh.ui.city.fragment.SearcherFragment;
import com.a16lao.wyh.ui.city.fragment.SearcherResultFragment;
import com.a16lao.wyh.utils.InputUtils;
import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.utils.storage.SPUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Spliterator;


/**
 * date:   2018/5/29 0029 下午 3:19
 * author: caoyan
 * description:
 */

public class SearcherActivity extends BaseActivity {
//    private TagFlowLayout mFlowLayout;

    private Fragment currentFragment, searcherFragment, resultFragment;
    private TextView text_search_cancel;
    private EditText edit_search;
    private LinearLayout view_search, view_search_result;
    private View line_search;
    private Set<String> historySet;
    private List<String> list;


    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        view_search = findViewById(R.id.view_search);
        view_search_result = findViewById(R.id.view_search_result);
        line_search = findViewById(R.id.line_search);
        text_search_cancel = findViewById(R.id.text_search_cancel);
        edit_search = findViewById(R.id.edit_search);
        setVisibility(false);
        searcherFragment = new SearcherFragment();
        resultFragment = new SearcherResultFragment();
        switchFragment(searcherFragment).commit();


//        mFlowLayout = findViewById(R.id.flow_search);
//        mFlowLayout.setAdapter(new FlowAdapter<String>(mStrings) {
//            @Override
//            public View getView(FlowLayout parent, int position, String s) {
//                TextView tv = (TextView) LayoutInflater.from(SearcherActivity.this).inflate(R.layout.list_item,
//                        mFlowLayout, false);
//                tv.setText(s);
//                return tv;
//            }
//        });

//        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
//            @Override
//            public void onSelected(Set<Integer> selectPosSet) {
//
//            }
//        });
    }

    @Override
    protected void initData() {
        historySet =  (LinkedHashSet)SPUtils.getObject("search_history_set");
        if (historySet == null) {
            historySet = new LinkedHashSet<>();
        }

    }

    @Override
    protected void setListener() {

        text_search_cancel.setOnClickListener(v -> {
            mActivity.finish();
            mActivity.overridePendingTransition(R.anim.out, R.anim.to_right);
        });
        edit_search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                setVisibility(true);
                switchFragment(resultFragment).commit();
                InputUtils.closeInputMethodManager(SearcherActivity.this);
                RxBus.getInstance().postSticky(new SearcherBean(edit_search.getText().toString().trim()));
                new Thread(() -> {
                    synchronized (this){
                        if (historySet.contains(edit_search.getText().toString().trim())){
                            historySet.remove(edit_search.getText().toString().trim());
                        }
                        historySet.add(edit_search.getText().toString().trim());
                        if (historySet.size() > 5){
                            historySet.remove(historySet.iterator().next());
                        }
                    }
                    SPUtils.saveObject("search_history_set", historySet);
                }).start();
            }
            return false;
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.out, R.anim.to_right);
    }

    private FragmentTransaction switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.replace(R.id.fragment_search, fragment);
        } else {
            transaction.hide(currentFragment).show(fragment);
        }

        currentFragment = fragment;

        return transaction;
    }


    private void setVisibility(boolean flag) {
        view_search.setVisibility(flag ? View.GONE : View.VISIBLE);
        line_search.setVisibility(flag ? View.GONE : View.VISIBLE);
        view_search_result.setVisibility(flag ? View.VISIBLE : View.GONE);
    }


}
