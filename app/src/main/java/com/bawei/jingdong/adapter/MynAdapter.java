package com.bawei.jingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bawei.jingdong.R;
import com.bawei.jingdong.activity.XiangQingActivity;
import com.bawei.jingdong.bao.OnItemClickListener;
import com.bawei.jingdong.bean.EventBean;
import com.bawei.jingdong.bean.NetDataBean;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

/**
 *适配器
 */

public class MynAdapter extends RecyclerView.Adapter<MynAdapter.myholder> {
    //两个参数  上下文，和数据源
    Context context;
    NetDataBean bean;
    public MynAdapter(Context context, NetDataBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局返回到holder

        View view=View.inflate(context, R.layout.lin_layout,null);
        myholder myholder=new myholder(view);

        return myholder;
    }

    @Override
    public void onBindViewHolder(final myholder holder, int position) {
        //通过数据源内的图片字符串 通过split截取成数组
         String[] imgdata=bean.getData().get(position).getImages().split("\\|");
         Glide.with(context)
                 .load(imgdata[0])
                 .into(holder.item_li_iv);
        //设置控件 属性
         holder.item_li_tv1.setText(bean.getData().get(position).getTitle());
         holder.item_li_tv2.setText(bean.getData().get(position).getPrice()+"￥");
        if(mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                    Intent intent = new Intent(context, XiangQingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("pid", bean.getData().get(position).getPid() + "");
                    bundle.putString("image", bean.getData().get(position).getImages());
                    bundle.putString("name", bean.getData().get(position).getTitle());
                    bundle.putString("subhead", bean.getData().get(position).getSubhead());
                    bundle.putString("price", bean.getData().get(position).getPrice() + "");
                    intent.putExtras(bundle);
                 //   EventBus.getDefault().postSticky(new EventBean(bean.getData().get(position).getPid()+"",bean.getData().get(position).getImages(),bean.getData().get(position).getTitle(),bean.getData().get(position).getSubhead(),bean.getData().get(position).getPrice()+""));
                    context.startActivity(intent);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return bean.getData()==null?0:bean.getData().size();
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    class myholder extends RecyclerView.ViewHolder{
        ImageView item_li_iv;
        TextView item_li_tv1;
        TextView item_li_tv2;
        public myholder(View itemView) {
            super(itemView);
            item_li_iv=itemView.findViewById(R.id.item_li_iv);
            item_li_tv1=itemView.findViewById(R.id.item_li_tv1);
            item_li_tv2=itemView.findViewById(R.id.item_li_tv2);

        }
    }

}
