package com.bawei.jingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong.R;
import com.bawei.jingdong.activity.XiangQingActivity;
import com.bawei.jingdong.bao.OnItemClickListener;
import com.bawei.jingdong.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Menglucywhh on 2017/11/27.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

    List<SearchBean.DataBean> listDa;
    Context context;
    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setListClear(){
        if(listDa!=null){

        listDa.clear();
        notifyDataSetChanged();
        }
    }
    public void addData(SearchBean bean,List<SearchBean.DataBean>list) {
        if(listDa==null){
            listDa = new ArrayList<>();
        }
        listDa.clear();
        if (bean.getData()== null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
        }else{
            listDa.addAll(list);
        }
        notifyDataSetChanged();
    }
    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_item,null);
        SearchViewHolder tuijianViewHolder = new SearchViewHolder(view);
        return tuijianViewHolder;
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, final int position) {
        if(listDa.size()>0) {
            if(listDa.get(position).getImages().contains("|")) {
                String[] split = listDa.get(position).getImages().split("\\|");
                holder.search_image.setImageURI(split[0]);
                holder.search_text.setText(listDa.get(position).getTitle());

            }else {
                holder.search_image.setImageURI(listDa.get(position).getImages());
                holder.search_text.setText(listDa.get(position).getTitle());

            }
        }
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                    Intent intent = new Intent(context, XiangQingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("pid",listDa.get(position).getPid()+"");
                    bundle.putString("image", listDa.get(position).getImages());
                    bundle.putString("name",listDa.get(position).getTitle());
                    bundle.putString("subhead",listDa.get(position).getSubhead());
                    bundle.putString("price",listDa.get(position).getPrice()+"");
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listDa==null?0:listDa.size();
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView search_image;
        private final TextView search_text;

        public SearchViewHolder(View itemView) {
            super(itemView);
            search_image =  itemView.findViewById(R.id.search_image);
            search_text =  itemView.findViewById(R.id.search_text);
        }
    }
}
