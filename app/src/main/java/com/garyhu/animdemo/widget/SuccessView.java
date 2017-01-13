package com.garyhu.animdemo.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.garyhu.animdemo.anim.Point;
import com.garyhu.animdemo.anim.PointEvaluator;

/**
 * 作者： garyhu.
 * 时间： 2017/1/13.
 * 一个完成的对号
 */

public class SuccessView extends View {

    private static final float PAINT_WIDTH = 3f;
    private float radius = 50f;
    private float mCenterX,mCenterY;
    private int mDegree;

    private Paint mPaint,mCirclePaint;
    private Path mLeftPath,mRightPath,path;
    private PathMeasure mLeftPathMeasure,mRightPathMeasure;
    private float[] mCurrentPosition = new float[2];
    private RectF mRectF = new RectF();

    private ScaleAnimListener listener;

    public void setListener(ScaleAnimListener listener){
        this.listener = listener;
    }

    public SuccessView(Context context) {
        super(context);
        init();
    }

    public SuccessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SuccessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xffC62F2F);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(PAINT_WIDTH);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(0xffC62F2F);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(PAINT_WIDTH);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = getWidth()/2;
        mCenterY = getHeight()/2;
        radius = Math.min(mCenterX,mCenterY)*0.9f;

        path.moveTo(radius*0.7f,radius*1.2f);

        mLeftPath = new Path();
        mLeftPath.moveTo(radius*0.7f,radius*1.2f);
        mLeftPath.lineTo(radius+3f,radius*3/2);
        mLeftPathMeasure = new PathMeasure(mLeftPath,false);

        mRightPath = new Path();
        mRightPath.moveTo(radius+3f,radius*3/2);
        mRightPath.lineTo(radius*1.7f,radius*0.8f);
        mRightPathMeasure = new PathMeasure(mRightPath,false);

        mRectF.left = mCenterX-radius;
        mRectF.top = mCenterY-radius;
        mRectF.right = mCenterX+radius;
        mRectF.bottom = mCenterY+radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRectF,-160,mDegree,false,mCirclePaint);
        canvas.drawPath(path,mPaint);
//        canvas.drawPath(mRightPath,mPaint);
    }

    public void startAnim(){
        if(mLeftPathMeasure==null||mRightPathMeasure==null){
            return;
        }
        //画弧
        ValueAnimator circleAnim = ValueAnimator.ofInt(0,360);
        circleAnim.setDuration(500);
        circleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mDegree = value;
                invalidate();
            }
        });

        //属性动画实现
        final ValueAnimator leftAnim = ValueAnimator.ofFloat(0, mLeftPathMeasure.getLength());
        leftAnim.setDuration(350);
        leftAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mLeftPathMeasure.getPosTan(value, mCurrentPosition, null);
                path.lineTo(mCurrentPosition[0],mCurrentPosition[1]);
                Log.d("garyhu", mCurrentPosition[0] + "@" + mCurrentPosition[1]+"宽为"+getWidth()+"高为"+getHeight());
                invalidate();
            }
        });

        ValueAnimator rightAnim = ValueAnimator.ofFloat(0, mRightPathMeasure.getLength());
        rightAnim.setDuration(350);
        rightAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mRightPathMeasure.getPosTan(value, mCurrentPosition, null);
                Log.d("garyhu", mCurrentPosition[0] + "@" + mCurrentPosition[1]);
                path.lineTo(mCurrentPosition[0],mCurrentPosition[1]);
                invalidate();
            }
        });

        final AnimatorSet set = new AnimatorSet();
        // 匀速插值器
        set.setInterpolator(new LinearInterpolator());
        set.play(circleAnim).before(leftAnim);
        set.play(rightAnim).after(leftAnim);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mLeftPathMeasure = null;
                mRightPathMeasure = null;
                if(listener!=null){
                    listener.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    //动画执行完成后的监听
    public interface ScaleAnimListener{
        public abstract void start();
    }
}
