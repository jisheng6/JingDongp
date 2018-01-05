package com.bawei.jingdong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bawei.jingdong.R;

import java.util.List;


public class MyGridAdapter extends BaseAdapter{
    private List<String> list;
    private List<Integer> imgList;
    private Context context;

    public MyGridAdapter(List<String> list, List<Integer> imgList, Context context) {
        this.list = list;
        this.imgList = imgList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.fragment_grid, null);
            holder.iv_grid = (ImageView) convertView.findViewById(R.id.iv_grid);
            holder.tv_grid = (TextView) convertView.findViewById(R.id.tv_grid);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_grid.setText(list.get(position));
        holder.iv_grid.setImageResource(imgList.get(position));
        return convertView;
    }
    class ViewHolder{
        ImageView iv_grid;
        TextView tv_grid;
    }

}
