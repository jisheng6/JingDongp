package com.bawei.jingdong.view;

import com.bawei.jingdong.adapter.MyGridAdapter;
import com.bawei.jingdong.bean.Bean;

/**
 * Created by Adminjs on 2017/12/26.
 */

public interface GridView {
    public void success(Bean bean);
    public void failure();

    void setAdapter(MyGridAdapter myGridAdapter);
}
