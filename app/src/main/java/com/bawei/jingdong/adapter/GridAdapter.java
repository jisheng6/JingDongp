package com.bawei.jingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.jingdong.R;
import com.bawei.jingdong.activity.XiangQingActivity;
import com.bawei.jingdong.bao.OnItemClickListener;
import com.bawei.jingdong.bean.Bean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.IViewholder>{
    private Context context;
    private List<Bean.MiaoshaBean.ListBeanX>list;

    public GridAdapter(Context context) {
        this.context = context;
    }
    public void addData(Bean bean){
        if(list == null){
            list = new ArrayList<>();
        }
        list.addAll(bean.getMiaosha().getList());
        notifyDataSetChanged();
    }
    @Override
    public IViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.grid_layout, null);
        return new IViewholder(view);
    }

    @Override
    public void onBindViewHolder(final IViewholder holder, int position) {
        String[] url = list.get(position).getImages().split("\\|");
        holder.image.setImageURI(url[0],list.get(position).getImages());
     holder.title.setText(list.get(position).getTitle());
        if(mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                    Intent intent = new Intent(context, XiangQingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("pid", list.get(position).getPid() + "");
                    bundle.putString("image", list.get(position).getImages());
                    bundle.putString("name", list.get(position).getTitle());
                    bundle.putString("subhead", list.get(position).getSubhead());
                    bundle.putString("price", list.get(position).getPrice() + "");
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    class IViewholder extends RecyclerView.ViewHolder {

        SimpleDraweeView image;
        TextView title;
        public IViewholder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }
}
