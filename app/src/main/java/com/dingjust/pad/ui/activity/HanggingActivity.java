package com.dingjust.pad.ui.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.dingjust.pad.R;
import com.dingjust.pad.ui.activity.base.BaseActivity;

//上吊挂工作界面
public class HanggingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangging);
        ButterKnife.bind(this);
    }
}
