package com.xmsj.tiantianjianzhi.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xmsj.tiantianjianzhi.R;

/**
 * Created by SuHongJin on 2018/7/14.
 */

public class ToastUtils {
    //显示文本的Toast
    public static void showTextToast(Context context,String message){
        View toastview= LayoutInflater.from(context).inflate(R.layout.toast_text_layout,null);
        TextView text=(TextView)toastview.findViewById(R.id.tv_toast);
        text.setText(message);
        Toast toast=new Toast(context);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastview);
        toast.show();
    }
    //显示文本+图片的Toast
    public static void showImageToast(Context context,String message){
        View toastview= LayoutInflater.from(context).inflate(R.layout.toast_image_layout,null);
        TextView text=(TextView)toastview.findViewById(R.id.tv_toast);
        text.setText(message);
        Toast toast=new Toast(context);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastview);
        toast.show();
    }
}
