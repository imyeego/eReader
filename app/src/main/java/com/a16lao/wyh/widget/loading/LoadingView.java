package com.a16lao.wyh.widget.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.a16lao.wyh.utils.view.ViewUtil;


/**
 * date:   2018/5/15 0015 上午 11:13
 * author: caoyan
 * description:
 */

public class LoadingView extends View {

    public static final int DEFAULT_SIZE = 45;
    private Paint mPaint = null;

    private boolean mHasAnimation;
    public static final float SCALE = 1.0f;

    public static final int ALPHA = 255;

    private ValueAnimator scaleAnim = null;
    private ValueAnimator alphaAnim = null;
    /**
     * 圆点的比例
     */
    float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};
    /**
     * 圆点的透明度集合
     */
    int[] alphas = new int[]{ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA};


    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);//设置画笔的颜色
        mPaint.setStyle(Paint.Style.FILL);//设置画笔的样式为填充
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(ViewUtil.dip2px(DEFAULT_SIZE, getContext()), widthMeasureSpec);//获取View的宽度
        int height = measureDimension(ViewUtil.dip2px(DEFAULT_SIZE, getContext()), heightMeasureSpec);//获取View的高度
        setMeasuredDimension(width, height);//
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);//测量规范
        int specSize = MeasureSpec.getSize(measureSpec);//测量大小
        if (specMode == MeasureSpec.EXACTLY) {//父控件已经为子控件设置确定的大小，子控件会考虑父控件给他的大小，自己需要多大设置多大
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {//子控件可以设置自己希望的指定大小
            result = Math.min(defaultSize, specSize);//取最小值
        } else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = getWidth() / 10;
        for (int i = 0; i < 8; i++) {
            canvas.save();
            Point point = circleAt(getWidth(), getHeight(), getWidth() / 2 - radius, i * (Math.PI / 4));
            canvas.translate(point.x, point.y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            mPaint.setAlpha(alphas[i]);
            canvas.drawCircle(0, 0, radius, mPaint);
            canvas.restore();
        }
    }

    Point circleAt(int width, int height, float radius, double angle) {
        float x = (float) (width / 2 + radius * (Math.cos(angle)));
        float y = (float) (height / 2 + radius * (Math.sin(angle)));
        return new Point(x, y);
    }

    public void createAnimation() {
        int[] delays = {0, 120, 240, 360, 480, 600, 720, 780, 840};
        for (int i = 0; i < 8; i++) {
            final int index = i;
            scaleAnim = ValueAnimator.ofFloat(1, 0.4f, 1);//创建ValueAnimator对象
            scaleAnim.setDuration(1000);//设置动画的持续时间
            scaleAnim.setRepeatCount(-1);//设置动画是否重复
            scaleAnim.setStartDelay(delays[i]);//延迟启动动画
            //ValueAnimator只负责第一次的内容，因此必须通过监听来实现对象的相关属性的更新
            scaleAnim.addUpdateListener(animation -> {
                scaleFloats[index] = (float) animation.getAnimatedValue();//获取当前帧的值
                postInvalidate();
            });
            scaleAnim.start();//启动属性动画

            alphaAnim = ValueAnimator.ofInt(255, 77, 255);//透明度动画
            alphaAnim.setDuration(1000);//
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(animation -> {
                alphas[index] = (int) animation.getAnimatedValue();
                postInvalidate();
            });
            alphaAnim.start();
        }
    }

    class Point {
        float x;
        float y;

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public void release() {
        scaleAnim.cancel();
        scaleAnim = null;
        alphaAnim.cancel();
        alphaAnim = null;

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!mHasAnimation) {
            mHasAnimation = true;
            createAnimation();
        }
    }
}
