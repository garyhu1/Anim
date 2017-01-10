package com.garyhu.animdemo.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.garyhu.animdemo.R;
import com.garyhu.animdemo.base.BaseActivity;

import uk.co.senab.photoview.PhotoView;

/**
 * 作者： garyhu.
 * 时间： 2017/1/10.
 * volley加载
 */

public class VolleyActivity extends BaseActivity {

    private RelativeLayout imgContainer;
    private PhotoView img;
    private PhotoView photo;
    private String url = "http://photo.iyaxin.com/attachement/jpg/site2/20120402/001966720af110e381132c.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
    }

    @Override
    public void initView() {
        imgContainer = (RelativeLayout) findViewById(R.id.img_container);
        photo = (PhotoView) findViewById(R.id.photo_view);
        img = new PhotoView(this);
        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(url);
            }
        });
    }

    public void load(String url){
        RequestQueue mQueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                        imgContainer.addView(img);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(imageRequest);
    }
}
