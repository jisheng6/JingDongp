package com.bawei.jingdong.present;
import com.bawei.jingdong.bean.UpdataBean;
import com.bawei.jingdong.model.IUpDataModel;
import com.bawei.jingdong.model.UpDataModel;
import com.bawei.jingdong.view.IUpDataView;

public class UpDataPresenter {
    private IUpDataView iUpDataView;
    private UpDataModel upDataModel;


    public UpDataPresenter(IUpDataView iUpDataView) {
        this.iUpDataView = iUpDataView;
        this.upDataModel = new UpDataModel();
    }

    public void  getData(String uid, String sellerid, String pid, String num){

        upDataModel.getData(uid, sellerid, pid, num, new IUpDataModel() {
            @Override
            public void success(UpdataBean bean) {
                iUpDataView.success(bean);
            }
        });
    }
}
