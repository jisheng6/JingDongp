package com.bawei.jingdong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong.MainActivity;
import com.bawei.jingdong.R;
import com.bawei.jingdong.adapter.MyAdapter;
import com.bawei.jingdong.adapter.SearchAdapter;
import com.bawei.jingdong.bao.OnItemClickListener;
import com.bawei.jingdong.bean.SearchBean;
import com.bawei.jingdong.present.SearchPresent;
import com.bawei.jingdong.view.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class SearchActivity extends Activity implements SearchView {
    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.sousuo_recyview)
    RecyclerView sousuoRecyview;
    private SearchAdapter searchAdapter;
    private SearchPresent present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        present = new SearchPresent(this);
        sousuoRecyview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchAdapter = new SearchAdapter(this);
       searchAdapter.setOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemClick(View view, int position) {

           }
       });

    }
    @OnClick({R.id.fanhui, R.id.search_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                //点击返回结束当前页面
                finish();
                break;
            case R.id.search_text:
                //点击搜索按钮
                present.getData(searchEdit.getText().toString());
                break;
        }
    }
    @Override
    public void success(SearchBean bean) {
        if(bean!=null) {
            // System.out.println(searchBean.getData().get(0).getBargainPrice());
            searchAdapter.addData(bean,bean.getData());
            sousuoRecyview.setAdapter(searchAdapter);
        }
    }

    @Override
    public void failure() {

    }

    @Override
    public void empty() {
        Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
        searchAdapter.setListClear();
    }

    @Override
    public void falseEdit() {
        searchAdapter.setListClear();
        Toast.makeText(this, "请输入手机或笔记本", Toast.LENGTH_SHORT).show();
    }


}
