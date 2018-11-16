package com.xmsj.tiantianjianzhi.ui.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;
import com.xmsj.tiantianjianzhi.utils.VersionUtils;

public class AboutActivity extends AppCompatActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_version;
    private TextView tv_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("关于我们");
        tv_version = (TextView) findViewById(R.id.tv_version);
        //设置版本号
        String versions = "version " + VersionUtils.getVersionName(AboutActivity.this);
        tv_version.setText(versions);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_update = (TextView) findViewById(R.id.tv_update);
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showImageToast(AboutActivity.this,"已经是最新版本了哟~");
            }
        });
    }
}
