package com.cursor.common.template.fragment.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cursor.common.R;
import com.cursor.common.template.fragment.BaseTemplateFragment;
import com.cursor.common.widget.recyclerview.OnScrollBottomListener;
import com.cursor.common.widget.recyclerview.IControllerImpl;
import com.cursor.common.widget.swiperefreshlayout.SmartSwipeRefreshLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * USER: ldx
 * DATE: 2015-06-16
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class BaseListFragment<T> extends BaseTemplateFragment {

    private static final String TAG  = "BaseListFragment";

    private static final int FIRST_PAGE = 0;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private IControllerImpl<T> mControllerAdapter;
    private RecyclerView.ItemAnimator mItemAnimator;
    private RecyclerView.LayoutManager mLayoutManager;

    private int mPage = FIRST_PAGE;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentResId(), container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.common_list_recycler_view);
        mSwipeRefreshLayout = (SmartSwipeRefreshLayout) view.findViewById(R.id.common_list_swipe_refresh_layout);

        mRecyclerView.setAdapter(mControllerAdapter = newAdapter());
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
        boolean isFlushAll = page == FIRST_PAGE;
        if(isFlushAll&&!mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(true);
        }
        onLoadData(isFlushAll, page);
    }

    public void loadNextPage() {
        loadData(mPage + 1);
    }

    public void addItem(T bean){
        mControllerAdapter.addItem(bean);
    }

    public void addItemModel(List<T> models){
        Log.e(TAG,""+models.size());
        mControllerAdapter.addItems(models);
    }

    /**
     * Get resId in subclass
     *
     * @return the res id of content
     */
    protected abstract int getContentResId();

    protected abstract IControllerImpl<T> newAdapter();

    protected abstract RecyclerView.LayoutManager newLayoutManager();

    /**
     * How to load data was specified in subclass.
     * @param isAllFlush Something matters with mSwipeBackLayout
     * @param page Which page should be load
     */
    protected abstract void onLoadData(boolean isAllFlush,int page);

    /**
     * Invoke it after onCreateView() or it will be null
     *
     * @return The reference of recyclerView.
     */
    @Nullable
    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public abstract class ListResponseListener<H> extends com.cursor.common.io.net.NetResponseListener<H> {
        private boolean isAllFlush;

        private int page;

        public ListResponseListener(boolean isAllFlush, int page){
            this.isAllFlush = isAllFlush;
            this.page = page;
        }

        @Override
        public void onResponse(Exception e, ArrayList<H> result) {
            super.onResponse(e, result);
            mPage = page;
            if(isAllFlush){
                mControllerAdapter.clear();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            onDataLoaded(e, result);
            mControllerAdapter.notifyDataSetChanged();
        }

        public abstract void onDataLoaded(Exception e, ArrayList<H> result);
    }
}