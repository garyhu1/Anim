package com.garyhu.animdemo.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.garyhu.animdemo.R;
import com.garyhu.animdemo.base.BaseActivity;
import com.garyhu.animdemo.payanim.AliPayAnimActivity;
import com.garyhu.animdemo.propertyanim.ValueAnimationActivity;
import com.garyhu.animdemo.shopcar.ShopCarAddAnimActivity;

public class MainActivity extends BaseActivity {

    private ImageView iv;
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = ((ImageView) findViewById(R.id.ic));

        //查看购物车
        findViewById(R.id.shop_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopCarAddAnimActivity.class));
            }
        });

        //加载图片
        findViewById(R.id.volley).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,VolleyActivity.class));
            }
        });

        //支付成功和失败的动画
        findViewById(R.id.ali_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AliPayAnimActivity.class));
            }
        });

        //属性动画
        findViewById(R.id.point_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ValueAnimationActivity.class));
            }
        });

        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        mPathMeasure = new PathMeasure(path, false);

        //属性动画实现
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(5000);
        // 匀速插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                iv.setTranslationX(mCurrentPosition[0]);
                iv.setTranslationY(mCurrentPosition[1]);
                Log.d("garyhu", mCurrentPosition[0] + "@" + mCurrentPosition[1]);

            }
        });
        valueAnimator.start();
    }

    @Override
    public void initView() {

    }
}
