package com.dingjust.pad.presenter;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public interface LoginPresenter {
    void validateCredentials(String userName, String password, String workStation);

    void destroy();
}
