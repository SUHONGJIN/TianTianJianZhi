package com.xmsj.tiantianjianzhi.ui.Activity.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.ui.Activity.MainActivity;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by SuHongJin on 2018/7/20.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText et_phone_login;
    private EditText et_pwd_login;
    private Button btn_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        et_phone_login = (EditText) view.findViewById(R.id.et_phone_login);
        et_pwd_login = (EditText) view.findViewById(R.id.et_pwd_login);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (et_phone_login.getText().toString().isEmpty()){
                    ToastUtils.showTextToast(getContext(),"账号不能为空哟~");
                }else if (et_phone_login.getText().toString().length()<6){
                    ToastUtils.showTextToast(getContext(),"账号不能小于6位~");
                }else if (et_pwd_login.getText().toString().isEmpty()){
                    ToastUtils.showTextToast(getContext(),"密码不能为空~");
                }else if (et_pwd_login.getText().toString().length()<6){
                    ToastUtils.showTextToast(getContext(),"密码不能小于6位哟~~");
                }else {
                    //登录
                    BmobUser bmobUser=new BmobUser();
                    bmobUser.setUsername(et_phone_login.getText().toString().trim());
                    bmobUser.setPassword(et_pwd_login.getText().toString().trim());
                    bmobUser.login(new SaveListener<Object>() {
                        @Override
                        public void done(Object o, BmobException e) {
                            if (e==null){
                                ToastUtils.showImageToast(getContext(),"登录成功");
                                Intent intent=new Intent(getContext(), MainActivity.class);
                                //清空栈底
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else {
                                ToastUtils.showTextToast(getContext(),"账号或密码错误！");
                            }
                        }
                    });
                }


                break;
        }
    }

}
