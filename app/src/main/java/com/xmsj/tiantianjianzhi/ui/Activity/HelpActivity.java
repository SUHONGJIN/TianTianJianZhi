package com.xmsj.tiantianjianzhi.ui.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmsj.tiantianjianzhi.R;

public class HelpActivity extends AppCompatActivity {

    private ImageView iv_back;
    private TextView tv_title;
    private ExpandableListView my_expandable;
    String[] groupArray={"怎么使用“我”的页面？","兼职怎么报名？","兼职要收费不？"};
    String[][] childArray={{"a.注册登录。b.可在我的名片编辑和查看我的简历。c.可以查看我的报名情况。d.每天可以签到。e.遇到问题，投诉建议都可以进行反馈。"},{"兼职怎么报名？点开首页的列表，进入可以进行报名参加兼职。"},
            {"兼职信息都是免费的，如有收费，可以在平台进行投诉反馈，我们工作人员会第一时间进行核实处理。"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initView();
        MyBaseAdapter adapter=new MyBaseAdapter();
        my_expandable.setAdapter(adapter);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("帮助中心");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        my_expandable=(ExpandableListView)findViewById(R.id.my_expandable);

    }

    private class MyBaseAdapter extends BaseExpandableListAdapter{
        @Override
        public int getGroupCount() {
            return groupArray.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childArray[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupArray[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childArray[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition*100;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition*100+childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View views=View.inflate(HelpActivity.this,R.layout.item_group_layout,null);
            TextView textView=(TextView)views.findViewById(R.id.tv_group);
            Object data=getGroup(groupPosition);
            textView.setText((String)data);
            return views;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View childview=View.inflate(HelpActivity.this,R.layout.item_child_layout,null);
            TextView textView1=(TextView)childview.findViewById(R.id.tv_child);
            Object childdata=getChild(groupPosition,childPosition);
            textView1.setText((String)childdata);
            return childview;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
