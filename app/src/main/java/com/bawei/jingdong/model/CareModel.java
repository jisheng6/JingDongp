package com.bawei.jingdong.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.widget.Toast;

import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.ShopBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.tencent.open.utils.Global.getSharedPreferences;

/**
 * Created by Adminjs on 2017/12/27.
 */

public class CareModel {

    public void getData(String uid,final CareModelCallBack callback){

            IGeation.api.getCare(uid+"","android")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ShopBean>() {
                        @Override
                        public void accept(ShopBean bean) throws Exception {
                            callback.success(bean);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
    }

    public interface CareModelCallBack {
        void success(ShopBean bean);
    }
}
