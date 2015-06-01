package com.cursor.common.template.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.cursor.common.AppData;
import com.cursor.common.CommonConfig;
import com.cursor.common.R;
import com.cursor.common.utils.DisplayUtils;
import com.cursor.common.utils.SelectorUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * USER: ldx
 * DATE: 2015/5/30
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class TabsActivity extends ToolbarActivity{

    //Debug TAG
    private static final String TAG = "TabsActivity";

    /**
     * store the names of fragments
     * {@link Fragment#instantiate(Context, String)}
     */
    private List<String> mFragmentNames;
    /**
     * By default, the first fragment was set to init the main layout
     */
    private int mInitFragmentIndex = 0;
    /**
     * store the drawable ids of tabs
     */
    private List<Integer> mTabImages;
    /**
     * Store the views of tabs
     */
    private List<View> mTabViews;
    /**
     * should be override firstly, as other functions will be decided by this number
     */
    private Integer mTabsCount = 0;
    /**
     * background color of pressed tabs
     */
    private int mColorTabBackgroundPresssed = 0xffff0000;
    /**
     * background color of normal tabs
     */
    private int mColorTabBackground = 0xffff00ff;
    /**
     * height of tabs layout ,unit dp
     */
    private int mTabHeight = 50;

    /**
     * Layout of tabs below to be set.
     */
    private ViewGroup mTabsLayout = null;

    /**
     * Store the real tab layout
     */
    private ViewGroup mTabs;
    private Drawable ColorSelector;

    //====================== LIFECYCLE ===========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CommonConfig.DEBUG) Logger.d(TAG, "onCreate");
        onPreDataInit();
        setContentView(generateViewRoot());
        deployTabs();
        fillWithFragment(getFragmentWithIndex(mInitFragmentIndex));
    }

    //======================== PRIVATE METHOD =========================

    /**
     * Generate the root view to be set in activity content.
     *
     * @return The content view of activity
     */
    private View generateViewRoot() {
        //View's layoutParams will be ignored when use {@link Activity#setContentView(View view)}
        //and set MATCH_PARENT both height and width
        RelativeLayout root = new RelativeLayout(AppData.getContext());
        //tab layout
        mTabs = getTabsLayout() == null ? generateTabLayout() : getTabsLayout();
        mTabs.setId(R.id.common_tabs_activity_tabs_layout);
        LayoutParams lpOfTabs = new LayoutParams(MATCH_PARENT, DisplayUtils.dip2px(mTabHeight));
        lpOfTabs.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        root.addView(mTabs, lpOfTabs);
        //main layout
        RelativeLayout mainLayout = new RelativeLayout(AppData.getContext());
        mainLayout.setId(R.id.common_tabs_activity_main_layout);
        LayoutParams lpOfMainLayout = new LayoutParams(MATCH_PARENT,MATCH_PARENT);
        lpOfMainLayout.addRule(RelativeLayout.ABOVE,R.id.common_tabs_activity_tabs_layout);
        root.addView(mainLayout, lpOfMainLayout);

        return root;
    }

    private ViewGroup generateTabLayout() {
        LinearLayout layout = new LinearLayout(AppData.getContext());
        if (mTabsCount == mTabViews.size()) {
            for (View v : mTabViews) {
                attachTabs(v, layout);
            }
        } else if (mTabsCount == mTabImages.size()) {
            for (Integer i : mTabImages) {
                View v = getTabViewFromId(i);
                attachTabs(v, layout);
            }
        } else {
            for (int i = 0; i < mTabsCount; i++) {
                View v = generateEmptyTabView();
                attachTabs(v,layout);
            }
        }
        return layout;
    }

    private View generateEmptyTabView(){
        ImageView image = new ImageView(AppData.getContext());
        image.setMinimumHeight(50);
        return image;
    }

    private void attachTabs(View v, ViewGroup viewGroup) {
        if(CommonConfig.DEBUG) Logger.d(TAG, "attachTabs");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0 , WRAP_CONTENT, 1);
        viewGroup.addView(v, lp);
        v.setTag(viewGroup.indexOfChild(v));
    }

    private View getTabViewFromId(int id) {
        ImageView image = new ImageView(AppData.getContext());
        image.setImageDrawable(getResources().getDrawable(id));
        return image;
    }

    private Fragment getFragmentWithIndex(int index) {
        if (CommonConfig.DEBUG) Logger.d(TAG, "getFragmentWithIndex : index = " + index);
        if (index >= mFragmentNames.size()) {
            throw new IllegalStateException("Index of fragment out of bounds");
        }
        return Fragment.instantiate(AppData.getContext(), mFragmentNames.get(index));
    }

    private Drawable generateColorSelector() {
        if (ColorSelector != null) {
            return ColorSelector;
        }
        return ColorSelector = SelectorUtils.generateSelector(
                new ColorDrawable(mColorTabBackground),
                new ColorDrawable(mColorTabBackgroundPresssed)
        );
    }

    /**
     * deploy tabs below
     */
    private void deployTabs() {
        if (CommonConfig.DEBUG) Logger.d(TAG, "deployTabs");

        if (mTabs == null) {
            throw new IllegalStateException("mTabs is null");
        }
        View v;
        for (int i = 0; i < mTabsCount; i++) {
            v = mTabs.getChildAt(i);
            v.setBackgroundDrawable(generateColorSelector());
            v.setOnClickListener(new OnTabClickInnerListener());
            onDeployTabs(v, i);
        }
    }

    private void cleanSelectedTabs() {
        if (CommonConfig.DEBUG) Logger.d(TAG, "cleanSelectedTabs");

        if (mTabs == null) {
            throw new IllegalStateException("mTabs is null");
        }
        View v;
        for (int i = 0; i < mTabsCount; i++) {
            v = mTabs.getChildAt(i);
            v.setBackgroundDrawable(generateColorSelector());
            onDeployTabs(v, i);
        }
    }

    //========================= PUBLIC METHOD ============================

    /**
     * Set the tabs from a layout resource.
     * The resource will be inflated, adding all top-level views to the activity contents.
     *
     * @param layoutResId Resource ID to be inflated.
     */
    public void setTabsView(int layoutResId) {
        mTabsLayout = (ViewGroup) View.inflate(AppData.getContext(), layoutResId, null);
    }

    /**
     * Set the tabs from a view.
     *
     * @param view View of tabs below
     */
    public void setTabsView(ViewGroup view) {
        mTabsLayout = view;
    }

    /**
     * Get the layout view of tabs below
     *
     * @return mTabsLayout, null if not set.
     */
    public ViewGroup getTabsLayout() {
        return mTabsLayout;
    }

    /**
     * By default, the first fragment was set to init the main layout
     *
     * @param initFragmentIndex The index of fragment to be set in main layout
     */
    public void setInitFragmentIndex(int initFragmentIndex) {
        this.mInitFragmentIndex = initFragmentIndex;
    }

    /**
     * Set tab height, unit dp
     *
     * @param height height of tabs
     */
    public void setTabHeight(int height) {
        mTabHeight = height;
        mTabs.invalidate();
    }

    /**
     * Fill the R.id.main_layout with fragment provided
     *
     * @param fragment fragment
     */
    public void fillWithFragment(Fragment fragment) {
        if (CommonConfig.DEBUG) Logger.d(TAG, "fragment : " + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction().replace(R.id.common_tabs_activity_main_layout, fragment).commit();
    }

    public void setmTabsCount(int count){
        mTabsCount = count;
    }

    public void setmInitFragmentIndex(int index){
        mInitFragmentIndex = index;
    }

    public List<String> getmFragmentNames(){
        return mFragmentNames;
    }

    public List<Integer> getmTabImages(){
        return mTabImages;
    }

    public List<View> getmTabViews(){
        return mTabViews;
    }

    //================== PROTECTED METHOD ========================

    /**
     * The only thing you have to override
     */
    protected void onPreDataInit() {
        mTabsCount = 0;
        mInitFragmentIndex = 0;
        mFragmentNames = new ArrayList<>(10);
        //Loading one of them or not, if both, use mTabViews
        mTabViews = new ArrayList<>(10);
        mTabImages = new ArrayList<>(10);
    }

    /**
     * Invoked when a tab was selected
     */
    protected void onTabSelected(View v, int index) {
        if (CommonConfig.DEBUG) Logger.d(TAG, "onTabSelected index = " + index);
        cleanSelectedTabs();
        v.setBackgroundColor(mColorTabBackgroundPresssed);
        fillWithFragment(getFragmentWithIndex(index));
    }

    /**
     * Invoked when tab view is being deployed
     */
    protected void onDeployTabs(View v, int index) {
        if (CommonConfig.DEBUG) Logger.d(TAG, "onDeployTabs index = " + index);
    }

    private class OnTabClickInnerListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int index = (int) v.getTag();
            onTabSelected(v, index);
        }
    }

}
