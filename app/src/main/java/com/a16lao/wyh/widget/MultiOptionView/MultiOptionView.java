package com.a16lao.wyh.widget.MultiOptionView;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.ui.shelf.adapter.AdapterListType;
import com.a16lao.wyh.widget.CommonDialog;
import com.koolearn.kooreader.book.MultiSelectableBook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MultiOptionView implements View.OnClickListener{

    private Activity activity;
    private RelativeLayout root, topView, bottomView;
    private AdapterListType<MultiSelectableBook> adapter;
    private TextView tvDeleteCount;
    private ImageView ivDeleted;
    private Context context;
    private CommonDialog dialog;
    private int i = 0;

    public MultiOptionView(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public MultiOptionView(Activity activity, RelativeLayout root, Context context) {
        this.activity = activity;
        this.root = root;
        this.context = context;
    }

    public void setRoot(RelativeLayout root) {
        this.root = root;
    }

    public void setAdapter(AdapterListType adapter) {
        this.adapter = adapter;
    }

    private void createView(Activity activity, RelativeLayout root){
        if (bottomView != null){
            return;
        }

        activity.getLayoutInflater().inflate(R.layout.layout_option_bottom, root, true);
//        activity.getLayoutInflater().inflate(R.layout.layout_option_top, root, true);
//        topView = root.findViewById(R.id.rl_option_top);
        bottomView = root.findViewById(R.id.rl_option_bottom);
        ivDeleted = root.findViewById(R.id.iv_delete);
        tvDeleteCount = root.findViewById(R.id.tv_delete_count);
        bottomView.setOnClickListener(this);

    }


    public void show(){
        createView(activity, root);
    }

    public void dismiss(){
//        if (bottomView.getVisibility() == View.VISIBLE){
//        }
        Log.e("bottom view", " " + bottomView);
        if (bottomView != null){
            bottomView.setVisibility(View.GONE);
            final ViewGroup root = (ViewGroup) bottomView.getParent();

            root.removeView(bottomView);
            bottomView = null;

        }

    }

    public void updateDeleteCount(){
         i = 0;
         for (MultiSelectableBook book : adapter.getList()){
             if (book.isClicked()){
                 i ++;
             }
         }

         tvDeleteCount.setText(i == 0 ? "删除" : "删除 ( " + i + " )");
        tvDeleteCount.setTextColor(i == 0 ? ContextCompat.getColor(context, R.color.color_4)
                : ContextCompat.getColor(context, R.color.color_13));
         ivDeleted.setBackgroundResource(i == 0 ? R.drawable.delete_2 : R.drawable.delete);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.text_toolbar_left:
//                ToastUtils.show(Latte.getApplication(), "all selected event");
//
//                break;

//            case R.id.text_toolbar_right:
//                Log.e("multioption view", "finish event");
//                dismiss();
//                break;

            case R.id.rl_option_bottom:
                if (i != 0){
                    CommonDialog.Builder builder = new CommonDialog.Builder(context).setTitle("提示")
                            .setContent("删除所选项目？(" + i + ")")
                            .setLeftClickListener("取消", () -> dialog.dismiss())
                            .setRightClickListener("确定", () -> {
                                for (int i = 0; i < adapter.getList().size(); ++i){
                                    Class<?> classType = adapter.getList().get(i).getClass();
                                    try {
                                        Method method = classType.getMethod("isClicked", new Class[]{});
                                        try {
                                            boolean isClicked = (boolean)method.invoke(adapter.getList().get(i), new Object[]{});
                                            if (isClicked){
                                                adapter.getList().remove(adapter.getList().get(i));
                                            }
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    }

                                }
                                dialog.dismiss();
                                adapter.notifyDataChanged();
                                updateDeleteCount();

                            });
                    dialog = builder.create();
                    dialog.show();
                }else{
                    //nothing to do
                }
                break;

        }
    }
}
