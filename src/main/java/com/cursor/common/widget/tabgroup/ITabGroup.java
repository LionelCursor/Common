package com.cursor.common.widget.tabgroup;

/**
 * USER: ldx
 * DATE: 2015/6/3
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public interface ITabGroup {

    void performTabSelected(int position);

    void setTabSelectedListener(OnTabSelectedObserver observer);

    void addTab(Tab tab);

}
