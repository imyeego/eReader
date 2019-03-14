package com.a16lao.wyh.ui.category.popupwindow;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePopupWindow;
import com.a16lao.wyh.bean.category.ChooseTypeBean;
import com.a16lao.wyh.ui.category.adapter.ChooseTypeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * date:   2018/5/24 0024 上午 9:56
 * author: caoyan
 * description:
 */

public class ChooseTypePopupWindow extends BasePopupWindow {

    private Resources resource;
    private TextView text_pop_reset, text_pop_sure;
    private List<List<ChooseTypeBean>> mList;
    private ChooseTypeAdapter typeAdapter;
    private CallBack mCallBack;

    public ChooseTypePopupWindow(Context context) {
        super(context);

    }

    @Override
    protected int setLayout() {
        return R.layout.pop_choose_type;
    }

    @Override
    protected void initData(View view) {
        view.setOnTouchListener((v, event) -> false);

        ListView list_choose_type = view.findViewById(R.id.list_choose_type);

        mList = new ArrayList<>();

        resource = getContext().getResources();

        String[] mStrings = resource.getStringArray(R.array.arr_choose_type);

        initDatas();

        typeAdapter = new ChooseTypeAdapter(getContext(), Arrays.asList(mStrings), mList);
        list_choose_type.setAdapter(typeAdapter);

        View footer = LayoutInflater.from(getContext()).inflate(R.layout.list_choose_type_footer, null);
        text_pop_reset = footer.findViewById(R.id.text_pop_reset);
        text_pop_sure = footer.findViewById(R.id.text_pop_sure);
        list_choose_type.addFooterView(footer);

        setListener();

    }

    private void setListener() {

        text_pop_reset.setOnClickListener(v -> {
            initDatas();
            typeAdapter.notifyDataSetChanged();
        });
        text_pop_sure.setOnClickListener(v -> {
            int count = 0;
            OUT:
            for (int i = 0; i < mList.size(); i++) {

                for (int j = 0; j < mList.get(i).size(); j++) {
                    if (j != 0) {
                        if (mCallBack != null && mList.get(i).get(j).isFlag()) {

                            mCallBack.onCallBack(mList.get(i).get(j).getString());
                            break OUT;
                        }
                    }


                }

                if (mList.get(i).get(0).isFlag()) {
                    count++;
                    if (mCallBack != null && count == mList.size()) {
                        mCallBack.onCallBack("不限筛选项");
                        break;
                    }
                }
            }


            dismiss();

        });
    }

    private void initDatas() {
        mList.clear();

        String[] arr0 = resource.getStringArray(R.array.arr_choose_type_form);
        String[] arr1 = resource.getStringArray(R.array.arr_choose_type_progress);
        String[] arr2 = resource.getStringArray(R.array.arr_choose_type_price);
        String[] arr3 = resource.getStringArray(R.array.arr_choose_type_time);
        String[] arr4 = resource.getStringArray(R.array.arr_choose_type_words);
        String[] arr5 = resource.getStringArray(R.array.arr_choose_type_author);

        mList.add(setData(arr0));
        mList.add(setData(arr1));
        mList.add(setData(arr2));
        mList.add(setData(arr3));
        mList.add(setData(arr4));
        mList.add(setData(arr5));
    }

    private List<ChooseTypeBean> setData(String[] arr) {
        List<ChooseTypeBean> bean = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                bean.add(new ChooseTypeBean(arr[i], true));
            } else {
                bean.add(new ChooseTypeBean(arr[i], false));
            }

        }
        return bean;
    }

    public void setOnCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public interface CallBack {

        void onCallBack(String s);
    }

}
