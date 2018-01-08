package com.bawei.jingdong.model;

import okhttp3.Callback;

/**
 * Created by asus on 2017/11/13.
 */

//model接口
public interface ISearchModel {
    public void GetNetData(String keywords, String page, Callback callback);
}
