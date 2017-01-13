package com.garyhu.animdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

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
        ObjectAnimator a1 = ObjectAnimator.ofFloat(successView, "scaleX", 1f, 0.95f, 0.9f, 0.85f, 0.8f);
        ObjectAnimator b1 = ObjectAnimator.ofFloat(successView, "scaleY", 1f, 0.95f, 0.9f, 0.85f, 0.8f);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(successView, "scaleX", 0.8f, 0.85f, 0.9f, 0.95f, 1f);
        ObjectAnimator b2 = ObjectAnimator.ofFloat(successView, "scaleY", 0.8f, 0.85f, 0.9f, 0.95f, 1f);

        ObjectAnimator a3 = ObjectAnimator.ofFloat(successView, "scaleX", 1f, 0.95f, 0.9f, 0.85f);
        ObjectAnimator b3 = ObjectAnimator.ofFloat(successView, "scaleY", 1f, 0.95f, 0.9f, 0.85f);
        ObjectAnimator a4 = ObjectAnimator.ofFloat(successView, "scaleX",  0.85f, 0.9f, 0.95f, 1f);
        ObjectAnimator b4 = ObjectAnimator.ofFloat(successView, "scaleY", 0.85f, 0.9f, 0.95f, 1f);

        ObjectAnimator a5 = ObjectAnimator.ofFloat(successView, "scaleX", 1f, 0.95f, 0.9f);
        ObjectAnimator b5= ObjectAnimator.ofFloat(successView, "scaleY", 1f, 0.95f, 0.9f);
        ObjectAnimator a6 = ObjectAnimator.ofFloat(successView, "scaleX",  0.9f, 0.95f, 1f);
        ObjectAnimator b6 = ObjectAnimator.ofFloat(successView, "scaleY", 0.9f, 0.95f, 1f);

        ObjectAnimator a7 = ObjectAnimator.ofFloat(successView, "scaleX", 1f, 0.95f);
        ObjectAnimator b7 = ObjectAnimator.ofFloat(successView, "scaleY", 1f, 0.95f);
        ObjectAnimator a8 = ObjectAnimator.ofFloat(successView, "scaleX",  0.95f, 1f);
        ObjectAnimator b8 = ObjectAnimator.ofFloat(successView, "scaleY", 0.95f, 1f);

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.setDuration(300);
        set.play(a1).with(b1);
        set.play(a2).with(b2).after(a1);
        set.play(a3).with(b3).after(a2);
        set.play(a4).with(b4).after(a3);
        set.play(a5).with(b5).after(a4);
        set.play(a6).with(b6).after(a5);
        set.play(a7).with(b7).after(a6);
        set.play(a8).with(b8).after(a7);
        set.start();
    }
}
