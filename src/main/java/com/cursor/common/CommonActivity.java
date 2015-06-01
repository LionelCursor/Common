package com.cursor.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cursor.common.utils.LifeCycleUtils;

/**
 * USER: ldx
 * DATE: 2015/5/30
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class CommonActivity extends AppCompatActivity{

    private boolean exitWhenDblClick = false;

    private long lastTimePressedBack = 0;

    @Override
    public void onBackPressed() {
        if (exitWhenDblClick) {
            if ((System.currentTimeMillis() - lastTimePressedBack) > 2000) {
                lastTimePressedBack = System.currentTimeMillis();
            } else {
                LifeCycleUtils.exitApplication();
            }
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deploy();
    }

    /**
     * deploy the
     */
    protected void deploy(){
        setExitWhenDblClick(false);
    }

    /**
     *
     * @param exit true if set exit when double click, false otherwise
     */
    public void setExitWhenDblClick(boolean exit){
        this.exitWhenDblClick = exit;
    }

}
