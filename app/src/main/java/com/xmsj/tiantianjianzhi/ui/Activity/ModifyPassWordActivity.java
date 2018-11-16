package com.xmsj.tiantianjianzhi.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.ui.Activity.Login.LoginAndRegisterActivity;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ModifyPassWordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private EditText et_old_pwd;
    private EditText et_new_pwd;
    private Button btn_modify_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pass_word);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("修改密码");
        et_old_pwd = (EditText) findViewById(R.id.et_old_pwd);
        et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
        btn_modify_pwd = (Button) findViewById(R.id.btn_modify_pwd);
        btn_modify_pwd.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_modify_pwd:
                String pwd_lod = et_old_pwd.getText().toString().trim();
                String pwd_new = et_new_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(pwd_lod)){
                    ToastUtils.showTextToast(ModifyPassWordActivity.this,"输入旧密码！");
                }else if (TextUtils.isEmpty(pwd_new)){
                    ToastUtils.showTextToast(ModifyPassWordActivity.this,"输入新密码！");
                }else if (pwd_lod.length()<6){
                    ToastUtils.showTextToast(ModifyPassWordActivity.this,"旧密码不能小于6位！");
                }else if (pwd_new.length()<6){
                    ToastUtils.showTextToast(ModifyPassWordActivity.this,"新密码不能小于6位！");
                }else{
                    BmobUser.updateCurrentUserPassword(et_old_pwd.getText().toString().trim(), et_new_pwd.getText().toString().trim(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                ToastUtils.showImageToast(ModifyPassWordActivity.this,"修改密码成功,请重新登录！");
                                Intent intent=new Intent(ModifyPassWordActivity.this, LoginAndRegisterActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });

                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
