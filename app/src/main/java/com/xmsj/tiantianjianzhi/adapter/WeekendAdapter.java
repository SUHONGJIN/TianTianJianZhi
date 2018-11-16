package com.xmsj.tiantianjianzhi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.bean.WeekendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuHongJin on 2018/7/26.
 */

public class WeekendAdapter extends RecyclerView.Adapter<WeekendAdapter.MyViewHolder> {
    private List<WeekendBean> datalist=new ArrayList<>();
    private Context context;



    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public WeekendAdapter(Context context, List<WeekendBean> datalist) {
        this.context=context;
        this.datalist=datalist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_weekend,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_title_weekend.setText(datalist.get(position).getTitle_weekend());
        holder.tv_address_weekend.setText(datalist.get(position).getAddress_weekend());
        holder.tv_money_weekend.setText(datalist.get(position).getMoney_weekend()+"");
        holder.tv_company_weekend.setText(datalist.get(position).getCompany_weekend());
        holder.tv_time_weekend.setText(datalist.get(position).getCreatedAt());
        holder.tv_count_w.setText(datalist.get(position).getwCount()+"人看过");

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title_weekend;
        private TextView tv_money_weekend;
        private TextView tv_time_weekend;
        private TextView tv_address_weekend;
        private TextView tv_company_weekend;
        private TextView tv_count_w;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title_weekend=(TextView)itemView.findViewById(R.id.tv_title_weekend);
            tv_time_weekend=(TextView)itemView.findViewById(R.id.tv_time_weekend);
            tv_money_weekend=(TextView)itemView.findViewById(R.id.tv_money_weekend);
            tv_address_weekend=(TextView)itemView.findViewById(R.id.tv_address_weekend);
            tv_company_weekend=(TextView)itemView.findViewById(R.id.tv_company_weekend);
            tv_count_w=(TextView)itemView.findViewById(R.id.tv_count_w);
        }
    }


}
