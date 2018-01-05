package com.bawei.jingdong.model;

import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.FenLeiLeftBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Menglucywhh on 2017/12/4.
 */

public class MyFenleiListViewModel {

    public void getData(final FenleiListModelCallBack fenleiListModelCallBack) {

        IGeation.api.getFenLei()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FenLeiLeftBean>() {
                    @Override
                    public void accept(FenLeiLeftBean fenLeiLeftBean) throws Exception {
                        fenleiListModelCallBack.success(fenLeiLeftBean);
                    }
                });
    }
}
