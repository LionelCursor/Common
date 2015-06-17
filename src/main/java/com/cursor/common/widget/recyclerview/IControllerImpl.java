package com.cursor.common.widget.recyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * USER: ldx
 * DATE: 2015-06-17
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class IControllerImpl<T> extends RecyclerView.Adapter<IViewImpl<T>> implements IController<T> {

    public IControllerImpl() {
        mModel = newModel(null);
    }

    public IControllerImpl(List<T> beans){
        mModel = newModel(beans);
    }

    //===============================MODEL================================

    IModel<T> mModel;

    @Override
    public abstract IModel<T> newModel(@Nullable List<T> beans);

    @Override
    public IModel<T> getModel() {
        return mModel;
    }

    @Override
    public void addItem(T bean) {
        mModel.addItem(bean);
        this.notifyDataSetChanged();
    }

    @Override
    public void addItems(List<T> beans) {
        mModel.addItems(beans);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mModel == null ? 0 : mModel.getCount();
    }

    //===============================VIEW==============================

    @Override
    public abstract IViewImpl<T> newView(ViewGroup viewGroup, int viewType);

    @Override
    public IViewImpl<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return newView(parent, viewType);
    }

    @Override
    public void onBindViewHolder(IViewImpl<T> holder, int position) {
        holder.bindData(mModel.getData(position));
    }
}