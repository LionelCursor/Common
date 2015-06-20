package com.cursor.common.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * USER: ldx
 * DATE: 2015-06-17
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public abstract class IViewImpl<T> extends RecyclerView.ViewHolder implements IView<T> {

    private IController<T> mController;

    public IViewImpl(IController<T> controller, View itemView) {
        super(itemView);
        mController = controller;
    }

    @Override
    public IController<T> getController() {
        return mController;
    }

    @Override
    public abstract void bindData(T data);

    public abstract void onViewCreated(View v);

    public static View inflate(ViewGroup parent, int ResId){
        return LayoutInflater.from(parent.getContext()).inflate(ResId,parent,false);
    }
}
