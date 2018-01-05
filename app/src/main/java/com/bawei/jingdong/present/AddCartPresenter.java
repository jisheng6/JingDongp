package com.bawei.jingdong.present;


import com.bawei.jingdong.bean.AddCartBean;
import com.bawei.jingdong.model.AddCartModel;
import com.bawei.jingdong.model.AddCartModelCallBack;
import com.bawei.jingdong.view.AddCartViewCallBack;

/**
 * Created by Menglucywhh on 2017/12/7.
 */

public class AddCartPresenter {
    AddCartModel addCartModel = new AddCartModel();

    AddCartViewCallBack addCartViewCallBack;
    public AddCartPresenter(AddCartViewCallBack addCartViewCallBack) {
        this.addCartViewCallBack = addCartViewCallBack;
    }


    public void getData(String pid) {

        addCartModel.getData(pid, new AddCartModelCallBack() {
            @Override
            public void success(AddCartBean addCartBean) {
                addCartViewCallBack.success(addCartBean);
            }

            @Override
            public void failure() {
                addCartViewCallBack.failure();
            }
        });

    }
}
