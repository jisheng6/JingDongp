package com.bawei.jingdong.model;

import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.FenLeiRightBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Menglucywhh on 2017/12/6.
 */

public class FenleiRightModel {
    //右边分类网址
    //https://www.zhaoapi.cn/product/getProductCatagory?cid=1,2,3,5,6
    public void getData(final FenleiRightModelCallBack callBack, String cid) {

        if (!cid.equals("1") && !cid.equals("2") && !cid.equals("3") && !cid.equals("5") && !cid.equals("6")) {
            cid = "3";
        }
        IGeation.api.getFenLeiRight(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FenLeiRightBean>() {
                    @Override
                    public void accept(FenLeiRightBean fenLeiRightBean) throws Exception {
                        callBack.success(fenLeiRightBean);
                    }
                });
    }

    }

