package com.a16lao.wyh.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.a16lao.wyh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:Wangqianfeng
 * 时间: wangqianfeng on 2018/7/6.
 * 说明:
 */

@SuppressLint("ResourceAsColor")
public class CustomRing extends View {


    private Context mContext;
    private Paint mPaint;
    private int mPaintWidth = 0;        // 画笔的宽
    private int topMargin = 30;         // 上边距
    private int leftMargin = 0;        // 左边距

    private Resources mRes;
    private DisplayMetrics dm;

    private int showRateSize = 10;      // 展示文字的大小

    private int circleCenterX = 0;     // 圆心点X  要与外圆半径相等
    private int circleCenterY = 0;     // 圆心点Y  要与外圆半径相等

    private int ringOuterRidus = 96;    // 外圆的半径
    private int ringInnerRidus = 33;    // 内圆的半径
    private int ringPointRidus = 80;    // 点 - 所在圆的半径

    private float rate = 0.4f;          //点的外延距离  与  点所在圆半径的长度比率
    private float extendLineWidth = 20; //点外延后  折的横线的长度

    private RectF rectF;                // 外圆所在的矩形
    private RectF rectFPoint;           // 点所在的矩形

    private List<Integer> colorList;
    private List<Float> rateList;
    private boolean isRing;
    private boolean isShowCenterPoint;   //是否显示中心圆点
    private boolean isShowRate;

    private float preAngle = -90;
    private float endAngle = -90;

    private float preRate;

    List<Point> pointList = new ArrayList<>();
    List<Point> pointArcCenterList = new ArrayList<>();

    public CustomRing(Context context) {
        super(context);
    }

    public CustomRing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();

    }

    public void setShow(List<Integer> colorList, List<Float> reatList) {
        setShow(colorList, reatList, false);
    }

    public void setShow(List<Integer> colorList, List<Float> reatList, boolean isRing) {
        setShow(colorList, reatList, isRing, false);
    }

    public void setShow(List<Integer> colorList, List<Float> reatList, boolean isRing, boolean isShowRate) {
        setShow(colorList, reatList, isRing, isShowRate, false);
    }

    public void setShow(List<Integer> colorList, List<Float> reatList, boolean isRing, boolean isShowRate, boolean isShowCenterPoint) {
        this.colorList = colorList;
        this.rateList = reatList;
        this.isRing = isRing;
        this.isShowRate = isShowRate;
        this.isShowCenterPoint = isShowCenterPoint;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pointList.clear();

        if (colorList != null) {
            for (int i = 0; i < colorList.size(); i++) {
                mPaint.setColor(mRes.getColor(colorList.get(i)));
                mPaint.setStyle(Paint.Style.FILL);
                drawOuter(canvas, i);
            }
        }
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        mPaint.setStyle(Paint.Style.FILL);
        if (isRing) {
            drawInner(canvas);
        }

        if (isShowCenterPoint) {
            drawCenterPoint(canvas);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);


    }

    //画外圆
    private void drawOuter(Canvas canvas, int position) {
        if (rateList != null) {
            Log.e("rate", rateList.get(position) + "");
            endAngle = getAngle(rateList.get(position));
            Log.e("rate1", endAngle + "");
        }
        canvas.drawArc(rectF, preAngle, endAngle, true, mPaint);

        if (isShowRate) {
            drawArcCenterPoint(canvas, position);
        }

        preAngle = preAngle + endAngle;
    }

    /**
     * 绘制中心点
     *
     * @param canvas
     */
    private void drawCenterPoint(Canvas canvas) {
        mPaint.setColor(mRes.getColor(R.color.color_1));
        canvas.drawCircle(dip2px((getWidth() > getHeight() ? getHeight() : getWidth()) / 2 + mPaintWidth * 2 + leftMargin), dip2px((getWidth() > getHeight() ? getHeight() : getWidth()) / 2 + mPaintWidth * 2 + topMargin), dip2px(1), mPaint);
    }

    /**
     * 内部圆点
     *
     * @param canvas
     */
    private void drawInner(Canvas canvas) {
        mPaint.setColor(mRes.getColor(R.color.color_9));
        /**
         * canvas.drawCircle用于在画布上绘制圆形，通过指定圆形圆心的坐标和半径来实现
         * x圆心所在的点的x坐标
         * y圆心所在的点的y坐标
         * Ridus  ringInnerRidus內圆半径
         */

        canvas.drawCircle(dip2px(200 + ringOuterRidus + mPaintWidth * 2 + leftMargin)/2 , dip2px(200 + ringPointRidus + mPaintWidth * 2 + topMargin)/2, dip2px(ringInnerRidus), mPaint);
    }

    private void drawArcCenterPoint(Canvas canvas, int position) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mRes.getColor(R.color.color_13));
        mPaint.setStrokeWidth(dip2px(1));
        canvas.drawArc(rectFPoint, preAngle, (endAngle) / 2, true, mPaint);
        dealPoint(rectFPoint, preAngle, (endAngle) / 2, pointArcCenterList);
        Point point = pointArcCenterList.get(position);
        mPaint.setColor(mRes.getColor(R.color.color_9));
        canvas.drawCircle(point.x, point.y, dip2px(2), mPaint);

        if (preRate / 2 + rateList.get(position) / 2 < 5) {
            extendLineWidth += 20;
            rate -= 0.05f;
        } else {
            extendLineWidth = 20;
            rate = 0.4f;
        }
        // 外延画折线
        float lineXPoint1 = (point.x - dip2px(leftMargin + ringOuterRidus)) * (1 + rate);
        float lineYPoint1 = (point.y - dip2px(topMargin + ringOuterRidus)) * (1 + rate);

        float[] floats = new float[8];
        floats[0] = point.x;
        floats[1] = point.y;
        floats[2] = dip2px(leftMargin + ringOuterRidus) + lineXPoint1;
        floats[3] = dip2px(topMargin + ringOuterRidus) + lineYPoint1;
        floats[4] = dip2px(leftMargin + ringOuterRidus) + lineXPoint1;
        floats[5] = dip2px(topMargin + ringOuterRidus) + lineYPoint1;
        if (point.x >= dip2px(leftMargin + ringOuterRidus)) {
            mPaint.setTextAlign(Paint.Align.LEFT);
            floats[6] = dip2px(leftMargin + ringOuterRidus) + lineXPoint1 + dip2px(extendLineWidth);
        } else {
            mPaint.setTextAlign(Paint.Align.RIGHT);
            floats[6] = dip2px(leftMargin + ringOuterRidus) + lineXPoint1 - dip2px(extendLineWidth);
        }
        floats[7] = dip2px(topMargin + ringOuterRidus) + lineYPoint1;
        mPaint.setColor(mRes.getColor(colorList.get(position)));
        canvas.drawLines(floats, mPaint);
        mPaint.setTextSize(dip2px(showRateSize));
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText(rateList.get(position) + "%", floats[6], floats[7] + dip2px(showRateSize) / 3, mPaint);
        preRate = rateList.get(position);
    }

    private void dealPoint(RectF rectF, float startAngle, float endAngle, List<Point> pointList) {
        Path orbit = new Path();
        //通过Path类画一个90度（180—270）的内切圆弧路径
        orbit.addArc(rectF, startAngle, endAngle);

        PathMeasure measure = new PathMeasure(orbit, false);
        Log.e("路径的测量长度:", "" + measure.getLength());

        float[] coords = new float[]{0f, 0f};
        //利用PathMeasure分别测量出各个点的坐标值coords
        int divisor = 1;
        measure.getPosTan(measure.getLength() / divisor, coords, null);
        Log.e("coords:", "x轴:" + coords[0] + " -- y轴:" + coords[1]);
        float x = coords[0];
        float y = coords[1];
        Point point = new Point(Math.round(x), Math.round(y));
        pointList.add(point);
    }

    /**
     * @param percent 百分比
     * @return
     */
    private float getAngle(float percent) {
        float a = 360f / 100f * percent;
        return a;
    }


    private void initView() {
        this.mRes = mContext.getResources();
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        //得到屏幕的宽度
        dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        //计算左边距
        leftMargin = 0;

        mPaint.setColor(getResources().getColor(R.color.color_9));
        mPaint.setStrokeWidth(dip2px(mPaintWidth));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);


        //绘制外圆所在的矩形
        rectF = new RectF(dip2px(mPaintWidth + leftMargin),
                dip2px(mPaintWidth + topMargin),
                dip2px(200 + ringOuterRidus + mPaintWidth * 2 + leftMargin),
                dip2px(200 + ringOuterRidus + mPaintWidth * 2 + topMargin));


        //绘制点---所在矩形
        rectFPoint = new RectF(dip2px(mPaintWidth + leftMargin + (ringOuterRidus - ringPointRidus)),
                dip2px(mPaintWidth + topMargin + (ringOuterRidus - ringPointRidus)),
                dip2px(circleCenterX + ringPointRidus + mPaintWidth * 2 + leftMargin),
                dip2px(circleCenterY + ringPointRidus + mPaintWidth * 2 + topMargin));

    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        return (int) (dpValue * dm.density + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int px2dip(float pxValue) {
        return (int) (pxValue / dm.density + 0.5f);
    }


}
