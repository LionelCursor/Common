package com.cursor.common;

import android.app.Application;

import com.cursor.common.utils.DisplayUtils;

/**
 * USER: ldx
 * EMAIL: danxionglei@foxmail.com
 * PROJECT_NAME: mofaforhackday
 * DATE: 2015/3/15
 */
public class CommonApplication extends Application{

    //DEBUG TAG
    private static final String TAG = "CommonApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        AppData.init(this);
        DisplayUtils.init(this);
    }
}
