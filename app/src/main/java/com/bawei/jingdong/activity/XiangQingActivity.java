package com.bawei.jingdong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong.R;
import com.bawei.jingdong.bao.GlideImageLoader;
import com.bawei.jingdong.bean.AddCartBean;
import com.bawei.jingdong.model.AddCartModelCallBack;
import com.bawei.jingdong.present.AddCartPresenter;
import com.bawei.jingdong.view.AddCartViewCallBack;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Adminjs on 2017/12/28.
 */

public class XiangQingActivity extends Activity implements AddCartModelCallBack, AddCartViewCallBack {
    @BindView(R.id.image)
    Banner banner;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.subhead)
    TextView subhead;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.jia)
    Button jia;
    @BindView(R.id.gou)
    Button gou;
    private AddCartPresenter addCartPresenter;
    private int pid;
    private String pid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_xiang);
        ButterKnife.bind(this);
        Bundle buddle = getIntent().getExtras();
        String image1 = buddle.getString("image");

        pid1 = buddle.getString("pid");

        String name1 = buddle.getString("name");
        String subhead1 = buddle.getString("subhead");
        String price1 = buddle.getString("price");

        final String[] split = image1.split("\\|");

        banner.setImageLoader(new GlideImageLoader());
        List<String> bannerList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            bannerList.add(split[i]);
        }
        System.out.println("banner中：："+bannerList.get(0));
        banner.setImages(bannerList);
        banner.start();

        name.setText(name1);
        subhead.setText(subhead1);
        price.setText("￥" + price1);
        addCartPresenter = new AddCartPresenter(this);

    }

    @OnClick({R.id.jia, R.id.gou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jia:
                addCartPresenter.getData(pid1);
                break;
            case R.id.gou:
                Toast.makeText(XiangQingActivity.this, "正在跳转....", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void success(AddCartBean addCartBean) {
        Toast.makeText(this,""+addCartBean.getMsg(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure() {
        Toast.makeText(this,""+"加入失败",Toast.LENGTH_LONG).show();

    }
}
