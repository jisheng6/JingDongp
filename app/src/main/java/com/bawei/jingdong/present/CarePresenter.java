package com.bawei.jingdong.present;

import com.bawei.jingdong.bean.ShopBean;
import com.bawei.jingdong.model.CareModel;
import com.bawei.jingdong.view.CareViewCallBack;

/**
 * Created by Adminjs on 2017/12/27.
 */

public class CarePresenter {
    private CareViewCallBack view;
    private CareModel model;

    public CarePresenter(CareViewCallBack view) {
        this.view = view;
        this.model = new CareModel();
    }
    public void getData(){
        model.getData(new CareModel.CareModelCallBack() {
            @Override
            public void success(ShopBean bean) {
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
