package com.dingjust.pad.interactor;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public interface LoginInteractor {
    interface LoginFinishedListener {
        void setUserError();

        void setPasswordError();

        void setWorkStationError();

        void success(String type);

    }

    void login(String userName, String password, String workStation, LoginFinishedListener loginFinishedListener);
}
