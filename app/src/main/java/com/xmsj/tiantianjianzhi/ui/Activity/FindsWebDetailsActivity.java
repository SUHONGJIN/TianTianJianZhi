package com.xmsj.tiantianjianzhi.ui.Activity;

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
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

import java.util.ArrayList;

public class FindsWebDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private WebView mWebView;
    private LinearLayout ll_error_state;
    private ImageView iv_sharing;
    private Button btn_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_details);
        initView();
        loadWeb();

    }


    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("返回");
        ll_error_state = (LinearLayout) findViewById(R.id.ll_error_state);
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.mWebView);
        iv_sharing = (ImageView) findViewById(R.id.iv_sharing);
        iv_sharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //长按弹出列表提示框
                final ArrayList<String> list = new ArrayList<>();
                list.add("分享到朋友圈");
                list.add("分享到新浪微博");
                list.add("分享到腾讯QQ");
                final OptionCenterDialog optionCenterDialog = new OptionCenterDialog();
                optionCenterDialog.show(FindsWebDetailsActivity.this, list);
                optionCenterDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                ToastUtils.showImageToast(FindsWebDetailsActivity.this, "分享成功");
                                break;
                            case 1:
                                ToastUtils.showImageToast(FindsWebDetailsActivity.this, "分享成功");
                                break;
                            case 2:
                                ToastUtils.showImageToast(FindsWebDetailsActivity.this, "分享成功");
                                break;
                            default:
                                break;
                        }
                        optionCenterDialog.dismiss();
                    }
                });
            }
        });

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
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
