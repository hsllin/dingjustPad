package com.dingjust.pad.interactor.impl;

import android.os.Handler;
import android.text.TextUtils;
import com.dingjust.pad.interactor.LoginInteractor;


/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class LoginInteratorImpl implements LoginInteractor {
    String type = "sewing";

    @Override
    public void login(final String userName, final String password, final String workStation, final LoginFinishedListener loginFinishedListener) {
        if (TextUtils.isEmpty(userName)) {
            loginFinishedListener.setUserError();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            loginFinishedListener.setPasswordError();
            return;
        }
        if (TextUtils.isEmpty(workStation)) {
            loginFinishedListener.setWorkStationError();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loginFinishedListener.success(type);
            }
        }, 2000);
    }
}
