package com.cursor.common.widget.swiperefreshlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * USER: ldx
 * DATE: 2015-06-16
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 *
 * Smart to compat the RecyclerView the canChildScroll()
 */
public class SmartSwipeRefreshLayout extends SwipeRefreshLayout {

    private RecyclerView mRecyclerView;

    private CanChildScrollUpListener mCanChildScrollUpListener;

    public SmartSwipeRefreshLayout(Context context) {
        super(context);
    }

    public SmartSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        if(child instanceof RecyclerView){
            attachRecyclerView((RecyclerView) child);
        }
    }

    @Override
    public boolean canChildScrollUp() {
        if (null != mCanChildScrollUpListener) {
            return mCanChildScrollUpListener.canChildScrollUp();
        } else if (null != mRecyclerView){
            return mRecyclerView.canScrollVertically(-1);
        }
        return super.canChildScrollUp();
    }

    public void attachRecyclerView(@NonNull RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    public void setCanChildScrollUpListener(CanChildScrollUpListener canChildScrollUpListener) {
        mCanChildScrollUpListener = canChildScrollUpListener;
    }

    public interface CanChildScrollUpListener {
        boolean canChildScrollUp();
    }

}
