package com.dingjust.pad.interactor.impl;

import android.util.Log;
import android.widget.ExpandableListView;
import com.dingjust.pad.bean.Order;
import com.dingjust.pad.interactor.OrderInteractor;
import com.dingjust.pad.ui.adapter.MyBaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class OrderInteractorImpl implements OrderInteractor {
    private List<String> groupList;
    private Order order;
    private List<Order> orderList;
    private List<List<String>> childList;
    private List<String> group;

    @Override
    public void findOrders(OnOrderFinishedListener listener) {
        loadOrderData();
        listener.onOrderFinished(groupList, childList);
    }

    public void loadOrderData() {
//        从数据库从查找出所有的order订单数据

        orderList = new ArrayList<>();
        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        for (int i = 30153; i < 30174; i++) {
            order = new Order("2910000", "紧急", "6-25", 115, "60:00", "钉扣", "钉扣机", 5, "0.5");
            groupList.add(String.valueOf(i));
            orderList.add(order);

        }
        for (Order order : orderList) {
            group = new ArrayList<>();
            group.add("款号：" + order.getMatnr() + "\n" + "订单类型：" + order.getOrtyp() + "\n" + "交期：" + order.getLfdat());
            group.add("标准工时：" + order.getSmv() + "\n");
            group.add("工序：" + order.getGxmc() + "\n" + "设备：" + order.getJqdm() + "\n" + "等级难度：" + order.getDiflv() + "\n" + "质量要求：" + order.getQmreq() + "\n");
            childList.add(group);
        }

    }
}
