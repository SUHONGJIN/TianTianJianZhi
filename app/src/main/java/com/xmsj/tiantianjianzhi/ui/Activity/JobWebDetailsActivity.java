package com.xmsj.tiantianjianzhi.ui.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longsh.optionframelibrary.OptionCenterDialog;
import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.ui.Activity.Login.LoginAndRegisterActivity;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

public class JobWebDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private WebView mWebView;
    private LinearLayout ll_error_state;
    private ImageView iv_sharing;
    private LinearLayout ll_collect;
    private Button btn_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        initView();
        loadWeb();

    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_collect = (LinearLayout) findViewById(R.id.ll_collect);
        tv_title.setText("返回");
        ll_error_state = (LinearLayout) findViewById(R.id.ll_error_state);
        mWebView = (WebView) findViewById(R.id.mWebView);
        iv_sharing = (ImageView) findViewById(R.id.iv_sharing);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_sharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //长按弹出列表提示框
                final ArrayList<String> list = new ArrayList<>();
                list.add("分享兼职");
                list.add("报名兼职");
                list.add("取消操作");
                final OptionCenterDialog optionCenterDialog = new OptionCenterDialog();
                optionCenterDialog.show(JobWebDetailsActivity.this, list);
                optionCenterDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                ToastUtils.showImageToast(JobWebDetailsActivity.this, "分享成功");
                                break;
                            case 1:
                                ToastUtils.showImageToast(JobWebDetailsActivity.this, "报名成功");
                                break;
                            default:
                                break;
                        }
                        optionCenterDialog.dismiss();
                    }
                });
            }
        });
        ll_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = BmobUser.getCurrentUser();
                if (bmobUser != null) {
                    ToastUtils.showImageToast(JobWebDetailsActivity.this, "收藏成功！");
                } else {
                    ToastUtils.showTextToast(JobWebDetailsActivity.this, "请先登录!");
                    startActivity(new Intent(JobWebDetailsActivity.this, LoginAndRegisterActivity.class));
                }
            }
        });
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);
    }

    private void loadWeb() {
        String url = (String) getIntent().getExtras().get("url");
        if (url != null) {
            mWebView.loadUrl(url);
        }
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //加载开始
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //加载结束
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //加载页面的服务器出错时调用
                mWebView.setVisibility(View.GONE);
                ll_error_state.setVisibility(View.VISIBLE);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                initView();
                loadWeb();
                break;
        }
    }
}
