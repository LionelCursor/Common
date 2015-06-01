package com.cursor.common.template.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.cursor.common.AppData;
import com.cursor.common.CommonConfig;
import com.cursor.common.R;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * USER: ldx
 * DATE: 2015/5/30
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class ToolbarActivity extends BaseTemplateActivity {

    //DEBUG TAG
    public static final String TAG = "ToolbarActivity";

    private Toolbar mToolbar;

    private RelativeLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CommonConfig.DEBUG) Logger.d(TAG, "onCreate");
        setContentViewInner(R.layout.toolbar_activity_content_root);
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        mContainer = (RelativeLayout) findViewById(R.id.common_container);
    }

    protected Toolbar getToolbar(){
        return mToolbar;
    }

    @Override
    public void setContentView(int layoutResID) {
        View.inflate(AppData.getContext(), layoutResID, mContainer);
    }

    public void setContentViewInner(int layoutResID){
        super.setContentView(layoutResID);
    }
}