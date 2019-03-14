package com.a16lao.wyh.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.utils.dimen.DimenUtils;


public class RoundTextView extends TextView{

    private Paint paint;
    private int radius;
    private int color = 0xfff77a4f;
    private static int PADDING_DEFAULT_TOP = 5;
    private static int PADDING_DEFAULT_LEFT = 8;

    public RoundTextView(Context context) {
        super(context, null);
        init();
    }

    public RoundTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init();

    }

    public RoundTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init(){
        this.setPadding(DimenUtils.dip2px(getContext(), PADDING_DEFAULT_LEFT),
                DimenUtils.dip2px(getContext(), PADDING_DEFAULT_TOP),
                DimenUtils.dip2px(getContext(), PADDING_DEFAULT_LEFT),
                DimenUtils.dip2px(getContext(), PADDING_DEFAULT_TOP));
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);


    }



    @Override
    protected void onDraw(Canvas canvas) {

        if (getPaddingTop() < PADDING_DEFAULT_TOP || getPaddingLeft() < PADDING_DEFAULT_LEFT){
            init();
        }


        radius = (getFontHeight(getTextSize()) + getPaddingBottom() + getPaddingTop()) >> 1;

        RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);

        super.onDraw(canvas);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getFontHeight(float fontSize)
    {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }


}
