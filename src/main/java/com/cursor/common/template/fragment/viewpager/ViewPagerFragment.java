package com.cursor.common.template.fragment.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cursor.common.R;
import com.cursor.common.template.fragment.BaseTemplateFragment;
import com.cursor.common.utils.Logger;

import java.util.List;

/**
 * USER: ldx
 * DATE: 2015/6/2
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class ViewPagerFragment extends BaseTemplateFragment {

    public static final String TAG = "ViewPagerFragment";

    /**
     * Arguments key of ParamsContents
     */
    public static final String ARG_CONTENTS = "contents";

    private List<String> mParamsContents;

    private OnViewPagerFinishedListener mViewPagerListener;

    private ViewPager mViewPager;

    private FragmentPagerAdapter mAdapter;

    //===================================LIFECYCLE=======================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamsContents = getArguments().getStringArrayList(ARG_CONTENTS);
        }


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mViewPagerListener = (OnViewPagerFinishedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mViewPagerListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        Logger.e(TAG, "onCreateView");
        mViewPager = (ViewPager) view.findViewById(R.id.fragment_viewpager_viewpager);
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager(), mViewPager);
        mAdapter.addPages(getPages());
        mViewPager.setAdapter(mAdapter);
        if (mViewPagerListener != null) {
            mViewPagerListener.onViewPagerFinished(mViewPager);
        }
        return view;
    }

    //=========================PRIVATE METHOD=================================

    //==========================PUBLIC METHOD=================================

    public FragmentPagerAdapter getAdapter() {
        return mAdapter;
    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    public void setViewPagerFinishedListener(OnViewPagerFinishedListener listener) {
        mViewPagerListener = listener;
    }

    //=========================PROTECTED METHOD===============================

    public abstract List<FragmentPagerAdapter.Info> getPages();

    public interface OnViewPagerFinishedListener {
        void onViewPagerFinished(ViewPager viewPager);
    }
}