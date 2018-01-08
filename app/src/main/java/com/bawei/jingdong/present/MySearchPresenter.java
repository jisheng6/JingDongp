package com.bawei.jingdong.present;

import android.content.Context;
import android.util.Log;
import com.bawei.jingdong.bean.NetDataBean;
import com.bawei.jingdong.model.MySearchModel;
import com.bawei.jingdong.util.OnUiCallback;
import com.bawei.jingdong.view.ISearchView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by asus on 2017/11/13.
 */

public class MySearchPresenter {
    Context context;
    ISearchView view;
    MySearchModel model;
    //将 mvp三层关联
    public MySearchPresenter(Context context, ISearchView view) {
        this.context = context;
        this.view = view;
        model=new MySearchModel();
    }
    public void getdata(String keyword,String page){
        model.GetNetData(keyword,page,new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
            //失败方法
            }

            @Override
            public void onSuccess(String result) {
                //获取数据成功时将放回的json 变成bean
                Log.i("3333333", "onSuccess: "+result.toString());
                Gson gson=new Gson();
                NetDataBean bean = gson.fromJson(result, NetDataBean.class);
                view.ShowView(bean);

            }
        });
    }
    //用来防止内存溢出
    public void destory(){
        this.view=null;
    }


}
