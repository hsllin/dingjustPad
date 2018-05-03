package com.dingjust.pad.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dingjust.pad.R;
import com.dingjust.pad.utils.SaveDataTool;
import com.dingjust.pad.view.LoginView;
import com.dingjust.pad.interactor.impl.LoginInteratorImpl;
import com.dingjust.pad.presenter.impl.LoginPresenterImpl;
import com.dingjust.pad.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {


    @BindView(R.id.input_user)
    EditText inputUser;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.input_workstation)
    EditText inputWorkStation;
    @BindView(R.id.btn_login)
    Button login;


    private LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;
    private Intent intent;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        SaveDataTool.getUserAndWorkStation(inputWorkStation, inputUser, this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        loginPresenter = new LoginPresenterImpl(this, new LoginInteratorImpl());


    }

    @Override
    public void onClick(View view) {
        loginPresenter.validateCredentials(inputUser.getText().toString(), inputPassword.getText().toString(), inputWorkStation.getText().toString());

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
    public void setWorkStationError() {
        inputWorkStation.setError("工位不能为空");
    }

    @Override
    public void navigateToHome(String type) {
        switch (type) {
            case "clipping":
                intent = new Intent(this, ClippingActivity.class);
                break;
            case "hangging":
                intent = new Intent(this, HanggingActivity.class);
                break;
            case "sewing":
                intent = new Intent(this, SewingActivity.class);
                break;
        }
        editor.putString("workStation", inputWorkStation.getText().toString());
        editor.putString("userName", inputUser.getText().toString());
        editor.apply();
        intent.putExtra("userName", inputUser.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在活动销毁时记得将progressDialog设置为空
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
