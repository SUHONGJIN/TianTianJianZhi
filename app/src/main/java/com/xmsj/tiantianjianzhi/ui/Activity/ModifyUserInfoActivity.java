package com.xmsj.tiantianjianzhi.ui.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.bean.MyUser;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ModifyUserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private EditText et_nick;
    private EditText et_motto;
    private Button btn_commit_info;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_info);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_back.setOnClickListener(this);
        et_nick = (EditText) findViewById(R.id.et_nick);
        et_motto = (EditText) findViewById(R.id.et_motto);
        btn_commit_info = (Button) findViewById(R.id.btn_commit_info);
        btn_commit_info.setOnClickListener(this);

        tag = getIntent().getExtras().getString("tag");
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        if (tag.equals("nick")) {
            tv_title.setText("修改昵称");
            et_nick.setVisibility(View.VISIBLE);
            //获取用户昵称
            if (user.getNick() != null) {
                et_nick.setText(user.getNick().toString());
                //显示光标位置在最后
                et_nick.setSelection(user.getNick().length());
            }

        } else if (tag.equals("motto")) {
            tv_title.setText("修改个性签名");
            et_motto.setVisibility(View.VISIBLE);
            //获取个性签名
            if (user.getMotto() != null) {
                et_motto.setText(user.getMotto().toString());
                //显示光标位置在最后
                et_motto.setSelection(user.getMotto().length());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_commit_info:
               //修改昵称
                if (tag.equals("nick")){
                    modifyNick();
                }
                //修改昵称
                if (tag.equals("motto")){
                    modifyMotto();
                }
                break;
        }

    }

    private void modifyMotto() {
        BmobUser myUser=BmobUser.getCurrentUser();
        myUser.setValue("motto",et_motto.getText().toString());
        myUser.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    ToastUtils.showImageToast(ModifyUserInfoActivity.this,"个性签名更新成功！");
                    setResult(200);
                    finish();
                }else {
                    ToastUtils.showTextToast(ModifyUserInfoActivity.this,"更新失败！");
                }
            }
        });
    }

    private void modifyNick() {
        BmobUser myUser=BmobUser.getCurrentUser();
        myUser.setValue("nick",et_nick.getText().toString());
        myUser.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    ToastUtils.showImageToast(ModifyUserInfoActivity.this,"昵称更新成功！");
                    setResult(200);
                    finish();
                }else {
                    ToastUtils.showTextToast(ModifyUserInfoActivity.this,"更新失败！");
                }
            }
        });
    }

}
