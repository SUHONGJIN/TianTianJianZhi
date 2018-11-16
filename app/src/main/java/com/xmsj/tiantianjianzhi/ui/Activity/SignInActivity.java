package com.xmsj.tiantianjianzhi.ui.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

public class SignInActivity extends AppCompatActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private ImageView iv_signing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("签到");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_signing = (ImageView) findViewById(R.id.iv_signing);
        iv_signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showImageToast(SignInActivity.this,"签到成功 +1");
            }
        });
    }
}
