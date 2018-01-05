package com.bawei.jingdong.model;

import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.ShopBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adminjs on 2017/12/27.
 */

public class CareModel {
    public void getData(final CareModelCallBack callback){
        IGeation.api.getCare(3859+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopBean>() {
                    @Override
                    public void accept(ShopBean bean) throws Exception {
                        callback.success(bean);
                    }
                });
    }

    public interface CareModelCallBack {
        void success(ShopBean bean);
    }
}
