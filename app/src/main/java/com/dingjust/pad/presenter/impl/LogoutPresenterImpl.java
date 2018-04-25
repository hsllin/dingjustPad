package com.dingjust.pad.presenter.impl;

import com.dingjust.pad.interactor.LogoutInteractor;
import com.dingjust.pad.presenter.LogoutPresenter;
import com.dingjust.pad.view.LogoutView;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class LogoutPresenterImpl implements LogoutPresenter, LogoutInteractor.LogoutFinishedListener {
    private LogoutView logoutView;
    private LogoutInteractor logoutInteractor;

    public LogoutPresenterImpl(LogoutView logoutView, LogoutInteractor logoutInteractor) {
        this.logoutView = logoutView;
        this.logoutInteractor = logoutInteractor;
    }

    @Override
    public void destroy() {
        if (logoutView != null) {
            logoutView = null;
        }
    }

    @Override
    public void validateLogout() {
        if (logoutView != null) {
            logoutView.showProgressDialog();
        }
        logoutInteractor.logout(this);
    }

    @Override
    public void success() {
        //TODO:get user from db and compare the one get from input is correct
        if (logoutView != null) {
            logoutView.navigateToLogin();
        }
    }
}
