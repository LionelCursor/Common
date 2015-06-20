package com.cursor.common.widget.recyclerview;


import android.view.View;

/**
 * USER: ldx
 * DATE: 2015-06-17
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 * <p/>
 * Adapter implements IController is the controller
 * IModel as it named
 * ViewHolder implements the function of view
 */
public interface IView<T> {

    IController<T> getController();

    void bindData(T data);

    void onViewCreated(View v);

}
