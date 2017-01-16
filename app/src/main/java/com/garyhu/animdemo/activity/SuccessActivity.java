package com.garyhu.animdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.garyhu.animdemo.R;
import com.garyhu.animdemo.widget.SuccessView;

/**
 * 作者： garyhu.
 * 时间： 2017/1/13.
 */

public class SuccessActivity extends AppCompatActivity {

    private SuccessView successView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        successView = (SuccessView) findViewById(R.id.my_success);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                successView.startAnim();
            }
        });

        successView.setListener(new SuccessView.ScaleAnimListener() {
            @Override
            public void start() {
                scaleAnim();
            }
        });
    }

    public void scaleAnim(){
        ValueAnimator anim = ValueAnimator.ofFloat(1f, 0.95f, 0.9f, 0.85f, 0.8f, 0.85f, 0.9f, 0.95f, 1f,0.95f, 0.9f, 0.85f,0.9f, 0.95f, 1f,
                0.95f, 0.9f, 0.95f, 1f, 0.95f, 1f);
        anim.setDuration(500);
        anim.setInterpolator(new LinearInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                successView.setScaleX(currentValue);
                successView.setScaleY(currentValue);
            }
        });
        anim.start();
    }
}
