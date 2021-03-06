package com.cursor.common.template.fragment.recycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cursor.common.R;
import com.cursor.common.widget.recyclerview.IControllerImpl;

/**
 * USER: ldx
 * DATE: 2015-06-16
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class SimpleListFragment<T> extends BaseListFragment<T> {

    @Override
    protected int getContentResId() {
        return R.layout.fragment_simple_list;
    }

    @Override
    protected RecyclerView.LayoutManager newLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

}
