package com.xmsj.tiantianjianzhi.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.adapter.FindsAadapter;
import com.xmsj.tiantianjianzhi.bean.BannerBean;
import com.xmsj.tiantianjianzhi.bean.Finds;
import com.xmsj.tiantianjianzhi.ui.Activity.FindsWebDetailsActivity;
import com.xmsj.tiantianjianzhi.utils.DividerItemDecoration;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by SuHongJin on 2018/7/13.
 */

public class FindFragment extends Fragment implements OnBannerListener, View.OnClickListener {
    private Banner mBanner;
    private ArrayList<String> titles_list;
    private ArrayList<String> images_list;
    private RecyclerView mRecyclerview;
    private List<Finds> datalist;
    private LinearLayout ll_load;
    private Button btn_load;
    private RelativeLayout rl_network_error;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment, container, false);
        initView(view);
        getBannerData();
        loadList();
        return view;
    }
    //获取轮播图后台数据
    private void getBannerData() {
        //放图片地址和标题的集合
        titles_list=new ArrayList<>();
        images_list=new ArrayList<>();
        BmobQuery<BannerBean> query=new BmobQuery<BannerBean>();
        query.findObjects(new FindListener<BannerBean>() {
            @Override
            public void done(List<BannerBean> list, BmobException e) {
                if (e==null){
                    for (BannerBean bannerbean:list){
                        //添加数据到集合
                        titles_list.add(bannerbean.getBtitle());
                        images_list.add(bannerbean.getBimage());
                        setBanner(titles_list,images_list);
                    }
                }else{
                    Log.e("banner","轮播图数据获取失败----"+e);
                }
            }
        });
    }
    //设置轮播图
    private void setBanner(ArrayList<String> titles_list, ArrayList<String> images_list) {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        mBanner.setImageLoader(new MyLoader());
        //设置图片地址集合
        mBanner.setImages(this.images_list);
        //设置标题的集合
        mBanner.setBannerTitles(this.titles_list);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置轮播动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置轮播
        mBanner.isAutoPlay(true);
        //设置指示器位置
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置监听
        mBanner.setOnBannerListener(this);
        //启动轮播图
        mBanner.start();
    }

    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.mBanner);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.mRecyclerview);
        ll_load = (LinearLayout) view.findViewById(R.id.ll_load);
        btn_load = (Button) view.findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);
        rl_network_error = (RelativeLayout) view.findViewById(R.id.rl_network_error);
    }
    //轮播图点击事件
    @Override
    public void OnBannerClick(int position) {
       // Toast.makeText(getContext(), position + "项", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                loadList();
                btn_load.setText("加载中...");
                break;
        }
    }

    //加载图片
    private class MyLoader implements ImageLoaderInterface {
        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load(path).into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }


    //加载列表数据
    private void loadList() {
        datalist = new ArrayList<>();
        //获取后台数据
        BmobQuery<Finds> query = new BmobQuery<Finds>();
        query.findObjects(new FindListener<Finds>() {
            @Override
            public void done(final List<Finds> list, BmobException e) {
                if (e == null) {
                    //添加数据到集合
                    datalist.addAll(list);
                    //禁止滑动
                    LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    mRecyclerview.setLayoutManager(llm);
                    //添加分割线
                    mRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
                    //适配器
                    FindsAadapter adapter = new FindsAadapter(getContext(), datalist);
                    mRecyclerview.setAdapter(adapter);
                    //隐藏加载view
                    ll_load.setVisibility(View.GONE);
                    rl_network_error.setVisibility(View.GONE);
                    //点击事件
                    adapter.setmItemClickListener(new FindsAadapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(getContext(), FindsWebDetailsActivity.class);
                            intent.putExtra("url", list.get(position).getUrl());
                            startActivityForResult(intent, 100);
                        }
                    });

                } else {
                    rl_network_error.setVisibility(View.VISIBLE);
                    btn_load.setText("重新加载");
                    ll_load.setVisibility(View.GONE);
                    ToastUtils.showTextToast(getContext(), "获取失败啦~请检查网络！");
                }
            }
        });
    }
}
