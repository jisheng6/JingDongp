package com.bawei.jingdong.present;

import com.bawei.jingdong.bean.Bean;
import com.bawei.jingdong.model.GridModel;
import com.bawei.jingdong.view.GridView;

/**
 * Created by Adminjs on 2017/12/26.
 */

public class GridPresent {
    private GridView view;
    private GridModel model;

    public GridPresent(GridView view) {
        this.view = view;
        this.model = new GridModel();
    }
    public void get(){
        model.getData(new GridModel.ModelCallBack() {
            @Override
            public void success(Bean bean) {
                if(view != null){
                    view.success(bean);
                }
            }
        });
    }
        public void detach(){
            view = null;

    }
}
