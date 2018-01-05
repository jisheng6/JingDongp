package com.bawei.jingdong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bawei.jingdong.R;
import com.bawei.jingdong.adapter.FenLeiRightRecyAdapter;
import com.bawei.jingdong.adapter.FenLeiRightRecyBigAdapter;
import com.bawei.jingdong.adapter.FenleiListAdapter;
import com.bawei.jingdong.bao.GlideImageLoader;
import com.bawei.jingdong.bean.FenLeiLeftBean;
import com.bawei.jingdong.bean.FenLeiRightBean;
import com.bawei.jingdong.present.FenleiRightPresenter;
import com.bawei.jingdong.present.MyFenleiListViewPresenter;
import com.bawei.jingdong.view.FenleiListViewCallBack;
import com.bawei.jingdong.view.FenleiRightViewCallBack;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class Fragment_fclass extends Fragment implements FenleiListViewCallBack, FenleiRightViewCallBack {

    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.fragment_banner)
    XBanner banner;
    @BindView(R.id.fenlei_right_recy)
    RecyclerView fenleiRightRecy;
    Unbinder unbinder;
    private MyFenleiListViewPresenter myFenleiListViewPresenter;
    private FenleiListAdapter fenleiListAdapter;
    private FenleiRightPresenter rightPresenter;
    String cid = "0";
    private FenLeiRightRecyAdapter fenLeiRightRecyAdapter;
    private FenLeiRightRecyBigAdapter fenLeiRightRecyBigAdapter;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fclass, container, false);
        unbinder = ButterKnife.bind(this, view);

        fenleiListAdapter = new FenleiListAdapter(getActivity());//左侧listview
        fenLeiRightRecyAdapter = new FenLeiRightRecyAdapter(getActivity());//右侧的小recyview
        fenLeiRightRecyBigAdapter = new FenLeiRightRecyBigAdapter(getActivity());//右侧的大recyview

        myFenleiListViewPresenter = new MyFenleiListViewPresenter(this);
        myFenleiListViewPresenter.getData();//左侧listview的请求数据

        list = new ArrayList<>();

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
        //右边的 presenter
        rightPresenter = new FenleiRightPresenter(this);
        //进入页面默认传cid为1 显示
        rightPresenter.getData(String.valueOf(cid));
        //右侧的大recy
        fenleiRightRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return view;
    }


    @Override
    public void success(final FenLeiLeftBean fenLeiLeftBean) {
        fenleiListAdapter.addData(fenLeiLeftBean.getData());
        listView.setAdapter(fenleiListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int cid = fenLeiLeftBean.getData().get(i).getCid();
                rightPresenter.getData(String.valueOf(cid));//右侧的请求
                // Toast.makeText(getActivity(),String.valueOf(cid),Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void failure() {

    }

    @Override
    public void success(FenLeiRightBean fenLeiRightBean) {
        fenLeiRightRecyBigAdapter.addData(fenLeiRightBean.getData());
        fenleiRightRecy.setAdapter(fenLeiRightRecyBigAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
