package com.bawei.jingdong.model;

import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.DeleteBean;
import com.bawei.jingdong.view.DeleteCartModelCallBack;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Menglucywhh on 2017/12/11.
 */

public class DeleteCartModel {

    public void delete(String pid,String uid, final DeleteCartModelCallBack deleteCartModelCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid",uid);
        map.put("pid",pid);

        IGeation.api.deleteCart(map).enqueue(new Callback<DeleteBean>() {
            @Override
            public void onResponse(Call<DeleteBean> call, Response<DeleteBean> response) {
                DeleteBean deleteBean = response.body();
                deleteCartModelCallBack.success(deleteBean);
            }

            @Override
            public void onFailure(Call<DeleteBean> call, Throwable t) {
                deleteCartModelCallBack.failure();
            }
        });
    }

}
