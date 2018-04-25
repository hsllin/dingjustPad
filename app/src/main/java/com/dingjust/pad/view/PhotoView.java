package com.dingjust.pad.view;

import com.dingjust.pad.bean.Material;
import com.dingjust.pad.view.base.BaseView;

import java.util.List;

public interface PhotoView {

    void showProgress();

    void hideProgress();

    void setItems(List<Material> list);

}
