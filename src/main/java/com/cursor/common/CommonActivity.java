package com.cursor.common;

import android.support.v4.app.FragmentActivity;

import com.cursor.common.utils.LifeCycleUtils;

/**
 * USER: ldx
 * DATE: 2015/5/30
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class CommonActivity extends FragmentActivity{

    private boolean exitWhenDblClick = false;

    private long lastTimePressedBack = 0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (exitWhenDblClick) {
            if ((System.currentTimeMillis() - lastTimePressedBack) > 2000) {
                lastTimePressedBack = System.currentTimeMillis();
            } else {
                LifeCycleUtils.exitApplication();
            }
        }
    }

    /**
     *
     * @param exit true if set exit when double click, false otherwise
     */
    public void setExitWhenDblClick(boolean exit){
        this.exitWhenDblClick = exit;
    }

}
