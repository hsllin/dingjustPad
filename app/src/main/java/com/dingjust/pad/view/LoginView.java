package com.dingjust.pad.view;

import android.app.ProgressDialog;
import com.dingjust.pad.view.base.BaseView;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public interface LoginView extends BaseView {

    void setUserError();

    void setPasswordError();

    void navigateToHome();
}
