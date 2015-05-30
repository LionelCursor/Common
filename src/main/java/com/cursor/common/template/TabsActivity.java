package com.cursor.common.template;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.cursor.common.AppData;
import com.cursor.common.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * USER: ldx
 * DATE: 2015/5/30
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class TabsActivity extends BaseTemplateActivity{

    //Debug TAG
    private static final String TAG = "TabsActivity";

    /**
     * store the names of fragments
     * {@link android.support.v4.app.Fragment#instantiate(Context, String)}
     */
    private List<String> mFragmentNames = new ArrayList<>(10);

    /**
     * store the drawable ids of tabs
     */
    private List<Integer> mTabImages  = new ArrayList<>(10);

    /**
     * should be override firstly, as other functions will be decided by this number
     */
    private int mTabsCount = 0;

    /**
     * background color of pressed tabs
     */
    private int mColorTabBackgroundPresssed = 0xffff0000;

    /**
     * background color of normal tabs
     */
    private int mColorTabBackground = 0xffffffff;

    /**
     * Layout of tabs below
     */
    private View mTabsLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG,"onCreate");
        setContentView(generateViewRoot());
    }

    /**
     * Generate the root view to be set in activity content.
     * @return The content view of
     */
    private View generateViewRoot(){
        //View's layoutParams will be ignored when use {@link Activity#setContentView(View view)}
        //and set MATCH_PARENT both height and width
        RelativeLayout root = new RelativeLayout(AppData.getContext());
        RelativeLayout mainLayout = new RelativeLayout(AppData.getContext());
        root.addView(mainLayout,new LayoutParams(MATCH_PARENT, MATCH_PARENT));
        //TODO-
    }

    /**
     * Set the tabs from a layout resource.
     * The resource will be inflated, adding all top-level views to the activity contents.
     * @param layoutResId Resource ID to be inflated.
     * @return
     */
    public void setTabsView(int layoutResId){

    }

    /**
     * Set the tabs from a view.
     * @param view View of tabs below
     */
    public void setTabsView(View view){
        mTabsLayout = view;
    }

    /**
     * Get the layout view of tabs below
     * @return mTabsLayout, null if not set.
     */
    public View getTabsLayout(){
        return mTabsLayout;
    }

    /**
     * invoked when a tab was selected
     */
    protected void onTabSelected(){

    }






}
