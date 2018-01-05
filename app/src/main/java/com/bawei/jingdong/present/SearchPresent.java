package com.bawei.jingdong.present;

import android.text.TextUtils;

import com.bawei.jingdong.bean.SearchBean;
import com.bawei.jingdong.model.SearchModel;
import com.bawei.jingdong.view.SearchView;

/**
 * Created by Adminjs on 2017/12/28.
 */

public class SearchPresent {
    private SearchModel model;
    private SearchView view;

    public SearchPresent(SearchView view) {
        this.view = view;
        this.model = new SearchModel();
    }
    public void getData(String editSearch){
        if(TextUtils.isEmpty(editSearch)||editSearch.length()==0){
            view.empty();
            return;
        }
        if(!editSearch.equals("笔记本")&&!editSearch.equals("手机")){
            view.falseEdit();
            return;
        }
        model.getData(editSearch, new SearchModel.SearchModelCallBack() {
            @Override
            public void success(SearchBean bean) {
                view.success(bean);
            }
        });
    }
}
