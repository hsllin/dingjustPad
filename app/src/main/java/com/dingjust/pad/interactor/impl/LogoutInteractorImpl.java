package com.dingjust.pad.interactor.impl;

import android.os.Handler;
import android.text.TextUtils;
import com.dingjust.pad.interactor.LogoutInteractor;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class LogoutInteractorImpl implements LogoutInteractor {
    @Override
    public void logout(final LogoutFinishedListener logoutFinishedListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logoutFinishedListener.success();
            }
        },2000);

    }
}
