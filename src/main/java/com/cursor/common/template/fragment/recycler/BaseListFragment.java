package com.cursor.common.template.fragment.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.cursor.common.R;
import com.cursor.common.template.fragment.BaseTemplateFragment;
import com.cursor.common.widget.swiperefreshlayout.SmartSwipeRefreshLayout;

/**
 * USER: ldx
 * DATE: 2015-06-16
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class BaseListFragment extends BaseTemplateFragment{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentResId(),container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.common_list_recycler_view);
        mSwipeRefreshLayout = (SmartSwipeRefreshLayout) view.findViewById(R.id.common_list_swipe_refresh_layout);
        return view;
    }

    /**
     * Get resId in sub class
     * @return the res id of content
     */
    protected abstract int getContentResId();
}
