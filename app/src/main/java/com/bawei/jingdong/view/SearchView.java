package com.bawei.jingdong.view;

import com.bawei.jingdong.bean.SearchBean;

/**
 * Created by Adminjs on 2017/12/28.
 */

public interface SearchView {
    public void success(SearchBean bean);
    public void failure();

    void empty();

    void falseEdit();
}
