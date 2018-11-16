package com.xmsj.tiantianjianzhi.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.adapter.FindsAadapter;
import com.xmsj.tiantianjianzhi.adapter.MessageAdapter;
import com.xmsj.tiantianjianzhi.bean.Message;
import com.xmsj.tiantianjianzhi.ui.Activity.HelpActivity;
import com.xmsj.tiantianjianzhi.ui.Activity.MyIntegralActivity;
import com.xmsj.tiantianjianzhi.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by SuHongJin on 2018/7/13.
 */

public class MessageFragment extends Fragment {
    private RecyclerView recycler_message;
    private PullToRefreshLayout refresh_message;
    private List<Message> datalist = new ArrayList<>();
    private ImageView iv_refresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment, container, false);
        initView(view);
        loadMessageList();
        return view;
    }

    private void initView(View view) {
        recycler_message = (RecyclerView) view.findViewById(R.id.recycler_message);
        refresh_message = (PullToRefreshLayout) view.findViewById(R.id.refresh_message);
        //刷新控件
        refresh_message.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMessageList();
                        //结束刷新
                        refresh_message.finishRefresh();
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showTextToast(getContext(),"没有更多内容了哟~");
                        //结束加载更多
                        refresh_message.finishLoadMore();
                    }
                }, 2000);

            }
        });

        iv_refresh = (ImageView) view.findViewById(R.id.iv_refresh);
        iv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMessageList();
            }
        });
    }

    private void loadMessageList() {
        BmobQuery<Message> query = new BmobQuery<Message>();
        query.findObjects(new FindListener<Message>() {
            @Override
            public void done(List<Message> object, BmobException e) {
                if (e == null) {
                    //添加前清除集合数据先，防止数据添加重复
                    datalist.clear();
                    datalist.addAll(object);
                    recycler_message.setLayoutManager(new LinearLayoutManager(getContext()));
                    MessageAdapter adapter = new MessageAdapter(getContext(), datalist);
                    recycler_message.setAdapter(adapter);
                    iv_refresh.setVisibility(View.GONE);
                    adapter.setmItemClickListener(new FindsAadapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            switch (position){
                                case 0:
                                    //startActivity(new Intent(getContext(),HelpActivity.class));
                                    break;
                                case 1:
                                    startActivity(new Intent(getContext(), MyIntegralActivity.class));
                                    break;
                                case 2:
                                    startActivity(new Intent(getContext(), HelpActivity.class));
                                    break;
                            }
                        }
                    });
                } else {
                    iv_refresh.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
