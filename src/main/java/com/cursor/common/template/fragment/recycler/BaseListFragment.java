package com.cursor.common.template.fragment.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cursor.common.R;
import com.cursor.common.template.fragment.BaseTemplateFragment;
import com.cursor.common.widget.recyclerview.OnScrollBottomListener;
import com.cursor.common.widget.swiperefreshlayout.SmartSwipeRefreshLayout;

/**
 * USER: ldx
 * DATE: 2015-06-16
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class BaseListFragment extends BaseTemplateFragment {

    private static final int FIRST_PAGE = 0;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.ItemAnimator mItemAnimator;
    private RecyclerView.LayoutManager mLayoutManager;


    private int mPage = FIRST_PAGE;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentResId(), container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.common_list_recycler_view);
        mSwipeRefreshLayout = (SmartSwipeRefreshLayout) view.findViewById(R.id.common_list_swipe_refresh_layout);

        mRecyclerView.setAdapter(mAdapter = newAdapter());
        mRecyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(getActivity()));

        mItemAnimator = new DefaultItemAnimator();
        mItemAnimator.setAddDuration(300);
        mItemAnimator.setRemoveDuration(300);
        mItemAnimator.setMoveDuration(500);
        mRecyclerView.setItemAnimator(mItemAnimator);

        mRecyclerView.addOnScrollListener(new OnScrollBottomListener() {
            @Override
            public void onBottom() {
                loadNextPage();
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(FIRST_PAGE);
            }
        });

        loadData(FIRST_PAGE);

        return view;
    }

    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        mItemAnimator = itemAnimator;
        mRecyclerView.setItemAnimator(itemAnimator);
    }

    public void setItemAnimatorDuration(long add, long remove, long move) {
        mItemAnimator.setAddDuration(add);
        mItemAnimator.setRemoveDuration(remove);
        mItemAnimator.setMoveDuration(move);
    }

    public void loadData(int page) {

    }

    public void loadNextPage() {
        loadData(mPage + 1);
    }

    /**
     * Get resId in sub class
     *
     * @return the res id of content
     */
    protected abstract int getContentResId();

    protected abstract RecyclerView.Adapter newAdapter();

    protected abstract RecyclerView.LayoutManager newLayoutManager();

    /**
     * Invoke it after onCreateView() or it will be null
     *
     * @return The reference of recyclerView.
     */
    @Nullable
    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }
}