package com.bawei.jingdong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong.MainActivity;
import com.bawei.jingdong.R;
import com.bawei.jingdong.adapter.MyAdapter;
import com.bawei.jingdong.adapter.MynAdapter;
import com.bawei.jingdong.bao.OnItemClickListener;
import com.bawei.jingdong.bean.NetDataBean;
import com.bawei.jingdong.present.MySearchPresenter;
import com.bawei.jingdong.view.ISearchView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Adminjs on 2018/1/8.
 */

public class SearchActivity extends Activity implements ISearchView, View.OnClickListener{
    //实现presenter和其他的一些变量
    MySearchPresenter presenter = new MySearchPresenter(this, this);
    private ImageView mChangeBt;
    private EditText mGoodsEt;
    private TextView mSearchBt;
    private XRecyclerView mXrv;
    private  int num=1;
    private  int  aa=1;
    Handler handler=new Handler();
    private MynAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //寻找控件
        initView();
    }
    private void initView() {
        mChangeBt = findViewById(R.id.bt_change);
        mChangeBt.setOnClickListener(this);
        mGoodsEt = (EditText) findViewById(R.id.et_goods);
        mSearchBt = findViewById(R.id.bt_search);
        mSearchBt.setOnClickListener(this);
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        ImageView image = findViewById(R.id.et_image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void ShowView(NetDataBean bean) {
        //view 层 的方法 用来更新ui
        Toast.makeText(this,bean.getMsg(),Toast.LENGTH_SHORT).show();

        if(aa % 2 ==1){
            LinearLayoutManager manager=new LinearLayoutManager(this);
            mXrv.setLayoutManager(manager);
            mChangeBt.setBackgroundResource(R.drawable.linear_icon);
        }

        adapter = new MynAdapter(SearchActivity.this,bean);
        mXrv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        //XRecyclerview的上拉下拉方法
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        presenter.getdata(mGoodsEt.getText().toString(),"1");
                        adapter.notifyDataSetChanged();
                        mXrv.refreshComplete();
                    }
                },900);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载
                        num++;
                        presenter.getdata(mGoodsEt.getText().toString(),num+"");
                        adapter.notifyDataSetChanged();
                        mXrv.loadMoreComplete();

                    }
                },900);
            }
        });

    }

    @Override
    public void onClick(View v) {
        //点击事件
        switch (v.getId()) {
            case R.id.bt_change:
                //根据aa变量是奇数还是偶数来判断加载那种布局返回那张图片
                aa++;
                if(aa % 2==0){
                    GridLayoutManager manager= new GridLayoutManager(this,2);
                    mXrv.setLayoutManager(manager);
                    mChangeBt.setBackgroundResource(R.drawable.linear_icon);

                }if(aa % 2 ==1){
                LinearLayoutManager manager=new LinearLayoutManager(this);
                mXrv.setLayoutManager(manager);
                mChangeBt.setBackgroundResource(R.drawable.grid_icon);
            }
                break;
            case R.id.bt_search:
                //点击搜索按钮时触发presenter的获取数据方法
                presenter.getdata(mGoodsEt.getText().toString(),"1");
                break;
            default:
                break;
        }
    }
    //实现presenter内部的防止内存溢出方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destory();
    }
}

