package com.a16lao.wyh.widget;

import android.view.Gravity;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/25 0028
 * author: caoyan
 * description:
 */

public class IOSDialogFragment extends BaseDialogFragment {
    List<String> list = new ArrayList<>();

    @Override
    protected Object setLayout() {
        return R.layout.fragment_ios_dialog;
    }


    @Override
    protected void initView() {
        TextView tv_title = rootView.findViewById(R.id.tv_title);
        tv_title.setText("我是标题党");
        ListView lv_menu = rootView.findViewById(R.id.lv_menu);
        list.add("选项1");
        list.add("选项2");
        list.add("选项3");
        lv_menu.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list));
        lv_menu.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(getActivity(), list.get(position), Toast.LENGTH_SHORT).show());
        TextView text_cancel = rootView.findViewById(R.id.text_cancel);
        text_cancel.setText("点击取消");
        text_cancel.setOnClickListener(v -> slideToDown(rootView));
    }

    @Override
    protected int initGravity() {
        return Gravity.BOTTOM;
    }

}
