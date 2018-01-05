package com.bawei.jingdong.model;


import com.bawei.jingdong.bean.AddCartBean;

/**
 * Created by Menglucywhh on 2017/12/7.
 */

public interface AddCartModelCallBack {
    public void success(AddCartBean addCartBean);
    public void failure();

}
