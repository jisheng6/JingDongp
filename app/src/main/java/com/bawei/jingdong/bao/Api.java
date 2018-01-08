package com.bawei.jingdong.bao;

import com.bawei.jingdong.bean.AddCartBean;
import com.bawei.jingdong.bean.Bean;
import com.bawei.jingdong.bean.DeleteBean;
import com.bawei.jingdong.bean.FenLeiLeftBean;
import com.bawei.jingdong.bean.FenLeiRightBean;
import com.bawei.jingdong.bean.LoginBean;
import com.bawei.jingdong.bean.RegistBean;
import com.bawei.jingdong.bean.SelectCartBean;
import com.bawei.jingdong.bean.ShopBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Adminjs on 2017/12/26.
 */

public interface Api {
    @GET("/ad/getAd")
    Observable<Bean> getshop();

    @GET("/product/getCatagory")
    Observable<FenLeiLeftBean> getFenLei();


    @GET("/product/getProductCatagory")
    Observable<FenLeiRightBean> getFenLeiRight(@Query("cid") String cid);



    @GET("/product/getCarts")
    Observable<ShopBean> getCare(@Query("uid") String uid,@Query("source") String source);
    //搜索 笔记本 手机的接口
    //https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
//    @GET("/product/searchProducts")
//    Observable<NetDataBean> search(@QueryMap Map<String,String> map);

    @GET("/product/addCart")
    Call<AddCartBean> addCart(@QueryMap Map<String, String> map);
    @GET("product/getCarts")
    Call<SelectCartBean> selectCart(@QueryMap Map<String, String> map);
    ///http://120.27.23.105/user/login
    @GET("/user/login")
    Observable<LoginBean> getLogin(@Query("mobile") String mobile, @Query("password") String passwprd);
    @GET("/user/reg")
    Observable<RegistBean> getRegist(@Query("mobile") String mobile, @Query("password") String passwprd);
    //删除
    //https://www.zhaoapi.cn/product/deleteCart?uid=1650&pid=58
    @GET("/product/deleteCart")
    Call<DeleteBean> deleteCart(@QueryMap Map<String,String> map);
}
