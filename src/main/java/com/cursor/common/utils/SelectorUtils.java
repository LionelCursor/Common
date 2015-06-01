package com.cursor.common.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/**
 * USER: ldx
 * DATE: 2015/5/31
 * EMAIL: danxionglei@foxmail.com
 * PROJECT: MicroTravelNotes
 */
public class SelectorUtils {

    public static StateListDrawable generateSelector(Drawable normal,Drawable pressed){
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        drawable.addState(new int[]{-android.R.attr.state_pressed}, normal);
        return drawable;
    }
}
