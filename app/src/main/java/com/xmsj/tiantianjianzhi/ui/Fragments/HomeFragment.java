package com.xmsj.tiantianjianzhi.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.ui.Activity.SearchActivity;
import com.xmsj.tiantianjianzhi.ui.Fragments.home.Fragment1;
import com.xmsj.tiantianjianzhi.ui.Fragments.home.Fragment2;
import com.xmsj.tiantianjianzhi.ui.Fragments.home.Fragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuHongJin on 2018/7/13.
 */

public class HomeFragment extends Fragment {
    private Fragment1 fg1;
    private Fragment2 fg2;
    private Fragment3 fg3;
    private TabLayout my_tab;
    private ViewPager my_viewpager;
    private List<Fragment> fragments;
    private List<String> titles;
    private LinearLayout ll_search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        my_tab = (TabLayout) view.findViewById(R.id.my_tab);
        my_viewpager = (ViewPager) view.findViewById(R.id.my_viewpager);
        fg1 = new Fragment1();
        fg2 = new Fragment2();
        fg3 = new Fragment3();
        //添加fragment
        fragments = new ArrayList<>();
        fragments.add(fg1);
        fragments.add(fg2);
        fragments.add(fg3);
        //添加标题
        titles = new ArrayList<>();
        titles.add("日结专区");
        titles.add("周末兼职");
        titles.add("精选专区");
        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        my_viewpager.setAdapter(adapter);
        my_tab.setupWithViewPager(my_viewpager);
//        my_tab.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(my_tab, 20, 20);
//            }
//        });
        ll_search = (LinearLayout) view.findViewById(R.id.ll_search);
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
//
//    //修改TabLayout下划线的长度
//    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
//        Class<?> tabLayout = tabs.getClass();
//        Field tabStrip = null;
//        try {
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//
//        tabStrip.setAccessible(true);
//        LinearLayout llTab = null;
//        try {
//            llTab = (LinearLayout) tabStrip.get(tabs);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
//        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
//
//        for (int i = 0; i < llTab.getChildCount(); i++) {
//            View child = llTab.getChildAt(i);
//            child.setPadding(0, 0, 0, 0);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//            params.leftMargin = left;
//            params.rightMargin = right;
//            child.setLayoutParams(params);
//            child.invalidate();
//        }
//
//    }

}
