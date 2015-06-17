package com.cursor.common.widget.recyclerview;

import java.util.List;

/**
 * USER: ldx
 * DATE: 2015-06-16
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 * <p>
 * Adapter implements IController is the controller
 * IModel as it named
 * ViewHolder implements the function of view
 */
public interface IModel<T> {

    void addItem(T bean);

    void addItems(List<T> beans);

    List<T> getData();

    T getData(int position);

    int getCount();

}
