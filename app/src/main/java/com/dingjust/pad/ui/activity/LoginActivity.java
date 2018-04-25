package com.dingjust.pad.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dingjust.pad.R;
import com.dingjust.pad.view.LoginView;
import com.dingjust.pad.interactor.impl.LoginInteratorImpl;
import com.dingjust.pad.presenter.impl.LoginPresenterImpl;
import com.dingjust.pad.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {


    @BindView(R.id.input_user)
    EditText inputUser;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    Button login;


    private LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        loginPresenter = new LoginPresenterImpl(this, new LoginInteratorImpl());


    }

    @Override
    public void onClick(View view) {
        loginPresenter.validateCredentials(inputUser.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("正在验证用户信息....");
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();

    }

    @Override
    public void setUserError() {
        inputUser.setError("用户名不能为空");
    }

    @Override
    public void setPasswordError() {
        inputPassword.setError("密码不能为空");

    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userName", inputUser.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("destroy", "destroy");
        //在活动销毁时记得将progressDialog设置为空
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
