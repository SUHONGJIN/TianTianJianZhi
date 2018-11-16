package com.xmsj.tiantianjianzhi.ui.Activity.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by SuHongJin on 2018/7/20.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText et_phone;
    private EditText et_pwd;
    private Button btn_register;
    private EditText et_pwd2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        et_phone = (EditText) view.findViewById(R.id.et_phone);
        et_pwd = (EditText) view.findViewById(R.id.et_pwd);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        et_pwd2 = (EditText) view.findViewById(R.id.et_pwd2);
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:

                if (et_phone.getText().toString().isEmpty()) {
                    ToastUtils.showImageToast(getContext(), "账号不能为空哟~");
                }else if (et_phone.getText().toString().length()<6) {
                ToastUtils.showImageToast(getContext(), "账号不能小于6位哟~");
                }else if (et_pwd.getText().toString().isEmpty()) {
                    ToastUtils.showImageToast(getContext(), "密码不能为空哟~");
                }else if (et_pwd.getText().toString().length()<6){
                    ToastUtils.showImageToast(getContext(), "密码不能小于6位哟~");
                }else if (et_pwd2.getText().toString().isEmpty()) {
                    ToastUtils.showImageToast(getContext(), "确认一下密码~");
                }else if (!et_pwd2.getText().toString().equals(et_pwd.getText().toString())){
                    ToastUtils.showImageToast(getContext(), "两次密码不一致~");
                }else {
                    //注册
                    BmobUser bmobUser = new BmobUser();
                    bmobUser.setUsername(et_phone.getText().toString().trim());
                    bmobUser.setPassword(et_pwd.getText().toString().trim());
                    bmobUser.signUp(new SaveListener<Object>() {
                        @Override
                        public void done(Object o, BmobException e) {
                            if (e == null) {
                                ToastUtils.showImageToast(getContext(), "注册成功！！");
                            } else {
                                ToastUtils.showTextToast(getContext(), "用户可能已经存在！");
                            }
                        }
                    });


                }

                break;
        }
    }

}
