package com.cursor.common.widget.tabgroup;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * USER: ldx
 * DATE: 2015/6/3
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 * **********************************
 * DONE- A selectable tab group which has robust observable system
 */
public abstract class BaseTabView extends ViewGroup implements ITabGroup {

    private static final String TAG = "RoundCornerTabAdapter";

    protected int mOldPosSlt = 0;

    protected int mOldPosHover = 0;

    protected int mCurPosSlt = 0;

    protected int mCurPosHover = 0;

    private List<Tab> mTabs = new ArrayList<>();

    private List<OnTabSelectedObserver> onTabSelectedListeners = new ArrayList<>();

    public BaseTabView(Context context) {
        super(context);
    }

    public BaseTabView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    //=============================LIFECYCLE=================================

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //Do nothing
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mOldPosHover = mCurPosHover;
                mCurPosHover = indexOfTab(event);
                if (mCurPosHover == mOldPosHover) {
                    break;
                }
                onEventHover(mOldPosHover, mCurPosHover);
                break;
            case MotionEvent.ACTION_UP:
                if (indexOfTab(event) == -1) {
                    break;
                }
                performTabSelected(mCurPosHover);
                break;

        }
        return true;
    }

    //==========================PUBLIC METHOD=============================

    public int getTabCount() {
        return mTabs == null ? 0 : mTabs.size();
    }

    @Override
    public void performTabSelected(int position) {
        if (position < 0)
            throw new IllegalStateException("Tab click position won't be less than 0");
        if (position >= getTabCount())
            throw new IllegalStateException("Tab click position won't be more than size of mTabs");
        mOldPosSlt = mCurPosSlt;
        mCurPosSlt = position;
        //Notify the client
        notifyTabSelected(mCurPosSlt, mOldPosSlt);
        onTabSelected();
    }

    private void notifyTabSelected(int curPos, int oldPos) {
        for (OnTabSelectedObserver listener : onTabSelectedListeners) {
            if (curPos == oldPos) {
                listener.onTabReselected(mTabs.get(curPos), curPos);
            } else {
                listener.onTabUnselected(mTabs.get(oldPos), oldPos);
                listener.onTabSelected(mTabs.get(curPos), curPos);
            }
        }
    }

    @Override
    public void setTabSelectedListener(OnTabSelectedObserver listener) {
        onTabSelectedListeners.add(listener);
    }

    @Override
    public void addTab(Tab tab) {
        mTabs.add(tab);
        requestLayout();
    }

    public void removeTab(Tab tab) {
        mTabs.remove(tab);
        requestLayout();
    }

    public List<Tab> getTabs() {
        return mTabs;
    }

    protected void onEventHover(int oldHoverPos, int curHoverPos) {
        //Do Nothing
    }

    protected void onTabSelected() {
        //Should extends to draw different ui
    }

    /**
     * Judge if params event was made in the bounds of this tab
     *
     * @return -1 when MotionEvent out of this tab group
     */
    protected abstract int indexOfTab(MotionEvent event);
}