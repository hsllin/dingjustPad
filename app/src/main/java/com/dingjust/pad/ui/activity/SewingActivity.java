package com.dingjust.pad.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import com.dingjust.pad.R;
import com.dingjust.pad.bean.Material;
import com.dingjust.pad.bean.Order;
import com.dingjust.pad.interactor.impl.LogoutInteractorImpl;
import com.dingjust.pad.interactor.impl.OrderInteractorImpl;
import com.dingjust.pad.interactor.impl.PhotoInteractorImpl;
import com.dingjust.pad.interactor.impl.VideoAndPhotoInteractorImpl;
import com.dingjust.pad.presenter.LogoutPresenter;
import com.dingjust.pad.presenter.OrderPresenter;
import com.dingjust.pad.presenter.PhotoPresenter;
import com.dingjust.pad.presenter.VideoAndPhotoPresenter;
import com.dingjust.pad.presenter.impl.LogoutPresenterImpl;
import com.dingjust.pad.presenter.impl.OrderPresenterImpl;
import com.dingjust.pad.presenter.impl.PhotoPresenterImpl;
import com.dingjust.pad.presenter.impl.VideoAndPhotoPresenterImpl;
import com.dingjust.pad.ui.adapter.BannerAdapter;
import com.dingjust.pad.ui.adapter.MaterialApter;
import com.dingjust.pad.ui.adapter.MyBaseExpandableListAdapter;
import com.dingjust.pad.ui.customView.IndicatorView;
import com.dingjust.pad.utils.TimeTools;
import com.dingjust.pad.view.LogoutView;
import com.dingjust.pad.view.OrderView;
import com.dingjust.pad.view.PhotoView;
import com.dingjust.pad.view.VideoAndPhotoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SewingActivity extends AppCompatActivity implements LogoutView, PhotoView, OrderView, VideoAndPhotoView, View.OnClickListener {
    @BindView(R.id.user)
    TextView user;
    @BindView(R.id.logout)
    Button logout;
    @BindView(R.id.units)
    TextView units;
    @BindView(R.id.count_time)
    TextView count_time;
    @BindView(R.id.start_pause)
    Button startAndPause;
    @BindView(R.id.finish)
    Button finish;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ExpandableListView elv;
    private ProgressDialog progressDialog;

    private List<String> groupList;
    List<List<String>> childList = new ArrayList<>();

    private int countNum = 316;

    private LogoutPresenter logoutPresenter;
    private PhotoPresenter photoPresenter;
    private OrderPresenter orderPresenter;
    private VideoAndPhotoPresenter videoAndPhotoPresenter;

    private Timer mTimer;
    private TimerTask mTimerTask;

    private static final long MAX_TIME = 5000;
    private long curTime = 5000;
    private static final int WHAT = 101;

    private MyBaseExpandableListAdapter myBaseExpandableListAdapter;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT:
                    long sRecLen = (long) msg.obj;
                    //毫秒换成00:00:00格式的方式，自己写的。
                    if (sRecLen >= 0) {
                        count_time.setTextColor(Color.rgb(0, 255, 0));
                        count_time.setText("完工倒计时:" + "\n" + TimeTools.getCountTimeByLong(sRecLen));
                    } else {
                        count_time.setTextColor(Color.rgb(255, 0, 0));
                        count_time.setText("您已经落后大部队:" + "\n" + TimeTools.getCountTimeByLong(sRecLen));
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewing);
        ButterKnife.bind(this);
        initParams();

        findViewById(R.id.logout).setOnClickListener(this);
        findViewById(R.id.qualityPro).setOnClickListener(this);
        findViewById(R.id.finish).setOnClickListener(this);
        findViewById(R.id.start_pause).setOnClickListener(this);
        elv = (ExpandableListView) findViewById(R.id.elv_local_data);
        logoutPresenter = new LogoutPresenterImpl(this, new LogoutInteractorImpl());
        photoPresenter = new PhotoPresenterImpl(this, new PhotoInteractorImpl());
        orderPresenter = new OrderPresenterImpl(this, new OrderInteractorImpl());
        videoAndPhotoPresenter = new VideoAndPhotoPresenterImpl(this, new VideoAndPhotoInteractorImpl());
        TimeTools.initTimer(mHandler, MAX_TIME);
        TimeTools.mTimer.schedule(TimeTools.mTimerTask, 0, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("userName");
            //    userName.setText("当前用户：" + data.getStringExtra("userName"));
        }
    }

    //这两个方法是饺子播放器中用于处理点击返回和暂停的
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                logoutPresenter.validateLogout();
                break;
            case R.id.qualityPro:
                Intent intent = new Intent(this, QualityProblemActivity.class);
                startActivity(intent);
                break;
            case R.id.finish:
                doneJob();
                break;
            case R.id.start_pause:
                TimeTools.startAndPause(startAndPause, mHandler);
                break;
            default:
        }
    }

    @Override
    public void setLogoutError() {
        //TODO:setLogoutError
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("正在注销当前用户...");
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderPresenter.destroy();
        videoAndPhotoPresenter.destroy();
        photoPresenter.destroy();
        logoutPresenter.destroy();
        TimeTools.isStart = false;
        Log.d("destroyActivity", "destroy---------------------");
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        TimeTools.destroyTimer();
        if (mHandler != null) {
            mHandler.removeMessages(WHAT);
            mHandler = null;
        }
    }

    public void initParams() {
        recyclerView = findViewById(R.id.material_detail);
        progressBar = findViewById(R.id.photoProgress);
        //获取用户名
        user.setText("当前用户：\n" + getIntent().getStringExtra("userName"));

    }

    @Override
    public void showProgress() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOrder(List<String> groupList, List<List<String>> childList) {
        this.groupList = groupList;
        this.childList = childList;
        myBaseExpandableListAdapter = new MyBaseExpandableListAdapter(this, groupList, childList);
        elv.setAdapter(myBaseExpandableListAdapter);
        elv.setGroupIndicator(null);
        elv.expandGroup(0);
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            // 当点击其他分组时，当前分组关闭，避免同时展开多个分组
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < myBaseExpandableListAdapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        elv.collapseGroup(i);
                    }
                }
            }
        });
    }

    @Override
    public void setItems(List<Material> list) {
        //使用recycleview组件显示可以水平左右滑动的图片加描述效果
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MaterialApter adapter = new MaterialApter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        photoPresenter.onResume();
        orderPresenter.onResume();
        videoAndPhotoPresenter.resume();
    }

    //完工逻辑
    public void doneJob() {
        TimeTools.destroyTimer();
        TimeTools.initTimer(mHandler, MAX_TIME);
        TimeTools.isStart = false;
        TimeTools.isPause = false;
        TimeTools.mTimer.schedule(TimeTools.mTimerTask, 0, 1000);
        try {
            if (groupList.size() > 0) {
                String message = myBaseExpandableListAdapter.getGroup(0);
                String parentPosition = myBaseExpandableListAdapter.getGroup(0);
                groupList.remove(parentPosition);
                childList.remove(0);
                elv.expandGroup(0);
//                            强制刷新expandablelistview布局
                myBaseExpandableListAdapter.notifyDataSetChanged();
                units.setText("件数: " + countNum);
                ++countNum;
            } else {
                finish.setEnabled(false);
                Toast.makeText(getApplicationContext(), "订单已全部完成", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setVideoAndPhotoUri(List<String> uriList) {
        BannerAdapter bannerAdapter = new BannerAdapter(this, uriList);
        viewPager.setAdapter(bannerAdapter);
        IndicatorView indicatorView = (IndicatorView) findViewById(R.id.id_view_indicator);
        indicatorView.setViewPager(viewPager);
    }
}

