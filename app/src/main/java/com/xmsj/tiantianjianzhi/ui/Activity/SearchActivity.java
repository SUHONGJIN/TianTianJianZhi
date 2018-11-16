package com.xmsj.tiantianjianzhi.ui.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private EditText et_search;
    private Button btn_search;
    private TextView tv_search_content;
    private LinearLayout ll_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }
    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("搜索");
        et_search = (EditText) findViewById(R.id.et_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);
        tv_search_content = (TextView) findViewById(R.id.tv_search_content);
        ll_search = (LinearLayout) findViewById(R.id.ll_search);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                if (et_search.getText().toString().isEmpty()){
                    ToastUtils.showTextToast(SearchActivity.this,"搜索内容不能为空哟~");
                }else {
                    ll_search.setVisibility(View.VISIBLE);
                    tv_search_content.setText("找不到关于“"+et_search.getText().toString()+"”的内容");
                }

                break;
        }
    }
}
