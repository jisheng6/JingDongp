package com.bawei.jingdong.model;

import com.bawei.jingdong.bao.IGeation;
import com.bawei.jingdong.bean.SearchBean;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adminjs on 2017/12/28.
 */

public class SearchModel {
    public void getData(String editSearch, final SearchModelCallBack searchModelCallBack) {
        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("keywords",editSearch);
        map.put("page","1");
        IGeation.api.getSearch(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBean>() {
                    @Override
                    public void accept(SearchBean bean) throws Exception {
                        searchModelCallBack.success(bean);
                    }
                });
    }

    public interface SearchModelCallBack {
        public void success(SearchBean bean);
    }
}
