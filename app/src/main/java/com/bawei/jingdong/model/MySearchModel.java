package com.bawei.jingdong.model;

import com.bawei.jingdong.util.OkHttp3Utils;

import okhttp3.Callback;

/**
 * Model实现类
 */

public class MySearchModel implements ISearchModel {
    //传三个参数 一个是分页加载时用到的 page 还有  输入框内搜索的值，还有callback 用来在presenter内拿出bean
    @Override
    public void GetNetData(String keywords,String page,Callback callback) {
        OkHttp3Utils.doGet("http://120.27.23.105/product/searchProducts?keywords="+keywords+"&page="+page+"&source=android",callback);
    }
}
