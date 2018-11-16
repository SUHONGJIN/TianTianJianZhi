package com.xmsj.tiantianjianzhi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmsj.tiantianjianzhi.R;
import com.xmsj.tiantianjianzhi.bean.Finds;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuHongJin on 2018/7/24.
 */

public class FindsAadapter extends RecyclerView.Adapter<FindsAadapter.MyViewHolder> implements View.OnClickListener {
    private Context content;
    private List<Finds> datalist=new ArrayList<>();
    /**
     * 点击事件
     * @param context
     * @param datalist
     */
    private OnItemClickListener mItemClickListener=null;

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public FindsAadapter(Context context, List<Finds> datalist) {
        this.content=context;
        this.datalist=datalist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_find,parent,false);
        //为每个item添加监听事件
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(datalist.get(position).getTitle());
        holder.tv_content.setText(datalist.get(position).getContent());
        holder.tv_time.setText(datalist.get(position).getCreatedAt());
        holder.tv_count_finds.setText(datalist.get(position).getCount()+"人看过");
        Glide.with(content).load(datalist.get(position).getImage()).error(R.drawable.load_bg).into(holder.iv_image);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_time;
        private TextView tv_content;
        private TextView tv_count_finds;
        private RoundedImageView iv_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_content=(TextView)itemView.findViewById(R.id.tv_content_find);
            tv_title=(TextView)itemView.findViewById(R.id.tv_title_find);
            tv_time=(TextView)itemView.findViewById(R.id.tv_time_find);
            tv_count_finds=(TextView)itemView.findViewById(R.id.tv_count_finds);
            iv_image=(RoundedImageView) itemView.findViewById(R.id.iv_image_find);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
