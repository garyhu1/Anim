package com.garyhu.animdemo.activity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.garyhu.animdemo.R;

/**
 * 作者： garyhu.
 * 时间： 2017/3/6.
 * M = moveto(M X,Y): 将画笔移动到指定的坐标位置
 * L = lineto(L X,Y): 画直线到指定的坐标位置
 * Z = closepath(): 关闭路径
 */

public class VectorActivity extends AppCompatActivity {

    private ImageView vectorImg,pathImg,starImg;
    private Drawable d;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
        vectorImg = (ImageView) findViewById(R.id.vector_img);
        pathImg = (ImageView) findViewById(R.id.path);
        starImg = (ImageView) findViewById(R.id.star);
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(
                this, R.drawable.arrow_anim
        );
        vectorImg.setImageDrawable(animatedVectorDrawableCompat);
        d = vectorImg.getDrawable();
        if(d instanceof Animatable){
            anim_1();
        }
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = pathImg.getDrawable();
                if(drawable instanceof Animatable){
                    ((Animatable) drawable).start();
                }
            }
        });

        findViewById(R.id.anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = starImg.getDrawable();
                if(drawable instanceof Animatable){
                    ((Animatable) drawable).start();
                }
            }
        });
    }

    public void anim_1(){
        ((Animatable) d).start();
    }
}
