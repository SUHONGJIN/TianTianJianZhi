package com.xmsj.tiantianjianzhi.ui.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.bean.MyUser;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;
import com.longsh.optionframelibrary.OptionBottomDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_title;
    private ImageView iv_user_head;
    private RelativeLayout rl_modify_user_head;
    private RelativeLayout rl_id;
    private RelativeLayout rl_modify_user_nick;
    private RelativeLayout rl_modify_user_motto;
    private RelativeLayout rl_to_mydata;
    private TextView tv_username;
    private TextView tv_nick;
    private TextView tv_motto;

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_ALBUM = 2;
    public static final int REQUEST_CROP = 3;
    public static final int REQUEST_INFO = 4;
    public static final int CAMERA_PERMISSION_CODE = 5;
    public static final int ALBUM_PERMISSION_CODE = 6;
    public static final String IMAGE_UNSPECIFIED = "image/*";
    private File mImageFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
    }

    //初始化
    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_nick = (TextView) findViewById(R.id.tv_nick);
        tv_motto = (TextView) findViewById(R.id.tv_motto);
        tv_title.setText("我的资料");
        iv_user_head = (ImageView) findViewById(R.id.iv_user_head);
        iv_back.setOnClickListener(this);
        rl_modify_user_head = (RelativeLayout) findViewById(R.id.rl_modify_user_head);
        rl_modify_user_head.setOnClickListener(this);
        rl_modify_user_nick = (RelativeLayout) findViewById(R.id.rl_modify_user_nick);
        rl_modify_user_nick.setOnClickListener(this);
        rl_modify_user_motto = (RelativeLayout) findViewById(R.id.rl_modify_user_motto);
        rl_modify_user_motto.setOnClickListener(this);
        rl_to_mydata = (RelativeLayout) findViewById(R.id.rl_to_mydata);
        rl_to_mydata.setOnClickListener(this);
        rl_id = (RelativeLayout) findViewById(R.id.rl_id);
        rl_id.setOnClickListener(this);
        //显示用户信息
        showUserInfo();
    }

    //显示用户资料
    private void showUserInfo() {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        if (user != null) {
            //获取头像地址
            if (user.getHead() != null) {
                //圆形头像
                Glide.with(MyInfoActivity.this).load(user.getHead().toString()).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv_user_head) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(MyInfoActivity.this.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        iv_user_head.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
            //获取用ID
            if (user.getUsername() != null) {
                tv_username.setText(user.getUsername().toString());
            }
            //获取用昵称
            if (user.getNick() != null) {
                tv_nick.setText(user.getNick().toString());
            }
            //获取用个性签名
            if (user.getMotto() != null) {
                tv_motto.setText(user.getMotto().toString());
            }
        }
    }

    //选择列表
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_modify_user_head:
                List<String> stringList = new ArrayList<String>();
                stringList.add("拍照");
                stringList.add("从相册选择");
                final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(MyInfoActivity.this, stringList);
                optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            //Android6.0以上要获取动态权限
                            //先判断该页面是否已经授予拍照权限
                            if (ContextCompat.checkSelfPermission(MyInfoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                //获取拍照权限
                                ActivityCompat.requestPermissions(MyInfoActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                            } else {
                                //拍照
                                selectCamera();
                            }
                        }
                        if (position == 1) {
                            //调用相册
                            selectAlbum();
                        }
                        optionBottomDialog.dismiss();
                    }
                });

                break;
            case R.id.rl_id:
                ToastUtils.showTextToast(MyInfoActivity.this,"用户ID不支持修改哟(＾Ｕ＾)");
                break;
            case R.id.rl_modify_user_nick:
                Intent intent = new Intent(MyInfoActivity.this, ModifyUserInfoActivity.class);
                intent.putExtra("tag", "nick");
                startActivityForResult(intent, REQUEST_INFO);
                break;
            case R.id.rl_modify_user_motto:
                Intent intent1 = new Intent(MyInfoActivity.this, ModifyUserInfoActivity.class);
                intent1.putExtra("tag", "motto");
                startActivityForResult(intent1, REQUEST_INFO);
                break;
            case R.id.rl_to_mydata:
                startActivity(new Intent(MyInfoActivity.this, MyDataActivity.class));
                break;

        }
    }

    //选择相机
    private void selectCamera() {
        mImageFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  //如果是7.0以上，使用FileProvider，否则会报错
            fileUri = FileProvider.getUriForFile(MyInfoActivity.this, "com.xmsj.tiantianjianzhi.fileprovider", mImageFile);
        } else {
            fileUri = Uri.fromFile(mImageFile);
        }
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    //选择相册
    private void selectAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    //回调处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_INFO:
                if (resultCode == 200) {
                    //刷新数据
                    showUserInfo();
                }
                break;
            case REQUEST_CAMERA:

                break;

            case REQUEST_CROP:

                break;
            case REQUEST_ALBUM:

                break;
        }
    }

    //裁剪照片
    private void cropImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageFile);
        startActivityForResult(intent, REQUEST_CROP);
    }


    //处理权限请求响应
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                selectCamera();
                //ToastUtils.showImageToast(MyInfoActivity.this, "权限申请OK！");
                break;
        }

    }
}
