package com.bawei.jingdong.view;

import com.bawei.jingdong.bean.DeleteBean;

/**
 * Created by Menglucywhh on 2017/12/11.
 */

public interface DeleteCartViewCallBack {
    public void success(DeleteBean deleteBean);
    public void failure();
}
