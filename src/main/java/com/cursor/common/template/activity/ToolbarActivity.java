package com.cursor.common.template.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cursor.common.AppData;
import com.cursor.common.CommonConfig;
import com.cursor.common.R;
import com.cursor.common.utils.Logger;

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

    private TextView mTitleText;

    private RelativeLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CommonConfig.DEBUG) Logger.d(TAG, "onCreate");
        setContentViewInner(R.layout.toolbar_activity_content_root);
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        mTitleText = (TextView) findViewById(R.id.common_title_text);
        mContainer = (RelativeLayout) findViewById(R.id.common_container);
        setTitle(getTitle());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(AppData.getContext());
        View view = inflater.inflate(R.layout.toolbar_activity_content_root, mContainer, false);
        mContainer.addView(view);
    }

    @Override
    public void setContentView(View view) {
        mContainer.addView(view);
    }

    private void setContentViewInner(int layoutResID) {
        super.setContentView(layoutResID);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitleText.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        mTitleText.setText(titleId);
    }

}