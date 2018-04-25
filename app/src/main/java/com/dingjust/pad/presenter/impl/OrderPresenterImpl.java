package com.dingjust.pad.presenter.impl;

import com.dingjust.pad.interactor.OrderInteractor;
import com.dingjust.pad.presenter.OrderPresenter;
import com.dingjust.pad.view.OrderView;

import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class OrderPresenterImpl implements OrderPresenter, OrderInteractor.OnOrderFinishedListener {
    private OrderView orderView;
    private OrderInteractor orderInteractor;

    public OrderPresenterImpl(OrderView orderView, OrderInteractor orderInteractor) {
        this.orderView = orderView;
        this.orderInteractor = orderInteractor;
    }

    @Override
    public void onResume() {
        orderInteractor.findOrders(this);
    }

    @Override
    public void destroy() {
        orderView = null;
    }

    @Override
    public void onOrderFinished(List<String> groupList, List<List<String>> childList) {
        if (orderView != null) {
            orderView.setOrder(groupList, childList);
        }
    }
}
