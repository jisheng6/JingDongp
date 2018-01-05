package com.bawei.jingdong.model;

import com.bawei.jingdong.bao.IGeation;

import com.bawei.jingdong.bean.Bean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class GridModel {
    public void getData(final ModelCallBack callBack){
        IGeation.api.getshop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bean>() {
                    @Override
                    public void accept(Bean bean) throws Exception {
                       callBack.success(bean);
                    }
                });
    }
    public interface ModelCallBack {
        public void success(Bean bean);
    }
}
