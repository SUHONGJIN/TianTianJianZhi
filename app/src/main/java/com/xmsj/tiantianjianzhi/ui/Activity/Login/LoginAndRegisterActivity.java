package com.xmsj.tiantianjianzhi.ui.Activity.Login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xmsj.tiantianjianzhi.R;

public class LoginAndRegisterActivity extends AppCompatActivity {

    private RadioButton rb_login;
    private RadioButton rb_register;
    private RadioGroup rg_login_register;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private FrameLayout frame_content;
    private FragmentManager fm;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();

    }


    private void initView() {
        rb_login = (RadioButton) findViewById(R.id.rb_login);
        rb_register = (RadioButton) findViewById(R.id.rb_register);
        rg_login_register = (RadioGroup) findViewById(R.id.rg_login_register);
        frame_content = (FrameLayout) findViewById(R.id.frame_content);
        loginFragment = new LoginFragment();
        //默认添加第一项
        fm = getSupportFragmentManager();
        rb_login.setChecked(true);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_content, loginFragment);
        ft.commit();

        rg_login_register.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //隐藏全部碎片
                hideAllFragment(ft);
                switch (checkedId) {
                    case R.id.rb_login:
                        if (loginFragment == null) {
                            loginFragment = new LoginFragment();
                            ft.add(R.id.frame_content, loginFragment);
                        } else {
                            ft.show(loginFragment);
                        }
                        break;
                    case R.id.rb_register:
                        if (registerFragment == null) {
                            registerFragment = new RegisterFragment();
                            ft.add(R.id.frame_content, registerFragment);
                        } else {
                            ft.show(registerFragment);
                        }
                        break;

                }
                ft.commit();
            }
        });

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (loginFragment != null) {
            ft.hide(loginFragment);
        }
        if (registerFragment != null) {
            ft.hide(registerFragment);
        }

    }
}
