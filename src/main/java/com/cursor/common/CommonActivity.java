package com.cursor.common;

import android.app.Activity;

/**
 * USER: ldx
 * DATE: 2015/5/30
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class CommonActivity extends Activity {

    private boolean exitWhenDblClick = false;

    private long lastTimePressedBack = 0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (exitWhenDblClick) {
            if ((System.currentTimeMillis() - lastTimePressedBack) > 2000) {
                lastTimePressedBack = System.currentTimeMillis();
            } else {

            }
        }
    }


    public void setExitWhenDblClick(boolean exit){
        this.exitWhenDblClick = exit;
    }

    //TODO- Exit application when the back button double clicked

}
