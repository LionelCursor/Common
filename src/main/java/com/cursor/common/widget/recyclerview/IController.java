package com.cursor.common.widget.recyclerview;

import android.view.ViewGroup;

import java.util.List;

/**
 * USER: ldx
 * DATE: 2015-06-17
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 * <p>
 * The controller of MVC design pattern
 * <p>
 * Adapter implements IController is the controller
 * IModel as it named
 * ViewHolder implements the function of view
 */
public interface IController<T> {

    IModel<T> getModel();

    IModel<T> newModel(List<T> beans);

    void addItem(T bean);

    void addItems(List<T> beans);

    IView<T> newView(ViewGroup viewGroup, int viewType);
}