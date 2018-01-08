package com.bawei.jingdong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bawei.jingdong.R;
import com.bawei.jingdong.activity.SearchActivity;
import com.bawei.jingdong.adapter.GriAdapter;
import com.bawei.jingdong.adapter.GridAdapter;
import com.bawei.jingdong.adapter.MyGridAdapter;
import com.bawei.jingdong.bao.OnItemClickListener;
import com.bawei.jingdong.bean.Bean;
import com.bawei.jingdong.present.GridPresent;
import com.bawei.jingdong.view.GridView;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class Fragment_shouye extends Fragment implements GridView {
    @BindView(R.id.ew)
    CheckBox ew;
    @BindView(R.id.et)
    TextView et;
    @BindView(R.id.banner)
    XBanner banner;
    Unbinder unbinder;
    @BindView(R.id.gv_fragment)
    android.widget.GridView gvFragment;
    private List<String> listT = new ArrayList<>();
    private List<Integer> imgT = new ArrayList<>();
    //  private String path = "http://120.27.23.105/ad/getAd";
    private ArrayList<String> list;
    private RecyclerView recyclerView;
    private GridPresent present;
    private GridAdapter adapter;
    private MyGridAdapter myGridAdapter;
    private RecyclerView re;
    private GriAdapter griAdapter;
    private TextView tvHour;
    private TextView tvMinute;
    private TextView tvSecond;
    static long mHour = 02;
    static long mMin =15;
    boolean isRun = true;
    static long mSecond = 36;
    Handler timeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                //调用 倒计时计算的方法
                computeTime();
                if(mHour<10){
                    tvHour.setText("0"+mHour+"");
                }else{
                    tvHour.setText(mHour+"");
                }
                if(mMin<10){
                    tvMinute.setText("0"+mMin+"");
                }else{
                    tvMinute.setText(mMin+"");
                }if(mSecond<10){
                    tvSecond.setText("0"+mSecond+"");
                }else{
                    tvSecond.setText(mSecond+"");
                }
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);

        unbinder = ButterKnife.bind(this, view);

        list = new ArrayList<>();
        list.add("https://www.zhaoapi.cn/images/quarter/ad1.png");
        list.add("https://www.zhaoapi.cn/images/quarter/ad2.png");
        list.add("https://www.zhaoapi.cn/images/quarter/ad3.png");
        list.add("https://www.zhaoapi.cn/images/quarter/ad4.png");
        banner.setData(list,null);
        // XBanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(1000);
        banner.setPageTransformer(Transformer.Cube);    //立体旋转

        ViewFlipper vf = view.findViewById(R.id.view_filpper);
        vf.addView(View.inflate(getActivity(),R.layout.item_paomadeng,null));


        recyclerView = view.findViewById(R.id.recyclerview);
        present = new GridPresent(this);
        present.get();
        adapter = new GridAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

        re = view.findViewById(R.id.recycle);
        griAdapter = new GriAdapter(getActivity());
        re.setLayoutManager(new GridLayoutManager(getActivity(),2));
        re.setAdapter(griAdapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        griAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        tvHour = view.findViewById(R.id.tv_hour);
        tvMinute = view.findViewById(R.id.tv_minute);
        tvSecond = view.findViewById(R.id.tv_second);
        startRun();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listT.add("京东超市");
        listT.add("全球购");
        listT.add("手机数码");
        listT.add("男装");
        listT.add("女装");
        listT.add("男鞋");
        listT.add("女鞋");
        listT.add("内衣配饰");
        listT.add("箱包手袋");
        listT.add("美妆个护");
        imgT.add(R.mipmap.jewe1l);
        imgT.add(R.mipmap.qqg);
        imgT.add(R.mipmap.phone);
        imgT.add(R.mipmap.man);
        imgT.add(R.mipmap.girl);
        imgT.add(R.mipmap.manshoe);
        imgT.add(R.mipmap.girlshoe);
        imgT.add(R.mipmap.shirt);
        imgT.add(R.mipmap.bag);
        imgT.add(R.mipmap.beauty);
        myGridAdapter = new MyGridAdapter(listT, imgT, getActivity());
        gvFragment.setAdapter(myGridAdapter);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ew, R.id.et})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ew:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent);
                break;
            case R.id.et:
                Intent intent1 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        present.detach();
    }

    @Override
    public void success(Bean bean) {
        adapter.addData(bean);
        griAdapter.addDa(bean);
    }

    @Override
    public void failure() {

    }

    @Override
    public void setAdapter(MyGridAdapter myGridAdapter) {

    }
    /**
     * 开启倒计时
     * */
    private void startRun(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRun){
                    try {
                        //睡眠一秒发送消息handler
                        Thread.sleep(1000);
                        Message message = Message.obtain();
                        message.what=1;
                        //发送消息
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
//调用 倒计时计算的方法
    /**
     * 倒计时计算
     * */
    private static void computeTime(){
        //首先把秒减1
        mSecond--;
        if(mSecond<0){//如果秒已经减到了0
            mMin--;//分钟就减1
            mSecond=59;//秒变成 59
            if(mMin<0){//如果分钟小于0
                mMin=59;//分钟变成59
                mHour--;//小时减1
            }
        }
    }
}
