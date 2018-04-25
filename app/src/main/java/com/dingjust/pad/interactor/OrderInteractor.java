package com.dingjust.pad.interactor;

import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public interface OrderInteractor {
    interface OnOrderFinishedListener {
        void onOrderFinished(List<String> groupList, List<List<String>> childList);
    }

    void findOrders(OnOrderFinishedListener listener);
}
