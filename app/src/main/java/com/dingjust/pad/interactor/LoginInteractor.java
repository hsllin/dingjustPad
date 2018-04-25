package com.dingjust.pad.interactor;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public interface LoginInteractor {
    interface LoginFinishedListener {
        void setUserError();

        void setPasswordError();

        void success();

    }

    void login(String userName, String password, LoginFinishedListener loginFinishedListener);
}
