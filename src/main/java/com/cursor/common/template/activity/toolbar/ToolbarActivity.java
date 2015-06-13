package com.cursor.common.template.activity.toolbar;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cursor.common.AppData;
import com.cursor.common.CommonConfig;
import com.cursor.common.R;
import com.cursor.common.template.activity.BaseTemplateActivity;
import com.cursor.common.utils.Logger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * USER: ldx
 * DATE: 2015/5/30
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 * *************************************
 * FEATURES
 * DONE - {@link #setTitle(int)}
 * TODO - Set MODE
 * TODO - Customized tabs for viewpager
 * TODO - Original Actionbar mode
 */
public class ToolbarActivity extends BaseTemplateActivity {

    //DEBUG TAG
    public static final String TAG = "ToolbarActivity";

    @IntDef(flag = true, value = {
            DISPLAY_ORIGINAL,
            DISPLAY_SHOW_TITLE,
            DISPLAY_SHOW_TABS
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayOptions {
    }

    public static final int DISPLAY_ORIGINAL = 0x1;
    public static final int DISPLAY_SHOW_TITLE = 0x1 << 1;
    public static final int DISPLAY_SHOW_TABS = 0x1 << 2;
    public int mDisplayOptions = 0x1;
    private Toolbar mToolbar;
    private TextView mTitleText;
    private RelativeLayout mToolbarContainer;
    private RelativeLayout mContentContainer;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CommonConfig.DEBUG) Logger.d(TAG, "onCreate");
        setContentViewInner(R.layout.toolbar_activity_content_root);
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        mTitleText = (TextView) findViewById(R.id.common_title_text);
        mContentContainer = (RelativeLayout) findViewById(R.id.common_container);
        mToolbarContainer = (RelativeLayout) findViewById(R.id.toolbar_container);
        setTitle(getTitle());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(AppData.getContext());
        View view = inflater.inflate(R.layout.toolbar_activity_content_root, mContentContainer, false);
        mContentContainer.addView(view);
    }

    @Override
    public void setContentView(View view) {
        mContentContainer.addView(view);
    }

    private void setContentViewInner(int layoutResID) {
        super.setContentView(layoutResID);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    //===========================PUBLIC METHOD==============================

    public void setDisplayMode(@DisplayOptions int newOptions) {
        int oldOpts = mDisplayOptions;
        int changed = oldOpts ^ newOptions;
        mDisplayOptions = newOptions;

        if (changed != 0) {
            if ((changed & DISPLAY_SHOW_TITLE) != 0) {
                if ((newOptions & DISPLAY_SHOW_TITLE) != 0) {
                    mTitleText.setText(mTitle);
                    mTitleText.setVisibility(View.VISIBLE);
                } else {
                    mTitleText.setVisibility(View.GONE);
                    mTitleText.setText(null);
                }
            }

            if ((changed & DISPLAY_SHOW_TABS) != 0) {
                if ((newOptions & DISPLAY_SHOW_TABS) != 0) {

                } else {

                }
            }
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        mTitleText.setText(mTitle);
    }

    @Override
    public void setTitle(int titleId) {
        mTitle = getResources().getString(titleId);
        mTitleText.setText(mTitle);
    }

    public void setCustomView(View view){
        mToolbarContainer.addView(view);
    }




}