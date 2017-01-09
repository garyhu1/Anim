package com.garyhu.animdemo.base;

import android.support.v7.app.AppCompatActivity;

import com.garyhu.animdemo.R;
import com.garyhu.animdemo.utils.StatusBarUtil;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
    }
    /**
     * 初始化视图
     */
    public abstract void initView();


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.out_to_bottom);
    }
}
