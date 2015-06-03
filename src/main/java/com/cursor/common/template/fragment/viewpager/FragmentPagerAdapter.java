package com.cursor.common.template.fragment.viewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.cursor.common.CommonConfig;
import com.cursor.common.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * USER: ldx
 * DATE: 2015/6/2
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter
        implements ViewPager.OnPageChangeListener{

    public static final String TAG = "FragmentPagerAdapter";

    public static final class Info{
        private final Class clazz;

        private final Bundle args;

        public Info(Class clazz, @Nullable Bundle args) {
            this.clazz = clazz;
            this.args = args;
        }
    }

    private List<Info> mClazzInfoes = new ArrayList<>();

    private ViewPager mViewPager;



    public FragmentPagerAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        this.mViewPager = viewPager;
    }


    public void addPage(Class clss, Bundle args){
        Info info = new Info(clss,args);
        mClazzInfoes.add(info);
        notifyDataSetChanged();
    }

    public void addPage(Info info){
        mClazzInfoes.add(info);
        notifyDataSetChanged();
    }

    public void addPages(List<Info> clazzInfoes){
        mClazzInfoes.addAll(clazzInfoes);
        notifyDataSetChanged();
    }

    //Fragment State Adapter

    @Override
    public Fragment getItem(int position) {
        Info info = mClazzInfoes.get(position);
        if (CommonConfig.DEBUG) Logger.d(TAG,"getItem %d , %s",position,info.clazz.getName());
        return Fragment.instantiate(mViewPager.getContext(), info.clazz.getName(), info.args);
    }

    @Override
    public int getCount() {
        return mClazzInfoes.size();
    }

    //ViewPager.OnPageChangeAdapter

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
