package com.dingjust.pad.utils;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import com.dingjust.pad.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class TimeTools {
    private static final int WHAT = 101;
    private static long curTime = 5000;
    public static boolean isStart = false;
    public static boolean isPause = false;
    public static TimerTask mTimerTask;
    public static Timer mTimer;
    private static final long MAX_TIME = 5000;

    public static String getCountTimeByLong(long finishTime) {
        int totalTime = (int) (finishTime / 1000);//秒
        boolean flag = false;
        if (totalTime < 0) {
            flag = true;
            totalTime = -totalTime;
        }
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();
        if (flag) {
            sb.append("--");
        }
        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }
        return sb.toString();

    }

    public static void initTimer(final Handler mHandler, final long time) {
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (!isStart) {
                    curTime = time;
                    isStart = true;
                } else {
                    curTime -= 1000;
                }
                Message message = new Message();
                message.what = WHAT;
                message.obj = curTime;
                mHandler.sendMessage(message);
            }
        };
        mTimer = new Timer();
    }

    public static void destroyTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    public static void startAndPause(TextView startAndPause, Handler mHandler) {
        if (!isPause) {
            isPause = true;
            TimeTools.mTimer.cancel();
            Log.d("start--------", "start click");
            startAndPause.setText("重新开始");
            return;
        }
        if (isPause) {
            // destroyTimer();
            TimeTools.destroyTimer();
            TimeTools.initTimer(mHandler, MAX_TIME);
            TimeTools.mTimer.schedule(TimeTools.mTimerTask, 0, 1000);
            isPause = false;
            Log.d("pause--------", "pause click");
            startAndPause.setText("暂停");
            return;
        }
    }

}
