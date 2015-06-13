package com.cursor.common.widget.tabgroup;


/**
 * USER: ldx
 * DATE: 2015/6/4
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 * <p>
 * When tab changed, TabGroup will notify all observer which register in it.
 * {@link ITabGroup#setTabSelectedListener(OnTabSelectedObserver)}
 */
public interface OnTabSelectedObserver {

    void onTabSelected(Tab tab, int position);

    void onTabUnselected(Tab tab, int position);

    void onTabReselected(Tab tab, int position);
}
