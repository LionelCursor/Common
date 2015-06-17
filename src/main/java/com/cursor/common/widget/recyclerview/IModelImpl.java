package com.cursor.common.widget.recyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * USER: ldx
 * DATE: 2015-06-17
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class IModelImpl<T> implements IModel<T> {

    List<T> mModels;

    public IModelImpl() {
        mModels = new ArrayList<>();
    }

    public IModelImpl(List<T> models) {
        if (models == null) {
            mModels = new ArrayList<>();
        } else {
            mModels = models;
        }
    }

    @Override
    public void addItem(T bean) {
        mModels.add(bean);
    }

    @Override
    public void addItems(List<T> beans) {
        mModels.addAll(beans);
    }

    @Override
    public List<T> getData() {
        return mModels;
    }

    @Override
    public T getData(int position) {
        return mModels.get(position);
    }

    @Override
    public int getCount() {
        return mModels == null ? 0 : mModels.size();
    }
}
