package com.dingjust.pad.interactor;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public interface LogoutInteractor {
    interface LogoutFinishedListener {
        //TODO:setLogoutError

        void success();
    }

    void logout(LogoutFinishedListener logoutFinishedListener);
}
