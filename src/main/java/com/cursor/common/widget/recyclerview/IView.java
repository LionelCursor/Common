package com.cursor.common.widget.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * USER: ldx
 * DATE: 2015-06-17
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 * <p>
 * Adapter implements IController is the controller
 * IModel as it named
 * ViewHolder implements the function of view
 */
public interface IView<T> {

    IController<T> getController();
    void bindData(T data);

    static View inflate(ViewGroup parent, int ResId){
        return LayoutInflater.from(parent.getContext()).inflate(ResId,parent,false);
    }
}
