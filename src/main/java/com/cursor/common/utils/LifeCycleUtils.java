package com.cursor.common.utils;

import android.content.Intent;

import com.cursor.common.AppData;

/**
 * USER: ldx
 * DATE: 2015/5/31
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class LifeCycleUtils {

    /**
     * Exit all application by imitating of pressing HOME button
     */
    public static void exitApplication() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        AppData.getContext().startActivity(intent);
    }
}
