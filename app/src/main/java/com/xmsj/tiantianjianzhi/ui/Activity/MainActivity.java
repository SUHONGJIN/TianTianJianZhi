package com.xmsj.tiantianjianzhi.ui.Activity;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xmsj.tiantianjianzhi.ui.Fragments.FindFragment;
import com.xmsj.tiantianjianzhi.ui.Fragments.HomeFragment;
import com.xmsj.tiantianjianzhi.ui.Fragments.MessageFragment;
import com.xmsj.tiantianjianzhi.ui.Fragments.UserFragment;
import com.xmsj.tiantianjianzhi.R;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioGroup rg_bottom_bar;
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private MessageFragment messageFragment;
    private UserFragment userFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //初始化控件
        rg_bottom_bar = (RadioGroup) findViewById(R.id.rg_bottom_bar);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);


        Drawable dr1=getResources().getDrawable(R.drawable.bottom_bar_home_selector);
        Drawable dr2=getResources().getDrawable(R.drawable.bottom_bar_find_selector);
        Drawable dr3=getResources().getDrawable(R.drawable.bottom_bar_message_selector);
        Drawable dr4=getResources().getDrawable(R.drawable.bottom_bar_my_selector);
        //改变图片的比例大小
        Rect r1 = new Rect(0, 0, dr1.getMinimumWidth() * 2 / 9, dr1.getMinimumHeight() * 2 / 9);
        Rect r2 = new Rect(0, 0, dr2.getMinimumWidth() * 2 / 9, dr2.getMinimumHeight() * 2 / 9);
        Rect r3 = new Rect(0, 0, dr3.getMinimumWidth() * 2 / 9, dr3.getMinimumHeight() * 2 / 9);
        Rect r4 = new Rect(0, 0, dr4.getMinimumWidth() * 2 / 9, dr4.getMinimumHeight() * 2 / 9);
        dr1.setBounds(r1);
        dr2.setBounds(r2);
        dr3.setBounds(r3);
        dr4.setBounds(r4);
        rb1.setCompoundDrawables(null,dr1,null,null);
        rb2.setCompoundDrawables(null,dr2,null,null);
        rb3.setCompoundDrawables(null,dr3,null,null);
        rb4.setCompoundDrawables(null,dr4,null,null);

        //注册RadioGroup的事件监听
        rg_bottom_bar.setOnCheckedChangeListener(this);
        rb1.setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        //创建碎片的事务
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //先隐藏掉所以的碎片
        hideAllFragmen(fragmentTransaction);
        switch (checkedId){
            case R.id.rb1:
                if (homeFragment==null){
                    homeFragment=new HomeFragment();
                    fragmentTransaction.add(R.id.fl_content,homeFragment);
                }else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case R.id.rb2:
                if (findFragment==null){
                    findFragment=new FindFragment();
                    fragmentTransaction.add(R.id.fl_content,findFragment);
                }else {
                    fragmentTransaction.show(findFragment);
                }
                break;
            case R.id.rb3:
                if (messageFragment==null){
                    messageFragment=new MessageFragment();
                    fragmentTransaction.add(R.id.fl_content,messageFragment);
                }else {
                    fragmentTransaction.show(messageFragment);
                }
                break;
            case R.id.rb4:
                if (userFragment ==null){
                    userFragment =new UserFragment();
                    fragmentTransaction.add(R.id.fl_content, userFragment);
                }else {
                    fragmentTransaction.show(userFragment);
                }
                break;
        }

        //提交事务
        fragmentTransaction.commit();

    }

    private void hideAllFragmen(FragmentTransaction fragmentTransaction) {
        if (homeFragment!=null){
            fragmentTransaction.hide(homeFragment);
        }
        if (findFragment!=null){
            fragmentTransaction.hide(findFragment);
        }
        if (messageFragment!=null){
            fragmentTransaction.hide(messageFragment);
        }
        if (userFragment !=null){
            fragmentTransaction.hide(userFragment);
        }
    }
}
