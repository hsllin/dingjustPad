package com.dingjust.pad.interactor.impl;

import android.os.Handler;
import android.text.TextUtils;
import com.dingjust.pad.interactor.LoginInteractor;


/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class LoginInteratorImpl implements LoginInteractor {
    @Override
    public void login(final String userName, final String password, final LoginFinishedListener loginFinishedListener) {
        if (TextUtils.isEmpty(userName)) {
            loginFinishedListener.setUserError();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            loginFinishedListener.setPasswordError();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loginFinishedListener.success();
            }
        }, 2000);
    }
}
