package com.dingjust.pad.presenter.impl;

import android.app.ProgressDialog;
import com.dingjust.pad.interactor.LoginInteractor;
import com.dingjust.pad.presenter.LoginPresenter;
import com.dingjust.pad.view.LoginView;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.LoginFinishedListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;


    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentials(String userName, String password, String workStation) {
        if (loginView != null) {
            loginView.showProgressDialog();
        }
        loginInteractor.login(userName, password, workStation, this);
    }

    @Override
    public void destroy() {
        loginView = null;
    }

    @Override
    public void setUserError() {
        if (loginView != null) {
            loginView.setUserError();
            loginView.hideProgressDialog();
        }
    }

    @Override
    public void setPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgressDialog();
        }

    }

    @Override
    public void setWorkStationError() {
        if (loginView != null) {
            loginView.setWorkStationError();
            loginView.hideProgressDialog();
        }
    }

    @Override
    public void success(String type) {
        if (loginView != null) {
            loginView.navigateToHome(type);
        }
    }
}
