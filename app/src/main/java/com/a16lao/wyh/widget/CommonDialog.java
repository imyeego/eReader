package com.a16lao.wyh.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.utils.StringUtils;
import com.a16lao.wyh.utils.dimen.DimenUtils;


public class CommonDialog extends Dialog {

    private static Context context;
    private static CommonDialog dialog;
    private static View view;
    private static TextView tvTitle,tvContent,leftBtn,rightBtn;
    private static LinearLayout llContentView;
    public CommonDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public static class Builder{
        private Context context;
        private String title;
        private String content;
        private String leftStr;
        private String rightStr;
        private View contentView;
        private LeftBtnClickListener leftBtnClickListener;
        private RightBtnClickListener rightBtnClickListener;
        public Builder(Context context){
            this.context = context;
        }
        public Builder setTitle(String title){
            this.title = title;
            return this;
        }
        public Builder setContent(String content){
            this.content = content;
            return this;
        }
        public Builder setLeftClickListener(String leftsStr,LeftBtnClickListener leftBtnClickListener){
            this.leftStr = leftsStr;
            this.leftBtnClickListener = leftBtnClickListener;
            return this;
        }
        public Builder setRightClickListener(String rightStr,RightBtnClickListener rightBtnClickListener){
            this.rightStr = rightStr;
            this.rightBtnClickListener = rightBtnClickListener;
            return this;
        }
        public Builder setContentView(View contentView){
            this.contentView = contentView;
            return this;
        }

        public interface LeftBtnClickListener{
            void leftClick();
        }
        public interface RightBtnClickListener{
            void rightClick();
        }

        public CommonDialog create(){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new CommonDialog(context,R.style.common_dialog);
            view = inflater.inflate(R.layout.layout_common_dialog, null);
            tvTitle = view.findViewById(R.id.tv_title);
            tvContent = view.findViewById(R.id.tv_content);
            leftBtn = view.findViewById(R.id.tv_left);
            rightBtn = view.findViewById(R.id.tv_right);

            if(StringUtils.isNullString(title)){
                tvTitle.setVisibility(View.GONE);
            }else{
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(title);
                TextPaint paint = tvTitle.getPaint();
                paint.setFakeBoldText(true);
            }

            tvContent.setText(content);
            leftBtn.setText(leftStr);
            rightBtn.setText(rightStr);
            TextPaint paint = rightBtn.getPaint();
            paint.setFakeBoldText(true);
            leftBtn.setOnClickListener(v -> {
                if(leftBtnClickListener != null){
                    leftBtnClickListener.leftClick();
                }
            });
            rightBtn.setOnClickListener(v -> {
                if(rightBtnClickListener != null){
                    rightBtnClickListener.rightClick();
                }
            });

            dialog.addContentView(view, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            int screenWidth = DimenUtils.getScreenWidth();
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = screenWidth-150;
            dialog.getWindow().setAttributes(lp);
            dialog.setContentView(view);

            return dialog;
        }

    }

    public void setTitleVisible(int visible){
        tvTitle.setVisibility(visible);
    }
    public void setleftBtnColor(int color){
        leftBtn.setBackgroundColor(color);
    }
    public void setRightBtnColor(int color){
        rightBtn.setBackgroundColor(color);
    }
    public void setLeftBtnTextColor(int color){
        leftBtn.setTextColor(color);
    }
    public void setRightBtnTextColor(int color){
        rightBtn.setTextColor(color);
    }
    public void setLeftBtnVisible(int visible){
        leftBtn.setVisibility(visible);
        findViewById(R.id.tv_left).setVisibility(visible);
    }
    public void setRightBtnVisible(int visible) {
        rightBtn.setVisibility(visible);
        findViewById(R.id.tv_right).setVisibility(visible);
    }
}
