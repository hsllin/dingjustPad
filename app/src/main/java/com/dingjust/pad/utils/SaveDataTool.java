package com.dingjust.pad.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
//该工具用于将用户上一次输入的用户名和工位记录下来
public class SaveDataTool {
    private static SharedPreferences preferences;
    private static String workStation;
    private static String userName;

    public static void getUserAndWorkStation(EditText workStationText, EditText userNameText, Context context) {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        workStation = preferences.getString("workStation", "");
        userName = preferences.getString("userName", "");
        if (workStation != null) {
            workStationText.setText(workStation);
        }
        if (userName != null) {
            userNameText.setText(userName);
        }
    }
}
