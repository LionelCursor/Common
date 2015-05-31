package com.cursor.common.template;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.cursor.common.AppData;
import com.cursor.common.CommonConfig;
import com.cursor.common.R;
import com.cursor.common.utils.DisplayUtils;
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
public abstract class TabsActivity extends BaseTemplateActivity {

    //Debug TAG
    private static final String TAG = "TabsActivity";

    /**
     * store the names of fragments
     * {@link android.support.v4.app.Fragment#instantiate(Context, String)}
     */
    private List<String> mFragmentNames = new ArrayList<>(10);
    /**
     * By default, the first fragment was set to init the main layout
     */
    private int mInitFragmentIndex = 0;
    /**
     * store the drawable ids of tabs
     */
    private List<Integer> mTabImages = new ArrayList<>(10);
    /**
     * Store the views of tabs
     */
    private List<View> mTabViews = new ArrayList<>(10);
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
     * height of tabs layout ,unit dp
     */
    private int mTabHeight = 50;

    /**
     * Layout of tabs below
     */
    private View mTabsLayout = null;

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
        //main layout
        RelativeLayout mainLayout = new RelativeLayout(AppData.getContext());
        mainLayout.setId(getResources().getIdentifier("main_layout", null, null));
        root.addView(mainLayout, new LayoutParams(MATCH_PARENT, MATCH_PARENT));
        //tab layout
        View tabs = getTabsLayout() == null ? generateTabLayout() : getTabsLayout();
        tabs.setId(getResources().getIdentifier("tabs_layout", null, null));
        RelativeLayout.LayoutParams lpOfTabs = new LayoutParams(MATCH_PARENT, DisplayUtils.dip2px(mTabHeight));
        lpOfTabs.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lpOfTabs.addRule(RelativeLayout.BELOW, R.id.main_layout);
        root.addView(tabs, lpOfTabs);
        return root;
    }

    private View generateTabLayout() {
        LinearLayout layout = new LinearLayout(AppData.getContext());
        if (mTabsCount == mTabImages.size()) {
            for (Integer i:mTabImages) {
                View v = getTabView(i);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT,1);
                layout.addView(v,lp);
            }
        }
        return layout;
    }

    private View getTabView(int id) {
        ImageView image = new ImageView(AppData.getContext());
        image.setImageDrawable(getResources().getDrawable(id, null));
        return image;
    }

    private Fragment getFragment(int index) {
        if (CommonConfig.DEBUG) Logger.d(TAG, "getFragment : index = " + index);
        if (index >= mFragmentNames.size()) {
            throw new IllegalStateException("Index of fragment out of bounds");
        }
        return Fragment.instantiate(AppData.getContext(), mFragmentNames.get(index));
    }

    //========================= PUBLIC METHOD ============================

    /**
     * Set the tabs from a layout resource.
     * The resource will be inflated, adding all top-level views to the activity contents.
     *
     * @param layoutResId Resource ID to be inflated.
     */
    public void setTabsView(int layoutResId) {

    }

    /**
     * Set the tabs from a view.
     *
     * @param view View of tabs below
     */
    public void setTabsView(View view) {
        mTabsLayout = view;
    }

    /**
     * Get the layout view of tabs below
     *
     * @return mTabsLayout, null if not set.
     */
    public View getTabsLayout() {
        return mTabsLayout;
    }

    /**
     * By default, the first fragment was set to init the main layout
     *
     * @return mInitFragmentIndex
     */
    public int getInitFragmentIndex() {
        return mInitFragmentIndex;
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
    }

    /**
     * Fill the R.id.main_layout with fragment provided
     *
     * @param fragment fragment
     */
    public void fillWithFragment(Fragment fragment) {
        if (CommonConfig.DEBUG) Logger.d(TAG, "fragment : " + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment).commit();

    }

    //================== PROTECTED METHOD ========================

    /**
     * Invoked when a tab was selected
     */
    protected void onTabSelected() {
        if (CommonConfig.DEBUG) Logger.d(TAG, "onTabSelected");
    }

    //=================== ABSTRACT METHOD ========================

    protected abstract void setFragmentNames(List<String> fragmentNames);

    protected abstract void setmTabsCount(int count);


    //====================== LIFECYCLE ===========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (CommonConfig.DEBUG) Logger.d(TAG, "onCreate");
        setContentView(generateViewRoot());
        fillWithFragment(getFragment(mInitFragmentIndex));

    }


}
