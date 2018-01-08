package com.bawei.jingdong.present;


import com.bawei.jingdong.bean.DeleteBean;
import com.bawei.jingdong.model.DeleteCartModel;
import com.bawei.jingdong.view.DeleteCartModelCallBack;
import com.bawei.jingdong.view.DeleteCartViewCallBack;

/**
 * Created by Menglucywhh on 2017/12/11.
 */

public class DeleteCartPresenter {

    DeleteCartModel deleteCartModel = new DeleteCartModel();
    DeleteCartViewCallBack deleteCartViewCallBack;
    public DeleteCartPresenter(DeleteCartViewCallBack deleteCartViewCallBack) {
        this.deleteCartViewCallBack = deleteCartViewCallBack;
    }

    public void delete(String pid) {
        deleteCartModel.delete(pid, new DeleteCartModelCallBack() {
            @Override
            public void success(DeleteBean deleteBean) {
            deleteCartViewCallBack.success(deleteBean);
            }

            @Override
            public void failure() {
            deleteCartViewCallBack.failure();
            }
        });
    }
}
