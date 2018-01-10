package com.bawei.jingdong.model;

import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.UpdataBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UpDataModel {

    public void getData(String uid, String sellerid, String pid, String num, final IUpDataModel iUpDataModel){
        IGeation.api.getUpData(uid, sellerid, pid, "0", num, "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdataBean>() {
                    @Override
                    public void accept(UpdataBean bean) throws Exception {
                        iUpDataModel.success(bean);
                    }
                });
    }
}
