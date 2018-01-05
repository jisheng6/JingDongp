package com.bawei.jingdong.present;


import com.bawei.jingdong.bean.FenLeiLeftBean;
import com.bawei.jingdong.model.FenleiListModelCallBack;
import com.bawei.jingdong.model.MyFenleiListViewModel;
import com.bawei.jingdong.view.FenleiListViewCallBack;

/**
 * Created by Menglucywhh on 2017/12/4.
 */

public class MyFenleiListViewPresenter {

    MyFenleiListViewModel myFenleiListViewModel = new MyFenleiListViewModel();
    FenleiListViewCallBack fenleiListViewCallBack;
    public MyFenleiListViewPresenter(FenleiListViewCallBack fenleiListViewCallBack) {
        this.fenleiListViewCallBack = fenleiListViewCallBack;
    }

    public void getData(){
        myFenleiListViewModel.getData(new FenleiListModelCallBack() {
            @Override
            public void success(FenLeiLeftBean fenLeiLeftBean) {
                fenleiListViewCallBack.success(fenLeiLeftBean);
            }

            @Override
            public void failure() {
                fenleiListViewCallBack.failure();
            }
        });
    }
}
