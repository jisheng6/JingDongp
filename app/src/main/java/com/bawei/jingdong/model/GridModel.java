package com.bawei.jingdong.model;

import android.util.Log;

import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.Bean;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class GridModel {
    public void getData(final ModelCallBack callBack) {
        IGeation.api.getshop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bean>() {
                    @Override
                    public void accept(Bean bean) throws Exception {
                        Log.i("---------", bean.toString());
                        callBack.success(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("---------", throwable.toString());

                    }
                });

    }
    public interface ModelCallBack {
        public void success(Bean bean);
    }
}
