package com.cursor.common.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.cursor.common.AppData;

/**
 * USER: ldx
 * DATE: 2015-06-21
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class ViewUtils {
    public static View inflate(int resId) {
        RelativeLayout root = new RelativeLayout(AppData.getContext());
        return LayoutInflater.from(root.getContext()).inflate(resId, root, false);
    }
}
