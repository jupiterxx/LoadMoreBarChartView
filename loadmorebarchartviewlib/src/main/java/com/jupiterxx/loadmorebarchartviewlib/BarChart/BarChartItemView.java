package com.jupiterxx.loadmorebarchartviewlib.BarChart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jupiterxx.loadmorebarchartviewlib.R;


/**
 * Created by jupiter on 2017/11/17.
 */

public class BarChartItemView extends View {


    private int mWidth;
    private int mHeight;
    private float mProgress;

    private Paint mBarPaint;
//    private Paint mBackgroundPaint;
    private Paint mLinePaint;

    public BarChartItemView(Context context) {
        this(context, null);
    }

    public BarChartItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarChartItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        mBarPaint = new Paint();
        mBarPaint.setColor(getResources().getColor(R.color.barChartBarColor));
        mBarPaint.setStrokeWidth(0);
        mBarPaint.setAntiAlias(true);
        mBarPaint.setStyle(Paint.Style.FILL);                   // 设置画布模式为填充

//        mBackgroundPaint = new Paint();
//        mBackgroundPaint.setColor(Color.WHITE);
//        mBackgroundPaint.setStrokeWidth(0);
//        mBackgroundPaint.setAntiAlias(true);
//        mBackgroundPaint.setStyle(Paint.Style.FILL);                   // 设置画布模式为填充

        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(R.color.barChartLineColor));
        mLinePaint.setStrokeWidth(10);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.FILL);                   // 设置画布模式为填充

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        if (progress < 0) {
            return;
        }
        if (progress > 100)
            mProgress = 100;
        else
            mProgress = progress;

        invalidate();
    }
//    public void setProgressWithAnimator(float beforeProgress , float progress) {
//        if (progress < 0) {
//            return;
//        }
//        ValueAnimator animator = ValueAnimator.ofFloat(beforeProgress, progress);
////        animator.setTarget(mBlueBall);
//        animator.setDuration(1000).start();
//		animator.setInterpolator(new OvershootInterpolator());
//        animator.addUpdateListener(animation ->
//        {
//
//                setProgress((Float) animation.getAnimatedValue());
//
//        });
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2.0f, mHeight);
        canvas.scale(1, -1);          // 移动画布(坐标系)

//        canvas.drawLine(-100, 0, 100, 0, mLinePaint);


//        canvas.scale(1, -1 );          // 移动画布(坐标系)
//        canvas.translate(50,50 );          // 移动画布(坐标系)
//
//        canvas.drawLine( - 100 , 0 , 100 , 0 , mPaint);

        Path path = new Path();                                     // 创建Path

//path.setFillType(Path.FillType.EVEN_ODD);                   // 设置Path填充模式为 奇偶规则
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);            // 反奇偶规则
        path.moveTo(-mWidth / 5, 0);
        if (mProgress > 2) {

            path.lineTo(-mWidth / 5, mHeight * (mProgress - 2) / 100);
        } else {
            path.moveTo(-mWidth / 5, mHeight * (mProgress - 2) / 100);
        }
        path.quadTo(0, mHeight * (mProgress + 2) / 100, mWidth / 5, mHeight * (mProgress - 2) / 100);

        if (mProgress > 2) {
            path.lineTo(mWidth / 5, 0);
        }
        path.close();

//        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);                    // 设置Path填充模式为非零环绕规则
//
//        canvas.drawPath(path, mBackgroundPaint);                       // 绘制Path

        canvas.drawLine(0, 0, 0, mHeight, mLinePaint);

        path.setFillType(Path.FillType.EVEN_ODD);                    // 设置Path填充模式为非零环绕规则

        canvas.drawPath(path, mBarPaint);                       // 绘制Path


    }
}
