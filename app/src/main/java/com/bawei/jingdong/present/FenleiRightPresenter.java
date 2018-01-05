package com.bawei.jingdong.present;

import com.bawei.jingdong.bean.FenLeiRightBean;
import com.bawei.jingdong.model.FenleiRightModel;
import com.bawei.jingdong.model.FenleiRightModelCallBack;
import com.bawei.jingdong.view.FenleiRightViewCallBack;

/**
 * Created by Menglucywhh on 2017/12/6.
 */

public class FenleiRightPresenter {
	
    private FenleiRightModel fenleiRightModel;
    FenleiRightViewCallBack fenleiRightViewCallBack;
	
    public FenleiRightPresenter(FenleiRightViewCallBack fenleiRightViewCallBack){
        this.fenleiRightViewCallBack =fenleiRightViewCallBack;
        fenleiRightModel = new FenleiRightModel();
    }

    public void getData(String cid){
        fenleiRightModel.getData(new FenleiRightModelCallBack() {
            @Override
            public void success(FenLeiRightBean fenLeiRightBean) {
                fenleiRightViewCallBack.success(fenLeiRightBean);
            }

            @Override
            public void failure() {
                fenleiRightViewCallBack.failure();
            }
        },cid);
    }
}
