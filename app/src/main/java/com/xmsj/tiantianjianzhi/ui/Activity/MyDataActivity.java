package com.xmsj.tiantianjianzhi.ui.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.bean.MyUser;

import cn.bmob.v3.BmobUser;

public class MyDataActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_to_edit;
    private ImageView iv_user_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        initView();
        showUserInfo();
    }

    //显示用户资料
    private void showUserInfo() {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        if (user != null) {
            //获取头像地址
            if (user.getHead() != null) {
                //圆形头像
                Glide.with(MyDataActivity.this).load(user.getHead().toString()).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv_user_head) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(MyDataActivity.this.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        iv_user_head.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }

        }
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("基本资料");
        tv_to_edit = (TextView) findViewById(R.id.tv_to_edit);
        iv_back.setOnClickListener(this);
        tv_to_edit.setOnClickListener(this);
        iv_user_head = (ImageView) findViewById(R.id.iv_user_head);
        iv_user_head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_edit:
                startActivity(new Intent(MyDataActivity.this,EditUserInfoActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
